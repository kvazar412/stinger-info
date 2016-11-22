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
        <c:if test="${not empty voteList}">
            <table class="info-list nine-column-table title-row second-row">
                <tr>
                    <td>Movie ID</td>
                    <td>Title</td>
                    <td>Year</td>
                    <td>Director</td>
                    <td>Country</td>
                    <td>User ID</td>
                    <td>Vote</td>
                    <td>Vote's date</td>
                </tr>

                <c:forEach items="${voteList}" var="vote">
                    <tr>
                        <td>${vote.movie.movieId}</td>
                        <td>${vote.movie.movieTitle}</td>
                        <td>${vote.movie.movieYear}</td>
                        <td>${vote.movie.movieDirector}</td>
                        <td>${vote.movie.movieCountry}</td>
                        <td>${vote.user.userId}</td>
                        <td>${vote.voteValue}</td>
                        <td>${vote.voteChanged}</td>
                        <td><a class="btn-view"
                                href="<c:url value='/deleteVote/${vote.movie.movieId}&${vote.user.userId}'/>">delete</a></td>
                    </tr>
                </c:forEach>
            </table>
        </c:if>
    </div> 
</body>
</html>