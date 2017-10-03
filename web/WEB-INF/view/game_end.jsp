<%-- 
    Document   : game_end
    Created on : 7 févr. 2016, 20:00:29
    Author     : Melkior
--%>

<script>
$(document).ready(function(){
    $('[data-toggle="tooltip"]').tooltip(); 
});
</script>
<c:set var='view' value='/game_end' scope='session' />
<div class="main">
    
    <p><strong><fmt:message key="finish"/> ${quiz.score}.</strong></p>
    <p><strong><fmt:message key="tagScores"/> : <br/> ${quiz.tagsScoresString}</strong></p>
    <div class="game_end_btns_container">
        <p class="game_end_btn"><a href="game_end?tryAgain" class="btn"><fmt:message key="tryAgain"/></a></p>
        <c:choose>
            <c:when test="${loggedPlayer==null}">
                <p  class="game_end_btn">
                    <a href="#" class="disabledbtn">
                        <span title="<fmt:message key="mustBeLogged"/>" ><fmt:message key="saveScore"/></span>
                    </a>
                </p>
            </c:when>
            <c:otherwise>
                <p class="game_end_btn"><a href="game_end?saveScore" class="btn"><fmt:message key="saveScore"/></a></p>
            </c:otherwise>
        </c:choose>
        
    </div>
    
</div>
<div class="aside">
 
</div>
