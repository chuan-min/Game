package com.mm.game.util;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class SoundPlayer {
    File file;
    AudioInputStream stream;
    AudioFormat format;
    DataLine.Info info;
    Clip clip;

    public SoundPlayer(){
    }
    public void loadSound(String fileName){
        file = new File(fileName);
        try{
            System.out.println(fileName);
            stream = AudioSystem.getAudioInputStream(file);

        }catch (UnsupportedAudioFileException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }
        format = stream.getFormat();
    }

    public void playeSound() {
        info = new DataLine.Info(Clip.class,format);

        try {
            clip = (Clip) AudioSystem.getLine(info);
            clip.open(stream);
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        clip.start();

    }
}
