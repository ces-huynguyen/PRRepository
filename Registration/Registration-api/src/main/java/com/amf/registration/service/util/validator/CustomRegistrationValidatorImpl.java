package com.amf.registration.service.util.validator;

import com.amf.registration.service.exceptions.CustomRegistrationUserException;
import com.amf.registration.service.validator.CustomRegistrationValidator;
import com.liferay.petra.string.StringUtil;
import com.liferay.portal.kernel.exception.AddressCityException;
import com.liferay.portal.kernel.exception.AddressStreetException;
import com.liferay.portal.kernel.exception.AddressZipException;
import com.liferay.portal.kernel.exception.ContactNameException;
import com.liferay.portal.kernel.exception.PhoneNumberException;
import com.liferay.portal.kernel.exception.RegionCodeException;
import com.liferay.portal.kernel.exception.TermsOfUseException;
import com.liferay.portal.kernel.exception.UserEmailAddressException;
import com.liferay.portal.kernel.exception.UserPasswordException;
import com.liferay.portal.kernel.exception.UserReminderQueryException;
import com.liferay.portal.kernel.exception.UserScreenNameException;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.security.auth.EmailAddressValidatorFactory;

import java.time.LocalDate;
import java.time.Period;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.osgi.service.component.annotations.Component;

@Component(immediate = true, service = CustomRegistrationValidator.class)
public class CustomRegistrationValidatorImpl implements CustomRegistrationValidator {

	private final int MAX_50_CHARS = 50;
	private final int MAX_255_CHARS = 255;
	private final int MAX_16_CHARS = 16;
	private final int MAX_10_CHARS = 10;
	private final int MIN_4_CHARS = 4;

	// Password policy: minimum 6 characters, must contain one uppercase, one
	// number, one special character
	// digit + lowercase char + uppercase char + punctuation + symbol
	private static final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$";

	@Override
	public void validateTermOfUse(Boolean termOfUse) throws TermsOfUseException {
		if (termOfUse == null || !termOfUse) {
			throw new TermsOfUseException("You must agree with the terms of use");
		}
	}

	@Override
	public void validateAge(int birthDay, int birthMonth, int birthYear, int acceptedAge)
			throws CustomRegistrationUserException {
		// setup
		LocalDate birthDate = LocalDate.of(birthYear, birthMonth, birthDay);
		LocalDate now = LocalDate.now();

		long years = Period.between(birthDate, now).getYears();
		if (years < acceptedAge) {
			throw new CustomRegistrationUserException.UserAgeInvalid(String.format("User must be over %s years old", acceptedAge),
					acceptedAge);
		}
	}

	@Override
	public void validateFirstName(String firstName) throws ContactNameException, CustomRegistrationUserException {
		if (Validator.isNull(firstName) || firstName.trim().isEmpty()) {
			throw new ContactNameException.MustHaveFirstName();
		}
		if (firstName.length() > MAX_50_CHARS) {
			String message = String.format(
					"First name must not be more than %s characters",
					MAX_50_CHARS);
			throw new CustomRegistrationUserException.FirstNameMaxLengthInvalid(message, MAX_50_CHARS);
		}

	}

	@Override
	public void validateLastName(String lastName) throws ContactNameException, CustomRegistrationUserException {

		if (Validator.isNull(lastName) || lastName.trim().isEmpty()) {
			throw new ContactNameException.MustHaveLastName();
		}
		if (lastName.length() > MAX_50_CHARS) {
			String message = String.format(
					"Last name must not be more than %s characters",
					MAX_50_CHARS);
			throw new CustomRegistrationUserException.LastNameMaxLengthInvalid(message, MAX_50_CHARS);
		}
	}

	@Override
	public void validateEmail(String email) throws UserEmailAddressException {
		if (Validator.isNull(email) || email.trim().isEmpty()) {
			throw new UserEmailAddressException.MustNotBeNull();
		}
		if (!Validator.isEmailAddress(email)) {
			throw new UserEmailAddressException.MustValidate(email, EmailAddressValidatorFactory.getInstance());
		}
	}

	@Override
	public void validateUsername(String username) throws UserScreenNameException, CustomRegistrationUserException {
		if (Validator.isNull(username) || username.trim().isEmpty()) {
			throw new UserScreenNameException.MustNotBeNull();
		}
		if (username.length() > MAX_16_CHARS) {
			String message = String.format(
					"Username must not be more than %s characters",
					MAX_16_CHARS);
			throw new CustomRegistrationUserException.UserNameMaxLengthInvalid(message, MAX_16_CHARS);
		}
		if (username.length() < MIN_4_CHARS) {
			String message = String.format(
					"Username must be at least %s characters",
					MIN_4_CHARS);
			throw new CustomRegistrationUserException.UserNameMinLengthInvalid(message, MIN_4_CHARS);
		}
	}

