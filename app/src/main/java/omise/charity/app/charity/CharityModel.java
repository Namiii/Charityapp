
package omise.charity.app.charity;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CharityModel implements Parcelable {

	@SerializedName("id")
	@Expose
	private long id;
	@SerializedName("name")
	@Expose
	private String name;
	@SerializedName("logo_url")
	@Expose
	private String logoUrl;
	public final static Creator<CharityModel> CREATOR = new Creator<CharityModel>() {


		@SuppressWarnings({
				"unchecked"
		})
		public CharityModel createFromParcel(Parcel in) {
			return new CharityModel(in);
		}

		public CharityModel[] newArray(int size) {
			return (new CharityModel[size]);
		}

	};

	protected CharityModel(Parcel in) {
		this.id = ((long) in.readValue((long.class.getClassLoader())));
		this.name = ((String) in.readValue((String.class.getClassLoader())));
		this.logoUrl = ((String) in.readValue((String.class.getClassLoader())));
	}

	public CharityModel() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLogoUrl() {
		return logoUrl;
	}

	public void setLogoUrl(String logoUrl) {
		this.logoUrl = logoUrl;
	}

	public void writeToParcel(Parcel dest, int flags) {
		dest.writeValue(id);
		dest.writeValue(name);
		dest.writeValue(logoUrl);
	}

	public int describeContents() {
		return 0;
	}

}