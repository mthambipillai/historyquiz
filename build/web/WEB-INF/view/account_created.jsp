<%-- 
    Document   : account_created
    Created on : 14 févr. 2016, 18:08:19
    Author     : Melkior
--%>
<c:set var='view' value='/account_created' scope='session' />
<div class="main">
    <h2><fmt:message key="welcome"/> ${player.pseudo}! <fmt:message key="created"/></h2>
    <p><a href="<c:url value='/'/>" class="btn"><fmt:message key="backToWelcomePage"/></a></p>
</div>
