package com.mm.game;

import com.mm.game.util.Direct;
import com.mm.game.util.R;
import com.mm.game.util.SoundPlayer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MyPanel extends JPanel implements KeyListener {
    /**
     * 背景音乐
     */
    private SoundPlayer sound = null;

    /**
     *设定背景方格默认行数
     */
    private  static final int ROW = 15;
    /**
     *设定背景方格默认列数
     */
    private  static final int COL=15;

    /**
     * 方格默认大小32x32
     */
    private static final int CS = 32;

    /**
     * 设置默认地图 15x15
      */
    private int[][] map = {
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
            {1,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
            {1,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
            {1,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
            {1,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
            {1,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
            {1,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
            {1,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
            {1,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
            {1,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
            {1,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
            {1,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
            {1,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
            {1,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}
    };

    /**
     * 图片资源
     */
    private Image floorImage;
    private Image wallImage;
    /**
     * 游戏角色
     */
    private int x,y=10;
    private Image roleImage;

    /**
     * 角色方向
     */
    private  Direct direction= Direct.DOWN;

    /**
     * 记步器
     */
    private int count;

    private Thread threadAnime;
    /**
     * 背景图片
     */
    private Image backGroundImage;

    public MyPanel(){
        setPreferredSize(new Dimension(R.SCREEN_WIDTH,R.SCREEN_HEIGHT));
        /**
         * 播放背景音乐
         */
        loadBackGroundMusic();

        setFocusable(true);
        addKeyListener(this);
        threadAnime= new Thread(new AnimationThread());
        threadAnime.start();
    }

    private void loadBackGroundImage(Graphics g) {
        backGroundImage = new ImageIcon(R.IMAGE_PATH+"/background.jpg").getImage();
        g.drawImage(backGroundImage,0,0,this);
    }

    private void loadBackGroundMusic(){
        if (sound == null) {
            sound = new SoundPlayer();
        }
        sound.loadSound( R.SOUND_PATH+"/bgm.wav");
        sound.playeSound();
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {


    }
    @Override
    public void keyTyped(KeyEvent keyEvent) {
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        if (sound == null) {
            sound = new SoundPlayer();
        }
        int keyCode = keyEvent.getKeyCode();
        switch(keyCode){
            case KeyEvent.VK_SPACE:
                System.out.println("空格键");
                sound.loadSound(R.SOUND_PATH+"/jump.wav");

                sound.playeSound();
                break;
            case KeyEvent.VK_UP:
                direction= Direct.UP;
                y -= CS;
                break;
            case KeyEvent.VK_DOWN:
                direction= Direct.DOWN;
                y += CS;
                break;
            case KeyEvent.VK_LEFT:
                direction= Direct.LEFT;
                x -= CS;
                break;
            case KeyEvent.VK_RIGHT:
                direction= Direct.RIGHT;
                x += CS;
                break;
            case KeyEvent.VK_ESCAPE:
            System.exit(0);
            break;
        }
    }
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        //drawMap(g);
        loadBackGroundImage(g);
        drawRole(g);

    }

    private void drawRole(Graphics g) {
        roleImage = new ImageIcon(R.IMAGE_PATH+"/hero.png").getImage();

        if(direction==Direct.DOWN){
            g.drawImage(roleImage,x,y,x+260,y+340,
                    0,0,260,340,this);
        }else if(direction==Direct.UP){
            g.drawImage(roleImage,x,y,x+260,y+340,
                    780,0,1040,340,this);
        }else if(direction==Direct.RIGHT){
            g.drawImage(roleImage,x,y,x+260,y+340,
                    520,0,260,340,this);
        }else if(direction==Direct.LEFT){
            g.drawImage(roleImage,x,y,x+260,y+340,
                    260,0,540,340,this);
        }
    }

    private void drawMap(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(0,0,this.getWidth(),this.getHeight());
        for (int i = 0;i<15;i++) {
            for (int j = 0;j<15;j++) {
                if(map[i][j]==0){
                    g.setColor(new Color(108, 76, 100));
                    g.fillRect(i*CS,j*CS,CS,CS);
                }else if(map[i][j]==1){
                    g.setColor(new Color(54, 22, 7));
                    g.fillRect(i*CS,j*CS,CS,CS);
                }
                g.setColor(new Color(205, 231, 182));
                g.drawRect(i*CS,j*CS,CS,CS);
            }
        }

    }

    private class AnimationThread implements Runnable {
        @Override
        public void run() {
            while(true){
                if (count==0){
                    count = 1;
                }else if (count==1){
                    count = 0;
                }
                repaint();
                try{
                    Thread.sleep(300);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }
}
