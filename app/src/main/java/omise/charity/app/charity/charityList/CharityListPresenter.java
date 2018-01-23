package omise.charity.app.charity.charityList;

import omise.charity.app.BasePresenter;
import omise.charity.app.ResultCallback;
import omise.charity.app.charity.CharityList;

public interface CharityListPresenter extends BasePresenter<MainActivity> {
	void getItems(ResultCallback<CharityList> callback);
}