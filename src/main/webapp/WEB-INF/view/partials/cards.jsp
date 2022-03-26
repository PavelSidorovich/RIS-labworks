<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${not empty filter}">
    <div class="alert alert-info" role="alert">
        Show results: cafe type - ${filter}
    </div>
</c:if>
<div class="album py-5 bg-light">
    <div class="container">
        <div class="row">
            <c:if test="${not empty requestScope.cafes}">
                <c:forEach var="cafe" items="${requestScope.cafes}">
                    <div class="col-md-4">
                        <div class="card flex-md-column mb-4 box-shadow h-md-250">
                            <div class="card-header align-items-start">
                                <strong class="d-inline-block mb-2 text-primary">#${cafe.type.type}</strong>
                            </div>
                            <div class="card-body d-flex flex-column align-items-start">
                                <h2 class="mb-0">
                                    <a class="text-dark" href="#">${cafe.name}</a>
                                </h2>
                                <h5 class="card-title pricing-card-title">$${cafe.avgCost} <small class="text-muted">/
                                    per
                                    person</small></h5>
                                <p class="card-text mb-auto">- ${cafe.tableAmount} tables in cafe</p>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </c:if>
        </div>
    </div>
</div>