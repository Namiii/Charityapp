package omise.charity.app.charity.charityDetail;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import co.omise.android.ui.CreditCardEditText;
import omise.charity.app.Constants;
import omise.charity.app.R;
import omise.charity.app.charity.CharityModel;

public class CharityDetailActivity extends AppCompatActivity {
	private TextView mCharityNameTextView;
	private ImageView mCharityLogoImageView;
	private EditText mFullNameEditText;
	private CreditCardEditText mCreditCardNumberEditText;
	private Spinner mExpiryMonthSpinner;
	private Spinner mExpiryYearSpinner;
	private EditText mSecurityCodeEditText;
	private EditText mAmountEditText;
	private Button mDonateButton;
	private CharityModel mCharityModel;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.charity_detail_layout);
		mCharityNameTextView = findViewById(R.id.charity_detail_name);
		mCharityLogoImageView = findViewById(R.id.charity_detail_logo);
		mFullNameEditText = findViewById(R.id.charity_detail_full_name_et);
		mCreditCardNumberEditText = findViewById(R.id.charity_detail_card_number_et);
		mExpiryMonthSpinner = findViewById(R.id.charity_detail_expiry_month_spinner);
		mExpiryYearSpinner = findViewById(R.id.charity_detail_expiry_year_spinner);
		mSecurityCodeEditText = findViewById(R.id.charity_detail_security_code_et);
		mAmountEditText = findViewById(R.id.charity_detail_amount_et);
		mDonateButton = findViewById(R.id.charity_detail_donate_button);

		setup(getIntent());
	}

	private void setup(Intent intent) {
		mExpiryMonthSpinner.setAdapter(new MontlyAdapter());
		mExpiryYearSpinner.setAdapter(new YearlyAdapter());

		Bundle bundle = intent.getExtras();
		if (bundle == null) return;
		mCharityModel = (CharityModel) bundle.get(Constants.CHARITY_DATA);

		if (mCharityModel == null) return;

		mCharityNameTextView.setText(mCharityModel.getName());
		Picasso.with(this).
				load(mCharityModel.getLogoUrl())
				.fit()
				.into(mCharityLogoImageView);
	}
}