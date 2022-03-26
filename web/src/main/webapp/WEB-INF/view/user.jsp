<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>User Finder</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css"
          integrity="sha384-zCbKRCUGaJDkqS1kPbPd7TveP5iyJE0EjAuZQTgFLD2ylzuqKfdKlfG/eSrtxUkn" crossorigin="anonymous">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-fQybjgWLrvvRgtW6bFlB7jaZrFsaBXjsOMm/tB9LTS58ONXgqbR9W8oWht/amnpF"
            crossorigin="anonymous"></script>
</head>

<body>
<jsp:include page="partials/navBar.jsp"/>
<div class="container my-5">
    <form method="get" class="form-inline">
        <div class="form-group">
            <label for="userId" class="col-form-label mr-2">User ID:</label>
            <input type="number" id="userId" class="form-control" name="userId" min="0" value="1">
        </div>
        <div class="form-group">
            <button type="submit" class="btn btn-block btn-primary">Search</button>
        </div>
    </form>
    <div class="card">
        <div class="row no-gutters">
            <div class="col-md-3 my-5 ml-5">
                <img src="${contextPath}/images/person-circle.svg" width="200em" alt="account photo label">
            </div>
            <div class="col">
                <c:choose>
                    <c:when test="${not empty requestScope.user}">
                        <div class="card-body my-5">
                            <h1 class="card-title">${requestScope.user.firstName} ${requestScope.user.lastName}</h1>
                            <h2 class="card-title">ID: ${requestScope.user.id}</h2>
                            <h5 class="card-title">ZIP: ${requestScope.user.zip}</h5>
                            <h5 class="card-title">Email: ${requestScope.user.email}</h5>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <div class="card-body my-5">
                            <h1 class="card-title">User not found</h1>
                        </div>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </div>
</div>
<jsp:include page="partials/footer.jsp"/>
</body>
</html>