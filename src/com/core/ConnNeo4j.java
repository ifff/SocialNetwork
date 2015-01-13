package com.core;


import com.core.*;

import java.io.*;

import org.neo4j.graphdb.*;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;


public class ConnNeo4j {
	private static final String DB_PATH = "/Users/fanfeifan/Desktop/SocialNetwork";

    String myString;
    //GraphDatabaseService graphDb;
    Node myFirstNode;
    Node mySecondNode;
    Relationship myRelationship;

    
    public static void main( final String[] args )
    {
    
        ConnNeo4j myNeoInstance = new ConnNeo4j();
        myNeoInstance.createDb();
        myNeoInstance.removeData();
        myNeoInstance.shutDown();
        
    }
    
    @SuppressWarnings("deprecation")
	public
	void createDb()
    {
    	GraphDatabaseService graphDb = new GraphDatabaseFactory().newEmbeddedDatabase( DB_PATH );
    }
	// load social network from sample file
	void loadGraphFile() {
//		String path = System.getProperty("usr.dir");
//		String path1 = this.getClass().getResource("").getPath();
//		System.out.println("path"+path);
//		System.out.println("path1:"+path1);
		// load Person configure
		File personFile = new File("/Users/fanfeifan/Documents/Develop/SocialNetwork/WebRoot/Database/petster-hamster/ent.petster-hamster");
		BufferedReader reader1 = null;
//		BufferedReader reader2 = null;
		try {
			reader1 = new BufferedReader(new FileReader(personFile));
			String line = null;
			while ((line = reader1.readLine()) != null) {
				String property[] = line.split(" ");
				for (int i = 0; i < property.length; i ++) {
					System.out.println(property[i]);
				}
				break;
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader1 != null) {
				try {
					reader1.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
    
    @SuppressWarnings("deprecation")
	void removeData()
    {
    }
    
    void shutDown()
    {

        System.out.println("graphDB shut down.");   
    }    
}
