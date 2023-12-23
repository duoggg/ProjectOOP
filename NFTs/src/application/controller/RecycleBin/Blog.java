//	@Override
//	public TwitterModel getPost(WebElement n) {
//		String author            = getAuthor(n);
//		String date              = getDate(n);
//		String tags              = getTags(n);
//		String postUrl		     = getPostUrl(n);
//		String imageUrl          = getImageUrl(n);
//		int    likes			 = getLikes(n);	
//		int    views			 = getViews(n);	
//		TwitterModel item = new TwitterModel(author, date, tags, postUrl, imageUrl, views, likes) ;
//		return item;
//	}
//
//	@Override
//	public String getAuthor(WebElement n) {
//        WebElement usernameElement = driver.findElement(By.cssSelector("div[data-testid='User-Name'] > div:nth-child(2) > div:nth-child(1) > div:nth-child(1) > a:nth-child(1) > div:nth-child(1) > span:nth-child(1)"));
//        String acount = usernameElement.getText();
//        System.out.println("User: " + acount);
//        return acount ;
//	}
//
//	@Override
//	public String getDate(WebElement n) {
//        WebElement dateElement = driver.findElement(By.cssSelector("div[data-testid='User-Name'] > div:nth-child(2) > div:nth-child(1) > div:nth-child(3) > a:nth-child(1) > time:nth-child(1)"));
//        String  dateString = dateElement.getAttribute("datetime");
//        System.out.println("Date: " + dateString) ;
//        return dateString;
//	}
//
//	@Override
//	public String getTags(WebElement n) {      
//        WebElement hashtagElement = driver.findElement(By.cssSelector("div[data-testid='tweetText']"));
//        String[] words = hashtagElement.getText().split("\\s+");
//        String hashtagString = new String();
//        for (String word : words) {
//            if (word.contains("#")) {
//            	hashtagString += word ;
//            }
//        }
//        System.out.println("Hashtag: " + hashtagString);
//        return hashtagString ;
//	}
//
//	@Override
//	public String getPostUrl(WebElement n) {
//		WebElement postUrlElement = driver.findElement(By.cssSelector("div[data-testid='User-Name'] > div:nth-child(2) > div:nth-child(1) > div:nth-child(3) > a:nth-child(1)"));
//		String postUrl = postUrlElement.getAttribute("href")  ;
//		System.out.println("Post URL: " + postUrl);
//		return postUrl ;
//	}
//
//	@Override
//	public String getImageUrl(WebElement n) {
//		  WebElement imageElement = driver.findElement(By.cssSelector("div[data-testid='tweetPhoto'] > img:nth-child(2)"));
//          String imageUrl = imageElement.getAttribute("src");
//          System.out.println("Image: " + imageUrl);
//          return imageUrl ;
//	}
//	
//	public int getViews(WebElement n) throws NumberFormatException {
//		 WebElement viewsElement = driver.findElement(By.cssSelector("a[aria-label*='views"));
//         String viewsString = viewsElement.getAttribute("aria-label");
//         viewsString = viewsString.substring(0, viewsString.indexOf(" "));
//         int views = Integer.parseInt(viewsString);
//         System.out.println("Views: " + views);
//         return views ;
//	}
//	
//	public int getLikes(WebElement n) throws NumberFormatException {
//		WebElement likesElement = driver.findElement(By.cssSelector("div[data-testid='like']"));
//        String likesString = likesElement.getAttribute("aria-label");
//        likesString = likesString.substring(0, likesString.indexOf(" "));
//        int likes = Integer.parseInt(likesString);
//        System.out.println("Views: " + likes);
//        return likes ;
//	}
//	
package application;
//@Override
//public BlogModel getPost(WebElement n) {
//	// TODO Auto-generated method stub
//	return null;
//}
//
//@Override
//public String getAuthor(WebElement n) {
//	WebElement authorElement = n.findElement(By.cssSelector("div[class='author'] > a:nth-child(1) > span:nth-child(2)"));
//	String author = authorElement.getText();
//	System.out.println("Author: " + author);
//	return author ;
//}
//
//@Override
//public String getDate(WebElement n) {
//	WebElement dateElement = n.findElement(By.cssSelector("div[class='blog-date'] > ul:nth-child(1) > li:nth-child(1)"));
//	checkDate = dateElement.getText();
//	if (!checkDate.contains("2023")) continue;
//	item.setDate(checkDate);
////	item.setDate(dateElement.getText());
//	System.out.println("Date: " + item.getDate());
//	
//}
//
//@Override
//public String getTags(WebElement n) {
//	String classNameString = n.getAttribute("class");
//		// Tách các từ trong chuỗi
//    String[] words = classNameString.split("\\s+");
//    String tagString = new String();
//    for (String word : words) {
//        if (word.contains("tag")) {
//        	word = word.substring(4);
//        	tagString += word ;
//        }
//    }
//    System.out.println("Tag: " + tagString);
//    return tagString ;
//}
//
//@Override
//public String getPostUrl(WebElement n) {
//	// Lấy URL của Blog
//	WebElement blogURLElement = titleElement.findElement(By.cssSelector("a:nth-child(1)"));
//	item.setBlogURL(blogURLElement.getAttribute("href"));
//	System.out.println("URL: " + item.getBlogURL());
//}
//
//@Override
//public String getImageUrl(WebElement n) {
//	WebElement imgElement = n.findElement(By.cssSelector("div[class*='image'] > a:nth-child(1) > img:nth-child(1)"));
//	String img = imgElement.getAttribute("src");
//	System.out.println("Image: " + img);
//	return img ;
//}
//
//public String getTitle(WebElement n) {
//	WebElement titleElement = n.findElement(By.className("blog-title"));
//	String img  = titleElement.getText();
//	System.out.println("Title: " + img);
//	return img ;
//}
//
//public String getDescribe(WebElement n) {
//	WebElement describElement = n.findElement(By.cssSelector("div[class='blog-info'] > p:nth-child(3)"));
//	String describe = describElement.getText();
//	System.out.println("Describe: " + describe);
//	return describe ;
//}



