package omise.charity.app.charity.charityList;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;

import javax.inject.Inject;

import omise.charity.app.ApplicationClass;
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

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		ListView mCharitiesListView = findViewById(R.id.main_activity_list_view);
		mProgressBar = findViewById(R.id.main_activity_progress_bar);

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

	@Override
	public void finish() {
		super.finish();
	}

	public void setItems(CharityList items) {
		mAdapter.setItems(items.getData());
	}

	public void showError(String error) {
		//TODO
	}

	public void isLoading(boolean isLoading) {
		mProgressBar.setVisibility(isLoading ? View.VISIBLE : View.GONE);
	}

	@Override
	public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
		Intent intent = new Intent(this, CharityDetailActivity.class);
		intent.putExtra("charity_data", (CharityModel) adapterView.getItemAtPosition(i));
		startActivity(intent);
		ScreenTransitionManager.setActivitySlideRightIn(this);
	}
}