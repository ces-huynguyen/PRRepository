/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.amf.registration.service.impl;

import com.amf.registration.constants.RegistrationConstants;
import com.amf.registration.model.RegistrationLog;
import com.amf.registration.service.base.RegistrationLogServiceBaseImpl;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermission;

import java.util.Date;
import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.osgi.service.component.annotations.ReferencePolicyOption;

/**
 * @author Brian Wing Shun Chan
 */
@Component(property = { "json.web.service.context.name=registration",
		"json.web.service.context.path=RegistrationLog" }, service = AopService.class)
public class RegistrationLogServiceImpl extends RegistrationLogServiceBaseImpl {

	public RegistrationLog addRegistrationLog(long groupId, long companyId, long userId, String userName, Date createDate,
			Date modifiedDate, String eventType, String ipAddress) throws PortalException {

		return registrationLogLocalService.addRegistrationLog(groupId, companyId, userId, userName, createDate, modifiedDate,
				eventType, ipAddress);
	}

	public List<RegistrationLog> getRegistrationLogs(int start, int end) {
		
		return registrationLogLocalService.getRegistrationLogs(start, end);
	}

	// get all by User name
	public List<RegistrationLog> getRegistrationLogsByUser(long userId, int start, int end) {

		return registrationLogLocalService.getRegistrationLogsByUser(userId, start, end);
	}

	// filter by eventtype
	public List<RegistrationLog> getRegistrationLogsByEventType(String eventType, int start, int end) {

		return registrationLogLocalService.getRegistrationLogsByEventType(eventType, start, end);
	}

	// filter by eventtype & username
	public List<RegistrationLog> getRegistrationLogsByUserEventType(long userId, String eventType, int start,
			int end) {

		return registrationLogLocalService.getRegistrationLogsByUserEventType(userId, eventType, start, end);
	}

	//count all by event type
	public long getRegistrationLogsCountByEventType(String eventType) {

		return registrationLogLocalService.getRegistrationLogsCountByEventType(eventType);
	}

	// count & filter by eventtype & username
	public long getRegistrationLogsCountByUserEventType(long userId, String eventType) {
		return registrationLogLocalService.getRegistrationLogsCountByUserEventType(userId, eventType);
	}

	// count all records
	public int getRegistrationLogsCount() {
		return registrationLogLocalService.getRegistrationLogsCount();
	}

	//count all by username
	public long getRegistrationLogsCountByUser(long userId) {
		return registrationLogLocalService.getRegistrationLogsCountByUser(userId);
	}
	
	
	@Reference(policy=ReferencePolicy.DYNAMIC, policyOption = ReferencePolicyOption.GREEDY,
			target="(model.class.name=" + RegistrationConstants.RESOURCE_MODEL+ ")")
	private volatile ModelResourcePermission<RegistrationLog> _assignmentModelResourcePermission;
	
	
	@Reference(policy = ReferencePolicy.DYNAMIC,
			 policyOption = ReferencePolicyOption.GREEDY,
			 target = "(resource.name=" + RegistrationConstants.RESOURCE_NAME + ")")
	private volatile PortletResourcePermission _portletResourcePermission;
}