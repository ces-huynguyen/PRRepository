package com.amf.web.registration.portlet;

import com.amf.web.registration.constants.RegistrationPortletKeys;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

import javax.portlet.Event;
import javax.portlet.EventRequest;
import javax.portlet.EventResponse;
import javax.portlet.Portlet;
import javax.portlet.ProcessEvent;

import org.osgi.service.component.annotations.Component;

/**
 * @author ces-user
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.SearchPortlet",
		"com.liferay.portlet.header-portlet-css=/css/main.css",
		"com.liferay.portlet.instanceable=false",
		"javax.portlet.display-name=SearchResults",
		"javax.portlet.init-param.template-path=/search-results/",
		"javax.portlet.init-param.view-template=/search-results/search-results.jsp",
		"javax.portlet.name=" + RegistrationPortletKeys.SEARCH_RESULTS,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user",
		"javax.portlet.supported-processing-event=eventZipCodeParam;http://amf.com/events"

	},
	service = Portlet.class
)
public class SearchResultsPortlet extends MVCPortlet {

    @ProcessEvent(qname = "{http://amf.com/events}eventZipCodeParam")
    public void myEvent(EventRequest request, EventResponse response) {
        Event event = request.getEvent();
        //Get data from the event
        String searchTerm = (String) event.getValue();
        //Set the text in response to display in UI
        response.setRenderParameter("eventZipCodeParam", searchTerm);
    }
}