package com.example.lambdaapigateway;

/**
 * @author jugul.mishra May 10, 2018 3:05:02 PM
 */
public abstract class ReturnNullInsteadOfBooleanCheck {

	private ReturnNullInsteadOfBooleanCheck() {

	}

	public Boolean shouldBeWarning() {
		return null;
	}
}
