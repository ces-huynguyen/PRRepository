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

package com.amf.registration.service.persistence.impl;

import com.amf.registration.exception.NoSuchLogException;
import com.amf.registration.model.RegistrationLog;
import com.amf.registration.model.impl.RegistrationLogImpl;
import com.amf.registration.model.impl.RegistrationLogModelImpl;
import com.amf.registration.service.persistence.RegistrationLogPersistence;
import com.amf.registration.service.persistence.RegistrationLogUtil;
import com.amf.registration.service.persistence.impl.constants.RegistrationPersistenceConstants;

import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.configuration.Configuration;
import com.liferay.portal.kernel.dao.orm.ArgumentsResolver;
import com.liferay.portal.kernel.dao.orm.EntityCache;
import com.liferay.portal.kernel.dao.orm.FinderCache;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.SessionFactory;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.security.auth.CompanyThreadLocal;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.MapUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.ProxyUtil;

import java.io.Serializable;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.sql.DataSource;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * The persistence implementation for the registration log service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = RegistrationLogPersistence.class)
public class RegistrationLogPersistenceImpl
	extends BasePersistenceImpl<RegistrationLog>
	implements RegistrationLogPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>RegistrationLogUtil</code> to access the registration log persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		RegistrationLogImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;
	private FinderPath _finderPathWithPaginationFindByEventType;
	private FinderPath _finderPathWithoutPaginationFindByEventType;
	private FinderPath _finderPathCountByEventType;

	/**
	 * Returns all the registration logs where eventType = &#63;.
	 *
	 * @param eventType the event type
	 * @return the matching registration logs
	 */
	@Override
	public List<RegistrationLog> findByEventType(String eventType) {
		return findByEventType(
			eventType, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the registration logs where eventType = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>RegistrationLogModelImpl</code>.
	 * </p>
	 *
	 * @param eventType the event type
	 * @param start the lower bound of the range of registration logs
	 * @param end the upper bound of the range of registration logs (not inclusive)
	 * @return the range of matching registration logs
	 */
	@Override
	public List<RegistrationLog> findByEventType(
		String eventType, int start, int end) {

		return findByEventType(eventType, start, end, null);
	}

	/**
	 * Returns an ordered range of all the registration logs where eventType = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>RegistrationLogModelImpl</code>.
	 * </p>
	 *
	 * @param eventType the event type
	 * @param start the lower bound of the range of registration logs
	 * @param end the upper bound of the range of registration logs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching registration logs
	 */
	@Override
	public List<RegistrationLog> findByEventType(
		String eventType, int start, int end,
		OrderByComparator<RegistrationLog> orderByComparator) {

		return findByEventType(eventType, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the registration logs where eventType = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>RegistrationLogModelImpl</code>.
	 * </p>
	 *
	 * @param eventType the event type
	 * @param start the lower bound of the range of registration logs
	 * @param end the upper bound of the range of registration logs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching registration logs
	 */
	@Override
	public List<RegistrationLog> findByEventType(
		String eventType, int start, int end,
		OrderByComparator<RegistrationLog> orderByComparator,
		boolean useFinderCache) {

		eventType = Objects.toString(eventType, "");

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByEventType;
				finderArgs = new Object[] {eventType};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByEventType;
			finderArgs = new Object[] {
				eventType, start, end, orderByComparator
			};
		}

		List<RegistrationLog> list = null;

		if (useFinderCache) {
			list = (List<RegistrationLog>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (RegistrationLog registrationLog : list) {
					if (!eventType.equals(registrationLog.getEventType())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler sb = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					3 + (orderByComparator.getOrderByFields().length * 2));
			}
			else {
				sb = new StringBundler(3);
			}

			sb.append(_SQL_SELECT_REGISTRATIONLOG_WHERE);

			boolean bindEventType = false;

			if (eventType.isEmpty()) {
				sb.append(_FINDER_COLUMN_EVENTTYPE_EVENTTYPE_3);
			}
			else {
				bindEventType = true;

				sb.append(_FINDER_COLUMN_EVENTTYPE_EVENTTYPE_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(RegistrationLogModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindEventType) {
					queryPos.add(eventType);
				}

				list = (List<RegistrationLog>)QueryUtil.list(
					query, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache) {
					finderCache.putResult(finderPath, finderArgs, list);
				}
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first registration log in the ordered set where eventType = &#63;.
	 *
	 * @param eventType the event type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching registration log
	 * @throws NoSuchLogException if a matching registration log could not be found
	 */
	@Override
	public RegistrationLog findByEventType_First(
			String eventType,
			OrderByComparator<RegistrationLog> orderByComparator)
		throws NoSuchLogException {

		RegistrationLog registrationLog = fetchByEventType_First(
			eventType, orderByComparator);

		if (registrationLog != null) {
			return registrationLog;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("eventType=");
		sb.append(eventType);

		sb.append("}");

		throw new NoSuchLogException(sb.toString());
	}

	/**
	 * Returns the first registration log in the ordered set where eventType = &#63;.
	 *
	 * @param eventType the event type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching registration log, or <code>null</code> if a matching registration log could not be found
	 */
	@Override
	public RegistrationLog fetchByEventType_First(
		String eventType,
		OrderByComparator<RegistrationLog> orderByComparator) {

		List<RegistrationLog> list = findByEventType(
			eventType, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last registration log in the ordered set where eventType = &#63;.
	 *
	 * @param eventType the event type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching registration log
	 * @throws NoSuchLogException if a matching registration log could not be found
	 */
	@Override
	public RegistrationLog findByEventType_Last(
			String eventType,
			OrderByComparator<RegistrationLog> orderByComparator)
		throws NoSuchLogException {

		RegistrationLog registrationLog = fetchByEventType_Last(
			eventType, orderByComparator);

		if (registrationLog != null) {
			return registrationLog;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("eventType=");
		sb.append(eventType);

		sb.append("}");

		throw new NoSuchLogException(sb.toString());
	}

	/**
	 * Returns the last registration log in the ordered set where eventType = &#63;.
	 *
	 * @param eventType the event type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching registration log, or <code>null</code> if a matching registration log could not be found
	 */
	@Override
	public RegistrationLog fetchByEventType_Last(
		String eventType,
		OrderByComparator<RegistrationLog> orderByComparator) {

		int count = countByEventType(eventType);

		if (count == 0) {
			return null;
		}

		List<RegistrationLog> list = findByEventType(
			eventType, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the registration logs before and after the current registration log in the ordered set where eventType = &#63;.
	 *
	 * @param registrationLogId the primary key of the current registration log
	 * @param eventType the event type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next registration log
	 * @throws NoSuchLogException if a registration log with the primary key could not be found
	 */
	@Override
	public RegistrationLog[] findByEventType_PrevAndNext(
			long registrationLogId, String eventType,
			OrderByComparator<RegistrationLog> orderByComparator)
		throws NoSuchLogException {

		eventType = Objects.toString(eventType, "");

		RegistrationLog registrationLog = findByPrimaryKey(registrationLogId);

		Session session = null;

		try {
			session = openSession();

			RegistrationLog[] array = new RegistrationLogImpl[3];

			array[0] = getByEventType_PrevAndNext(
				session, registrationLog, eventType, orderByComparator, true);

			array[1] = registrationLog;

			array[2] = getByEventType_PrevAndNext(
				session, registrationLog, eventType, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected RegistrationLog getByEventType_PrevAndNext(
		Session session, RegistrationLog registrationLog, String eventType,
		OrderByComparator<RegistrationLog> orderByComparator,
		boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_REGISTRATIONLOG_WHERE);

		boolean bindEventType = false;

		if (eventType.isEmpty()) {
			sb.append(_FINDER_COLUMN_EVENTTYPE_EVENTTYPE_3);
		}
		else {
			bindEventType = true;

			sb.append(_FINDER_COLUMN_EVENTTYPE_EVENTTYPE_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields =
				orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				sb.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				sb.append(_ORDER_BY_ENTITY_ALIAS);
				sb.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						sb.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN);
					}
					else {
						sb.append(WHERE_LESSER_THAN);
					}
				}
			}

			sb.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				sb.append(_ORDER_BY_ENTITY_ALIAS);
				sb.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						sb.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC);
					}
					else {
						sb.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			sb.append(RegistrationLogModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		if (bindEventType) {
			queryPos.add(eventType);
		}

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						registrationLog)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<RegistrationLog> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the registration logs where eventType = &#63; from the database.
	 *
	 * @param eventType the event type
	 */
	@Override
	public void removeByEventType(String eventType) {
		for (RegistrationLog registrationLog :
				findByEventType(
					eventType, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(registrationLog);
		}
	}

	/**
	 * Returns the number of registration logs where eventType = &#63;.
	 *
	 * @param eventType the event type
	 * @return the number of matching registration logs
	 */
	@Override
	public int countByEventType(String eventType) {
		eventType = Objects.toString(eventType, "");

		FinderPath finderPath = _finderPathCountByEventType;

		Object[] finderArgs = new Object[] {eventType};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_REGISTRATIONLOG_WHERE);

			boolean bindEventType = false;

			if (eventType.isEmpty()) {
				sb.append(_FINDER_COLUMN_EVENTTYPE_EVENTTYPE_3);
			}
			else {
				bindEventType = true;

				sb.append(_FINDER_COLUMN_EVENTTYPE_EVENTTYPE_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindEventType) {
					queryPos.add(eventType);
				}

				count = (Long)query.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_EVENTTYPE_EVENTTYPE_2 =
		"registrationLog.eventType = ?";

	private static final String _FINDER_COLUMN_EVENTTYPE_EVENTTYPE_3 =
		"(registrationLog.eventType IS NULL OR registrationLog.eventType = '')";

	public RegistrationLogPersistenceImpl() {
		setModelClass(RegistrationLog.class);

		setModelImplClass(RegistrationLogImpl.class);
		setModelPKClass(long.class);
	}

	/**
	 * Caches the registration log in the entity cache if it is enabled.
	 *
	 * @param registrationLog the registration log
	 */
	@Override
	public void cacheResult(RegistrationLog registrationLog) {
		entityCache.putResult(
			RegistrationLogImpl.class, registrationLog.getPrimaryKey(),
			registrationLog);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the registration logs in the entity cache if it is enabled.
	 *
	 * @param registrationLogs the registration logs
	 */
	@Override
	public void cacheResult(List<RegistrationLog> registrationLogs) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (registrationLogs.size() >
				 _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (RegistrationLog registrationLog : registrationLogs) {
			if (entityCache.getResult(
					RegistrationLogImpl.class,
					registrationLog.getPrimaryKey()) == null) {

				cacheResult(registrationLog);
			}
		}
	}

	/**
	 * Clears the cache for all registration logs.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(RegistrationLogImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the registration log.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(RegistrationLog registrationLog) {
		entityCache.removeResult(RegistrationLogImpl.class, registrationLog);
	}

	@Override
	public void clearCache(List<RegistrationLog> registrationLogs) {
		for (RegistrationLog registrationLog : registrationLogs) {
			entityCache.removeResult(
				RegistrationLogImpl.class, registrationLog);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(RegistrationLogImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new registration log with the primary key. Does not add the registration log to the database.
	 *
	 * @param registrationLogId the primary key for the new registration log
	 * @return the new registration log
	 */
	@Override
	public RegistrationLog create(long registrationLogId) {
		RegistrationLog registrationLog = new RegistrationLogImpl();

		registrationLog.setNew(true);
		registrationLog.setPrimaryKey(registrationLogId);

		registrationLog.setCompanyId(CompanyThreadLocal.getCompanyId());

		return registrationLog;
	}

	/**
	 * Removes the registration log with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param registrationLogId the primary key of the registration log
	 * @return the registration log that was removed
	 * @throws NoSuchLogException if a registration log with the primary key could not be found
	 */
	@Override
	public RegistrationLog remove(long registrationLogId)
		throws NoSuchLogException {

		return remove((Serializable)registrationLogId);
	}

	/**
	 * Removes the registration log with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the registration log
	 * @return the registration log that was removed
	 * @throws NoSuchLogException if a registration log with the primary key could not be found
	 */
	@Override
	public RegistrationLog remove(Serializable primaryKey)
		throws NoSuchLogException {

		Session session = null;

		try {
			session = openSession();

			RegistrationLog registrationLog = (RegistrationLog)session.get(
				RegistrationLogImpl.class, primaryKey);

			if (registrationLog == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchLogException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(registrationLog);
		}
		catch (NoSuchLogException noSuchEntityException) {
			throw noSuchEntityException;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	protected RegistrationLog removeImpl(RegistrationLog registrationLog) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(registrationLog)) {
				registrationLog = (RegistrationLog)session.get(
					RegistrationLogImpl.class,
					registrationLog.getPrimaryKeyObj());
			}

			if (registrationLog != null) {
				session.delete(registrationLog);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (registrationLog != null) {
			clearCache(registrationLog);
		}

		return registrationLog;
	}

	@Override
	public RegistrationLog updateImpl(RegistrationLog registrationLog) {
		boolean isNew = registrationLog.isNew();

		if (!(registrationLog instanceof RegistrationLogModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(registrationLog.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					registrationLog);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in registrationLog proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom RegistrationLog implementation " +
					registrationLog.getClass());
		}

		RegistrationLogModelImpl registrationLogModelImpl =
			(RegistrationLogModelImpl)registrationLog;

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		Date date = new Date();

		if (isNew && (registrationLog.getCreateDate() == null)) {
			if (serviceContext == null) {
				registrationLog.setCreateDate(date);
			}
			else {
				registrationLog.setCreateDate(
					serviceContext.getCreateDate(date));
			}
		}

		if (!registrationLogModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				registrationLog.setModifiedDate(date);
			}
			else {
				registrationLog.setModifiedDate(
					serviceContext.getModifiedDate(date));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(registrationLog);
			}
			else {
				registrationLog = (RegistrationLog)session.merge(
					registrationLog);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(
			RegistrationLogImpl.class, registrationLogModelImpl, false, true);

		if (isNew) {
			registrationLog.setNew(false);
		}

		registrationLog.resetOriginalValues();

		return registrationLog;
	}

	/**
	 * Returns the registration log with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the registration log
	 * @return the registration log
	 * @throws NoSuchLogException if a registration log with the primary key could not be found
	 */
	@Override
	public RegistrationLog findByPrimaryKey(Serializable primaryKey)
		throws NoSuchLogException {

		RegistrationLog registrationLog = fetchByPrimaryKey(primaryKey);

		if (registrationLog == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchLogException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return registrationLog;
	}

	/**
	 * Returns the registration log with the primary key or throws a <code>NoSuchLogException</code> if it could not be found.
	 *
	 * @param registrationLogId the primary key of the registration log
	 * @return the registration log
	 * @throws NoSuchLogException if a registration log with the primary key could not be found
	 */
	@Override
	public RegistrationLog findByPrimaryKey(long registrationLogId)
		throws NoSuchLogException {

		return findByPrimaryKey((Serializable)registrationLogId);
	}

	/**
	 * Returns the registration log with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param registrationLogId the primary key of the registration log
	 * @return the registration log, or <code>null</code> if a registration log with the primary key could not be found
	 */
	@Override
	public RegistrationLog fetchByPrimaryKey(long registrationLogId) {
		return fetchByPrimaryKey((Serializable)registrationLogId);
	}

	/**
	 * Returns all the registration logs.
	 *
	 * @return the registration logs
	 */
	@Override
	public List<RegistrationLog> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	@Override
	public List<RegistrationLog> findAll(int start, int end) {
		return findAll(start, end, null);
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
	@Override
	public List<RegistrationLog> findAll(
		int start, int end,
		OrderByComparator<RegistrationLog> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
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
	@Override
	public List<RegistrationLog> findAll(
		int start, int end,
		OrderByComparator<RegistrationLog> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindAll;
				finderArgs = FINDER_ARGS_EMPTY;
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindAll;
			finderArgs = new Object[] {start, end, orderByComparator};
		}

		List<RegistrationLog> list = null;

		if (useFinderCache) {
			list = (List<RegistrationLog>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_REGISTRATIONLOG);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_REGISTRATIONLOG;

				sql = sql.concat(RegistrationLogModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<RegistrationLog>)QueryUtil.list(
					query, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache) {
					finderCache.putResult(finderPath, finderArgs, list);
				}
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the registration logs from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (RegistrationLog registrationLog : findAll()) {
			remove(registrationLog);
		}
	}

	/**
	 * Returns the number of registration logs.
	 *
	 * @return the number of registration logs
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_REGISTRATIONLOG);

				count = (Long)query.uniqueResult();

				finderCache.putResult(
					_finderPathCountAll, FINDER_ARGS_EMPTY, count);
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	@Override
	protected EntityCache getEntityCache() {
		return entityCache;
	}

	@Override
	protected String getPKDBName() {
		return "registrationLogId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_REGISTRATIONLOG;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return RegistrationLogModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the registration log persistence.
	 */
	@Activate
	public void activate(BundleContext bundleContext) {
		_bundleContext = bundleContext;

		_argumentsResolverServiceRegistration = _bundleContext.registerService(
			ArgumentsResolver.class,
			new RegistrationLogModelArgumentsResolver(),
			MapUtil.singletonDictionary(
				"model.class.name", RegistrationLog.class.getName()));

		_valueObjectFinderCacheListThreshold = GetterUtil.getInteger(
			PropsUtil.get(PropsKeys.VALUE_OBJECT_FINDER_CACHE_LIST_THRESHOLD));

		_finderPathWithPaginationFindAll = _createFinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0],
			new String[0], true);

		_finderPathWithoutPaginationFindAll = _createFinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0],
			new String[0], true);

		_finderPathCountAll = _createFinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0], new String[0], false);

		_finderPathWithPaginationFindByEventType = _createFinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByEventType",
			new String[] {
				String.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			},
			new String[] {"eventType"}, true);

		_finderPathWithoutPaginationFindByEventType = _createFinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByEventType",
			new String[] {String.class.getName()}, new String[] {"eventType"},
			true);

		_finderPathCountByEventType = _createFinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByEventType",
			new String[] {String.class.getName()}, new String[] {"eventType"},
			false);

		_setRegistrationLogUtilPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		_setRegistrationLogUtilPersistence(null);

		entityCache.removeCache(RegistrationLogImpl.class.getName());

		_argumentsResolverServiceRegistration.unregister();

		for (ServiceRegistration<FinderPath> serviceRegistration :
				_serviceRegistrations) {

			serviceRegistration.unregister();
		}
	}

	private void _setRegistrationLogUtilPersistence(
		RegistrationLogPersistence registrationLogPersistence) {

		try {
			Field field = RegistrationLogUtil.class.getDeclaredField(
				"_persistence");

			field.setAccessible(true);

			field.set(null, registrationLogPersistence);
		}
		catch (ReflectiveOperationException reflectiveOperationException) {
			throw new RuntimeException(reflectiveOperationException);
		}
	}

	@Override
	@Reference(
		target = RegistrationPersistenceConstants.SERVICE_CONFIGURATION_FILTER,
		unbind = "-"
	)
	public void setConfiguration(Configuration configuration) {
	}

	@Override
	@Reference(
		target = RegistrationPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setDataSource(DataSource dataSource) {
		super.setDataSource(dataSource);
	}

	@Override
	@Reference(
		target = RegistrationPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	private BundleContext _bundleContext;

	@Reference
	protected EntityCache entityCache;

	@Reference
	protected FinderCache finderCache;

	private static final String _SQL_SELECT_REGISTRATIONLOG =
		"SELECT registrationLog FROM RegistrationLog registrationLog";

	private static final String _SQL_SELECT_REGISTRATIONLOG_WHERE =
		"SELECT registrationLog FROM RegistrationLog registrationLog WHERE ";

	private static final String _SQL_COUNT_REGISTRATIONLOG =
		"SELECT COUNT(registrationLog) FROM RegistrationLog registrationLog";

	private static final String _SQL_COUNT_REGISTRATIONLOG_WHERE =
		"SELECT COUNT(registrationLog) FROM RegistrationLog registrationLog WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "registrationLog.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No RegistrationLog exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No RegistrationLog exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		RegistrationLogPersistenceImpl.class);

	private FinderPath _createFinderPath(
		String cacheName, String methodName, String[] params,
		String[] columnNames, boolean baseModelResult) {

		FinderPath finderPath = new FinderPath(
			cacheName, methodName, params, columnNames, baseModelResult);

		if (!cacheName.equals(FINDER_CLASS_NAME_LIST_WITH_PAGINATION)) {
			_serviceRegistrations.add(
				_bundleContext.registerService(
					FinderPath.class, finderPath,
					MapUtil.singletonDictionary("cache.name", cacheName)));
		}

		return finderPath;
	}

	private Set<ServiceRegistration<FinderPath>> _serviceRegistrations =
		new HashSet<>();
	private ServiceRegistration<ArgumentsResolver>
		_argumentsResolverServiceRegistration;

	private static class RegistrationLogModelArgumentsResolver
		implements ArgumentsResolver {

		@Override
		public Object[] getArguments(
			FinderPath finderPath, BaseModel<?> baseModel, boolean checkColumn,
			boolean original) {

			String[] columnNames = finderPath.getColumnNames();

			if ((columnNames == null) || (columnNames.length == 0)) {
				if (baseModel.isNew()) {
					return new Object[0];
				}

				return null;
			}

			RegistrationLogModelImpl registrationLogModelImpl =
				(RegistrationLogModelImpl)baseModel;

			long columnBitmask = registrationLogModelImpl.getColumnBitmask();

			if (!checkColumn || (columnBitmask == 0)) {
				return _getValue(
					registrationLogModelImpl, columnNames, original);
			}

			Long finderPathColumnBitmask = _finderPathColumnBitmasksCache.get(
				finderPath);

			if (finderPathColumnBitmask == null) {
				finderPathColumnBitmask = 0L;

				for (String columnName : columnNames) {
					finderPathColumnBitmask |=
						registrationLogModelImpl.getColumnBitmask(columnName);
				}

				if (finderPath.isBaseModelResult() &&
					(RegistrationLogPersistenceImpl.
						FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION ==
							finderPath.getCacheName())) {

					finderPathColumnBitmask |= _ORDER_BY_COLUMNS_BITMASK;
				}

				_finderPathColumnBitmasksCache.put(
					finderPath, finderPathColumnBitmask);
			}

			if ((columnBitmask & finderPathColumnBitmask) != 0) {
				return _getValue(
					registrationLogModelImpl, columnNames, original);
			}

			return null;
		}

		private static Object[] _getValue(
			RegistrationLogModelImpl registrationLogModelImpl,
			String[] columnNames, boolean original) {

			Object[] arguments = new Object[columnNames.length];

			for (int i = 0; i < arguments.length; i++) {
				String columnName = columnNames[i];

				if (original) {
					arguments[i] =
						registrationLogModelImpl.getColumnOriginalValue(
							columnName);
				}
				else {
					arguments[i] = registrationLogModelImpl.getColumnValue(
						columnName);
				}
			}

			return arguments;
		}

		private static final Map<FinderPath, Long>
			_finderPathColumnBitmasksCache = new ConcurrentHashMap<>();

		private static final long _ORDER_BY_COLUMNS_BITMASK;

		static {
			long orderByColumnsBitmask = 0;

			orderByColumnsBitmask |= RegistrationLogModelImpl.getColumnBitmask(
				"createDate");

			_ORDER_BY_COLUMNS_BITMASK = orderByColumnsBitmask;
		}

	}

}