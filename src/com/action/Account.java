package com.action;

import javax.servlet.http.HttpServletRequest;

import com.core.*;

import org.neo4j.graphdb.*;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import com.actionForm.AccountForm;
import com.core.ConnNeo4j;
import com.dao.AccountDAO;
//import javax.jms.Session;

public class Account extends HttpServlet {
	private AccountDAO accountDAO = null; 

	public Account() {
		this.accountDAO = new AccountDAO();
	}

	@SuppressWarnings("deprecation")
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");

		if (action == null || "".equals(action)) {
			request.getRequestDispatcher("error.jsp")
					.forward(request, response);
		} else if ("login".equals(action)) {
			AccountLogin(request, response);
		} 
		else if (action.equals("register")) {
			ChStr chStr=new ChStr();
			String aName = chStr.filterStr(request.getParameter("name"));
			String birthday = chStr.filterStr(request.getParameter("birthday"));
			String hometown = chStr.filterStr(request.getParameter("hometown"));
			String activity = chStr.filterStr(request.getParameter("activity"));
			String food = chStr.filterStr(request.getParameter("food"));
			String pwd = chStr.filterStr(request.getParameter("pwd1"));
			String gender = chStr.filterStr(request.getParameter("gender"));
			try ( Transaction tx = ConnNeo4j.getGraphDb().beginTx() )
	        {
				Node personNode = ConnNeo4j.getGraphDb().createNode();
				String id = String.valueOf(ConnNeo4j.getPersonMap().size()+1);
				personNode.setProperty("id", id);
				personNode.setProperty("name", aName);
				Date current = new Date();
				personNode.setProperty("joined", current.toLocaleString());
				personNode.setProperty("gender", gender);
				personNode.setProperty("birthday", birthday);
				personNode.setProperty("hometown", hometown);
				personNode.setProperty("favorite_activity", activity);
				personNode.setProperty("favorite_food", food);
				personNode.setProperty("passwd", pwd);
				personNode.setProperty("species", "animal");
				personNode.setProperty("coloring", "red");
				personNode.setProperty("age", "18");
				personNode.setProperty("favorite_toy", "toy");
				personNode.setProperty("type", "user");
				Label label = DynamicLabel.label("User");
				personNode.addLabel(label);
	            // add into hashmap
				Person person = new Person(personNode);
	            ConnNeo4j.getPersonMap().put(id, person);
	            System.out.println("register successfully! id is "+ id);
	            response.sendRedirect("index.jsp");
				// START SNIPPET: transaction
	            tx.success();
	        }
		}
		else if (action.equals("updateProfile")) {
			ChStr chStr=new ChStr();
			String aName = chStr.filterStr(request.getParameter("name"));
			String birthday = chStr.filterStr(request.getParameter("birthday"));
			String hometown = chStr.filterStr(request.getParameter("hometown"));
			String activity = chStr.filterStr(request.getParameter("activity"));
			String food = chStr.filterStr(request.getParameter("food"));
			String id = request.getSession().getAttribute("id").toString();
			try ( Transaction tx = ConnNeo4j.getGraphDb().beginTx() )
		        {
		       		Person person = ConnNeo4j.getPersonMap().get(id);
		       		if (aName != "" && aName != null) {
		       			person.getUnderlyingNode().setProperty("name", aName);
		       		}
		       		if (birthday != "" && birthday != null) {
		       			person.getUnderlyingNode().setProperty("birthday", birthday);
		       		}
		       		if (hometown != "" && hometown != null) {
		       			person.getUnderlyingNode().setProperty("hometown", hometown);
		       		}
		       		if (activity != "" && activity != null) {
		       			person.getUnderlyingNode().setProperty("favorite_activity", activity);
		       		}
		       		if (food != "" && food != null) {
		       			person.getUnderlyingNode().setProperty("favorite_food", food);
		       		}
			        tx.success();
		        }
			response.sendRedirect("profile.jsp");
		}
	}



	public void AccountLogin(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		AccountForm accountForm = new AccountForm();
		accountForm.setId(request.getParameter("name"));
		accountForm.setPwd(request.getParameter("pwd"));
		String path = request.getSession().getServletContext().getRealPath("/upload/");
		System.out.println("path:"+path);
//		accountForm.setRand(request.getParameter("rand"));
		System.out.println("id:"+request.getParameter("name")+",pwd:"+request.getParameter("pwd"));
//		//System.out.println(accountForm.getName() + accountForm.getPwd() + accountForm.getRand());
//		HttpSession session_rand=request.getSession();
//		String rand = (String)session_rand.getAttribute("rand");
//		if(!rand.equals(request.getParameter("rand"))){
//			request.setAttribute("error", "验证码输入错误");
//			request.getRequestDispatcher("error.jsp")
//					.forward(request, response);
//		}
//		else{
			int ret = accountDAO.checkAccount(accountForm);
			if (ret == 1) {
	            HttpSession session=request.getSession();
	            session.setAttribute("name",accountForm.getName());
	            session.setAttribute("id", accountForm.getId());
//	            accountDAO.postStatus(accountForm.getId(), "hello neo4j");
//	            request.getRequestDispatcher("home.jsp?id="+accountForm.getId()).forward(request, response);
	            response.sendRedirect("home.jsp?id="+accountForm.getId());
			} else {
				request.setAttribute("error", "用户名或密码输入错误");
				request.getRequestDispatcher("error.jsp")
						.forward(request, response);
			}
//		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
