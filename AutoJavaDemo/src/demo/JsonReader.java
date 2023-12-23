package demo;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import test.Tweet;


public class JsonReader {
	public void read(String filename) {
        try {
            // Đọc nội dung tệp JSON
            String jsonContent = new String(Files.readAllBytes(Paths.get(filename)));

            // Chuyển đổi chuỗi JSON thành mảng JSON
            JSONArray jsonArray = new JSONArray(jsonContent);

            // Mảng để lưu trữ các đối tượng Person
            ArrayList<Tweet> posts = new ArrayList<>();

            // Đọc từng đối tượng JSON và chuyển đổi thành đối tượng Person
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                
                String account  = jsonObject.getString("acount"); 
            	String date     = jsonObject.getString("date");
            	String hashtag  = jsonObject.getString("hashtag");
            	String imageUrl = jsonObject.getString("imageUrl");
            	String postlUrl = jsonObject.getString("postlUrl");
            	int views		= jsonObject.getInt("views");
            	int likes		= jsonObject.getInt("likes");
               
                Tweet post = new Tweet();
                post.setAcount(account);
                post.setDate(date);
                post.setHashtag(hashtag);
                post.setImageUrl(imageUrl);
                post.setPostlUrl(postlUrl);
                post.setViews(views);
                post.setLikes(likes);

                posts.add(post);
            }

            // In thông tin từ mảng đối tượng Person
            for (Tweet p : posts) {
                System.out.println("ID: " + p.getAcount() + ", Name: " + p.getLikes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

}
	
}