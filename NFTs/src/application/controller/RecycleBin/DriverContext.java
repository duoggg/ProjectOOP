package application.controller.RecycleBin;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class DriverContext {
	protected WebDriver driver ;
	public DriverContext() {
		System.setProperty("webdriver.edge.driver", "E:\\NFTs\\NFTs\\lib\\msedgedriver.exe");
		driver = new EdgeDriver() ;
	}
}
