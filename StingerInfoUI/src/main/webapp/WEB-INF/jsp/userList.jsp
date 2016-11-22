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
        <c:url var="action" value="/createOrUpdateUser"/>
        <form:form action="${action}" commandName="user">     
            <table class="info-list four-column-table-userlist input-row">
                <tr>
                    <td><form:input path="userId" maxlength="255"/></td>
                    <td><form:input path="userRole" maxlength="7"/></td>
                    <td><input type="submit" class="btn-view" value="save"/></td>
                    <td><input type="reset" class="btn-view" value="reset"/></td>
                </tr>
                <tr>
                    <td colspan="2">
                        <form:errors path="userId"/>
                        <form:errors path="userRole"/>                        
                    </td>
                </tr>
            </table>            
        </form:form>
    </div>  
    
     <div>
        <c:if test="${not empty userList}">          
            <table class="info-list four-column-table-userlist title-row second-row">
                <tr>
                    <td>Id</td>
                    <td>Role</td>
                </tr>
                <c:forEach items="${userList}" var="user">
                    <tr>
                        <td>${user.userId}</td>
                        <td>${user.userRole}</td>
                        <td><a class="btn-view"
                                href="<c:url value='/readUser/${user.userId}'/>">edit</a></td>
                        <td><a class="btn-view"
                                href="<c:url value='/deleteUser/${user.userId}'/>">delete</a></td>
                    </tr>
                </c:forEach>
            </table>
        </c:if>
    </div>
</body>
</html>