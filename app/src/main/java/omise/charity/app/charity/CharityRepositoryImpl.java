package omise.charity.app.charity;


import android.support.annotation.NonNull;

import omise.charity.app.ResultCallback;
import omise.charity.app.charity.charityDetail.DonationResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CharityRepositoryImpl implements CharityRepository {
	private CharityDataService mCharityDataService;

	public CharityRepositoryImpl(CharityDataService mCharityDataService) {
		this.mCharityDataService = mCharityDataService;
	}

	@Override
	public void getCharities(final ResultCallback<CharityList> resultCallback) {
		if (mCharityDataService == null) return;

		mCharityDataService.getCharitiesList().enqueue(new Callback<CharityList>() {
			@Override
			public void onResponse(@NonNull Call<CharityList> call, @NonNull Response<CharityList> response) {
				if (resultCallback == null) return;

				if (response.isSuccessful() && response.body() != null) {
					resultCallback.Success(response.body());
				} else {
					resultCallback.failed("Could not connect to the server");
				}
			}

			@Override
			public void onFailure(@NonNull Call<CharityList> call, @NonNull Throwable t) {
				if (resultCallback == null) return;

				resultCallback.failed(t.getMessage());
			}
		});
	}

	@Override
	public void makeDonation(DonationModel donationModel, final ResultCallback<Boolean> resultCallback) {
		if (mCharityDataService == null) return;

		mCharityDataService.makeDonation(donationModel).enqueue(new Callback<DonationResponse>() {
			@Override
			public void onResponse(@NonNull Call<DonationResponse> call, @NonNull Response<DonationResponse> response) {
				if (resultCallback == null) return;

				if (response.isSuccessful() && response.body() != null) {
					if (response.body().isSuccess()) {
						resultCallback.Success(true);
					} else {
						resultCallback.failed(response.body().getErrorMessage());
					}

				} else {
					resultCallback.failed("Could not make the donation at the moment");
				}

			}

			@Override
			public void onFailure(@NonNull Call<DonationResponse> call, @NonNull Throwable t) {
				if (resultCallback == null) return;

				resultCallback.failed(t.getMessage());
			}
		});
	}
}