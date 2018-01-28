package omise.charity.app;

public interface ResultCallback<T> {
	void Success(T t);
	void failed(String error);
}
