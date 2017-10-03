<%-- 
    Document   : game
    Created on : 1 févr. 2016, 18:22:34
    Author     : Melkior
--%>
<c:set var='view' value='/training' scope='session' />
<div class="main">
    
    <p><img class="img-responsive" src="${initParam.questionsImagesPath}${training.currentPictureFileName}.jpg" alt=""></p>
    
    <p class="event">${training.currentEvent}</p>

    <c:choose>
        <c:when test="${answerSubmitted}">
            <p><strong>${training.currentAnswerResult}</strong></p>
            <p>
                <a href="training?nextQuestion" class="btn">
                    <fmt:message key="nextQuestion"/>
                </a>
            </p>
        </c:when>
        <c:otherwise>
            <form action="submitTrainingAnswer" method="post">
                <fmt:message key="answer"/> (<fmt:message key="${training.currentQuestionType}"/>) : <input type="text" name="answer"/>
                <input type="submit" value="<fmt:message key="validate"/>"/>
            </form>
        </c:otherwise>
    </c:choose>
</div>
<div class="aside">
    <fmt:message key="selectedTags"/> :
    <ul>
        <c:forEach var="tag" items="${gameSelectedTags}">
            <li>${tag.name}</li>
        </c:forEach>
    </ul>
</div>