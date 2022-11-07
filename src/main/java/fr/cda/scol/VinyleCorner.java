package fr.cda.scol;



import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import java.io.*;
import java.util.List;

public class VinyleCorner {
    public static void main(String[] args) throws IOException {

        PrintWriter ecrire;
        BufferedReader impression = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Entrez le nom de l'artiste : ");
        String search = impression.readLine();


        String url = "https://www.vinylcorner.fr/recherche?controller=search&s=" + search;

        WebClient webClient = new WebClient();

        webClient.getOptions().setUseInsecureSSL(true);
        webClient.getOptions().setCssEnabled(false);
        webClient.getOptions().setJavaScriptEnabled(false);
        HtmlPage htmlPage = webClient.getPage(url);


        String nomFichierSortie = "ResultatDeRecherches" + File.separator + search.toLowerCase() + ".txt";

            String ValueNA="";
            String ValuePrix="";
            String ValueDesc = "";
            String ValueDate = "";
            String ValueNAlbum = "";
            String ValueGenre = "";

            List<HtmlAnchor> li = (htmlPage.getAnchors());
            List<HtmlElement> general = htmlPage.getByXPath("//h2/a[@href]");

            for(HtmlElement e : li) {

                for (HtmlElement gr : general) {

                    HtmlPage htmlPage1 = webClient.getPage(gr.click().getUrl());


                    List<HtmlElement> nomArtistes = htmlPage1.getByXPath("//h1[@class='productpage_title']");
                    List<HtmlElement> prix = htmlPage1.getByXPath("//span[@itemprop='price']");
                    List<HtmlElement> description = htmlPage1.getByXPath("//p[@class='product-info']");
                    List<HtmlElement> date = htmlPage1.getByXPath("//strong");
                    List<HtmlElement> album = htmlPage1.getByXPath("//h2[@class='artist']");
                    List<HtmlElement> genre = htmlPage1.getByXPath("//p[@class='ref-genre-cat show-list-only']");

                    for (HtmlElement na : nomArtistes) {
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
                    for (HtmlElement dt : date) {
                        ValueDate = dt.getTextContent();
                        System.out.println(ValueDate);
                    }
                    for (HtmlElement g : genre) {
                        ValueGenre = g.getTextContent();
                        System.out.println(ValueGenre);
                    }
                    for (HtmlElement px : prix) {
                        ValuePrix = px.getTextContent();
                        System.out.println(ValuePrix);
                    }
                }
            }
        }
    }

