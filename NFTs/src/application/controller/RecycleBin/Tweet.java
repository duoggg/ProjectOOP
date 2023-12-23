package application.controller.RecycleBin;

import org.json.JSONObject;

public class Tweet {
	private String acount;
	private String date;
	private String hashtag;
	private String imageUrl;
	private String postlUrl;
	private int views;
	private int likes;
	
	public Tweet() {
		// TODO Auto-generated constructor stub
	}
	
	public Tweet(String acount, String date, String hashtag, String imageUrl, String postlUrl, int views, int likes) {
		this.acount = acount;
		this.date = date;
		this.hashtag = hashtag;
		this.imageUrl = imageUrl;
		this.postlUrl = postlUrl;
		this.views = views;
		this.likes = likes;
	}
	
	public String getAcount() {
		return acount;
	}
	public void setAcount(String acount) {
		this.acount = acount;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getHashtag() {
		return hashtag;
	}
	public void setHashtag(String hashtag) {
		this.hashtag = hashtag;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public String getPostlUrl() {
		return postlUrl;
	}
	public void setPostlUrl(String postlUrl) {
		this.postlUrl = postlUrl;
	}
	public int getViews() {
		return views;
	}
	public void setViews(int views) {
		this.views = views;
	}
	public int getLikes() {
		return likes;
	}
	public void setLikes(int likes) {
		this.likes = likes;
	}
	
	// Factory method to create Tweet object from JSON
    public static Tweet fromJson(JSONObject json) {
    	String acount = json.getString("acount");
    	String date = json.getString("date");
    	String hashtag = json.getString("hashtag");
    	String imageUrl = json.getString("imageUrl");
    	String postlUrl = json.getString("postlUrl");
    	int views = json.getInt("views");
    	int likes = json.getInt("likes");

        return new Tweet(acount, date, hashtag, imageUrl, postlUrl, views, likes);
    }
	
}
