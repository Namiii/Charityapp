package omise.charity.app.dagger;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import omise.charity.app.Constants;
import omise.charity.app.charity.CharityDataService;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkModule {
	@Singleton
	@Provides
	OkHttpClient providesHttpClient() {
		OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
		httpClient.addInterceptor(new HttpLoggingInterceptor()
				.setLevel(HttpLoggingInterceptor.Level.BODY));
		return httpClient.build();
	}

	@Provides
	@Singleton
	Retrofit providesRetrofitClient(OkHttpClient client) {
		return new Retrofit.Builder()
				.baseUrl(Constants.BASE_URL)
				.client(client)
				.addConverterFactory(GsonConverterFactory.create())
				.build();
	}

	@Provides
	CharityDataService providesCharityDataService(Retrofit retrofit) {
		return retrofit.create(CharityDataService.class);
	}
}