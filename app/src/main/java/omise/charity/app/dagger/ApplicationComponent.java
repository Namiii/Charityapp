package omise.charity.app.dagger;

import javax.inject.Singleton;

import dagger.Component;
import omise.charity.app.charity.charityDetail.CharityDetailActivity;
import omise.charity.app.charity.charityList.MainActivity;

@Singleton
@Component(modules = {ApplicationModule.class, NetworkModule.class})
public interface ApplicationComponent {
	void inject(MainActivity target);

	void inject(CharityDetailActivity target);
}
