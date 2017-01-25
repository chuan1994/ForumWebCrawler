package helperClasses;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class stores all the details regarding the forum and the ids/tags of the divs containing the areas of interest
 * NOTE: may want to change to hash map storing the attribute name and attribute value to be searched by to allow for more flexible searches in the HTML
 * @author chuan
 *
 */

public class ForumDetail {
	private String baseURL;
	private List<String> acceptedPatterns;
	private String actualPattern;
	private String name;
	private Map<String,String> questionTitleId = new HashMap<String, String>();
	private Map<String,String> questionAuthorId = new HashMap<String, String>();
	private Map<String,String> questionDetailId = new HashMap<String, String>();
	private Map<String,String> questionDateId = new HashMap<String, String>();
	private Map<String,String> repliesId = new HashMap<String, String>();
	
	public ForumDetail( String URL, List<String> acceptedPatterns, String actualPattern){
		this.setBaseURL(URL);
		this.setAcceptedPatterns(acceptedPatterns);
		this.setActualPattern(actualPattern);
	}

	public String getBaseURL() {
		return baseURL;
	}

	public void setBaseURL(String baseURL) {
		this.baseURL = baseURL;
	}

	public Map<String,String> getQuestionTitleId() {
		return questionTitleId;
	}

	public void setQuestionTitleId(String attr, String questionTitleId) {
		this.questionTitleId.put(attr, questionTitleId);
	}

	public Map<String,String> getQuestionAuthorId() {
		return questionAuthorId;
	}

	public void setQuestionAuthorId(String attr, String questionAuthorId) {
		this.questionAuthorId.put(attr, questionAuthorId);
	}

	public Map<String,String> getQuestionDetailId() {
		return questionDetailId;
	}

	public void setQuestionDetailId(String attr, String questionDetailId) {
		this.questionDetailId.put(attr, questionDetailId);
	}

	public Map<String,String> getRepliesId() {
		return repliesId;
	}

	public void setRepliesId(String attr, String repliesId) {
		this.repliesId.put(attr, repliesId);
	}

	public List<String> getAcceptedPatterns() {
		return acceptedPatterns;
	}

	public void setAcceptedPatterns(List<String> acceptedPatterns) {
		this.acceptedPatterns = acceptedPatterns;
	}

	public String getActualPattern() {
		return actualPattern;
	}

	public void setActualPattern(String actualPattern) {
		this.actualPattern = actualPattern;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Map<String,String> getQuestionDateId() {
		return questionDateId;
	}

	public void setQuestionDateId(String attr, String questionDateId) {
		this.questionDateId.put(attr,  questionDateId);
	}
}

