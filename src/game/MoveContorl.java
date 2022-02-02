package game;

import actor.PhyActor;
import org.jbox2d.common.Vec2;
import script.Script;

import java.awt.event.KeyEvent;

public class MoveContorl extends Script {
	private boolean leftWalking = false;
	private boolean rightWalking = false;
	private PhyActor own = null;
	
	@Override
	public void start() {
		if(actor instanceof PhyActor){
			own = (PhyActor)actor;
		}
	}
	@Override
	public void update() {
		//??????
		if(own.body.getLinearVelocity().x <= 5 && rightWalking == true){
			own.body.applyLinearImpulse(new Vec2(50, 0), own.body.getPosition());
		}

		if(own.body.getLinearVelocity().x >= -5 && leftWalking == true){
			own.body.applyLinearImpulse(new Vec2(-50, 0), own.body.getPosition());
		}
		
		//???????
		if(own.body.getLinearVelocity().x != 0 && own.getAnimation() != null){
			own.getAnimation().setActive("move");
		}else if(own.body.getLinearVelocity().x == 0.0 && own.getAnimation() != null){
			own.getAnimation().setActive("default");
		}
//		System.out.println("dasdas");
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_D:
			rightWalking = true;
//			CirclePhyActor circlePhyActor = new CirclePhyActor(50, 50, 20, Color.green);
//			actor.getScene().addActor(circlePhyActor);
			break;
		case KeyEvent.VK_A:
			leftWalking = true;
			break;
		case KeyEvent.VK_SPACE:
			if(own.body.getLinearVelocity().y >= -10){
				own.body.applyLinearImpulse(new Vec2(0, -100), own.body.getPosition());
			}
//			actor.destroy();
			break;
		default:
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_D:
			rightWalking = false;
			break;
		case KeyEvent.VK_A:
			leftWalking = false;
			break;
		case KeyEvent.VK_SPACE:
			break;
		default:
			break;
		}
	}
}
