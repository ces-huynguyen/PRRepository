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

import com.amf.registration.exception.NoSuchLogException;
import com.amf.registration.model.RegistrationLog;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the registration log service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see RegistrationLogUtil
 * @generated
 */
@ProviderType
public interface RegistrationLogPersistence
	extends BasePersistence<RegistrationLog> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link RegistrationLogUtil} to access the registration log persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Caches the registration log in the entity cache if it is enabled.
	 *
	 * @param registrationLog the registration log
	 */
	public void cacheResult(RegistrationLog registrationLog);

	/**
	 * Caches the registration logs in the entity cache if it is enabled.
	 *
	 * @param registrationLogs the registration logs
	 */
	public void cacheResult(java.util.List<RegistrationLog> registrationLogs);

	/**
	 * Creates a new registration log with the primary key. Does not add the registration log to the database.
	 *
	 * @param registrationLogId the primary key for the new registration log
	 * @return the new registration log
	 */
	public RegistrationLog create(long registrationLogId);

	/**
	 * Removes the registration log with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param registrationLogId the primary key of the registration log
	 * @return the registration log that was removed
	 * @throws NoSuchLogException if a registration log with the primary key could not be found
	 */
	public RegistrationLog remove(long registrationLogId)
		throws NoSuchLogException;

	public RegistrationLog updateImpl(RegistrationLog registrationLog);

	/**
	 * Returns the registration log with the primary key or throws a <code>NoSuchLogException</code> if it could not be found.
	 *
	 * @param registrationLogId the primary key of the registration log
	 * @return the registration log
	 * @throws NoSuchLogException if a registration log with the primary key could not be found
	 */
	public RegistrationLog findByPrimaryKey(long registrationLogId)
		throws NoSuchLogException;

	/**
	 * Returns the registration log with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param registrationLogId the primary key of the registration log
	 * @return the registration log, or <code>null</code> if a registration log with the primary key could not be found
	 */
	public RegistrationLog fetchByPrimaryKey(long registrationLogId);

	/**
	 * Returns all the registration logs.
	 *
	 * @return the registration logs
	 */
	public java.util.List<RegistrationLog> findAll();

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
	public java.util.List<RegistrationLog> findAll(int start, int end);

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
	public java.util.List<RegistrationLog> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<RegistrationLog>
			orderByComparator);

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
	public java.util.List<RegistrationLog> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<RegistrationLog>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the registration logs from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of registration logs.
	 *
	 * @return the number of registration logs
	 */
	public int countAll();

}