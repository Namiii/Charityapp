package omise.charity.app.charity.charityList;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import javax.inject.Inject;

import omise.charity.app.ApplicationClass;
import omise.charity.app.Constants;
import omise.charity.app.R;
import omise.charity.app.ScreenTransitionManager;
import omise.charity.app.charity.CharityList;
import omise.charity.app.charity.CharityModel;
import omise.charity.app.charity.charityDetail.CharityDetailActivity;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
	@Inject
	public CharityListPresenter mPresenter;
	private CharityAdapter mAdapter;
	private ProgressBar mProgressBar;
	private TextView mErrorView;
	private ListView mCharitiesListView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mCharitiesListView = findViewById(R.id.main_activity_list_view);
		mProgressBar = findViewById(R.id.main_activity_progress_bar);
		mErrorView = findViewById(R.id.main_activity_error_view);

		mAdapter = new CharityAdapter(this);
		mCharitiesListView.setAdapter(mAdapter);
		mCharitiesListView.setOnItemClickListener(this);

		((ApplicationClass) getApplication()).getComponent().inject(this);
	}

	@Override
	protected void onStart() {
		super.onStart();
		mPresenter.start(this);
		mPresenter.getItems();
	}

	@Override
	protected void onStop() {
		super.onStop();
		mPresenter.stop();
	}

	public void setItems(CharityList items) {
		mCharitiesListView.setVisibility(View.VISIBLE);
		mAdapter.setItems(items.getData());
	}

	public void showError(String error) {
		mErrorView.setText(error);
		mErrorView.setVisibility(View.VISIBLE);
	}

	public void hideError() {
		mErrorView.setVisibility(View.GONE);
	}

	public void isLoading(boolean isLoading) {
		mProgressBar.setVisibility(isLoading ? View.VISIBLE : View.GONE);
	}

	public void hideData() {
		mCharitiesListView.setVisibility(View.GONE);
	}

	@Override
	public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
		Intent intent = new Intent(this, CharityDetailActivity.class);
		intent.putExtra(Constants.CHARITY_DATA, (CharityModel) adapterView.getItemAtPosition(i));
		startActivity(intent);
		ScreenTransitionManager.setActivitySlideRightIn(this);
	}
}