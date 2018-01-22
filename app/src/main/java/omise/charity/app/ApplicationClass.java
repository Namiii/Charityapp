package omise.charity.app;

import android.app.Application;

import omise.charity.app.dagger.ApplicationComponent;
import omise.charity.app.dagger.ApplicationModule;
import omise.charity.app.dagger.DaggerApplicationComponent;

public class ApplicationClass extends Application {
	private ApplicationComponent mApplicationComponent;

	@Override
	public void onCreate() {
		super.onCreate();
		setupDagger();
	}

	private void setupDagger() {
		mApplicationComponent = DaggerApplicationComponent.builder()
				.applicationModule(new ApplicationModule(this))
				.build();
	}

	public ApplicationComponent getComponent() {
		return mApplicationComponent;
	}
}
