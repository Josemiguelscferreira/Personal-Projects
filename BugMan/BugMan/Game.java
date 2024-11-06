import org.academiadecodigo.simplegraphics.graphics.Canvas;
import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Text;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Game {
    public static int gameState = 0;
    static Sound sound = new Sound();
    private static boolean isGameOver = false;
    private static boolean isGameWon = false;
    private static Text timerText;
    private static GameTimer gameTimer;
    public Ghost[] arrayGhost;
    public Game(Grid globalGameGrid) {


        Canvas.setMaxX(globalGameGrid.WIDTH - 10);
        Canvas.setMaxY(globalGameGrid.HEIGTH - 10);

        // TITLE SCREEN
        if (gameState == 0) {
            Menu menu = new Menu();
            new MyKeyboardMenu(menu);
        }
    }

    public static void startGame(Grid globalGameGrid) {


        // Draw background
        Picture background = new Picture(0, 0, ResourceHandler.PREFIX+"/map.jpg");
        background.draw();

        //Draw grid
        globalGameGrid.drawMap();

        //Start background sound
        playMusic();
        final BugMan bugMan = new BugMan(globalGameGrid);
        final Leith ghost = new Leith(ResourceHandler.PREFIX+"imgLeith.png", 11, 9,globalGameGrid,bugMan);
        final Chris ghost1 = new Chris(ResourceHandler.PREFIX+"imgChris.png", 12, 9,globalGameGrid,bugMan);
        final Maggie ghost2 = new Maggie(ResourceHandler.PREFIX+"imgMaggie.png",13, 9,globalGameGrid,bugMan);

        new MyKeyboardHandler(bugMan); //instances bugman and starts keyboard

        // Loop for continuous movement
        new Thread(new Runnable() {
            public void run() {
                while (Game.gameState == 1) {
                    bugMan.move();
                    bugMan.checkCollision(ghost);
                    showYouWon();
                    try {
                        Thread.sleep(100); //Set speed
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
        gameTimer = new GameTimer(1, 30, timerText);   // Set timer

        new Thread(new Runnable() {
            public void run() {
                while (Game.gameState == 1) {
                    ghost.randomMove();

                    try {
                        Thread.sleep(250); //Set speed
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
        new Thread(new Runnable() {
            public void run() {
                while (Game.gameState == 1) {
                    ghost1.randomMove();

                    try {
                        Thread.sleep(250); //Set speed
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
        new Thread(new Runnable() {
            public void run() {
                while (Game.gameState == 1) {
                    ghost2.randomMove();

                    try {
                        Thread.sleep(250); //Set speed
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    public static void triggerGameOver() {
        isGameOver = true; // Game Over
        gameState = 2; // Game Over
        stopMusic();
        showGameOverScreen(); // Show Game Over Screen
    }

    private static void showGameOverScreen() {
        if (!isGameOver) {
            return;
        }
        Text newgameOverText = new Text((Grid.WIDTH /2)- 5, (Grid.HEIGTH / 3) + 55, "Game Over!");
        newgameOverText.grow(250, 200);
        newgameOverText.setColor(Color.BLACK);
        newgameOverText.draw();
        Text gameOverText = new Text(Grid.WIDTH /2 - 10, (Grid.HEIGTH / 3) + 60, "Game Over!");
        gameOverText.grow(250, 200);
        gameOverText.setColor(Color.RED);
        gameOverText.draw();
    }
    private static void showYouWon() {
        if (Grid.computerCount == 0) {
            isGameWon = true;
            gameState = 2;
            Text shadowYouWon = new Text(Grid.WIDTH / 2 - 5, (Grid.HEIGTH / 3) + 55, "You Won!");
            shadowYouWon.grow(250, 200);
            shadowYouWon.setColor(Color.BLACK);
            shadowYouWon.draw();
            Text youWon = new Text(Grid.WIDTH / 2 - 10, (Grid.HEIGTH / 3) + 60, "You Won!");
            youWon.grow(250, 200);
            youWon.setColor(Color.BLUE);
            youWon.draw();
        }
    }

    public static void playMusic() {
        sound.setFile();
        sound.start();
        sound.loop();
    }


    public static void stopMusic() {
        sound.stop();
    }
}
