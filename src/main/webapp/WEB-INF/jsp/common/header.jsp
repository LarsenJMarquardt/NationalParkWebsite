<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>National Park Geek</title>
    <c:url value="/css/nationalparkgeek2.css" var="cssHref"/>
    <link rel="stylesheet" href="${cssHref}">
</head>
<body>
<header>
    <c:url value="/" var="homePageHref"/>
    <c:url value="/img/logo.png" var="logoSrc"/>
    <a href="${homePageHref}">
        <img id="main_image" src="${logoSrc}" alt="National Park Geek logo"/>
    </a>
<nav>
    <ul>
        <li><a href="/">Home</a></li>
        <li><a href="/survey">Survey</a></li>
    </ul>
</nav>
</header>