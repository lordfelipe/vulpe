<%@include file="/WEB-INF/protected-jsp/commons/taglibs.jsp" %>
<%@ attribute name="showSize" required="false" rtexprvalue="true" type="java.lang.Boolean" %>
<c:if test="${showSize}">
${fn:length(entities)}
</c:if>