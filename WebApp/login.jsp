<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>Login</title>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="shortcut icon" href="${link}IMAGES/admin-tab-icon.ico">

<link rel="stylesheet" href="${link}bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="${link}CSS/page-layout.css">

<script src="${link}JS/jquery-2.1.3.min.js"></script>
<script src="${link}bootstrap/js/bootstrap.min.js"></script>

<script type="text/javascript">
	$(function() {

		function checkEmpty() {

			if ($("#userName").val().length == 0) {
				$("#userName").parent().addClass("has-error");

				// 				$(".error_msg").show();
				return true;
			}

 			if ($("#password").val().length == 0) {
				$("#password").parent().addClass("has-error");
				// 				$(".error_msg").show();
				return true;
			} 
			// 			if( $(".userName").val().length==0 || $("#password").val().length == 0 ){
			// 		$(".error_msg").show();
			// 	}
			return false;
		}

		$("#userName").focusout(function() {
			checkEmpty();
		});

		$("#password").focusout(function() {
			checkEmpty();
		});

		$("#login-form").submit(function(event) {

			if (!checkEmpty()) {
				$(this).attr("action", "superlogin");
				$(this).submit();
			}

		});
		$("#login-submit").click(function(){
			
		});
		
	});
</script>
<style type="text/css">
.login-div {
	width: 300px;
	height: 380px;
	margin-bottom: 30px;
	background-color: #FFFFFF;
	border-top: solid 20px;
	border-bottom: solid 5px;
	border-left: solid 1px;
	border-right: solid 1px;
	border-color: #009089;
	padding-bottom: 10px;
	border-radius: 10px;
}

.login-header {
	background-color: #D3E9E9;
	padding: 10px;
}

.alert {
	background-color: #f3f8f8;
	color:red;
	font-family:monospace;
	font-size: 16px;
}

.version{
font-size: 12px;
font-family:monospace;
color:#009089;
}
</style>
</head>
<%-- <body>
	<div class="container top-section custom-title" align="center">
		<h1>${main_title}</h1>
	</div>
	<div class="container middle-section" align="center">
		<hr />
		<div class="login-div" align="center">
			<div class="login-header">
				<h2 class="custom-title">${title}</h2>
			</div>
			<hr />
			<form role="login" id="login-form" method="post">
				<div class="form-group" align="left">
					<label for="userName">User name:</label> <input type="text"
						class="form-control" name="userName" id="userName" tabindex="1"
						placeholder="Enter Username" required>
				</div>
				<div class="form-group" align="left">
					<label for="password">Password:</label> <input type="password"
						class="form-control" name="password" id="password" tabindex="2"
						placeholder="Enter Password" required>
				</div>

				<div class="form-group" align="center">
					<input type="submit" value="Login" id="login-submit"
						class="btn btn-custom input-lg" tabindex="3">
				</div>
			</form>
		</div>
		<hr />
		<div>
		<c:choose>
		<c:when test="${not empty error }">
			<p class="alert alert-danger">${error}</p>
		</c:when>
		<c:otherwise>
		</c:otherwise>
		</c:choose>
		</div>
		<p class="version">Ver.${version}</p>
		<p class="version"><b style="font-family: Courier New;font-size: 12px">powered by</b><a href="http://www.viandd.com" target="_blank"><img alt="viandd logo" src="${link}IMAGES/viandd.png" height="55" width="85"></a></p>
	</div>
</body> --%>
</html>