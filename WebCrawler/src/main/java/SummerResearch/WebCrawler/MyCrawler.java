package SummerResearch.WebCrawler;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;

public class MyCrawler extends WebCrawler {
	public static List<String> patterns = new ArrayList<String>();
	
	public boolean shouldVisit (Page referringPage, WebURL url){
		String href = url.getURL().toLowerCase();
		for(String s : patterns){
			Matcher m = Pattern.compile(s).matcher(href);
			if(m.matches()){
				return true;
			}
		}
		return false;
	}


	public void visit(Page page){
		String url = page.getWebURL().getURL();
		System.out.println("URL: " + url);
		
		
		 if (page.getParseData() instanceof HtmlParseData) {
             HtmlParseData htmlParseData = (HtmlParseData) page.getParseData();
             String text = htmlParseData.getText();
             String html = htmlParseData.getHtml();
             Set<WebURL> links = htmlParseData.getOutgoingUrls();

             System.out.println("Text length: " + text.length());
             System.out.println("Html length: " + html.length());
             System.out.println("Number of outgoing links: " + links.size());
             
             if(url.toLowerCase().matches(App.fD.getActualPattern())){
            	 HTMLExtractor extractor = new HTMLExtractor(html, url);
            	 extractor.beginExtraction();
             }
             
             System.out.println("");
         }
	}
	
}
