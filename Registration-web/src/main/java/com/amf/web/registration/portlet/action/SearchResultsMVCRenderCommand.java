package com.amf.web.registration.portlet.action;

import com.amf.registration.service.persistence.CustomUserFinder;
import com.amf.web.registration.constants.MVCCommandNames;
import com.amf.web.registration.constants.RegistrationPortletKeys;
import com.amf.web.registration.internal.security.permission.resource.SearchUserToplevelPermission;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.service.UserServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.portlet.PortletException;
import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * MVC command for showing the Registration logs list.
 *
 * @author liferay
 */
@Component(immediate = true, property = { "javax.portlet.name=" + RegistrationPortletKeys.SEARCH_RESULTS,
		"mvc.command.name=/",

		"mvc.command.name=" + MVCCommandNames.SEARCH_USER }, service = MVCRenderCommand.class)
public class SearchResultsMVCRenderCommand implements MVCRenderCommand {
	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {
		String eventZipCodeParam = ParamUtil.getString(renderRequest, "eventZipCodeParam", StringPool.BLANK);
		ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
		
		boolean allowSearch = hasPermission(themeDisplay);
		System.out.println("TEC_SEARCH: "+ allowSearch);
		renderRequest.setAttribute("allowSearch", allowSearch);
		if(allowSearch) {
		
			// Resolve start and end for the search.
			int currentPage = ParamUtil.getInteger(renderRequest, SearchContainer.DEFAULT_CUR_PARAM,
					SearchContainer.DEFAULT_CUR);
			int delta = ParamUtil.getInteger(renderRequest, SearchContainer.DEFAULT_DELTA_PARAM,
					SearchContainer.DEFAULT_DELTA);
			int start = ((currentPage > 0) ? (currentPage - 1) : 0) * delta;
			int end = start + delta;
	
			List<User> users = new ArrayList<User>();
	
			long searchCount = 0;
	
			users.addAll(_customUserAddressFinder.findByZip(eventZipCodeParam, start, end));
			searchCount = _customUserAddressFinder.countUserByZip(eventZipCodeParam);
			/*
			 * Creating IteratorURL and in that we will pass tab parameter
			 */
			PortletURL iteratorURL = renderResponse.createRenderURL();
			iteratorURL.setParameter("eventZipCodeParam", eventZipCodeParam);
			
			renderRequest.setAttribute("iteratorURL", iteratorURL);
			renderRequest.setAttribute("searchResults", users);
			renderRequest.setAttribute("searchCount", searchCount);
		}
		return "/search-results/search-results.jsp";
	}
	private boolean hasPermission(ThemeDisplay themeDisplay) {
		return SearchUserToplevelPermission.contains(themeDisplay.getPermissionChecker(),
				themeDisplay.getScopeGroupId(), "TEC_SEARCH");
	}
	@Reference
	private CustomUserFinder _customUserAddressFinder;

}