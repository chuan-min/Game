package game;

import animation.NormalStrategy;
import animation.Spirit;
import ui.Button;
import ui.UIPanel;
import util.R;
import core.Game;
import core.GameWorld;
import scene.PhysicalScene;
import view.RectViewPort;

import java.awt.*;

/**
 * @TODO(����):��Ϸ��ʼ���
 * @CRATE_TIME:22-2-3 ����1:28
 * @AUTHOR: Li Chuanmin
 * @VERSION: v_0.1
 */
public class StartGame extends Game {
    private PhysicalScene scene = null;
    @Override
    /**
     * ��ʼ����Ϸ
     */
    public void init() {
        /**
         * viewPort:����
         */
        RectViewPort viewPort = new RectViewPort(0,0, R.SCREEN_WIDTH, R.SCREEN_HEIGHT);
        /**
         * scene:��һ���������(JPanel)
         */
        scene = new PhysicalScene(R.SCREEN_WIDTH, R.SCREEN_HEIGHT, Color.white, 2.0f/60.0f, 10f);
        /**
         * bk:����ͼƬ
         */
        Spirit bk = new Spirit(new NormalStrategy(R.IMAGE_PATH+"/bk.gif", "gif"));
        bk.start();
        /**
         * ����������ӱ�����ɫ
         */
        scene.setSpirit(bk);
        /**
         * ��������������
         */
        viewPort.setScene(scene);


        UIPanel panel = new UIPanel(300, 300);
        panel.setOpacity(0.7f);

        ui.Button button = new Button(60, 20, 120, 50, 5, 5);
        button.setText("Start Game");

        ui.Button button2 = new Button(60, 80, 120, 50, 5, 5);
        button2.setText("Exit Game");


        panel.addUI(button);
        panel.addUI(button2);

        scene.setUIPanel(panel);

    }

    @Override
    public void start() {
        //��ʼ����Ϸ
        init();
        //���ڵ���ʾ
        GameWorld.view.setVisible(true);
        //����ȡ����
        scene.requestFocus();
        //��������߳�
        scene.startWorld();
    }

    @Override
    public void restart() {

    }

    @Override
    public void suspended() {

    }

    @Override
    public void stop() {

    }

    public static void main(String[] args) {
        new StartGame().start();
    }
}
