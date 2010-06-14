<c:if test="${empty saveInSession}">
	<c:set var="saveInSession" value="${false}"/>
</c:if>

<c:if test="${empty expireInSession}">
	<c:set var="expireInSession" value="${false}"/>
</c:if>

<c:if test="${empty paragraph}">
	<c:set var="paragraph" value="true"/>
</c:if>

<c:if test="${empty style}">
	<c:set var="style" value=""/>
</c:if>

<c:set var="styleClass" value="${styleClass} focused"/>

<c:if test="${required}">
	<c:set var="styleClass" value="${styleClass} vulpeRequired"/>
</c:if>

<c:if test="${not empty vulpeShowAsMobile}">
	<c:set var="breakLabel" value="${true}"/>
</c:if>

<c:if test="${empty targetName}">
	<c:if test="${empty targetConfig}">
		<c:set var="targetName" value="entity"/>
	</c:if>
	<c:if test="${not empty targetConfig}">
		<c:set var="targetNameEL" value="${'${'}${targetConfig.baseName}_status.index${'}'}"/>
		<c:set var="targetName" value="${targetConfigPropertyName}[${util:eval(pageContext, targetNameEL)}]"/>
	</c:if>
</c:if>

<c:if test="${empty targetValue}">
	<c:if test="${empty targetConfig}">
		<c:set var="targetValueEL" value="${'${'}${targetName}${'}'}"/>
		<c:set var="targetValue" value="${util:eval(pageContext, targetValueEL)}"/>
	</c:if>
	<c:if test="${not empty targetConfig}">
		<c:set var="targetValueEL" value="${'${'}${targetConfig.baseName}_item${'}'}"/>
		<c:set var="targetValue" value="${util:eval(pageContext, targetValueEL)}"/>
	</c:if>
</c:if>

<c:if test="${not empty property && empty name}">
	<c:set var="name" value="${targetName}.${property}"/>
</c:if>

<c:if test="${empty elementId}">
	<c:set var="prepareName" value="${fn:replace(name, '[', ':')}"/>
	<c:set var="prepareName" value="${fn:replace(prepareName, '].', ':')}"/>
	<c:set var="elementId" value="${controllerConfig.formName}_${prepareName}"/>
</c:if>

<c:if test="${not empty property && empty value}">
	<c:set var="valueEL" value="${'${'}targetValue.${property}${'}'}"/>
	<c:set var="value" value="${util:eval(pageContext, valueEL)}"/>
</c:if>

<c:if test="${paragraph}">
<p>
</c:if>

<c:if test="${not empty labelKey}">
	<fmt:message key="${labelKey}" var="label"/>
	<c:if test="${empty titleKey}">
		<c:set var="title" value="${label}"/>
	</c:if>
	<c:if test="${empty labelClass}">
		<c:set var="labelClass" value="vulpeBlockLabel"/>
	</c:if>
	<label id="${elementId}_label" for="${elementId}" style="${labelStyle}" class="${labelClass}">${label}</label>
	<c:if test="${breakLabel}">
		<br/>
	</c:if>
</c:if>

<c:if test="${saveInSession}">
	<c:set var="valueInSession" value="${util:saveInSession(name, value, expireInSession)}"/>
</c:if>

<c:if test="${not empty value}">
	<c:set var="value" value="${util:toString(value)}"/>
</c:if>

<c:if test="${onlyToSee}">
	<c:set var="showAsText" value="${true}"/>
</c:if>