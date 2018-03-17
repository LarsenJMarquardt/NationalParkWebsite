<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:import url="/WEB-INF/jsp/common/header.jsp"/>

<div class="main_park">
<c:forEach var="item" items="${getAllParks}">
    <div class="park">
        <div class="main_park_image"><a href="parkDetail?parkCode=${item.parkCode}&temperaturePreference=${temperaturePreference.temperaturePreference}">
            <img class="park_images" src="/img/parks/${item.parkCode.toLowerCase()}.jpg"/></a></div>
        <div class="main_park_text"><h1><a href="parkDetail?parkCode=${item.parkCode}&temperaturePreference=${temperaturePreference.temperaturePreference}"><c:out
                value="${item.parkName}"/></a></h1>
        <p><c:out value="${item.parkDescription}"/></p></div>
    </div>
</c:forEach>
</div>

<c:import url="/WEB-INF/jsp/common/footer.jsp"/>
