import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Computer {
    private Picture sprite;
    private int col;
    private int row;
    private boolean deleted = false;

    public Computer(Grid globalGameGrid, int col, int row, String address) {
        this.col = col;
        this.row = row;
        sprite = new Picture(globalGameGrid.CELL_SIZE * col + globalGameGrid.OFFSET_X, globalGameGrid.CELL_SIZE * row + globalGameGrid.OFFSET_Y, address);
        sprite.draw();
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void deletePC() {
            this.sprite.delete();
            deleted = true;
    }

    public Object getPC() {
        return this;
    }

    public int getCol() {
        return this.col;
    }

    public int getRow() {
        return this.row;
    }



}

