package world;

import actor.Actor;

/**
 * 游戏世界的抽象父类
 */
public interface AbstractWorld {
    /*
     * 添加和刪除角色
     */
	void addActor(Actor actor);

    void removeActor(Actor actor);

    void removeActor(String actorName);

	/**
	 * 开始游戏世界
	 */
    void startWorld();

	/**
	 * 停止游戏世界
	 */
    void stopWorld();

	/**
	 *	暂停游戏世界
	 */
    void suspendedWorld();
}
