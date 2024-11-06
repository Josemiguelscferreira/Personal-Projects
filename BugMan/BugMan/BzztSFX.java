import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;

public class BzztSFX {
    Clip clip;

    URL soundURL[] = new URL[5];  //Sound fx array

    public BzztSFX(){
        soundURL[0] = getClass().getResource(ResourceHandler.PREFIX+"bzzt.wav");
    }


    // SET FILE
    public void setFile(){
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[0]);
            clip = AudioSystem.getClip();
            clip.open(ais);
        } catch (UnsupportedAudioFileException e) {
            throw new RuntimeException(e);
        } catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public void start(){    // Starts music
        clip.start();
    }

    public void loop(){     // Loops music
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop(){     // Stops music
        clip.stop();
    }
}

