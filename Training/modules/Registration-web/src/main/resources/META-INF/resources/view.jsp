<%@page import="com.liferay.portal.kernel.exception.RegionCodeException"%>
<%@ include file="/init.jsp"%>
<%
	long addressCountryId = 19;//USA country code

	boolean male = true;

	Calendar birthdayCalendar = CalendarFactoryUtil.getCalendar();

%>
<c:if test="<%=themeDisplay.isSignedIn()%>">
	<liferay-ui:message key="user-already-registered-x" arguments="<%= themeDisplay.getUser().getEmailAddress()%>"/>
</c:if>
<c:if test="<%=!themeDisplay.isSignedIn()%>">
	<div class="container-fluid-1280">
		<portlet:actionURL var="signUpNewUser"
			name="<%=MVCCommandNames.SIGN_UP_USER%>">
			<portlet:param name="redirect" value="${param.redirect}" />
		</portlet:actionURL>
		<aui:form action="${signUpNewUser}" name="fm" method="post"
			validateOnBlur="<%=true %>">
			
			<liferay-ui:error
				exception="<%=UserReminderQueryException.class%>"
				message="please select security question" />
			<liferay-ui:error
				focusField="birthday"
				exception="<%=CustomRegistrationUserException.UserAgeInvalid.class%>">
					<%
					CustomRegistrationUserException.UserAgeInvalid upe = (CustomRegistrationUserException.UserAgeInvalid) errorException;
				%>
				<liferay-ui:message arguments="<%=String.valueOf(upe.minAge)%>"
					key="user-must-be-over-x"
					translateArguments="<%=false%>" />
					
			</liferay-ui:error>
			<liferay-ui:error
				exception="<%=CustomRegistrationUserException.UserNameMaxLengthInvalid.class%>">
				
				<%
					CustomRegistrationUserException.UserNameMaxLengthInvalid upe = (CustomRegistrationUserException.UserNameMaxLengthInvalid) errorException;
				%>
				<liferay-ui:message arguments="<%=String.valueOf(upe.maxLength)%>"
					key="username-must-not-be-more-than-x-characters"
					translateArguments="<%=false%>" />
			</liferay-ui:error>
			<liferay-ui:error
				exception="<%=CustomRegistrationUserException.UserNameMinLengthInvalid.class%>">
				
				<%
					CustomRegistrationUserException.UserNameMinLengthInvalid upe = (CustomRegistrationUserException.UserNameMinLengthInvalid) errorException;
				%>
				<liferay-ui:message arguments="<%=String.valueOf(upe.minLength)%>"
					key="username-must-be-atleast-x-characters"
					translateArguments="<%=false%>" />
			</liferay-ui:error>
			
			<liferay-ui:error
				exception="<%=CustomRegistrationUserException.FirstNameMaxLengthInvalid.class%>">
				
				<%
					CustomRegistrationUserException.FirstNameMaxLengthInvalid upe = (CustomRegistrationUserException.FirstNameMaxLengthInvalid) errorException;
				%>
				<liferay-ui:message arguments="<%=String.valueOf(upe.maxLength)%>"
					key="first-name-must-not-be-more-than-x-characters"
					translateArguments="<%=false%>" />
			</liferay-ui:error>
						<liferay-ui:error
				exception="<%=CustomRegistrationUserException.LastNameMaxLengthInvalid.class%>">
				
				<%
					CustomRegistrationUserException.LastNameMaxLengthInvalid upe = (CustomRegistrationUserException.LastNameMaxLengthInvalid) errorException;
				%>
				<liferay-ui:message arguments="<%=String.valueOf(upe.maxLength)%>"
					key="last-name-must-not-be-more-than-x-characters"
					translateArguments="<%=false%>" />
			</liferay-ui:error>
			
			<liferay-ui:error exception="<%=AddressCityException.class%>"
				message="please-enter-a-valid-city" />
			<liferay-ui:error exception="<%=AddressStreetException.class%>"
				message="please-enter-a-valid-street" />
			<liferay-ui:error exception="<%=AddressZipException.class%>"
				message="please-enter-a-valid-postal-code" />
			<liferay-ui:error exception="<%=CompanyMaxUsersException.class%>"
				message="unable-to-create-user-account-because-the-maximum-number-of-users-has-been-reached" />
			<liferay-ui:error exception="<%=ContactBirthdayException.class%>"
				message="please-enter-a-valid-birthday" />
			<liferay-ui:error
				exception="<%=ContactNameException.MustHaveFirstName.class%>"
				message="please-enter-a-valid-first-name" />
			<liferay-ui:error
				exception="<%=ContactNameException.MustHaveLastName.class%>"
				message="please-enter-a-valid-last-name" />
			<liferay-ui:error
				exception="<%=ContactNameException.MustHaveValidFullName.class%>"
				message="please-enter-a-valid-first-middle-and-last-name" />
			<liferay-ui:error exception="<%=EmailAddressException.class%>"
				message="please-enter-a-valid-email-address" />
			<liferay-ui:error exception="<%=NoSuchRegionException.class%>"
				message="please-select-a-region" />
			<liferay-ui:error exception="<%=PhoneNumberException.class%>"
				message="please-enter-a-valid-phone-number" />
			<liferay-ui:error
				exception="<%=PhoneNumberExtensionException.class%>"
				message="please-enter-a-valid-phone-number-extension" />
			<liferay-ui:error exception="<%=RequiredFieldException.class%>"
				message="please-fill-out-all-required-fields" />
			<liferay-ui:error exception="<%=TermsOfUseException.class%>"
				message="you-must-agree-to-the-terms-of-use" />
			<liferay-ui:error
				exception="<%=UserEmailAddressException.MustNotBeDuplicate.class%>"
				message="the-email-address-you-requested-is-already-taken" />
			<liferay-ui:error
				exception="<%=UserEmailAddressException.MustNotBeNull.class%>"
				message="please-enter-an-email-address" />
			<liferay-ui:error
				exception="<%=UserEmailAddressException.MustNotBePOP3User.class%>"
				message="the-email-address-you-requested-is-reserved" />
			<liferay-ui:error
				exception="<%=UserEmailAddressException.MustNotBeReserved.class%>"
				message="the-email-address-you-requested-is-reserved" />
			<liferay-ui:error
				exception="<%=UserEmailAddressException.MustNotUseCompanyMx.class%>"
				message="the-email-address-you-requested-is-not-valid-because-its-domain-is-reserved" />
			<liferay-ui:error
				exception="<%=UserEmailAddressException.MustValidate.class%>"
				message="please-enter-a-valid-email-address" />
			<liferay-ui:error
				exception="<%=UserIdException.MustNotBeNull.class%>"
				message="please-enter-a-user-id" />
			<liferay-ui:error
				exception="<%=UserIdException.MustNotBeReserved.class%>"
				message="the-user-id-you-requested-is-reserved" />

			<liferay-ui:error
				exception="<%=UserPasswordException.MustBeLonger.class%>">

				<%
					UserPasswordException.MustBeLonger upe = (UserPasswordException.MustBeLonger) errorException;
				%>

				<liferay-ui:message arguments="<%=String.valueOf(upe.minLength)%>"
					key="that-password-is-too-short" translateArguments="<%=false%>" />
			</liferay-ui:error>

			<liferay-ui:error
				exception="<%=UserPasswordException.MustComplyWithModelListeners.class%>"
				message="that-password-is-invalid-please-enter-a-different-password" />

			<liferay-ui:error
				exception="<%=UserPasswordException.MustComplyWithRegex.class%>">

				<%
					UserPasswordException.MustComplyWithRegex upe = (UserPasswordException.MustComplyWithRegex) errorException;
				%>

				<liferay-ui:message arguments="<%=HtmlUtil.escape(upe.regex)%>"
					key="that-password-does-not-comply-with-the-regular-expression"
					translateArguments="<%=false%>" />
			</liferay-ui:error>

			<liferay-ui:error
				exception="<%=UserPasswordException.MustMatch.class%>"
				message="the-passwords-you-entered-do-not-match" />
			<liferay-ui:error
				exception="<%=UserPasswordException.MustNotBeNull.class%>"
				message="the-password-cannot-be-blank" />
			<liferay-ui:error
				exception="<%=UserPasswordException.MustNotBeTrivial.class%>"
				message="that-password-uses-common-words-please-enter-a-password-that-is-harder-to-guess-i-e-contains-a-mix-of-numbers-and-letters" />
			<liferay-ui:error
				exception="<%=UserPasswordException.MustNotContainDictionaryWords.class%>"
				message="that-password-uses-common-dictionary-words" />
			<liferay-ui:error
				exception="<%=UserPasswordException.MustHaveMoreNumbers.class%>"
				message="that-password-must-contain-at-least-x-numbers" />
			<liferay-ui:error
				exception="<%=UserPasswordException.MustHaveMoreLowercase.class%>"
				message="that-password-must-contain-at-least-x-lowercase-characters" />
			<liferay-ui:error
				exception="<%=UserPasswordException.MustHaveMoreUppercase.class%>"
				message="that-password-must-contain-at-least-x-uppercase-characters" />
			<liferay-ui:error
				exception="<%=UserPasswordException.MustHaveMoreSymbols.class%>"
				message="that-password-must-contain-at-least-x-symbols" />

			<liferay-ui:error
				exception="<%=UserScreenNameException.MustNotBeDuplicate.class%>"
				focusField="userName"
				message="the-screen-name-you-requested-is-already-taken" />
			<liferay-ui:error
				exception="<%=UserScreenNameException.MustNotBeNull.class%>"
				focusField="userName" message="the-screen-name-cannot-be-blank" />
			<liferay-ui:error
				exception="<%=UserScreenNameException.MustNotBeNumeric.class%>"
				focusField="userName"
				message="the-screen-name-cannot-contain-only-numeric-values" />
			<liferay-ui:error
				exception="<%=UserScreenNameException.MustNotBeReserved.class%>"
				message="the-screen-name-you-requested-is-reserved" />
			<liferay-ui:error
				exception="<%=UserScreenNameException.MustNotBeReservedForAnonymous.class%>"
				focusField="userName"
				message="the-screen-name-you-requested-is-reserved-for-the-anonymous-user" />
			<liferay-ui:error
				exception="<%=UserScreenNameException.MustNotBeUsedByGroup.class%>"
				focusField="userName"
				message="the-screen-name-you-requested-is-already-taken-by-a-site" />
			<liferay-ui:error
				exception="<%=UserScreenNameException.MustProduceValidFriendlyURL.class%>"
				focusField="userName"
				message="the-screen-name-you-requested-must-produce-a-valid-friendly-url" />
			<liferay-ui:error
				exception="<%=RegionCodeException.class%>"
				focusField="addressRegionId"
				message="please-select-a-region" />

			<liferay-ui:error
				exception="<%=UserScreenNameException.MustValidate.class%>"
				focusField="userName">

				<%
					UserScreenNameException.MustValidate usne = (UserScreenNameException.MustValidate) errorException;
				%>
				<liferay-ui:message
					key="<%=usne.screenNameValidator.getDescription(locale)%>" />
			</liferay-ui:error>
			<aui:fieldset-group markupView="lexicon">
				<aui:fieldset label="registration-information">
					<aui:input label="screen-name" name="userName" value="xxxxx">
						<aui:validator name="required" />
					</aui:input>
					<div class="form-group-autofit">
						<div class="form-group-item">
							<aui:input label="first-name" name="firstName" value="xxxxx">
								<aui:validator name="required" />
							</aui:input>
						</div>
						<div class="form-group-item">
							<aui:input label="last-name" name="lastName" value="xxxxx">
								<aui:validator name="required"></aui:validator>
							</aui:input>
						</div>
					</div>
					<aui:input name="emailAddress" label="primary-email" value="xxxxx@gmail.com">
						<aui:validator name="required" />
						<aui:validator name="email" />
					</aui:input>
					<div class="form-group-autofit">
						<div class="form-group-item">
							<aui:input label="password" name="password1" size="30"
								type="password" value="Ces@123">
								<aui:validator name="required" />
							</aui:input>
						</div>
						<div class="form-group-item">
							<aui:input label="enter-again" name="password2" size="30"
								type="password" value="Ces@123">
								<aui:validator name="equalTo">
						'#<portlet:namespace />password1'
					</aui:validator>
								<aui:validator name="required" />
							</aui:input>
						</div>
					</div>
					<aui:input name="birthday" model="<%=Contact.class%>"
						value="<%=birthdayCalendar%>">
						<aui:validator name="required" />
					</aui:input>

					<aui:select label="gender" name="male">
						<aui:option label="male" value="1" />
						<aui:option label="female" selected="<%=!male%>" value="0" />\
					<aui:validator name="required" />
					</aui:select>

				</aui:fieldset>
			</aui:fieldset-group>
			<%--Phone --%>
			<aui:fieldset-group markupView="lexicon">
				<aui:fieldset label="contact-information">

					<div class="form-group-autofit">
						<div class="form-group-item">
							<aui:input name="homePhone" value="0935615234"></aui:input>
						</div>
						<div class="form-group-item">
							<aui:input name="mobilePhone"  value="0935615234"></aui:input>
						</div>
					</div>

				</aui:fieldset>
			</aui:fieldset-group>
			<%--Billing address --%>
			<aui:fieldset-group markupView="lexicon">
				<aui:fieldset label="billing-address">
					<div class="form-group-autofit">
						<div class="form-group-item">
							<aui:input label="street1" name="street1"  value="Nguyen ba hoc">
								<aui:validator name="required" />
							</aui:input>
						</div>

						<div class="form-group-item">
							<aui:input name="street2" label="street2" value="Binh minh 03"></aui:input>
						</div>
					</div>
					<div class="form-group-autofit">
						<div class="form-group-item">
							<aui:input name="city" label="city" required="<%=true%>" value="Da nang"/>
						</div>

						<div class="form-group-item">
							<aui:select label="region" name="addressRegionId"
								required="<%=true%>">
								<aui:validator
									errorMessage='<%=LanguageUtil.get(request, "this-field-is-required")%>'
									name="state">
									function(val, fieldNode) {
										if (fieldNode.length === 1) {
											return true;
										}
			
										if (Number(val) !== 0) {
											return true;
										}
			
										return false;
									}
								</aui:validator>
							</aui:select>
						</div>
					</div>

					<div class="form-group-autofit">
						<div class="form-group-item">
							<aui:input label="postal-code" name="zipCode" value="22222"
								required="<%=true%>" />
						</div>


					</div>



				</aui:fieldset>
			</aui:fieldset-group>
			<%--misc --%>
			<aui:fieldset-group markupView="lexicon">
				<aui:fieldset label="password-reminder">
					<aui:select label="reminder-query-question" id="securityQuestion"
						name="securityQuestion">
						<aui:option value="" selected="true" disabled="true">
							<liferay-ui:message key="security.question.header" />
						</aui:option>
						<aui:option
							value="<%=LanguageUtil.get(request, "security.question1")%>">
							<liferay-ui:message key="security.question1" />
						</aui:option>
						<aui:option
							value="<%=LanguageUtil.get(request, "security.question2")%>">
							<liferay-ui:message key="security.question2" />
						</aui:option>
						<aui:option
							value="<%=LanguageUtil.get(request, "security.question3")%>">
							<liferay-ui:message key="security.question3" />
						</aui:option>
						<aui:option
							value="<%=LanguageUtil.get(request, "security.question4")%>">
							<liferay-ui:message key="security.question4" />
						</aui:option>
						<aui:validator name="required" />

					</aui:select>
					<aui:input name="securityAnswer" label="answer" value="Nguyen ba hoc">
						<aui:validator name="required" />
					</aui:input>

					<aui:input label="termOfUse.text" name="acceptTou" type="checkbox">
						<aui:validator name="required" />
					</aui:input>
				</aui:fieldset>
			</aui:fieldset-group>
			<aui:button-row>
				<aui:button cssClass="btn btn-primary" type="submit" value="Sign up"></aui:button>
				<aui:button cssClass="btn btn-secondary" type="cancel"
					value="Cancel"></aui:button>
			</aui:button-row>
		</aui:form>
	</div>
	<script>
	new Liferay.DynamicSelect([
		{
			select: '',
			selectData: Liferay.Address.getCountries,
			selectDesc: 'nameCurrentValue',
			selectId: 'countryId',
			selectSort: '<%=true%>',
			selectVal: '<%=19L%>',<%--19L because default only US--%>
		},
		{
			select: '<portlet:namespace />addressRegionId',
			selectData: Liferay.Address.getRegions,
			selectDesc: 'name',
			selectDisableOnEmpty: '<%=true%>',
			selectId: 'regionId',
			selectVal: 'regionId',
		},
	]);
</script>

</c:if>