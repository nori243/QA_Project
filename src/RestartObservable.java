import java.util.Observable;


public class RestartObservable extends Observable
{
	public boolean restart = false;
	
	public RestartObservable()
	{
		this.addObserver(StartFrame.gameFrame);
	}
	
	public void restart(boolean restart)
	{
		this.restart = restart;
		this.setChanged();
		this.notifyObservers();
	}


}
