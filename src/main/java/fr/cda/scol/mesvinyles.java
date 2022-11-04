package fr.cda.scol;




import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import java.io.*;
import java.util.List;

public class mesvinyles {
    public static void main(String[] args) throws IOException {

        PrintWriter ecrire;
        BufferedReader impression = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Entrez le nom de l'artiste : ");
        String search = impression.readLine();


        String url = "https://mesvinyles.fr/fr/recherche?controller=search&s=" + search;

        WebClient webClient = new WebClient();

        webClient.getOptions().setUseInsecureSSL(true);
        webClient.getOptions().setCssEnabled(false);
        webClient.getOptions().setJavaScriptEnabled(false);
        HtmlPage htmlPage = webClient.getPage(url);

        File rep = new File("ResultatDeRecherches");
        rep.mkdir();

        String nomFichierSortie = "ResultatDeRecherches" + File.separator + search + ".txt";

        List<HtmlAnchor> li = (htmlPage.getAnchors());
        // récupération du lien vers un produit
        List<HtmlElement> general = htmlPage.getByXPath("//h3/a[@href]");


        for (HtmlAnchor e : li) {

            for (HtmlElement gr : general) {

                HtmlPage htmlPage1 = webClient.getPage(e.click().getUrl());
                String ValueNA="";
                String ValuePrix="";
                String ValueDesc = "";
                String ValueDate = "";
                String ValueNAlbum = "";
                String ValueGenre = "";

                    List<HtmlElement> prix = htmlPage1.getByXPath("//span[@itemprop='price']");
                    List<HtmlElement> description = htmlPage1.getByXPath("//div[@class='product-description']/table/tbody/tr/td");
                    List<HtmlElement> date = htmlPage1.getByXPath("div[@itemprop='description']/p[1]");
                    List<HtmlElement> album = htmlPage1.getByXPath("//h1[@class='productpage_title']");
                    List<HtmlElement> nomArtistes = htmlPage1.getByXPath("//div[@itemprop='description']/p[2]");
                    List<HtmlElement> genre = htmlPage1.getByXPath("//div[@itemprop='description']/p[2]");

                    for (HtmlElement na : nomArtistes) {
                        ValueNA = na.getTextContent();
                        System.out.println(ValueNA);

                    }
                    for (HtmlElement alb : album) {
                        ValueNAlbum = alb.getTextContent();
                        System.out.println(ValueNAlbum);
                    }
                    for(HtmlElement g : genre) {
                        ValueGenre = g.getTextContent();
                        System.out.println(ValueGenre);
                    }
                    for (HtmlElement desc : description) {
                        ValueDesc = desc.getTextContent();
                        System.out.println(ValueDesc);
                    }
                    for (HtmlElement dt : date) {
                        ValueDate = dt.getTextContent();
                        System.out.println(ValueDate);
                    }
                for (HtmlElement px : prix) {
                    ValuePrix = px.getTextContent();
                    System.out.println(ValuePrix);
                }
                }

        }
    }

}

