package com.actionForm;
import org.neo4j.graphdb.Transaction;

import com.action.*;
import com.core.*;

public class AccountForm {
	private String id = "";
	private String name = "";
	private String pwd = "";
	private String rand = "";
	private String accountType = "";
	private String joined = "";
	private String species = "";
	private String coloring = "";
	private String gender = "";
	private String birthday = "";
	private String age = "";
	private String hometown = "";
	private String favorite_toy = "";
	private String favorite_activity = "";
	private String favorite_food = "";
	private String type = "user";
	
	public AccountForm() {
		
	}
	public AccountForm(AccountForm form) {
		this.id = form.id;
		this.name = form.name;
		this.pwd = form.pwd;
		this.rand = form.rand;
		this.accountType = form.accountType;
		this.joined = form.joined;
		this.species = form.species;
		this.coloring = form.coloring;
		this.gender = form.gender;
		this.birthday = form.birthday;
		this.age = form.age;
		this.hometown = form.hometown;
		this.favorite_activity = form.favorite_activity;
		this.favorite_toy = form.favorite_toy;
		this.favorite_food = form.favorite_food;
	}
	
	public void initWithPersonNode(Person person) {
//		try ( Transaction tx = ConnNeo4j.getGraphDb().beginTx() )
//        {
        	this.name = person.getUnderlyingNode().getProperty("name").toString();
        	this.joined = person.getUnderlyingNode().getProperty("joined").toString();
        	this.species = person.getUnderlyingNode().getProperty("species").toString();
        	this.coloring = person.getUnderlyingNode().getProperty("coloring").toString();
        	this.gender = person.getUnderlyingNode().getProperty("gender").toString();
        	this.birthday = person.getUnderlyingNode().getProperty("birthday").toString();
        	this.age = person.getUnderlyingNode().getProperty("age").toString();
        	this.hometown = person.getUnderlyingNode().getProperty("hometown").toString();
        	this.favorite_toy = person.getUnderlyingNode().getProperty("favorite_toy").toString();
        	this.favorite_activity = person.getUnderlyingNode().getProperty("favorite_activity").toString();
        	this.favorite_food = person.getUnderlyingNode().getProperty("favorite_food").toString();
        	this.type = person.getUnderlyingNode().getProperty("type").toString();
//        	tx.success();
//        }
	}
	public String getType() {
		return type;
	}	
	public void setType(String type) {
		this.type = type;
	}
	public String getFavoriteFood() {
		return favorite_food;
	}	
	public void setFavoriteFood(String food) {
		this.favorite_food = food;
	}
	public String getFavoriteActivity() {
		return favorite_activity;
	}	
	public void setFavoriteActivity(String activity) {
		this.favorite_activity = activity;
	}
	public String getFavoriteToy() {
		return favorite_toy;
	}	
	public void setFavoriteToy(String favoriteToy) {
		this.favorite_toy = favoriteToy;
	}
	public String getHometown() {
		return hometown;
	}	
	public void setHometown(String hometown) {
		this.hometown = hometown;
	}
	public String getAge() {
		return age;
	}	
	public void setAge(String age) {
		this.age = age;
	}
	public String getBirthday() {
		return birthday;
	}	
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getGender() {
		return gender;
	}	
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getColoring() {
		return coloring;
	}
	public void setColoring(String coloring) {
		this.coloring = coloring;
	}
	public String getSpecies() {
		return species;
	}
	
	public void setSpecies(String species) {
		this.species = species;
	}
	public String getJoined() {
		return joined;
	}
	
	public void setJoined(String joined) {
		this.joined = joined;
	}
	
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getName(){
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getPwd(){
		return pwd;
	}
	
	public String getRand(){
		return rand;
	}
	
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
	public void setRand(String rand){
		this.rand = rand;
	}
	
	public String getAccountType() {
		return accountType;
	}
	
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
}
