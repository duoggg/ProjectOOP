package application.controller.RecycleBin;

import java.io.IOException;
import org.json.JSONArray;
import org.json.JSONObject;

import application.controller.analysis.GraphController;
import application.database.dao.OpenSeaDB;
import application.database.dao.TwitterDB;
import application.service.SeeCollectionGraphService;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Table extends Application {
	private static GraphController controller ;
	private JSONArray data = new JSONArray() ;
	
    @Override
    public void start(Stage stage) throws IOException {
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("application/view.fxml"));
    	SeeCollectionGraphService db = new SeeCollectionGraphService(new TwitterDB(), new OpenSeaDB());
    	data = db.getCollectionWithNumberOfTagIn();
    	
    	controller = new GraphController();
//    	Integer[] doubles = {12,14,15,18};

    	
    	JSONObject j1 = data.getJSONObject(1);
    	fxmlLoader.setController(controller);
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle(j1.getString("name"));
        stage.setScene(scene);
        stage.show();
        
        controller.addData(j1.getInt("Volume Day1"),j1.getInt("Volume Day2"),j1.getInt("Volume Day3"),j1.getInt("Volume Day4"),
        				   j1.getInt("Tag Day1")   ,j1.getInt("Tag Day2")   ,j1.getInt("Tag Day3")   ,j1.getInt("Tag Day4"));
    }

    public static void main(String[] args) {
        launch(args);
    }
}