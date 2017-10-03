<%-- 
    Document   : game
    Created on : 1 févr. 2016, 18:22:34
    Author     : Melkior
--%>
<c:set var='view' value='/game' scope='session' />
<div class="main">
    
    <p><img class="img-responsive" src="${initParam.questionsImagesPath}${quiz.currentPictureFileName}.jpg" alt=""></p>
    
    <p class="event">${quiz.currentEvent}</p>

    <c:choose>
        <c:when test="${answerSubmitted}">
            <p><strong>${quiz.currentAnswerResult}</strong></p>
            <p>
                <a href="game?nextQuestion" class="btn">
                    <c:choose>
                        <c:when test="${currentQuizSize!=-1}">
                            <fmt:message key="nextQuestion"/>
                        </c:when>
                        <c:otherwise>
                            <fmt:message key="endQuiz"/>
                        </c:otherwise>
                    </c:choose>

                </a>
            </p>
        </c:when>
        <c:otherwise>
            <form action="submitQuizAnswer" method="post">
                <fmt:message key="answer"/> (<fmt:message key="${quiz.currentQuestionType}"/>) : <input type="text" name="answer"/>
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
    
    <p><fmt:message key="currentQuestion"/> : ${quiz.nbQuestions}</p>
    <p><fmt:message key="currentScore"/> : ${quiz.score}</p>
    
</div>
