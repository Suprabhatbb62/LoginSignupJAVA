<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Register Page</title>
</head>
<body>
	<form action="regForm" method="post">
		Name : <input type="text" name="name1" /> <br/><br/> Email : <input type="text"
			name="email1" /><br/> Password : <input type="text" name="password1" /><br/><br/>
		Gender: Male<input type="radio" name="gender1" value="Male" checked/> Female<input
			type="radio" name="gender1" value="Female"/><br/><br/> City: <select name="city1">
			<option>Kolkata</option>
			<option>Delhi</option>
			<option>Mumbai</option>
			<option>Pune</option>
			<option>Banglore</option>
		</select><br/><br/>
		<input type="submit" value="Register"/>
	</form>
</body>
</html>