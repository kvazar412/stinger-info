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
        <div class="header">
            <a href="<c:url value="/filmList"/>">UserName</a>
            <!-- Link currently doesn't work -->
            <input class="exitButton" name="pressedButton"
                type="submit" value="X" />
        </div>

        <div class="title">
            <h1>Find out does this film have post credit scenes!</h1>
            <input name="filmName" type="text" maxlength="255" size="50"
                placeholder="Enter film name here, for example: Deadpool" /> <input
                name="pressedButton" type="submit" value="search" />
        </div>

        <div class="answer yes">
            <p>YES</p>
        </div>

        <div class="answer no">
            <p>NO</p>
        </div>

        <h2>Let's make a site together! Does this film have post credit
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
            <table class="film-list four-column-table title-row">
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