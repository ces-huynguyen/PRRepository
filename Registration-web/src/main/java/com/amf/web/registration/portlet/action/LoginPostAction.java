package com.amf.web.registration.portlet.action;

import com.amf.registration.service.RegistrationLogLocalServiceUtil;
import com.liferay.portal.kernel.events.ActionException;
import com.liferay.portal.kernel.events.LifecycleAction;
import com.liferay.portal.kernel.events.LifecycleEvent;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.util.PortalUtil;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;

@Component(immediate = true, property = { "key=login.events.post" }, service = LifecycleAction.class)
public class LoginPostAction implements LifecycleAction {

	@Override
	public void processLifecycleEvent(LifecycleEvent lifecycleEvent) throws ActionException {
		
		HttpServletRequest request = lifecycleEvent.getRequest();
		try {
			long userId = PortalUtil.getUser(request).getUserId();

			long groupId = PortalUtil.getUser(request).getGroupId();
			String userName = PortalUtil.getUser(request).getScreenName() + "(" + userId + ")";
			String clientIpAddress =request.getRemoteAddr();
			long companyId = PortalUtil.getCompanyId(request);

			RegistrationLogLocalServiceUtil.addRegistrationLog(groupId, companyId, userId, userName, 
					new Date(), new Date(),"Login",clientIpAddress);

		} catch (PortalException e) {
			e.printStackTrace();			
		}
	}
	
}
