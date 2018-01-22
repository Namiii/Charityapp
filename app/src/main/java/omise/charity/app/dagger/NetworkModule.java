package omise.charity.app.dagger;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import omise.charity.app.Constants;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
class NetworkModule {
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
	Retrofit providesRetrofitClient(OkHttpClient httpClient) {
		return new Retrofit.Builder()
				.baseUrl(Constants.BASE_URL)
				.client(httpClient)
				.addConverterFactory(GsonConverterFactory.create())
				.build();
	}
}
