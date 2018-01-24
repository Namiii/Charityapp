package omise.charity.app.charity.charityDetail;

import omise.charity.app.BasePresenter;

public interface CharityDetailPresenter extends BasePresenter<CharityDetailActivity> {
	void donationButtonPressed(String mFullName, String mCardNumber, int expiryMonth, int expiryYear, String securityCode, int amount);
}
