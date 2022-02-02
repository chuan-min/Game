package util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ImageCut {
	public static final int Hcut = 1;
	public static final int Vcut = 2;
	
	public static BufferedImage cutImage(BufferedImage image,int x,int y,int w,int h){
		BufferedImage result = new BufferedImage(w, h, BufferedImage.TYPE_3BYTE_BGR);
		Graphics2D g = result.createGraphics();
		//????????????????????
		result = g.getDeviceConfiguration().createCompatibleImage(w, h,
			    Transparency.TRANSLUCENT);
		g.dispose();
		Graphics2D g2 = result.createGraphics();
		//??????
//		g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP,0.5f));
		g2.translate(-x, -y);
		g2.drawImage(image, 0, 0, null);
		g2.translate(x, y);
		g2.dispose();
//		g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER)); //????????? ????
		return result;
	}
	
	public static BufferedImage[] getImagesByFilePath(String FilePath,int dir,int number){
		BufferedImage image1 = null;
		BufferedImage[] image2 = new BufferedImage[number];
		try {
			image1 =  ImageIO.read(new File(FilePath));
		} catch (IOException e) {
			System.out.println("?????????");
			e.printStackTrace();
		}
		int width = image1.getWidth();
		int height = image1.getHeight();
		int eachImageW = 0;
		int eachImageH = 0;
		switch (dir) {
		case ImageCut.Hcut://?????
			eachImageW = width/number;
			eachImageH = height;
			for(int i = 0;i < number;i++){
				image2[i] = cutImage(image1,i*eachImageW,0,eachImageW,eachImageH);
			}
			break;
		case ImageCut.Vcut://?????
			eachImageW = width;
			eachImageH = height/number;
			for(int i = 0;i < number;i++){
				image2[i] = cutImage(image1,0,i*eachImageH,eachImageW,eachImageH);
			}
			break;
		default:
			System.out.println("?????????§Ù????????????????§Õ???");
			break;
		}
		for(BufferedImage image : image2){
			try {
				ImageIO.write(image, "png", new File("/home/neo/i.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return image2;
	}
	
	public static BufferedImage[] getImagesByFolderPath(String folderPath){
		List<File> files = new ArrayList<File>();
		File dir = new File(folderPath);
		File[] list = dir.listFiles();
		for(File file : list){
			 String fileName = file.getName();
			 String fileType = fileName.substring(fileName.lastIndexOf(".")+1,fileName.length());
			 if(fileType.equals("jpg") || fileType.equals("png")){
				files.add(file);
			 }
		}
		Collections.sort(files,new Comparator<File>(){
			@Override
			public int compare(File o1, File o2) {
				String name1 = o1.getName().split("\\.")[0];
				String name2 = o2.getName().split("\\.")[0];
				return Integer.parseInt(name1) - Integer.parseInt(name2);
			}
		});
		
		List<BufferedImage> images = new ArrayList<BufferedImage>();
		for(File file : files){
			try {
				images.add(ImageIO.read(file));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		BufferedImage[] images2 = new BufferedImage[images.size()];
		for(int i = 0; i < images.size(); i++){
			images2[i] = images.get(i);
		}
		
		return images2;
	}
	
}
