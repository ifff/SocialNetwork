<%@ page contentType="text/html; charset=gbk" language="java"%>
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
	String qid = id;
	AccountDAO accountDAO = new AccountDAO();
	AccountForm account = accountDAO.getAccountForm(qid);
	Collection<String> friendList = accountDAO.getFriendList(qid);
	Collection<StatusForm> statuses = accountDAO.getPersonalStatuses(qid);
	Iterator<StatusForm> it = statuses.iterator();
	int statusCount = statuses.size();
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
            <!-- banner部分DIV -->
            <div id="mainBannerMenu">
                <!-- 左侧mainBannerMenuTopDIV 开始 -->
                <div id="mainBannerMenuTop">
                    <!-- 左侧mainBannerMenuTopWord1DIV 开始 -->
                    <div id="mainBannerMenuTopWord1"><b>设置</b>
                    </div></div></div>
    <div id="banner">
        <!-- banner部分的leftDIV -->
        <div class="left" id="left">
        <form id="form1" action="account?action=updateProfile"
		method="post">
            <table width="564" border="0" cellpadding="0" cellspacing="0" class="left">
                <!-- 昵称 -->
                <tbody><tr>
                    <td width="120" height="50" align="right"><strong>你的昵称</strong></td>
                    <td width="20" height="60">&nbsp;</td>
                    <td width="425" height="60"><label>
                         <input name="name" type="text" class="n1" id="name" value=<%=account.getName() %>>
<br>
                              您的昵称将显示在您的主页中</label></td>
                </tr>
                
                
                <!-- 生日 -->
                <tr>
                    <td width="120" height="49" align="right"><strong>生日</strong></td>
                    <td width="20" height="49">&nbsp;</td>
                    <td width="425" height="49"><label>
                         <input name="birthday" type="text" class="n3" id="birthday" value=<%=account.getBirthday() %>>
                             </label></td>
                </tr>
                <!-- 地址 -->
                <tr>
                    <td width="120" height="52" align="right"><strong>地址</strong></td>
                    <td width="20" height="52">&nbsp;</td>
                    <td width="425" height="52">
                    <label>
                      <input name="hometown" type="text" class="n3" id="hometown" value=<%=account.getHometown() %>>
                    </label>
            <label>
              <br>
              你在哪？让你周围的更多的朋友找到你</label></td>
                </tr>
                <!-- 喜欢运动 -->
                <tr>
                    <td width="120" height="68" align="right"><strong>喜欢运动</strong></td>
                    <td width="20" height="68">&nbsp;</td>
                    <td width="425" height="68"><label>
                        <input name="activity" type="text" class="n1" id="activity" value=<%=account.getFavoriteActivity() %>>
                        <br>
                         说出你喜欢运动</label></td>
                </tr>
                <!-- 个性签名 -->
                <tr>
                    <td width="120" height="180" align="right"><strong>喜欢的食物</strong></td>
                    <td width="20" height="180">&nbsp;</td>
                    <td width="425" height="180">
                       <div>
                            <label>
                                  <input name="food" type="text" class="n1" id="food" value=<%=account.getFavoriteFood() %>>
                            </label>
                       </div></td>
                </tr>
                <!-- 保存按钮 -->
                <tr>
                    <td width="120" height="20" align="right">&nbsp;</td>
                    <td width="20" height="44">&nbsp;</td>
                    <td width="425" height="44"><label>
                         <input name="button" type="submit" class="btn" id="button" value="保存">
                            </label></td>
                </tr>
            </tbody></table></form>
        </div>
        <!-- banner_left部分DIV结束 -->
        <!-- banner_right部分DIV -->
        <div class="right" id="right">
           <p>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp在这里
              ，你可以设置你账号的基本信息，隐私信息等</p>
        </div>
        <!-- banner_right部分DIV结束 -->
    </div>
    <!-- banner部分DIV结束 -->
<%--             <div id="mainBannerMenu">
                <!-- 左侧mainBannerMenuTopDIV 开始 -->
                <div id="mainBannerMenuTop">
                    <!-- 左侧mainBannerMenuTopWord1DIV 开始 -->
                   
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
            </div> --%>
            <!-- 左侧mainBannerMenu DIV 结束-->
            <!--自己发微博的地方-->
            <div id="mainBannerContent"> 
                <!--给微博定位-->
               <div id="mainBannerContent1">
                    <!--给微博定位-->
                  <div id="mainBannerContent2">
                     <!--个人微博-->
 
                       <!-- 第一个人微博 mainBannerContent2PeopleImg DIV 开始 -->

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
                    &nbsp;<font color="#005DC3"><b><a href=<% out.println("home.jsp?id="+qid); %> class="a1"><% out.println(account.getName()); %></a></b></font><br>
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