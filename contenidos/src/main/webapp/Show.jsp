<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="layout.jsp" />

    <div class="container">
        <div class="row">
            <div class="col-6">
                <p>hola</p>
            </div>
            <div class="col-6 ">
                <c:forEach items="${users}" var="user">
                    <h5>${user.name}</h5>
                </c:forEach>
            </div>
            <h2>${camila.name}</h2>
        </div>
    </div>
</body>
</html>
