package animation;

import core.HasName;
import world.TimeLine;

import java.awt.image.BufferedImage;

/**
 * Spirit:心灵，精神，（Sprite小精灵）
 */
public class Spirit implements HasName{
	
	private Strategy state = null;
	private long delayTime = 100;
	private String name = null;
	private BufferedImage oneFrame = null;
	private long oldTime = 0;
	
	public Spirit(Strategy state) {
		this.state =state;
		oneFrame = state.getOneFrameByNoStart();
	}
	
	public Spirit(Strategy state,String name) {
		this.state =state;
		this.name = name;
		oneFrame = state.getOneFrameByNoStart();
	}
	
	public final BufferedImage getOneFrame(){
		long curTime = TimeLine.getCurTime();
		if(curTime - oldTime >= delayTime){
			oldTime = curTime;
			switch (state.getState()) {
			case Strategy.NoStart:
				oneFrame =  state.getOneFrameByNoStart();
				break;
			case Strategy.Start:
				oneFrame =  state.getOneFrameByStart();
				break;
			case Strategy.Stop:
				oneFrame =  state.getOneFrameByStop();
				break;
			case Strategy.Suspended:
				oneFrame =  state.getOneFrameBySus();
				break;
			default:
				break;
			}
		}
		return oneFrame;
	}
	
	public void stop(){
		if(state.getState() != Strategy.NoStart){
			state.setState(Strategy.Stop);
		}
	}

	public void suspended(){
		if(state.getState() == Strategy.Start){
			state.setState(Strategy.Suspended);
		}
	}
	
	public void start(){
		state.setState(Strategy.Start);
	}
	
	public int getState(){
		return state.getState();
	}

	public String getName() {
		return name;
	}

	public Spirit setName(String name) {
		this.name = name;
		return this;
	}

	public long getDelayTime() {
		return delayTime;
	}

	public void setDelayTime(long delayTime) {
		this.delayTime = delayTime;
	}
}
