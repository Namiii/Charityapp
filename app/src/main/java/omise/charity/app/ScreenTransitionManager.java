package omise.charity.app;

import android.app.Activity;

public class ScreenTransitionManager {
	public static void setActivitySlideRightOut(Activity activity) {
		activity.overridePendingTransition(R.anim.slide_out_enter, R.anim.slide_out_exit);
	}

	public static void setActivitySlideRightIn(Activity activity) {
		activity.overridePendingTransition(R.anim.slide_in_enter, R.anim.slide_in_exit);
	}
}
