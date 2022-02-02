package trigger;

import java.util.List;

/**
 * TriggerHandle:触发功能
 */
public interface TriggerHandle {
	/**
	 * 获取触发器列表
	 */
	public List<Trigger> getTriggers();

	/**
	 * 添加触发器
	 */

	public void addTrigger(Trigger trigger);

	/**
	 * 判断是否已触发
	 */
	public boolean isBeTriggered();
}
