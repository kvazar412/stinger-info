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
    <div class="header">
        <a href="<c:url value="/filmList"/>">UserName</a>
        <input class="exitButton"
            name="pressedButton" type="submit" value="X" />
    </div>

    <div class="main-menu-href">
        <a href="<c:url value="/searchPage"/>">Back to search</a>
    </div>

    <div>      
        <c:url var="action" value="/createOrUpdate"/>
        <form:form action="${action}" commandName="film">
            <table class="film-list eight-column-table input-row">
                <tr>
                    <td><form:input path="filmId"/></td>
                    <td><form:input path="filmName" maxlength="255"/></td>
                    <c:if test="${empty film.filmId}">
                        <td><input name="filmYear" type="text" maxlength="4"/></td>
                    </c:if>
                    <c:if test="${not empty film.filmId}">
                        <td><form:input path="filmYear" maxlength="4"/></td> 
                    </c:if>                    
                    <td><form:input path="filmDirector" maxlength="70"/></td>
                    <td><form:input path="filmCountry" maxlength="70"/></td>
                    <td><form:input path="pcsInfo" maxlength="7"/></td>
                    <td><input type="submit" class="btn-view" value="save"/></td>
                    <td><input type="reset" class="btn-view" value="reset"/></td>
                </tr>
                <tr>
                    <td colspan="6">
                        <form:errors path="filmId"/>
                        <form:errors path="filmName"/>
                        <form:errors path="filmYear"/>
                        <form:errors path="filmDirector"/>
                        <form:errors path="filmCountry"/>
                        <form:errors path="pcsInfo"/>
                    </td>
                </tr>
            </table>
        </form:form>
    </div>

    <div>
        <c:if test="${not empty filmList}">
            <table class="film-list eight-column-table title-row">
                <tr>
                    <td>ID</td>
                    <td>Title</td>
                    <td>Year</td>
                    <td>Director</td>
                    <td>Country</td>
                    <td>PCS-info</td>
                </tr>

                <c:forEach items="${filmList}" var="film">
                    <tr>
                        <td>${film.filmId}</td>
                        <td>${film.filmName}</td>
                        <td>${film.filmYear}</td>
                        <td>${film.filmDirector}</td>
                        <td>${film.filmCountry}</td>
                        <td>${film.pcsInfo}</td>
                        <td><a class="btn-view"
                                href="<c:url value='/readFilm/${film.filmId}'/>">edit</a></td>
                        <td><a class="btn-view"
                                href="<c:url value='/deleteFilm/${film.filmId}'/>">delete</a></td>
                    </tr>
                </c:forEach>
            </table>
        </c:if>
    </div>
</body>
</html>
