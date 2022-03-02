package com.amf.web.registration.utils;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.Contact;
import com.liferay.portal.kernel.model.ListType;
import com.liferay.portal.kernel.model.Phone;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.RoleConstants;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.security.auth.PrincipalThreadLocal;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.PermissionCheckerFactoryUtil;
import com.liferay.portal.kernel.security.permission.PermissionThreadLocal;
import com.liferay.portal.kernel.service.ListTypeServiceUtil;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.users.admin.kernel.util.UsersAdminUtil;

import java.util.List;

/**
 * Service API for adding/updating registrants.
 *
 * @author Neil Griffin
 */
public class RegistrantServiceUtil {

	public static PermissionChecker getAdministratorPermissionChecker(long companyId)
			throws PortalException, SystemException {
		PermissionChecker administratorPermissionChecker = null;
		Role administratorRole = RoleLocalServiceUtil.getRole(companyId, RoleConstants.ADMINISTRATOR);
		List<User> administratorUsers = UserLocalServiceUtil.getRoleUsers(administratorRole.getRoleId());

		if ((administratorUsers != null) && (administratorUsers.size() > 0)) {

			User administratorUser = administratorUsers.get(0);

			try {
				administratorPermissionChecker = PermissionCheckerFactoryUtil.getPermissionCheckerFactory()
						.create(administratorUser);
			} catch (Exception e) {
				throw new SystemException(e.getMessage(), e);
			}
		} else {
			throw new SystemException("Unable to find a user with the Administrator role! Impossible!");
		}

		return administratorPermissionChecker;
	}

}