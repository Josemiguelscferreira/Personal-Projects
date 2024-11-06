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

    // set timer
    public GameTimer(int startMinute, int startSecond, Text timerDisplay) {
        this.minute = startMinute;
        this.second = startSecond;
        this.timerDisplay = timerDisplay;

        updateDisplay();
        setupTimer();
        timer.start();
    }


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

                // Stops timer when it reaches 00:00 and prints gameover
                if (minute == 0 && second == 0) {
                    timer.stop();


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


    private void updateDisplay() {
        timerDisplay.setText(ddMinute + ":" + ddSecond);
    }
}

