import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;

public class BzztSFX {
    Clip clip; // PRECISAMOS DISSO

    URL soundURL[] = new URL[5];  // AQUI TEMOS UM ARRAY PRA ARMAZENAR OS EFEITOS SONOROS

    public BzztSFX(){
        soundURL[0] = getClass().getResource(ResourceHandler.PREFIX+"bzzt.wav");  // AQUI VIRIA TODOS OS EFEITOS SONOROS POR INDICE
    }


    // SET FILE
    public void setFile(){
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[0]);
            clip = AudioSystem.getClip();                               // FAZER O TRY/CATCH DESSA MANEIRA
            clip.open(ais);
        } catch (UnsupportedAudioFileException e) {
            throw new RuntimeException(e);
        } catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public void start(){    // FUNÇÃO PRA INICIAR A MUSICA
        clip.start();
    }

    public void loop(){     // FUNÇÃO QUE FAZ UM LOOP DA MUSICA (ESSE LOOP É INFINITO)
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop(){     // FUNÇÃO PRA PARAR A MUSICA
        clip.stop();
    }
}

