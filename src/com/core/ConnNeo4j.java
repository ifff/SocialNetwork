package com.core;
import org.neo4j.graphdb.*;

public class ConnNeo4j {
	private static final String DB_PATH = "/Users/fanfeifan/Desktop/SocialNetwork";
	GraphDatabaseService graphDb;
	
	private static enum RelTypes implements RelationshipType
    {
        FRIEND, STATUS, NEXT
    }
}
