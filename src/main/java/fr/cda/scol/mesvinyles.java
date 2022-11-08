package fr.cda.scol;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import java.io.*;
import java.util.List;

public class mesvinyles {
    public static String ScrapingMesvinyles(String search) throws IOException {


        String url = "https://mesvinyles.fr/fr/recherche?controller=search&s=" + search;

        WebClient webClient = new WebClient();

        webClient.getOptions().setUseInsecureSSL(true);
        webClient.getOptions().setCssEnabled(false);
        webClient.getOptions().setJavaScriptEnabled(false);
        HtmlPage htmlPage = webClient.getPage(url);




        // récupération du lien vers un produit
        List<HtmlElement> general = htmlPage.getByXPath("//h3/a[@href]");
        String res="";

        try {
            for (HtmlElement gr : general) {

                HtmlPage htmlPage1 = webClient.getPage(gr.click().getUrl());
                String ValueNA = "";
                String ValuePrix = "";
                String ValueDesc = "";
                String ValueDate = "";
                String ValueNAlbum = "";
                String ValueGenre = "";

                List<HtmlElement> prix = htmlPage1.getByXPath("//span[@itemprop='price']");
                List<HtmlElement> description = htmlPage1.getByXPath("//div[@class='product-description']/table/tbody/tr/td");
                List<HtmlElement> date = htmlPage1.getByXPath("//html/body/main/section/div/div/div/section/section/div/div/div[2]/section[1]/dl/dd[5]\n");
                List<HtmlElement> album = htmlPage1.getByXPath("//h1[@class='h1 productpage_title']");
                List<HtmlElement> nomArtistes = htmlPage1.getByXPath("//div[@class='product-description']/p[2]");
                List<HtmlElement> genre = htmlPage1.getByXPath("//div[@itemprop='description']/p[2]");

                for (HtmlElement na : nomArtistes) {
                    ValueNA = na.getTextContent();
                    System.out.println(ValueNA);

                }
                for (HtmlElement alb : album) {
                    ValueNAlbum = alb.getTextContent();
                    System.out.println(ValueNAlbum);
                }
                for (HtmlElement g : genre) {
                    ValueGenre = g.getTextContent();
                    System.out.println(ValueGenre);
                }
               /* for (HtmlElement desc : description) {
                    ValueDesc = desc.getTextContent();
                    System.out.println(ValueDesc);
                }*/
                for (HtmlElement dt : date) {
                    ValueDate = dt.getTextContent();
                    System.out.println(ValueDate);
                }
                for (HtmlElement px : prix) {
                    ValuePrix = px.getTextContent();
                    System.out.println(ValuePrix);
                }
                res += "Nom Artiste:" + ValueNA + "\n" +
                        "Nom Album:" + ValueNAlbum + "\n" +
                        "Date:" + ValueDate + "\n" +
                        "Prix:" + ValuePrix + "\n" +
                        "lien : " + htmlPage1.getUrl() +
                        "\n--------------------------------------------------------------------\n";
            }
            } catch(IOException e) {
                e.printStackTrace();
            }

        return res;
        }

}

