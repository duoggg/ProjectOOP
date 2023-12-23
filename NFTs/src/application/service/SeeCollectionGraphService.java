package application.service;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

import application.database.dao.ICollectionDB;
import application.database.dao.IPostDB;
import application.database.model.ACollectionModel;
import application.database.model.APostModel;

public class SeeCollectionGraphService {
	private IPostDB<?> postDB ;
	private ICollectionDB<?> marketDB;
	private static final int NUMBEROFPLOT = 4 ;
	private JSONArray result = new JSONArray() ;
	
	public SeeCollectionGraphService(IPostDB<?> postDB, ICollectionDB<?> marketDB ) {
		this.postDB = postDB ;
		this.marketDB = marketDB ;
	}
	
	public  JSONObject  getDataDraw(String name) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("name",name );
	
			// Volume theo ngay
			for(int i = 1 ; i <= NUMBEROFPLOT ; i++) {
				if(marketDB.getCollectionByNameInDayAgo(name, i).size()==0) jsonObject.put("Volume Day"+ i, 0);
				else {
					ACollectionModel collection = marketDB.getCollectionByNameInDayAgo(name, i).get(0) ;
					int volumeInt = collection.calVolume(); jsonObject.put("Volume Day" + i , volumeInt ) ;
				}
			}
			
			// So HashTag theo ngay
			for(int i = 1 ; i <= NUMBEROFPLOT ; i++) {
				List<String> listTagList = new ArrayList<String>();
				int count = 0;
				for(APostModel post : postDB.getPostInDayAgo(i+12)) listTagList.addAll(post.tagList());
				for(String tag : listTagList) if(name.equals(tag)) count ++ ; 
				jsonObject.put("Tag Day" + i , count );
			}	
		return jsonObject;	
	}
}
