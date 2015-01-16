<%@ page contentType="text/html; charset=utf-8" language="java"%>
<html xmlns="http://www.w3.org/1999/xhtml"><head>
<%@ page import="java.util.*"%>
<%@ page import="java.io.*"%>
<%@ page import="com.actionForm.*"%>
<%@ page import="com.core.*" %>
<%@ page import="com.dao.*" %>
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
	AccountDAO accountDAO = new AccountDAO();
	AccountForm account = accountDAO.getAccountForm(id);
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
                <li><a href="home.jsp">我的状态</a></li>
                <li><a href="accountpage.html">设置</a></li>
                <li><a href="loginpage.html">退出</a></li>
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
                    <div id="mainBannerMenuTopWord1"><b>新鲜事</b>
                    </div>
                    <!-- 左侧mainBannerMenuTopWord1DIV 结束-->
                    <!-- 左侧mainBannerMenuTopWord2DIV 开始 -->
                    <div id="mainBannerMenuTopWord2"><img src="images/mainBannerMenuTopWord2.gif" title="" alt=""><a href="mainpage.jsp">发状态</a></div>
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
                     <div id="mainBannerContent2People">
                       <!-- 第一个人微博 mainBannerContent2PeopleImg DIV 开始 -->
                       <%--                      <%
	                     Iterator<StatusUpdate> statuses = accountDAO.getPersonalStatuses(id);
		                 while (statuses.hasNext()) {
		             		StatusUpdate status = statuses.next();
		             		out.println("<div id='mainBannerContent2People'>\n");
		             		out.println("<div id='mainBannerContent2PeopleImg'>\n");
		             		out.println("<img src='images/head1.jpg' width='54' height='54' alt='' title=''>\n");		             		
		             		out.println("</div>");
		             		out.println("<div id='mainBannerContent2PeopleWord'>");
		             		//
		             		out.println("<img src='images/biao.gif' alt='' width='17' height='13' align='absmiddle' id='pic1' title=''> <font class='f1'><a href='' class='a1'>"+account.getName()+"</a></font><font class='f2'> 吃了两包辣条，感觉好蛋疼 </font><br>");
		             		
		             		//
		             		out.println("</div>");
		             		System.out.println("text:"+status.getStatusText()+","+status.getDate().toLocaleString());
			             }
                     %> --%>
                        <div id="mainBannerContent2PeopleImg">
                          	<img src="images/head1.jpg" width="54" height="54" alt="" title="">
                        </div>
                        <!-- 第一个人微博 mainBannerContent2PeopleImg DIV 结束 -->
                        <!-- 第一个人微博 mainBannerContent2peopleWord DIV 开始  -->
                        <div id="mainBannerContent2PeopleWord">
<img src="images/biao.gif" alt="" width="17" height="13" align="absmiddle" id="pic1" title=""> <font class="f1"><a href="#" class="a1"><%out.println(account.getName()); %></a></font><font class="f2"> 吃了两包辣条，感觉好蛋疼 </font><br>
						<font class="f3">今天&nbsp;&nbsp;11:11<div id="textright"><a href="#" class="a2">查看</a>&nbsp;&nbsp;<a href="#" class="a2">回复(3</a>)&nbsp;&nbsp;<a href="#" class="a2">转发</a>&nbsp;&nbsp;<img src="images/star.gif" alt="" width="16" height="17" align="absmiddle" title=""></div></font><br>
                          <!--微博回复栏-->
                            <div id="mainBannerContent2PeopleWordBack">
                                 <table width="400" border="0" cellpadding="0" cellspacing="0">
                                          <tbody>
                                          <tr>
                                            <td><a href="#" class="a1">@呵呵哥</a>：你说的什么鬼<a href="#"><span style="color:#ccc">05月31 12:00</span>回复</a></td>
                                          </tr>
                                          <tr>
                                            <td><a href="#" class="a1">@呆呆</a>：你看我傻不傻<a href="#"><span style="color:#ccc">07月30 13:00</span>回复</a></td>
                                          </tr>
                                        </tbody></table>
                            </div><br>
						</div>
                        <!-- 第一个人微博 mainBannerContent2peopleWord DIV 结束  -->
                        <!--第二个人的微博-->
                    <div id="mainBannerContent2People">
                        <!-- 第二个人微博 mainBannerContent2PeopleImg DIV 开始 -->
                        <div id="mainBannerContent2PeopleImg">
                          	<img src="images/head1.jpg" width="54" height="54" alt="" title="">
                        </div>
                        <!-- 第二个人微博 mainBannerContent2PeopleImg DIV 结束 -->
                        <!-- 第二个人微博 mainBannerContent2PeopleWord DIV 开始 -->
                        <div id="mainBannerContent2PeopleWord">
                        <img src="images/biao.gif" alt="" width="17" height="13" align="absmiddle" id="pic" title=""> <font class="f1"><a href="#" class="a1">用户昵称</a></font><font class="f2">&nbsp;&nbsp;图片测试~~~
