package ui;

public interface UIEvent {
	public void addListener(UIListener listener);
	public void removeListener(UIListener listener);
	public void notifyAllListener();
}
