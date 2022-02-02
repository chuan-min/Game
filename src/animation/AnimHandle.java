package animation;

/**
 * 可以像动画一样的功能
 */
public interface AnimHandle {
	public Spirit getSpirit();
	public void setSpirit(Spirit spirit);
	
	public Animation getAnimation();
	public AnimHandle setAnimation(Animation animation);
}
