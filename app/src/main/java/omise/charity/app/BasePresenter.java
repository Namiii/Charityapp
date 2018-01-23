package omise.charity.app;

public interface BasePresenter<ViewT> {
	void start(ViewT view);

	void stop();
}
