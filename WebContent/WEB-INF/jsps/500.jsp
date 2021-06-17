<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh">
<head>
<meta charset="utf-8">
<title>500 Error!</title>
</head>

<body class="http-error">

	<div class="row-fluid">
		<div class="http-error">
			<h1>Oops!</h1>
			<p class="info">Something happened that we didn't expect.</p>
			<p>
				<i class="icon-home"></i>
			</p>
			<p>
				<a href="inter/index.jsp">Back to the sign-in page</a>
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


