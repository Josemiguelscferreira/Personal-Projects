import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;

public class MyKeyboardMenu implements KeyboardHandler {
    private Keyboard keyboard;
    private Menu menu;

    public MyKeyboardMenu(Menu menu){
        keyboard = new Keyboard(this);
        setKeyboardEvent();
        this.menu = menu;
    }
    public void setKeyboardEvent(){
        KeyboardEvent up = new KeyboardEvent();
        up.setKey(KeyboardEvent.KEY_W);
        up.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(up);

        KeyboardEvent down = new KeyboardEvent();
        down.setKey(KeyboardEvent.KEY_S);
        down.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(down);

        KeyboardEvent enter = new KeyboardEvent();
        enter.setKey(KeyboardEvent.KEY_ENTER);
        enter.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(enter);
    }
    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {
        if (Game.gameState == 0) {  // Active menu
            if (keyboardEvent.getKey() == KeyboardEvent.KEY_W) {
                if (Menu.commandNum > 0) {
                    Menu.commandNum--;  // Move arrow up
                    Menu.updateMenu();  // Refreshes menu
                }
            }

            if (keyboardEvent.getKey() == KeyboardEvent.KEY_S) {
                if (Menu.commandNum < 1) {
                    Menu.commandNum++;  // Move arrow down
                    Menu.updateMenu();  // Refreshes menu
                }
            }

            if (keyboardEvent.getKey() == KeyboardEvent.KEY_ENTER) {
                if (Menu.commandNum == 0) {  // "New Game" chosen
                    System.out.println("Game starting...");
                    Game.gameState = 1;  // Changes game state
                    Grid globalGameGrid = new Grid();
                    Game.startGame(globalGameGrid);  // Starts game
                }

                if (Menu.commandNum == 1) {
                    System.exit(0);  // Quits game
                }
            }
        }
    }
        @Override
        public void keyReleased (KeyboardEvent keyboardEvent){

        }
    }
