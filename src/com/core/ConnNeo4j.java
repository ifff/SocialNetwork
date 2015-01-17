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
    	registerShutdownHook( graphDb );
    	loadGraphFile();
    }
	
	public static GraphDatabaseService getGraphDb() {
		if (graphDb == null) {
//			createDb();
			graphDb = new GraphDatabaseFactory().newEmbeddedDatabase( DB_PATH );
			registerShutdownHook( graphDb );
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
		//ConnNeo4j.createDb();
		int command = 0;
		char continueFlag = 'y';
		while (continueFlag == 'y') {
			System.out.println("########## Scoial Network Graph Search Console ##########");
			System.out.println("[0] exit");
			System.out.println("[1] search a user's friends with depth");
			System.out.println("[2] get a user's status list");
			System.out.println("[3] get the status list of the user's friends");
			System.out.println("[4]  Add friend relationship between two person");
			System.out.println("[5]  Remove friend relationship between two person");
			System.out.println("[6] get relationship between two person (show total path count and closest path)");
			System.out.println("[7] recommendation friends for a specific person");
			Scanner scan = new Scanner(System.in);
			command = scan.nextInt();
			if (command == 0) {
				break;
			}
			else if (command == 1) {
				int tag = 1;
				while (tag != 0){
					System.out.println("######## search a user's friends with depth #####");
					System.out.println("Please input the userId and depth:");
					int userId = scan.nextInt();
					int depth = scan.nextInt();
			    	try ( Transaction tx = ConnNeo4j.getGraphDb().beginTx() )
			        {
						Person user = ConnNeo4j.getPersonMap().get(String.valueOf(userId));
						Iterable<Person> friends = user.getFriendsByDepth(depth);
						int friendCount = 1;
						System.out.println("The friend list of user "+user.getName() + "(" + userId + ") is followed...");
						for (Person friend: friends) {
							System.out.println("["+friendCount+"]"+friend.getName()+"("+friend.getUnderlyingNode().getProperty("id")+")");
							friendCount += 1;
						}
						tx.success();
			        }
					System.out.println("######## search a user's friends with depth #####");
					System.out.println("[0] back to main menu");
					System.out.println("[1] continue search friends");
					tag = scan.nextInt();
				}
			}
			else if (command == 4) {
				int tag = 1;
				while (tag != 0){
					System.out.println("####### Add friend relationship between two person ######");
					System.out.println("Please input the ids of the two person:");
					int userId1 = scan.nextInt();
					int userId2 = scan.nextInt();
			    	try ( Transaction tx = ConnNeo4j.getGraphDb().beginTx() )
			        {
						Person user1 = ConnNeo4j.getPersonMap().get(String.valueOf(userId1));
						Person user2 = ConnNeo4j.getPersonMap().get(String.valueOf(userId2));
						user1.addFriend(user2);
						Iterable<Person> friends = user1.getFriends();
						int friendCount = 1;
						System.out.println("The friend list of user "+user1.getName() + "(" + userId1 + ") is followed...");
						for (Person friend: friends) {
							System.out.println("["+friendCount+"]"+friend.getName()+"("+friend.getUnderlyingNode().getProperty("id")+")");
							friendCount += 1;
						}
						tx.success();
			        }
			    	System.out.println("######  Add friend relationship between two person ######");
					System.out.println("[0] back to main menu");
					System.out.println("[1] continue add friend relationship");
					tag = scan.nextInt();
				}
			}
			else if (command == 5) {
				int tag = 1;
				while (tag != 0){
					System.out.println("####### Remove friend relationship between two person ######");
					System.out.println("Please input the ids of the two person:");
					int userId1 = scan.nextInt();
					int userId2 = scan.nextInt();
			    	try ( Transaction tx = ConnNeo4j.getGraphDb().beginTx() )
			        {
						Person user1 = ConnNeo4j.getPersonMap().get(String.valueOf(userId1));
						Person user2 = ConnNeo4j.getPersonMap().get(String.valueOf(userId2));
						user1.removeFriend(user2);;
						Iterable<Person> friends = user1.getFriends();
						int friendCount = 1;
						System.out.println("The friend list of user "+user1.getName() + "(" + userId1 + ") is followed...");
						for (Person friend: friends) {
							System.out.println("["+friendCount+"]"+friend.getName()+"("+friend.getUnderlyingNode().getProperty("id")+")");
							friendCount += 1;
						}
						tx.success();
			        }
			    	System.out.println("######  Remove friend relationship between two person ######");
					System.out.println("[0] back to main menu");
					System.out.println("[1] continue remove friend relationship");
					tag = scan.nextInt();
				}
			}
			else if (command == 6) {
				int tag = 1;
				while (tag != 0){
					System.out.println("####### get closest relationship between two person ######");
					System.out.println("Please input the ids of the two person:");
					int userId1 = scan.nextInt();
					int userId2 = scan.nextInt();
			    	try ( Transaction tx = ConnNeo4j.getGraphDb().beginTx() )
			        {
						Person user1 = ConnNeo4j.getPersonMap().get(String.valueOf(userId1));
						Person user2 = ConnNeo4j.getPersonMap().get(String.valueOf(userId2));
						Iterable<Person> path = user1.getShortestPathTo(user2, 6);
						System.out.println("The path between "+user1.getName() + "(" + user1.getUnderlyingNode().getProperty("id") + ") "+user2.getName()+"("+String.valueOf(userId2)+" )is followed...");
						String totalPath = "";
						for (Person friend: path) {
							if (friend.getUnderlyingNode().getProperty("id").equals(String.valueOf(userId2))) {
								totalPath += friend.getName()+"("+friend.getUnderlyingNode().getProperty("id")+")";
							}
							else {
								totalPath += friend.getName()+"("+friend.getUnderlyingNode().getProperty("id")+") ==> ";
							}
						}
						System.out.println(totalPath);
						tx.success();
			        }
			    	System.out.println("###### get closest relationship between two person ######");
					System.out.println("[0] back to main menu");
					System.out.println("[1] continue search friends");
					tag = scan.nextInt();
				}
			}
			else if (command == 7) {
				int tag = 1;
				while (tag != 0){
					System.out.println("######## recommendation friends for a specific person #####");
					System.out.println("Please input the userId and recommendation count:");
					int userId = scan.nextInt();
					int count = scan.nextInt();
			    	try ( Transaction tx = ConnNeo4j.getGraphDb().beginTx() )
			        {
						Person user = ConnNeo4j.getPersonMap().get(String.valueOf(userId));
						Iterable<Person> friends = user.getFriendRecommendation(count);
						int friendCount = 1;
						System.out.println("The recommendation friend list of user "+user.getName() + "(" + userId + ") is followed...");
						for (Person friend: friends) {
							System.out.println("["+friendCount+"]"+friend.getName()+"("+friend.getUnderlyingNode().getProperty("id")+")");
							friendCount += 1;
						}
						tx.success();
			        }
					System.out.println("######## recommendation friends for a specific person #####");
					System.out.println("[0] back to main menu");
					System.out.println("[1] continue recommendation friends");
					tag = scan.nextInt();
				}
			}
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