	@Override
	public void validatePassword(String password1, String password2) throws UserPasswordException {
		if (Validator.isNull(password1) || password1.trim().isEmpty()) {
			throw new UserPasswordException.MustNotBeNull(0);
		}
		if (!password1.equals(password2)) {
			throw new UserPasswordException.MustMatch(0);
		}
		// regex check
		_passwordValidates(password1);
	}

	@Override
	public void validateHomePhone(String homePhone) throws PhoneNumberException {
		if (Validator.isNull(homePhone)) {
			throw new PhoneNumberException("Home phone must not be null");
		}
		if (Validator.isNotNull(homePhone) && homePhone.length() > MAX_10_CHARS) {
			throw new PhoneNumberException("home phone max length invalid");
		}
	}

	@Override
	public void validateMobilePhone(String mobilePhone) throws PhoneNumberException {
		if (Validator.isNotNull(mobilePhone) && mobilePhone.length() > MAX_10_CHARS) {
			throw new PhoneNumberException("mobile phone max length invalid");
		}
	}

	@Override
	public void validateStreet1(String street1) throws AddressStreetException {
		if (Validator.isNull(street1) || street1.trim().isEmpty()) {
			throw new AddressStreetException("Street 1 must not be null");
		}
		if (street1.length() > MAX_255_CHARS) {
			throw new AddressStreetException("Street 1 max length invalid");
		}

	}

	@Override
	public void validateStreet2(String street2) throws AddressStreetException {
		if (Validator.isNotNull(street2) && street2.length() > MAX_255_CHARS) {
			throw new AddressStreetException("Street 2 max length invalid");
		}
	}

	@Override
	public void validateCity(String city) throws AddressCityException {
		if (Validator.isNull(city) || city.trim().isEmpty()) {
			throw new AddressCityException("City must not be null");
		}
		if (city.length() > MAX_255_CHARS) {
			throw new AddressCityException("City  max length invalid");
		}
	}

	@Override
	public void validateZip(String zipCode) throws AddressZipException {
		if (Validator.isNull(zipCode) || zipCode.trim().isEmpty()) {
			throw new AddressZipException("Zipcode must not be null");
		}
		if (!Validator.isDigit(zipCode)) {
			throw new AddressZipException("Zipcode must be number");
		}

		if (zipCode.length() > 5) {
			throw new AddressZipException("Zipcode max length invalid");
		}
	}

	@Override
	public void validateState(String stateCode) throws RegionCodeException {
		if (Validator.isNull(stateCode) || stateCode.trim().isEmpty() || stateCode.trim().equals("0")) {
			throw new RegionCodeException("State code must not be empty");
		}
	}

	@Override
	public void validateSecurityQuestion(String question) throws UserReminderQueryException {
		if (Validator.isNull(question) || question.trim().isEmpty()) {
			throw new UserReminderQueryException("Security question must not be null");
		}
	}

	/**
	 * Ensure that we have at least three out of four password criteria met. This
	 * would far more complicated to achieve using standard regular expressions.
	 */
	private void _passwordValidates(String password) throws UserPasswordException {
		Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
		Matcher matcher = pattern.matcher(password);
		if (matcher.matches()) {

		}
		if (6 <= password.length() && password.length() <= 32) {

			if (!password.matches("^(?=.*[0-9]).{6,32}$")) {
				throw new UserPasswordException.MustHaveMoreNumbers(1);
			}
			if (!password.matches("^(?=.*[0-9])(?=.*[a-z]).{6,32}$")) {
				throw new UserPasswordException.MustHaveMoreLowercase(1);
			}

			if (!password.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{6,32}$")) {
				throw new UserPasswordException.MustHaveMoreUppercase(1);
			}

			if (!password.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?*~$^+=<>]).{6,32}$")) {
				throw new UserPasswordException.MustHaveMoreSymbols(1);
			}

		} else {
			throw new UserPasswordException.MustBeLonger(0, 6);
		}

	}

	@Override
	public void validateSecurityQuestionAnswer(String answer) throws UserReminderQueryException {
		if (Validator.isNull(answer) || answer.trim().isEmpty()) {
			throw new UserReminderQueryException("Security answer must not be null");
		}
		if (answer.length() > MAX_255_CHARS) {
			throw new UserReminderQueryException("Securityanswer  max length invalid");
		}
	}
}
