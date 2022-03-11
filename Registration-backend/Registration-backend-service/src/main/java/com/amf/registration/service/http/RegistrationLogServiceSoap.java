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

package com.amf.registration.service.http;

import com.amf.registration.service.RegistrationLogServiceUtil;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.rmi.RemoteException;

/**
 * Provides the SOAP utility for the
 * <code>RegistrationLogServiceUtil</code> service
 * utility. The static methods of this class call the same methods of the
 * service utility. However, the signatures are different because it is
 * difficult for SOAP to support certain types.
 *
 * <p>
 * ServiceBuilder follows certain rules in translating the methods. For example,
 * if the method in the service utility returns a <code>java.util.List</code>,
 * that is translated to an array of
 * <code>com.amf.registration.model.RegistrationLogSoap</code>. If the method in the
 * service utility returns a
 * <code>com.amf.registration.model.RegistrationLog</code>, that is translated to a
 * <code>com.amf.registration.model.RegistrationLogSoap</code>. Methods that SOAP
 * cannot safely wire are skipped.
 * </p>
 *
 * <p>
 * The benefits of using the SOAP utility is that it is cross platform
 * compatible. SOAP allows different languages like Java, .NET, C++, PHP, and
 * even Perl, to call the generated services. One drawback of SOAP is that it is
 * slow because it needs to serialize all calls into a text format (XML).
 * </p>
 *
 * <p>
 * You can see a list of services at http://localhost:8080/api/axis. Set the
 * property <b>axis.servlet.hosts.allowed</b> in portal.properties to configure
 * security.
 * </p>
 *
 * <p>
 * The SOAP utility is only generated for remote services.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see RegistrationLogServiceHttp
 * @deprecated As of Athanasius (7.3.x), with no direct replacement
 * @generated
 */
@Deprecated
public class RegistrationLogServiceSoap {

	public static com.amf.registration.model.RegistrationLogSoap
			addRegistrationLog(
				long groupId, long companyId, long userId, String userName,
				java.util.Date createDate, java.util.Date modifiedDate,
				String eventType, String ipAddress)
		throws RemoteException {

		try {
			com.amf.registration.model.RegistrationLog returnValue =
				RegistrationLogServiceUtil.addRegistrationLog(
					groupId, companyId, userId, userName, createDate,
					modifiedDate, eventType, ipAddress);

			return com.amf.registration.model.RegistrationLogSoap.toSoapModel(
				returnValue);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static com.amf.registration.model.RegistrationLogSoap[]
			getRegistrationLogs(int start, int end)
		throws RemoteException {

		try {
			java.util.List<com.amf.registration.model.RegistrationLog>
				returnValue = RegistrationLogServiceUtil.getRegistrationLogs(
					start, end);

			return com.amf.registration.model.RegistrationLogSoap.toSoapModels(
				returnValue);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static com.amf.registration.model.RegistrationLogSoap[]
			getRegistrationLogsByUser(long userId, int start, int end)
		throws RemoteException {

		try {
			java.util.List<com.amf.registration.model.RegistrationLog>
				returnValue =
					RegistrationLogServiceUtil.getRegistrationLogsByUser(
						userId, start, end);

			return com.amf.registration.model.RegistrationLogSoap.toSoapModels(
				returnValue);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static com.amf.registration.model.RegistrationLogSoap[]
			getRegistrationLogsByEventType(String eventType, int start, int end)
		throws RemoteException {

		try {
			java.util.List<com.amf.registration.model.RegistrationLog>
				returnValue =
					RegistrationLogServiceUtil.getRegistrationLogsByEventType(
						eventType, start, end);

			return com.amf.registration.model.RegistrationLogSoap.toSoapModels(
				returnValue);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static com.amf.registration.model.RegistrationLogSoap[]
			getRegistrationLogsByUserEventType(
				long userId, String eventType, int start, int end)
		throws RemoteException {

		try {
			java.util.List<com.amf.registration.model.RegistrationLog>
				returnValue =
					RegistrationLogServiceUtil.
						getRegistrationLogsByUserEventType(
							userId, eventType, start, end);

			return com.amf.registration.model.RegistrationLogSoap.toSoapModels(
				returnValue);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static long getRegistrationLogsCountByEventType(String eventType)
		throws RemoteException {

		try {
			long returnValue =
				RegistrationLogServiceUtil.getRegistrationLogsCountByEventType(
					eventType);

			return returnValue;
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static long getRegistrationLogsCountByUserEventType(
			long userId, String eventType)
		throws RemoteException {

		try {
			long returnValue =
				RegistrationLogServiceUtil.
					getRegistrationLogsCountByUserEventType(userId, eventType);

			return returnValue;
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static int getRegistrationLogsCount() throws RemoteException {
		try {
			int returnValue =
				RegistrationLogServiceUtil.getRegistrationLogsCount();

			return returnValue;
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static long getRegistrationLogsCountByUser(long userId)
		throws RemoteException {

		try {
			long returnValue =
				RegistrationLogServiceUtil.getRegistrationLogsCountByUser(
					userId);

			return returnValue;
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		RegistrationLogServiceSoap.class);

}