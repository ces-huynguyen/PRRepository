package com.amf.web.registration.internal.security.permission.resource;

import com.amf.registration.constants.RegistrationConstants;
import com.amf.registration.model.RegistrationLog;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(immediate = true, service = RegistrationLogPermission.class) // entity permission checking
public class RegistrationLogPermission {

	public static boolean contains(PermissionChecker permissionChecker, RegistrationLog registrationLog,
			String actionId) throws PortalException {
		return _registrationLogModelResourcePermission.contains(permissionChecker, registrationLog, actionId);
	}

	public static boolean contains(PermissionChecker permissionChecker, long legistrationLogId, String actionId)
			throws PortalException {
		return _registrationLogModelResourcePermission.contains(permissionChecker, legistrationLogId, actionId);
	}
	public static boolean contains(PermissionChecker permissionChecker,
			String actionId) throws PortalException {
		
		return _registrationLogModelResourcePermission.contains(permissionChecker, null, actionId);
	}

	@Reference(target = "(model.class.name=" + RegistrationConstants.RESOURCE_MODEL + ")", unbind = "-")
	protected void setEntryModelPermission(ModelResourcePermission<RegistrationLog> modelResourcePermission) {
		_registrationLogModelResourcePermission = modelResourcePermission;
	}

	private static ModelResourcePermission<RegistrationLog> _registrationLogModelResourcePermission;
}
