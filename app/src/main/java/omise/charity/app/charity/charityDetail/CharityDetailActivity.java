package omise.charity.app.charity.charityDetail;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import co.omise.android.ui.CreditCardEditText;
import omise.charity.app.ApplicationClass;
import omise.charity.app.Constants;
import omise.charity.app.R;
import omise.charity.app.ScreenTransitionManager;
import omise.charity.app.UiHelper;
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
	private ProgressBar mProgressBar;
	@Inject
	public CharityDetailPresenter mPresenter;
	private Button mDonateButton;

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
		mProgressBar = findViewById(R.id.charity_detail_donate_progress_bar);
		mDonateButton = findViewById(R.id.charity_detail_donate_button);

		mDonateButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				handleDonateButtonClick();
			}
		});

		((ApplicationClass) getApplication()).getComponent().inject(this);

		ActionBar actionBar = getSupportActionBar();
		if (actionBar != null) {
			actionBar.setDisplayHomeAsUpEnabled(true);
		}

		setup(getIntent());
	}

	private void handleDonateButtonClick() {
		if (mPresenter == null) return;

		UiHelper.hideKeyBoard(this);

		int amount = TextUtils.isEmpty(mAmountEditText.getText().toString()) ? 0
				: Integer.valueOf(mAmountEditText.getText().toString());

		mPresenter.donationButtonPressed(mFullNameEditText.getText().toString(),
				mCreditCardNumberEditText.getText().toString(),
				(Integer) mExpiryMonthSpinner.getSelectedItem(),
				(Integer) mExpiryYearSpinner.getSelectedItem(),
				mSecurityCodeEditText.getText().toString(),
				amount);
	}

	@Override
	protected void onStart() {
		super.onStart();
		mPresenter.start(this);
	}

	@Override
	protected void onStop() {
		super.onStop();
		mPresenter.stop();
	}

	@Override
	public boolean onSupportNavigateUp() {
		finish();
		return true;
	}

	private void setup(Intent intent) {
		mExpiryMonthSpinner.setAdapter(new MonthlyAdapter());
		mExpiryYearSpinner.setAdapter(new YearlyAdapter());

		Bundle bundle = intent.getExtras();
		if (bundle == null) return;

		CharityModel mCharityModel = (CharityModel) bundle.get(Constants.CHARITY_DATA);

		if (mCharityModel == null) return;

		mCharityNameTextView.setText(mCharityModel.getName());
		Picasso.with(this).
				load(mCharityModel.getLogoUrl())
				.fit()
				.into(mCharityLogoImageView);
	}

	public void isLoading(boolean isLoading) {
		mProgressBar.setVisibility(isLoading ? View.VISIBLE : View.GONE);
		ViewCompat.setTranslationZ(mProgressBar, isLoading ? UiHelper.dpToPx(5)
				: UiHelper.dpToPx(0));
		mDonateButton.setText(isLoading ? "" : getString(R.string.charity_detail_donate_button_text));
		mDonateButton.setEnabled(!isLoading);
	}

	public void hideErrors() {
		mFullNameEditText.setError(null);
		mCreditCardNumberEditText.setError(null);
		mSecurityCodeEditText.setError(null);
		mAmountEditText.setError(null);
	}

	public void showNameError() {
		mFullNameEditText.setError(getString(R.string.charity_detail_name_error));
	}

	public void showCreditCardError() {
		mCreditCardNumberEditText.setError(getString(R.string.charity_detail_credit_card_error));
	}

	public void showSecurityCodeError() {
		mSecurityCodeEditText.setError(getString(R.string.charity_detail_security_code_error));
	}

	public void showAmountError() {
		mAmountEditText.setError(getString(R.string.charity_detail_amount_error));
	}

	public void showError(String error) {
		Toast.makeText(this, error, Toast.LENGTH_LONG).show();

	}

	public void navigateToNextScreen() {
		startActivity(new Intent(this, DonationSuccessActivity.class));
		ScreenTransitionManager.setActivitySlideRightIn(this);
		finish();
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		ScreenTransitionManager.setActivitySlideRightOut(this);
	}
}