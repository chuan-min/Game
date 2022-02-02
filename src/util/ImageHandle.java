package util;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.gif4j.GifDecoder;
import com.gif4j.GifFrame;
import com.gif4j.GifImage;

public class ImageHandle {
	public static BufferedImage getImageByPath(String path){
		BufferedImage image = null;
		try {
			image = ImageIO.read(new File(path));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return image;
	}
	
	public static BufferedImage[] getImagesByGIF(String path){
		GifImage gifImage = null;
		try {
			gifImage = GifDecoder.decode(new File(path));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BufferedImage[] images = new BufferedImage[gifImage.getNumberOfFrames()];
		int w = gifImage.getScreenWidth();
		int h = gifImage.getScreenHeight();
		for (int i = 0; i < gifImage.getNumberOfFrames(); i++) {
            GifFrame frame = gifImage.getFrame(i);
            BufferedImage bufferedImage = frame.getAsBufferedImage();
           
            BufferedImage oneFrame = new BufferedImage(w, h, BufferedImage.TYPE_3BYTE_BGR);
            Graphics2D g = oneFrame.createGraphics();
    		//??????????
            oneFrame = g.getDeviceConfiguration().createCompatibleImage(w, h,
    			    Transparency.TRANSLUCENT);
    		g.dispose();
    		Graphics2D g2 = oneFrame.createGraphics();
    		g2.drawImage(bufferedImage, frame.getX(), frame.getY(), null);
    		g2.dispose();
    		images[i] = oneFrame;
        }
		return images;
	}
	
	public static BufferedImage setAlpha(BufferedImage image,float alpha){
		 int width = image.getWidth();
		 int height = image.getHeight();
		 BufferedImage re = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		 for(int i = 0; i< width;i++){
			 for(int j = 0;j < height;j++){
				 int rgb = image.getRGB(i, j);
				 Color old = new Color(rgb);
				 Color color = new Color(old.getRed(), old.getGreen(),old.getBlue(), (int)alpha);
				 re.setRGB(i, j, color.getRGB());
			 }
		 }
		 return re;
	}
}
