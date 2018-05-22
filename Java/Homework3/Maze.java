import java.util.ArrayList;

/**
 * Class that solves maze problems with backtracking.
 * @author Koffman and Wolfgang
 **/
public class Maze implements GridColors {

    /** The maze */
    private TwoDimGrid maze;
    public Maze(TwoDimGrid m) { maze = m;
    }
    /** Wrapper method. */
    public boolean findMazePath() {
        return findMazePath(0, 0); // (0, 0) is the start point.
    }
    /**
     * Attempts to find a path through point (x, y).
     * @pre Possible path cells are in BACKGROUND color;
     *      barrier cells are in ABNORMAL color.
     * @post If a path is found, all cells on it are set to the
     *       PATH color; all cells that were visited but are
     *       not on the path are in the TEMPORARY color.
     * @param x The x-coordinate of current point
     * @param y The y-coordinate of current point
     * @return If a path through (x, y) is found, true;
     *         otherwise, false
     */
    public boolean findMazePath(int x, int y) {
        if(x<0 || y<0 || x >= maze.getNCols() || y >= maze.getNRows())
            return false;
        else if(!maze.getColor(x, y).equals(GridColors.NON_BACKGROUND)){
            return false;
        } else if(x == maze.getNRows() - 1 && y == maze.getNCols() - 1){
            maze.recolor(x, y, GridColors.PATH);
            return true;
        } else
        {
            maze.recolor(x, y, GridColors.PATH );

            boolean right = findMazePath(x + 1,y);
            boolean left = findMazePath(x - 1,y);
            boolean up = findMazePath(x,y + 1);
            boolean down = findMazePath(x,y - 1);

            if(right || left || up || down)
                return true;
            else
            {
                maze.recolor(x, y, GridColors.TEMPORARY);
                return false;
            }
        }
    }
    // ADD METHOD FOR PROBLEM 2 HERE
    public ArrayList<ArrayList<PairInt>> findAllMazePaths() {return findAllMazePaths(0, 0); }
    public ArrayList<ArrayList<PairInt>> findAllMazePaths(int x,int y) {return findAllMazePaths(x, y, new ArrayList<>() );}
    public ArrayList<ArrayList<PairInt>> findAllMazePaths(int x,int y, ArrayList<PairInt> curr) {
        if(x < 0 || y < 0 || x >= maze.getNCols() || y >= maze.getNRows() || maze.getColor(x, y) != GridColors.NON_BACKGROUND )
            return new ArrayList<>();
        else if(x == maze.getNRows() - 1 && y == maze.getNCols() - 1) {
            curr.add( new PairInt(x, y) );
            ArrayList<ArrayList<PairInt>> output = new ArrayList<>();
            output.add(curr);
            return output;
        } else {
            ArrayList<PairInt> newCurr = (ArrayList<PairInt>) curr.clone();
            newCurr.add(new PairInt( x, y ));

            maze.recolor(x, y, GridColors.PATH);

            ArrayList<ArrayList<PairInt>> right = findAllMazePaths(x + 1,y, newCurr);
            ArrayList<ArrayList<PairInt>> left = findAllMazePaths(x - 1,y, newCurr);
            ArrayList<ArrayList<PairInt>> up = findAllMazePaths(x,y + 1, newCurr);
            ArrayList<ArrayList<PairInt>> down = findAllMazePaths(x,y - 1, newCurr);

            maze.recolor(x,y,GridColors.NON_BACKGROUND);

            ArrayList<ArrayList<PairInt>> output = new ArrayList<>();
            output.addAll(up);
            output.addAll(down);
            output.addAll(left);
            output.addAll(right);

            return output;
        }
    }
    // ADD METHOD FOR PROBLEM 3 HERE
    public ArrayList<PairInt> findMazePathMin() {return findMazePathMin(0, 0);}
    public ArrayList<PairInt> findMazePathMin(int x, int y) {
        ArrayList<ArrayList<PairInt>> paths = findAllMazePaths(x,y);
        ArrayList<PairInt> shortestPath = new ArrayList<>();
        int smallest = 100;

        for(ArrayList<PairInt> L : paths) {
            if(L.size() < smallest) {
                shortestPath = L;
                smallest = L.size();
            }
        }
        return shortestPath;
    }

    /*<exercise chapter="5" section="6" type="programming" number="2">*/
    public void resetTemp() {
        maze.recolor(TEMPORARY, BACKGROUND);
    }
    /*</exercise>*/

    /*<exercise chapter="5" section="6" type="programming" number="3">*/
    public void restore() {
        resetTemp();
        maze.recolor(PATH, BACKGROUND);
        maze.recolor(NON_BACKGROUND, BACKGROUND);
    }
    /*</exercise>*/
}
/*</listing>*/
