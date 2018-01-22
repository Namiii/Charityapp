package omise.charity.app.charity;


import android.support.annotation.NonNull;

import omise.charity.app.ResultCallback;
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
}