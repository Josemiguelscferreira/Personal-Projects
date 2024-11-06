import org.academiadecodigo.simplegraphics.pictures.Picture;

public class BugMan {
    private static final int LIFE = 3;
    private static boolean isDead;
    private static int position_X;
    private static int position_Y;
    private final Grid globalGameGrid;
    public int col = 13;
    public int row = 7;
    private Picture bugman;
    static BzztSFX bzzt = new BzztSFX();

    public Ghost ghost1;
    public Ghost ghost2;
    public Ghost ghost3;

    // Movement direction
    public String motion = "none";
    public String prevMotion = motion;



    public BugMan(Grid globalGameGrid) {
        this.bugman = new Picture(globalGameGrid.CELL_SIZE * col + globalGameGrid.OFFSET_X, globalGameGrid.CELL_SIZE * row + globalGameGrid.OFFSET_Y, ResourceHandler.PREFIX+"bugmanRight.png");
        bugman.draw();
        this.globalGameGrid = globalGameGrid;
    }

    public void move() {
        globalGameGrid.pcCheck();
        switch (motion) {
            case ("up"): {
                if (globalGameGrid.map[row - 1][col] != 1) {
                    if (globalGameGrid.map[row - 1][col] == 3) {
                        checkCollisionSelf();
                    }
                    if (globalGameGrid.map[row - 1][col] == 2) {
                        for (int i = 0; i < globalGameGrid.arrayPC.length - 1; i++) {
                            Computer obj = (Computer) globalGameGrid.arrayPC[i];
                            if (obj != null) {
                                System.out.println(" I FOUND A PC!");
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
                    bugman.translate(0, -globalGameGrid.CELL_SIZE);
                    bugman.load(ResourceHandler.PREFIX+"bugmanUp.png");
                    row--;
                } else {
                    motion = "none"; // If it hits a wall
                }
                break;
            }
            case ("down"): {
                if (globalGameGrid.map[row + 1][col] != 1) {
                    if (globalGameGrid.map[row + 1][col] == 3) {//if there is a Ghost
                        checkCollisionSelf();
                    }
                    if (globalGameGrid.map[row + 1][col] == 2) {//if there is a PC
                        for (int i = 0; i < globalGameGrid.arrayPC.length - 1; i++) {
                            Computer obj = (Computer) globalGameGrid.arrayPC[i];
                            if (obj != null) {
                                System.out.println("I FOUND A PC");
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
                    bugman.translate(0, globalGameGrid.CELL_SIZE);
                    bugman.load(ResourceHandler.PREFIX+"bugmanDown.png");
                    row++;
                } else {
                    motion = "none"; // Stop if hits a wall
                }
                break;
            }
            case ("right"): {
                if (globalGameGrid.map[row][col + 1] != 1) {
                    if (globalGameGrid.map[row][col + 1] == 3) {//If there is a Ghost
                        checkCollisionSelf();
                    }

                    if (globalGameGrid.map[row][col + 1] == 2) {//if there is a PC
                        for (int i = 0; i < globalGameGrid.arrayPC.length - 1; i++) {
                            Computer obj = (Computer) globalGameGrid.arrayPC[i];
                            if (obj != null) {
                                System.out.println("I FOUND A PC");
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
                    bugman.translate(globalGameGrid.CELL_SIZE, 0);
                    bugman.load(ResourceHandler.PREFIX+"bugmanRight.png");
                    col++;
                } else {
                    motion = "none"; // Stop if it finds a wall
                }
                break;
            }
            case ("left"): {
                if (globalGameGrid.map[row][col - 1] != 1) {
                    if (globalGameGrid.map[row][col - 1] == 3) {//if there is a Ghost
                        checkCollisionSelf();
                    }
                    if (globalGameGrid.map[row][col - 1] == 2) {//if there is a PC
                        for (int i = 0; i < globalGameGrid.arrayPC.length - 1; i++) {
                            Computer obj = (Computer) globalGameGrid.arrayPC[i];
                            if (obj != null) {
                                System.out.println("I FOUND A PC");
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
                    bugman.translate(-globalGameGrid.CELL_SIZE, 0);
                    bugman.load(ResourceHandler.PREFIX+"bugmanLeft.png");
                    col--;
                } else {
                    motion = "none"; //If it his a wall

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

    // Change direction
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



