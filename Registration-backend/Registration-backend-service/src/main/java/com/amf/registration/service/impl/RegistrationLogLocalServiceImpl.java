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

import com.amf.registration.model.RegistrationLog;
import com.amf.registration.service.base.RegistrationLogLocalServiceBaseImpl;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.Junction;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;

import java.util.Date;
import java.util.List;

import org.osgi.service.component.annotations.Component;

/**
 * @author Brian Wing Shun Chan
 */
@Component(property = "model.class.name=com.amf.registration.model.RegistrationLog", service = AopService.class)
public class RegistrationLogLocalServiceImpl extends RegistrationLogLocalServiceBaseImpl {

	public RegistrationLog addRegistrationLog(long groupId, long companyId, long userId, String userName, Date createDate,
			Date modifiedDate, String eventType, String ipAddress) throws PortalException {
		long logId = counterLocalService.increment(RegistrationLog.class.getName());
		RegistrationLog registrationLog = createRegistrationLog(logId);

		registrationLog.setGroupId(groupId);
		registrationLog.setCompanyId(companyId);
		registrationLog.setUserName(userName);
		registrationLog.setCreateDate(createDate);
		registrationLog.setModifiedDate(modifiedDate);
		registrationLog.setIpAddress(ipAddress);
		registrationLog.setEventType(eventType);
		registrationLog.setUserId(userId);
		super.addRegistrationLog(registrationLog);

		boolean portletActions = false;
		boolean addGroupPermissions = true;
		boolean addGuestPermissions = true;
		resourceLocalService.addResources(companyId, groupId, userId, RegistrationLog.class.getName(),
				registrationLog.getRegistrationLogId(), portletActions, addGroupPermissions, addGuestPermissions);
		
		return registrationLog;
	}

	// get all by User name
	public List<RegistrationLog> getRegistrationLogsByUser(long userId, int start, int end) {

		return registrationLogPersistence.findByUserId(userId, start, end);
	}

	// filter by eventtype
	public List<RegistrationLog> getRegistrationLogsByEventType(String eventType, int start, int end) {

		return registrationLogPersistence.findByEventType(eventType, start, end);
	}

	// filter by eventtype & username
	public List<RegistrationLog> getRegistrationLogsByUserEventType(long userId, String eventType, int start,
			int end) {
		DynamicQuery dynamicQuery = _getKeywordSearchDynamicQuery(userId, eventType);
		return super.dynamicQuery(dynamicQuery, start, end);
	}

	public long getRegistrationLogsCountByEventType(String eventType) {

		return registrationLogPersistence.countByEventType(eventType);
	}

	// count filter by eventtype & username
	public long getRegistrationLogsCountByUserEventType(long userId, String eventType) {
		DynamicQuery dynamicQuery = _getKeywordSearchDynamicQuery(userId, eventType);
		return super.dynamicQueryCount(dynamicQuery);
	}

	public long getRegistrationLogsCountByUser(long userId) {
		return registrationLogPersistence.countByUserId(userId);
	}

	private DynamicQuery _getKeywordSearchDynamicQuery(long userId, String eventType) {
		DynamicQuery dynamicQuery = dynamicQuery().add(RestrictionsFactoryUtil.eq("eventType", eventType));
		if (userId != 0) {
			Junction junction = RestrictionsFactoryUtil.conjunction(); 
			junction.add(RestrictionsFactoryUtil.eq("userId",  userId));
			dynamicQuery.add(junction);
		}

		return dynamicQuery;
	}	
}