</font><br><br>
                        <img src="images/testimage.jpg" width="166" height="166" alt="" title=""><br><br>
						<font class="f3">07月30日&nbsp;&nbsp;12:56<div id="textright"><a href="#" class="a2">查看</a>&nbsp;&nbsp;<a href="#" class="a2">回复(4)</a>&nbsp;&nbsp;<a href="#" class="a2">转发</a>&nbsp;&nbsp;<img src="images/star.gif" alt="" width="16" height="17" align="absmiddle" title=""></div></font><br>
                        <!--微博回复栏-->
                            <div id="mainBannerContent2PeopleWordBack">
                                 <table width="400" border="0" cellpadding="0" cellspacing="0">
                                          <tbody>
                                          <tr>
                                            <td><a href="#" class="a1">@DarkDemon</a>：恩啊，唉~~~~<a href="#"><span style="color:#ccc">07月31 12:00</span>回复</a></td>
                                          </tr>
                                          <tr>
                                            <td><a href="#" class="a1">@呆呆</a>：我也无语啊！！<a href="#"><span style="color:#ccc">07月30 13:00</span>回复</a></td>
                                          </tr>
                                        </tbody></table>

                            </div><br><br>
						</div>
                        <!-- 第二个人微博 mainBannerContent2peopleWord DIV 结束  -->
					</div>
                     </div>
                     <!--个人微博-->
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
                    &nbsp;<font color="#005DC3"><b><a href="home.jsp" class="a1">用户昵称</a></b></font><br>
                    <% out.println(account.getHometown()); %>
                    </div>
                    <!-- 右侧mainRightPostionFirstLineWord1 DIV 结束 -->
                    <!-- 右侧mainRightPostionFirstLineWord2 DIV 开始 -->
                    <div id="mainRightPostionFirstLineWord2">
                    <ul id="ul1">
                    <li><a href="home.jsp" class="a1"><font class="style1">2</font><br><font class="style2">新鲜事</font></a></li>
                    <li><a href="friendpage.html" class="a1"><font class="style1">12</font><br><font class="style2">关注</font></a></li>
                    <li><a href="focusonyou.html" class="a1"><font class="style1">23</font><br><font class="style2">好友</font></a></li>
                    </ul>
                    </div>
                <!-- 右侧mainRightPostionFirstLineWord2 DIV 结束 -->                    
                </div>
                <!-- 右侧mainRightPostionFirstLine DIV 结束 -->
              </div>
                <!-- 右侧mainRightPostionSixthLine DIV 开始 -->
              <div id="mainRightPostionSixthLine">
                    <div id="mainRightPositionSixthLineContent">
                    <a href="#" onclick="" class="a1"><font class="style4">我关注的</font>
                    <img src="images/ThirdLineUpArrow.gif" alt="" width="12" height="14" align="right" title="" border="0"></a>
                    </div>
                    <ul>
                    <a href="#" class="a1"><li><img src="images/head2.jpg" width="48" height="48" border="0"><br><font class="style2">辣条哥哥</font></li></a>
                    <a href="#" class="a1"><li><img src="images/head3.jpg" width="48" height="48" border="0"><br><font class="style2">闷骚新人</font></li></a>
                    </ul>
                </div>
          </div>
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