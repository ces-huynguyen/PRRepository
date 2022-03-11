package com.amf.web.registration.portlet;

import com.amf.web.registration.constants.RegistrationPortletKeys;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

import javax.portlet.Portlet;

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
		"javax.portlet.display-name=Search",
		"javax.portlet.init-param.template-path=/search/",
		"javax.portlet.init-param.view-template=/search/search.jsp",
		"javax.portlet.name=" + RegistrationPortletKeys.SEARCH,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user",
		"javax.portlet.supported-publishing-event=eventZipCodeParam;http://amf.com/events"

	},
	service = Portlet.class
)
public class SearchPortlet extends MVCPortlet {
	
}