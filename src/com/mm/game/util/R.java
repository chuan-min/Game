package com.mm.game.util;

/**
 * 游戏的常量类
 */
public interface R {
    public static final String ROOT_PATH=""+System.getProperty("user.dir");
    public static final String SOUND_PATH=ROOT_PATH+"/resource/sounds";
    public static final String IMAGE_PATH=ROOT_PATH+"/resource/images";

    public static final String TITLE = "迷宫游戏";
    public static final int SCREEN_WIDTH = 480;
    public static final int SCREEN_HEIGHT = 512;

    public static final int WORLD_WIDTH = SCREEN_WIDTH * 5;
    public static final int WORLD_HEIGHT = SCREEN_HEIGHT * 3;

}