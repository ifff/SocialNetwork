<%@ page contentType="text/html; charset=utf-8" language="java"%>
<html xmlns="http://www.w3.org/1999/xhtml"><head>
<%@ page import="java.util.*"%>
<%@ page import="java.io.*"%>
<%@ page import="com.actionForm.*"%>
<%@ page import="com.core.*" %>
<%@ page import="com.dao.*" %>
<%@ page import="org.neo4j.graphdb.*" %>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>同窗网-看你的朋友都在忙些什么鬼</title>
<link href="styles/MyWB.css" type="text/css" rel="stylesheet">
<link href="styles/global.css" type="text/css" rel="stylesheet">
<script language="javascript">
<!--

window.onload = function(){
}
-->
</script>
</head>

<%
	String id = (String)session.getAttribute("id");
	String name = (String)session.getAttribute("name");
	if (id == null || "".equals(id)){
		response.sendRedirect("index.jsp");
	} 
	String qid = request.getParameter("userid");
	AccountDAO accountDAO = new AccountDAO();
	AccountForm account = accountDAO.getAccountForm(qid);
	Collection<String> friendList = accountDAO.getFriendList(qid);
	Collection<StatusForm> statuses = accountDAO.getPersonalStatuses(qid);
	Iterator<StatusForm> it = statuses.iterator();
	int statusCount = statuses.size();

	Iterator<String> friendIt = friendList.iterator();
/* 	System.out.println(form.getFavoriteActivity()); */
%>
<body>
<!-- 总容器 container开 始-->
<div id="container">
    <!-- topDIV 开始 -->
    <div id="top">
      <!-- top部分的LogoDIV -->
    	<div id="topLogo">
        	<!-- topLogo部分的icoDIV -->
            <div id="topLogoIco"> <a href="mainpage.html"><img src="images/weblogo.jpg" width="72" height="72" alt=""></a>
            </div>
            <!-- topLogo部分的icoDIV结束 -->
      </div>
        <!-- top部分的LogoDIV结束 -->
        
        <!-- top部分的文字导航 -->
        <div id="topWordMenu">
        	<ul>
            	<li><a href="mainpage.jsp">首页</a></li>
                <li><a href=<%out.println("home.jsp?id="+id);%>>我的状态</a></li>
                <li><a href="profile.jsp">设置</a></li>
                <li><a href="logout.jsp">退出</a></li>
            </ul>
        </div>
        <!-- top部分的文字导航结束 -->
    </div>
    <!-- topDIv 结束-->
    <!-- 内容总容器 mainDIV 开 始-->
