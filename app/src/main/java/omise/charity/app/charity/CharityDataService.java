package omise.charity.app.charity;

import omise.charity.app.charity.charityDetail.DonationResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface CharityDataService {
	@GET("charities")
	Call<CharityList> getCharitiesList();

	@POST("donations")
	Call<DonationResponse> makeDonation(@Body DonationModel donationModel);
}