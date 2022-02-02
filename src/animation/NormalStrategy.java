package animation;

import util.ImageCut;
import util.ImageHandle;

import java.awt.image.BufferedImage;

public class NormalStrategy implements Strategy{
	
	private int state = NoStart;
	private BufferedImage[] images = null;
	private int curNumber = 0;
	private BufferedImage curImage = null;
	
	public NormalStrategy(String folderPath) {
		images = ImageCut.getImagesByFolderPath(folderPath);
	}
	
	public NormalStrategy(String FilePath,int dir,int number){
		images = ImageCut.getImagesByFilePath(FilePath, dir,number);
	}
	
	public NormalStrategy(String FilePath,String type){
//		System.out.println(FilePath);
		if(type.equals("png") || type.equals("jpg")){
			images = new BufferedImage[1];
			images[0] = ImageHandle.getImageByPath(FilePath);
		}else if(type.equals("gif")){
			images = ImageHandle.getImagesByGIF(FilePath);
		}
	}
	
	public NormalStrategy(BufferedImage[] images){
		this.images = images;
	}
	
	public NormalStrategy(BufferedImage image){
		images = new BufferedImage[1];
		images[0] = image;
	}
	
	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}
	
	public BufferedImage getOneFrameByStart(){
		if(curNumber >= images.length){
			curNumber = 0;
		}
		
		curImage = images[curNumber++];
		
		return curImage;
	}
	
	public BufferedImage getOneFrameByStop(){
		curNumber = 0;
		return images[0];
	}	
	
	public BufferedImage getOneFrameBySus(){
		return curImage;
	}
	
	public BufferedImage getOneFrameByNoStart(){
		return images[0];
	}
}
