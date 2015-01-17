<!DOCTYPE html>
<html style="height:100%"><head>
	<title>同窗网登陆界面</title>
	<meta name="keywords" content="">
	<meta name="description" content="">
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	
	<link href="css/font-awesome.min.css" rel="stylesheet" type="text/css">
	<link href="css/bootstrap.min.css" rel="stylesheet" type="text/css">
	<link href="css/bootstrap-theme.min.css" rel="stylesheet" type="text/css">
	<link href="css/bootstrap-social.css" rel="stylesheet" type="text/css">	
	<link href="css/templatemo_style.css" rel="stylesheet" type="text/css">	
</head>
<script language="javascript">
	function check(form){
		if (form.name.value==""){
			alert("用户名不能为空");
			form.name.focus();
			return false;
		}
		if (form.pwd.value==""){
			alert("密码不能为空");
			form.pwd.focus();
			return false;
		}
	}
</script>
<body background="images/background.jpg" style="background-size:cover;height:100%;">
	<div class="container">
		<div class="col-md-12">			
			<form id = "form1" class="form-horizontal templatemo-login-form-2" role="form" action="account?action=login" method="post">
				<div class="row">
					<div class="col-md-12">
						<h1>同窗网    --找一起吃辣条的同学</h1>
					</div>
				</div>
				<div class="row">
					<div class="templatemo-one-signin col-md-6">
				        <div class="form-group">
				          <div class="col-md-12">		          	
				            <label for="username" class="control-label">用户名</label>
				            <div class="templatemo-input-icon-container">
				            	<i class="fa fa-user"></i>
				            	<input type="text" class="form-control" id="name" name="name" placeholder="">
				            </div>		            		            		            
				          </div>              
				        </div>
				        <div class="form-group">
				          <div class="col-md-12">
				            <label for="password" class="control-label">密码</label>
				            <div class="templatemo-input-icon-container">
				            	<i class="fa fa-lock"></i>
				            	<input type="password" class="form-control" id="pwd" name="pwd" placeholder="">
				            </div>
				          </div>
				        </div>
				        <div class="form-group">
				          <div class="col-md-12">
				            <div class="checkbox">
				                <label>
				                  <input type="checkbox">下次直接登陆</label>
				            </div>
				          </div>
				        </div>
				        <div class="form-group">
				          <div class="col-md-12">
				            <input type="submit" value="登陆" class="btn btn-warning" onclick="return check(form1)">
				          </div>
				        </div>
				        <div class="form-group">
				          	<div class="col-md-12">
				        		<a href="register.jsp" class="text-center">注册新用户</a>
				       	 	</div>
				    	</div>
					</div>
					   
				</div>				 	
		      </form>		      		      
		</div>
	</div>

</body></html>