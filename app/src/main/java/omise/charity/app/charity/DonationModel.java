package omise.charity.app.charity;

import android.os.Parcel;
import android.os.Parcelable;

public class DonationModel implements Parcelable {
	private String name;
	private String token;
	private int amount;

	public DonationModel(String name, String token, int amount) {
		this.name = name;
		this.token = token;
		this.amount = amount;
	}


	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(this.name);
		dest.writeString(this.token);
		dest.writeInt(this.amount);
	}

	protected DonationModel(Parcel in) {
		this.name = in.readString();
		this.token = in.readString();
		this.amount = in.readInt();
	}

	public static final Parcelable.Creator<DonationModel> CREATOR = new Parcelable.Creator<DonationModel>() {
		@Override
		public DonationModel createFromParcel(Parcel source) {
			return new DonationModel(source);
		}

		@Override
		public DonationModel[] newArray(int size) {
			return new DonationModel[size];
		}
	};
}
