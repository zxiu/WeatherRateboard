<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
  pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Spring MVC Tutorial Series by Crunchify.com</title>

<link rel="stylesheet"
  href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
  src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script
  src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<style type="text/css">
body {
	background-image: url('');
}
</style>
</head>
<body>
  <div class="container primary">
    <div class="page-header">
      <h1>Weather Forecast 10 days</h1>
    </div>
    <ul class="nav nav-pills">
      <c:forEach var="city" items="${cities}">
        <li role="presentation"><a
          href="?country=${city.country}&city=${city.name}">${city.name}</a></li>
      </c:forEach>
    </ul>
    ${weather}
  </div>
</body>
</html>