<%@ include file="/init.jsp"%>
<div class="container-fluid-1280">
<c:if test="${allowSearch}">
	<portlet:actionURL var="searchUser"
		name="<%=MVCCommandNames.SEARCH_USER%>">
	</portlet:actionURL>
	<aui:form action="${searchUser}" name="fm" method="post"
		validateOnBlur="<%=true %>">
		<%--misc --%>
		<liferay-ui:error exception="<%=AddressZipException.class%>"
			message="please-enter-a-valid-postal-code" />
		<aui:input name="zipCode" label="enter-us-zip">
			<aui:validator name="required" />
		</aui:input>
		<aui:button-row>
			<aui:button cssClass="btn btn-primary" type="submit" value="search"></aui:button>
		</aui:button-row>
	</aui:form>
</c:if>
<c:if test="${!allowSearch}">
	<liferay-ui:message key="search-feature-is-only-available-for-tec"/>
</c:if>
</div>