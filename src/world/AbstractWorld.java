package world;

import actor.Actor;

/**
 * ��Ϸ����ĳ�����
 */
public interface AbstractWorld {
    /*
     * ��Ӻ̈́h����ɫ
     */
	void addActor(Actor actor);

    void removeActor(Actor actor);

    void removeActor(String actorName);

	/**
	 * ��ʼ��Ϸ����
	 */
    void startWorld();

	/**
	 * ֹͣ��Ϸ����
	 */
    void stopWorld();

	/**
	 *	��ͣ��Ϸ����
	 */
    void suspendedWorld();
}
