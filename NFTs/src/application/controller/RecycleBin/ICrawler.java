package application.controller.RecycleBin;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

public interface ICrawler {
	
	public void setUp();
	public void crawl();
	public void saveAsJsonString() ;
	
}
