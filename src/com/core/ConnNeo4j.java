package com.core;


import com.core.*;

import java.io.*;
import java.util.*;

import org.neo4j.graphdb.*;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.neo4j.graphdb.index.Index;
import org.neo4j.kernel.impl.util.FileUtils;


public class ConnNeo4j {
	private static final String DB_PATH = "/Users/fanfeifan/Desktop/SocialNetwork";
    private static GraphDatabaseService graphDb = null;
    private static Map<String, Person> personMap = new HashMap<String, Person>();

	public static
	void createDb()
    {
    	clearDb();
    	graphDb = new GraphDatabaseFactory().newEmbeddedDatabase( DB_PATH );
    	//registerShutdownHook( graphDb );
    	loadGraphFile();
    }
	
	public static GraphDatabaseService getGraphDb() {
		if (graphDb == null) {
			graphDb = new GraphDatabaseFactory().newEmbeddedDatabase( DB_PATH );
			//registerShutdownHook( graphDb );
		}
		return graphDb;
	}
	
	public static Map<String, Person> getPersonMap() {
		if (personMap.isEmpty()) {
			loadPersonNodes();
		}
		return personMap;
	}
	
	public static void loadPersonNodes() {
//		clearDb();
//		graphDb = getGraphDb();
//		if (!personMap.isEmpty()) return;
		// START SNIPPET: transaction
        try ( Transaction tx = getGraphDb().beginTx() )
        {
    		Label label = DynamicLabel.label( "User" );
        	try ( ResourceIterator<Node> users =
        			getGraphDb().findNodesByLabelAndProperty( label, "type", "user").iterator() )
            {
                while ( users.hasNext() )
                {
                	Node node = users.next();
                	Person personNode = new Person(node);
                    personMap.put(node.getProperty("id").toString(), personNode);
                }
            }
        	// START SNIPPET: transaction
            tx.success();
        }
	}
    
	// load social network from sample file
	static void loadGraphFile() {
		// load Person configure
//		System.out.println("start read file");
		File personFile = new File("/Users/fanfeifan/Documents/Develop/SocialNetwork/WebRoot/Database/petster-hamster/ent.petster-hamster");
		File relationFile = new File("/Users/fanfeifan/Documents/Develop/SocialNetwork/WebRoot/Database/petster-hamster/out.petster-hamster");
		BufferedReader reader1 = null;
		BufferedReader reader2 = null;
		try {
			reader1 = new BufferedReader(new FileReader(personFile));
			String line = null;
			while ((line = reader1.readLine()) != null) {
				String property[] = line.split("\" \"");
				// START SNIPPET: transaction
		        try ( Transaction tx = graphDb.beginTx() )
		        {
					Node personNode = graphDb.createNode();
					personNode.setProperty("id", property[0].substring(1,property[0].length()));
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
					personNode.setProperty("favorite_food", property[11].substring(0,property[11].length()-1));
					personNode.setProperty("passwd", "123456");
					personNode.setProperty("type", "user");
					Label label = DynamicLabel.label("User");
					personNode.addLabel(label);
		            // add into hashmap
					Person person = new Person(personNode);
		            personMap.put(property[0].substring(1,property[0].length()), person);
					// START SNIPPET: transaction
		            tx.success();
		        }
		        // END SNIPPET: transaction
			}
			// START SNIPPET: transaction
	        try ( Transaction tx = graphDb.beginTx() )
	        {
				// load relationship
				reader2 = new BufferedReader(new FileReader(relationFile));
				while ((line = reader2.readLine()) != null) {
					String relation[] = line.split(" ");
					if (!personMap.containsKey(relation[0]) || !personMap.containsKey(relation[1]))
						continue;
					Person person1 = personMap.get(relation[0]);
					Person person2 = personMap.get(relation[1]);
//					System.out.println("add friend..."+relation[0]+','+relation[1]);
					person1.addFriend(person2);
				}
	            tx.success();
	        }
	        // END SNIPPET: transaction
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
	
	private static void clearDb()
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
	public static void main( final String[] args )
    {
    
//        ConnNeo4j.createDb();
        ConnNeo4j.loadPersonNodes();
        
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
