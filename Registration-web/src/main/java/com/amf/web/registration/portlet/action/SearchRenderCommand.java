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
@Component(immediate = true, property = { "javax.portlet.name=" + RegistrationPortletKeys.SEARCH,
		"mvc.command.name=/"}, service = MVCRenderCommand.class)
public class SearchRenderCommand implements MVCRenderCommand {
	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {
		
		ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);		
		boolean allowSearch = hasPermission(themeDisplay);
		System.out.println("TEC_SEARCH: "+ allowSearch);
		renderRequest.setAttribute("allowSearch", allowSearch);

		return "/search/search.jsp";
	}
	private boolean hasPermission(ThemeDisplay themeDisplay) {
		return SearchUserToplevelPermission.contains(themeDisplay.getPermissionChecker(),
				themeDisplay.getScopeGroupId(), "TEC_SEARCH");
	}
}