import org.academiadecodigo.simplegraphics.graphics.Canvas;
import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Text;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Game {
    public static int gameState = 0;
    static Sound som = new Sound();      // INSTANCIANDO UM SOM
    private static boolean isGameOver = false;
    private static boolean isIsGameWon = false;
    private static Text timerText;
    private static GameTimer gameTimer;
    public Ghost[] arrayGhost;
    public Game(Grid globalGameGrid) {                     // CONSTRUTOR
        //Grid globalGameGrid = new Grid();             // INSTANCIANDO UMA GRID

        // SETANDO CANVAS
        Canvas.setMaxX(globalGameGrid.COMPRI - 10);
        Canvas.setMaxY(globalGameGrid.ALTURA - 10);

        // TITLE SCREEN
        if (gameState == 0) {
            Menu menu = new Menu();
            new MyKeyboardMenu(menu);
        }
    }

    public static void startGame(Grid globalGameGrid) {
        // Aqui você desenha a tela do jogo

        // Desenha o fundo do jogo
        Picture background = new Picture(0, 0, ResourceHandler.PREFIX+"/map.jpg");
        background.draw();

        // Desenha a grid/mapa do jogo
        //Grid grid = new Grid();
        globalGameGrid.drawMap();

        // Inicia o som de fundo
        playMusic();
        final BugMan bugMan = new BugMan(globalGameGrid);
        final Leith ghost = new Leith(ResourceHandler.PREFIX+"imgLeith.png", 11, 9,globalGameGrid,bugMan);
        final Chris ghost1 = new Chris(ResourceHandler.PREFIX+"imgChris.png", 12, 9,globalGameGrid,bugMan);
        final Maggie ghost2 = new Maggie(ResourceHandler.PREFIX+"imgMaggie.png",13, 9,globalGameGrid,bugMan);

        new MyKeyboardHandler(bugMan); // Instancia o BugMan e começa a mover com o teclado

        // Loop para o movimento contínuo
        new Thread(new Runnable() {
            public void run() {
                while (Game.gameState == 1) { // Certifique-se de que o jogo está ativo
                    bugMan.move(); // Chama o método move do BugMan
                    bugMan.checkCollision(ghost);
                    showYouWon();
                    try {
                        Thread.sleep(100); // Ajuste a velocidade conforme necessário
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        timerText = new Text(1000, 20, "");
        timerText.grow(40, 40);
        timerText.setColor(Color.RED);
        timerText.draw();
        gameTimer = new GameTimer(1, 30, timerText);   // Inicia o temporizador de 1 minuto e 30 segundos

        new Thread(new Runnable() {
            public void run() {
                while (Game.gameState == 1) { // Certifique-se de que o jogo está ativo
                    ghost.randomMove(); // Chama o método move do Ghost

                    try {
                        Thread.sleep(250); // Ajuste a velocidade conforme necessário
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
        new Thread(new Runnable() {
            public void run() {
                while (Game.gameState == 1) { // Certifique-se de que o jogo está ativo
                    ghost1.randomMove(); // Chama o método move do Ghost

                    try {
                        Thread.sleep(250); // Ajuste a velocidade conforme necessário
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
        new Thread(new Runnable() {
            public void run() {
                while (Game.gameState == 1) { // Certifique-se de que o jogo está ativo
                    ghost2.randomMove(); // Chama o método move do Ghost

                    try {
                        Thread.sleep(250); // Ajuste a velocidade conforme necessário
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    public static void triggerGameOver() {
        isGameOver = true; // Define o estado de Game Over
        gameState = 2; // Altera o estado do jogo para Game Over
        stopMusic();
        showGameOverScreen(); // Exibe a tela de Game Over
    }

    private static void showGameOverScreen() {
        if (!isGameOver) {
            return;
        }
        Text newgameOverText = new Text((Grid.COMPRI /2)- 5, (Grid.ALTURA / 3) + 55, "Game Over!");
        newgameOverText.grow(250, 200);
        newgameOverText.setColor(Color.BLACK);// Aumenta o tamanho do texto
        newgameOverText.draw();
        Text gameOverText = new Text(Grid.COMPRI /2 - 10, (Grid.ALTURA / 3) + 60, "Game Over!");
        gameOverText.grow(250, 200);
        gameOverText.setColor(Color.RED);// Aumenta o tamanho do texto
        gameOverText.draw();
    }
    private static void showYouWon() {
        if (Grid.computerCount == 0) {
            isIsGameWon = true;
            gameState = 2;
            Text shadowYouWon = new Text(Grid.COMPRI / 2 - 5, (Grid.ALTURA / 3) + 55, "You Won!");
            shadowYouWon.grow(250, 200);
            shadowYouWon.setColor(Color.BLACK);// Aumenta o tamanho do texto
            shadowYouWon.draw();
            Text youWon = new Text(Grid.COMPRI / 2 - 10, (Grid.ALTURA / 3) + 60, "You Won!");
            youWon.grow(250, 200);
            youWon.setColor(Color.BLUE);// Aumenta o tamanho do texto
            youWon.draw();
        }
    }
    // METODO PARA TOCAR A MUSICA
    public static void playMusic() {
        som.setFile();
        som.start();
        som.loop();
    }

    // METODO PARA PARAR A MUSICA
    public static void stopMusic() {
        som.stop();
    }
}
