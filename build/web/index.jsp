
<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:if test="${!empty language}">
    <fmt:setLocale value="${language}" scope="session" />
</c:if>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>History Quiz</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="Description" lang="en" content="History Quiz Contest According to subjects">
        <meta name="author" content="MTT">
        <meta name="robots" content="index, follow">

        <!-- icons -->
        <link rel="apple-touch-icon" href="assets/img/apple-touch-icon.png">
        <link rel="shortcut icon" href="favicon.ico">

        <!-- Override CSS file - add your own CSS rules -->
        <link rel="stylesheet" href="css/history_quiz_styles.css">

        <script src="js/jquery-1.4.2.js" type="text/javascript"></script>
        <script src="js/jquery-ui-1.8.4.custom.min.js" type="text/javascript"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
    </head>
    <body>
        <c:set var='view' value='/index' scope='session' />
        <div class="header">
            <ul class="list-inline">
                <li>
                    <h1 class="header-heading">
                        <a class="title" href="<c:url value='/'/>">
                            History Quiz
                        </a>
                    </h1>
                </li>
                <li class="language" style="float:right">
                    <c:choose>
                        <c:when test="${empty sessionScope['javax.servlet.jsp.jstl.fmt.locale.session']}">
                            <c:choose>
                                <c:when test="${pageContext.request.locale.language ne 'fr'}">
                                    english
                                </c:when>
                                <c:otherwise>
                                    <c:url var="url" value="chooseLanguage">
                                        <c:param name="language" value="en"/>
                                    </c:url>
                                    <a class="headerLink" href="${url}">english</a>
                                </c:otherwise>
                            </c:choose> |

                            <c:choose>
                                <c:when test="${pageContext.request.locale.language eq 'fr'}">
                                    français
                                </c:when>
                                <c:otherwise>
                                    <c:url var="url" value="chooseLanguage">
                                        <c:param name="language" value="fr"/>
                                    </c:url>
                                    <a class="headerLink" href="${url}">français</a>
                                </c:otherwise>
                            </c:choose>
                        </c:when>
                        <c:otherwise>
                            <c:choose>
                                <c:when test="${sessionScope['javax.servlet.jsp.jstl.fmt.locale.session'] ne 'fr'}">
                                    english
                                </c:when>
                                <c:otherwise>
                                    <c:url var="url" value="chooseLanguage">
                                        <c:param name="language" value="en"/>
                                    </c:url>
                                    <a class="headerLink"  href="${url}">english</a>
                                </c:otherwise>
                            </c:choose> |

                            <c:choose>
                                <c:when test="${sessionScope['javax.servlet.jsp.jstl.fmt.locale.session'] eq 'fr'}">
                                    français
                                </c:when>
                                <c:otherwise>
                                    <c:url var="url" value="chooseLanguage">
                                        <c:param name="language" value="fr"/>
                                    </c:url>
                                    <a class="headerLink" href="${url}">français</a>
                                </c:otherwise>
                            </c:choose>
                        </c:otherwise>
                    </c:choose>
                </li>
                <li class="headerLogin" style="float:right">
                    <c:choose>
                        <c:when test="${loggedPlayer==null}">
                            <span><a class="headerLink" href="profile"><fmt:message key="logIn"/></a></span>
                            </c:when>
                            <c:otherwise>
                            <span>
                                <fmt:message key="loggedInAs"/> ${loggedPlayer.pseudo}
                                <a class="headerLink" href="logout">(<fmt:message key="logOut"/>)</a>
                            </span>
                        </c:otherwise>
                    </c:choose>
                </li>

            </ul>
        </div>
        <div class="nav-bar">
            <div class="container">
                <ul class="nav">
                    <li><a href="game_selection"><fmt:message key="startQuizPage"/></a></li>
                    <li><a href="allquestions"><fmt:message key="allQuestionsPage"/></a></li>
                    <li><a href="rankings"><fmt:message key="rankingsPage"/></a></li>
                    <li><a href="profile"><fmt:message key="profilePage"/></a></li>
                    <li><a href="contact"><fmt:message key="contactUs"/></a></li>
                </ul>
            </div>
        </div>
        <div class="content" style="background-image: url(img/HistoryQuizBackground.jpg)">
            <div class="container">
                <div class="main">
                    <h1 class="welcomeTitle"><fmt:message key="welcomePageText"/></h1>
                </div>
                <div class="aside">

                </div>
            </div>
        </div>
        <div class="footer">
            <div class="container">
                &copy; Copyright MTT 2016
            </div>
        </div>
    </body>
</html>
