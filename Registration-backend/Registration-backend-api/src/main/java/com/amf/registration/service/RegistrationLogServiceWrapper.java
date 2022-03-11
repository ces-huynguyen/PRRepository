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

package com.amf.registration.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link RegistrationLogService}.
 *
 * @author Brian Wing Shun Chan
 * @see RegistrationLogService
 * @generated
 */
public class RegistrationLogServiceWrapper
	implements RegistrationLogService, ServiceWrapper<RegistrationLogService> {

	public RegistrationLogServiceWrapper(
		RegistrationLogService registrationLogService) {

		_registrationLogService = registrationLogService;
	}

	@Override
	public com.amf.registration.model.RegistrationLog addRegistrationLog(
			long groupId, long companyId, long userId, String userName,
			java.util.Date createDate, java.util.Date modifiedDate,
			String eventType, String ipAddress)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _registrationLogService.addRegistrationLog(
			groupId, companyId, userId, userName, createDate, modifiedDate,
			eventType, ipAddress);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _registrationLogService.getOSGiServiceIdentifier();
	}

	@Override
	public java.util.List<com.amf.registration.model.RegistrationLog>
		getRegistrationLogs(int start, int end) {

		return _registrationLogService.getRegistrationLogs(start, end);
	}

	@Override
	public java.util.List<com.amf.registration.model.RegistrationLog>
		getRegistrationLogsByEventType(String eventType, int start, int end) {

		return _registrationLogService.getRegistrationLogsByEventType(
			eventType, start, end);
	}

	@Override
	public java.util.List<com.amf.registration.model.RegistrationLog>
		getRegistrationLogsByUser(long userId, int start, int end) {

		return _registrationLogService.getRegistrationLogsByUser(
			userId, start, end);
	}

	@Override
	public java.util.List<com.amf.registration.model.RegistrationLog>
		getRegistrationLogsByUserEventType(
			long userId, String eventType, int start, int end) {

		return _registrationLogService.getRegistrationLogsByUserEventType(
			userId, eventType, start, end);
	}

	@Override
	public int getRegistrationLogsCount() {
		return _registrationLogService.getRegistrationLogsCount();
	}

	@Override
	public long getRegistrationLogsCountByEventType(String eventType) {
		return _registrationLogService.getRegistrationLogsCountByEventType(
			eventType);
	}

	@Override
	public long getRegistrationLogsCountByUser(long userId) {
		return _registrationLogService.getRegistrationLogsCountByUser(userId);
	}

	@Override
	public long getRegistrationLogsCountByUserEventType(
		long userId, String eventType) {

		return _registrationLogService.getRegistrationLogsCountByUserEventType(
			userId, eventType);
	}

	@Override
	public RegistrationLogService getWrappedService() {
		return _registrationLogService;
	}

	@Override
	public void setWrappedService(
		RegistrationLogService registrationLogService) {

		_registrationLogService = registrationLogService;
	}

	private RegistrationLogService _registrationLogService;

}