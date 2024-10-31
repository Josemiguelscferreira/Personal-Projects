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

        // SETANDO LABEL DO TITULO DO JOGO E A SOMBRA
        Text title = new Text(grid.COMPRI / 2 -28, grid.ALTURA / 2- 300, "BUGMAN");  // Centraliza o texto
        title.setColor(Color.DARK_GRAY);  // Define a cor do título
        title.grow(165, 135);  // Aumenta o tamanho da caixa de texto para que fique mais visível
        title.draw();
        title = new Text(grid.COMPRI / 2 -28, grid.ALTURA / 2- 300, "BUGMAN");  // Centraliza o texto
        title.setColor(Color.RED);  // Define a cor do título
        title.grow(160, 130);  // Aumenta o tamanho da caixa de texto para que fique mais visível
        title.draw();


        // MENU OPTIONS
        Text newGame = new Text(grid.COMPRI / 2 -20 , grid.ALTURA / 2 + 200, "NEW GAME");
        newGame.setColor(Color.RED);
        newGame.grow(50, 30);
        newGame.draw();
        if(commandNum == 0){
            Text seta = new Text(grid.COMPRI / 2 - 120, grid.ALTURA / 2 + 200, ">");
            seta.setColor(Color.RED);
            seta.grow(20, 20);
            seta.draw();
        }


        Text quit = new Text(grid.COMPRI / 2 -20, grid.ALTURA / 2 + 300, "QUIT");
        quit.setColor(Color.RED);
        quit.grow(30, 30);
        quit.draw();
        if(commandNum == 1){
            Text seta = new Text(grid.COMPRI / 2 - 120, grid.ALTURA / 2 + 300, ">");
            seta.setColor(Color.RED);
            seta.grow(20, 20);
            seta.draw();
        }
    }
    public static  void updateMenu() {
        screen.delete();  // Se necessário, apaga a tela anterior
        screen = new Picture(0, 0, ResourceHandler.PREFIX+"FUNDO_MENU_resized.jpg");  // Redefine a tela
        screen.draw();
        DrawTitleScreen();  // Redesenha o menu com o novo estado
    }
}
