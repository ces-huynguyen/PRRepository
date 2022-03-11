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
		"com.liferay.portlet.display-category=category.AdminMonitor",
		"com.liferay.portlet.header-portlet-css=/css/main.css",
		"com.liferay.portlet.instanceable=false",
		"javax.portlet.display-name=AdminMonitor",
		"javax.portlet.init-param.template-path=/monitor/",
		"javax.portlet.init-param.view-template=/monitor/view.jsp",
		"javax.portlet.name=" + RegistrationPortletKeys.ADMIN_MONITOR,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class AdminMonitorPortlet extends MVCPortlet {
	
}