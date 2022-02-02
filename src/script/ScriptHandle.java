package script;

import java.util.List;

/**
 * ScriptHandle：处理脚本的功能
 */
public interface ScriptHandle {
    /**
     * 添加删除脚本、获取脚本列表
     */
	public List<Script> getScripts();
	public void addScript(Script script);
    public void removeScript(String name);
    public void removeScript(Script script);
}
