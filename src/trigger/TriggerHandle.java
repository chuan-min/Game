package trigger;

import java.util.List;

/**
 * TriggerHandle:��������
 */
public interface TriggerHandle {
	/**
	 * ��ȡ�������б�
	 */
	public List<Trigger> getTriggers();

	/**
	 * ��Ӵ�����
	 */

	public void addTrigger(Trigger trigger);

	/**
	 * �ж��Ƿ��Ѵ���
	 */
	public boolean isBeTriggered();
}
