<%@ page contentType="text/html; charset=utf-8" language="java"%>
<html xmlns="http://www.w3.org/1999/xhtml"><head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>同窗网-看你朋友都在忙什么鬼</title>
<link href="styles/CustomerIndex.css" type="text/css" rel="stylesheet">
<link href="styles/global.css" type="text/css" rel="stylesheet">
<script language="javascript" src="script/CustomerIndex.js"></script>
<script language="javascript" src="script/trim.js"></script>
<script language="javascript" src="script/jquery-1.6.2.min.js"></script>
<%@ page import="java.util.*"%>
<%@ page import="java.io.*"%>
<%@ page import="com.actionForm.*"%>
<%@ page import="com.core.*" %>
<%@ page import="com.dao.*" %>
<%@ page import="org.neo4j.graphdb.*" %>
</head>
<script language="javascript">	
	function isEmpty(str) {
		for (var i = 0; i < str.length; i ++) {
			if (str.charAt(i)!=="")
				return false;
		}
		return true;
	}

	function check(form) {
		var fileName = form.file1.value;
		if (form.content.value=="" || form.content.value == null) {
			alert("状态不能为空！")
			form.textfield1.focus();
			return false;
		}
		if (isEmpty(fileName)) {
			alert("请选择文件！");
			form.file1.focus();
			return false;
		}
		if (fileName.lastIndexOf(".") != -1) {
			var fileType = (fileName.substring(fileName.lastIndexOf(".")+1,fileName.length)).toLowerCase();
       		var suppotFile = new Array();
    		suppotFile[0] = "png";
    		supportFile[1] = "jpg";
   			for(var i =0;i<suppotFile.length;i++){
      			if(suppotFile[i]==fileType){
    			 	return true;
    			}
				else{
   					continue;
    			}
  			}
  			alert("不支持文件类型"+fileType);
  			return false;
 		}
		else{
  			alert("文件只支持jpg,png格式");
  			return false;
 		}
	}
</script>
<%
	String id = (String)session.getAttribute("id");
	String name = (String)session.getAttribute("name");
	if (id == null || "".equals(id)){
		response.sendRedirect("index.jsp");
	} 
	AccountDAO accountDAO = new AccountDAO();
	AccountForm account = accountDAO.getAccountForm(id);
	Collection<String> friendList = accountDAO.getFriendList(id);
    Collection<StatusForm> statuses = accountDAO.getPersonalStatuses(id);
	Iterator<StatusForm> it = statuses.iterator();
	int statusCount = statuses.size();
	
	Collection<StatusForm> friendStatus = accountDAO.getFriendStatus(id);
	Iterator<StatusForm> fit = friendStatus.iterator();
/* 	System.out.println(form.getFavoriteActivity()); */
%>
<body style="filter:alpha(opacity=100)" id="totop">
<!-- 总容器 container开 始-->
<div>
<div id="container">
	    <!-- topDIV 开始 -->
  <div id="top">
    <!-- top部分的LogoDIV -->
    	<div id="topLogo">
        	<!-- topLogo部分的icoDIV -->
            <div id="topLogoIco"> <a href="CustomerIndex.html"><img src="images/weblogo.jpg" width="72" height="72" alt=""></a>
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
    <div id="mainBanner" style="height: 1372px;">
        	<!-- mainBannerTop DIV 开始 -->
      <div id="mainBannerTop">
            <!--id=mainBannerTopIssue 发布框-->
        <div id="mainBannerTopIssue">
                    <!--id=mainBannerTopIssuePoint 提示-->
                    <div id="mainBannerTopIssuePoint">正在发生的事情
                    </div>
                    <div style="float:right;">您还可以输入<font id="counter1">140</font>字！
                    </div>
                    <form id="form1" action="fileupload?action=postStatus"
		method="post" enctype="multipart/form-data">
                      <div id="mainBannerTopIssueForm">
                            <!--id="mainBannerTopIssueFrame-->
                            <div id="mainBannerTopIssueFrame">
                              <textarea id="content" name="content" cols="70" rows="4"></textarea>
        </div>
                            <!--id="mainBannerTopIssueInsert 插入链接-->
                        <div id="mainBannerTopIssueInsert">
                            <!--4个小div-->
                        </div>
                          <!--确认发布-->
                        <div id="mainBannerTopIssueSure">
                        	<div align="left">
                        	<tr>
								<td>插入图片：</td>
								<td><label for="file1"></label> <input type="file" name="file1"
									id="file1" /></td>
								<td colspan='10' align='right'><input type="submit" name="submit" id="submit" value="发布" style="background-color:#3295E6; border:none" onclick="return check(form1)"></td>
							</tr>
							</div>
                          <div id="mainBannerTopIssueSure2"> <a href="file" class="a1">
                            <div id="mainBannerTopIssueInsert1"></div>
                            <div>

							</div>

