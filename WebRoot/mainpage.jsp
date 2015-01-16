<%@ page contentType="text/html; charset=utf-8" language="java"%>
<html xmlns="http://www.w3.org/1999/xhtml"><head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>同窗网-看你朋友都在忙什么鬼</title>
<link href="styles/CustomerIndex.css" type="text/css" rel="stylesheet">
<link href="styles/global.css" type="text/css" rel="stylesheet">
<script language="javascript" src="script/CustomerIndex.js"></script>
<script language="javascript" src="script/trim.js"></script>
<script language="javascript" src="script/jquery-1.6.2.min.js"></script>
</head>

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
                    <form action="" method="post" id="">
                      <div id="mainBannerTopIssueForm">
                            <!--id="mainBannerTopIssueFrame-->
                            <div id="mainBannerTopIssueFrame">
                              <textarea name="textfield1" rows="4" class="Size" id="textfield2" style="overflow:hidden;border:1px #0CF solid;" onkeyup="calNum(this,counter1,0)"></textarea>
        </div>
                            <!--id="mainBannerTopIssueInsert 插入链接-->
                        <div id="mainBannerTopIssueInsert">
                            <!--4个小div-->
                        </div>
                          <!--确认发布-->
                        <div id="mainBannerTopIssueSure">
                          <div id="mainBannerTopIssueSure2"> <a href="file" class="a1">
                            <div id="mainBannerTopIssueInsert1"></div>
                            <div id="mainBannerTopIssueInsert2">插入图片 </div>
                            </a>
                            <a href="javascript:biaoQingXianShi()" class="a1">
                              <div id="mainBannerTopIssueInsert5"></div>
                              <div id="mainBannerTopIssueInsert6">插入表情</div>
                            </a>
                            <input type="button" name="button1" id="button1" value="发布" style="background-color:#3295E6; border:none" onclick="submitState()">
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
                <div id="mainBannerTitleWord"><b>好友新鲜事</b>
                </div>
                <!--首页提示与隐藏-->
            </div> 
            <!--不同人的内容-->
            <div id="mainBannerContent">
            	<!--个人展示-->
            	<div class="stateShow" >
                  <div class="stateShowWord">
                    <table width="450" border="0" cellpadding="0" cellspacing="0" class="stateTable">
                      <tbody><tr>
                        <td width="70" align="center" valign="top"><a href="#"><img src="images/head4.jpg" alt="" width="48" height="48"></a></td>
                        <td width="380"><a href="#">乡下妹</a>
                          【视频：超模的儿子每月需要30万元生活费？】- 超模琳达·伊万格丽斯塔为四岁的儿子向他的父亲、法国亿万富翁弗朗索瓦-亨利·皮诺特索要每月4.6万美元的抚养费。在视频中，《华尔街日报》的Robert Frank和Kelsey Hubbard讨论了富豪家庭在孩子身上的花销。 </td>
                      </tr>
                    </tbody></table>
                  </div>
                   <div class="stateImgShow" ><img src="images/testimage2.jpg">
                   </div>            
                  <div class="stateShowtime"> 07月31日 08:02 </div>
                  <div class="stateOp"><a class="opState" onclick="reXianShi(this)">回复</a><a class="opState">转发</a><a class="opState" onclick="delState(this)">删除</a></div>
                   <div id="recieve">
                        <div id="ff" style="float:left;"><font style="font-size:16px; color:#FDFDFD">&nbsp;&nbsp;&nbsp;&nbsp;回&nbsp;&nbsp;复</font></div>
                        <div id="left" style="float:right; margin-top:10px; color:#FFF; margin-right:10px;">您还有可以输入<font id="counter2" color="#ffffff">140</font>字！&nbsp;&nbsp;&nbsp;<img src="images/hongcha1.gif" alt="" width="14" height="13" align="absmiddle" title="" onclick="windowClose()"></div><br>
                        <div id="wt">
                        <form action="" method="get">
                          <textarea name="ta" cols="" rows="" id="ta1" onkeyup="calNum(this,counter2,1)" style="overflow:hidden;border:1px #0CF solid;">                            </textarea>
                          <br>
                            <div style="float:right; margin-right:25px; margin-top:7px; text-align:right;">
                                <!--&nbsp;&nbsp;<input type="checkbox" name="引用原微博" value="复选框"/>&nbsp;&nbsp;引用原微博-->
                                <input name="" type="button" value=" 回 复 " id="sub1" onclick="submitRstate()">
                            </div>
                        </form>           
                        </div> 
                        <!-- 回复DIV 结束 -->
                  </div>
                </div>
                 <!--个人展示结束-->
                  <!--个人展示-->
                <div class="stateShow" >
                  <div class="stateShowWord">
                    <table width="450" border="0" cellpadding="0" cellspacing="0" class="stateTable">
                      <tbody><tr>
                        <td width="70" align="center" valign="top"><a href="#"><img src="images/head1.jpg" width="54" height="54" alt="" title=""></a></td>
                        <td width="380"><a href="#">开心段子微博</a>
                          【真相】 记得小时候书本上总说中国用世界7%的耕地养活了22%的人口，可是它没有告诉我们，这22%的人口养了世界60%的公务员；这22%的人口的教育经费只占世界的3%；这22%的人口的财富97%集中在其中1%的人手里；这22%的人口中的90%吃着全世界最毒的食物，缴纳着最高的税，干着最脏最累的活??? </td>
                      </tr>
                    </tbody></table>
                  </div>
                   <div class="stateImgShow" ></div>            
                  <div class="stateShowtime"> 07月31日 08:02 </div>
                  <div class="stateOp"><a onclick="reXianShi(this)" class="opState">回复</a><a class="opState">转发</a><a class="opState" onclick="delState(this)">删除</a></div>
                   <div id="recieve">
                        <div id="ff" style="float:left;"><font style="font-size:16px; color:#FDFDFD">&nbsp;&nbsp;&nbsp;&nbsp;回&nbsp;&nbsp;复</font></div>
                        <div id="left" style="float:right; margin-top:10px; color:#FFF; margin-right:10px;">您还有可以输入<font id="counter2" color="#ffffff">140</font>字！&nbsp;&nbsp;&nbsp;<img src="images/hongcha1.gif" alt="" width="14" height="13" align="absmiddle" title="" onclick="windowClose()"></div><br>
                        <div id="wt">
                        <form action="" method="get">
                          <textarea name="ta" cols="" rows="" id="ta1" onkeyup="calNum(this,counter2,1)" style="overflow:hidden;border:1px #0CF solid;">                            </textarea>
                          <br>
                            <div style="float:right; margin-right:25px; margin-top:7px; text-align:right;">
                                <!--&nbsp;&nbsp;<input type="checkbox" name="引用原微博" value="复选框"/>&nbsp;&nbsp;引用原微博-->
                                <input name="" type="button" value=" 回 复 " id="sub1" onclick="submitRstate()">
                            </div>
                        </form>           
                        </div> 
                        <!-- 回复DIV 结束 -->
                  </div>
                </div>
                 <!--个人展示结束-->
                 <!--个人展示-->
                <div class="stateShow" >
                  <div class="stateShowWord">
                    <table width="450" border="0" cellpadding="0" cellspacing="0" class="stateTable">
                      <tbody><tr>
                        <td width="70" align="center" valign="top"><a href="#"><img src="images/head5.jpg" width="54" height="54" alt="" title=""></a></td>
                        <td width="380"><a href="#">不明真真相</a>
                          你我都是这“奇迹”国中的一员花销。 </td>
                      </tr>
                    </tbody></table>
                  </div>
                   <div class="stateImgShow">
                   	 <img src="images/testimage.jpg" width="166" height="166" alt="" title=""></div>            
                  <div class="stateShowtime"> 07月31日 08:02 </div>
                  <div class="stateOp"><a onclick="reXianShi(this)" class="opState">回复</a><a class="opState">转发</a><a class="opState" onclick="delState(this)">删除</a></div>
                    <div id="recieve">
                        <div id="ff" style="float:left;"><font style="font-size:16px; color:#FDFDFD">&nbsp;&nbsp;&nbsp;&nbsp;回&nbsp;&nbsp;复</font></div>
                        <div id="left" style="float:right; margin-top:10px; color:#FFF; margin-right:10px;">您还有可以输入<font id="counter2" color="#ffffff">140</font>字！&nbsp;&nbsp;&nbsp;<img src="images/hongcha1.gif" alt="" width="14" height="13" align="absmiddle" title="" onclick="windowClose()"></div><br>
                        <div id="wt">
                        <form action="" method="get">
                          <textarea name="ta" cols="" rows="" id="ta1" onkeyup="calNum(this,counter2,1)" style="overflow:hidden;border:1px #0CF solid;">                            </textarea>
                          <br>
                            <div style="float:right; margin-right:25px; margin-top:7px; text-align:right;">
                                <!--&nbsp;&nbsp;<input type="checkbox" name="引用原微博" value="复选框"/>&nbsp;&nbsp;引用原微博-->
                                <input name="" type="button" value=" 回 复 " id="sub1" onclick="submitRstate()">
                            </div>
                        </form>           
                        </div> 
                        <!-- 回复DIV 结束 -->
                  </div>
                </div>
                 <!--个人展示结束-->
                  <!--个人展示-->
                <div class="stateShow" >
                    <table width="450" border="0" cellpadding="0" cellspacing="0" class="stateTable">
                      <tbody><tr>
                        <td width="70" align="center" valign="top"><a href="#"><img src="images/head6.jpg" alt="" width="48" height="48"></a></td>
                        <td width="380"><a href="#">家人杂志</a>
                          分享图片:二十多年前,林青霞和邓丽君在法国戛纳.当时最红的两位女子定格在一起,她们陪伴多少人走过那段流金岁月? </td>
                      </tr>
                    </tbody></table>
                  </div>
                   <div class="stateImgShow">
                   	 <img src="images/testimage3.jpg"></div>            
                  <div class="stateShowtime"> 07月31日 08:02 </div>
                  <div class="stateOp"><a onclick="reXianShi(this)" class="opState">回复</a><a class="opState">转发</a><a class="opState" onclick="delState(this)">删除</a></div>
                    <div id="recieve">
                        <div id="ff" style="float:left;"><font style="font-size:16px; color:#FDFDFD">&nbsp;&nbsp;&nbsp;&nbsp;回&nbsp;&nbsp;复</font></div>
                        <div id="left" style="float:right; margin-top:10px; color:#FFF; margin-right:10px;">您还有可以输入<font id="counter2" color="#ffffff">140</font>字！&nbsp;&nbsp;&nbsp;<img src="images/hongcha1.gif" alt="" width="14" height="13" align="absmiddle" title="" onclick="windowClose()"></div><br>
                        <div id="wt">
                        <form action="" method="get">
                          <textarea name="ta" cols="" rows="" id="ta1" onkeyup="calNum(this,counter2,1)" style="overflow:hidden;border:1px #0CF solid;">                            </textarea>
                          <br>
                            <div style="float:right; margin-right:25px; margin-top:7px; text-align:right;">
                                <!--&nbsp;&nbsp;<input type="checkbox" name="引用原微博" value="复选框"/>&nbsp;&nbsp;引用原微博-->
                                <input name="" type="button" value=" 回 复 " id="sub1" onclick="submitRstate()">
                            </div>
                        </form>           
                        </div> 
                        <!-- 回复DIV 结束 -->
                  </div>
                </div>
                 <!--个人展示结束-->            
            </div>
      </div>
      <!-- 左侧mainBanner DIV 结束-->
      <!-- 右侧mainRight DIV开始 -->
  <div id="mainRight" style="height: 1372px;">
          <table width="200" border="0" cellpadding="0" cellspacing="0" bgcolor="#E9F4FA">
 				<tbody><tr>
                    <td bgcolor="#E9F4FA">
                    	<!-- 右侧mainRightPostionFirstLine DIV 开始 -->
                    	<div id="mainRightPostionFirstLine">
                            <!-- 右侧mainRightPostionFirstLineIcon DIV 开始 -->
                            <div style="height:58px;">
                            <div id="mainRightPostionFirstLineIcon">
                            	<a href="mypage.html"><img src="images/head1.jpg" alt="" width="48" height="48" align="absmiddle" title="" border="0"></a>
                            </div>
                            <!-- 右侧mainRightPostionFirstLineIcon DIV 结束 -->
                            <!-- 右侧mainRightPostionFirstLineWord1 DIV 开始 -->
                            <div id="mainRightPostionFirstLineWord1">                 
                            &nbsp;<font color="#005DC3"><b><a href="mypage.html" class="a1">用户昵称</a></b></font><br>
                            &nbsp;城市&nbsp;&nbsp;所在区
                            </div>
                            </div>
                            <!-- 右侧mainRightPostionFirstLineWord1 DIV 结束 -->
                        	<!-- 右侧mainRightPostionFirstLineWord2 DIV 开始 -->
                          <div id="mainRightPostionFirstLineWord2">
                                <ul>
                                	<a href="mypage.html" class="a1"><li><font class="style1">2</font><br><font class="style2">新鲜事</font></li></a>
   								    <a href="friend.html" class="a1"><li><font class="style1">12</font><br><font class="style2">关注</font></li></a>
   								    <a href="focusonyou.html" class="a1"><li><font class="style1">23</font><br><font class="style2">好友</font></li></a>
                                </ul>
                           </div>
                           <!-- 右侧mainRightPostionFirstLineWord2 DIV 结束 -->                    
                        </div>
                        <!-- 右侧mainRightPostionFirstLine DIV 结束 -->
                    </td>
                </tr>
                <tr>
                    <td bgcolor="#E9F4FA">                    
                        <!-- 右侧mainRightPostionSecondLine DIV 开始 -->
                        <div id="mainRightPostionSecondLine">
                            <!-- 右侧mainRightPositionSecondLineContent DIV 开始 -->
                            <!-- 右侧mainRightPositionSecondLineContent DIV 结束 -->
                        </div>
                        <!-- 右侧mainRightPostionSecondLine DIV 结束 -->
                    </td>
                </tr>
                <tr>
                  <td>  
                        <!-- 右侧mainRightPostionThirdLine DIV 开始 -->
                        <div id="mainRightPostionThirdLine">
                        </div>
                        <!-- 右侧mainRightPostionThirdLine DIV 结束 -->
                  </td>
                </tr>
                <tr>
                    <td>
                        <!-- 右侧mainRightPostionFourthLine DIV 开始 -->
                        <div id="mainRightPostionFouthLine">
                        	<!-- 右侧mainRightPositionFourthLineContent DIV 开始 -->	
                            <!-- 右侧mainRightPositionFourthLineContent DIV 结束 -->	
                        </div>
                        <!-- 右侧mainRightPostionFoutrhLine DIV 结束 -->
                    </td>
                </tr>
                <tr>
                    <td> <!-- 右侧mainRightPostionFifthLine DIV 开始 -->
                    <div id="mainRightPostionFifthLine">
                    </div>
                    <!-- 右侧mainRightPostionFifthLine DIV 结束 -->
                    </td>
                </tr>
                <tr>
                    <td><!-- 右侧mainRightPostionSixthLine DIV 开始 -->
                    <div id="mainRightPostionSixthLine">
                    	<div id="mainRightPositionSixthLineContent">
                        	<a href="#" onclick="" class="a1"><font class="style4">我关注的</font>
                      </div>
                        <ul>
                        	<a href="#" class="a1"><li><img src="images/head5.jpg" width="48" height="48" border="0"><br><font class="style2">呵呵哥哥</font></li></a>
                            <a href="#" class="a1"><li><img src="images/head6.jpg" width="48" height="48" border="0"><br><font class="style2">&nbsp;&nbsp;闷骚&nbsp;&nbsp;男</font></li></a>
                            <a href="#" class="a1"><li><img src="images/head4.jpg" width="48" height="48" border="0"><br><font class="style2">辣条熊</font></li></a>
                        </ul>
                    </div>
                    <!-- 右侧mainRightPostionSixthLine DIV 结束 -->
                    </td>
                </tr>
                <tr>
                    <td><!-- 右侧mainRightPostionSeventhLine DIV 开始 -->
                    <div id="mainRightPostionSeventhLine">
                    </div>
                    <!-- 右侧mainRightPostionSeventhLine DIV 结束 -->
                    </td>
                </tr>
          </tbody></table>
     </div>
      <!-- 右侧mainRightDiv 结束 -->
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