<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="false"%>
<html>
<head>
    <title>DTMHAPCS?</title>
    <link href="<c:url value="/css/style.css"/>" type="text/css"
    rel="stylesheet">
</head>

<body>
    <jsp:include page="header.jsp"/>
    <jsp:include page="backNtab.jsp"/>

    <div>      
        <c:url var="action" value="/createOrUpdateMovie"/>
        <form:form action="${action}" commandName="movie">
            <table class="info-list eight-column-table input-row">
                <tr>
                    <td><form:input path="movieId" maxlength="255"/></td>
                    <td><form:input path="movieTitle" maxlength="255"/></td>
                    <c:if test="${empty movie.movieId}">
                        <td><input name="movieYear" type="text" maxlength="4"/></td>
                    </c:if>
                    <c:if test="${not empty movie.movieId}">
                        <td><form:input path="movieYear" maxlength="4"/></td> 
                    </c:if>                    
                    <td><form:input path="movieDirector" maxlength="70"/></td>
                    <td><form:input path="movieCountry" maxlength="70"/></td>
                    <td><form:input path="pcsInfo" maxlength="7"/></td>
                    <td><input type="submit" class="btn-view" value="save"/></td>
                    <td><input type="reset" class="btn-view" value="reset"/></td>
                </tr>
                <tr>
                    <td colspan="6">
                        <form:errors path="movieId"/>
                        <form:errors path="movieTitle"/>
                        <form:errors path="movieYear"/>
                        <form:errors path="movieDirector"/>
                        <form:errors path="movieCountry"/>
                        <form:errors path="pcsInfo"/>
                    </td>
                </tr>
            </table>
        </form:form>
    </div>

    <div>
        <c:if test="${not empty movieList}">
            <table class="info-list eight-column-table title-row second-row">
                <tr>
                    <td>ID</td>
                    <td>Title</td>
                    <td>Year</td>
                    <td>Director</td>
                    <td>Country</td>
                    <td>PCS-info</td>
                </tr>

                <c:forEach items="${movieList}" var="movie">
                    <tr>
                        <td>${movie.movieId}</td>
                        <td>${movie.movieTitle}</td>
                        <td>${movie.movieYear}</td>
                        <td>${movie.movieDirector}</td>
                        <td>${movie.movieCountry}</td>
                        <td>${movie.pcsInfo}</td>
                        <td><a class="btn-view"
                                href="<c:url value='/readMovie/${movie.movieId}'/>">edit</a></td>
                        <td><a class="btn-view"
                                href="<c:url value='/deleteMovie/${movie.movieId}'/>">delete</a></td>
                    </tr>
                </c:forEach>
            </table>
        </c:if>
    </div>
</body>
</html>
