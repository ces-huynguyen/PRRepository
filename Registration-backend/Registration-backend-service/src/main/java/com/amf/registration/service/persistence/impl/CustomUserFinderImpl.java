package com.amf.registration.service.persistence.impl;

import com.amf.registration.service.persistence.CustomUserFinder;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.Order;
import com.liferay.portal.kernel.dao.orm.OrderFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.Address;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;

import java.util.List;

import org.osgi.service.component.annotations.Component;

@Component(service = CustomUserFinder.class)
public class CustomUserFinderImpl implements CustomUserFinder {

	@Override
	public List<User> findByZip(String zip, int start, int end) {

		try {

			ClassLoader classLoader = getClass().getClassLoader();

			DynamicQuery addressQuery = DynamicQueryFactoryUtil.forClass(Address.class, classLoader)
					.add(RestrictionsFactoryUtil.eq("zip", zip)).add(RestrictionsFactoryUtil.eq("primary", true))
					.setProjection(ProjectionFactoryUtil.property("userId"));

			Order order = OrderFactoryUtil.desc("modifiedDate");

			DynamicQuery userQuery = DynamicQueryFactoryUtil.forClass(User.class, classLoader)
					.add(PropertyFactoryUtil.forName("userId").in(addressQuery)).addOrder(order);

			List<User> entries = UserLocalServiceUtil.dynamicQuery(userQuery, start, end);

			return entries;
		} catch (Exception e) {
			try {
				throw new SystemException(e);
			} catch (SystemException se) {
				se.printStackTrace();
			}
		}
		return null;
	}
	private DynamicQuery _buildZipDynamicQuery(String zip, ClassLoader classloader) {
		DynamicQuery addressQuery = DynamicQueryFactoryUtil.forClass(Address.class, classloader)
				.add(RestrictionsFactoryUtil.eq("zip", zip)).add(RestrictionsFactoryUtil.eq("primary", true))
				.setProjection(ProjectionFactoryUtil.property("userId"));

		DynamicQuery userQuery = DynamicQueryFactoryUtil.forClass(User.class, classloader)
				.add(PropertyFactoryUtil.forName("userId").in(addressQuery));
		return userQuery;
	}
	@Override
	public long countUserByZip(String zip) {
		try {

			ClassLoader classLoader = getClass().getClassLoader();

			DynamicQuery userQuery = _buildZipDynamicQuery(zip, classLoader);

			long count = UserLocalServiceUtil.dynamicQueryCount(userQuery);

			return count;
		} catch (Exception e) {
			try {
				throw new SystemException(e);
			} catch (SystemException se) {
				se.printStackTrace();
			}
		}
		return 0;
	}

}