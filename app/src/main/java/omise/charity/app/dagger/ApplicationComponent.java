package omise.charity.app.dagger;

import javax.inject.Singleton;

import dagger.Component;
import omise.charity.app.MainActivity;

@Singleton
@Component(modules = {ApplicationModule.class, NetworkModule.class})
public interface ApplicationComponent {
	void inject(MainActivity target);
}
