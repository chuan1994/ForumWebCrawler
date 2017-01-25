package SummerResearch.WebCrawler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class HTMLExtractor {
	private ExtractedData ed = new ExtractedData();
	private String html;
	
	public HTMLExtractor(String html, String url){
		this.html = html;
		ed.setURL(url);
	}
	
	public void beginExtraction(){
        Document doc = Jsoup.parseBodyFragment(html);
        if(App.fD.getName() != null){
            ed.setForumName(App.fD.getName()); //NEEDS DOING
        }
        if(App.fD.getQuestionTitleId().size() > 0){
            ed.setTitle(processMap(doc, App.fD.getQuestionTitleId()).text());
        }
        if(App.fD.getQuestionAuthorId().size() > 0){
            ed.setAuthor(processMap(doc, App.fD.getQuestionAuthorId()).text());
        }
        if(App.fD.getQuestionDateId().size() > 0){
            ed.setAskDate(processMap(doc, App.fD.getQuestionDateId()).text());
        }
        if(App.fD.getQuestionDetailId().size() > 0){
            ed.setDetails(processMap(doc, App.fD.getQuestionDetailId()).text());
        }
        
        if(App.fD.getRepliesId().size() > 0) {
        	extractReplies(processMap(doc, App.fD.getRepliesId())); //Further processing required (hardcoded a bit)
        }
        
        toJSON();
        //PROCESS REPLIES
	}
	
	private Element processMap(Document doc, Map<String, String> map){
		if(map.containsKey("id")){
			return doc.getElementById(map.get("id"));
		}
		else if(map.containsKey("class")){
			return doc.getElementsByClass(map.get("class")).first();
		}
		
		//For identifying by other attributes
		else if(map.size() == 1){
			return doc.getElementsByAttributeValue(map.keySet().iterator().next(), map.values().iterator().next()).first();
		}
		
		//Might need changing!!!
		return doc;
	}
	
	private void extractReplies(Element ele){
		if(App.fD.getName().toLowerCase().contains("xero")){
			Elements answers = ele.getElementsByClass("answer");
			for (Element e: answers){
				if(e.getElementsByClass("answerContent").first() != null){
					ed.getReplies().add(new Reply(e.getElementsByClass("answerContent").first().text(), (e.hasClass("by-staff")||e.hasClass("by-manager"))));
				}
			}
		}
		
		else if(App.fD.getName().toLowerCase().contains("micro")){
			Elements answers = ele.getElementsByClass("messageContent");
			for(Element e : answers){
				ed.getReplies().add(new Reply(e.text(), false));
			}
			
		}
	}
	
	
	private void toJSON(){
		 GsonBuilder builder = new GsonBuilder();
		 Gson gson = builder.create();
		 System.out.println(gson.toJson(ed));
	}
}

class ExtractedData{
	private String forumName  = "";
	private String URL  = "";
	private String title  = "";
	private String author  = "";
	private String askDate  = "";
	private String details  = "";
	private List<Reply> replies = new ArrayList<Reply>();
	public String getForumName() {
		return forumName;
	}
	public void setForumName(String forumName) {
		this.forumName = forumName;
	}
	public String getURL() {
		return URL;
	}
	public void setURL(String uRL) {
		URL = uRL;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String question) {
		this.details = question;
	}
	public List<Reply> getReplies() {
		return replies;
	}
	public void setReplies(List<Reply> replies) {
		this.replies = replies;
	}
	public String getAskDate() {
		return askDate;
	}
	public void setAskDate(String askDate) {
		this.askDate = askDate;
	}
	
	
}

class Reply{
	private String replyContent;
	private boolean officialAnswer;
	
	public Reply(String replyContent, boolean officialAnswer){
		setReplyContent(replyContent);
		setOfficialAnswer(officialAnswer);
		
	}
	
	public String getReplyContent() {
		return replyContent;
	}
	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}
	public boolean isOfficialAnswer() {
		return officialAnswer;
	}
	public void setOfficialAnswer(boolean bestAnswer) {
		this.officialAnswer = bestAnswer;
	}
}
