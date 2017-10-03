<%-- 
    Document   : contact
    Created on : 27 févr. 2016, 14:21:21
    Author     : Melkior
--%>

<c:set var='view' value='/contact' scope='session' />
<div class="main">
    <h1><fmt:message key="contactUs"/></h1>
    <p>
        <fmt:message key="sendText"/>
    </p>
    <form action='sendContactMail' method="post" >
        <c:if test="${!empty mailSubjectError}">
            <p class="error"><fmt:message key="subjectError"/></p>
        </c:if>
        <c:if test="${!empty mailMessageError}">
            <p class="error"><fmt:message key="messageError"/></p>
        </c:if>
        <input type='text' name="mailSubject" value="<fmt:message key="subject"/>"/>
        <br/>
        <br/>
        <textarea rows='10' cols="50" name="mailMessage"></textarea>
        <br/>
        <input type='submit' value="<fmt:message key="send"/>"/>
    </form>
    
</div>
