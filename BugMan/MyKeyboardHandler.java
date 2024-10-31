import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;

public class MyKeyboardHandler implements org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler {
    private Keyboard keyboard;
    private BugMan bugMan;


    public MyKeyboardHandler(BugMan man) {
        keyboard = new Keyboard(this);
        setKeyboardEvent();
        this.bugMan = man;

    }

    public void setKeyboardEvent() {
        KeyboardEvent left = new KeyboardEvent();
        left.setKey(KeyboardEvent.KEY_LEFT);
        left.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(left);

        KeyboardEvent right = new KeyboardEvent();
        right.setKey(KeyboardEvent.KEY_RIGHT);
        right.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(right);

        KeyboardEvent up = new KeyboardEvent();
        up.setKey(KeyboardEvent.KEY_UP);
        up.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(up);

        KeyboardEvent down = new KeyboardEvent();
        down.setKey(KeyboardEvent.KEY_DOWN);
        down.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(down);

    }

    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {
        if (Game.gameState == 1) {
            if (keyboardEvent.getKey() == KeyboardEvent.KEY_LEFT) {
                bugMan.moveLeft();
            }

            if (keyboardEvent.getKey() == KeyboardEvent.KEY_RIGHT) {
                bugMan.moveRight();
            }

            if (keyboardEvent.getKey() == KeyboardEvent.KEY_UP) {
                bugMan.moveUp();
            }

            if (keyboardEvent.getKey() == KeyboardEvent.KEY_DOWN) {
                bugMan.moveDown();
            }
        }
    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {
        // Não precisa fazer nada aqui por enquanto
    }
}
