package engine;

/**
 * Title: AbstractSearchEngine<p>
 * Description: Abstract search engine for searching paths in a maze<p>
 * Copyright: Copyright (c) Mark Watson, Released under Open Source Artistic
 * License<p>
 * Company: Mark Watson Associates<p>
 * @author Mark Watson
 * @version 1.0
 */
import java.awt.Dimension;
import javax.swing.JOptionPane;

public class AbstractSearchEngine {

    public AbstractSearchEngine(int width, int height) {
        maze = new Maze(width, height);
        initSearch();
    }

    public AbstractSearchEngine(int width, int height, Dimension start, Maze maze) {
        this.maze = maze;
        if (searchPath == null) {
            searchPath = new Dimension[1000];
            for (int i = 0; i < 1000; i++) {
                searchPath[i] = new Dimension();
            }
        }
        pathCount = 0;
        startLoc = start;
        currentLoc = startLoc;
        goalLoc = maze.BonusLoc;
        searchPath[pathCount++] = currentLoc;
    }

    public Maze getMaze() {
        return maze;
    }
    protected Maze maze;
    /**
     * We will use the Java type Dimension (fields width and height will encode
     * the coordinates in x and y directions) for the search path:
     */
    protected Dimension[] searchPath = null;
    protected int pathCount;
    protected int maxDepth;
    protected Dimension startLoc, goalLoc, currentLoc;
    protected boolean isSearching = true;

    protected void initSearch() {
        if (searchPath == null) {
            searchPath = new Dimension[1000];
            for (int i = 0; i < 1000; i++) {
                searchPath[i] = new Dimension();
            }
        }
        pathCount = 0;
        startLoc = maze.BotLoc;
        currentLoc = startLoc;
        goalLoc = maze.BonusLoc;
        searchPath[pathCount++] = currentLoc;
    }

    protected boolean equals(Dimension d1, Dimension d2) {
        return d1.getWidth() == d2.getWidth() && d1.getHeight() == d2.getHeight();
    }

    public Dimension[] getPath() {
        Dimension[] ret = new Dimension[maxDepth];
        for (int i = 0; i < maxDepth; i++) {
            ret[i] = searchPath[i];
        }
        return ret;
    }

    protected Dimension[] getPossibleMoves(Dimension loc) {
        Dimension tempMoves[] = new Dimension[4];
        tempMoves[0] = tempMoves[1] = tempMoves[2] = tempMoves[3] = null;
        int x = loc.width;
        int y = loc.height;
        int num = 0;
        if (maze.getValue(x - 1, y) == 0 || maze.getValue(x - 1, y) == Maze.Bonus || maze.getValue(x - 1, y) == Maze.USER ) {
            tempMoves[num++] = new Dimension(x - 1, y);
        }
        if (maze.getValue(x + 1, y) == 0 || maze.getValue(x + 1, y) == Maze.Bonus || maze.getValue(x + 1, y) == Maze.USER ) {
            tempMoves[num++] = new Dimension(x + 1, y);
        }
        if (maze.getValue(x, y - 1) == 0 || maze.getValue(x, y - 1) == Maze.Bonus || maze.getValue(x, y - 1) == Maze.USER ) {
            tempMoves[num++] = new Dimension(x, y - 1);
        }
        if (maze.getValue(x, y + 1) == 0 || maze.getValue(x, y + 1) == Maze.Bonus || maze.getValue(x, y + 1) == Maze.USER ) {
            tempMoves[num++] = new Dimension(x, y + 1);
        }
        return tempMoves;
    }

    public int up(short player) {

        Dimension playerLocation = maze.getCurrentLocation(player);

        if (maze.getValue(playerLocation.width, playerLocation.height - 1) == 0) {

            maze.setValue(playerLocation.width, playerLocation.height - 1, player);

            maze.setValue(playerLocation.width, playerLocation.height, (short) 0);

            maze.setCurrentLocation(Maze.USER, new Dimension(playerLocation.width, playerLocation.height - 1));

            return 0;

        } else if (maze.getValue(playerLocation.width, playerLocation.height - 1) == Maze.Bonus) {

            maze.setValue(playerLocation.width, playerLocation.height - 1, player);

            maze.setValue(playerLocation.width, playerLocation.height, (short) 0);

            maze.setCurrentLocation(Maze.USER, new Dimension(playerLocation.width, playerLocation.height - 1));

            maze.generateBonus();

            return 1;

        }else if ( maze.getValue(playerLocation.width, playerLocation.height - 1) == Maze.BOT ){
            JOptionPane.showMessageDialog(null, "Your dead");
            return 2;
        }

        return -1;

    }

