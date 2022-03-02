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

package com.amf.registration.service.base;

import com.amf.registration.model.RegistrationLog;
import com.amf.registration.service.RegistrationLogLocalService;
import com.amf.registration.service.persistence.RegistrationLogPersistence;

import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.dao.db.DB;
import com.liferay.portal.kernel.dao.db.DBManagerUtil;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdate;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdateFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DefaultActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.module.framework.service.IdentifiableOSGiService;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.BaseLocalServiceImpl;
import com.liferay.portal.kernel.service.PersistedModelLocalService;
import com.liferay.portal.kernel.service.persistence.BasePersistence;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PortalUtil;

import java.io.Serializable;

import java.util.List;

import javax.sql.DataSource;

import org.osgi.service.component.annotations.Reference;

/**
 * Provides the base implementation for the registration log local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.amf.registration.service.impl.RegistrationLogLocalServiceImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.amf.registration.service.impl.RegistrationLogLocalServiceImpl
 * @generated
 */
public abstract class RegistrationLogLocalServiceBaseImpl
	extends BaseLocalServiceImpl
	implements AopService, IdentifiableOSGiService,
			   RegistrationLogLocalService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Use <code>RegistrationLogLocalService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>com.amf.registration.service.RegistrationLogLocalServiceUtil</code>.
	 */

	/**
	 * Adds the registration log to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect RegistrationLogLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param registrationLog the registration log
	 * @return the registration log that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public RegistrationLog addRegistrationLog(RegistrationLog registrationLog) {
		registrationLog.setNew(true);

		return registrationLogPersistence.update(registrationLog);
	}

	/**
	 * Creates a new registration log with the primary key. Does not add the registration log to the database.
	 *
	 * @param registrationLogId the primary key for the new registration log
	 * @return the new registration log
	 */
	@Override
	@Transactional(enabled = false)
	public RegistrationLog createRegistrationLog(long registrationLogId) {
		return registrationLogPersistence.create(registrationLogId);
	}

	/**
	 * Deletes the registration log with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect RegistrationLogLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param registrationLogId the primary key of the registration log
	 * @return the registration log that was removed
	 * @throws PortalException if a registration log with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public RegistrationLog deleteRegistrationLog(long registrationLogId)
		throws PortalException {

		return registrationLogPersistence.remove(registrationLogId);
	}

	/**
	 * Deletes the registration log from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect RegistrationLogLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param registrationLog the registration log
	 * @return the registration log that was removed
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public RegistrationLog deleteRegistrationLog(
		RegistrationLog registrationLog) {

		return registrationLogPersistence.remove(registrationLog);
	}

	@Override
	public DynamicQuery dynamicQuery() {
		Class<?> clazz = getClass();

		return DynamicQueryFactoryUtil.forClass(
			RegistrationLog.class, clazz.getClassLoader());
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery) {
		return registrationLogPersistence.findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.amf.registration.model.impl.RegistrationLogModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return registrationLogPersistence.findWithDynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.amf.registration.model.impl.RegistrationLogModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<T> orderByComparator) {

		return registrationLogPersistence.findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(DynamicQuery dynamicQuery) {
		return registrationLogPersistence.countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(
		DynamicQuery dynamicQuery, Projection projection) {

		return registrationLogPersistence.countWithDynamicQuery(
			dynamicQuery, projection);
	}

	@Override
	public RegistrationLog fetchRegistrationLog(long registrationLogId) {
		return registrationLogPersistence.fetchByPrimaryKey(registrationLogId);
	}

	/**
	 * Returns the registration log with the primary key.
	 *
	 * @param registrationLogId the primary key of the registration log
	 * @return the registration log
	 * @throws PortalException if a registration log with the primary key could not be found
	 */
	@Override
	public RegistrationLog getRegistrationLog(long registrationLogId)
		throws PortalException {

		return registrationLogPersistence.findByPrimaryKey(registrationLogId);
	}

	@Override
	public ActionableDynamicQuery getActionableDynamicQuery() {
		ActionableDynamicQuery actionableDynamicQuery =
			new DefaultActionableDynamicQuery();

		actionableDynamicQuery.setBaseLocalService(registrationLogLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(RegistrationLog.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("registrationLogId");

		return actionableDynamicQuery;
	}

	@Override
	public IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		IndexableActionableDynamicQuery indexableActionableDynamicQuery =
			new IndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setBaseLocalService(
			registrationLogLocalService);
		indexableActionableDynamicQuery.setClassLoader(getClassLoader());
		indexableActionableDynamicQuery.setModelClass(RegistrationLog.class);

		indexableActionableDynamicQuery.setPrimaryKeyPropertyName(
			"registrationLogId");

		return indexableActionableDynamicQuery;
	}

	protected void initActionableDynamicQuery(
		ActionableDynamicQuery actionableDynamicQuery) {

		actionableDynamicQuery.setBaseLocalService(registrationLogLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(RegistrationLog.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("registrationLogId");
	}

	/**
	 * @throws PortalException
	 */
	public PersistedModel createPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return registrationLogPersistence.create(
			((Long)primaryKeyObj).longValue());
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException {

		return registrationLogLocalService.deleteRegistrationLog(
			(RegistrationLog)persistedModel);
	}

	public BasePersistence<RegistrationLog> getBasePersistence() {
		return registrationLogPersistence;
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return registrationLogPersistence.findByPrimaryKey(primaryKeyObj);
	}

	/**
	 * Returns a range of all the registration logs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.amf.registration.model.impl.RegistrationLogModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of registration logs
	 * @param end the upper bound of the range of registration logs (not inclusive)
	 * @return the range of registration logs
	 */
	@Override
	public List<RegistrationLog> getRegistrationLogs(int start, int end) {
		return registrationLogPersistence.findAll(start, end);
	}

	/**
	 * Returns the number of registration logs.
	 *
	 * @return the number of registration logs
	 */
	@Override
	public int getRegistrationLogsCount() {
		return registrationLogPersistence.countAll();
	}

	/**
	 * Updates the registration log in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect RegistrationLogLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param registrationLog the registration log
	 * @return the registration log that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public RegistrationLog updateRegistrationLog(
		RegistrationLog registrationLog) {

		return registrationLogPersistence.update(registrationLog);
	}

	@Override
	public Class<?>[] getAopInterfaces() {
		return new Class<?>[] {
			RegistrationLogLocalService.class, IdentifiableOSGiService.class,
			PersistedModelLocalService.class
		};
	}

	@Override
	public void setAopProxy(Object aopProxy) {
		registrationLogLocalService = (RegistrationLogLocalService)aopProxy;
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return RegistrationLogLocalService.class.getName();
	}

	protected Class<?> getModelClass() {
		return RegistrationLog.class;
	}

	protected String getModelClassName() {
		return RegistrationLog.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource = registrationLogPersistence.getDataSource();

			DB db = DBManagerUtil.getDB();

			sql = db.buildSQL(sql);
			sql = PortalUtil.transformSQL(sql);

			SqlUpdate sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(
				dataSource, sql);

			sqlUpdate.update();
		}
		catch (Exception exception) {
			throw new SystemException(exception);
		}
	}

	protected RegistrationLogLocalService registrationLogLocalService;

	@Reference
	protected RegistrationLogPersistence registrationLogPersistence;

	@Reference
	protected com.liferay.counter.kernel.service.CounterLocalService
		counterLocalService;

	@Reference
	protected com.liferay.portal.kernel.service.ClassNameLocalService
		classNameLocalService;

	@Reference
	protected com.liferay.portal.kernel.service.ResourceLocalService
		resourceLocalService;

	@Reference
	protected com.liferay.portal.kernel.service.UserLocalService
		userLocalService;

}