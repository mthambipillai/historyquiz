<%-- 
    Document   : login
    Created on : 8 févr. 2016, 11:56:35
    Author     : Melkior
--%>
<c:set var='view' value='/login' scope='session' />
<script src="js/jquery.validate.js" type="text/javascript"></script>
<c:choose>
    <c:when test="${empty sessionScope['javax.servlet.jsp.jstl.fmt.locale.session']}">
        <c:if test="${pageContext.request.locale.language eq 'fr'}">
            <script src="js/localization/messages_fr.js" type="text/javascript"></script>
        </c:if>
    </c:when>
    <c:otherwise>
        <c:if test="${sessionScope['javax.servlet.jsp.jstl.fmt.locale.session'] eq 'fr'}">
            <script src="js/localization/messages_fr.js" type="text/javascript"></script>
        </c:if>
    </c:otherwise>
</c:choose>
            
<script type="text/javascript">

    $(document).ready(function(){
        $("#newAccount").validate({
            rules: {
                newAccountPseudo: "required",
                newAccountPassword: {
                    required: true,
                    minlength : 10
                },
                newAccountPasswordConfirm: {
                    required: true,
                    minlength : 10
                },
                newAccountEmail: {
                    required: true,
                    email: true
                }
            }
        });
    });
</script>

<p><strong><fmt:message key="alreadyAccount"/></strong></p>
<form action="j_security_check" method=post>
    <div id="loginBox">
        <p><strong><fmt:message key="pseudo"/>:</strong>
            <input type="text" size="20" name="j_username"></p>

        <p><strong><fmt:message key="password"/>:</strong>
            <input type="password" size="20" name="j_password"></p>

        <p><input type="submit" value="<fmt:message key="validate"/>"></p>
    </div>
</form>
<p><strong><fmt:message key="notMember"/></strong></p>
<form id="newAccount" action="createAccount" method=post>
    <div id="loginBox">
    <table>
        <c:if test="${!empty validationErrorFlag}">
            <tr>
                <td colspan="2" style="text-align:left">
                    <span class="error"><fmt:message key="provideValidEntries"/>

                      <c:if test="${!empty nameError}">
                        <br><span class="indent"><strong><fmt:message key="pseudo"/></strong></span>
                      </c:if>
                      <c:if test="${!empty passwordError}">
                        <br><span class="indent"><strong><fmt:message key="password"/></strong></span>
                      </c:if>
                      <c:if test="${!empty confirmPasswordError}">
                        <br><span class="indent"><strong><fmt:message key="confirmPassword"/></strong></span>
                      </c:if>
                      <c:if test="${!empty emailError}">
                        <br><span class="indent"><strong><fmt:message key="email"/></strong></span>
                      </c:if>
                    </span>
                </td>
            </tr>
        </c:if>
        <c:if test="${!empty usedPseudoErrorFlag}">
            <tr>
                <td colspan="2"><span><fmt:message key="pseudoTaken"/></span></td>
            </tr>
        </c:if>
        <tr>
            <td><strong><fmt:message key="pseudo"/>:</strong></td>
            <td><input id="newAccountPseudo" type="text" size="20" name="newAccountPseudo"></td>
        </tr>
        <tr>
            <td><strong><fmt:message key="password"/>:</strong></td>
            <td><input id="newAccountPassword" type="password" size="20" name="newAccountPassword"></td>
        </tr>
        <tr>
            <td><strong><fmt:message key="confirmPassword"/>:</strong></td>
            <td><input id="newAccountPasswordConfirm" type="password" size="20" name="newAccountPasswordConfirm"></td>
        </tr>
        <tr>
            <td><strong><fmt:message key="email"/>:</strong></td>
            <td><input id="newAccountEmail" type="text" size="50" name="newAccountEmail"></td>
        </tr>
        <tr>
            <td colspan="2"><input type="submit" value="<fmt:message key="validate"/>"></td>
        </tr>
    </table>
    </div>
</form>