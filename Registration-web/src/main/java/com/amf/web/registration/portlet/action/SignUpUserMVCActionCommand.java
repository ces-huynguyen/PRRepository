package com.amf.web.registration.portlet.action;

import com.amf.registration.exception.CustomRegistrationUserException;
import com.amf.registration.service.validator.CustomRegistrationValidator;
import com.amf.web.registration.constants.MVCCommandNames;
import com.amf.web.registration.constants.RegistrationPortletKeys;
import com.amf.web.registration.utils.RegistrantServiceUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.captcha.CaptchaException;
import com.liferay.portal.kernel.exception.AddressCityException;
import com.liferay.portal.kernel.exception.AddressStreetException;
import com.liferay.portal.kernel.exception.AddressZipException;
import com.liferay.portal.kernel.exception.CompanyMaxUsersException;
import com.liferay.portal.kernel.exception.ContactBirthdayException;
import com.liferay.portal.kernel.exception.ContactNameException;
import com.liferay.portal.kernel.exception.DuplicateOpenIdException;
import com.liferay.portal.kernel.exception.EmailAddressException;
import com.liferay.portal.kernel.exception.GroupFriendlyURLException;
import com.liferay.portal.kernel.exception.NoSuchCountryException;
import com.liferay.portal.kernel.exception.NoSuchListTypeException;
import com.liferay.portal.kernel.exception.NoSuchOrganizationException;
import com.liferay.portal.kernel.exception.NoSuchRegionException;
import com.liferay.portal.kernel.exception.OrganizationParentException;
import com.liferay.portal.kernel.exception.PhoneNumberException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.RegionCodeException;
import com.liferay.portal.kernel.exception.RequiredFieldException;
import com.liferay.portal.kernel.exception.RequiredUserException;
import com.liferay.portal.kernel.exception.TermsOfUseException;
import com.liferay.portal.kernel.exception.UserEmailAddressException;
import com.liferay.portal.kernel.exception.UserIdException;
import com.liferay.portal.kernel.exception.UserPasswordException;
import com.liferay.portal.kernel.exception.UserReminderQueryException;
import com.liferay.portal.kernel.exception.UserScreenNameException;
import com.liferay.portal.kernel.exception.UserSmsException;
import com.liferay.portal.kernel.exception.WebsiteURLException;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.CompanyConstants;
import com.liferay.portal.kernel.model.Contact;
import com.liferay.portal.kernel.model.ListType;
import com.liferay.portal.kernel.model.ListTypeConstants;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.PortletURLFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.security.auth.PrincipalThreadLocal;
import com.liferay.portal.kernel.security.auth.session.AuthenticatedSessionManager;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.PermissionThreadLocal;
import com.liferay.portal.kernel.service.AddressLocalServiceUtil;
import com.liferay.portal.kernel.service.ListTypeServiceUtil;
import com.liferay.portal.kernel.service.PhoneLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.login.web.constants.LoginPortletKeys;

import java.util.List;
import java.util.Locale;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletMode;
import javax.portlet.PortletModeException;
import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;
import javax.portlet.WindowState;
import javax.portlet.WindowStateException;
import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(immediate = true, property = { "javax.portlet.name=" + RegistrationPortletKeys.REGISTRATION,
		"mvc.command.name=" + MVCCommandNames.SIGN_UP_USER }, service = MVCActionCommand.class)
public class SignUpUserMVCActionCommand extends BaseMVCActionCommand {

	final long USA_COUNTRY_CODE = 19;

