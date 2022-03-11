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

package com.amf.registration.model.impl;

import com.amf.registration.model.RegistrationLog;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing RegistrationLog in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class RegistrationLogCacheModel
	implements CacheModel<RegistrationLog>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof RegistrationLogCacheModel)) {
			return false;
		}

		RegistrationLogCacheModel registrationLogCacheModel =
			(RegistrationLogCacheModel)object;

		if (registrationLogId == registrationLogCacheModel.registrationLogId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, registrationLogId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(19);

		sb.append("{registrationLogId=");
		sb.append(registrationLogId);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", userName=");
		sb.append(userName);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", eventType=");
		sb.append(eventType);
		sb.append(", ipAddress=");
		sb.append(ipAddress);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public RegistrationLog toEntityModel() {
		RegistrationLogImpl registrationLogImpl = new RegistrationLogImpl();

		registrationLogImpl.setRegistrationLogId(registrationLogId);
		registrationLogImpl.setGroupId(groupId);
		registrationLogImpl.setCompanyId(companyId);
		registrationLogImpl.setUserId(userId);

		if (userName == null) {
			registrationLogImpl.setUserName("");
		}
		else {
			registrationLogImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			registrationLogImpl.setCreateDate(null);
		}
		else {
			registrationLogImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			registrationLogImpl.setModifiedDate(null);
		}
		else {
			registrationLogImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (eventType == null) {
			registrationLogImpl.setEventType("");
		}
		else {
			registrationLogImpl.setEventType(eventType);
		}

		if (ipAddress == null) {
			registrationLogImpl.setIpAddress("");
		}
		else {
			registrationLogImpl.setIpAddress(ipAddress);
		}

		registrationLogImpl.resetOriginalValues();

		return registrationLogImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		registrationLogId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		eventType = objectInput.readUTF();
		ipAddress = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(registrationLogId);

		objectOutput.writeLong(groupId);

		objectOutput.writeLong(companyId);

		objectOutput.writeLong(userId);

		if (userName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(userName);
		}

		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);

		if (eventType == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(eventType);
		}

		if (ipAddress == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(ipAddress);
		}
	}

	public long registrationLogId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public String eventType;
	public String ipAddress;

}