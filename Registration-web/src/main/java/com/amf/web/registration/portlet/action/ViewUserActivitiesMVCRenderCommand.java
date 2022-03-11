package com.amf.web.registration.portlet.action;

import com.amf.registration.model.RegistrationLog;
import com.amf.registration.service.RegistrationLogService;
import com.amf.web.registration.constants.MVCCommandNames;
import com.amf.web.registration.constants.RegistrationPortletKeys;
import com.amf.web.registration.internal.security.permission.resource.RegistrationLogToplevelPermission;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.ArrayList;
import java.util.List;

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
@Component(immediate = true, property = { "javax.portlet.name=" + RegistrationPortletKeys.ADMIN_MONITOR,
		"mvc.command.name=/",
		"mvc.command.name=" + MVCCommandNames.VIEW_USER_ACTIVITIES }, service = MVCRenderCommand.class)
public class ViewUserActivitiesMVCRenderCommand implements MVCRenderCommand {
	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {
		// Add registration logs list related attributes.
		ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
		if (themeDisplay.isSignedIn()) {
			String eventFilter = ParamUtil.getString(renderRequest, "eventFilter", StringPool.BLANK);
			findAllUserActivities(renderRequest, renderResponse, eventFilter);
		}
		return "/monitor/view.jsp";
	}

	/**
	 * Adds assigment list related attributes to the request.
	 *
	 * @param renderRequest
	 */
	private void findAllUserActivities(RenderRequest renderRequest, RenderResponse renderResponse, String eventType) {
		// Resolve start and end for the search.
		int currentPage = ParamUtil.getInteger(renderRequest, SearchContainer.DEFAULT_CUR_PARAM,
				SearchContainer.DEFAULT_CUR);
		int delta = ParamUtil.getInteger(renderRequest, SearchContainer.DEFAULT_DELTA_PARAM,
				SearchContainer.DEFAULT_DELTA);
		int start = ((currentPage > 0) ? (currentPage - 1) : 0) * delta;
		int end = start + delta;

		// Call the service to get the list of assignments.
		List<RegistrationLog> registrationLogs = new ArrayList<RegistrationLog>();

		long count = 10;
		ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);

		long userId = themeDisplay.getUserId();

		// check permission VIEW ALL LOGS?
		if (hasPermission(themeDisplay)) {
			// show all user activities logs
			if (eventType == null || eventType.isEmpty() || eventType.equals("All")) {
				registrationLogs = _registrationLogService.getRegistrationLogs(start, end);

				count = _registrationLogService.getRegistrationLogsCount();
			} else {
				registrationLogs = _registrationLogService.getRegistrationLogsByEventType(eventType, start, end);

				count = _registrationLogService.getRegistrationLogsCountByEventType(eventType);
			}
		} else {
			// show only user's related sactivities logs
			if (eventType == null || eventType.isEmpty() || eventType.equals("All")) {
				registrationLogs = _registrationLogService.getRegistrationLogsByUser(userId, start, end);

				count = _registrationLogService.getRegistrationLogsCountByUser(userId);
			} else {
				registrationLogs = _registrationLogService.getRegistrationLogsByUserEventType(userId, eventType, start,
						end);

				count = _registrationLogService.getRegistrationLogsCountByUserEventType(userId, eventType);
			}
		}
		
		// Creating IteratorURL and in that we will pass tab parameter
		
		PortletURL iteratorURL = renderResponse.createRenderURL();
		iteratorURL.setParameter("eventFilter", eventType);
		
		// Set request attributes.
		renderRequest.setAttribute("iteratorURL", iteratorURL);
		renderRequest.setAttribute("registrationLogs", registrationLogs);
		renderRequest.setAttribute("registrationLogCount", count);

	}

	private boolean hasPermission(ThemeDisplay themeDisplay) {
		return RegistrationLogToplevelPermission.contains(themeDisplay.getPermissionChecker(),
				themeDisplay.getScopeGroupId(), "VIEW_ALL_LOGS");
	}

	@Reference
	protected RegistrationLogService _registrationLogService;

}