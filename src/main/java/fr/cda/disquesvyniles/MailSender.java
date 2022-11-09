package fr.cda.disquesvyniles;

import fr.cda.disquesvyniles.*;
import sendinblue.*;

import sendinblue.auth.ApiKeyAuth;
import sibApi.TransactionalEmailsApi;
import sibModel.*;
import sibApi.AccountApi;
import io.github.cdimascio.dotenv.Dotenv;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;


/** class gerant l envois de mail **/

public class MailSender{
    public static void send(String mail,String search) {
        Dotenv dotenv = Dotenv.load();
        ApiClient defaultClient = Configuration.getDefaultApiClient();

        // Configure API key authorization: api-key
        ApiKeyAuth apiKey = (ApiKeyAuth) defaultClient.getAuthentication("api-key");
        apiKey.setApiKey("");

        AccountApi apiInstance = new AccountApi();

        try {

            String path = "search.txt";
            PrintWriter ecrire = new PrintWriter(new BufferedWriter
                    (new FileWriter(path)));
            ecrire.println(search);
            ecrire.close();
            TransactionalEmailsApi api = new TransactionalEmailsApi();
            SendSmtpEmailSender sender = new SendSmtpEmailSender();

            sender.setEmail("damien.miremont@gmail.com");
            sender.setName("Damien Miremont");
            List<SendSmtpEmailTo> toList = new ArrayList<SendSmtpEmailTo>();
            SendSmtpEmailTo to = new SendSmtpEmailTo();

            to.setEmail(mail);
            toList.add(to);
            SendSmtpEmailReplyTo replyTo = new SendSmtpEmailReplyTo();
            replyTo.setEmail(dotenv.get("SENDER_MAIL"));
            replyTo.setName("John Doe");
            SendSmtpEmailAttachment attachment = new SendSmtpEmailAttachment();
            attachment.setName("search.txt");
            byte[] encode = Files.readAllBytes(Paths.get("search.txt"));
            attachment.setContent(encode);
            List<SendSmtpEmailAttachment> attachmentList = new ArrayList<SendSmtpEmailAttachment>();
            attachmentList.add(attachment);
            Properties headers = new Properties();
            headers.setProperty("Some-Custom-Name", "unique-id-1234");
            Properties params = new Properties();
            params.setProperty("parameter", "My param value");
            params.setProperty("subject", "RÃ©sultat de la recherche");
            SendSmtpEmail sendSmtpEmail = new SendSmtpEmail();
            sendSmtpEmail.setSender(sender);
            sendSmtpEmail.setTo(toList);
            sendSmtpEmail.setHtmlContent("<html><body><h1>This is my first transactional email {{params.parameter}}</h1></body></html>");
            sendSmtpEmail.setSubject("{{params.subject}}");
            sendSmtpEmail.setReplyTo(replyTo);
            sendSmtpEmail.setAttachment(attachmentList);
            sendSmtpEmail.setHeaders(headers);
            sendSmtpEmail.setParams(params);

            CreateSmtpEmail response = api.sendTransacEmail(sendSmtpEmail);
            System.out.println(response.toString());

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}
