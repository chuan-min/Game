package animation;

/**
 * �����񶯻�һ���Ĺ���
 */
public interface AnimHandle {
	public Spirit getSpirit();
	public void setSpirit(Spirit spirit);
	
	public Animation getAnimation();
	public AnimHandle setAnimation(Animation animation);
}
