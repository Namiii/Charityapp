package omise.charity.app.dagger;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

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
}
