package fr.cda.scol;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import java.io.*;
import java.util.List;

public class VinyleCorner {
    public static String VinylCornerScrapping(String search) throws IOException {


        String url = "https://www.vinylcorner.fr/catalogsearch/result/?q=" + search + "d&category=3";
        String res = "";
        try {
            WebClient webClient = new WebClient();

            webClient.getOptions().setUseInsecureSSL(true);
            webClient.getOptions().setCssEnabled(false);
            webClient.getOptions().setJavaScriptEnabled(false);
            HtmlPage htmlPage = webClient.getPage(url);


            String ValueNA = "";
            String ValuePrix = "";
            String ValueDesc = "";
            String ValueDate = "";
            String ValueNAlbum = "";
            String ValueGenre = "";



            List<HtmlElement> general = htmlPage.getByXPath("//strong[@class='product name product-item-name']/a[@href]");


            for (HtmlElement gr : general) {

                HtmlPage htmlPage1 = webClient.getPage(gr.click().getUrl());


                List<HtmlElement> nomArtistes = htmlPage1.getByXPath("//p[@class='product-artiste']");
                List<HtmlElement> prix = htmlPage1.getByXPath("//html/body/div[2]/main/div/div/div/div[2]/div[2]/div[4]/div/span/span/span\n");
                List<HtmlElement> description = htmlPage1.getByXPath("//html/body/div[2]/main/div/div/div/div[6]/span\n");
                List<HtmlElement> date = htmlPage1.getByXPath("//html/body/div[2]/main/div/div/div/div[5]/div[2]/div/div/div[1]/div[2]/p[2]\n");
                List<HtmlElement> album = htmlPage1.getByXPath("//span[@data-ui-id='page-title-wrapper']");
                List<HtmlElement> genre = htmlPage1.getByXPath("//html/body/div[2]/main/div/div/div/div[5]/div[1]/ul/li[2]/a/span\n");

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
                    System.out.println("description:" + ValueDesc);
                }
                for (HtmlElement dt : date) {
                    ValueDate = dt.getTextContent();
                    System.out.println("date:" + ValueDate);
                }
                for (HtmlElement g : genre) {
                    ValueGenre = g.getTextContent();
                    System.out.println("genre:" + ValueGenre);
                }
                for (HtmlElement px : prix) {
                    ValuePrix = px.getTextContent();
                    System.out.println("prix:" + ValuePrix);
                }
                res = "Nom Artiste:" + ValueNA + "\n" +
                        "Nom Album:" + ValueNAlbum + "\n" +
                        "Description:" + ValueDesc + "\n" +
                        "Genre:" + ValueGenre + "\n" +
                        "Date:" + ValueDate + "\n" +
                        "Prix:" + ValuePrix + "\n" +
                        " lien : " + htmlPage1.getUrl() +
                        "\n--------------------------------------------------------------------\n";
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return res;
    }
}