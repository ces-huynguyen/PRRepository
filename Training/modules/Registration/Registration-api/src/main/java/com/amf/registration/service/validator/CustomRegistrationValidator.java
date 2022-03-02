package com.amf.registration.service.validator;

import com.amf.registration.service.exceptions.CustomRegistrationUserException;
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

public interface CustomRegistrationValidator {
	public void validateAge(int birthdayDay, int birthdayMonth, int birthdayYear, int acceptedAge) throws CustomRegistrationUserException;
	public void validateTermOfUse(Boolean termOfUse) throws TermsOfUseException;
	public void validateFirstName(String firstName) throws ContactNameException, CustomRegistrationUserException;
	public void validateLastName(String lastName) throws ContactNameException, CustomRegistrationUserException;
	public void validateEmail(String email) throws UserEmailAddressException;
	public void validateUsername(String username) throws UserScreenNameException, CustomRegistrationUserException;
	public void validatePassword(String password1, String password2) throws UserPasswordException;
	public void validateHomePhone(String homePhone) throws PhoneNumberException;
	public void validateMobilePhone(String mobilePhone) throws PhoneNumberException;
	public void validateStreet1(String street1) throws AddressStreetException;
	public void validateStreet2(String street2) throws AddressStreetException;
	public void validateCity(String city) throws AddressCityException;
	public void validateZip(String zipCode) throws AddressZipException;
	public void validateState(String stateCode) throws RegionCodeException;
	public void validateSecurityQuestion(String question) throws UserReminderQueryException;	
	public void validateSecurityQuestionAnswer(String answer) throws UserReminderQueryException;	

	
}
