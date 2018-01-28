package omise.charity.app.charity;

import android.os.Parcel;
import android.os.Parcelable;

public class PaymentModel implements Parcelable {
	private String mFullName;
	private String mCardNumber;
	private int mExpiryMonth;
	private int mExpiryYear;
	private String mSecurityCode;
	private int mAmount;

	public PaymentModel(String cardNumber, String fullName, int expiryMonth, int expiryYear, String securityCode, int amount) {
		this.mCardNumber = cardNumber;
		this.mFullName = fullName;
		this.mExpiryMonth = expiryMonth;
		this.mExpiryYear = expiryYear;
		this.mSecurityCode = securityCode;
		this.mAmount = amount;
	}

	public String getCardNumber() {
		return mCardNumber;
	}

	public String getFullName() {
		return mFullName;
	}

	public int getExpiryMonth() {
		return mExpiryMonth;
	}

	public int getExpiryYear() {
		return mExpiryYear;
	}

	public String getSecurityCode() {
		return mSecurityCode;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(this.mFullName);
		dest.writeString(this.mCardNumber);
		dest.writeInt(this.mExpiryMonth);
		dest.writeInt(this.mExpiryYear);
		dest.writeString(this.mSecurityCode);
		dest.writeInt(this.mAmount);
	}

	protected PaymentModel(Parcel in) {
		this.mFullName = in.readString();
		this.mCardNumber = in.readString();
		this.mExpiryMonth = in.readInt();
		this.mExpiryYear = in.readInt();
		this.mSecurityCode = in.readString();
		this.mAmount = in.readInt();
	}

	public static final Creator<PaymentModel> CREATOR = new Creator<PaymentModel>() {
		@Override
		public PaymentModel createFromParcel(Parcel source) {
			return new PaymentModel(source);
		}

		@Override
		public PaymentModel[] newArray(int size) {
			return new PaymentModel[size];
		}
	};
}
