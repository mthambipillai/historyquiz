<%-- 
    Document   : game_selection
    Created on : 1 févr. 2016, 20:50:52
    Author     : Melkior
--%>
<script>
$(document).ready(function(){
    $('[data-toggle="tooltip"]').tooltip(); 
});
</script>
<c:set var='view' value='/game_selection' scope='session' />
<c:choose>
    <c:when test="${emptySelection}">
        <script>alert("<fmt:message key="noQuestionsAlert"/>");</script>
    </c:when>
</c:choose>
<div class="main">
    <div class="selection_form">
        <form action="addTag" method='post'>
            <select name='tagSelection'>
                <option><fmt:message key="all"/></option>
                <c:forEach var="tag" items="${tags}">
                    <option>${tag.name}</option>
                </c:forEach>
            </select>
            <input type="submit" value="<fmt:message key="add"/>"/>
        </form>
    </div>
    <p><a href="game" class="btn">
            <span title="<fmt:message key="playExplanation"/>" ><fmt:message key="startPlay"/></span>
        </a></p>
    <p><a href="training" class="btn">
            <span title="<fmt:message key="trainExplanation"/>" ><fmt:message key="startTrain"/></span>
        </a></p>
</div>
<div class="aside">
    <h3><fmt:message key="selectedTags"/></h3>
    <c:if test="${!empty gameSelectedTags}">
        <p><a href="unselectAllTags"><fmt:message key="unselectAll"/></a></p>
    </c:if>
    <c:forEach var="tag" items="${gameSelectedTags}">
        <div class="selected_tag">
            <ul class="list-inline">
                <li>
                    <span>${tag.name}</span>
                </li>
                <li style="float:right">
                    <form action="removeTag" method="post">
                        <input type="hidden" name="tagName" value="${tag.name}"/>
                        <input type="submit" value="X"/>
                    </form>
                </li>
            </ul>
            
              
        </div>
                    
    </c:forEach>
</div>
