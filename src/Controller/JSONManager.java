package Controller;


import com.google.gson.Gson;

import Model.Comment;
import Model.Comments;
import Model.Opinion;

public class JSONManager {
	
	private Gson gson;
	public JSONManager() {
		gson = new Gson();
	}
	
	public Comment toComment(String json) {
		return gson.fromJson(json, Comment.class);
	}
	
	public String toJson(Comment comment) {
		return gson.toJson(comment);
	}
	
	public Comments toComments(String json) {
		return new Comments(gson.fromJson(json, Comment[].class));
	}
	
	public Opinion[] toOpinions(String json) {
		return gson.fromJson(json, Opinion[].class);
	}
}
