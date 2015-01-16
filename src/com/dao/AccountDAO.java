package com.dao;

import com.core.*;

import java.util.*;

import com.actionForm.AccountForm;

import java.sql.*;
import java.*;

import org.neo4j.graphdb.Transaction;

public class AccountDAO {
    private ConnDB conn = new ConnDB();

   


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
    
    @SuppressWarnings("deprecation")
	public Iterator<StatusUpdate> getPersonalStatuses(String userId) {
    	try ( Transaction tx = ConnNeo4j.getGraphDb().beginTx() )
        {
//    		userId = "875"; //875
        	Person person = ConnNeo4j.getPersonMap().get(userId);
        	
//        	person.addStatus("896 message 1","/upload/2.png");
//        	person.addStatus("875 message 2","/upload/2.png");
        	Iterator<StatusUpdate> statuses = person.friendStatuses();
        	
//        	System.out.println("start print status list");
//        	for (StatusUpdate status: statuses) {
//        		System.out.println("text:"+status.getStatusText()+","+status.getDate().toLocaleString());
//        	}
//        	while (statuses.hasNext()) {
//        		StatusUpdate status = statuses.next();
//        		System.out.println("text:"+status.getStatusText()+","+status.getDate().toLocaleString());
//        	}
        	tx.success();
        	return statuses;
        }
    }
    
    public String getAccountType(AccountForm accountForm){
    	String accountType = "";
        ChStr chStr=new ChStr();
        String sql = "SELECT * FROM tb_account where aname='" +
        chStr.filterStr(accountForm.getName()) + "'";
        ResultSet rs = conn.executeQuery(sql);
        try {
			if (rs.next()) {
			    String pwd = chStr.filterStr(accountForm.getPwd());		//��ȡ��������벢���������ַ��е�Σ���ַ�
			    if (pwd.equals(rs.getString(3))) {
			        accountType = rs.getString(4);
			    } 
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return accountType;
    }

 
    public AccountForm query_pwd(AccountForm AccountForm) {
        AccountForm AccountForm1 = null;
        String sql = "SELECT * FROM tb_Account WHERE name='" +AccountForm.getName() + "'";
        ResultSet rs = conn.executeQuery(sql);
        try {
            while (rs.next()) {
                AccountForm1 = new AccountForm();
                AccountForm1.setId(rs.getString(1));
                AccountForm1.setName(rs.getString(2));
                AccountForm1.setPwd(rs.getString(3));
            }
        } catch (SQLException ex) {
        	ex.printStackTrace();
        }finally{
        	conn.close();
        }
        return AccountForm1;
    }
    //������
    public int insert(AccountForm AccountForm) {
        String sql1="SELECT * FROM tb_Account WHERE name='"+AccountForm.getName()+"'";
        ResultSet rs = conn.executeQuery(sql1);
        String sql = "";
        int falg = 0;
            try {
                if (rs.next()) {
                    falg=2;
                } else {
                    sql = "INSERT INTO tb_Account (name,pwd) values('" +
                                 AccountForm.getName() + "','" +
                                 AccountForm.getPwd() +
                                 "')";
                    falg = conn.executeUpdate(sql);
                    System.out.println("��ӹ���Ա��Ϣ��SQL��" + sql);
                }
            } catch (SQLException ex) {
                falg=0;
            }finally{
            	conn.close();
            }
        return falg;
    }

    //���ù���ԱȨ��
    /*public int update(AccountForm AccountForm) {
        String sql1="SELECT * FROM tb_purview WHERE id="+AccountForm.getId()+"";
        ResultSet rs=conn.executeQuery(sql1);	//��ѯҪ����Ȩ�޵Ĺ���Ա��Ȩ����Ϣ
        String sql="";
        int falg=0;
        try {					//��׽�쳣��Ϣ
            if (rs.next()) {	//���Ѿ�����Ȩ��ʱ��ִ�и������
                sql = "Update tb_purview set sysset=" + AccountForm.getSysset() +
                             ",readerset=" + AccountForm.getReaderset() + ",bookset="+AccountForm.getBookset()+",borrowback="+AccountForm.getBorrowback()+",sysquery="+AccountForm.getSysquery()+" where id=" +
                     AccountForm.getId() + "";
            }else{	//δ����Ȩ��ʱ��ִ�в������
                sql="INSERT INTO tb_purview values("+AccountForm.getId()+","+AccountForm.getSysset()+","+AccountForm.getReaderset()+","+AccountForm.getBookset()+","+AccountForm.getBorrowback()+","+AccountForm.getSysquery()+")";
            }
            falg = conn.executeUpdate(sql);
            System.out.println("�޸����ʱ��SQL��" + sql);
        } catch (SQLException ex) {
            falg=0;			//��ʾ���ù���ԱȨ��ʧ��
        }finally{
        	conn.close();	//�ر���ݿ�����
        }
        return falg;
    }*/
    //�޸Ĺ���Ա����
public int updatePwd(AccountForm AccountForm){
    String sql="UPDATE tb_Account SET pwd='"+AccountForm.getPwd()+"' where name='"+AccountForm.getName()+"'";
    int ret=conn.executeUpdate(sql);
    System.out.println("�޸Ĺ���Ա����ʱ��SQL��"+sql);
    conn.close();
    return ret;
}

//ɾ�����
    public int delete(AccountForm AccountForm) {
    	int flag=0;
    	try{		//��׽�쳣��Ϣ
        String sql = "DELETE FROM tb_Account where id=" + AccountForm.getId() +"";
        flag = conn.executeUpdate(sql);	//ִ��ɾ�����Ա��Ϣ�����
        if (flag !=0){
            String sql1 = "DELETE FROM tb_purview where id=" + AccountForm.getId() +"";
            conn.executeUpdate(sql1);	//ִ��ɾ��Ȩ����Ϣ�����
        }}catch(Exception e){
        	System.out.println("ɾ�����Ա��Ϣʱ����Ĵ���"+e.getMessage());	//���������Ϣ
        }finally{
        	conn.close();	//�ر���ݿ�����
        }
        return flag;
    }
}
