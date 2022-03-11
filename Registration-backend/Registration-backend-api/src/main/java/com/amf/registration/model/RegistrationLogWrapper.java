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

package com.amf.registration.model;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.wrapper.BaseModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link RegistrationLog}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see RegistrationLog
 * @generated
 */
public class RegistrationLogWrapper
	extends BaseModelWrapper<RegistrationLog>
	implements ModelWrapper<RegistrationLog>, RegistrationLog {

	public RegistrationLogWrapper(RegistrationLog registrationLog) {
		super(registrationLog);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("registrationLogId", getRegistrationLogId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("eventType", getEventType());
		attributes.put("ipAddress", getIpAddress());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long registrationLogId = (Long)attributes.get("registrationLogId");

		if (registrationLogId != null) {
			setRegistrationLogId(registrationLogId);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		String userName = (String)attributes.get("userName");

		if (userName != null) {
			setUserName(userName);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		String eventType = (String)attributes.get("eventType");

		if (eventType != null) {
			setEventType(eventType);
		}

		String ipAddress = (String)attributes.get("ipAddress");

		if (ipAddress != null) {
			setIpAddress(ipAddress);
		}
	}

	/**
	 * Returns the company ID of this registration log.
	 *
	 * @return the company ID of this registration log
	 */
	@Override
	public long getCompanyId() {
		return model.getCompanyId();
	}

	/**
	 * Returns the create date of this registration log.
	 *
	 * @return the create date of this registration log
	 */
	@Override
	public Date getCreateDate() {
		return model.getCreateDate();
	}

	/**
	 * Returns the event type of this registration log.
	 *
	 * @return the event type of this registration log
	 */
	@Override
	public String getEventType() {
		return model.getEventType();
	}

	/**
	 * Returns the group ID of this registration log.
	 *
	 * @return the group ID of this registration log
	 */
	@Override
	public long getGroupId() {
		return model.getGroupId();
	}

	/**
	 * Returns the ip address of this registration log.
	 *
	 * @return the ip address of this registration log
	 */
	@Override
	public String getIpAddress() {
		return model.getIpAddress();
	}

	/**
	 * Returns the modified date of this registration log.
	 *
	 * @return the modified date of this registration log
	 */
	@Override
	public Date getModifiedDate() {
		return model.getModifiedDate();
	}

	/**
	 * Returns the primary key of this registration log.
	 *
	 * @return the primary key of this registration log
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the registration log ID of this registration log.
	 *
	 * @return the registration log ID of this registration log
	 */
	@Override
	public long getRegistrationLogId() {
		return model.getRegistrationLogId();
	}

	/**
	 * Returns the user ID of this registration log.
	 *
	 * @return the user ID of this registration log
	 */
	@Override
	public long getUserId() {
		return model.getUserId();
	}

	/**
	 * Returns the user name of this registration log.
	 *
	 * @return the user name of this registration log
	 */
	@Override
	public String getUserName() {
		return model.getUserName();
	}

	/**
	 * Returns the user uuid of this registration log.
	 *
	 * @return the user uuid of this registration log
	 */
	@Override
	public String getUserUuid() {
		return model.getUserUuid();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the company ID of this registration log.
	 *
	 * @param companyId the company ID of this registration log
	 */
	@Override
	public void setCompanyId(long companyId) {
		model.setCompanyId(companyId);
	}

	/**
	 * Sets the create date of this registration log.
	 *
	 * @param createDate the create date of this registration log
	 */
	@Override
	public void setCreateDate(Date createDate) {
		model.setCreateDate(createDate);
	}

	/**
	 * Sets the event type of this registration log.
	 *
	 * @param eventType the event type of this registration log
	 */
	@Override
	public void setEventType(String eventType) {
		model.setEventType(eventType);
	}

	/**
	 * Sets the group ID of this registration log.
	 *
	 * @param groupId the group ID of this registration log
	 */
	@Override
	public void setGroupId(long groupId) {
		model.setGroupId(groupId);
	}

	/**
	 * Sets the ip address of this registration log.
	 *
	 * @param ipAddress the ip address of this registration log
	 */
	@Override
	public void setIpAddress(String ipAddress) {
		model.setIpAddress(ipAddress);
	}

	/**
	 * Sets the modified date of this registration log.
	 *
	 * @param modifiedDate the modified date of this registration log
	 */
	@Override
	public void setModifiedDate(Date modifiedDate) {
		model.setModifiedDate(modifiedDate);
	}

	/**
	 * Sets the primary key of this registration log.
	 *
	 * @param primaryKey the primary key of this registration log
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the registration log ID of this registration log.
	 *
	 * @param registrationLogId the registration log ID of this registration log
	 */
	@Override
	public void setRegistrationLogId(long registrationLogId) {
		model.setRegistrationLogId(registrationLogId);
	}

	/**
	 * Sets the user ID of this registration log.
	 *
	 * @param userId the user ID of this registration log
	 */
	@Override
	public void setUserId(long userId) {
		model.setUserId(userId);
	}

	/**
	 * Sets the user name of this registration log.
	 *
	 * @param userName the user name of this registration log
	 */
	@Override
	public void setUserName(String userName) {
		model.setUserName(userName);
	}

	/**
	 * Sets the user uuid of this registration log.
	 *
	 * @param userUuid the user uuid of this registration log
	 */
	@Override
	public void setUserUuid(String userUuid) {
		model.setUserUuid(userUuid);
	}

	@Override
	protected RegistrationLogWrapper wrap(RegistrationLog registrationLog) {
		return new RegistrationLogWrapper(registrationLog);
	}

}