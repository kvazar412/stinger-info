<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="false"%>
<html>
<head>
<head>
    <title>DTMHAPCS?</title>
    <link href="<c:url value="/css/style.css"/>" type="text/css"
        rel="stylesheet">
</head>
<body>
    <form>
    <jsp:include page="header.jsp" />

        <div class="title">
            <h1>Find out does this movie have post credit scenes!</h1>
            <input name="movieTitle" type="text" maxlength="255" size="50"
                placeholder="Enter movie name here, for example: Deadpool" /> <input
                name="pressedButton" type="submit" value="search" />
        </div>

        <div class="answer yes">
            <p>YES</p>
        </div>

        <div class="answer no">
            <p>NO</p>
        </div>

        <h2>Let's make a site together! Does this movie have post credit
            scenes?</h2>

        <div class="vote-yes">
            <input class="answer yes" name="pressedButton" type="submit"
                value="YES" />
            <span># votes</span>
        </div>
        <div class="vote-no">
            <input class="answer no" name="pressedButton" type="submit"
                value="NO" />
            <span># votes</span>
        </div>
        
        <h3>Thanks for your vote, UserName!</h3>

        <div>
            <table class="info-list four-column-table title-row second-row">
                <tr>
                    <td>Title</td>
                    <td>Year</td>
                    <td>Director</td>
                    <td>Country</td>
                </tr>
                <tr>
                    <td>Deadpool</td>
                    <td>2016</td>
                    <td>Tim Miller</td>
                    <td>USA</td>
                </tr>
                <tr>
                    <td>The Matrix</td>
                    <td>1999</td>
                    <td>Lana Wachowski, Lilly Wachowski</td>
                    <td>USA</td>
                </tr>
            </table>
        </div>
    </form>
</body>
</html>