<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:import url="/WEB-INF/jsp/common/header.jsp"/>

<div class="survey_result">
<c:forEach var="item" items="${getAllSurveys}">
    <div class="survey">
        <img src="/img/parks/${item.parkCode.toLowerCase()}.jpg"/></a>
        <div class="survey_text"><h1><c:out value="${item.parkName}"/></h1>
        <p class="votes">Votes: <c:out value="${item.parkCount}"/></p>
        </div>
    </div>
</c:forEach>
</div>

<c:import url="/WEB-INF/jsp/common/footer.jsp"/>
