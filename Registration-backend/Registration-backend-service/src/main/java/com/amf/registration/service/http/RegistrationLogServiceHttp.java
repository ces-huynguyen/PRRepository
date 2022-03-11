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
import com.liferay.portal.kernel.security.auth.HttpPrincipal;
import com.liferay.portal.kernel.service.http.TunnelUtil;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;

/**
 * Provides the HTTP utility for the
 * <code>RegistrationLogServiceUtil</code> service
 * utility. The
 * static methods of this class calls the same methods of the service utility.
 * However, the signatures are different because it requires an additional
 * <code>HttpPrincipal</code> parameter.
 *
 * <p>
 * The benefits of using the HTTP utility is that it is fast and allows for
 * tunneling without the cost of serializing to text. The drawback is that it
 * only works with Java.
 * </p>
 *
 * <p>
 * Set the property <b>tunnel.servlet.hosts.allowed</b> in portal.properties to
 * configure security.
 * </p>
 *
 * <p>
 * The HTTP utility is only generated for remote services.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see RegistrationLogServiceSoap
 * @generated
 */
public class RegistrationLogServiceHttp {

	public static com.amf.registration.model.RegistrationLog addRegistrationLog(
			HttpPrincipal httpPrincipal, long groupId, long companyId,
			long userId, String userName, java.util.Date createDate,
			java.util.Date modifiedDate, String eventType, String ipAddress)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				RegistrationLogServiceUtil.class, "addRegistrationLog",
				_addRegistrationLogParameterTypes0);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, groupId, companyId, userId, userName, createDate,
				modifiedDate, eventType, ipAddress);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (com.amf.registration.model.RegistrationLog)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static java.util.List<com.amf.registration.model.RegistrationLog>
		getRegistrationLogs(HttpPrincipal httpPrincipal, int start, int end) {

		try {
			MethodKey methodKey = new MethodKey(
				RegistrationLogServiceUtil.class, "getRegistrationLogs",
				_getRegistrationLogsParameterTypes1);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, start, end);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (java.util.List<com.amf.registration.model.RegistrationLog>)
				returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static java.util.List<com.amf.registration.model.RegistrationLog>
		getRegistrationLogsByUser(
			HttpPrincipal httpPrincipal, long userId, int start, int end) {

		try {
			MethodKey methodKey = new MethodKey(
				RegistrationLogServiceUtil.class, "getRegistrationLogsByUser",
				_getRegistrationLogsByUserParameterTypes2);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, userId, start, end);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (java.util.List<com.amf.registration.model.RegistrationLog>)
				returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static java.util.List<com.amf.registration.model.RegistrationLog>
		getRegistrationLogsByEventType(
			HttpPrincipal httpPrincipal, String eventType, int start, int end) {

		try {
			MethodKey methodKey = new MethodKey(
				RegistrationLogServiceUtil.class,
				"getRegistrationLogsByEventType",
				_getRegistrationLogsByEventTypeParameterTypes3);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, eventType, start, end);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (java.util.List<com.amf.registration.model.RegistrationLog>)
				returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static java.util.List<com.amf.registration.model.RegistrationLog>
		getRegistrationLogsByUserEventType(
			HttpPrincipal httpPrincipal, long userId, String eventType,
			int start, int end) {

		try {
			MethodKey methodKey = new MethodKey(
				RegistrationLogServiceUtil.class,
				"getRegistrationLogsByUserEventType",
				_getRegistrationLogsByUserEventTypeParameterTypes4);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, userId, eventType, start, end);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (java.util.List<com.amf.registration.model.RegistrationLog>)
				returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static long getRegistrationLogsCountByEventType(
		HttpPrincipal httpPrincipal, String eventType) {

		try {
			MethodKey methodKey = new MethodKey(
				RegistrationLogServiceUtil.class,
				"getRegistrationLogsCountByEventType",
				_getRegistrationLogsCountByEventTypeParameterTypes5);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, eventType);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return ((Long)returnObj).longValue();
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static long getRegistrationLogsCountByUserEventType(
		HttpPrincipal httpPrincipal, long userId, String eventType) {

		try {
			MethodKey methodKey = new MethodKey(
				RegistrationLogServiceUtil.class,
				"getRegistrationLogsCountByUserEventType",
				_getRegistrationLogsCountByUserEventTypeParameterTypes6);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, userId, eventType);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return ((Long)returnObj).longValue();
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static int getRegistrationLogsCount(HttpPrincipal httpPrincipal) {
		try {
			MethodKey methodKey = new MethodKey(
				RegistrationLogServiceUtil.class, "getRegistrationLogsCount",
				_getRegistrationLogsCountParameterTypes7);

			MethodHandler methodHandler = new MethodHandler(methodKey);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return ((Integer)returnObj).intValue();
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static long getRegistrationLogsCountByUser(
		HttpPrincipal httpPrincipal, long userId) {

		try {
			MethodKey methodKey = new MethodKey(
				RegistrationLogServiceUtil.class,
				"getRegistrationLogsCountByUser",
				_getRegistrationLogsCountByUserParameterTypes8);

			MethodHandler methodHandler = new MethodHandler(methodKey, userId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return ((Long)returnObj).longValue();
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		RegistrationLogServiceHttp.class);

	private static final Class<?>[] _addRegistrationLogParameterTypes0 =
		new Class[] {
			long.class, long.class, long.class, String.class,
			java.util.Date.class, java.util.Date.class, String.class,
			String.class
		};
	private static final Class<?>[] _getRegistrationLogsParameterTypes1 =
		new Class[] {int.class, int.class};
	private static final Class<?>[] _getRegistrationLogsByUserParameterTypes2 =
		new Class[] {long.class, int.class, int.class};
	private static final Class<?>[]
		_getRegistrationLogsByEventTypeParameterTypes3 = new Class[] {
			String.class, int.class, int.class
		};
	private static final Class<?>[]
		_getRegistrationLogsByUserEventTypeParameterTypes4 = new Class[] {
			long.class, String.class, int.class, int.class
		};
	private static final Class<?>[]
		_getRegistrationLogsCountByEventTypeParameterTypes5 = new Class[] {
			String.class
		};
	private static final Class<?>[]
		_getRegistrationLogsCountByUserEventTypeParameterTypes6 = new Class[] {
			long.class, String.class
		};
	private static final Class<?>[] _getRegistrationLogsCountParameterTypes7 =
		new Class[] {};
	private static final Class<?>[]
		_getRegistrationLogsCountByUserParameterTypes8 = new Class[] {
			long.class
		};

}