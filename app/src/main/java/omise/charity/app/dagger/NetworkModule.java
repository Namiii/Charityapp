package omise.charity.app.dagger;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import omise.charity.app.Constants;
import omise.charity.app.charity.CharityDataService;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkModule {

	@Provides
	@Singleton
	Retrofit providesRetrofitClient() {
		return new Retrofit.Builder()
				.baseUrl(Constants.BASE_URL)
				.addConverterFactory(GsonConverterFactory.create())
				.build();
	}

	@Provides
	CharityDataService providesCharityDataService(Retrofit retrofit) {
		return retrofit.create(CharityDataService.class);
	}
}