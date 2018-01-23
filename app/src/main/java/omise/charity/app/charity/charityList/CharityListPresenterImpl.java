package omise.charity.app.charity.charityList;

import omise.charity.app.ResultCallback;
import omise.charity.app.charity.CharityList;
import omise.charity.app.charity.CharityRepository;

public class CharityListPresenterImpl implements CharityListPresenter {
	private CharityRepository mRepository;
	private MainActivity mView;

	public CharityListPresenterImpl(CharityRepository mRepository) {
		this.mRepository = mRepository;
	}

	@Override
	public void start(MainActivity view) {
		this.mView = view;
	}

	@Override
	public void stop() {
		mView = null;
	}

	@Override
	public void getItems() {
		if (mRepository == null) return;

		mView.isLoading(true);
		mView.hideError();
		mView.hideData();

		mRepository.getCharities(new ResultCallback<CharityList>() {
			@Override
			public void Success(CharityList charityList) {
				if (mView == null) return;

				mView.isLoading(false);
				mView.hideError();
				mView.setItems(charityList);
			}

			@Override
			public void failed(String error) {
				if (mView == null) return;

				mView.isLoading(false);
				mView.hideData();
				mView.showError(error);
			}
		});
	}
}