<div id="main">
		<!-- 左侧mainBannerDIV 开始 -->
      	<div id="mainBanner">
            <!-- 左侧mainBannerTopDIV 开始 -->
            <div id="mainBannerTop">
                <!-- 左侧mainBannerTopImgDIV 开始 -->
                <div id="mainBannerTopImg">
                  <img src="images/head1.jpg" width="80" height="80" alt="" title="">
                </div>
                <% out.println("<div id='mainBannerTopWord'><font color='#330000' size='5'><b>"+account.getName()+"</b></font><br>"); %>
                <% out.println(account.getGender()+"<br>"+account.getBirthday()); %>
                </div>
            	<!-- 左侧mainBannerTopWordDIV 结束 -->
            </div>
            <!-- 左侧mainBannerTopDIV 结束 -->
            <!-- 左侧mainBannerMenuDIV 开始 -->
            <div id="mainBannerMenu">
                <!-- 左侧mainBannerMenuTopDIV 开始 -->
                <div id="mainBannerMenuTop">
                    <!-- 左侧mainBannerMenuTopWord1DIV 开始 -->
                    <div id="mainBannerMenuTopWord1"><b>好友</b>
                    </div>
                    <!-- 左侧mainBannerMenuTopWord1DIV 结束-->
                    <!-- 左侧mainBannerMenuTopWord2DIV 开始 -->
                    <%
                    if (qid.equals(id)) {
                    	out.println("<div id='mainBannerMenuTopWord2'><img src='images/mainBannerMenuTopWord2.gif' title='' alt=''><a href='mainpage.jsp'>发状态</a></div>");
                    }
                    %>
                    <!-- 左侧mainBannerMenuTopWord2DIV 结束 -->
                </div>
            	<!-- 左侧mainBannerMenuTopDIV 结束-->
            </div>
            <!-- 左侧mainBannerMenu DIV 结束-->
            <!--自己发微博的地方-->
            <div id="mainBannerContent"> 
                <!--给微博定位-->
               <div id="mainBannerContent1">
                    <!--给微博定位-->
                  <div id="mainBannerContent2">
                     <!--个人微博-->
 
                       <!-- 第一个人微博 mainBannerContent2PeopleImg DIV 开始 -->
                       <%

                       		while (friendIt.hasNext()) {
                       			String friend = (String)friendIt.next();
								Random r = new Random();
    		             		int a = r.nextInt(6) + 1;
								String friendInfo[] = friend.split("\t");
                       			out.println("<div id='mainBannerContent2People'>\n");
    		             		out.println("<div id='mainBannerContent2PeopleImg'>\n");
    		             		out.println("<img src='images/head"+String.valueOf(a)+".jpg' width='54' height='54' alt='' title=''>\n");		             		
    		             		out.println("</div>");
    		             		out.println("<div id='mainBannerContent2PeopleWord'>");
    		             		//
    		             		ChStr chStr=new ChStr();
//				        String id = chStr.filterStr(accountForm.getId());
    		             		out.println("<font class='f1'><a href='home.jsp?id="+friendInfo[0]+"' class='a1'>"+chStr.filterStr(friendInfo[1])+"&nbsp&nbsp&nbsp</a></font><font class='f2'>"+friendInfo[4]+" </font><br>");
    		             		out.println("<font class='f2'>"+friendInfo[2]+" </font><br>");
								out.println("<font class='f2'>"+friendInfo[3]+" </font><br>");
    		             		out.println("<br><font class='f3'>"+friendInfo[5]+"<div id='textright'></div></font><br>  ");
    		             		//
    		             		out.println("</div><br>");
    		             		out.println("</div>");
                       		}

                     %>

                  </div>  
                  <!--给微博定位结束-->
               </div>
                 <!--给微博定位结束-->
            </div>
             <!--自己发微博的地方结束-->
        </div>
        <!-- 左侧mainBannerDIV 结束 -->                       
        <!-- 右侧mainRight DIV开始 -->
        <div id="mainRight">
            <div id="mainRight1">
               <!-- 右侧mainRightPostionFirstLine DIV 开始 -->
                <div id="mainRightPostionFirstLine">
                    <!-- 右侧mainRightPostionFirstLineIcon DIV 开始 -->
                    <div id="mainRightPostionFirstLineIcon">
                    <a href="#"><img src="images/head1.jpg" alt="" width="48" height="48" align="absmiddle" title="" border="0"></a>
                    </div>
                    <!-- 右侧mainRightPostionFirstLineIcon DIV 结束 -->
                    <!-- 右侧mainRightPostionFirstLineWord1 DIV 开始 -->
                    <div id="mainRightPostionFirstLineWord1">                 
                    &nbsp;<font color="#005DC3"><b><a href=<% out.println("home.jsp?id="+id);%> class="a1"><% out.println(account.getName()); %></a></b></font><br>
                    <% out.println(account.getHometown()); %>
                    </div>
                    <!-- 右侧mainRightPostionFirstLineWord1 DIV 结束 -->
                    <!-- 右侧mainRightPostionFirstLineWord2 DIV 开始 -->
                    <div id="mainRightPostionFirstLineWord2">
                    <ul id="ul1">
                    <li><font class="style1"><%out.println(statusCount); %></font><br><font class="style2">新鲜事</font></li>
                    <li><a href=<% out.println("friend.jsp?userid="+qid) ;%> class="a1"><font class="style1"><%out.println(friendList.size()); %></font><br><font class="style2">好友</font></a></li>
                    </ul>
                    </div>
                <!-- 右侧mainRightPostionFirstLineWord2 DIV 结束 -->                    
                </div>
                <!-- 右侧mainRightPostionFirstLine DIV 结束 -->
              </div>
                <!-- 右侧mainRightPostionSixthLine DIV 开始 -->

        </div>         
  </div>
  <!-- 内容总容器 mainDIV 结束-->
  <!-- footer部分 -->
  <div id="footer">
      <!-- footer网站版权信息 -->
      <div id="footerCopy">
       	  同窗网版权所有
      </div>
      <!-- footer网站版权信息结束 -->
  </div>
  <!-- footer部分结束 --> 
</div>
<!--总容器 container结束-->


</body></html>