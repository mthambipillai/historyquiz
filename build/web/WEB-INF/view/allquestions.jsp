<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- 
    Document   : allquestions
    Created on : 28 janv. 2016, 18:23:32
    Author     : Melkior
--%>
<c:set var='view' value='/allquestions' scope='session' />
<div class="main">
    <c:choose>
        <c:when test="${selectedTag==null}">
            <h1><fmt:message key="allQuestions"/></h1>
        </c:when>
        <c:otherwise>
            <h1><fmt:message key="allQuestionsConcerning"/> '${selectedTag.name}'</h1>
        </c:otherwise>
    </c:choose>
    
    <table>
        <tr>
            <th><fmt:message key="type"/></th>
            <th><fmt:message key="event"/></th>
            <th><fmt:message key="answer"/></th>
            <th><fmt:message key="otherTags"/></th>
        </tr>
        <c:forEach var="date" items="${selectedDates}">
            <tr>
                <td><fmt:message key="date"/></td><td>${date.event}</td><td>${date.year}</td>
                <td>
                    <c:forEach var="tag" items="${date.tagCollection}">
                        <c:choose>
                            <c:when test="${tag.name!=selectedTag.name}">
                                '${tag.name}'
                            </c:when>
                        </c:choose>
                    </c:forEach>
                </td>
            </tr>
        </c:forEach>
        <c:forEach var="period" items="${selectedPeriods}">
            <tr>
                <td><fmt:message key="period"/></td><td>${period.event}</td><td>${period.beginYear}-${period.endYear}</td>
                <td>
                    <c:forEach var="tag" items="${period.tagCollection}">
                        <c:choose>
                            <c:when test="${tag.name!=selectedTag.name}">
                                '${tag.name}'
                            </c:when>
                        </c:choose>
                    </c:forEach>
                </td>
            </tr>
        </c:forEach>
        <c:forEach var="exactDate" items="${selectedExactDates}">
            <tr>
                <td><fmt:message key="exactDate"/></td><td>${exactDate.event}</td><td>${exactDate.day}.${exactDate.month}.${exactDate.year}</td>
                <td>
                    <c:forEach var="tag" items="${exactDate.tagCollection}">
                        <c:choose>
                            <c:when test="${tag.name!=selectedTag.name}">
                                '${tag.name}'
                            </c:when>
                        </c:choose>
                    </c:forEach>
                </td>
            </tr>
        </c:forEach>
        <c:forEach var="nameQuestion" items="${selectedNames}">
            <tr>
                <td><fmt:message key="name"/></td><td>${nameQuestion.event}</td><td>${nameQuestion.name}</td>
                <td>
                    <c:forEach var="tag" items="${nameQuestion.tagCollection}">
                        <c:choose>
                            <c:when test="${tag.name!=selectedTag.name}">
                                '${tag.name}'
                            </c:when>
                        </c:choose>
                    </c:forEach>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
<div class="aside">
    <h3><fmt:message key="tagsList"/></h3>
    <p><a class="tag" href="allquestions?0"><fmt:message key="all"/></a></p>
    <c:forEach var="tagCategory" items="${tagsCategories}">
        <c:choose>
            <c:when test="${tagCategoriesWrapper.getIsSelected(tagCategory.tagCategoryId)}">
                <p>
                    <a class="tag" href="selectTagCategory?${tagCategory.tagCategoryId}">
                        <strong>&#9660; <fmt:message key="${tagCategory.tagCategoryName}"/></strong>
                    </a>
                </p>
                <c:forEach var="tag" items="${tagCategory.tagCollection}">
                    <p><a class="tag" href="allquestions?${tag.tagId}">${tag.name}</a></p>
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


