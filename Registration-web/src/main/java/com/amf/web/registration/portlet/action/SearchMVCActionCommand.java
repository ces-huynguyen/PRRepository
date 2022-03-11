package com.amf.web.registration.portlet.action;

import com.amf.web.registration.constants.MVCCommandNames;
import com.amf.web.registration.constants.RegistrationPortletKeys;
import com.amf.web.registration.internal.security.permission.resource.RegistrationLogToplevelPermission;
import com.amf.web.registration.internal.security.permission.resource.SearchUserToplevelPermission;
import com.liferay.portal.kernel.exception.AddressZipException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.xml.namespace.QName;

import org.osgi.service.component.annotations.Component;

@Component(immediate = true, property = { "javax.portlet.name=" + RegistrationPortletKeys.SEARCH,
		"mvc.command.name=" + MVCCommandNames.SEARCH_USER }, service = MVCActionCommand.class)
public class SearchMVCActionCommand extends BaseMVCActionCommand {

	@Override
	protected void doProcessAction(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {
		
		String zipCode = ParamUtil.getString(actionRequest, "zipCode", "");
		if (zipCode.length() == 5 && Validator.isNumber(zipCode)) {
			process(zipCode, actionResponse);	
		}else {
			throw new AddressZipException("Invalid address zip");
		}
		
	}

	public void process(String zipCode , ActionResponse response) {

		/**
		 * Refer portlet.xml
		 */
		QName qName = new QName("http://amf.com/events", "eventZipCodeParam");
		response.setEvent(qName, zipCode);
	}
}
