package com.action;

import java.io.*;

import java.util.ArrayList;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.*;
import jxl.write.*;
import jxl.write.biff.RowsExceededException;


import com.actionForm.CourseForm;
import com.actionForm.StudentForm;
import com.dao.AcdemicDAO;


public class FileDownload extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		String account = request.getParameter("account");
		System.out.println("account: " +account);
		String path = request.getSession().getServletContext().getRealPath("/download");	
		System.out.println("dpath: " + path);
		if (action.equals("exportStudentInfo")) {	// ����ѧ������Ϣ
			try {
				// ��ӵ�һ��
				String filename = "exportStudent.xls";
				OutputStream outStream = new FileOutputStream(new File(path + "/" + filename));
				WritableWorkbook wwb = Workbook.createWorkbook(outStream);
				WritableSheet ws = wwb.createSheet("Sheet 1", 0);
				Label label0 = new Label(0, 0, "ѧ��ѧ��");
				Label label1 = new Label(1, 0, "��������");
				Label label2 = new Label(2, 0, "��������");
				Label label3 = new Label(3, 0, "�Ա�");
				Label label4 = new Label(4, 0, "Ժϵ����");
				Label label5 = new Label(5, 0, "רҵ����");
				Label label6 = new Label(6, 0, "ѧ�Ƴ���");
				Label label7 = new Label(7, 0, "���֤��");
				Label label8 = new Label(8, 0, "��ѧʱ��");
				Label label9 = new Label(9, 0, "ѧ��״̬");
				Label label10 = new Label(10, 0, "��ҵѧУ");
				Label label11 = new Label(11, 0, "����");
				Label label12 = new Label(12, 0, "�绰");
				Label label13 = new Label(13, 0, "��ͥסַ");
				Label label14= new Label(14, 0, "��������");
				Label label15= new Label(15, 0, "������Ϣ");
				Label label16= new Label(16, 0, "������Ϣ");
				ws.addCell(label0);
				ws.addCell(label1);
				ws.addCell(label2);
				ws.addCell(label3);
				ws.addCell(label4);
				ws.addCell(label5);
				ws.addCell(label6);
				ws.addCell(label7);
				ws.addCell(label8);
				ws.addCell(label9);
				ws.addCell(label10);
				ws.addCell(label11);
				ws.addCell(label12);
				ws.addCell(label13);
				ws.addCell(label14);
				ws.addCell(label15);
				ws.addCell(label16);
				
				// ѧ����Ϣ
				String sql = "select * from tb_student where college_num in (select college_name from" +
						" tb_college_info where college_id = " + account + ")";
				System.out.println(sql);
				AcdemicDAO dao = new AcdemicDAO();
				ArrayList<StudentForm> al = dao.queryStudentInfo(sql);
				for (int i = 0; i < al.size(); i ++) {
					Label[] label = new Label[17];
					label[0] = new Label(0, i + 1, al.get(i).getStuNum());
					label[1] = new Label(1, i + 1, al.get(i).getNameCh());
					label[2] = new Label(2, i + 1, al.get(i).getBirthTime());
					label[3] = new Label(3, i + 1, al.get(i).getGender());
					label[4] = new Label(4, i + 1, al.get(i).getCollegeNum());
					label[5] = new Label(5, i + 1, al.get(i).getMajorNum());
					label[6] = new Label(6, i + 1, al.get(i).getSchLength());
					label[7] = new Label(7, i + 1, al.get(i).getIdNum());
					label[8] = new Label(8, i + 1, al.get(i).getEntrTime());
					label[9] = new Label(9, i + 1, al.get(i).getStuStatus());
					label[10] = new Label(10, i + 1, al.get(i).getGraduSch());
					label[11] = new Label(11, i + 1, al.get(i).getEmail());
					label[12] = new Label(12, i + 1, al.get(i).getTelephone());
					label[13] = new Label(13, i + 1, al.get(i).getHomeAddr());
					label[14] = new Label(14, i + 1, al.get(i).getPosCode());
					label[15] = new Label(15, i + 1, al.get(i).getCitizenShip());
					label[16] = new Label(16, i + 1, al.get(i).getNation());
					for (int j = 0; j < 17; j ++) ws.addCell(label[j]);
				}
				wwb.write();
				wwb.close();
				outStream.close();
				request.getRequestDispatcher("result.jsp?para=3").forward(request, response);
			} catch (RowsExceededException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (WriteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		if (action.equals("exportCourseInfo")) {	// �����γ̱���Ϣ
			try {
				// ��ӵ�һ��
				String filename = "exportCourse.xls";
				OutputStream outStream = new FileOutputStream(new File(path + "/" + filename));
				WritableWorkbook wwb = Workbook.createWorkbook(outStream);
				WritableSheet ws = wwb.createSheet("Sheet 1", 0);
				Label label0 = new Label(0, 0, "�γ̱��");
				Label label1 = new Label(1, 0, "�γ�������");
				Label label2 = new Label(2, 0, "�γ�Ӣ����");
				Label label3 = new Label(3, 0, "ѧ��");
				Label label4 = new Label(4, 0, "��ѧʱ");
				Label label5 = new Label(5, 0, "ѧ��");
				Label label6 = new Label(6, 0, "��ѧ��ʽ");
				Label label7 = new Label(7, 0, "����Ժϵ");
				Label label8 = new Label(8, 0, "�������");
				ws.addCell(label0);
				ws.addCell(label1);
				ws.addCell(label2);
				ws.addCell(label3);
				ws.addCell(label4);
				ws.addCell(label5);
				ws.addCell(label6);
				ws.addCell(label7);
				ws.addCell(label8);
				
				// ѧ����Ϣ
				String sql = "select * from tb_course_info where college_id in (select college_id from" +
						" tb_college_info where college_id = " + account + ")";
				System.out.println(sql);
				AcdemicDAO dao = new AcdemicDAO();
				ArrayList<CourseForm> al = dao.queryCourseInfo(sql);
				for (int i = 0; i < al.size(); i ++) {
					Label[] label = new Label[9];
					label[0] = new Label(0, i + 1, Integer.toString(al.get(i).getId()));
					label[1] = new Label(1, i + 1, al.get(i).getNameC());
					label[2] = new Label(2, i + 1, al.get(i).getNameE());
					label[3] = new Label(3, i + 1, String.valueOf(al.get(i).getCredit()));
					label[4] = new Label(4, i + 1, Integer.toString(al.get(i).getWeekHour()));
					label[5] = new Label(5, i + 1, al.get(i).getSemester());
					label[6] = new Label(6, i + 1, al.get(i).getTeacherMode());
					label[7] = new Label(7, i + 1, Integer.toString(al.get(i).getCollegeId()));
					label[8] = new Label(8, i + 1, al.get(i).getYear());
					for (int j = 0; j < 9; j ++) ws.addCell(label[j]);
				}
				wwb.write();
				wwb.close();
				outStream.close();
				request.getRequestDispatcher("result.jsp?para=3").forward(request, response);
			} catch (RowsExceededException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (WriteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}

}
