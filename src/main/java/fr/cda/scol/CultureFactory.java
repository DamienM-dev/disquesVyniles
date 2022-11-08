/*package org.example;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import java.io.*;
import java.util.List;
public class CultureFactory {
    public static void main(String[] args) {
        PrintWriter ecrire;
        BufferedReader impression = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Entrez le nom de l'artiste : ");
        String search = impression.readLine();


        String url = "https://culturefactory.fr/recherche?controller=search&s=" + search;

        WebClient webClient = new WebClient();

        webClient.getOptions().setUseInsecureSSL(true);
        webClient.getOptions().setCssEnabled(false);
        webClient.getOptions().setJavaScriptEnabled(false);
        HtmlPage htmlPage = webClient.getPage(url);

        File rep = new File("ResultatDeRecherches");
        rep.mkdir();

        String nomFichierSortie = "ResultatDeRecherches" + File.separator + search.toLowerCase() + ".txt";

        String ValueNA="";
        String ValuePrix="";
        String ValueDesc = "";
        String ValueDate = "";
        String ValueNAlbum = "";
        String ValueGenre = "";


        List<HtmlElement> general = htmlPage.getByXPath("//section/section/div[3]/div/div[1]/div[1]/article/div[2]/h4/a]");

        for (HtmlElement gr : general) {

            HtmlPage htmlPage1 = webClient.getPage(gr.click().getUrl());

            List<HtmlElement> nomArtistes = htmlPage1.getByXPath("//h1[@class='h1 namne_details']");
            List<HtmlElement> prix = htmlPage1.getByXPath("//div[@class='product-price']");
            List<HtmlElement> description = htmlPage1.getByXPath("//div[@id='tab-content']");
            List<HtmlElement> album = htmlPage1.getByXPath("//itemprop[@class='name']");

            for(HtmlElement na : nomArtistes) {
                ValueNA = na.getTextContent();
                System.out.println(ValueNA);

            }
            for (HtmlElement alb : album) {
                ValueNAlbum = alb.getTextContent();
                System.out.println(ValueNAlbum);
            }
            for (HtmlElement desc : description) {
                ValueDesc = desc.getTextContent();
                System.out.println(ValueDesc);
            }
            for(HtmlElement px : prix) {
                ValuePrix = px.getTextContent();
                System.out.println(ValuePrix);
            }
        }


    }

}
 */
package fr.cda.scol;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import java.io.*;
import java.util.List;

public class CultureFactory {
    public static String CultureFactoryScrapping(String search) throws IOException {


        String url = "https://culturefactory.fr/recherche?controller=search&s=" + search;

        WebClient webClient = new WebClient();

        webClient.getOptions().setUseInsecureSSL(true);
        webClient.getOptions().setCssEnabled(false);
        webClient.getOptions().setJavaScriptEnabled(false);
        HtmlPage htmlPage = webClient.getPage(url);



        String ValueNA = "";
        String ValuePrix = "";
        String ValueDesc = "";
        String ValueNAlbum = "";
        String res ="";

        try {
            List<HtmlElement> general = htmlPage.getByXPath("//h4/a[@href]");


            for (HtmlElement gr : general) {

                HtmlPage htmlPage1 = webClient.getPage(gr.click().getUrl());
                List<HtmlElement> nomArtistes = htmlPage1.getByXPath("//html/body/main/div[1]/div[1]/nav/ol/li[2]/a/span\n");
                List<HtmlElement> prix = htmlPage1.getByXPath("//html/body/main/div[1]/div[2]/div/div/section/div[1]/div[2]/div[1]/div[1]/div/span");
                List<HtmlElement> description = htmlPage1.getByXPath("//div[@class='product-desc']");
                List<HtmlElement> album = htmlPage1.getByXPath("//h1[@class='h1 namne_details']");

                for (HtmlElement na : nomArtistes) {
                    ValueNA = na.getTextContent();
                    System.out.println("nom artiste:" + ValueNA);

                }
                for (HtmlElement alb : album) {
                    ValueNAlbum = alb.getTextContent();
                    System.out.println("nom album:" + ValueNAlbum);
                }
                for (HtmlElement desc : description) {
                    ValueDesc = desc.getTextContent();
                    System.out.println("description" + ValueDesc);
                }
                for (HtmlElement px : prix) {
                    ValuePrix = px.getTextContent();
                    System.out.println("prix: " + ValuePrix);
                }
                res += "Nom Artiste: " + ValueNA + "\n" +
                        "Nom Album: " + ValueNAlbum + "\n" +
                        "Description: " + ValueDesc + "\n" +
                        "Prix: " + ValuePrix + "\n" +
                        "lien : " + htmlPage1.getUrl() +
                        "\n--------------------------------------------------------------------\n";
            }

            } catch(IOException e) {
                e.printStackTrace();
            }
            return res;



    }
    }



