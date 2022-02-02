package game;
/**
 *@TODO(功能):
 *@CRATE_TIME:  22-2-2  下午9:49
 *@AUTHOR: Li Chuanmin
 *@VERSION: v_0.1
 */
import actor.CirclePhyActor;
import actor.DrawActor;
import actor.PolygonPhyActor;
import actor.RectPhyActor;
import animation.NormalStrategy;
import animation.Spirit;
import com.mm.game.util.R;
import core.Game;
import core.GameWorld;
import org.jbox2d.common.Vec2;
import scene.PhysicalScene;
import ui.Button;
import ui.UIPanel;
import view.RectViewPort;

import java.awt.*;

public class TestGame extends Game {
	private PhysicalScene scene = null;
	@Override
	public void init() {
		RectViewPort viewPort = new RectViewPort(0,0,500, 350);
		
		scene = new PhysicalScene(500,350,Color.white, 2.0f/60.0f, 10f);
		Spirit bk = new Spirit(new NormalStrategy(R.ROOT_PATH+"/images/bk.gif", "gif"));
		bk.start();
		scene.setSpirit(bk);
		viewPort.setScene(scene);
		
		
		
		RectPhyActor wallBottom = new RectPhyActor(0, 340, 500, 10, Color.blue,true,false);
		
		RectPhyActor wallLeft = new RectPhyActor(0, 0, 10, 350, Color.blue,true,false);
		RectPhyActor wallRight = new RectPhyActor(485, 0, 15, 350, Color.blue,true,false);
		RectPhyActor wallTop = new RectPhyActor(0, 0, 500, 10, Color.blue,true,false);
		wallBottom.setFriction(1f);
		wallBottom.setRestitution(0);
		wallLeft.setFriction(0);
		wallRight.setFriction(0);
		wallTop.setFriction(0);
		
		CirclePhyActor circleActor = new CirclePhyActor(200, 100, 50, Color.yellow);
		
//		circleActor.setFixedRotation(true);
		
		RectPhyActor rectActor1 = new RectPhyActor(180, 320, 30, 40, Color.red,true);
		rectActor1.setName("testRect");
		//???????????????????????
		MoveAnimation animation = new MoveAnimation();
		animation.start();//???????
		//?????????????????
		rectActor1.setAnimation(animation);
		rectActor1.addScript(new MoveContorl());
		rectActor1.setFixedRotation(true);
		rectActor1.setRestitution(0.1f);
		rectActor1.setFriction(0.2f);
		RectPhyActor rectActor2 = new RectPhyActor(200, 320, 10, 20, Color.red);
		RectPhyActor rectActor3 = new RectPhyActor(220, 320, 10, 20, Color.red);
		PolygonPhyActor polygonActor = new PolygonPhyActor(200, 250,new Vec2[]{new Vec2(0,0),new Vec2(20, 0),new Vec2(20, 20),new Vec2(0,20)},Color.red);
		
		
		DrawActor cActor = new DrawActor(50, 50, 20);
		cActor.addScript(new TestScript());
		Spirit spirit = new Spirit(new NormalStrategy(R.ROOT_PATH+"/images/2.gif", "gif"));
		spirit.start();
		cActor.setSpirit(spirit);
		
		
		scene.addActor(wallBottom);
		scene.addActor(wallLeft);
		scene.addActor(wallRight);
		scene.addActor(wallTop);
		scene.addActor(circleActor);
		scene.addActor(rectActor1);
		scene.addActor(rectActor2);
		scene.addActor(rectActor3);
		scene.addActor(polygonActor);
		scene.addActor(cActor);
		
		UIPanel panel = new UIPanel(300, 300);
		panel.setOpacity(0.7f);
		
		Button button = new Button(60, 20, 50, 30, 5, 5);
		button.setText("按钮");
		panel.addUI(button);
		
		scene.setUIPanel(panel);
//		circleActor.setR(30);
//		
//		rectActor1.setWidth(20);
		//????????????
//		scene.renderWorld.isBorderRender = true;
//		scene.renderWorld.isAxisRender = true;
//		scene.renderWorld.isTriggerBorder = true;
		
	}

	@Override
	public void start() {
		init();
		GameWorld.view.setVisible(true);
		scene.requestFocus();
		scene.startWorld();
	}

	@Override
	public void restart() {
		// TODO Auto-generated method stub

	}

	@Override
	public void suspended() {
		// TODO Auto-generated method stub

	}
	
	public static void main(String[] args) {
		new TestGame().start();
	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub
		
	}

}
