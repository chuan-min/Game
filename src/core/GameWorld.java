package core;

import scene.Scene;
import view.ViewPort;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class GameWorld {
	
	
	/*??????????????????????λ?????*/
	public static final float RETA = 10;
	
	public static ViewPort view = null;
	/**
	 * CopyOnWriteArrayList:是线程安全的ArrayList类,防止多线程同时写数据时覆盖数据的问题
	 */
	public static List<Scene> scenes = new CopyOnWriteArrayList<Scene>();
	
	public static Scene getSceneByName(String name){
		for(Scene scene : scenes){
			if(scene.getName() != null && scene.getName().equals(name)){
				return scene;
			}
		}
		return null;
	}
	
}
