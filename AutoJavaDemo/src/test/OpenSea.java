package test;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.gson.Gson;

public class OpenSea {
	// Kiểm tra xem item đã tồn tại trong mảng lưu trữ chưa
	public static boolean isItemExist(ArrayList<NFTs> list, int rank) {
		for (NFTs l : list) {
			if (l.getRank() == rank) return true;
		}
		return false;
    }
	
	public static void main(String[] args) throws InterruptedException {
		
		ArrayList<NFTs> listCollection = new ArrayList<NFTs>();
		
		// Lấy ngày và giờ hiện tại
        LocalDateTime currentDateTime = LocalDateTime.now();

        // Định dạng ngày và giờ theo ý muốn (ví dụ: yyyy-MM-dd HH:mm:ss)
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String DateTime = currentDateTime.format(formatter);
		
		System.setProperty("webdriver.edge.driver", "D:\\LINH\\Document\\BK\\20231\\OOP\\Source\\msedgedriver.exe");
		
		WebDriver driver = new EdgeDriver();
		
		driver.get("https://opensea.io/");
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        
        driver.findElement(By.xpath("//button[contains(text(),'View all')]")).click();
		Thread.sleep(5000);
		
		WebDriverWait wait = new WebDriverWait(driver, 50);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("a[data-id='Item']")));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,500)");
		Thread.sleep(3000);
		
		while (listCollection.size() < 95) {
			// Đợi cho đến khi thêm bài viết mới xuất hiện (hoặc có thể sử dụng điều kiện khác)
            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("a[data-id='Item']")));
            Thread.sleep(3000);
            List<WebElement> nftElements = driver.findElements(By.cssSelector("a[data-id='Item']"));
       
            
            for (WebElement n : nftElements) {
            	NFTs item = new NFTs();
            	// Get rank
            	WebElement rankElement = n.findElement(By.cssSelector("div:nth-child(1) > div:nth-child(1)"));
            	int rank = Integer.parseInt(rankElement.getText());
            	if (isItemExist(listCollection, rank)) continue;
                item.setRank(rank);
                System.out.println("Rank: " + item.getRank());
//                Thread.sleep(500);
                
                // Get name collection
                WebElement collectionElement = n.findElement(By.cssSelector("div:nth-child(1) > div:nth-child(3) > div:nth-child(1) > div:nth-child(1) > span:nth-child(1)"));
                item.setCollection(collectionElement.getText());
                System.out.println("Collection: " + item.getCollection());
//                Thread.sleep(500);
                
                // Get volume
                WebElement volumeElement = n.findElement(By.cssSelector("div:nth-child(2) > div:nth-child(1) > span:nth-child(1) > div:nth-child(1)"));
                item.setVolume(volumeElement.getText());
                System.out.println("Volume: " + item.getVolume());
//                Thread.sleep(500);
                
                // Get percentChange
                WebElement percentElement = n.findElement(By.cssSelector("div:nth-child(3) > span:nth-child(1)"));
                item.setPercentChange(percentElement.getText());
                System.out.println("%Change: " + item.getPercentChange());
//                Thread.sleep(500);
                
                // Get Floor Price
                WebElement priceElement = n.findElement(By.cssSelector("div:nth-child(4) > div:nth-child(1) > span:nth-child(1)"));
                item.setFloorPrice(priceElement.getText());
                System.out.println("Floor Price: " + item.getFloorPrice());
//                Thread.sleep(500);
                
                // Get Sales
                WebElement salesElement = n.findElement(By.cssSelector("div:nth-child(5) > span:nth-child(1)"));
                String saleString = salesElement.getText();
                if (saleString.contains(",")) {
                	saleString = saleString.replace(",", "");
                	item.setSales(Integer.parseInt(saleString));
                }
                else if (saleString.contains("K")) {
                	saleString = saleString.replace("K", "");
                	item.setSales(Integer.parseInt(saleString) * 1000);
                }
                else item.setSales(Integer.parseInt(saleString));
                System.out.println("Sales: " + item.getSales());
                System.out.println("\n");
                
                // Set Date
                item.setDate(DateTime);


                listCollection.add(item);
                Thread.sleep(500);
                
            }      
            
//          Scroll 
            js.executeScript("window.scrollBy(0,2200)");
		}
		
		//Chuyển listCollection thành JSON text       
        Gson gson = new Gson();
        String jsonData = gson.toJson(listCollection);
        
      //Write JSON file
        try (FileWriter file = new FileWriter("collection(11).json")) {
        	
        	file.write(jsonData);
        	file.flush();
			
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}

        System.out.println(jsonData);
        for (int i = 0; i < listCollection.size(); i++){
            System.out.println("#" + listCollection.get(i).getRank() + " " + listCollection.get(i).getCollection());
        }
		driver.quit();
	}
}
