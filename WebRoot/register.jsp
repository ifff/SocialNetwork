<%@ page contentType="text/html; charset=utf-8" language="java"%>
<html xmlns="http://www.w3.org/1999/xhtml"><head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>注册 同窗网——一起吃辣条好不好</title>
<link href="styles/register.css" type="text/css" rel="stylesheet">
<link href="styles/global.css" type="text/css" rel="stylesheet">
<script src="script/sitedata_bas.js" language="javascript"></script>
<script src="script/datecreate.js" language="javascript"></script>
<script src="script/trim.js" language="javascript"></script>
<script src="script/register.js" language="javascript"></script>
</head>
<script language="javascript">
	function check(form){
		if (form.name.value==""){
			alert("用户名不能为空");
			form.name.focus();
			return false;
		}
		if (form.pwd1.value==""){
			alert("密码不能为空");
			form.pwd1.focus();
			return false;
		}
		if (form.pwd2.value==""){
			alert("确认密码不能为空");
			form.pwd2.focus();
			return false;
		}
		if (form.pwd1.value != form.pwd2.value) {
			alert("两次密码不一致");
			form.pwd2.focus();
			return false;
		}
		if (form.hometown.value==""){
			alert("城市不能为空");
			form.hometown.focus();
			return false;
		}
		if (form.gender.value==""){
			alert("性别不能为空");
			form.gender.focus();
			return false;
		}
		if (form.birthday.value==""){
			alert("生日不能为空");
			form.birthday.focus();
			return false;
		}
		if (form.activity.value==""){
			alert("喜欢的运动不能为空");
			form.activity.focus();
			return false;
		}
		if (form.food.value=="") {
			alert("喜欢的食物不能为空");
			form.food.focus();
			return false;
		}
	}
</script>
<body>
<div id="container">
  <!-- top部分DIV -->
	<div id="top">
    	<!-- top部分的LogoDIV -->
    	<div id="topLogo">
        	<!-- topLogo部分的icoDIV -->
            <div id="topLogoIco"> <a href="index.html"><img src="images/weblogo.jpg" width="72" height="72" alt=""></a>
        </div>
            <!-- topLogo部分的icoDIV结束 -->   
      </div>
        <!-- top部分的LogoDIV结束 -->
        
        <!-- top部分的文字导航 -->
        <div id="topWordMenu">
        	<ul>
          </ul>
        </div>
        <!-- top部分的文字导航结束 -->
    </div>
    <!-- top部分结束 -->
  <div id="banner">
    <div id="bannerTop">
      <div id="bannerWord1">加入同窗网</div>
      <div id="bannerWord2">如果你已经是同窗网注册用户，请直接<a href="index.jsp">登陆同窗网</a></div>
    </div>
    <div id="main">
      <form id="form1" action="account?action=register" method="post">
      <table width="765" border="0" cellpadding="0" cellspacing="0">
        <tbody><tr>
          <td width="71">&nbsp;</td>
          <td width="86" align="center" valign="middle" class="wordleft">用 &nbsp;户&nbsp; 名</td>
          <td width="189" align="center" valign="middle"><input name="name" type="text" class="form" id="name"></td>
          <td width="419" align="left" valign="middle" class="wordright"><img name="img1" width="16" height="16" id="img1"><div class="registertip" id="userIDtip">请输入真实姓名，方便您的朋友与你联系</div></td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td align="center" valign="middle" class="wordleft">创建密码</td>
          <td align="center" valign="middle"><input name="pwd1" type="password" class="form" id="pwd1" onfocus="getfocus(this,img5)" onblur="checkUserPass(img5,this)" maxlength="20"></td>
          <td align="left" valign="middle" class="wordright"><img name="img5" width="16" height="16" id="img5"><div class="registertip" id="userPasstip">记住你的密码哈</div></td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td align="center" valign="middle" class="wordleft">密码确认</td>
          <td align="center" valign="middle"><input name="pwd2" type="password" class="form" id="pwd2" onblur="checkUserRpass(img6,this)" onfocus="getfocus(this,img6)" maxlength="20"></td>
          <td align="left" valign="middle" class="wordright"><img name="img6" width="16" height="16" id="img6"><div class="registertip" id="userRpasstip">请再次输入密码</div></td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td align="center" valign="middle" class="wordleft">所在城市</td>
          <td width="189" align="center" valign="middle"><input name="hometown" type="text" class="form" id="hometown" onblur="checkUserId(img1,this)" onfocus="getfocus(this,img1)"></td>
          <td width="419" align="left" valign="middle" class="wordright"><img name="img1" width="16" height="16" id="img1"><div class="registertip" id="userIDtip">看看哪些人与你同城呀然后约吧</div></td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td align="center" valign="middle" class="wordleft">性别</td>
          <td width="189" align="center" valign="middle"><input name="gender" type="text" class="form" id="gender" onblur="checkUserId(img1,this)" onfocus="getfocus(this,img1)"></td>
          <td width="419" align="left" valign="middle" class="wordright"><img name="img1" width="16" height="16" id="img1"><div class="registertip" id="userIDtip">输入性别</div></td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td align="center" valign="middle" class="wordleft">喜欢的食物</td>
          <td width="189" align="center" valign="middle"><input name="food" type="text" class="form" id="food" onblur="checkUserId(img1,this)" onfocus="getfocus(this,img1)"></td>
          <td width="419" align="left" valign="middle" class="wordright"><img name="img1" width="16" height="16" id="img1"><div class="registertip" id="userIDtip">一起聚餐吧</div></td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td align="center" valign="middle" class="wordleft">喜欢的运动</td>
          <td width="189" align="center" valign="middle"><input name="activity" type="text" class="form" id="activity" onblur="checkUserId(img1,this)" onfocus="getfocus(this,img1)"></td>
          <td width="419" align="left" valign="middle" class="wordright"><img name="img1" width="16" height="16" id="img1"><div class="registertip" id="userIDtip">一起来运动</div></td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td align="center" valign="middle" class="wordleft">出生年月</td>
          <td width="189" align="center" valign="middle"><input name="birthday" type="text" class="form" id="birthday" onblur="checkUserId(img1,this)" onfocus="getfocus(this,img1)"></td>
          <td width="419" align="left" valign="middle" class="wordright"><img name="img1" width="16" height="16" id="img1"><div class="registertip" id="userIDtip">告诉我们你的生日</div></td>
        </tr>
        <tr>
          <td colspan="4" align="center" valign="middle"><input type="submit" name="button" id="button" value="立即注册" class="button" onclick="return check(form1)"></td>
          </tr>
      </tbody></table>
    </form>
    </div>
    <!-- footer部分 -->
    <div id="footer">
    	<!-- footer网站链接部分 -->
        <div id="footerCopy">
        	欢迎加入同窗网
        </div>
        <!-- footer网站版权信息结束 -->
    </div>
    <!-- footer部分结束 -->
</div>
</div>


</body></html>