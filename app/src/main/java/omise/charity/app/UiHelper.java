package omise.charity.app;


import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

public class UiHelper {

	public static float dpToPx(float dpValue) {
		return dpValue * Resources.getSystem().getDisplayMetrics().density;
	}

	public static void hideKeyBoard(Activity activity) {
		if (activity == null) {
			return;
		}
		hideKeyBoard(activity, activity.getCurrentFocus());
	}

	private static void hideKeyBoard(Context context, View view) {
		if (context == null || view == null) {
			return;
		}

		InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
		if (imm == null) return;

		imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

	}

}
