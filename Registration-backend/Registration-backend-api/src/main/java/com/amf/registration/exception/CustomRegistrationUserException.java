package com.amf.registration.exception;

import com.liferay.portal.kernel.exception.PortalException;

public class CustomRegistrationUserException extends PortalException {

	public CustomRegistrationUserException(String msg) {
		super(msg);
	}

	public static class UserNameMinLengthInvalid extends CustomRegistrationUserException {

		public UserNameMinLengthInvalid(String message, int minLength) {
			super(message);
			this.minLength = minLength;
		}

		public final int minLength;
	}

	public static class UserNameMaxLengthInvalid extends CustomRegistrationUserException {

		public UserNameMaxLengthInvalid(String message, int maxLength) {
			super(message);
			this.maxLength = maxLength;
		}

		public final int maxLength;
	}

	public static class UserAgeInvalid extends CustomRegistrationUserException {

		public UserAgeInvalid(String message, int minAge) {
			super(message);
			this.minAge = minAge;
		}

		public final int minAge;

	}

	public static class FirstNameMaxLengthInvalid extends CustomRegistrationUserException {

		public FirstNameMaxLengthInvalid(String message, int maxLength) {
			super(message);
			this.maxLength = maxLength;
		}

		public final int maxLength;

	}

	public static class LastNameMaxLengthInvalid extends CustomRegistrationUserException {

		public LastNameMaxLengthInvalid(String message, int maxLength) {
			super(message);
			this.maxLength = maxLength;
		}

		public final int maxLength;

	}

}