<%@ include file="/init.jsp"%>

<c:if test="<%=themeDisplay.isSignedIn()%>">
	<portlet:renderURL var="viewUserActivitiesEventURL">
		<portlet:param name="mvcRenderCommandName"
			value="<%=MVCCommandNames.VIEW_USER_ACTIVITIES%>" />
		<portlet:param name="eventFilter" value="${eventFilter}"></portlet:param>
	</portlet:renderURL>

	<div class="container-fluid-1280">
		<liferay-ui:tabs names="All,Registration,Login" refresh="<%=true %>"
			url="${viewUserActivitiesEventURL}" param="eventFilter"
			tabsValues="All,Registration,Login">
			<liferay-ui:section>
				<%@include file="/monitor/entry_search_columns.jspf"%>

			</liferay-ui:section>
			<liferay-ui:section>
				<%@include file="/monitor/entry_search_columns.jspf"%>
			</liferay-ui:section>
			<liferay-ui:section>
				<%@include file="/monitor/entry_search_columns.jspf"%>
			</liferay-ui:section>
		</liferay-ui:tabs>
	</div>
</c:if>
<c:if test="<%=!themeDisplay.isSignedIn()%>">
	<liferay-ui:message key="user-not-logged-in" />
</c:if>