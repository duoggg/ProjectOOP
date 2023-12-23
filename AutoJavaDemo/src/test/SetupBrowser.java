package test;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.*;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.*;


public class SetupBrowser {
	
	public WebDriver driver;
	public JavascriptExecutor js;
	
	@BeforeClass
	public void createDriver() {
		
		System.setProperty("webdriver.edge.driver", "D:\\LINH\\Document\\BK\\20231\\OOP\\Source\\msedgedriver.exe");
		driver = new EdgeDriver();
		System.out.println("Started Driver");
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		js = (JavascriptExecutor) driver;
	}
	
	@AfterClass
	public void closeDriver() {
		driver.quit();
		System.out.println("Closed Driver");
	}
	

}
