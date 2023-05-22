package main;


import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URL;

public class Sound {
    AudioInputStream ais;
    Clip clip;
    URL[] soundURL = new URL[10];


    public Sound() {
        soundURL[0] = getResourceURL("data/sound/music.wav");
        soundURL[1] = getResourceURL("data/sound/box.wav");
        soundURL[2] = getResourceURL("data/sound/gate.wav");
        soundURL[3] = getResourceURL("data/sound/item.wav");
        soundURL[4] = getResourceURL("data/sound/idle.wav");

    }

    private URL getResourceURL(String resourcePath) {
        try {
            File file = new File(resourcePath);
            URI uri = file.toURI();
            return uri.toURL();
        } catch (IOException e) {
            System.out.println("error get resource : " + e.getMessage());
        }
        return null;
    }

    public void setFile(int i) {
        try {
            ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println("error set sound file: " + e.getMessage());
        }
    }

    public void play() {
        clip.start();
    }

    public void loop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop() {
        clip.stop();
    }

    boolean isPlaying = false;
    public void playWalk(){
        if(isPlaying){
            return;
        }
        isPlaying = true;
        clip.start();

        new Thread(() -> {
            try{
                Thread.sleep(300); // delay for 0.3s
                clip.stop();
                isPlaying = false;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}