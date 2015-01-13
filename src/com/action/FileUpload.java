package com.action;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.*;
import jxl.write.*;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.actionForm.ShowGradesForm;
import com.dao.AcdemicDAO;


public class FileUpload extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");	//���ñ���
		String action = request.getParameter("action");
		//��ô����ļ���Ŀ����
		DiskFileItemFactory factory = new DiskFileItemFactory();
		//��ȡ�ļ���Ҫ�ϴ�����·��
		String path = request.getSession().getServletContext().getRealPath("/upload/");
		System.out.println("path:" + path);
		//���û�����������õĻ����ϴ���� �ļ� ��ռ�� �ܶ��ڴ棬
		//������ʱ��ŵ� �洢�� , ����洢�ң����Ժ� ���մ洢�ļ� ��Ŀ¼��ͬ
		/**
		 * ԭ�� �����ȴ浽 ��ʱ�洢�ң�Ȼ��������д�� ��ӦĿ¼��Ӳ���ϣ� 
		 * ������˵ ���ϴ�һ���ļ�ʱ����ʵ���ϴ������ݣ���һ������ .tem ��ʽ�� 
		 * Ȼ���ٽ�������д�� ��ӦĿ¼��Ӳ����
		 */
		factory.setRepository(new File(path));
		//���� ����Ĵ�С�����ϴ��ļ������������û���ʱ��ֱ�ӷŵ� ��ʱ�洢��
		factory.setSizeThreshold(1024*1024) ;
		
		//��ˮƽ��API�ļ��ϴ�����
		ServletFileUpload upload = new ServletFileUpload(factory);
		
		
		try {
			//�����ϴ�����ļ�
			List<FileItem> list = (List<FileItem>)upload.parseRequest(request);
			for(FileItem item : list)
			{
				//��ȡ������������
				String name = item.getFieldName();
				System.out.println("name:" + name);
				//�����ȡ�� ����Ϣ����ͨ�� �ı� ��Ϣ
				if(item.isFormField())
				{
					//��ȡ�û�����������ַ��� ���������ͦ�ã���Ϊ���ύ�������� �ַ������͵�
					String value = item.getString() ;
					request.setAttribute(name, value);
				}
				//�Դ���ķ� �򵥵��ַ������д��� ������˵�����Ƶ� ͼƬ����Ӱ��Щ
				else
				{
					System.out.println("--2");
					//��ȡ·����
					String value = item.getName() ;
					//���������һ����б��
					int start = value.lastIndexOf("\\");
					//��ȡ �ϴ��ļ��� �ַ������֣���1�� ȥ����б�ܣ�
					String filename = value.substring(start+1);
					//System.out.println("filename is " + filename + ", action is " + action);
					item.write(new File(path, filename));
					System.out.println("filename is " + filename + ", action is " + action);
					InputStream instream = new FileInputStream(path + "/" + filename);
					System.out.println(action);
					if (action.equals("importStudentInfo")) {  // ����Ա�˵���ѧ��ѧ����Ϣ
						AcdemicDAO dao = new AcdemicDAO();
						
						Workbook readwb = Workbook.getWorkbook(instream);
						Sheet readsheet = readwb.getSheet(0);
						int rsColumns = readsheet.getColumns();
						int rsRows = readsheet.getRows();
						System.out.println("columns: " + rsColumns + ",rows:" + rsRows);
						String stu_num = "";
						String name_en = "";
						String birth_time = "";
						String gender = "";
						String college_num = "";
						String major_num = "";
						String sch_length = "";
						String id_num = "";
						String entr_time = "";
						String stu_status = "";
						String gradu_sch = "";
						String email = "";
						String telephone = "";
						String home_addr = "";
						String pos_code =  "";
						String citizenship = "";
						String nation = "";
						boolean error = false;
						for (int i = 1; i < rsRows ; i ++) {
							stu_num = readsheet.getCell(0, i).getContents();
							name_en = readsheet.getCell(1, i).getContents();
							birth_time = readsheet.getCell(2, i).getContents();
							if (birth_time.charAt(0) == '9') {
								birth_time = "19" + birth_time;
							}
							gender = readsheet.getCell(3, i).getContents();
							college_num = readsheet.getCell(4, i).getContents();
							major_num = readsheet.getCell(5, i).getContents();
							sch_length = readsheet.getCell(6, i).getContents();
							id_num = readsheet.getCell(7, i).getContents();
							entr_time = readsheet.getCell(8, i).getContents();
							stu_status = readsheet.getCell(9, i).getContents();
							gradu_sch = readsheet.getCell(10, i).getContents();
							email = readsheet.getCell(11, i).getContents();
							telephone = readsheet.getCell(12, i).getContents();
							home_addr = readsheet.getCell(13, i).getContents();
							pos_code = readsheet.getCell(14, i).getContents();
							citizenship = readsheet.getCell(15, i).getContents();
							nation = readsheet.getCell(16, i).getContents();
							String sql = "insert into tb_Student(stu_num,name_ch,birth_time,gender,college_num,major_num," +
									"sch_length,id_num,entr_time,stu_status,gradu_sch,email,telephone,home_addr,pos_code," +
									"citizenship,nation) values ('" + stu_num + "','" + name_en + "','" + birth_time + "','"+
									gender + "','" + college_num + "','" + major_num + "','" + sch_length + "','" + id_num +
									"','" + entr_time + "','" + stu_status + "','" + gradu_sch + "','" + email + "','" +
									telephone + "','" + home_addr + "','" + pos_code + "','" + citizenship + "','" +
									nation +
									"')";
							System.out.println("stu_num is " + stu_num);
							if (dao.isExistStudent(stu_num) == 0) {	//�����ظ�����
								if (dao.insertStudentInfo(sql) <= 0) {
									error = true;
									break;
								}
							}
						}	
						if (!error)
							request.getRequestDispatcher("result.jsp?para=1").forward(request, response);
						else 
							request.getRequestDispatcher("result.jsp?para=2").forward(request, response);
					}
					if (action.equals("importGradesInfo")) {  // ����Ա�˵���ѧ��ѧ����Ϣ
						AcdemicDAO dao = new AcdemicDAO();
						
						Workbook readwb = Workbook.getWorkbook(instream);
						Sheet readsheet = readwb.getSheet(0);
						int rsColumns = readsheet.getColumns();
						int rsRows = readsheet.getRows();
						System.out.println("columns: " + rsColumns + ",rows:" + rsRows);
						String stu_id = "";
						String course_id = "";
						String score = "";
						boolean error = false;
						for (int i = 1; i < rsRows ; i ++) {
							stu_id = readsheet.getCell(0, i).getContents();
							course_id = readsheet.getCell(1, i).getContents();
							score = readsheet.getCell(2, i).getContents();
							
							ShowGradesForm form = new ShowGradesForm();
							form.setStu_num(stu_id);
							form.setCourse_id(course_id);
							form.setScore(score);
							
							if (dao.gradeItemExist(stu_id, course_id) == false) {	//�����ظ�����
								if (dao.updateScore(form) <= 0) {
									error = true;
									break;
								}
							}
						}
						System.out.println( "error is " + error);
						if (!error)
							request.getRequestDispatcher("add_ok.jsp?para=1").forward(request, response);
						else 
							request.getRequestDispatcher("add_ok.jsp?para=2").forward(request, response);
					}
					if (action.equals("importCourseInfo")) {  // ����Ա�˵���γ���Ϣ
						AcdemicDAO daocourse = new AcdemicDAO();
						
						Workbook readcourse = Workbook.getWorkbook(instream);
						Sheet readsheetcourse = readcourse.getSheet(0);
						int cColumns = readsheetcourse.getColumns();
						int cRows = readsheetcourse.getRows();
						System.out.println("columns: " + cColumns + ",rows:" + cRows);
						String course_id = "";
						String course_name_chs = "";
						String course_name_egh = "";
						String credit = "";
						String week_hour = "";
						String semester = "";
						String teach_mode = "";
						String college_id = "";
						String course_year = "";
						boolean errorcourse = false;
						for (int i = 1; i < cRows ; i ++) {
							course_id = readsheetcourse.getCell(0, i).getContents();
							course_name_chs = readsheetcourse.getCell(1, i).getContents();
							course_name_egh = readsheetcourse.getCell(2, i).getContents();
							credit = readsheetcourse.getCell(3, i).getContents();
							week_hour = readsheetcourse.getCell(4, i).getContents();
							semester = readsheetcourse.getCell(5, i).getContents();
							teach_mode = readsheetcourse.getCell(6, i).getContents();
							college_id = readsheetcourse.getCell(7, i).getContents();
							course_year = readsheetcourse.getCell(8, i).getContents();
							
							String sql = "insert into tb_course_info(course_id,course_name_chs,course_name_egh,credit,week_hour,semester,teach_mode,college_id," + 
									"course_year) values (" + course_id + ",'" + course_name_chs + "','"+ course_name_egh + "'," +
									credit + "," + week_hour + ",'" + semester + "','" + teach_mode + "'," + college_id +
									",'" + course_year + "')";
							System.out.println(sql);
							if (daocourse.insertCourseInfo(sql) <= 0) {
								errorcourse = true;
								break;
							}
						}
						System.out.println(errorcourse);
						if (!errorcourse)
							request.getRequestDispatcher("result.jsp?para=1").forward(request, response);
						else 
							request.getRequestDispatcher("result.jsp?para=2").forward(request, response);
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
