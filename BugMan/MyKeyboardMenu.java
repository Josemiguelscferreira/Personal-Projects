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
        KeyboardEvent cima = new KeyboardEvent();
        cima.setKey(KeyboardEvent.KEY_W);
        cima.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(cima);

        KeyboardEvent baixo = new KeyboardEvent();
        baixo.setKey(KeyboardEvent.KEY_S);
        baixo.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(baixo);

        KeyboardEvent enter = new KeyboardEvent();
        enter.setKey(KeyboardEvent.KEY_ENTER);
        enter.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(enter);
    }
    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {
        if (Game.gameState == 0) {  // Menu ativo
            if (keyboardEvent.getKey() == KeyboardEvent.KEY_W) {
                if (Menu.commandNum > 0) {
                    Menu.commandNum--;  // Move a seleção para cima
                    Menu.updateMenu();  // Atualiza o menu
                }
            }

            if (keyboardEvent.getKey() == KeyboardEvent.KEY_S) {
                if (Menu.commandNum < 1) {
                    Menu.commandNum++;  // Move a seleção para baixo
                    Menu.updateMenu();  // Atualiza o menu
                }
            }

            if (keyboardEvent.getKey() == KeyboardEvent.KEY_ENTER) {
                if (Menu.commandNum == 0) {  // "New Game" selecionado
                    System.out.println("Iniciando o jogo...");
                    Game.gameState = 1;  // Muda o estado do jogo
                    Grid globalGameGrid = new Grid();
                    Game.startGame(globalGameGrid);  // Chama a função para iniciar o jogo
                }

                if (Menu.commandNum == 1) {
                    System.exit(0);  // Sai do jogo
                }
            }
        }
    }
        @Override
        public void keyReleased (KeyboardEvent keyboardEvent){

        }
    }
