package fr.cda.scol;


import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import java.io.*;
import java.util.List;

public class Leboncoin {

    public static String ScrapLeboncoin(String searchTitle) {
        String searchPriceMin ="";
        String searchPriceMax ="";
        String url = "https://leboncoin.fr/recherche?category=26&text=" + searchTitle + "&price=" + searchPriceMin + "-" + searchPriceMax;
        String res = "";
        try {
            WebClient webClient = new WebClient();

            webClient.getOptions().setUseInsecureSSL(true);
            webClient.getOptions().setCssEnabled(false);
            webClient.getOptions().setJavaScriptEnabled(false);
            HtmlPage htmlPage = webClient.getPage(url);


            List<HtmlElement> li = htmlPage.getByXPath("//a/div[2]/div[2]/div[1]/p");

            for (HtmlElement e : li) {
                HtmlPage htmlPage1 = webClient.getPage(e.click().getUrl());
                String nomArticle = "";
                String prixArticle = "";
                String description = "";

                List<HtmlElement> nom = htmlPage1.getByXPath("//h1[@data-qa-id='adview_title']");
                List<HtmlElement> prix = htmlPage1.getByXPath("//span[@class='Roh2X _3gP8T _35DXM _25LNb']");
                List<HtmlElement> desc = htmlPage1.getByXPath("//p[@class='sc-bhlBdH gOkeRT']");

                for (HtmlElement n : nom) {
                    nomArticle = n.getTextContent();

                }
                for (HtmlElement p : prix) {
                    prixArticle = p.getTextContent();
                    prixArticle = prixArticle.replace("\u00a0", "");
                }
                for (HtmlElement d : desc) {
                    description = d.getTextContent();
                }
                res += "Article : " + nomArticle +
                        "\n Prix : " + prixArticle +
                        "\n Description de l'article : " + description +
                        "\n lien : " + htmlPage1.getUrl() +
                        "\n--------------------------------------------------------------------\n";
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return res;
    }
}