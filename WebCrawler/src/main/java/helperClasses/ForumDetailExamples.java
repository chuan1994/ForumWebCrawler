package helperClasses;

import java.util.ArrayList;
import java.util.List;

/**
 * Must be edited to add new forums
 * @author chuan
 *
 */
public class ForumDetailExamples {

	public static ForumDetail getXeroDetails(){
		String baseURL = "https://community.xero.com/developer";
		List<String> acceptedPatterns = new ArrayList<String>();
		String actualPattern = "(https://community.xero.com/developer/discussion/[0-9]+(/*))";
		
		//AddPatterns
		acceptedPatterns.add(actualPattern);
		acceptedPatterns.add(baseURL);
		acceptedPatterns.add("(https://community.xero.com/developer/topic/[0-9]+(/*))");
		acceptedPatterns.add("(https://community.xero.com/developer/topic/[0-9]+/[0-9]+/latest(/*))");
		ForumDetail fd = new ForumDetail(baseURL, acceptedPatterns, actualPattern);
		
		fd.setName("Xero-Developer");
		fd.setQuestionTitleId("id", "currentTitle");
		fd.setQuestionDetailId("id", "currentDetails");
		//fd.setQuestionDateId("",""); no identifier
		//fd.setQuestionAuthorId("", ""); no identifier
		fd.setRepliesId("id", "Answers"); // Further processing hard coded in the HTMLExtractor class
		//AddMore
		
		
		return fd;
	}
	
	public static ForumDetail getMicrosoftDetails(){
		String baseURL = "https://social.msdn.microsoft.com/forums/en-US/home/";
		List<String> acceptedPatterns  = new ArrayList<String>();
		String actualPattern = "(https://social.msdn.microsoft.com/forums/[a-z]{2}-[a-z]{2}/[a-zA-Z0-9]{8}-[a-zA-Z0-9]{4}-[a-zA-Z0-9]{4}-[a-zA-Z0-9]{4}-[a-zA-Z0-9]{12}/.*)";
		
		//AddPatterns
		acceptedPatterns.add(actualPattern);
		acceptedPatterns.add(baseURL);
		acceptedPatterns.add("https://social.msdn.microsoft.com/forums/[a-z]{2}-[a-z]{2}/home?.+page=[0-9]+");
		acceptedPatterns.add("https://social.msdn.microsfot.com/forums/[a-z]{2}-[a-z]{2}/home?category=.+");
		acceptedPatterns.add("https://social.msdn.microsfot.com/forums/[a-z]{2}-[a-z]{2}/home?forum=.+");
		ForumDetail fd = new ForumDetail(baseURL, acceptedPatterns, actualPattern);
		
		fd.setName("Microsoft Developer Network");
		fd.setQuestionTitleId("name", "subject");
		fd.setQuestionDetailId("class", "messageContent");
		fd.setQuestionDateId("class", "date");
		//fd.setQuestionAuthorId("", ""); no identifier
		fd.setRepliesId("id", "allReplies"); // Further processing hard coded in the HTMLExtractor class
		
		return fd;
	}
}
