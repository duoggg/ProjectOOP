package application.controller.RecycleBin;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import application.crawl.crawler.AMyCrawler;
import application.crawl.crawler.OpenSeaCrawler;
import application.crawl.driver.EdgeDriverContext;
import application.database.model.ACollectionModel;
import application.database.model.OpenSeaModel;

public abstract class AMarketCrawl<T extends ACollectionModel> extends AMyCrawler  {
	
	public abstract T       getCollection (WebElement n);
	public abstract int     getRank (WebElement n);
	public abstract String  getName (WebElement n);
	public abstract String  getVolume (WebElement n);
	public abstract String  getPercentChange (WebElement n);
	public abstract String  getFloorPrice (WebElement n);
	
	public String  getDate () {
		 LocalDateTime currentDateTime = LocalDateTime.now();
	     DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	     String DateTime = currentDateTime.format(formatter);
	     return DateTime ;
	}
//	@Override
//	public OpenSeaModel getCollection(WebElement n) {
//		int    rank              = getRank(n) ;
//		String collection        = getName(n);
//		String volume            = getVolume(n);
//		String percentChange     = getPercentChange(n);
//		String floorPrice		 = getFloorPrice(n);
//		String date              = getDate();
//		int    sales			 = getSale(n);	
//		OpenSeaModel item = new OpenSeaModel(rank, collection, volume, percentChange, floorPrice, date,sales );
//		return item;
//	}
//
//	@Override
//	public int getRank(WebElement n) throws NumberFormatException {
//		WebElement rankElement = n.findElement(By.cssSelector("div:nth-child(1) > div:nth-child(1)"));
//        int rank = Integer.parseInt(rankElement.getText());
//        System.out.println("Rank: " + rank);
//		return 0;
//	}
//
//	@Override
//	public String getName(WebElement n) {
//        WebElement collectionElement = n.findElement(By.cssSelector("div:nth-child(1) > div:nth-child(3) > div:nth-child(1) > div:nth-child(1) > span:nth-child(1)"));
//        String  name =  collectionElement.getText();
//        System.out.println("Collection: " + name);
//        return name ;
//	}
//
//	@Override
//	public String getVolume(WebElement n) {
//		  WebElement volumeElement = n.findElement(By.cssSelector("div:nth-child(2) > div:nth-child(1) > span:nth-child(1) > div:nth-child(1)"));
//		  String     volume        =  volumeElement.getText();
//          System.out.println("Volume: " + volume);
//          return volume ;
//	}
//
//	@Override
//	public String getPercentChange(WebElement n) {
//		 WebElement percentElement = n.findElement(By.cssSelector("div:nth-child(3) > span:nth-child(1)"));
//         String percentChange = percentElement.getText();
//         System.out.println("%Change: " + percentChange);
//         return percentChange ;
//	}
//
//	@Override
//	public String getFloorPrice(WebElement n) {
//		 WebElement priceElement = n.findElement(By.cssSelector("div:nth-child(4) > div:nth-child(1) > span:nth-child(1)"));
//         String floorPrice = priceElement.getText();
//         System.out.println("Floor Price: " + floorPrice);
//         return floorPrice ;
//	}
//	
//	public int getSale(WebElement n) throws NumberFormatException {
//		WebElement salesElement = n.findElement(By.cssSelector("div:nth-child(5) > span:nth-child(1)"));
//		String saleString = salesElement.getText();
//		if (saleString.contains(","))      saleString = saleString.replace(",", "");
//		else if (saleString.contains("K")) saleString = saleString.replace("K", "");
//		int sales = Integer.parseInt(saleString) ;
//		System.out.println("Sales: " + sales);
//		return sales ;
//	}
//	
//	public static void main(String[] args) throws InterruptedException {
//	   OpenSeaCrawler aBinanceCrawler = new OpenSeaCrawler(new EdgeDriverContext());
//       aBinanceCrawler.crawl();
//		
//	}
//	
//}
}

//@Override
//public BinanceModel getCollection(WebElement n) {
//	int    rank              = getRank(n) ;
//	String collection        = getName(n);
//	String volume            = getVolume(n);
//	String percentChange     = getPercentChange(n);
//	String floorPrice		 = getFloorPrice(n);
//	String date              = getDate();
//	int    owners			 = getOwners(n);
//	int    items 			 = getItems(n) ;
//	
//	BinanceModel item = new BinanceModel(rank, collection, volume, percentChange, floorPrice, date, owners, items); 
//	return item;
//}
//
//@Override
//public int getRank(WebElement n) {
//	int rank ;
//	WebElement rankElement = n.findElement(By.cssSelector("div:nth-child(1) > div:nth-child(1)"));
//	if (rankElement.getText().equals("")) {
//    		WebElement rankSpecial = n.findElement(By.cssSelector("div:nth-child(1) > div:nth-child(1) > span:nth-child(1) > img:nth-child(1)"));
//    		String     rankSrc     = rankSpecial.getAttribute("src");
//    		if      (rankSrc.contains("first_iii"))  rank = 1;
//    		else if (rankSrc.contains("second_iii")) rank = 2;
//    		else 									 rank = 3;
//	}
//	else    rank =  Integer.parseInt(rankElement.getText());
//    System.out.println("Rank: " + rank);
//	return rank ;
//}
//
//@Override
//public String getName(WebElement n) {
//	String collection ;
//	WebElement collectionElement = n.findElement(By.cssSelector("div:nth-child(1) div:nth-child(3) div:nth-child(1) div:nth-child(1)"));
//    collection = collectionElement.getText();
//    System.out.println("Collection: " + collection);
//	return collection;
//}
//
//@Override
//public String getVolume(WebElement n) {
//	 String volume ;
//     WebElement volumeElement = n.findElement(By.cssSelector("div:nth-child(2) > div:nth-child(1) > div:nth-child(1)"));
//     volume = volumeElement.getText();
//     System.out.println("Volume: " + volume);
//	return volume;
//}
//
//@Override
//public String getPercentChange(WebElement n) {
//	 String percentChange ;
//     WebElement percentElement = n.findElement(By.cssSelector("div:nth-child(3) > div:nth-child(1) > div:nth-child(1)"));
//     percentChange = percentElement.getText();
//     System.out.println("%Change: " + percentChange);
//	 return percentChange;
//}
//
//@Override
//public String getFloorPrice(WebElement n) {
//	 String floorPrice ;
//     WebElement priceElement =  n.findElement(By.cssSelector("div:nth-child(4) > div:nth-child(1) > div:nth-child(1)"));
//     floorPrice = priceElement.getText();
//     System.out.println("Floor Price: " + floorPrice );
//	 return floorPrice;
//}
//
//public int getOwners(WebElement n) {
//	int owners ;
//    WebElement ownersElement = n.findElement(By.cssSelector("div:nth-child(6)"));
//    String ownerString = ownersElement.getText();
//    if     (ownerString.contains(","))    owners = Integer.parseInt(ownerString.replace(",", ""));
//    else if(!ownerString.equals("--"))    owners = Integer.parseInt(ownerString); 
//    else  								  owners = 0;
//    System.out.println("Owners: " + owners);
//    return owners ;
//}
//
//public int getItems(WebElement n) {
//	int items ;
//    WebElement itemsElement = n.findElement(By.cssSelector("div:nth-child(7)"));
//    String itemString = itemsElement.getText() ;
//    if     (itemsElement.getText().contains(","))  items = Integer.parseInt(itemString.replace(",", ""));
//    else if  (!itemString.equals("--"))            items = Integer.parseInt(itemString); 
//    else  										   items = 0;
//    System.out.println("Items: " + items);
//    return items ;
//}
