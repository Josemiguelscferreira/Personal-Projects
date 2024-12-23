public class Leith extends Ghost {

    private int step = 0;

    public Leith(String sprite, int col, int row, Grid globalGameGrid, BugMan target) {
        super(sprite, col, row, globalGameGrid, target);
        target.ghost1 = this;
    }

    @Override
    public void getOutOfBox() {
        System.out.println("I WANT TO BREAK FREE");
        System.out.println((col));
        System.out.println((row));
        switch (step) {
            case 0:
            case 1:
            case 4:
            case 5: {
                ghost.translate(0, globalGameGrid.CELL_SIZE);
                row++;
                step++;
                break;
            }
            case 2:
            case 3:{
                ghost.translate(globalGameGrid.CELL_SIZE, 0);
                col++;
                step++;
                break;
            }
            case 6:{
                ghost.translate(-globalGameGrid.CELL_SIZE, 0);
                col--;
                step++;
                break;
            }
            case 7: {
                ghost.translate(-globalGameGrid.CELL_SIZE, 0);
                col--;
                prevGridValue = globalGameGrid.map[this.row][this.col];
                System.out.println("I AM FREE");
                outOfBox = true;
                break;
            }
        }
    }
}
