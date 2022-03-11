<%@ include file="/init.jsp"%>

<c:if test="<%=themeDisplay.isSignedIn()%>">
	<liferay-ui:message key="user-already-registered-x" arguments="<%= themeDisplay.getUser().getEmailAddress()%>"/>
</c:if>
<c:if test="<%=!themeDisplay.isSignedIn()%>">
	<%@ include file="/components/registration_form.jspf"%>
</c:if>