package SummerResearch.WebCrawler;

import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;
import helperClasses.ForumDetail;
import helperClasses.ForumDetailExamples;

/**
 * Hello world!
 *
 */
public class App 
{
	
	static ForumDetail fD = null;
    public static void main( String[] args ) throws Exception
    {
    	
    	setMode(args);
    	if(fD == null){
    		System.out.println("Invalid Input");
    		return;
    	}
    	
    	int numberOfCrawlers = 10;
    	
    	CrawlConfig config = new CrawlConfig();
    	config.setCrawlStorageFolder("data/crawler");
    	config.setMaxPagesToFetch(20); //SET TO  -1 TO FETCH ALL PAGES
    	config.setPolitenessDelay(1000); //OPTIONAL, DECREASE FOR SPEED
    	config.setMaxDepthOfCrawling(10); //SET TO -1 TO INCREASE DEPTH
    	
    	PageFetcher pageFetcher = new PageFetcher(config);
    	RobotstxtConfig rtc = new RobotstxtConfig();
    	RobotstxtServer rts = new RobotstxtServer(rtc, pageFetcher);
    	CrawlController contr = new CrawlController(config, pageFetcher, rts);
        
    	MyCrawler.patterns = fD.getAcceptedPatterns();
    	contr.addSeed(fD.getBaseURL());
    	contr.start(MyCrawler.class, numberOfCrawlers);
    }
    
    /**
     * Must be editted to add new forums
     * @param args
     */
    public static void setMode(String[] args){
    	if(args.length != 1){
    		return;
    	}
    	
    	if(args[0].trim().toLowerCase().equals("xero")){
    		App.fD = ForumDetailExamples.getXeroDetails();    	
    	}
    	else if(args[0].trim().toLowerCase().equals("microsoft")){
    		//setup crawler for microsoft
    		App.fD = ForumDetailExamples.getMicrosoftDetails();
    	}
    }
}
