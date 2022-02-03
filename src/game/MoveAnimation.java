package game;

import animation.Animation;
import animation.NormalStrategy;
import animation.Spirit;
import util.R;
import util.ImageCut;

import java.awt.image.BufferedImage;

public class MoveAnimation extends Animation {
	@Override
	public void init() {
		BufferedImage[] images = ImageCut.getImagesByFilePath(R.IMAGE_PATH+"/ren.png", ImageCut.Hcut, 4);
		Spirit spirit = new Spirit(new NormalStrategy(images[0]));
		Spirit spirit2 = new Spirit(new NormalStrategy(images));
		
		spirit.setName("default");
		spirit2.setName("move");
		
		addSpirit(spirit);
		addSpirit(spirit2);
		
		setDefaultActive("default");
	}
}
