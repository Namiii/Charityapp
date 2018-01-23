package omise.charity.app.charity.charityList;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import omise.charity.app.R;
import omise.charity.app.charity.CharityModel;

public class CharityAdapter extends BaseAdapter {
	private Context mContext;
	private List<CharityModel> mCharities;


	CharityAdapter(Context mContext) {
		this.mContext = mContext;
	}

	void setItems(List<CharityModel> items) {
		this.mCharities = items;
		notifyDataSetChanged();
	}

	@Override
	public int getCount() {
		return mCharities != null ? mCharities.size() : 0;
	}

	@Override
	public CharityModel getItem(int i) {
		return mCharities.get(i);
	}

	@Override
	public long getItemId(int i) {
		return mCharities.get(i).getId();
	}

	@NonNull
	@Override
	public View getView(int i, View convertView, @NonNull ViewGroup viewGroup) {
		ViewHolder holder;

		if (convertView == null) {
			convertView = LayoutInflater.from(mContext).inflate(R.layout.charity_item_layout, viewGroup, false);
			holder = new ViewHolder();
			holder.logo = convertView.findViewById(R.id.charity_item_logo);
			holder.name = convertView.findViewById(R.id.charity_item_name);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		CharityModel charityModel = mCharities.get(i);
		holder.name.setText(charityModel.getName());
		Picasso.with(mContext).
				load(charityModel.getLogoUrl())
				.fit()
				.into(holder.logo);
		return convertView;
	}

	static class ViewHolder {
		ImageView logo;
		TextView name;
	}
}