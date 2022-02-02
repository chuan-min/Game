package game;

import script.Script;

public class TestScript extends Script {
	@Override
	public void update() {
		actor.translate(1, 0);
	}
}
