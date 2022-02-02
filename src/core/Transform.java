package core;

/**
 * Transform:变换功能
 */
public interface Transform{
	/**
	 *变换（平移）
	 */
	public Transform translate(float x,float y);

	/**
	 * 旋转
	 */
	public Transform rotate(float angle,float x,float y);

	/**
	 *缩放
	 */
	public Transform scale(float x,float y);
}
