<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<!--防止转发路径问题  -->
<base href="http://localhost:8080/gdms-web/">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<meta name="renderer" content="webkit">
<title>登录</title>
<link rel="stylesheet" href="css/pintuer.css">
<link rel="stylesheet" href="css/admin.css">
<link rel="stylesheet" href="css/dialog-min.css">
<script src="js/jquery-3.2.1.min.js"></script>
<script src="js/dialog-min.js"></script>
<script src="js/pintuer.js"></script>
</head>
<body style="background: url(images/bg.jpg);">


	<!-- 	<div class="bg"></div> -->
	<div class="container">
		<div class="line bouncein">
			<div class="xs6 xm4 xs3-move xm4-move">
				<div style="height: 0px;"></div>
				<div class="media media-y margin-big-bottom"></div>

				<!----------------------------------------------------------  -->

				<form action="admin/user/login.action" method="post" id="loginForm">
					<!--检查提交  -->
					<input type='hidden' id='loginMsg' value="${login_msg}">
					<div style="height: 150px;"></div>
					<div class="panel loginbox">
						<div class="text-center margin-big padding-big-top">
							<h1>后台管理中心</h1>
						</div>
						<div class="panel-body"
							style="padding: 30px; padding-bottom: 10px; padding-top: 10px;">
							<div class="form-group">
								<div class="field field-icon-right">
									<input type="text" class="input input-big" name="name" id="name"
										placeholder="登录账号" data-validate="required:请填写账号" value="yy" /> <span
										class="icon icon-phone margin-small"></span>
								</div>
							</div>
							<div class="form-group">
								<div class="field field-icon-right">
									<input type="password" class="input input-big" name="password" id="password"
										placeholder="登录密码" data-validate="required:请填写密码" value="222" /> <span
										class="icon icon-key margin-small"></span>
								</div>
							</div>
							<div class="form-group">
								<div class="field">
									<input type="text" class="input input-big" name="code" id="code" value="AAAA"
										placeholder="填写右侧的验证码" data-validate="required:请填写右侧的验证码" />
									<img src="admin/user/yzm/code.png" alt="" width="100"
										height="32" class="passcode"
										style="height: 43px; cursor: pointer;"
										onclick="this.src=this.src+'?'">

								</div>
							</div>
						</div>
						<div style="padding: 30px;">
							<input type="submit"
								class="button button-block bg-main text-big input-big"
								id="Login" value="登录">
						</div>
						<div style="padding: 0px 30px 30px;">
							<input type="button"
								class="button button-block bg-main text-big input-big"
								id="goReg" value="去注册">
						</div>
					</div>
				</form>
				<!---------------------------------------------------------  -->
				<iframe name="ifr_up" style="display: none;"></iframe>
				<form action="admin/user" style="display: none;" target="ifr_up"
					name="form1" enctype="multipart/form-data" method="post" id="form1">
					<input name="file" type="file"
						accept="image/gif, image/jpeg, image/png" id="file" /><br />
					<!-- 允许上传的文件类型 gif , png, jpg  也可以用accept="image/*" -->
				</form>




				<form action="admin/user/reg.action" id="regForm" method="post"
					style="display: none;" onsubmit="return check()">
					<!--检查提交  -->
					<input type='hidden' id='errMsg' value="${err_msg}">
					<input type='hidden' id='regMsg' value="${reg_msg}">
					<!--绑定服务端错误消息  -->
					<div class="panel loginbox">
						<div class="text-center margin-big padding-big-top">
							<h1>后台管理中心</h1>
						</div>
						<div class="panel-body"
							style="padding: 30px; padding-bottom: 10px; padding-top: 10px;">
							<div class="form-group">
								<div class="field field-icon-right">
									<input type="text" class="input input-big" name="name"
										placeholder="手机号码" data-validate="required:请填写手机号" /> <span
										class="icon icon-phone margin-small"></span>
								</div>
							</div>
							<div class="form-group">
								<div class="field field-icon-right">
									<input type="password" class="input input-big" name="password"
										id="pwd1" placeholder="登录密码" data-validate="required:请填写密码" />
									<span class="icon icon-key margin-small"></span>
								</div>
							</div>
							<div class="form-group">
								<div class="field field-icon-right">
									<input type="password" class="input input-big" name="password2"
										id="pwd2" placeholder="确认密码" data-validate="required:确认密码" />
									<span class="icon icon-key margin-small"></span>
								</div>
							</div>
							<div class="form-group">
								<div class="field field-icon-right">
									<input type="text" class="input input-big" name="realname"
										placeholder="真实姓名" data-validate="required:请填写真实姓名" /> <span
										class="icon icon-user margin-small"></span>
								</div>
							</div>
							<div class="form-group">
								<!-- <div class="label">
						          <label>网站LOGO：</label>
						        </div> -->
								<div class="field">
									<input type="text" placeholder="点击此处上传头像"
										data-validate="required:请选择图片作为头像" id="pic" readonly
										name="slogo" class="input tips"
										style="width: 60%; float: left;" value="" /> <input
										type="button" class="button bg-blue margin-left" id="doupload"
										value="+ 提交上传"> <span id="view"></span>
								</div>
							</div>
							<div class="form-group">
								<div class="field field-icon-right">
									<input type="text" class="input input-big" name="Idnumber"
										placeholder="身份证号" data-validate="required:请填写身份证号" /> <span
										class="icon icon-user margin-small"></span>
								</div>
							</div>
							<div class="form-group">
								<div class="field field-icon-right">
									<select class="place input-big" name="prov" id="prov"></select>
									<select class="place input-big" name="city" id="city"></select>
								</div>
							</div>
							<div class="form-group">
								<div class="field">
									<input type="text" class="input input-big" name="code" id="yzm"
										placeholder="填写右侧的验证码" data-validate="required:请填写右侧的验证码" />
									<img src="admin/user/yzm/code.png" alt="" width="100"
										height="32" class="passcode"
										style="height: 43px; cursor: pointer;"
										onclick="this.src=this.src+'?'">

								</div>
							</div>
						</div>
						<div style="padding: 30px;">
							<input type="submit"
								class="button button-block bg-main text-big input-big" id="Reg"
								value="注册">
						</div>
						<div style="padding: 0px 30px 30px;">
							<input type="button"
								class="button button-block bg-main text-big input-big"
								id="goLogin" value="去登录">
						</div>
					</div>
				</form>
				<!---------------------------------------------------------  -->
			</div>
		</div>
	</div>

