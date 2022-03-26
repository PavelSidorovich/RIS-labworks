<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Message Driven Beans</title>
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
    <h2>Message sender<span id="filterCount" class="badge badge-secondary badge-pill"></span></h2>
    <c:if test="${not empty message}">
        <div class="alert alert-success" role="alert">
            <h4 class="alert-heading">Success!</h4>
            <p>${message}</p>
        </div>
    </c:if>
    <form method="post">
        <div class="input-group mb-3">
            <textarea name="messages" class="form-control" style="resize: none"
                      placeholder="Write messages (divided by ';')"
                      aria-label="Write a message" rows="10" required></textarea>
            <div class="input-group-append">
                <button class="btn btn-outline-primary" type="submit">Send</button>
            </div>
        </div>
    </form>
    <br>
</div>
<jsp:include page="partials/footer.jsp"/>
</body>
</html>
