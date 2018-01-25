package omise.charity.app.charity.charityDetail;

import android.graphics.drawable.Animatable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import omise.charity.app.R;
import omise.charity.app.ScreenTransitionManager;

public class DonationSuccessActivity extends AppCompatActivity {

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.donation_success_layout);
		Button dismissButton = findViewById(R.id.donation_success_dismiss_button);
		ImageView mCheckImageView = findViewById(R.id.donation_success_check_mark);

		((Animatable) mCheckImageView.getDrawable()).start();

		dismissButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				finishScreen();
			}
		});
	}

	private void finishScreen() {
		ScreenTransitionManager.setActivityZoomOut(this);
		finish();
	}
}