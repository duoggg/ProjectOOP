package application.controller.RecycleBin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class ListController {
	
	@FXML
	private VBox itemList;
	
	public VBox getRoot() {
		return itemList;
	} 
	@FXML
	private Text Text1;
	@FXML
	private Text Text2;
	@FXML
	private Text Text3;
	
	public ListController() {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("application/itemList.fxml"));
		fxmlLoader.setController(this);

      try {
          itemList = fxmlLoader.load();
      } catch (Exception exception) {
          throw new RuntimeException(exception);
      }
	}
	
//	public<T> void itemList(List<T> p) {
//			List<VBox> itl = new ArrayList<VBox>();
//			for(int i = 0 ; i< p.size() ;i++) {
//				AnchorPane firstChild = (AnchorPane)itemList.getChildren().get(0);
//				Text sText = (Text)firstChild.getChildren().get(1);
//				sText.setText(p.get(i)) ;
//				itl.add(itemList);
//				showArea.getChildren().add(itemList);
//		}
//	}
	
	public void add(String name1, String name2, String name3 ) {
		Text1.setText(name1);
		Text2.setText(name2);
		Text3.setText(name3);
		
	}
}
