package omise.charity.app.charity.charityDetail;

import android.text.TextUtils;

import omise.charity.app.OmiseClientManager;
import omise.charity.app.ResultCallback;
import omise.charity.app.ValidationHelper;
import omise.charity.app.charity.CharityRepository;
import omise.charity.app.charity.DonationModel;
import omise.charity.app.charity.PaymentModel;

public class CharityDetailPresenterImpl implements CharityDetailPresenter {
	private CharityDetailActivity mView;
	private CharityRepository mCharityRepository;
	private OmiseClientManager mOmiseClientManager;

	public CharityDetailPresenterImpl(CharityRepository charityRepository, OmiseClientManager omiseClientManager) {
		this.mCharityRepository = charityRepository;
		this.mOmiseClientManager = omiseClientManager;
	}

	@Override
	public void start(CharityDetailActivity view) {
		this.mView = view;
	}

	@Override
	public void stop() {
		mView = null;
	}

	@Override
	public void donationButtonPressed(final String mFullName, String mCardNumber, int expiryMonth, int expiryYear, String securityCode, final int amount) {
		boolean isValid = true;

		if (TextUtils.isEmpty(mFullName)) {
			mView.showNameError();
			isValid = false;
		}

		if (TextUtils.isEmpty(mCardNumber) && ValidationHelper.isCreditCardNumberValid(mCardNumber)) {
			mView.showCreditCardError();
			isValid = false;
		}

		if (TextUtils.isEmpty(securityCode)) {
			mView.showSecurityCodeError();
			isValid = false;
		}

		if (amount == 0) {
			mView.showAmountError();
			isValid = false;
		}

		if (!isValid) return;

		if (mOmiseClientManager == null) return;

		PaymentModel paymentModel =
				new PaymentModel(mCardNumber,
						mFullName,
						expiryMonth,
						expiryYear,
						securityCode,
						amount);


		mView.isLoading(true);
		mView.hideErrors();

		mOmiseClientManager.createPaymentToken(paymentModel,
				new ResultCallback<String>() {
					@Override
					public void Success(String token) {
						if (mCharityRepository == null) return;

						DonationModel donationModel =
								new DonationModel(mFullName,
										token,
										amount);

						mCharityRepository.makeDonation(donationModel,
								new ResultCallback<Boolean>() {
									@Override
									public void Success(Boolean success) {
										if (mView == null) return;

										mView.isLoading(false);
										mView.hideErrors();
										mView.navigateToNextScreen();
									}

									@Override
									public void failed(String error) {
										if (mView == null) return;

										mView.isLoading(false);
										mView.showError(error);
									}
								});
					}

					@Override
					public void failed(String error) {
						if (mView == null) return;

						mView.showError(error);
					}
				});
	}
}
