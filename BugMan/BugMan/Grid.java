import org.academiadecodigo.simplegraphics.graphics.Rectangle;

public class Grid {

    final static int WIDTH = 1170;
    final static int HEIGTH = 900;
    public static int computerCount = 0;
    final int CELL_SIZE = 40;
    final int OFFSET_X = 29;
    final int OFFSET_Y = 10;


    public Object[] arrayPC = new Object[300];
    public int pcSerial = 0;

    public int[][] map = {
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 0, 2, 0, 2, 0, 2, 0, 2, 0, 2, 0, 2, 0, 2, 0, 2, 0, 2, 0, 2, 0, 2, 0, 2, 0, 1},
            {1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 1, 1, 2, 1, 1, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1},
            {1, 0, 1, 0, 2, 0, 1, 0, 1, 0, 2, 0, 2, 0, 2, 0, 2, 0, 1, 0, 1, 0, 2, 0, 1, 0, 1},
            {1, 2, 1, 1, 1, 1, 1, 2, 1, 2, 1, 1, 1, 2, 1, 1, 1, 2, 1, 2, 1, 1, 1, 1, 1, 2, 1},
            {1, 0, 2, 0, 2, 0, 2, 0, 1, 0, 2, 0, 2, 0, 2, 0, 2, 0, 1, 0, 2, 0, 2, 0, 2, 0, 1},
            {1, 2, 1, 2, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 2, 1, 2, 1},
            {1, 0, 1, 0, 2, 0, 2, 0, 2, 0, 2, 0, 2, 0, 2, 0, 2, 0, 2, 0, 2, 0, 2, 0, 1, 0, 1},
            {1, 2, 1, 2, 1, 1, 0, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 0, 1, 1, 2, 1, 2, 1},
            {1, 0, 1, 0, 1, 0, 2, 0, 1, 0, 1, 0, 0, 0, 0, 0, 1, 0, 1, 0, 2, 0, 1, 0, 1, 0, 1},
            {1, 2, 1, 2, 0, 2, 1, 2, 1, 2, 1, 0, 0, 0, 0, 0, 1, 2, 1, 2, 1, 2, 0, 2, 1, 2, 1},
            {1, 0, 2, 0, 1, 0, 2, 0, 1, 0, 1, 0, 0, 0, 0, 0, 1, 0, 1, 0, 2, 0, 1, 0, 2, 0, 1},
            {1, 1, 1, 2, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 2, 1, 1, 1},
            {1, 0, 2, 0, 2, 0, 2, 0, 2, 0, 2, 0, 2, 0, 2, 0, 2, 0, 2, 0, 2, 0, 2, 0, 2, 0, 1},
            {1, 2, 1, 2, 1, 1, 1, 2, 1, 2, 1, 1, 1, 1, 1, 1, 1, 2, 1, 2, 1, 1, 1, 2, 1, 2, 1},
            {1, 0, 2, 0, 2, 0, 1, 0, 2, 0, 2, 0, 2, 0, 2, 0, 2, 0, 2, 0, 1, 0, 2, 0, 2, 0, 1},
            {1, 1, 1, 2, 1, 2, 1, 1, 1, 1, 1, 2, 1, 2, 1, 2, 1, 1, 1, 1, 1, 2, 1, 2, 1, 1, 1},
            {1, 0, 2, 0, 1, 0, 2, 0, 2, 0, 2, 0, 1, 0, 1, 0, 2, 0, 2, 0, 2, 0, 1, 0, 2, 0, 1},
            {1, 2, 1, 2, 1, 2, 1, 1, 1, 1, 1, 2, 1, 2, 1, 2, 1, 1, 1, 1, 1, 2, 1, 2, 1, 2, 1},
            {1, 0, 2, 0, 2, 0, 2, 0, 2, 0, 2, 0, 1, 0, 1, 0, 2, 0, 2, 0, 2, 0, 2, 0, 2, 0, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}

            //0 is and empty space
            //1 is a wall
            //2 is a computer
    };

    public void drawMap() {
        for (int row = 0; row < map.length; row++) {
            for (int col = 0; col < map[row].length; col++) {
                if (map[row][col] == 1) {
                    Rectangle wall = new Rectangle(
                            ((col * CELL_SIZE) + OFFSET_X),
                            ((row * CELL_SIZE) + OFFSET_Y),
                            CELL_SIZE,
                            CELL_SIZE);
                }//NEW THINGS
                else if (map[row][col] == 2) { //creates PCs and adds them to the array
                    Computer pc = new Computer(this, col, row, ResourceHandler.PREFIX+"pc.png");
                    arrayPC[pcSerial] = pc.getPC();
                    computerCount++;
                    pcSerial += 1;
                }
            }
        }
    }

    public void pcCheck() {
        for (int i = 0; i < arrayPC.length - 1; i++) {
            Computer pc = (Computer) arrayPC[i];
            if (pc != null) {
                if (!pc.isDeleted()) {
                    int checkRow = pc.getRow();
                    int checkCol = pc.getCol();
                    map[checkRow][checkCol] = 2;
                }
            }
        }
    }
}


