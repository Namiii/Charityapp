package omise.charity.app;

public interface ResultCallback<T> {
	public void Success(T t);
	public void failed(String error);
}
