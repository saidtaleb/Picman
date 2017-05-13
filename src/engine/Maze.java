package engine;

import java.awt.Dimension;
import static java.lang.Float.max;
import static java.lang.Float.min;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Class Maze - private class for representing search space as a two-dimensional
 * maze
 */
public class Maze {

    public static short OBSTICLE = -1;
    public static short START_LOC_VALUE = -2;
    public static short GOAL_LOC_VALUE = -3;
    public static short USER = -4;
    public static short BOT = -5;
    public static short Bonus = -6;
    
    private int width = 0;
    private int height = 0;
    
    public Dimension startLoc = new Dimension();
    public Dimension goalLoc = new Dimension();
    
    public Dimension userLoc = new Dimension();
    public Dimension BonusLoc = new Dimension();
    public Dimension BotLoc = new Dimension();
    
    /**
     * The maze (or search space) data is stored as a short integer rather than
     * as a boolean so that bread-first style searches can use the array to
     * store search depth. A value of -1 indicates a barrier in the maze.
     */
    private short[][] maze;

    public Maze(int width, int height) {
        System.out.println("New maze of size " + width + " by " + height);
        this.width = width;
        this.height = height;
        maze = new short[width + 2][height + 2];
        for (int i = 0; i < width + 2; i++) {
            for (int j = 0; j < height + 2; j++) {
                maze[i][j] = 0;
            }
        }
        for (int i = 0; i < height + 2; i++) {
            maze[0][i] = maze[width + 1][i] = OBSTICLE;
        }
        for (int i = 0; i < width + 2; i++) {
            maze[i][0] = maze[i][height + 1] = OBSTICLE;
        }
        /**
         * Randomize the maze by putting up arbitray obsticals
         */
        java.util.Random rnd = new java.util.Random();
        rnd.setSeed((long) 4);
        int max_obsticles = (width * height) * 3 / 10;
        for (int i = 0; i < max_obsticles; i++) {
            int x = rnd.nextInt(width);
            int y = rnd.nextInt(height);
            setValue(x, y, OBSTICLE);
        }

        /**
         * Specify the starting location
         */
        startLoc.width = 0;
        startLoc.height = 0;
        setValue(0, 0, (short) 0);
        /**
         * Specify the goal location
         */
        goalLoc.width = width - 1;
        goalLoc.height = height - 1;
        setValue(width - 1, height - 1, GOAL_LOC_VALUE);
        
        /*
        * User Location
        */
        userLoc.width = 0;
        userLoc.height = 0;
        setValue(0, 0, USER);

        BotLoc.width = width - 1;
        BotLoc.height = 0;
        setValue(BotLoc.width, BotLoc.height, BOT);
        
        generateBonus();
        
        
        
        
    }
    
    public void generateBonus(){
        
        int i;
        int j;
        
        do{
            i =  ThreadLocalRandom.current().nextInt(0, width + 1);
            j = ThreadLocalRandom.current().nextInt(0, height + 1);
        }while( getValue(i, j) != 0);
        
        BonusLoc.width = i;
        BonusLoc.height = j;
        setValue(BonusLoc.width,BonusLoc.height, Bonus);
        
    }
    
    synchronized public short getValue(int x, int y) {
        return maze[x + 1][y + 1];
    }

    synchronized public void setValue(int x, int y, short value) {
        maze[x + 1][y + 1] = value;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
    
    public Dimension getCurrentLocation(short player){
        
        Dimension object = null;
        
        switch(player){
            case -4 : 
                object =  userLoc;
            break;
            case -5 : 
                object =  BotLoc;
            break;
            default:
                System.out.println("PLAYER NOT FOUND");
        }
        
        
        return object;
        
    }
    
    public void setCurrentLocation(short player,Dimension Location){
        switch(player){
            case -4 : 
                this.userLoc =  Location;
            break;
            default:
                System.out.println("PLAYER NOT FOUND");
        }
        
    }
    
}
