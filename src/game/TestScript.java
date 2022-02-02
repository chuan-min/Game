package game;
/**
 *@TODO(¹¦ÄÜ):
 *@CRATE_TIME:${DATE} ${TIME}
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
