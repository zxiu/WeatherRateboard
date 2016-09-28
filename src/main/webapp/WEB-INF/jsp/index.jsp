<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<meta charset="UTF-8">
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
      <a href="/weather" style="text-decoration: none">
        <h1>Weather Forecast 10 days</h1>
      </a>
    </div>
    <ul class="nav nav-pills">
      <c:forEach var="city" items="${cities}">
        <li role="presentation"><a
          href="?country=${city.country}&city=${city.name}">${city.name}</a></li>
      </c:forEach>
    </ul>
    <c:if test="${city!=null}">
      <div class='alert alert-${error==null?"success":"warning" }'
        role="alert">
        ${message} <strong>${error}</strong>
      </div>
    </c:if>
    <c:if test="${city==null}">
      <img id="map" usemap="#planetmap"
        src="https://scontent.ftxl1-1.fna.fbcdn.net/v/t1.0-9/14517598_635694859926798_2421197280889107570_n.jpg?oh=308efc55365ae369a85f8360c58128c1&oe=5874064B"
        style="width: 100%;" />
    </c:if>

    <div id="data" class="hide">${weather}</div>
    <table class="table hidden" id="weather_table">
      <thead>
        <tr>
          <th></th>
          <c:forEach begin="0" end="9" varStatus="loop">
            <th class="title ${loop.index}"></th>
          </c:forEach>
        </tr>
      </thead>
      <tbody>
        <tr>
          <td></td>
          <c:forEach begin="0" end="9" varStatus="loop">
            <td class="text-center"><img src=""
              class="weather icon ${loop.index}" /></td>
          </c:forEach>
        </tr>
        <c:forEach var="item" items="${items}">
          <tr>
            <td class="text-center">${item}<span
              class="${item} unit"></span>
            </td>
            <c:forEach begin="0" end="9" varStatus="loop">
              <td class="text-center ${item} w${loop.index}">-</td>
            </c:forEach>
          </tr>
        </c:forEach>
      </tbody>
    </table>
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
					$(".title." + i).html(
							f.date.year + " " + f.date.monthname_short + " " + f.date.day);
					$(".weather.icon." + i).attr("src", f.icon_url);
					setTdValue("high", i, f.high.celsius, "℃");
					setTdValue("low", i, f.low.celsius, "℃");
					setTdValue("avehumidity", i, f.avehumidity, "%");
					setTdValue("qpf_allday", i, f.qpf_allday.mm, "mm");
					setTdValue("qpf_day", i, f.qpf_day.mm, "mm");
					setTdValue("qpf_night", i, f.qpf_night.mm, "mm");
					setTdValue("snow_allday", i, f.snow_allday.cm, "cm");
					setTdValue("snow_day", i, f.snow_day.cm, "cm");
					setTdValue("snow_night", i, f.snow_night.cm, "cm");
					setTdValue("maxwind", i, f.maxwind.mph + "/" + f.maxwind.degrees
							+ "°", "mph");
				}
				$('#weather_table').fadeIn().removeClass('hidden');
			}

			function setTdValue(td, i, value, unit) {
				$("." + td + ".w" + i).html(value);
				if (unit) {
					$("." + td + ".unit").html("(" + unit + ")");
				}
			}
		</script>
</body>

</html>
