package actor;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 * ��ʾ����
 */
public interface Display {
     public void display(Actor actor,BufferedImage image,Graphics2D g2d);
}
