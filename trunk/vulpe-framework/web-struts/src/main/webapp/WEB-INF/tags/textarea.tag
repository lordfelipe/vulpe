<%@include file="/WEB-INF/protected-jsp/commons/tags/tagAttributes.jsp" %>
<%@ attribute name="cols" required="false" rtexprvalue="true" %>
<%@ attribute name="property" required="true" rtexprvalue="true" %>
<%@ attribute name="readonly" required="false" rtexprvalue="true" %>
<%@ attribute name="rows" required="false" rtexprvalue="true" %>

<%@include file="/WEB-INF/protected-jsp/commons/tags/headerTag.jsp" %>

<c:if test="${show eq true}">
	<%@include file="/WEB-INF/protected-jsp/commons/tags/beginTag.jsp" %>
	
	<c:if test="${not empty property && util:isFieldInValidator(targetValue, property)}">
		<c:set var="onblur" value="validate${fn:toUpperCase(fn:substring(property, 0, 1))}${fn:substring(property, 1, -1)}(); ${onblur}"/>
	</c:if>
	
	<c:choose>
		<c:when test="${showAsText}">
			<s:label theme="simple" name="${name}" accesskey="${accesskey}" disabled="${disabled}" onblur="${onblur}" onchange="${onchange}" onclick="${onclick}" ondblclick="${ondblclick}" onfocus="${onfocus}" onkeydown="${onkeydown}" onkeypress="${onkeypress}" onkeyup="${onkeyup}" onmousedown="${onmousedown}" onmousemove="${onmousemove}" onmouseout="${onmouseout}" onmouseover="${onmouseover}" onmouseup="${onmouseup}" cssStyle="${style}" cssClass="simpleLabel ${styleClass}" id="${elementId}" tabindex="${tabindex}" title="${title}" value="${value}"/>
		</c:when>
		<c:otherwise>
			<s:textarea theme="simple" name="${name}" accesskey="${accesskey}" cols="${cols}" disabled="${disabled}" onblur="${onblur}" onchange="${onchange}" onclick="${onclick}" ondblclick="${ondblclick}" onfocus="${onfocus}" onkeydown="${onkeydown}" onkeypress="${onkeypress}" onkeyup="${onkeyup}" onmousedown="${onmousedown}" onmousemove="${onmousemove}" onmouseout="${onmouseout}" onmouseover="${onmouseover}" onmouseup="${onmouseup}" id="${elementId}" readonly="${readonly}" rows="${rows}" cssStyle="${style}" cssClass="${styleClass}" tabindex="${tabindex}" title="${title}" value="${value}"/>
		</c:otherwise>
	</c:choose>
	<jsp:doBody/>
	<%@include file="/WEB-INF/protected-jsp/commons/tags/endTag.jsp" %>
</c:if>