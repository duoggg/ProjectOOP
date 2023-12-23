package application.controller.post;

import java.io.IOException;
import org.json.JSONArray;
import org.json.JSONObject;

import application.controller.AMyController;
import application.database.dao.BlogDB;
import application.database.dao.TwitterDB;
import application.service.SeePostService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class SeePostController extends AMyController {	
	
	private static final String URL = "application/view/PostList.fxml" ;
	private static final String HEADER_STRING = "Danh sách bài viết" ;
	private JSONArray data = new JSONArray(); 	
	private SeePostService service ;
	@FXML
	private VBox root;
	@FXML
	private Label header;
	@FXML
	private VBox showArea;
	@FXML
	private ComboBox<String> socialOption;
	@FXML
	private TextField search ;
	public SeePostController() {
		loadView();
	}
	
	public VBox getRoot() {
		return root ;
	}
	
	@FXML
	void searchButtonPressed(ActionEvent event) throws IOException {
		showArea.getChildren().clear();
		if(socialOption.getValue()   == null) {
			ItemPostController addInform = new ItemPostController() ;
			addInform.addError("Hãy chọn nền tảng bài viết");
			showArea.getChildren().add(addInform.getRoot());
		} 
		else {
			loadData();
			if(data.length()==0) {
				ItemPostController addInform = new ItemPostController() ;
				addInform.addError("Không tìm thấy kết quả");
				showArea.getChildren().add(addInform.getRoot());
			  } 
			for(int i = 0;i < data.length() ;i++) {	
				JSONObject jsonObject = data.getJSONObject(i);
				ItemPostController itemControl = new ItemPostController(jsonObject) ;
				itemControl.loadData();
				itemControl.addAction();
				showArea.getChildren().add(itemControl.getRoot());
			}
		}
	}
	
	@Override
	public void loadView() {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource(URL));
		fxmlLoader.setController(this);
	      try {
	          root = fxmlLoader.load();
	      } catch (IOException e) {
	    	  e.printStackTrace();
	      }
	      header.setText(HEADER_STRING);
	      ObservableList<String> options = FXCollections.observableArrayList("Twitter","Blog");
		  socialOption.setItems(options);
	}
	
	@Override
	public void loadData() {
		if     (search.getText().isEmpty() && (socialOption.getValue().equals("Twitter")))  { 
			service = new SeePostService(new TwitterDB()) ;
			data   =  service.getAllPost() ;
		}	
		else if (search.getText().isEmpty() && (socialOption.getValue().equals("Blog"))) {
			service = new SeePostService(new BlogDB()) ;
			data   =  service.getAllPost() ;
		}
		else if(!search.getText().isEmpty() && (socialOption.getValue().equals("Twitter"))) {
			service = new SeePostService(new TwitterDB()) ;
			data   =  service.getPostByHashtag(search.getText()) ;
		}
		else if(!search.getText().isEmpty() && (socialOption.getValue().equals("Blog"))) {
			service = new SeePostService(new BlogDB()) ;
			data   =  service.getPostByHashtag(search.getText()) ;
		} 
	}
}


		
	

