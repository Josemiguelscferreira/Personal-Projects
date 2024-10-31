import org.academiadecodigo.simplegraphics.pictures.Picture;

public abstract class Ghost {
    protected Grid globalGameGrid;
    protected Picture ghost;
    protected String sprite;

    protected int col;
    protected int row;
    protected int prevCol;
    protected int prevRow;
    protected boolean outOfBox = false;
    protected int prevGridValue = 0;
    protected int prevNextGridValue;

    protected int direction = 3;
    protected int prevDirection = direction;
    protected int targetCol;
    protected int targetRow;
    protected BugMan target;
    protected int chaseModeCooldown = 2000; //milliseconds

    //NOVO
    public abstract void getOutOfBox();

    public Ghost(String sprite, int col, int row, Grid globalGameGrid, BugMan target) {
        this.sprite = sprite;
        this.col = col;
        this.row = row;
        this.globalGameGrid = globalGameGrid;
        this.target = target;
        ghost = new Picture(globalGameGrid.CELL_SIZE * col + globalGameGrid.OFFSET_X, globalGameGrid.CELL_SIZE * row + globalGameGrid.OFFSET_Y, sprite);
        ghost.draw();
    }

    public void randomMove() {
        if (outOfBox) {
            if (this.row == target.getRow() && this.col == target.getCol()) {
                target.checkCollision(this); // Verifica a colisão
            }
            //LETS CHASE THE BUG
            targetCol = target.getCol();
            targetRow = target.getRow();
            chaseModeCooldown--;
            if (targetRow == this.row && targetCol == this.col) {
            }
            if (targetRow != this.row) { //no need to change if we are on the same row
                if (targetRow < this.row) { //IS HE ABOVE?
                    if (globalGameGrid.map[this.row - 1][this.col] != 1 && globalGameGrid.map[this.row - 1][this.col] != 3) { //CAN WE MOVE?
                        this.direction = 0; //MoveUp
                    }
                } else {//IS HE BELOW?
                    if (globalGameGrid.map[this.row + 1][this.col] != 1 && globalGameGrid.map[this.row + 1][this.col] != 3) { //CAN WE MOVE?
                        direction = 1; //MoveDown
                    }
                }
            }
            if (targetRow != this.col) { //no need to change if we are on the same col
                {
                    if (targetCol > this.col) { //IS HE TO THE RIGHT?
                        if (globalGameGrid.map[this.row][this.col + 1] != 1 && globalGameGrid.map[this.row][this.col + 1] != 3) { //CAN WE MOVE?
                            direction = 3; //MoveRight, yes, it's 3
                        }
                    } else if (targetCol < this.col) { //IS HE TO THE LEFT?
                        if (globalGameGrid.map[this.row][this.col - 1] != 1 && globalGameGrid.map[this.row][this.col - 1] != 3) { //CAN WE MOVE?
                            direction = 2; //MoveLeft
                        }
                    }
                }
            }

            if (chaseModeCooldown == 0) { //dont get stuck
                if (((globalGameGrid.map[this.row - 1][this.col] != 1) && (globalGameGrid.map[this.row - 1][this.col] != 3))
                        || ((globalGameGrid.map[this.row + 1][this.col] != 1) && (globalGameGrid.map[this.row + 1][this.col] != 3))
                        || ((globalGameGrid.map[this.row][this.col - 1] != 1) && (globalGameGrid.map[this.row][this.col - 1] != 3))
                        || ((globalGameGrid.map[this.row][this.col + 1] != 1) && (globalGameGrid.map[this.row][this.col + 1] != 3))) {
                    prevDirection = direction;
                    while (direction == prevDirection) {
                        direction = (int) (Math.floor(Math.random() * 4));
                    }
                }else {
                    direction = 4;
                }
                chaseModeCooldown = 2000;//reset timer
            }


            switch (direction) {
                case 0: // MoveUp
                {
                    if (globalGameGrid.map[this.row - 1][this.col] != 1 && globalGameGrid.map[this.row - 1][this.col] != 3) { //can't go over walls or other ghosts
                        globalGameGrid.map[this.row][this.col] = prevGridValue; // put the grid value back
                        globalGameGrid.map[this.prevRow][this.prevCol] = prevNextGridValue;
                        ghost.translate(0, -globalGameGrid.CELL_SIZE);
                        ghost.load(sprite);
                        if (globalGameGrid.map[this.row - 1][this.col] != 3) { //pls fix bug
                            prevGridValue = globalGameGrid.map[this.row - 1][this.col];//save the value to put it back next move
                        }
                        if (this.row - 1 == targetRow && this.col == targetCol) {
                            Game.triggerGameOver();
                        }
                        prevNextGridValue = globalGameGrid.map[this.row-1][this.col];
                        prevRow = this.row;
                        prevCol = this.col;
                        globalGameGrid.map[this.row-1][this.col] = 3;
                        this.row--;
                        globalGameGrid.map[this.row][this.col] = 3;//set the cell to be a kill zone
                        break;
                    } else {
                        while (direction == 0) {
                            direction = (int) (Math.floor(Math.random() * 4));
                        }
                        break;
                    }
                }
                case 1: //MoveDown
                {
                    if (globalGameGrid.map[this.row + 1][this.col] != 1 && globalGameGrid.map[this.row + 1][this.col] != 3) {
                        globalGameGrid.map[this.row][this.col] = prevGridValue; // put the grid value back
                        ghost.translate(0, globalGameGrid.CELL_SIZE);
                        ghost.load(sprite);
                        if (globalGameGrid.map[this.row + 1][this.col] != 3) {
                            prevGridValue = globalGameGrid.map[this.row + 1][this.col];//save the value to put it back next move
                        }
                        if (this.row + 1 == targetRow && this.col == targetCol) {
                            Game.triggerGameOver();
                        }
                        prevNextGridValue = globalGameGrid.map[this.row+1][this.col];
                        prevRow = this.row;
                        prevCol = this.col;
                        globalGameGrid.map[this.row+1][this.col] = 3;
                        this.row++;
                        globalGameGrid.map[this.row][this.col] = 3;//set the cell to be a kill zone
                        break;
                    } else {
                        while (direction == 1) {
                            direction = (int) (Math.floor(Math.random() * 4));
                        }
                        break;
                    }
                }
                case 2: //MoveLeft
                {
                    if (globalGameGrid.map[this.row][this.col - 1] != 1 && globalGameGrid.map[this.row][this.col - 1] != 3) {
                        globalGameGrid.map[this.row][this.col] = prevGridValue; // put the grid value back
                        ghost.translate(-globalGameGrid.CELL_SIZE, 0);
                        ghost.load(sprite);
                        if (globalGameGrid.map[this.row][this.col - 1] != 3) {
                            prevGridValue = globalGameGrid.map[this.row][this.col - 1];//save the value to put it back next move
                        }
                        if (this.row == targetRow && this.col - 1 == targetCol) {
                            Game.triggerGameOver();
                        }
                        prevNextGridValue = globalGameGrid.map[this.row][this.col-1];
                        prevRow = this.row;
                        prevCol = this.col;
                        globalGameGrid.map[this.row][this.col-1] = 3;
                        this.col--;
                        globalGameGrid.map[this.row][this.col] = 3;//set the cell to be a kill zone
                        break;
                    } else {
                        while (direction == 2) {
                            direction = (int) (Math.floor(Math.random() * 4));
                        }
                        break;
                    }
                }
                case 3: //MoveRight
                {
                    if (globalGameGrid.map[this.row][this.col + 1] != 1 && globalGameGrid.map[this.row][this.col + 1] != 3) {
                        globalGameGrid.map[this.row][this.col] = prevGridValue; // put the grid value back
                        ghost.translate(globalGameGrid.CELL_SIZE, 0);
                        ghost.load(sprite);
                        if (globalGameGrid.map[this.row][this.col + 1] != 3) {
                            prevGridValue = globalGameGrid.map[this.row][this.col + 1];//save the value to put it back next move
                        }
                        if (this.row == targetRow && this.col + 1 == targetCol) {
                            Game.triggerGameOver();
                        }
                        prevNextGridValue = globalGameGrid.map[this.row][this.col+1];
                        prevRow = this.row;
                        prevCol = this.col;
                        globalGameGrid.map[this.row][this.col+1] = 3;
                        this.col++;
                        globalGameGrid.map[this.row][this.col] = 3;//set the cell to be a kill zone
                        break;
                    } else {
                        while (direction == 3) {
                            direction = (int) (Math.floor(Math.random() * 4));
                        }
                        break;
                    }
                }
                case 4:{ //CANT MOVE
                    direction = (int) (Math.floor(Math.random() * 4));
                    break;
                }
            }
        } else {
            getOutOfBox();
        }
    }

    public int getCol(){
        return this.col;
    }
    public int getRow(){
        return this.row;
    }
}
