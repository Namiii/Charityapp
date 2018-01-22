package omise.charity.app.charity;


import omise.charity.app.ResultCallback;

public interface CharityRepository {
	void getCharities(ResultCallback<CharityList> resultCallback);
}