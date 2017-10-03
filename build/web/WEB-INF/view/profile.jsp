<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<c:set var='view' value='/profile' scope='session' />
<div class="main">
    <h2><fmt:message key="personalDetails"/></h2>
    <br/>
    <form action="changePseudoEmail" method="post">
        <c:if test="${!empty newPseudoError}">
            <p class="error">
                <fmt:message key="provideValidEntries"/> 
                <strong> <fmt:message key="pseudo"/></strong>
            </p>
        </c:if>
        <c:if test="${!empty newEmailError}">
            <p class="error">
                <fmt:message key="provideValidEntries"/> 
                <strong> <fmt:message key="email"/></strong>
            </p>
        </c:if>
        <c:if test="${!empty newPseudoTaken}">
            <p class="error">
                <fmt:message key="pseudoTaken"/> 
            </p>
        </c:if>
        <p>
            <strong><fmt:message key="pseudo"/> : </strong>
            <input type="text" value="${loggedPlayer.pseudo}" name="newPseudo"/>
        </p>
        <p>
            <strong><fmt:message key="email"/> : </strong>
            <input type="text" value="${loggedPlayer.email}" name="newEmail"/>
        </p>
        <input type="submit" value="<fmt:message key="save"/>"/>
    </form>
    <br/>
    <c:choose>
        <c:when test="${changePasswordRequired}">
            <form action="passwordChanged" method=post>
                <div id="loginBox">
                <table>
                    <c:if test="${!empty validationErrorFlag}">
                        <tr>
                            <td colspan="2" style="text-align:left">
                                <span class="error"><fmt:message key="provideValidEntries"/>
                                    <c:if test="${!empty currentPasswordError}">
                                        <br><span class="indent"><strong><fmt:message key="currentPassword"/></strong></span>
                                    </c:if>
                                    <c:if test="${!empty newPasswordError}">
                                        <br><span class="indent"><strong><fmt:message key="newPassword"/></strong></span>
                                    </c:if>
                                    <c:if test="${!empty confirmNewPasswordError}">
                                        <br><span class="indent"><strong><fmt:message key="confirmNewPassword"/></strong></span>
                                    </c:if>
                                </span>
                            </td>
                        </tr>
                    </c:if>
                    <tr>
                        <td><strong><fmt:message key="currentPassword"/>:</strong></td>
                        <td><input id="currentPassword" type="password" size="20" name="currentPassword"></td>
                    </tr>
                    <tr>
                        <td><strong><fmt:message key="newPassword"/>:</strong></td>
                        <td><input id="newPassword" type="password" size="20" name="newPassword"></td>
                    </tr>
                    <tr>
                        <td><strong><fmt:message key="confirmNewPassword"/>:</strong></td>
                        <td><input id="confirmNewPassword" type="password" size="20" name="confirmNewPassword"></td>
                    </tr>
                    <tr>
                        <td colspan="2"><input type="submit" value="<fmt:message key="validate"/>"></td>
                    </tr>
                </table>
                </div>
            </form>
        </c:when>
        <c:otherwise>
            <p><a href="changePassword"><fmt:message key="changePassword"/></a></p>
            </c:otherwise>
        </c:choose>
        <br/>
</div>
<div class="main">
    <h2><fmt:message key="personalScores"/></h2>
    <br/>
    <table>
        <tr>
            <th><fmt:message key="tag"/></th><th><fmt:message key="bestScore"/></th><th><fmt:message key="timeDate"/></th>
        </tr>
        <c:forEach var="score" items="${loggedPlayerScores}">
            <tr>
                <td>${score.tag.name}</td><td>${score.bestScore}</td><td>${score.dateFormatted}</td>
            </tr>
        </c:forEach>
    </table>
</div>