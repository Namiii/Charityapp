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
	public void getItems(ResultCallback<CharityList> callback) {
		if (mRepository == null) return;

		mRepository.getCharities(new ResultCallback<CharityList>() {
			@Override
			public void Success(CharityList charityList) {
				if (mView == null) return;

				mView.setItems(charityList);
			}

			@Override
			public void failed(String error) {
				if (mView == null) return;

				mView.showError(error);
			}
		});
	}
}
