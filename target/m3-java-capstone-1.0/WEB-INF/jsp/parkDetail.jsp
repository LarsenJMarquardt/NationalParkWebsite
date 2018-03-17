<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:import url="/WEB-INF/jsp/common/header.jsp"/>
<div class="detail_grid">
    <div class="image"><img src="/img/parks/${park.parkCode.toLowerCase()}.jpg"/></div>
    <div class="detail_text">
        <div><c:out value="${park.parkName}"/></div>
        <div><c:out value="Founded in ${park.yearFounded} at the state of ${park.state}"/></div>
        <div><c:out value="${park.inspirationalQuote} from ${park.inspirationalQuoteSource}"/></div>
        <div><c:out value="Acreage: ${park.acreage}"/></div>
        <div><c:out value="Elevation in Feet: ${park.elevationInFeet}"/></div>
        <div><c:out value="Miles of Trail: ${park.milesOfTrail}"/></div>
        <div><c:out value="Number of Campsites: ${park.numberOfCampsites}"/></div>
        <div><c:out value="Climate in: ${park.climate}"/></div>
        <div><c:out value="Annual Visitors: ${park.annualVisitorCount}"/></div>
        <div><c:out value="Park Description: ${park.parkDescription}"/></div>
        <div><c:out value="Park entry fee: ${park.entryFee}"/></div>
        <div><c:out value="Number of animal species: ${park.numberOfAnimalSpecies}"/></div>
    </div>

    <div class="small_weather">
        <c:set var="i" value="0"/>
        <c:set var="j" value="1"/>
        <c:forEach var="weather" items="${weather}" varStatus="loop">
            <c:choose>
            <c:when test = "${loop.index == 0}">
                <div  class="firstWeather">
                    <a href="parkDetail?parkCode=${park.parkCode}&temperaturePreference=Celsius"><c:out value="C"/></a>/<a
                        href="parkDetail?parkCode=${park.parkCode}&temperaturePreference=Fahrenheit"><c:out value="F"/></a>
                    <h4>Today</h4>
            </c:when>
            <c:otherwise>
                <div  class="weekWeather">
            </c:otherwise>
            </c:choose>
                <div><img src="/img/weather/${weather.forecast.replaceAll("\\s","")}.png"/></div>
                <div><c:out value="High: ${weather.high}"/></div>
                <div><c:out value="Low: ${weather.low}"/></div>
                <div><c:out value="Weather: ${weather.forecast}"/></div>
            </div>
        </c:forEach>
                </div>
        <c:forEach var="recommendation" items="${recommendation}" varStatus="loop">
            <c:out value="${recommendation}"/>
            <br>
        </c:forEach>
        </div>
<c:import url="/WEB-INF/jsp/common/footer.jsp"/>
