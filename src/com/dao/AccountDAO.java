package com.dao;

import com.core.*;

import java.util.*;

import com.actionForm.AccountForm;
import com.actionForm.StatusForm;

import java.sql.*;
import java.*;

import org.neo4j.graphdb.Transaction;

public class AccountDAO {

    public int checkAccount(AccountForm accountForm) {
//    	ConnNeo4j.createDb();
    	ConnNeo4j.loadPersonNodes();
        ChStr chStr=new ChStr();
        String id = chStr.filterStr(accountForm.getId());
        String pwd = chStr.filterStr(accountForm.getPwd());
        System.out.println("User:"+id+",passwd:"+pwd);
        try ( Transaction tx = ConnNeo4j.getGraphDb().beginTx() )
        {
        	System.out.println("length:" + ConnNeo4j.getPersonMap().size());
	        if (ConnNeo4j.getPersonMap().containsKey(id)) {
				// START SNIPPET: transaction
	
		        	Person person = ConnNeo4j.getPersonMap().get(id);
		        	String pid = person.getUnderlyingNode().getProperty("id").toString();
		        	String ppwd = person.getUnderlyingNode().getProperty("passwd").toString();
		        	System.out.println("Userp:"+pid+",ppasswd:"+ppwd);
		        	if (!id.equals(pid) || !pwd.equals(ppwd))
		        		return 0;
		        	else 
		        		return 1;
	        }
	        else {
	        	System.out.println("Not found user");
	        }
	        tx.success();
        }
        return 0;   
    }
    
    public AccountForm getAccountForm(String userId) {
//    	System.out.println("start get account form");
    	AccountForm account = new AccountForm();
    	try ( Transaction tx = ConnNeo4j.getGraphDb().beginTx() )
        {
        	Person person = ConnNeo4j.getPersonMap().get(userId);
            account.initWithPersonNode(person);
        	tx.success();
        }
    	return account;
    }
    
    public void postStatus(String userId, String text, String picturePath) {
        try ( Transaction tx = ConnNeo4j.getGraphDb().beginTx() )
        {
        	Person person = ConnNeo4j.getPersonMap().get(userId);
        	person.addStatus(text, picturePath);
        	tx.success();
        }
    }
    
    public Collection<String> getFriendList(String userId) {
    	Collection<String> friendList = new ArrayList<String>();
    	try ( Transaction tx = ConnNeo4j.getGraphDb().beginTx() )
        {
    		Person person = ConnNeo4j.getPersonMap().get(userId);
    		Iterable<Person> friends = person.getFriends();
    		for (Person user: friends) {
    			String friend = user.getUnderlyingNode().getProperty("id").toString()+"\t"+user.getName()+"\t"+user.getUnderlyingNode().getProperty("hometown").toString();
    			friend = friend + "\t" + user.getUnderlyingNode().getProperty("favorite_activity").toString()+"\t"+user.getUnderlyingNode().getProperty("gender").toString();
    			friend = friend + "\t" + user.getUnderlyingNode().getProperty("joined").toString();
    			friendList.add(friend);
    		}
    		tx.success();
        }
    	return friendList;
    }
    
    public Collection<StatusForm> getFriendStatus(String userId) {
    	Collection<StatusForm> statusList = new ArrayList<StatusForm>();
    	try ( Transaction tx = ConnNeo4j.getGraphDb().beginTx() )
        {
    		Person person = ConnNeo4j.getPersonMap().get(userId);
    		Iterator<StatusUpdate> statuses = person.friendStatuses();
        	while (statuses.hasNext()) {
        		StatusUpdate status = statuses.next();
        		StatusForm form = new StatusForm();
        		form.setText(status.getStatusText());
        		form.setDate(status.getDate().toLocaleString());
        		Person friend = status.getPerson();
        		form.setUserId(friend.getUnderlyingNode().getProperty("id").toString());
        		form.setUserName(friend.getUnderlyingNode().getProperty("name").toString());
        		form.setPicturePath(status.getStatusPicturePath());
        		statusList.add(form);
        	}
    		tx.success();
        }
    	return statusList;
    }
    
    @SuppressWarnings("deprecation")
	public Collection<StatusForm> getPersonalStatuses(String userId) {
    	Collection<StatusForm> statusList = new ArrayList<StatusForm>();
    	try ( Transaction tx = ConnNeo4j.getGraphDb().beginTx() )
        {
//    		userId = "875"; //875
        	Person person = ConnNeo4j.getPersonMap().get(userId);
//        	person.addStatus("Today is a very nice day","/upload/2.png");
        	Iterable<StatusUpdate> statuses = person.getStatus();

//        	person.addStatus("875 message 2","/upload/2.png");
//        	statuses = person.friendStatuses();
        	
//        	System.out.println("start print status list");
        	for (StatusUpdate status: statuses) {
        		System.out.println("text:"+status.getStatusText()+","+status.getDate().toLocaleString()+","+status.getStatusPicturePath());
        		StatusForm form = new StatusForm();
        		form.setText(status.getStatusText());
        		form.setDate(status.getDate().toLocaleString());
        		Person friend = status.getPerson();
        		form.setUserId(friend.getUnderlyingNode().getProperty("id").toString());
        		form.setUserName(friend.getUnderlyingNode().getProperty("name").toString());
        		form.setPicturePath(status.getStatusPicturePath());
        		statusList.add(form);
        	}
//        	while (statuses.hasNext()) {
//        		StatusUpdate status = statuses.next();
//        		System.out.println("text:"+status.getStatusText()+","+status.getDate().toLocaleString());
//        	}
        	tx.success();
        }
    	return statusList;
    }
   
}
