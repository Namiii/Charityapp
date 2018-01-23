package omise.charity.app.charity.charityList;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;

import javax.inject.Inject;

import omise.charity.app.ApplicationClass;
import omise.charity.app.R;
import omise.charity.app.charity.CharityList;

public class MainActivity extends AppCompatActivity {
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
		mAdapter.setItems(items.getData());
	}

	public void showError(String error) {
		//TODO
	}

	public void isLoading(boolean isLoading) {
		mProgressBar.setVisibility(isLoading ? View.VISIBLE : View.GONE);
	}
}