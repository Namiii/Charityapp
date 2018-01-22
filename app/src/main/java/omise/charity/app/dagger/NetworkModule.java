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
//	@Singleton
//	@Provides
//	OkHttpClient providesHttpClient() {
//		OkHttpClient.Builder httpClient =
//				new OkHttpClient.Builder();
//		httpClient.addInterceptor(new Interceptor() {
//			@Override
//			public Response intercept(@NonNull Chain chain) throws IOException {
//				Request original = chain.request();
//				HttpUrl originalHttpUrl = original.url();
//
//				HttpUrl url = originalHttpUrl.newBuilder()
////						.addQueryParameter("APPID", Constants.OPEN_WEATHER_KEY)
//						.addQueryParameter("units","metric")
//						.build();
//
//				// Request customization: add request headers
//				Request.Builder requestBuilder = original.newBuilder()
//						.url(url);
//
//				Request request = requestBuilder.build();
//				return chain.proceed(request);
//			}
//		});
//		httpClient.addInterceptor(new HttpLoggingInterceptor()
//				.setLevel(HttpLoggingInterceptor.Level.BODY));
//		return httpClient.build();
//	}

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