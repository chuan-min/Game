package core;

/**
 * Transform:�任����
 */
public interface Transform{
	/**
	 *�任��ƽ�ƣ�
	 */
	public Transform translate(float x,float y);

	/**
	 * ��ת
	 */
	public Transform rotate(float angle,float x,float y);

	/**
	 *����
	 */
	public Transform scale(float x,float y);
}
