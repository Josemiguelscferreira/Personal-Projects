import org.academiadecodigo.simplegraphics.graphics.Text;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

public class GameTimer {
    private int second, minute;
    private String ddSecond, ddMinute;
    private DecimalFormat dFormat = new DecimalFormat("00");
    private Timer timer;
    private Text timerDisplay;

    // inicia o tempo e configura o timer
    public GameTimer(int startMinute, int startSecond, Text timerDisplay) {
        this.minute = startMinute;
        this.second = startSecond;
        this.timerDisplay = timerDisplay;

        updateDisplay(); // Atualiza o display inicialmente
        setupTimer();
        timer.start(); // Inicia o temporizador
    }

    //  do timer
    private void setupTimer() {
        timer = new Timer(1000, new ActionListener() { // A cada 1 segundo
            @Override
            public void actionPerformed(ActionEvent e) {
                second--;

                ddSecond = dFormat.format(second);
                ddMinute = dFormat.format(minute);
                updateDisplay();

                if (second == -1) {
                    second = 59;
                    minute--;

                    ddSecond = dFormat.format(second);
                    ddMinute = dFormat.format(minute);
                    updateDisplay();
                }

                // Para o temporizador quando chegar a 00:00 e printa gameover
                if (minute == 0 && second == 0) {
                    timer.stop();

                    // Aqui você pode chamar a lógica de Game Over
                    Game.triggerGameOver();
                }
                if(Grid.computerCount == 0){
                    timer.stop();
                }

                if (Game.gameState == 2){
                    timer.stop();
                }
            }
        });
    }

    // Atualiza a exibição do tempo
    private void updateDisplay() {
        timerDisplay.setText(ddMinute + ":" + ddSecond);
    }
}

