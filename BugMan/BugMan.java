import org.academiadecodigo.simplegraphics.pictures.Picture;

public class BugMan {
    private static final int LIFE = 3;
    private static boolean isDead;
    private static int position_X;
    private static int position_Y;
    private final Grid globalGameGrid;
    public int col = 13;
    public int row = 7;
    private Picture pacman;
    static BzztSFX bzzt = new BzztSFX();

    public Ghost ghost1;
    public Ghost ghost2;
    public Ghost ghost3;

    // Direção do movimento
    public String motion = "none";
    public String prevMotion = motion;

    //Grid grid = new Grid();

    public BugMan(Grid globalGameGrid) {
        this.pacman = new Picture(globalGameGrid.CELL_SIZE * col + globalGameGrid.OFFSET_X, globalGameGrid.CELL_SIZE * row + globalGameGrid.OFFSET_Y, ResourceHandler.PREFIX+"bugmanRight.png");
        pacman.draw();
        this.globalGameGrid = globalGameGrid;
    }

    // Método para mover o BugMan
    public void move() {
        globalGameGrid.pcCheck();
        switch (motion) {
            case ("up"): {
                if (globalGameGrid.map[row - 1][col] != 1) {
                    if (globalGameGrid.map[row - 1][col] == 3) {//se pegou
                        checkCollisionSelf();
                    }
                    if (globalGameGrid.map[row - 1][col] == 2) {//se houver um PC
                        for (int i = 0; i < globalGameGrid.arrayPC.length - 1; i++) {
                            Computer obj = (Computer) globalGameGrid.arrayPC[i];
                            if (obj != null) {
                                System.out.println("ENCONTREI UM PC");
                                int objRow = obj.getRow();
                                int objCol = obj.getCol();
                                if (objRow == row - 1 && objCol == col) {
                                    if (!obj.isDeleted()) {
                                        obj.deletePC();
                                        bzzt.setFile();
                                        bzzt.start();
                                        globalGameGrid.map[row - 1][col] = 0;
                                        break;
                                    }
                                }
                            }
                        }
                    }
                    pacman.translate(0, -globalGameGrid.CELL_SIZE);
                    pacman.load(ResourceHandler.PREFIX+"bugmanUp.png");
                    row--;
                } else {
                    motion = "none"; // Pare se encontrar uma parede
                }
                break;
            }
            case ("down"): {
                if (globalGameGrid.map[row + 1][col] != 1) {
                    if (globalGameGrid.map[row + 1][col] == 3) {//se houver um Ghost
                        checkCollisionSelf();
                    }
                    if (globalGameGrid.map[row + 1][col] == 2) {//se houver um PC
                        for (int i = 0; i < globalGameGrid.arrayPC.length - 1; i++) {
                            Computer obj = (Computer) globalGameGrid.arrayPC[i];
                            if (obj != null) {
                                System.out.println("ENCONTREI UM PC");
                                int objRow = obj.getRow();
                                int objCol = obj.getCol();
                                if (objRow == row + 1 && objCol == col) {
                                    if (!obj.isDeleted()) {
                                        obj.deletePC();
                                        bzzt.setFile();
                                        bzzt.start();
                                        globalGameGrid.map[row + 1][col] = 0;
                                        break;
                                    }
                                }
                            }
                        }
                    }
                    pacman.translate(0, globalGameGrid.CELL_SIZE);
                    pacman.load(ResourceHandler.PREFIX+"bugmanDown.png");
                    row++;
                } else {
                    motion = "none"; // Pare se encontrar uma parede
                }
                break;
            }
            case ("right"): {
                if (globalGameGrid.map[row][col + 1] != 1) {
                    if (globalGameGrid.map[row][col + 1] == 3) {//se houver um Ghost
                        checkCollisionSelf();
                    }

                    if (globalGameGrid.map[row][col + 1] == 2) {//se houver um PC
                        for (int i = 0; i < globalGameGrid.arrayPC.length - 1; i++) {
                            Computer obj = (Computer) globalGameGrid.arrayPC[i];
                            if (obj != null) {
                                System.out.println("ENCONTREI UM PC");
                                int objRow = obj.getRow();
                                int objCol = obj.getCol();
                                if (objRow == row && objCol == col + 1) {
                                    if (!obj.isDeleted()) {
                                        obj.deletePC();
                                        bzzt.setFile();
                                        bzzt.start();
                                        globalGameGrid.map[row][col + 1] = 0;
                                        break;
                                    }
                                }
                            }
                        }
                    }
                    pacman.translate(globalGameGrid.CELL_SIZE, 0);
                    pacman.load(ResourceHandler.PREFIX+"bugmanRight.png");
                    col++;
                } else {
                    motion = "none"; // Pare se encontrar uma parede
                }
                break;
            }
            case ("left"): {
                if (globalGameGrid.map[row][col - 1] != 1) {
                    if (globalGameGrid.map[row][col - 1] == 3) {//se houver um Ghost
                        checkCollisionSelf();
                    }
                    if (globalGameGrid.map[row][col - 1] == 2) {//se houver um PC
                        for (int i = 0; i < globalGameGrid.arrayPC.length - 1; i++) {
                            Computer obj = (Computer) globalGameGrid.arrayPC[i];
                            if (obj != null) {
                                System.out.println("ENCONTREI UM PC");
                                int objRow = obj.getRow();
                                int objCol = obj.getCol();
                                if (objRow == row && objCol == col - 1) {
                                    if (!obj.isDeleted()) {
                                        obj.deletePC();
                                        bzzt.setFile();
                                        bzzt.start();
                                        globalGameGrid.map[row][col - 1] = 0;
                                        break;
                                    }
                                }
                            }
                        }
                    }
                    pacman.translate(-globalGameGrid.CELL_SIZE, 0);
                    pacman.load(ResourceHandler.PREFIX+"bugmanLeft.png");
                    col--;
                } else {
                    motion = "none"; // Pare se encontrar uma parede

                }
                break;
            }
            case ("none"): {
                break;
            }
        }
    }


    public int getCol() {
        return this.col;
    }

    public int getRow() {
        return this.row;
    }

    // Métodos para mudar a direção
    public void moveUp() {
        prevMotion = motion;
        if (globalGameGrid.map[row - 1][col] != 1) {
            motion = "up";
        } else {
            motion = prevMotion;
        }
    }

    public void moveDown() {
        prevMotion = motion;
        if (globalGameGrid.map[row + 1][col] != 1) {
            motion = "down";
        } else {
            motion = prevMotion;
        }
    }

    public void moveRight() {
        prevMotion = motion;
        if (globalGameGrid.map[row][col + 1] != 1) {
            motion = "right";
        } else {
            motion = prevMotion;
        }
    }

    public void moveLeft() {
        prevMotion = motion;
        if (globalGameGrid.map[row][col - 1] != 1) {
            motion = "left";
        } else {
            motion = prevMotion;
        }
    }

    public void checkCollision(Ghost ghost) {
        if (this.row == ghost.row && this.col == ghost.col) {
            Game.triggerGameOver();
        }
    }
    public void checkCollisionSelf() {
        if (this.row == ghost1.row && this.col == ghost1.col) {
            Game.triggerGameOver();
        }
        if (this.row == ghost2.row && this.col == ghost2.col) {
            Game.triggerGameOver();
        }
        if (this.row == ghost2.row && this.col == ghost2.col) {
            Game.triggerGameOver();
        }
    }
}



