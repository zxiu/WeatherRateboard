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
    <div id="data" class="hide">${weather}</div>

    <div id="exTab1" class="container">
      <ul class="nav nav-tabs">
        <li class="active"><a id="title0" href="#0a"
          data-toggle="tab"></a></li>
        <li><a id="title1" href="#1a" data-toggle="tab"></a></li>
        <li><a id="title2" href="#2a" data-toggle="tab"></a></li>
        <li><a id="title3" href="#3a" data-toggle="tab"></a></li>
        <li><a id="title4" href="#4a" data-toggle="tab"></a></li>
        <li><a id="title5" href="#5a" data-toggle="tab"></a></li>
        <li><a id="title6" href="#6a" data-toggle="tab"></a></li>
        <li><a id="title7" href="#7a" data-toggle="tab"></a></li>
        <li><a id="title8" href="#8a" data-toggle="tab"></a></li>
        <li><a id="title9" href="#9a" data-toggle="tab"></a></li>
      </ul>
      <div class="tab-content clearfix">
        <div class="tab-pane active" id="0a"></div>
        <div class="tab-pane" id="1a"></div>
        <div class="tab-pane" id="2a"></div>
        <div class="tab-pane" id="3a"></div>
        <div class="tab-pane" id="4a"></div>
        <div class="tab-pane" id="5a"></div>
        <div class="tab-pane" id="6a"></div>
        <div class="tab-pane" id="7a"></div>
        <div class="tab-pane" id="8a"></div>
        <div class="tab-pane" id="9a"></div>
      </div>
    </div>

    <div id="template">
      <img src="" class="icon" />
      
    </div>

  </div>
  <script type="text/javascript">
			$(document).ready(function() {
				showWeather($("#data").html());
			});
			function showWeather(json) {
				var weather = JSON.parse(json);
				if (!weather) {
					return;
				}
				var forecast = weather.forecast;
				var simpleforecast = forecast.simpleforecast;
				var forecastday = simpleforecast.forecastday;

				for (var i = 0; i < 10; i++) {
					if (i == 0) {
						console.log(forecastday[i]);
					}
					var f = forecastday[i];
					$("#title" + i).html(
							f.date.year + " " + f.date.monthname_short + " " + f.date.day);

					var template = $("#template").clone();
					$("#"+i+"a").append(template);
					template.find(".icon").attr("src", f.icon_url)
					
				}
			}
		</script>
</body>

</html>