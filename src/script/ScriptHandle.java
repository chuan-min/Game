package script;

import java.util.List;

/**
 * ScriptHandle������ű��Ĺ���
 */
public interface ScriptHandle {
    /**
     * ���ɾ���ű�����ȡ�ű��б�
     */
	public List<Script> getScripts();
	public void addScript(Script script);
    public void removeScript(String name);
    public void removeScript(Script script);
}