<!--                             </a> -->
<!--                             <a href="javascript:biaoQingXianShi()" class="a1">
                              <div id="mainBannerTopIssueInsert5"></div>
                              <div id="mainBannerTopIssueInsert6">插入表情</div>
                            </a> -->
                            
                          </div>
                        </div>
                        </div>
                    </form> 
          </div>
          <!-- 表情DIV -->
          <div id="biaoqing">
          	<table width="200" border="1" cellspacing="0" cellpadding="0">
              <tbody><tr>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
              </tr>
            </tbody></table>

          </div>
        </div> 
            
            <!--id="mainBannerTitle 首页-->
            <div id="mainBannerTitle">
                <!--id="mainBannerTitleWord"-->
                <div id="mainBannerTitleWord"><b>新鲜事</b>
                </div>
                <!--首页提示与隐藏-->
            </div> 
            <!--不同人的内容-->
            <div id="mainBannerContent">
          
        <%

                       		while (fit.hasNext()) {
                       			StatusForm status = (StatusForm)fit.next();
                       			ChStr chStr=new ChStr();
                       			out.println("<div class='stateShow' >\n");
    		             		out.println("<div class='stateShowWord'>\n");
    		             		out.println(" <table width='450' border='0' cellpadding='0' cellspacing='0' class='stateTable'>");
    		             		out.println("<tbody><tr>");
    		             		Random r = new Random();
    		             		int a = r.nextInt(6) + 1;
    		             		out.println("<td width='70' align='center' valign='top'><a href='"+"home.jsp?id="+status.getUserId()+"'><img src=images/head"+String.valueOf(a)+".jpg"+" alt='' width='48' height='48'></a></td>");
    		             		out.println("<td width='380'><a href='home.jsp?id="+status.getUserId()+"'>"+status.getUserName()+"</a>");
    		             		out.println(chStr.filterStr(status.getText()));
    		             		out.println("</tr></tbody></table></div>");
    		             		out.println("<div class='stateImgShow' ><img src='"+status.getPicturePath()+"'>");
    		             		out.println("</div>");
    		             		out.println("<div class='stateShowtime'> 07月31日 08:01 </div></div>");
    		             		
    		
/*     		             		ChStr chStr=new ChStr();
//				        String id = chStr.filterStr(accountForm.getId());
    		             		out.println("<font class='f1'><a href='' class='a1'>"+account.getName()+"                         "+"&nbsp&nbsp&nbsp</a></font><font class='f2'>"+chStr.filterStr(status.getText())+" </font><br>");
    		             		out.println("<img src="+status.getPicturePath()+" width='166' height='166' alt='' title='' align='absmiddle'>");
    		             		out.println("<br><font class='f3'>"+status.getDate()+"<div id='textright'></div></font><br>  ");
    		             		//
    		             		out.println("</div><br>");
    		             		out.println("</div>"); */
                       		}

                     %>
            </div>
      </div>
      <!-- 左侧mainBanner DIV 结束-->
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
                    &nbsp;<font color="#005DC3"><b><a href=<% out.println("home.jsp?id="+id); %> class="a1"><% out.println(account.getName()); %></a></b></font><br>
                    <% out.println(account.getHometown()); %>
                    </div>
                    <!-- 右侧mainRightPostionFirstLineWord1 DIV 结束 -->
                    <!-- 右侧mainRightPostionFirstLineWord2 DIV 开始 -->
                    <br>
                    <div id="mainRightPostionFirstLineWord2">
                    <ul id="ul1">
                    <li><font class="style1"><%out.println(statusCount); %></font><br><font class="style2">新鲜事</font></li>
                    <li><a href=<% out.println("friend.jsp?userid="+id) ;%> class="a1"><font class="style1"><%out.println(friendList.size()); %></font><br><font class="style2">好友</font></a></li>
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
    	<!-- footer网站链接部分结束 -->
        <!-- footer网站版权信息 -->
  <div id="footerCopy">
        	同窗网-版权所有
        </div>
        <!-- footer网站版权信息结束 -->
    </div>
    <!-- footer部分结束 -->
    
</div>
<!--总容器 container结束-->
 <p id="backtop"><a id="backtop1" href="#totop"><span></span>回到顶部</a></p>


</div></body></html>