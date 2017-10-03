<c:set var='view' value='/rankings' scope='session' />
<div class="main">
    <c:choose>
        <c:when test="${selectedTag==null}">
            <h1><fmt:message key="selectTag"/></h1>
        </c:when>
        <c:otherwise>
            <h1><fmt:message key="rankingsFor"/> '${selectedTag.name}'</h1>
        </c:otherwise>
    </c:choose>
    <table>
        <tr>
            <th><fmt:message key="rank"/></th>
            <th><fmt:message key="player"/></th>
            <th><fmt:message key="bestScore"/></th>
            <th><fmt:message key="timeDate"/></th>
        </tr>
        <c:forEach var="rank" items="${selectedScores}" varStatus="iter">
            <tr>
                <td>${iter.index+1}</td><td>${rank.player.pseudo}</td><td>${rank.bestScore}</td><td>${rank.dateFormatted}</td>
            </tr>
        </c:forEach>
    </table>
</div>
<div class="aside">
    <h3><fmt:message key="tagsList"/></h3>
    <c:forEach var="tagCategory" items="${tagsCategories}">
        <c:choose>
            <c:when test="${tagCategoriesWrapper.getIsSelected(tagCategory.tagCategoryId)}">
                <p>
                    <a class="tag" href="selectTagCategory?${tagCategory.tagCategoryId}">
                        <strong>&#9660; <fmt:message key="${tagCategory.tagCategoryName}"/></strong>
                    </a>
                </p>
                <c:forEach var="tag" items="${tagCategory.tagCollection}">
                    <p><a class="tag" href="rankings?${tag.tagId}">${tag.name}</a></p>
                </c:forEach>
            </c:when>
            <c:otherwise>
                <p>
                    <a class="tag" href="selectTagCategory?${tagCategory.tagCategoryId}">
                        <strong>&#9658; <fmt:message key="${tagCategory.tagCategoryName}"/></strong>
                    </a>
                </p>
            </c:otherwise>
        </c:choose>
        
    </c:forEach>
</div>