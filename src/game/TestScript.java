package game;
/**
 *@TODO(����):
 *@CRATE_TIME:  22-2-2  ����9:49
 *@AUTHOR: Li Chuanmin
 *@VERSION: v_0.1
 */
import script.Script;

public class TestScript extends Script {
	@Override
	public void update() {
		actor.translate(1, 0);
	}
}