</body>
<script type="text/javascript">
	$("#goReg,#goLogin").click(function() {
		$("#loginForm").slideToggle();
		$("#regForm").slideToggle();
		//		$("form:not(:eq(1))").slideToggle();
	})

	function check() { //检查输入项是否合法,不合法就提示,并return false阻止表单提交
		var v = $("#pic").val();
		var p = $("#pwd1").val();
		var p2 = $("#pwd2").val();
		if (v.indexOf("\\") != -1) { //查找内容中是否有路径分隔符,有就提示上传
			qipao("请提交上传", $("#pic"));
			return false;
		}
		if (p != p2) {
			qipao("密码不一致", $("#pwd2"));
			return false;
		}
		return true;
	}

	function initProv(target, pid) {
		$.ajax({

			url : "admin/area/provlist", //请求的URI
			data : {
				"parentid" : pid
			}, //提交的参数数据
			type : "GET", //请求的方式一般获取用GET提交用POST
			success : function(result) { //成功之后做什么
				var json = eval(result); //把返回的json对象接收成js中的json对象
				var list = json.data; //得到对象中的集合

				target.options.length = 0;//清空当前下拉选项
				for (var i = 0; i < list.length; i++) {
					var o = list[i];
					var op = new Option(o.name, o.areaid);
					target.options.add(op);//向下拉框增加选项
				}
			}

		});
	}

	onload = function() {
		//初始化
		initProv(document.getElementById("prov"), 0);
		initProv(document.getElementById("city"), 1);
		$("#prov").change(function() {
			//那当前省份id,发起新的查询,得到city
			var id = $(this).val();
			initProv(document.getElementById("city"), id);

		});

		var m = $("#errMsg").val();//获取注册隐藏域的错误消息

		if (m != "") {
			$("#loginForm").hide();
			$("#regForm").show();
			qipao(m, $("#yzm"));//验证码附近弹气泡
		}
		
		m = $("#regMsg").val();//获取登录隐藏域的消息
		if (m != "") {
			qipao(m);//注册成功弹气泡在中间
		}
		
		m = $("#loginMsg").val();//获取登录隐藏域的消息

		if (m != "") {
			qipao(m, $("#code"));//验证码附近弹气泡
		}

		$("#pic").click(function() {
			//模拟文件选择控件的单击事件
			$("#file").trigger("click");
		});

		$("#file").change(function() {
			$("#pic").val($(this).val());
		})

		$("#doupload").click(function() {
			var formdata = new FormData(form1);
			//发异步请求来完成上传
			$.ajax({

				url : "admin/user", //请求的URI
				data : formdata, //提交的参数数据
				type : "POST", //请求的方式
				async : false, //是否异步请求
				cache : false, //是否缓存
				contentType : false, //是否处理内容类型
				processData : false, //是否对数据进行处理
				success : function(result) { //成功之后做什么
					var json = eval(result);//把返回的json对象接收成js中的json对象
					$("#pic").val(json.entity.msg);
					qipao(json.entity.data)
				}

			});

		})
	}
</script>
</html>