<%@ include file="/init.jsp"%>
<c:if test="${allowSearch}">
	<%
		String eventZipCodeParam = (String) renderRequest.getParameter("eventZipCodeParam");
	if (eventZipCodeParam == null){
		eventZipCodeParam = "No Zipcode";
		
	}
		
	%>
	<div class="container-fluid-1280">
		<liferay-ui:message key="search-results-for-x"
			arguments="<%=eventZipCodeParam%>" />
		<liferay-ui:search-container delta="5" deltaConfigurable="true"
			emptyResultsMessage="no-results-found" iteratorURL="${iteratorURL}"
			id="searchResults" total="${searchCount}">
			<liferay-ui:search-container-results results="${searchResults}" />
			<liferay-ui:search-container-row
				className="com.liferay.portal.kernel.model.User"
				keyProperty="userId" modelVar="entry">
				
				<liferay-ui:search-container-column-text 
					value="${entry.firstName} . ${entry.lastName.charAt(0)}"
					name="full-name"/>			
				
				<liferay-ui:search-container-column-text property="screenName"
					name="screen-name" />
				<liferay-ui:search-container-column-text property="emailAddress"
					name="email-address" />
			</liferay-ui:search-container-row>
			<%-- Iterator / Paging --%>
			<liferay-ui:search-iterator markupView="lexicon" />

		</liferay-ui:search-container>
	</div>

</c:if>
<c:if test="${!allowSearch}">
	<div class="container-fluid-1280">

		<liferay-ui:message key="search-feature-is-only-available-for-tec" />
	</div>
</c:if>
