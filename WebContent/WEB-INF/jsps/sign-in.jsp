<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh">
<head>
<meta charset="utf-8">
<title>Cloudisk 私有云</title>
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
		<div class="dialog">
			<div class="block">
				<p class="block-heading">登录</p>
				<div class="block-body">
					<form id='login-form' action="/Cloudisk/login/check" method="post">
						<fieldset>
							<div class="form-group">
								<label>邮箱</label>
								<div class="col-lg-5">
									<input type="text" class="span12" name="email" />
								</div>
							</div>
							<div class="form-group">
								<label>密码</label>
								<div class="col-lg-5">
									<input type="password" class="span12" name="psd" />
								</div>
							</div>
							<div class="form-group">
								<div class="col-lg-9 col-lg-offset-3">
									<button type="submit" class="btn btn-primary pull-right">登录</button>
								</div>
							</div>
							<div class="clearfix">${LOGIN_ERROR}</div>
						</fieldset>
					</form>
				</div>
			</div>
			<p class="pull-right">
				<a href="/Cloudisk/regist">注册 </a>/<a href="/Cloudisk/reset-password">
					忘记密码?</a>
			</p>
		</div>
	</div>

	<script type="text/javascript">
		$(document).ready(function() {
			$('#login-form').bootstrapValidator({
				message : 'This value is not valid',
				feedbackIcons : {
					valid : 'glyphicon glyphicon-ok',
					invalid : 'glyphicon glyphicon-remove',
					validating : 'glyphicon glyphicon-refresh'
				},
				fields : {
					email : {
						validators : {
							notEmpty : {
								message : '账号邮箱不能为空！'
							},
							emailAddress : {
								message : '账号邮箱格式不对！'
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
								message : '密码长度有误！。'
							}
						}
					}
				}
			});
		});
	</script>

</body>
</html>


