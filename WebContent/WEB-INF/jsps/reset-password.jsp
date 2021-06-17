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
			<a class="brand" href="index"> <span
				class="first"></span> <span class="second">Cloudisk 私有云</span>
			</a>
		</div>
	</div>

	<div class="row-fluid">
		<div class="dialog">
			<div class="block">
				<p class="block-heading">重置密码</p>
				<div class="block-body">
					<form id="reset-password-form" action="/Cloudisk/reset-password/send-email" method="post">
						<label>注册邮箱：</label> <input type="text" class="span12"></input> <a
							href="#" class="btn btn-primary pull-right">发送重置邮件</a>
						<div class="clearfix"></div>
					</form>
				</div>
			</div>
			<p class="pull-right">
				<a href="index">返回</a>
			</p>
		</div>
	</div>

	<script src="lib/bootstrap/js/bootstrap.min.js"></script>
	<script type="text/javascript">
        $("[rel=tooltip]").tooltip();
        $(function() {
            $('.demo-cancel-click').click(function(){return false;});
        });
    </script>

</body>
</html>


