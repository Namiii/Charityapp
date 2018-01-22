package omise.charity.app.dagger;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import omise.charity.app.charity.CharityDataService;
import omise.charity.app.charity.CharityRepository;
import omise.charity.app.charity.CharityRepositoryImpl;

@Module
public class ApplicationModule {
	private Application mApplication;

	public ApplicationModule(Application application) {
		this.mApplication = application;
	}

	@Provides
	@Singleton
	Context providesContext() {
		return mApplication;
	}

	@Provides
	CharityRepository providesCharityRepository(CharityDataService charityDataService){
		return new CharityRepositoryImpl(charityDataService);
	}
}
