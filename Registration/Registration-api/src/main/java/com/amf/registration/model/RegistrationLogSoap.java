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

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.amf.registration.service.http.RegistrationLogServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @deprecated As of Athanasius (7.3.x), with no direct replacement
 * @generated
 */
@Deprecated
public class RegistrationLogSoap implements Serializable {

	public static RegistrationLogSoap toSoapModel(RegistrationLog model) {
		RegistrationLogSoap soapModel = new RegistrationLogSoap();

		soapModel.setRegistrationLogId(model.getRegistrationLogId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setEventType(model.getEventType());
		soapModel.setIpAddress(model.getIpAddress());

		return soapModel;
	}

	public static RegistrationLogSoap[] toSoapModels(RegistrationLog[] models) {
		RegistrationLogSoap[] soapModels =
			new RegistrationLogSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static RegistrationLogSoap[][] toSoapModels(
		RegistrationLog[][] models) {

		RegistrationLogSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels =
				new RegistrationLogSoap[models.length][models[0].length];
		}
		else {
			soapModels = new RegistrationLogSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static RegistrationLogSoap[] toSoapModels(
		List<RegistrationLog> models) {

		List<RegistrationLogSoap> soapModels =
			new ArrayList<RegistrationLogSoap>(models.size());

		for (RegistrationLog model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new RegistrationLogSoap[soapModels.size()]);
	}

	public RegistrationLogSoap() {
	}

	public long getPrimaryKey() {
		return _registrationLogId;
	}

	public void setPrimaryKey(long pk) {
		setRegistrationLogId(pk);
	}

	public long getRegistrationLogId() {
		return _registrationLogId;
	}

	public void setRegistrationLogId(long registrationLogId) {
		_registrationLogId = registrationLogId;
	}

	public long getGroupId() {
		return _groupId;
	}

	public void setGroupId(long groupId) {
		_groupId = groupId;
	}

	public long getCompanyId() {
		return _companyId;
	}

	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	public long getUserId() {
		return _userId;
	}

	public void setUserId(long userId) {
		_userId = userId;
	}

	public String getUserName() {
		return _userName;
	}

	public void setUserName(String userName) {
		_userName = userName;
	}

	public Date getCreateDate() {
		return _createDate;
	}

	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
	}

	public String getEventType() {
		return _eventType;
	}

	public void setEventType(String eventType) {
		_eventType = eventType;
	}

	public String getIpAddress() {
		return _ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		_ipAddress = ipAddress;
	}

	private long _registrationLogId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private String _eventType;
	private String _ipAddress;

}