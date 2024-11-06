import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Text;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Menu {
    private  static Picture screen;
    private static Grid grid = new Grid();
    public static int commandNum = 0;
    public Menu(){
        screen = new Picture(0, 0, ResourceHandler.PREFIX+"FUNDO_MENU_resized.jpg");
        screen.draw();
        DrawTitleScreen();
    }

    public static void DrawTitleScreen(){


        Text title = new Text(grid.WIDTH / 2 -28, grid.HEIGTH / 2- 300, "BUGMAN");  // Centralize text
        title.setColor(Color.DARK_GRAY);
        title.grow(165, 135);
        title.draw();
        title = new Text(grid.WIDTH / 2 -28, grid.HEIGTH / 2- 300, "BUGMAN");  // Centralize text
        title.setColor(Color.RED);
        title.grow(160, 130);
        title.draw();


        // MENU OPTIONS
        Text newGame = new Text(grid.WIDTH / 2 -20 , grid.HEIGTH / 2 + 200, "NEW GAME");
        newGame.setColor(Color.RED);
        newGame.grow(50, 30);
        newGame.draw();
        if(commandNum == 0){
            Text arrow = new Text(grid.WIDTH / 2 - 120, grid.HEIGTH / 2 + 200, ">");
            arrow.setColor(Color.RED);
            arrow.grow(20, 20);
            arrow.draw();
        }


        Text quit = new Text(grid.WIDTH / 2 -20, grid.HEIGTH / 2 + 300, "QUIT");
        quit.setColor(Color.RED);
        quit.grow(30, 30);
        quit.draw();
        if(commandNum == 1){
            Text arrow2 = new Text(grid.WIDTH / 2 - 120, grid.HEIGTH / 2 + 300, ">");
            arrow2.setColor(Color.RED);
            arrow2.grow(20, 20);
            arrow2.draw();
        }
    }
    public static  void updateMenu() {
        screen.delete();
        screen = new Picture(0, 0, ResourceHandler.PREFIX+"FUNDO_MENU_resized.jpg");
        screen.draw();
        DrawTitleScreen();
    }
}
