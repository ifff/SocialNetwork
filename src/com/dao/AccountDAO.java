package com.dao;

import com.core.*;
import java.util.*;
import com.actionForm.AccountForm;
import java.sql.*;
import java.*;

public class AccountDAO {
    private ConnDB conn = new ConnDB();
   /* public Collection query(String queryif) {
        AccountForm AccountForm = null;
      Collection Accountcoll = new ArrayList();
        String sql = "";
        if (queryif == null || queryif == "" || queryif == "all") {	//������queryif��ֵΪnull��all���ʱ��ѯȫ�����
            sql = "select m.*,p.sysset,p.readerset,p.bookset,p.borrowback,p.sysquery from tb_Account m left join tb_purview p on m.id=p.id";
            }else{
                sql="select m.*,p.sysset,p.readerset,p.bookset,p.borrowback,p.sysquery from tb_Account m left join tb_purview p on m.id=p.id where m.name='"+queryif+"'";			//�˴���ҪӦ��������
        }
        ResultSet rs = conn.executeQuery(sql);					//ִ��SQL���
        try {											//��׽�쳣��Ϣ
            while (rs.next()) {
                AccountForm = new AccountForm();
                AccountForm.setId(Integer.valueOf(rs.getString(1)));
                AccountForm.setName(rs.getString(2));
                AccountForm.setPwd(rs.getString(3));
                AccountForm.setSysset(rs.getInt(4));
                AccountForm.setReaderset(rs.getInt(5));
                AccountForm.setBookset(rs.getInt(6));
                AccountForm.setBorrowback(rs.getInt(7));
                AccountForm.setSysquery(rs.getInt(8));
                Accountcoll.add(AccountForm);			//����ѯ���浽Collection������
            }
        } catch (SQLException e) {}
        return Accountcoll;								//���ز�ѯ���
    }*/

    /*********************************************************/
    //��ѯȨ����Ϣ
   /* public AccountForm query_p(String str) {
        AccountForm AccountForm1 = null;
        String sql = "select m.*,p.sysset,p.readerset,p.bookset,p.borrowback,p.sysquery from tb_Account m left join tb_purview p on m.id=p.id where m.name='"+str+"'";

        ResultSet rs = conn.executeQuery(sql);
        try {
            while (rs.next()) {
                AccountForm1 = new AccountForm();
                AccountForm1.setId(Integer.valueOf(rs.getString(1)));
                AccountForm1.setName(rs.getString(2));
                AccountForm1.setPwd(rs.getString(3));
                AccountForm1.setSysset(rs.getInt(4));
                AccountForm1.setReaderset(rs.getInt(5));
                AccountForm1.setBookset(rs.getInt(6));
                AccountForm1.setBorrowback(rs.getInt(7));
                AccountForm1.setSysquery(rs.getInt(8));
            }
        } catch (SQLException ex) {
        	ex.printStackTrace();
        }finally{
        	conn.close();
        }
        return AccountForm1;
    }*/


    public int checkAccount(AccountForm accountForm) {
        int flag = 0;
        ChStr chStr=new ChStr();
        String sql = "SELECT * FROM tb_account where aname='" +
        chStr.filterStr(accountForm.getName()) + "'";
        ResultSet rs = conn.executeQuery(sql);
        try {
            if (rs.next()) {
                String pwd = chStr.filterStr(accountForm.getPwd());		//��ȡ��������벢���������ַ��е�Σ���ַ�
                if (pwd.equals(rs.getString(3))) {
                    flag = 1;
                } else {
                    flag = 0;
                }
            }else{
                flag = 0;
            }
        } catch (SQLException ex) {
            flag = 0;
        }finally{
        	conn.close();
        }
        return flag;
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

   /* //�޸�ʱӦ�õĲ�ѯ����
    public AccountForm query_update(AccountForm AccountForm) {
        AccountForm AccountForm1 = null;
        String sql = "select m.*,p.sysset,p.readerset,p.bookset,p.borrowback,p.sysquery from tb_Account m left join tb_purview p on m.id=p.id where m.id=" +
                     AccountForm.getId() + "";

        ResultSet rs = conn.executeQuery(sql);	//ִ�в�ѯ���
        try {
            while (rs.next()) {
                AccountForm1 = new AccountForm();
                AccountForm1.setId(Integer.valueOf(rs.getString(1)));
                AccountForm1.setName(rs.getString(2));
                AccountForm1.setPwd(rs.getString(3));
                AccountForm1.setSysset(rs.getInt(4));
                AccountForm1.setReaderset(rs.getInt(5));
                AccountForm1.setBookset(rs.getInt(6));
                AccountForm1.setBorrowback(rs.getInt(7));
                AccountForm1.setSysquery(rs.getInt(8));
            }
        } catch (SQLException ex) {
        	ex.printStackTrace();	//����쳣��Ϣ
        }finally{
        	conn.close();	//�ر���ݿ�����
        }
        return AccountForm1;
    }*/
    //��Ŀ���ʱӦ�õĲ�ѯ����
    public AccountForm query_pwd(AccountForm AccountForm) {
        AccountForm AccountForm1 = null;
        String sql = "SELECT * FROM tb_Account WHERE name='" +AccountForm.getName() + "'";
        ResultSet rs = conn.executeQuery(sql);
        try {
            while (rs.next()) {
                AccountForm1 = new AccountForm();
                AccountForm1.setId(Integer.valueOf(rs.getString(1)));
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
