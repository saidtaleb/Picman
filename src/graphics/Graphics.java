package graphics;

import engine.DepthFirstSearchEngine;
import engine.Maze;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JPanel;

public class Graphics extends JPanel implements Runnable {

    DepthFirstSearchEngine currentSearchEngine = null;
    ArrayList path;
    int session;
    
    public Graphics(int width, int height) {
        currentSearchEngine = new DepthFirstSearchEngine(width, height);
        path = new ArrayList();
        this.setBackground(Color.white);
        for (int i = currentSearchEngine.getPath().length - 1; i >= 0; i--) {
            path.add(currentSearchEngine.getPath()[i]);
        }
        uiInit();
        repaint();
        
    }

    @Override
    protected void paintComponent(java.awt.Graphics g) {
        if (currentSearchEngine == null) {
            return;
        }
        Maze maze = currentSearchEngine.getMaze();

        int width = maze.getWidth();
        int height = maze.getHeight();
        super.paintComponent(g);
        BufferedImage image = new BufferedImage(320, 320, BufferedImage.TYPE_INT_RGB);

        java.awt.Graphics g2 = image.getGraphics();

        g2.setColor(Color.white);
        g2.fillRect(0, 0, 320, 320);
        g2.setColor(Color.black);

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                short val = maze.getValue(x, y);

                if (val == Maze.OBSTICLE) {
                    g2.setColor(Color.lightGray);
                    g2.fillRect(6 + x * 29, 3 + y * 29, 29, 29);
                    g2.setColor(Color.black);
                    g2.drawRect(6 + x * 29, 3 + y * 29, 29, 29);
                } else if (val == Maze.USER) {
                    g2.setColor(Color.BLUE);
                    g2.fillRect(6 + x * 29, 3 + y * 29, 29, 29);
                    g2.setColor(Color.black);
                    g2.drawRect(6 + x * 29, 3 + y * 29, 29, 29);
                } else if (val == Maze.Bonus) {
                    g2.setColor(Color.YELLOW);
                    g2.fillRect(6 + x * 29, 3 + y * 29, 29, 29);
                    g2.setColor(Color.black);
                    g2.drawRect(6 + x * 29, 3 + y * 29, 29, 29);
                } else if (val == Maze.BOT) {
                    g2.setColor(Color.RED);
                    g2.fillRect(6 + x * 29, 3 + y * 29, 29, 29);
                    g2.setColor(Color.black);
                    g2.drawRect(6 + x * 29, 3 + y * 29, 29, 29);
                } else {
                    g2.setColor(Color.black);
                    g2.drawRect(6 + x * 29, 3 + y * 29, 29, 29);
                }
            }
        }
        // redraw the path in black:
        g2.setColor(Color.black);
        g.drawImage(image, 30, 40, 320, 320, null);
    }

    public void uiInit() {
        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent ke) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void keyPressed(KeyEvent ke) {

                int KeyCode = ke.getKeyCode();

                switch (KeyCode) {
                    case KeyEvent.VK_DOWN:
                        if (currentSearchEngine.down(Maze.USER) == 1) {
                            currentSearchEngine = new DepthFirstSearchEngine(
                                    currentSearchEngine.getMaze().getWidth(),
                                    currentSearchEngine.getMaze().getHeight(),
                                    currentSearchEngine.getMaze().getCurrentLocation(Maze.BOT),
                                    currentSearchEngine.getMaze()
                            );
                            path = new ArrayList();
                            for (int i = currentSearchEngine.getPath().length - 1; i >= 0; i--) {
                                path.add(currentSearchEngine.getPath()[i]);
                            }
                        }
                        repaint();
                        break;
                    case KeyEvent.VK_UP:
                        if (currentSearchEngine.up(Maze.USER) == 1) {
                            currentSearchEngine = new DepthFirstSearchEngine(
                                    currentSearchEngine.getMaze().getWidth(),
                                    currentSearchEngine.getMaze().getHeight(),
                                    currentSearchEngine.getMaze().getCurrentLocation(Maze.BOT),
                                    currentSearchEngine.getMaze()
                            );
                            path = new ArrayList();
                            for (int i = currentSearchEngine.getPath().length - 1; i >= 0; i--) {
                                path.add(currentSearchEngine.getPath()[i]);
                            }
                        }
                        repaint();
                        break;
                    case KeyEvent.VK_LEFT:
                        if (currentSearchEngine.left(Maze.USER) == 1) {
                            currentSearchEngine = new DepthFirstSearchEngine(
                                    currentSearchEngine.getMaze().getWidth(),
                                    currentSearchEngine.getMaze().getHeight(),
                                    currentSearchEngine.getMaze().getCurrentLocation(Maze.BOT),
                                    currentSearchEngine.getMaze()
                            );
                            path = new ArrayList();
                            for (int i = currentSearchEngine.getPath().length - 1; i >= 0; i--) {
                                path.add(currentSearchEngine.getPath()[i]);
                            }
                        }
                        repaint();
                        break;
                    case KeyEvent.VK_RIGHT:
                        //currentSearchEngine.right(Maze.USER);
                        if (currentSearchEngine.right(Maze.USER) == 1) {
                            currentSearchEngine = new DepthFirstSearchEngine(
                                    currentSearchEngine.getMaze().getWidth(),
                                    currentSearchEngine.getMaze().getHeight(),
                                    currentSearchEngine.getMaze().getCurrentLocation(Maze.BOT),
                                    currentSearchEngine.getMaze()
                            );
                            path = new ArrayList();
                            for (int i = currentSearchEngine.getPath().length - 1; i >= 0; i--) {
                                path.add(currentSearchEngine.getPath()[i]);
                            }
                        }
                        repaint();
                        break;
                    case KeyEvent.VK_SPACE:
                        System.out.println("PAUSE");
                        repaint();
                        break;
                    case KeyEvent.VK_ESCAPE:
                        System.exit(0);
                    default:
                        break;
                }

            }

            @Override
            public void keyReleased(KeyEvent ke) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
    }

    @Override
    public void run() {
        while (!path.isEmpty()) {

            try {
                System.out.println(path.get(0));

                int x = ((Dimension) path.get(0)).width;
                int y = ((Dimension) path.get(0)).height;
                path.remove(0);
                repaint();
                session = currentSearchEngine.MoveBot(x, y);
                if (session == 1) {
                    currentSearchEngine = new DepthFirstSearchEngine(
                            currentSearchEngine.getMaze().getWidth(),
                            currentSearchEngine.getMaze().getHeight(),
                            currentSearchEngine.getMaze().getCurrentLocation(Maze.BOT),
                            currentSearchEngine.getMaze()
                    );

                    path = new ArrayList();
                    for (int i = currentSearchEngine.getPath().length - 1; i >= 0; i--) {
                        path.add(currentSearchEngine.getPath()[i]);
                    }

                } else if (session == 2) {

                    currentSearchEngine = new DepthFirstSearchEngine(
                            currentSearchEngine.getMaze().getWidth(),
                            currentSearchEngine.getMaze().getHeight()
                    );

                    path = new ArrayList();
                    for (int i = currentSearchEngine.getPath().length - 1; i >= 0; i--) {
                        path.add(currentSearchEngine.getPath()[i]);
                    }
                }
                Thread.sleep(1000);
            } catch (Exception v) {
                System.out.println(v);
            }

        }
    }
}
