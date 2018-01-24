
package omise.charity.app.charity.charityDetail;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DonationResponse implements Parcelable {

	@SerializedName("success")
	@Expose
	private boolean success;
	@SerializedName("error_code")
	@Expose
	private String errorCode;
	@SerializedName("error_message")
	@Expose
	private String errorMessage;
	public final static Parcelable.Creator<DonationResponse> CREATOR = new Creator<DonationResponse>() {


		@SuppressWarnings({
				"unchecked"
		})
		public DonationResponse createFromParcel(Parcel in) {
			return new DonationResponse(in);
		}

		public DonationResponse[] newArray(int size) {
			return (new DonationResponse[size]);
		}

	};

	protected DonationResponse(Parcel in) {
		this.success = ((boolean) in.readValue((boolean.class.getClassLoader())));
		this.errorCode = ((String) in.readValue((String.class.getClassLoader())));
		this.errorMessage = ((String) in.readValue((String.class.getClassLoader())));
	}

	public DonationResponse() {
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public void writeToParcel(Parcel dest, int flags) {
		dest.writeValue(success);
		dest.writeValue(errorCode);
		dest.writeValue(errorMessage);
	}

	public int describeContents() {
		return 0;
	}

}
