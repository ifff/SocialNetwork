package com.action;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jxl.*;
import jxl.write.*;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.neo4j.graphdb.Transaction;


import com.dao.AccountDAO;

import com.core.*;

public class FileUpload extends HttpServlet {
	private AccountDAO accountDAO = new AccountDAO();
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("GBK");	
//        String t = request.getAttribute("content");
//        System.out.println("get text:"+t);
		String action = request.getParameter("action");
		DiskFileItemFactory factory = new DiskFileItemFactory();
		String path = request.getSession().getServletContext().getRealPath("/upload/");
		HttpSession session=request.getSession();
		String id = (String) session.getAttribute("id");	
		// TEST
		if (id == null) id = "100";
		path = path + '/' + id + '/';
		factory.setRepository(new File(path));
		factory.setSizeThreshold(1024*1024) ;
		
		// check file path
		File pathFile = new File(path);
		if (!pathFile.exists()) {
			pathFile.mkdir();
		}
		
		ServletFileUpload upload = new ServletFileUpload(factory);	
		try {
			List<FileItem> list = (List<FileItem>)upload.parseRequest(request);
			for(FileItem item : list)
			{
				String name = item.getFieldName();
				if(item.isFormField())
				{
					String value = item.getString() ;
					request.setAttribute(name, value);
				}
				else
				{
//					String value = item.getName() ;		
//					int start = value.lastIndexOf("\\");
//					System.out.println("filename is " + path + filename + ", action is " + action);
//					InputStream instream = new FileInputStream(path + "/" + filename);
//					System.out.println(action);
					if (action.equals("uploadAvatar")) {  
						String filename = "avatar.png";
						item.write(new File(path, filename));
//						accountDAO.getPersonalStatuses(id); // need to be removed
//						boolean error = false;	
//						if (!error)
//							request.getRequestDispatcher("result.jsp?para=1").forward(request, response);
//						else 
//							request.getRequestDispatcher("result.jsp?para=2").forward(request, response);
					}
					if (action.equals("postStatus")) {						
				        ChStr chStr=new ChStr();
//				        String id = chStr.filterStr(accountForm.getId());
//				        String pwd = chStr.filterStr(accountForm.getPwd());
//				        System.out.println("User:"+id+",passwd:"+pwd);
				        String text = chStr.filterStr(request.getAttribute("content").toString());
				        System.out.println("text:"+text);
				        long current = new Date().getTime();
				        String filename = String.valueOf(current)+".png";
				        item.write(new File(path, filename));
				        String picturePath = "upload/" + id + "/" + filename; 
				        accountDAO.postStatus(id, text, picturePath);
				        //request.getRequestDispatcher("home.jsp?").forward(request, response);
				        response.sendRedirect("home.jsp?id="+id);
					}

				}
			}	
		}
		catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (Exception e) {
			// TODO Auto-generated catch block	
			e.printStackTrace();
		}
		

	}

}
