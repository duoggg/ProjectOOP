package test;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.gson.Gson;


public class FirstDemo {
	
	public static void main(String[] args) throws InterruptedException {
		
		String query = "#nft";
		ArrayList<Tweet> listTweets = new ArrayList<Tweet>();
		
		try (FileReader reader = new FileReader("data.json")) {
            Gson gson = new Gson();
            Type listType = new TypeToken<ArrayList<Tweet>>() {}.getType();
            listTweets = gson.fromJson(reader, listType);

            // Hiển thị dữ liệu
            for (YourClass data : dataList) {
                System.out.println(data);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
		
		System.setProperty("webdriver.edge.driver", "D:\\LINH\\Document\\BK\\20231\\OOP\\Source\\msedgedriver.exe");
		
		WebDriver driver = new EdgeDriver();
		
		driver.get("https://twitter.com/login");
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		driver.findElement(By.xpath("//input[@name='text']")).sendKeys("Doraemo29925335");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//span[contains(text(),'Next')]")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys("03102003");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//span[contains(text(),'Log in')]")).click();
		Thread.sleep(3000);
		
		driver.findElement(By.xpath("//input[@aria-label='Search query']")).sendKeys(query);
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@aria-label='Search query']")).sendKeys(Keys.ENTER);
		
		WebDriverWait wait = new WebDriverWait(driver, 50);
		listTweets.clear();
        while (listTweets.size() <= 5) {	// Crawl 10 tweet
            // Scroll xuống cuối trang
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollBy(0, 1000)");
            Thread.sleep(3000);

            // Đợi cho đến khi thêm bài viết mới xuất hiện (hoặc có thể sử dụng điều kiện khác)
            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("a[aria-label*='ago']")));

        	Tweet item = new Tweet();
            
            // Lấy URL của tweet
            WebElement postUrlElement = driver.findElement(By.cssSelector("div[data-testid='User-Name'] > div:nth-child(2) > div:nth-child(1) > div:nth-child(3) > a:nth-child(1)"));
            item.setPostlUrl(postUrlElement.getAttribute("href"));
//            System.out.println("Post URL: " + item.getPostlUrl());
            
            // Lấy tên người dùng
            WebElement usernameElement = driver.findElement(By.cssSelector("div[data-testid='User-Name'] > div:nth-child(2) > div:nth-child(1) > div:nth-child(1) > a:nth-child(1) > div:nth-child(1) > span:nth-child(1)"));
            item.setAcount(usernameElement.getText());
//            System.out.println("User: " + item.getAcount());
            
            // Lấy Date created 
            WebElement dateElement = driver.findElement(By.cssSelector("div[data-testid='User-Name'] > div:nth-child(2) > div:nth-child(1) > div:nth-child(3) > a:nth-child(1) > time:nth-child(1)"));
            item.setDate(dateElement.getAttribute("datetime"));
//            System.out.println("Date: " + item.getDate());
            
         // Lấy Hashtag        
            WebElement hashtagElement = driver.findElement(By.cssSelector("div[data-testid='tweetText']"));
            // Tách các từ trong chuỗi
            String[] words = hashtagElement.getText().split("\\s+");
            for (String word : words) {
                if (word.contains("#")) {
                	if (item.getHashtag() == null) item.setHashtag(word);
                	else item.setHashtag(item.getHashtag() + word);
                }
            }
            System.out.println("Hashtag: " + item.getHashtag());
            
            // Lấy Image URL
            WebElement imageElement = driver.findElement(By.cssSelector("div[data-testid='tweetPhoto'] > img:nth-child(2)"));
            item.setImageUrl(imageElement.getAttribute("src"));
//            System.out.println("Image: " + item.getImageUrl());
            
         // Lấy view
            WebElement viewsElement = driver.findElement(By.cssSelector("a[aria-label*='views"));
            String viewsString = viewsElement.getAttribute("aria-label");
            viewsString = viewsString.substring(0, viewsString.indexOf(" "));
            item.setViews(Integer.parseInt(viewsString));
            System.out.println("Views: " + item.getViews());

            
         // Lấy like
            WebElement likesElement = driver.findElement(By.cssSelector("div[data-testid='like']"));
            String likesString = likesElement.getAttribute("aria-label");
            likesString = likesString.substring(0, likesString.indexOf(" "));
            item.setLikes(Integer.parseInt(likesString));
            System.out.println("Likes: " + item.getLikes());
           
            

            	listTweets.add(item);
        }
        
      //Chuyển listTweet thành JSON text       
        Gson gson = new Gson();
        String jsonData = gson.toJson(listTweets);
        
      //Write JSON file
        try (FileWriter file = new FileWriter("data.json")) {
   
        	file.write(jsonData);
        	file.flush();
			
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}

        System.out.println(jsonData);
        for (int i = 0; i < listTweets.size(); i++){
            System.out.println(i + " Post: " + listTweets.get(i).getPostlUrl());
        }
		
//		driver.quit();


        
	}
}


