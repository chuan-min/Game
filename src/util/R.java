package util;

/**
 * 游戏的常量类
 */
public interface R {
    public static final String ROOT_PATH=""+System.getProperty("user.dir");
    public static final String SOUND_PATH=ROOT_PATH+"/sounds";
    public static final String IMAGE_PATH=ROOT_PATH+"/images";

    public static final String TITLE = "游戏引擎";
    public static final int SCREEN_WIDTH = 800;
    public static final int SCREEN_HEIGHT = 640;

    public static final int WORLD_WIDTH = SCREEN_WIDTH * 2;
    public static final int WORLD_HEIGHT = SCREEN_HEIGHT * 2;

}