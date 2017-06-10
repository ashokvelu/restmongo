package org.ashkn.restful.messenger.Database;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import org.ashkn.restful.messenger.Model.ProdDoc;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.List;
import static java.util.Arrays.asList;

import java.util.ArrayList;



import static com.mongodb.client.model.Filters.*;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.MongoClient;


/**
 * Hello world!
 *
 */
public class MongoClass
{
	MongoClient client = new MongoClient();
    MongoDatabase db = client.getDatabase("site");
    
    public MongoClass()
    {
        
    }    
    public String updateOne(ProdDoc doc){
    	
    	MongoCollection<Document> coll = db.getCollection("product");
    	int price = (int)doc.getPrice();
    	
    	coll.updateOne(eq("prodname",doc.getProdname()) , new Document("$set",
    			new Document("prodtype",doc.getProdtype()).append("price", price)));    	
       // coll.insertMany(asList(prod, prod1, prod2, prod3));
    	
		return "insert sucessful"+doc.getProdname();
        
    }
    public String insertOne(ProdDoc doc){
    	MongoCollection<Document> coll = db.getCollection("product");
    	Document prod = new Document("prodname",doc.getProdname()).append("price",doc.getPrice())
    			.append("prodtype",doc.getProdtype());
    	coll.insertOne(prod);
    	
		return "Insert 1 record is successful";
    	
    }
    public List<ProdDoc> getAllMessages(){
		MongoCollection<Document> coll = db.getCollection("product");
    	List<Document> list = new ArrayList<>();
    	ProdDoc prod = new ProdDoc();
    	List<ProdDoc> listnew = new ArrayList<>();
    	
    	list = coll.find().into(new ArrayList<Document>());
    	
    	for (int i = 0; i < coll.count(); i++){
    	Document doc = list.get(i);
    	String prodname=doc.getString("prodname");
    	String prodtype=doc.getString("prodtype");
    	int price=doc.getInteger("price", 0);
    	
    	prod.setProdname(prodname);
    	prod.setProdtype(prodtype);
    	prod.setPrice(price);
    	listnew.add(new ProdDoc(prodname, prodtype, price));
    	}
    	
    	return listnew;
    	
    	
    }
}