	protected void addUser(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
		
		HttpServletRequest httpServletRequest = _portal.getHttpServletRequest(
				actionRequest);
		String userName = ParamUtil.getString(actionRequest, "userName", StringPool.BLANK);
		customRegistrationValidator.validateUsername(userName);
		String firstName = ParamUtil.getString(actionRequest, "firstName", StringPool.BLANK);
		customRegistrationValidator.validateFirstName(firstName);

		String lastName = ParamUtil.getString(actionRequest, "lastName", StringPool.BLANK);
		customRegistrationValidator.validateLastName(lastName);

		String email = ParamUtil.getString(actionRequest, "emailAddress", StringPool.BLANK);
		customRegistrationValidator.validateEmail(email);

		Boolean male = ParamUtil.getBoolean(actionRequest, "male");
		String password1 = ParamUtil.getString(actionRequest, "password1", StringPool.BLANK);
		String password2 = ParamUtil.getString(actionRequest, "password2", StringPool.BLANK);
		customRegistrationValidator.validatePassword(password1, password2);

		int birthdayMonth = ParamUtil.getInteger(actionRequest, "birthdayMonth");
		int birthdayDay = ParamUtil.getInteger(actionRequest, "birthdayDay");
		int birthdayYear = ParamUtil.getInteger(actionRequest, "birthdayYear");
		final int ACCEPTED_YEAR = 13;
		customRegistrationValidator.validateAge(birthdayDay, birthdayMonth, birthdayYear, ACCEPTED_YEAR);

		// validation password regex
		// validate password the same
		String homePhone = ParamUtil.getString(actionRequest, "homePhone", StringPool.BLANK);
		customRegistrationValidator.validateHomePhone(homePhone);

		String mobilePhone = ParamUtil.getString(actionRequest, "mobilePhone", StringPool.BLANK);
		customRegistrationValidator.validateMobilePhone(mobilePhone);

		String street1 = ParamUtil.getString(actionRequest, "street1", StringPool.BLANK);
		customRegistrationValidator.validateStreet1(street1);

		String street2 = ParamUtil.getString(actionRequest, "street2", StringPool.BLANK);
		customRegistrationValidator.validateStreet2(street2);

		String city = ParamUtil.getString(actionRequest, "city", StringPool.BLANK);
		customRegistrationValidator.validateCity(city);

		String state = ParamUtil.getString(actionRequest, "addressRegionId");
		customRegistrationValidator.validateState(state);
		// validate state of liferay?
		String zipCode = ParamUtil.getString(actionRequest, "zipCode", StringPool.BLANK);
		customRegistrationValidator.validateZip(zipCode);
		// validate 5 digit number
		String securityQuestion = ParamUtil.getString(actionRequest, "securityQuestion", StringPool.BLANK);
		customRegistrationValidator.validateSecurityQuestion(securityQuestion);
		// validate security required
		String securityAnswer = ParamUtil.getString(actionRequest, "securityAnswer", StringPool.BLANK);
		customRegistrationValidator.validateSecurityQuestionAnswer(securityAnswer);

		Boolean agreedToTermsOfUse = ParamUtil.getBoolean(actionRequest, "acceptTou");
		// check term of use accept
		customRegistrationValidator.validateTermOfUse(agreedToTermsOfUse);

		ServiceContext serviceContext = ServiceContextFactory.getInstance(User.class.getName(), actionRequest);

		int prefixId = 0;
		int suffixId = 0;

		long[] groupIds = new long[] {};
		long[] organizationIds = new long[] {};
		long[] roleIds = new long[] {};

		long[] userGroupIds = new long[] {};
		// Generate primary key for the assignment.
		long companyId = PortalUtil.getDefaultCompanyId();
		Locale locale = themeDisplay.getLocale();
		long newUserId = 0l;

		User user = UserLocalServiceUtil.addUser(newUserId, companyId, false, password1, password2, false, userName,
				email, locale, firstName, "", lastName, prefixId, suffixId, male, birthdayMonth, birthdayDay,
				birthdayYear, "", groupIds, organizationIds, roleIds, userGroupIds, false, serviceContext);
		PermissionChecker permissionCheckerBackup = PermissionThreadLocal.getPermissionChecker();
		PermissionThreadLocal.setPermissionChecker(RegistrantServiceUtil.getAdministratorPermissionChecker(companyId));

		// Note: Exception will be thrown if we don't set the PrinicpalThreadLocal name.
		String principalNameBackup = PrincipalThreadLocal.getName();
		PrincipalThreadLocal.setName(user.getUserId());

		// save Billing address address to user
		updateUserContactInfo(homePhone, mobilePhone, street1, street2, city, Long.parseLong(state), zipCode,
				serviceContext, user);

		user.setAgreedToTermsOfUse(agreedToTermsOfUse);
		user.setReminderQueryAnswer(securityAnswer);
		user.setReminderQueryQuestion(securityQuestion);
		UserLocalServiceUtil.updateUser(user);

		PrincipalThreadLocal.setName(principalNameBackup);
		PermissionThreadLocal.setPermissionChecker(permissionCheckerBackup);
		if (user.getStatus() == WorkflowConstants.STATUS_APPROVED) {
			SessionMessages.add(
				httpServletRequest, "userAdded", user.getEmailAddress());
		}
		else {
			SessionMessages.add(
				httpServletRequest, "userPending", user.getEmailAddress());
		}
		sendRedirect(
				actionRequest, actionResponse, themeDisplay, user,
				user.getPasswordUnencrypted());
	}

