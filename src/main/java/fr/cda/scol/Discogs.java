package fr.cda.scol;


import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import java.io.*;
import java.util.List;

public class Discogs {
    public static void main(String[] args) throws IOException {

        PrintWriter ecrire;
        BufferedReader impression = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Entrez le nom de l'artiste : ");
        String search = impression.readLine();


        String url = "https://www.discogs.com/fr/search/?q=" + search +"&type=all&type=all";

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
        String ValuePrixExpedition ="";
        String ValueDesc = "";
        String ValueDate = "";
        String ValueNAlbum = "";
        String ValueGenre = "";

        List<HtmlAnchor> li = (htmlPage.getAnchors());
        List<HtmlElement> general = htmlPage.getByXPath("//html/body/div[1]/div[4]/div[3]/div[2]/ul/li[1]/div[1]/a");



        for(HtmlElement e : li) {

            for (HtmlElement gr : general) {

                HtmlPage htmlPage1 = webClient.getPage(gr.click().getUrl());


                List<HtmlElement> page2 = htmlPage1.getByXPath("//span[@class='marketplace_for_sale_count']/a[@href]");

                for (HtmlElement p2 : page2) {


                    HtmlPage htmlPage2 = webClient.getPage(p2.click().getUrl());


                    List<HtmlElement> page3 = htmlPage2.getByXPath("//td[@class='item_description']/strong/a[@href]");

                    for (HtmlElement p3 : page3) {

                        HtmlPage htmlPage3 = webClient.getPage(p3.click().getUrl());


                        List<HtmlElement> nomArtistes = htmlPage3.getByXPath("//h1[@profile_title]/span/span/a");
                        List<HtmlElement> prixProduit = htmlPage3.getByXPath("//div[@class='section_content']/p[@class='pricing_info']/span[@class='price']");
                        List<HtmlElement> prixProduitExpedition = htmlPage3.getByXPath("//div[@class='section_content']/p[@class='pricing_info']/span[@class='reduced']");
                        List<HtmlElement> description = htmlPage3.getByXPath("//td[@class='track tracklist_track_title mini_playlist_track_has_artist']/span[@class='tracklist_track_title']");
                        List<HtmlElement> date = htmlPage3.getByXPath("//html/body/div[1]/div[4]/div[1]/div/div[1]/div/div[1]/div[9]");
                        List<HtmlElement> album = htmlPage3.getByXPath("//h1[@profile_title]/span");
                        List<HtmlElement> genre = htmlPage3.getByXPath("//html/body/div[1]/div[4]/div[1]/div/div[1]/div/div[1]/div[11]\n");

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
                        for (HtmlElement dt : date) {
                            ValueDate = dt.getTextContent().replaceAll("\\s+", "");
                            System.out.println("date: " + ValueDate);
                        }
                        for (HtmlElement g : genre) {
                            ValueGenre = g.getTextContent().replaceAll("\\s+", "");
                            System.out.println("genre" + ValueGenre);
                        }


                        for (HtmlElement pxp : prixProduit) {
                            ValuePrix = pxp.getTextContent();
                            ValuePrix = ValuePrix.replaceAll("\\d", "");

                            System.out.println("prix: " + ValuePrix);
                        }
                        for (HtmlElement pxpe : prixProduitExpedition) {
                            ValuePrixExpedition = pxpe.getTextContent();
                            ValuePrixExpedition =  ValuePrixExpedition.replaceAll("\\D+", "");

                            System.out.println("prix expedition: " +  ValuePrixExpedition);
                            System.out.println("prix total :" + (ValuePrixExpedition + ValuePrix));
                        }
                    }
                }
            }
        }
    }
}