<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh">
<head>
<meta charset="utf-8">
<title>Cloudisk私有云</title>
</head>

<body class="">

	<div class="navbar">
		<div class="navbar-inner">
			<ul class="nav pull-right">

			</ul>
			<a class="brand" href="index"><span class="first">Cloudisk</span> 
			<span class="second">私有云</span>
			</a>
		</div>
	</div>

	<div class="row-fluid">
		${RegistFalse}
		<div class="dialog">
			<div class="block">
				<p class="block-heading">注册</p>
				<div class="block-body">
					<form id='signup-form' action="/Cloudisk/regist/do" method="post">
						<input type = "hidden" name = "token" value = "${token}"/>
					
						<div class="form-group">
							<label>邮箱</label>
							<div class="col-lg-5">
								<input type="text" class="span12" name="email" />
							</div>
						</div>
						<div class="form-group">
							<label>姓名</label> <input type="text" class="span12"
								name="name" />
						</div>
						<div class="form-group">
							<label class="col-lg-3 control-label">密码</label>
							<div class="col-lg-5">
								<input type="password" class="span12" name="psd" />
							</div>
						</div>

						<div class="form-group">
							<label class="col-lg-3 control-label">重复密码</label>
							<div class="col-lg-5">
								<input type="password" class="span12" name="confirmPsd" />
							</div>
						</div>

						<div class="form-group">
							<div class="col-lg-9 col-lg-offset-3">
								<button type="submit" class="btn btn-primary pull-right">注册</button>
							</div>
						</div>

						<div class="clearfix"></div>
					</form>
				</div>
			</div>
		</div>
	</div>
	
	<script type="text/javascript">
		$(document)
				.ready(
						function() {
							$('#signup-form')
									.bootstrapValidator(
											{
												message : 'This value is not valid',
												feedbackIcons : {
													valid : 'glyphicon glyphicon-ok',
													invalid : 'glyphicon glyphicon-remove',
													validating : 'glyphicon glyphicon-refresh'
												},
												fields : {
													username : {
														validators : {
															notEmpty : {
																message : '姓名设置不能为空。'
															},
															stringLength : {
																max : 18,
																message : '姓名设置小于18位'
															},
															regexp : {
																regexp : /^[\u4e00-\u9fa5_a-zA-Z0-9]+$/,
																message : '姓名只允许中文、字母、数字和下划线。'
															}
														}
													},
													email : {
														validators : {
															notEmpty : {
																message : '邮箱不能为空！'
															},
															callback : {
																message : '邮箱已经被占用!',
																callback : function(
																		value,
																		validator) {
																	var res = true;
																	if (value
																			.match(/^[a-z0-9]+([._\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$/i)) {
																		$
																				.ajax({
																					url : '/Cloudisk/check-exist/email',
																					type : 'get',
																					async : false,
																					data : {
																						email : value
																					},
																					success : function(data) {
																						if (data >= 1) {
																							res = false;
																						}
																					}
																				});
																	}
																	return res;
																}
															},
															emailAddress : {
																message : '邮箱格式不正确！'
															}
														}
													},
													password : {
														validators : {
															notEmpty : {
																message : '密码不能为空！'
															},
															stringLength : {
																min : 6,
																max : 25,
																message : '密码设置应大于6位小于25位。'
															},
															identical : {
																field : 'confirmPassword',
																message : '两次输入的密码不相符！'
															}
														}
													},
													confirmPassword : {
														validators : {
															notEmpty : {
																message : '重复输入的密码不能为空！'
															},
															stringLength : {
																min : 6,
																max : 25,
																message : '密码设置应大于6位小于25位。'
															},
															identical : {
																field : 'password',
																message : '两次输入的密码不相符！'
															}
														}
													}
												}
											});
						});
	</script>
</body>
</html>


