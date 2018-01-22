
package omise.charity.app.charity;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CharityList implements Parcelable
{

    @SerializedName("total")
    @Expose
    private long total;
    @SerializedName("data")
    @Expose
    private List<CharityModel> data = null;
    public final static Creator<CharityList> CREATOR = new Creator<CharityList>() {


        @SuppressWarnings({
            "unchecked"
        })
        public CharityList createFromParcel(Parcel in) {
            return new CharityList(in);
        }

        public CharityList[] newArray(int size) {
            return (new CharityList[size]);
        }

    }
    ;

    protected CharityList(Parcel in) {
        this.total = ((long) in.readValue((long.class.getClassLoader())));
        in.readList(this.data, (CharityModel.class.getClassLoader()));
    }

    public CharityList() {
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<CharityModel> getData() {
        return data;
    }

    public void setData(List<CharityModel> data) {
        this.data = data;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(total);
        dest.writeList(data);
    }

    public int describeContents() {
        return  0;
    }

}
