<%-- 
    Document   : error
    Created on : 8 févr. 2016, 11:56:44
    Author     : Melkior
--%>
<c:set var='view' value='/error' scope='session' />
<div id="loginBox">

    <p class="error"><fmt:message key="invalid"/></p>

    <p><fmt:message key="returnTo"/> <strong><a href="login"><fmt:message key="loginPage"/></a></strong>.</p>

</div>
