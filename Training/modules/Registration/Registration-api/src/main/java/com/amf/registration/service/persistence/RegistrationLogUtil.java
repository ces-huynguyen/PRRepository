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

package com.amf.registration.service.persistence;

import com.amf.registration.model.RegistrationLog;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * The persistence utility for the registration log service. This utility wraps <code>com.amf.registration.service.persistence.impl.RegistrationLogPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see RegistrationLogPersistence
 * @generated
 */
public class RegistrationLogUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static void clearCache(RegistrationLog registrationLog) {
		getPersistence().clearCache(registrationLog);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#fetchByPrimaryKeys(Set)
	 */
	public static Map<Serializable, RegistrationLog> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<RegistrationLog> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<RegistrationLog> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<RegistrationLog> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<RegistrationLog> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static RegistrationLog update(RegistrationLog registrationLog) {
		return getPersistence().update(registrationLog);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static RegistrationLog update(
		RegistrationLog registrationLog, ServiceContext serviceContext) {

		return getPersistence().update(registrationLog, serviceContext);
	}

	/**
	 * Caches the registration log in the entity cache if it is enabled.
	 *
	 * @param registrationLog the registration log
	 */
	public static void cacheResult(RegistrationLog registrationLog) {
		getPersistence().cacheResult(registrationLog);
	}

	/**
	 * Caches the registration logs in the entity cache if it is enabled.
	 *
	 * @param registrationLogs the registration logs
	 */
	public static void cacheResult(List<RegistrationLog> registrationLogs) {
		getPersistence().cacheResult(registrationLogs);
	}

	/**
	 * Creates a new registration log with the primary key. Does not add the registration log to the database.
	 *
	 * @param registrationLogId the primary key for the new registration log
	 * @return the new registration log
	 */
	public static RegistrationLog create(long registrationLogId) {
		return getPersistence().create(registrationLogId);
	}

	/**
	 * Removes the registration log with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param registrationLogId the primary key of the registration log
	 * @return the registration log that was removed
	 * @throws NoSuchLogException if a registration log with the primary key could not be found
	 */
	public static RegistrationLog remove(long registrationLogId)
		throws com.amf.registration.exception.NoSuchLogException {

		return getPersistence().remove(registrationLogId);
	}

	public static RegistrationLog updateImpl(RegistrationLog registrationLog) {
		return getPersistence().updateImpl(registrationLog);
	}

	/**
	 * Returns the registration log with the primary key or throws a <code>NoSuchLogException</code> if it could not be found.
	 *
	 * @param registrationLogId the primary key of the registration log
	 * @return the registration log
	 * @throws NoSuchLogException if a registration log with the primary key could not be found
	 */
	public static RegistrationLog findByPrimaryKey(long registrationLogId)
		throws com.amf.registration.exception.NoSuchLogException {

		return getPersistence().findByPrimaryKey(registrationLogId);
	}

	/**
	 * Returns the registration log with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param registrationLogId the primary key of the registration log
	 * @return the registration log, or <code>null</code> if a registration log with the primary key could not be found
	 */
	public static RegistrationLog fetchByPrimaryKey(long registrationLogId) {
		return getPersistence().fetchByPrimaryKey(registrationLogId);
	}

	/**
	 * Returns all the registration logs.
	 *
	 * @return the registration logs
	 */
	public static List<RegistrationLog> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the registration logs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>RegistrationLogModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of registration logs
	 * @param end the upper bound of the range of registration logs (not inclusive)
	 * @return the range of registration logs
	 */
	public static List<RegistrationLog> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the registration logs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>RegistrationLogModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of registration logs
	 * @param end the upper bound of the range of registration logs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of registration logs
	 */
	public static List<RegistrationLog> findAll(
		int start, int end,
		OrderByComparator<RegistrationLog> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the registration logs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>RegistrationLogModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of registration logs
	 * @param end the upper bound of the range of registration logs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of registration logs
	 */
	public static List<RegistrationLog> findAll(
		int start, int end,
		OrderByComparator<RegistrationLog> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the registration logs from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of registration logs.
	 *
	 * @return the number of registration logs
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static RegistrationLogPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker
		<RegistrationLogPersistence, RegistrationLogPersistence>
			_serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(
			RegistrationLogPersistence.class);

		ServiceTracker<RegistrationLogPersistence, RegistrationLogPersistence>
			serviceTracker =
				new ServiceTracker
					<RegistrationLogPersistence, RegistrationLogPersistence>(
						bundle.getBundleContext(),
						RegistrationLogPersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}