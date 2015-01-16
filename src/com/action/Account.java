package com.action;

import javax.servlet.http.HttpServletRequest;
import com.core.*;
import org.neo4j.graphdb.*;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

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

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");

		if (action == null || "".equals(action)) {
			request.getRequestDispatcher("error.jsp")
					.forward(request, response);
		} else if ("login".equals(action)) {
			AccountLogin(request, response);
		} 
	}



	public void AccountLogin(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		AccountForm accountForm = new AccountForm();
		accountForm.setId(request.getParameter("name"));
		accountForm.setPwd(request.getParameter("pwd"));
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
				String accountType = accountDAO.getAccountType(accountForm);
	            HttpSession session=request.getSession();
	            session.setAttribute("name",accountForm.getName());
	            session.setAttribute("id", accountForm.getId());
	            session.setAttribute("AccountType", accountType);
//	            accountDAO.postStatus(accountForm.getId(), "hello neo4j");
	            response.sendRedirect("home.jsp");
			} else {
				request.setAttribute("error", "用户名或密码输入错误");
				request.getRequestDispatcher("error.jsp")
						.forward(request, response);
			}
//		}
	}

	// ��ѯ����Ա��Ϣ
/*	private void AccountQuery(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String str = null;
		request.setAttribute("AccountQuery", AccountDAO.query(str));
		request.getRequestDispatcher("Account.jsp").forward(request, response);
	}*/

	// ��ӹ���Ա��Ϣ
	/*private void AccountAdd(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		AccountForm accountForm = new AccountForm();
		accountForm.setName(request.getParameter("name")); // ��ȡ���ù���Ա���
		accountForm.setPwd(request.getParameter("pwd")); // ��ȡ����������
		int ret = AccountDAO.insert(accountForm); // ������ӹ���Ա��Ϣ
		if (ret == 1) {
			request.getRequestDispatcher("Account_ok.jsp?para=1").forward(
					request, response); // ת������Ա��Ϣ��ӳɹ�ҳ��

		} else if (ret == 2) {
			request.setAttribute("error", "�ù���Ա��Ϣ�Ѿ���ӣ�"); // ��������Ϣ���浽error������
			request.getRequestDispatcher("error.jsp")
					.forward(request, response); // ת��������ʾҳ��
		} else {
			request.setAttribute("error", "��ӹ���Ա��Ϣʧ�ܣ�"); // ��������Ϣ���浽error������
			request.getRequestDispatcher("error.jsp")
					.forward(request, response); // ת��������ʾҳ��
		}
	}*/

	// ��ѯ�޸Ĺ���Ա��Ϣ
	/*private void AccountModifyQuery(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		AccountForm AccountForm = new AccountForm();
		AccountForm.setId(Integer.valueOf(request.getParameter("id")));// ��ȡ�����ù���ID��
		System.out.print("��ѯ����id:" + request.getParameter("id"));
		request.setAttribute("AccountQueryif", AccountDAO
				.query_update(AccountForm));
		request.getRequestDispatcher("Account_Modify.jsp").forward(request,
				response); // ת��Ȩ�����óɹ�ҳ��
	}

	// �޸�����ʱ��ѯ
	private void pwdQuery(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		AccountForm AccountForm = new AccountForm();
		HttpSession session = request.getSession();
		String Account = (String) session.getAttribute("Account");
		AccountForm.setName(Account);
		System.out.print("��ѯ����Account:" + Account);
		request.setAttribute("pwdQueryif", AccountDAO.query_pwd(AccountForm));
		request.getRequestDispatcher("pwd_Modify.jsp").forward(request,
				response);
	}

	// ����ԱȨ������
	private void AccountModify(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		AccountForm AccountForm = new AccountForm();
		AccountForm.setId(Integer.parseInt(request.getParameter("id"))); // ��ȡ�����ù���ԱID��
		AccountForm.setName(request.getParameter("name")); // ��ȡ�����ù���Ա���
		AccountForm.setPwd(request.getParameter("pwd")); // ��ȡ�����ù���Ա����
		AccountForm.setSysset(request.getParameter("sysset") == null ? 0
				: Integer.parseInt(request.getParameter("sysset"))); // ��ȡ������ϵͳ����Ȩ��
		AccountForm.setReaderset(request.getParameter("readerset") == null ? 0
				: Integer.parseInt(request.getParameter("readerset"))); // ��ȡ�����ö��߹���Ȩ��
		AccountForm.setBookset(request.getParameter("bookset") == null ? 0
				: Integer.parseInt(request.getParameter("bookset"))); // ��ȡ������ͼ�����Ȩ��
		AccountForm
				.setBorrowback(request.getParameter("borrowback") == null ? 0
						: Integer.parseInt(request.getParameter("borrowback"))); // ��ȡ������ͼ��軹Ȩ��
		AccountForm.setSysquery(request.getParameter("sysquery") == null ? 0
				: Integer.parseInt(request.getParameter("sysquery"))); // ��ȡ������ϵͳ��ѯȨ��
		int ret = AccountDAO.update(AccountForm); // �������ù���ԱȨ�޵ķ���
		if (ret == 0) {
			request.setAttribute("error", "���ù���ԱȨ��ʧ�ܣ�"); // ���������ʾ��Ϣ��error������
			request.getRequestDispatcher("error.jsp")
					.forward(request, response); // ת��������ʾҳ��
		} else {
			request.getRequestDispatcher("Account_ok.jsp?para=2").forward(
					request, response);// ת��Ȩ�����óɹ�ҳ��
		}
	}

	// ɾ�����Ա��Ϣ
	private void AccountDel(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		AccountForm AccountForm = new AccountForm();
		AccountForm.setId(Integer.valueOf(request.getParameter("id"))); // ��ȡ�����ù���ԱID��
		int ret = AccountDAO.delete(AccountForm); // ����ɾ����Ϣ�ķ���delete()
		if (ret == 0) {
			request.setAttribute("error", "ɾ�����Ա��Ϣʧ�ܣ�"); // ���������ʾ��Ϣ��error������
			request.getRequestDispatcher("error.jsp")
					.forward(request, response); // ת��������ʾҳ��
		} else {
			request.getRequestDispatcher("Account_ok.jsp?para=3").forward(
					request, response); // ת��ɾ�����Ա��Ϣ�ɹ�ҳ��
		}
	}

	// �޸Ĺ���Ա����
	private void modifypwd(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		AccountForm AccountForm = new AccountForm();
		AccountForm.setName(request.getParameter("name"));
		AccountForm.setPwd(request.getParameter("pwd"));
		int ret = AccountDAO.updatePwd(AccountForm);
		if (ret == 0) {
			request.setAttribute("error", "��Ŀ���ʧ�ܣ�");
			request.getRequestDispatcher("error.jsp")
					.forward(request, response);
		} else {
			request.getRequestDispatcher("pwd_ok.jsp").forward(request,
					response);
		}
	}*/

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
