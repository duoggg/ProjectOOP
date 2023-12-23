package application.controller.RecycleBin;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.FSDirectory;

import application.database.dao.BlogDB;
import application.database.dao.ICollectionDB;
import application.database.dao.IPostDB;
import application.database.dao.OpenSeaDB;
import application.database.model.APostModel;


public class Test {
	
	private IPostDB<?> db ;
	private ICollectionDB<?> marketDB;
//	private List<String> soLieuList = new ArrayList<String>();
//	private List<String> baiVietList = new ArrayList<String>();
	
	public Test(IPostDB<?> db, ICollectionDB<?> marketDB ) {
		this.db = db ;
		this.marketDB = marketDB ;
	}

	public void result() throws Exception {
		List<String> soLieuList = new ArrayList<String>();
		List<String> baiVietList = new ArrayList<String>();
		double[] prices = {10.0, 12.0, 11.0, 13.0, 14.0};
		StringBuilder queryBuilder = new StringBuilder();
		queryBuilder.append("NFT" + " ");
		for (int i = 1; i < prices.length; i++) {
            if      (prices[i] > prices[i-1])     queryBuilder.append("price increase ");
            else if (prices[i] < prices[i-1])     queryBuilder.append("price decrease "); 
            else if (prices[i] == prices[i-1])    queryBuilder.append("price unchanged ");
        }
		//soLieuList.add("rise") ;
		
		
		for(APostModel p : db.getAllPost()) {
			baiVietList.add(p.getDetailSearch());
		}
		
      // Xây dựng chỉ mục
      FSDirectory indexDirectory = FSDirectory.open(Paths.get("E:\\MySql\\apacelucene"));
      IndexWriterConfig config = new IndexWriterConfig(new StandardAnalyzer());
      IndexWriter writer = new IndexWriter(indexDirectory, config);
      
      writer.deleteAll();
      
//      for (String article : baiVietList) {
//          Document doc = new Document();
//          doc.add(new TextField("content", article, Field.Store.YES));
//          writer.addDocument(doc);
//      }
      for (APostModel pos : db.getAllPost()) {
          Document doc = new Document();
          doc.add(new TextField("url", pos.getPostUrl(), Field.Store.YES));
          doc.add(new TextField("content",pos.getDetailSearch() , Field.Store.YES));
          writer.addDocument(doc);
      }

      writer.close();

      // Tìm kiếm và đề xuất bài viết liên quan
      DirectoryReader reader = DirectoryReader.open(indexDirectory);
      IndexSearcher searcher = new IndexSearcher(reader);

      
          QueryParser parser = new QueryParser("content", new StandardAnalyzer());
          Query query = parser.parse(queryBuilder.toString().trim());

          TopDocs topDocs = searcher.search(query, 5);
          System.out.println("Số liệu: " + queryBuilder.toString().trim());
          ScoreDoc[] scoreDocs = topDocs.scoreDocs;
			for (int i = 0; i < scoreDocs.length; i++) {
				ScoreDoc scoreDoc = scoreDocs[i];
				Document doc = searcher.getIndexReader().document(i);
              System.out.println("Bài viết liên quan: " + doc.get("url"));
			}
          System.out.println();
      

      	reader.close();
      	indexDirectory.close();
	}
	
    public static void main(String[] args) throws Exception {
    	Test test = new Test(new BlogDB(), new OpenSeaDB());
    	test.result();
    	
        // Dữ liệu số liệu và bài viết
    	
//        String[] soLieu = {
//                "100 triệu USD",
//                "Tăng trưởng 5%",
//                "Thâm hụt ngân sách",
//                "Giá dầu tăng",
//                "Xuất khẩu giảm"
//        };
//
//        String[] baiViet = {
//                "Tăng trưởng kinh tế và thâm hụt ngân sách",
//                "Giá dầu ảnh hưởng đến xuất khẩu",
//                "Những thay đổi về thị trường ngoại hối"
//        };
//
//        // Xây dựng chỉ mục
//        FSDirectory indexDirectory = FSDirectory.open(Paths.get("E:\\MySql\\apacelucene"));
//        IndexWriterConfig config = new IndexWriterConfig(new StandardAnalyzer());
//        IndexWriter writer = new IndexWriter(indexDirectory, config);
//        
//        writer.deleteAll();
//        for (String article : baiViet) {
//            Document doc = new Document();
//            doc.add(new TextField("content", article, Field.Store.YES));
//            writer.addDocument(doc);
//        }
//
//        writer.close();
//
//        // Tìm kiếm và đề xuất bài viết liên quan
//        DirectoryReader reader = DirectoryReader.open(indexDirectory);
//        IndexSearcher searcher = new IndexSearcher(reader);
//
//        for (String data : soLieu) {
//            QueryParser parser = new QueryParser("content", new StandardAnalyzer());
//            Query query = parser.parse(data);
//
//            TopDocs topDocs = searcher.search(query, 5);
//            System.out.println("Số liệu: " + data);
//            ScoreDoc[] scoreDocs = topDocs.scoreDocs;
//			for (int i = 0; i < scoreDocs.length; i++) {
//				ScoreDoc scoreDoc = scoreDocs[i];
//				Document doc = searcher.getIndexReader().document(i);
//                System.out.println("Bài viết liên quan: " + doc.get("content"));
//			}
//            System.out.println();
//        }
//
//        reader.close();
//        indexDirectory.close();
    }
}