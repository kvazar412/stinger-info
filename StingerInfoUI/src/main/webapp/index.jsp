<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<title>Welcome Page</title>      
<link href="<c:url value="/css/style.css"/>" type="text/css"
    rel="stylesheet">
</head>
<body>
    <h2>Welcome!</h2>  
    <h4><a href="<c:url value="/searchPage"/>">Let's start!</a></h4>
</body>
</html>