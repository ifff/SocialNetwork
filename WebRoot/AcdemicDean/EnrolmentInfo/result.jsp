<%@ page contentType="text/html; charset=gb2312" language="java"
	import="java.sql.*" errorPage=""%>
<html>
<head>
<title>����ɹ�</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
</head>

<body>
	<%
		int para = Integer.valueOf(request.getParameter("para"));
		System.out.println("para is " + para);
		switch(para) {
			case 1:
	 %>
	<script language="javascript">
			alert("ѧ����Ϣ����ɹ�!");
			window.location.href="query.jsp";
			</script>
	<%
			break;
			case 2:
	%>
	<script language="javascript">
			alert("ѧ����Ϣ����ʧ��!");
			window.location.href= "exportStudent.xls";
			</script>
	<%
			break;
			case 3:
	%>
	<script language="javascript">
			alert("ѧ����Ϣ�����ɹ�!");
			window.open("http://localhost:8080/StudentManagementSystem/download/exportStudent.xls");
			window.location.href="query.jsp";
			</script>
	<% 
			break;
			}
	 %>
</body>
</html>