    public int down(short player) {

        Dimension playerLocation = maze.getCurrentLocation(player);

        if (maze.getValue(playerLocation.width, playerLocation.height + 1) == 0) {

            maze.setValue(playerLocation.width, playerLocation.height + 1, player);

            maze.setValue(playerLocation.width, playerLocation.height, (short) 0);

            maze.setCurrentLocation(Maze.USER, new Dimension(playerLocation.width, playerLocation.height + 1));

            return 0;

        } else if (maze.getValue(playerLocation.width, playerLocation.height + 1) == Maze.Bonus) {

            maze.setValue(playerLocation.width, playerLocation.height + 1, player);

            maze.setValue(playerLocation.width, playerLocation.height, (short) 0);

            maze.setCurrentLocation(Maze.USER, new Dimension(playerLocation.width, playerLocation.height + 1));

            maze.generateBonus();

            return 1;

        }else if ( maze.getValue(playerLocation.width, playerLocation.height + 1) == Maze.BOT ){
            JOptionPane.showMessageDialog(null, "Your dead");
            return 2;
        }

        return -1;

    }

    public int left(short player) {

        Dimension playerLocation = maze.getCurrentLocation(player);

        if (maze.getValue(playerLocation.width - 1, playerLocation.height) == 0) {

            maze.setValue(playerLocation.width - 1, playerLocation.height, player);

            maze.setValue(playerLocation.width, playerLocation.height, (short) 0);

            maze.setCurrentLocation(Maze.USER, new Dimension(playerLocation.width - 1, playerLocation.height));

            return 0;

        } else if (maze.getValue(playerLocation.width - 1, playerLocation.height) == Maze.Bonus) {

            maze.setValue(playerLocation.width - 1, playerLocation.height, player);

            maze.setValue(playerLocation.width, playerLocation.height, (short) 0);

            maze.setCurrentLocation(Maze.USER, new Dimension(playerLocation.width - 1, playerLocation.height));

            maze.generateBonus();

            return 1;

        }else if ( maze.getValue(playerLocation.width - 1, playerLocation.height) == Maze.BOT ){
            JOptionPane.showMessageDialog(null, "Your dead");
            return 2;
        }

        return -1;

    }

    public int right(short player) {

        Dimension playerLocation = maze.getCurrentLocation(player);

        if (maze.getValue(playerLocation.width + 1, playerLocation.height) == 0) {

            maze.setValue(playerLocation.width + 1, playerLocation.height, player);

            maze.setValue(playerLocation.width, playerLocation.height, (short) 0);

            maze.setCurrentLocation(Maze.USER, new Dimension(playerLocation.width + 1, playerLocation.height));

            return 0;

        } else if (maze.getValue(playerLocation.width + 1, playerLocation.height) == Maze.Bonus) {

            maze.setValue(playerLocation.width + 1, playerLocation.height, player);

            maze.setValue(playerLocation.width, playerLocation.height, (short) 0);

            maze.setCurrentLocation(Maze.USER, new Dimension(playerLocation.width + 1, playerLocation.height));

            maze.generateBonus();

            return 1;

        }else if  (maze.getValue(playerLocation.width + 1, playerLocation.height) == Maze.BOT ){
            JOptionPane.showMessageDialog(null, "Your dead");
            return 2;
        }

        return -1;

    }

    public void setValue(int i, int j, short value) {
        maze.setValue(i, j, value);
    }

    public int MoveBot(int i, int j) {

        if ((new Dimension(i, j)).equals(maze.BonusLoc)) {
            maze.generateBonus();
            maze.setValue(maze.BotLoc.width, maze.BotLoc.height, (short) 0);

            maze.setValue(i, j, Maze.BOT);

            maze.BotLoc.width = i;
            maze.BotLoc.height = j;

            return 1;
        } else if( (new Dimension(i, j)).equals(maze.userLoc) ){
            
            JOptionPane.showMessageDialog(null, "Your dead");
            
            return 2;
            
        }else {
            maze.setValue(maze.BotLoc.width, maze.BotLoc.height, (short) 0);

            maze.setValue(i, j, Maze.BOT);

            maze.BotLoc.width = i;
            maze.BotLoc.height = j;

            return 0;
        }

    }

}
