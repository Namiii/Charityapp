package omise.charity.app.dagger;

import android.app.Application;
import android.content.Context;

import java.security.GeneralSecurityException;

import javax.inject.Singleton;

import co.omise.android.Client;
import dagger.Module;
import dagger.Provides;
import omise.charity.app.BuildConfig;
import omise.charity.app.OmiseClientManager;
import omise.charity.app.charity.CharityDataService;
import omise.charity.app.charity.CharityRepository;
import omise.charity.app.charity.CharityRepositoryImpl;
import omise.charity.app.charity.charityDetail.CharityDetailPresenter;
import omise.charity.app.charity.charityDetail.CharityDetailPresenterImpl;
import omise.charity.app.charity.charityList.CharityListPresenter;
import omise.charity.app.charity.charityList.CharityListPresenterImpl;

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
	CharityRepository providesCharityRepository(CharityDataService charityDataService) {
		return new CharityRepositoryImpl(charityDataService);
	}

	@Provides
	CharityListPresenter providesCharityListPresenter(CharityRepository charityRepository) {
		return new CharityListPresenterImpl(charityRepository);
	}

	@Singleton
	@Provides
	Client providesOmiseClient() {
		try {
			return new Client(BuildConfig.OMISE_P_K);
		} catch (GeneralSecurityException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Provides
	OmiseClientManager providesOmiseClientManager(Client client) {
		return new OmiseClientManager(client);
	}

	@Provides
	CharityDetailPresenter providesCharityDetailPresenter(CharityRepository charityRepository, OmiseClientManager omiseClientManager) {
		return new CharityDetailPresenterImpl(charityRepository, omiseClientManager);
	}
}