package omise.charity.app;

import co.omise.android.CardNumber;

public class ValidationHelper {
	public static boolean isCreditCardNumberValid(String cardNumber){
		return CardNumber.luhn(cardNumber);
	}
}
