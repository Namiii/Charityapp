package omise.charity.app;


import android.support.annotation.Nullable;

import co.omise.android.Client;
import co.omise.android.TokenRequest;
import co.omise.android.TokenRequestListener;
import co.omise.android.models.Token;
import omise.charity.app.charity.PaymentModel;

public class OmiseClientManager {
	private Client mClient;

	public OmiseClientManager(@Nullable Client client) {
		this.mClient = client;
	}

	public void createPaymentToken(PaymentModel paymentModel, final ResultCallback<String> callback) {
		if (mClient == null || paymentModel == null) return;

		TokenRequest request = new TokenRequest();
		request.number = paymentModel.getCardNumber();
		request.expirationMonth = paymentModel.getExpiryMonth();
		request.expirationYear = paymentModel.getExpiryYear();
		request.securityCode = paymentModel.getSecurityCode();
		request.name = paymentModel.getFullName();

		mClient.send(request, new TokenRequestListener() {
			@Override
			public void onTokenRequestSucceed(TokenRequest tokenRequest, Token token) {
				if (callback == null) return;

				callback.Success(token.id);
			}

			@Override
			public void onTokenRequestFailed(TokenRequest tokenRequest, Throwable throwable) {
				if (callback == null) return;

				callback.failed(throwable.getMessage());
			}
		});
	}
}