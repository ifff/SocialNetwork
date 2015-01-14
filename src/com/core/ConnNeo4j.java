package com.core;


import com.core.*;

import java.io.*;

import org.neo4j.graphdb.*;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.neo4j.kernel.impl.util.FileUtils;


public class ConnNeo4j {
	private static final String DB_PATH = "/Users/fanfeifan/Desktop/SocialNetwork";

    String myString;
    GraphDatabaseService graphDb;
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
    	clearDb();
    	graphDb = new GraphDatabaseFactory().newEmbeddedDatabase( DB_PATH );
    	registerShutdownHook( graphDb );
    	
    	loadGraphFile();
    }
    
	// load social network from sample file
	void loadGraphFile() {
		// load Person configure
		System.out.println("start read file");
		File personFile = new File("/Users/fanfeifan/Documents/Develop/SocialNetwork/WebRoot/Database/petster-hamster/ent.petster-hamster");
		BufferedReader reader1 = null;
//		BufferedReader reader2 = null;
		try {
			reader1 = new BufferedReader(new FileReader(personFile));
			String line = null;
			while ((line = reader1.readLine()) != null) {
				String property[] = line.split("\" \"");
				// START SNIPPET: transaction
		        try ( Transaction tx = graphDb.beginTx() )
		        {
					Node personNode = graphDb.createNode();
					personNode.setProperty("id", property[0]);
					personNode.setProperty("name", property[1]);
					personNode.setProperty("joined", property[2]);
					personNode.setProperty("species", property[3]);
					personNode.setProperty("coloring", property[4]);
					personNode.setProperty("gender", property[5]);
					personNode.setProperty("birthday", property[6]);
					personNode.setProperty("age", property[7]);
					personNode.setProperty("hometown", property[8]);
					personNode.setProperty("favorite_toy", property[9]);
					personNode.setProperty("favorite_activity", property[10]);
					personNode.setProperty("favorite_food", property[11]);
		            // START SNIPPET: transaction
		            tx.success();
		        }
		        // END SNIPPET: transaction
				for (int i = 0; i < property.length; i ++) {
					System.out.println(property[i]);
					
				}
				break;
//				break;
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
	
	private void clearDb()
    {
        try
        {
            FileUtils.deleteRecursively( new File( DB_PATH ) );
        }
        catch ( IOException e )
        {
            throw new RuntimeException( e );
        }
    }
    
	void removeData()
    {
		Node firtNode;
		Node secondNode;
        try ( Transaction tx = graphDb.beginTx() )
        {
            // START SNIPPET: removingData
            // let's remove the data
//            firstNode.getSingleRelationship( RelTypes.KNOWS, Direction.OUTGOING ).delete();
//            firstNode.delete();
//            graphDb.getAllNodes()
            // END SNIPPET: removingData

            tx.success();
        }
    }
	
	void shutDown()
    {
        System.out.println();
        System.out.println( "Shutting down database ..." );
        // START SNIPPET: shutdownServer
        graphDb.shutdown();
        // END SNIPPET: shutdownServer
    }
    
 // START SNIPPET: shutdownHook
    private static void registerShutdownHook( final GraphDatabaseService graphDb )
    {
        // Registers a shutdown hook for the Neo4j instance so that it
        // shuts down nicely when the VM exits (even if you "Ctrl-C" the
        // running application).
        Runtime.getRuntime().addShutdownHook( new Thread()
        {
            @Override
            public void run()
            {
                graphDb.shutdown();
            }
        } );
    } 
}
