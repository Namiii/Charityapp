package omise.charity.app.charity;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CharityDataService {
	@GET("charities")
	Call<CharityList> getCharitiesList();
}