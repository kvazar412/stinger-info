<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
    <title>DTMHAPCS?</title>
    <link href="<c:url value="/css/style.css"/>" type="text/css"
    rel="stylesheet">
</head>
<body>
    <div class="main-menu-href">
        <a href="<c:url value="/searchPage"/>">Back to search</a>
    </div>
    
    <div class="inset-switcher">
        <a href="<c:url value="/movieList"/>">MOVIES</a>
        <a href="<c:url value="/userList"/>">USERS</a>
        <a href="<c:url value="/voteList"/>">VOTES</a>    
    </div>
</body>
</html>