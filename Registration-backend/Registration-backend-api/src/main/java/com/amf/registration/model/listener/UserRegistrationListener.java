package com.amf.registration.model.listener;

import com.amf.registration.service.RegistrationLogLocalServiceUtil;
import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.BaseModelListener;
import com.liferay.portal.kernel.model.ModelListener;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;

import java.util.Date;

import org.osgi.service.component.annotations.Component;

@Component(immediate = true, service = ModelListener.class)
public class UserRegistrationListener extends BaseModelListener<User>{
	private final String DEFAULT_REGISTRATION_USER_IP = "0.0.0.0";
	private	final String REGISTRATION = "Registration";
	@Override
	public void onAfterAddAssociation(Object classPK, String associationClassName, Object associationClassPK)
			throws ModelListenerException {
		Long userId = (Long)classPK;
		try {
			User user = UserLocalServiceUtil.getUser(userId);
			Date now = new Date();
			String screenNameFormat = user.getScreenName() + "(" + userId + ")";
			
			RegistrationLogLocalServiceUtil.addRegistrationLog(user.getGroupId(), user.getCompanyId(), userId, screenNameFormat, now, now, REGISTRATION, DEFAULT_REGISTRATION_USER_IP);
		} catch (PortalException e) {
			e.printStackTrace();
		}		
	
	}
}
