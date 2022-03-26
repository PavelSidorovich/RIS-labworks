<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<nav class="navbar sticky-top navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="#">RIS LabWorks</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarText"
            aria-controls="navbarText" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarText">
        <ul id="menu" class="navbar-nav">
            <li class="nav-item">
                <a class="nav-link" href="${contextPath}/cafes/type">Find cafes by type</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="${contextPath}/cafes/filter">Find cafes by filter</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="${contextPath}/users">Find user by id</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="${contextPath}/messages">Message sender</a>
            </li>
        </ul>
    </div>
</nav>