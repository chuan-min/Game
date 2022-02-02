package actor;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import org.jbox2d.common.Vec2;

public class ScaleDisplay implements Display {

	@Override
	public void display(Actor actor, BufferedImage image, Graphics2D g2d) {
		Rectangle2D rect = actor.getShape().getBounds2D();
		float angle = actor.getAngle();
		Vec2 vec2 = actor.getCenter();
		g2d.rotate(angle, vec2.x,vec2.y);
		g2d.drawImage(image,(int)rect.getX(), (int)rect.getY(),
				(int)rect.getWidth(), (int)rect.getHeight(),  null);
		g2d.rotate(-angle, vec2.x, vec2.y);

	}

}
