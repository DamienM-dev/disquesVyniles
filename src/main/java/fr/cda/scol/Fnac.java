package fr.cda.scol;


import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import java.io.*;
import java.util.List;

public class Fnac {
    public static void main(String[] args) throws IOException {

        PrintWriter ecrire;
        BufferedReader impression = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Entrez le nom de l'artiste : ");
        String search = impression.readLine();


        String url = "https://www.fnac.com/SearchResult/ResultList.aspx?SCat=0&Search=" + search + "&sft=1&sa=1";

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

        List<HtmlAnchor> li = (htmlPage.getAnchors());
        List<HtmlElement> general = htmlPage.getByXPath("//p[@class='Article-desc']/span/a[@href]");

        for(HtmlElement e : li) {

            for (HtmlElement gr : general) {

                HtmlPage htmlPage1 = webClient.getPage(gr.click().getUrl());


                List<HtmlElement> nomArtistes = htmlPage1.getByXPath("//html/body/div[2]/div/div[1]/div[2]/div[2]/div[2]/div[2]/dl[1]/dd/p/a");
                List<HtmlElement> prix = htmlPage1.getByXPath("//span[@class='f-faPriceBox__price userPrice js-ProductBuy-standardCheckable checked']");
                List<HtmlElement> description = htmlPage1.getByXPath("//div[@class='f-productSection__body f-productDesc__body']/div[@class='f-productDesc__raw']");
                List<HtmlElement> date = htmlPage1.getByXPath("//html/body/div[2]/div/div[1]/div[1]/section/div[1]/span[3]");
                List<HtmlElement> album = htmlPage1.getByXPath("//h1[@class='f-productHeader-Title']");

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
                    ValueDate = dt.getTextContent();
                    System.out.println("date: " + ValueDate);
                }

                for (HtmlElement px : prix) {
                    ValuePrix = px.getTextContent();
                    System.out.println("prix: " + ValuePrix);
                }
            }
        }
    }
}