	private void updateUserContactInfo(String homePhone, String mobilePhone, String street1, String street2,
			String city, Long state, String zipCode, ServiceContext serviceContext, User user) throws Exception {

		AddressLocalServiceUtil.addAddress(user.getUserId(), Contact.class.getName(), user.getContactId(), street1,
				street2, "", city, zipCode, state, USA_COUNTRY_CODE, _getListTypeId(ListTypeConstants.CONTACT_ADDRESS),
				false, true, serviceContext);
		PhoneLocalServiceUtil.addPhone(user.getUserId(), Contact.class.getName(), user.getContactId(), homePhone, "",
				_getListTypeId(ListTypeConstants.CONTACT_PHONE), true, serviceContext);
		PhoneLocalServiceUtil.addPhone(user.getUserId(), Contact.class.getName(), user.getContactId(), mobilePhone, "",
				_getListTypeId(ListTypeConstants.CONTACT_PHONE), false, serviceContext);
	}

	@Override
	protected void doProcessAction(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {
		try {
			addUser(actionRequest, actionResponse);
		} catch (PortalException exception) {
			if (exception instanceof AddressCityException || exception instanceof AddressStreetException
					|| exception instanceof AddressZipException || exception instanceof CaptchaException
					|| exception instanceof CompanyMaxUsersException || exception instanceof ContactBirthdayException
					|| exception instanceof ContactNameException || exception instanceof DuplicateOpenIdException
					|| exception instanceof EmailAddressException || exception instanceof GroupFriendlyURLException
					|| exception instanceof NoSuchCountryException || exception instanceof NoSuchListTypeException
					|| exception instanceof NoSuchOrganizationException || exception instanceof NoSuchRegionException
					|| exception instanceof OrganizationParentException || exception instanceof PhoneNumberException
					|| exception instanceof RequiredFieldException || exception instanceof RequiredUserException
					|| exception instanceof TermsOfUseException || exception instanceof UserEmailAddressException
					|| exception instanceof UserIdException || exception instanceof UserPasswordException
					|| exception instanceof UserScreenNameException || exception instanceof UserSmsException
					|| exception instanceof UserReminderQueryException || exception instanceof RegionCodeException
					|| exception instanceof CustomRegistrationUserException
					|| exception instanceof WebsiteURLException) {

				SessionErrors.add(actionRequest, exception.getClass(), exception);
			} else {
				exception.printStackTrace();
				throw exception;
			}
		}
	}
	
	protected void sendRedirect(
			ActionRequest actionRequest, ActionResponse actionResponse,
			ThemeDisplay themeDisplay, User user, String password)
		throws Exception {

		String login = null;

		Company company = themeDisplay.getCompany();

		String authType = company.getAuthType();

		if (authType.equals(CompanyConstants.AUTH_TYPE_ID)) {
			login = String.valueOf(user.getUserId());
		}
		else if (authType.equals(CompanyConstants.AUTH_TYPE_SN)) {
			login = user.getScreenName();
		}
		else {
			login = user.getEmailAddress();
		}

		HttpServletRequest httpServletRequest = _portal.getHttpServletRequest(
			actionRequest);

		String redirect = _portal.escapeRedirect(
			ParamUtil.getString(actionRequest, "redirect"));

		if (Validator.isNotNull(redirect)) {
			_authenticatedSessionManager.login(
				httpServletRequest,
				_portal.getHttpServletResponse(actionResponse), login, password,
				false, null);
		}
		else {
			PortletURL loginURL = _getLoginURL(
				httpServletRequest, themeDisplay.getPlid());

			loginURL.setParameter("login", login);

			redirect = loginURL.toString();
		}

		actionResponse.sendRedirect(redirect);
	}
	
	private static PortletURL _getLoginURL(
			HttpServletRequest httpServletRequest, long plid)
		throws PortletModeException, WindowStateException {

		PortletURL portletURL = PortletURLFactoryUtil.create(
			httpServletRequest, LoginPortletKeys.LOGIN, plid,
			PortletRequest.RENDER_PHASE);

		portletURL.setParameter("saveLastPath", Boolean.FALSE.toString());
		portletURL.setParameter("mvcRenderCommandName", "/login/login");
		portletURL.setPortletMode(PortletMode.VIEW);
		portletURL.setWindowState(WindowState.MAXIMIZED);

		return portletURL;
	}
	
	private static long _getListTypeId(String type) throws Exception {
		List<ListType> listTypes = ListTypeServiceUtil.getListTypes(type);

		ListType listType = listTypes.get(0);

		return listType.getListTypeId();
	}
	

	@Reference
	protected CustomRegistrationValidator customRegistrationValidator;
	@Reference
	private AuthenticatedSessionManager _authenticatedSessionManager;
	@Reference
	private Portal _portal;

}
