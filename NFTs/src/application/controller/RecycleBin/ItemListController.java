package application.controller.RecycleBin;


import java.io.IOException;

import application.controller.AMyController;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class ItemListController extends AMyController {
	private static final String URL = "application/view/ItemList.fxml" ;
	@FXML
	private VBox itemList;
	@FXML
	private VBox detail;
	@FXML
	private HBox popular;
	@FXML
	private Text text1;
	@FXML
	private Text text2;
	@FXML
	private Text text3;
	@FXML
	private Text postUrl ;
	@FXML 
	private Text imgUrl ;
	@FXML
	private Text tag ;
	@FXML
	private Text view ;
	@FXML
	private Text like ;
	
	public VBox getRoot() {
		return itemList;
	} 
	
	public ItemListController() {
		loadView();
	}
	
	public void add(String t1 , String t2, String t3) {
		text1.setText(t1);
		text2.setText(t2);
		text3.setText(t3);
	}
	
	public void addAction(String hashtag, String img, String post, String views, String likes) {
		itemList.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			private static int count = 0 ;
			@Override
			public void handle(MouseEvent arg0) {
				if(count == 0) {
					tag.setText(hashtag);
					imgUrl.setText(img);
					postUrl.setText(post);
					view.setText(views);
					like.setText(likes);
					detail.setManaged(true);
					detail.setVisible(true);
					count ++ ;	
				} else {
					detail.setVisible(false);
					detail.setManaged(false);
					count = 0 ;
				}
				if(views==null || likes == null) {popular.setVisible(false); popular.setManaged(false);}
			}
		});
	}
	
	@Override
	public void loadView() {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource(URL));
		fxmlLoader.setController(this);
	      try {
	          itemList = fxmlLoader.load();
	      } catch (IOException e) {
	    	  e.printStackTrace();
	      }
	    detail.setVisible(false);
	    detail.setManaged(false); 
	}

	@Override
	public void loadData() {
		// TODO Auto-generated method stub
		
	}
}
