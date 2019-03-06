/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package breakingBad;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.LinkedList;

/**
 *
 * @author antoniomejorado
 */
public class Game implements Runnable {
    private BufferStrategy bs;                          // to have several buffers when displaying
    private Graphics g;                                    // to paint objects
    private Display display;                             // to display in the game
    String title;                                                 // title of the window
    private int width;                                        // width of the window
    private int height;                                       // height of the window
    private Thread thread;                              // thread to create the game
    private boolean running;                           // to set the game
    private Player player;                                // to use a player
    private KeyManager keyManager;            // to manage the keyboard
    private LinkedList<Enemy> bricks;                              // to move an enemy
    private int lives;                                         // to count the remaining lives of the player
    private boolean endGame;                       // to know when to end the game
    private Ball ball;                      //to use a ball
    private LinkedList<PowerUps> powerUps;
    private LinkedList <PowerUps> pollos;
    private WriteFile wfile;
    private ReadFile rfile;
    private int score;
    private boolean pauseGame;
    
    /**
     * to create title, width and height and set the game is still not running
     * @param title to set the title of the window
     * @param width to set the width of the window
     * @param height  to set the height of the window
     */
    public Game(String title, int width, int height) {
        this.title = title;
        this.width = width;
        this.height = height;
        running = false;
        keyManager = new KeyManager();
        //mouseManager = new MouseManager();
        bricks = new LinkedList<Enemy>();
        wfile = new WriteFile(this);
        rfile = new ReadFile(this);
        powerUps = new LinkedList<PowerUps>();
        pollos = new LinkedList<PowerUps>();
    }

    /**
     * To get the width of the game window
     * @return an <code>int</code> value with the width
     */
    public int getWidth() {
        return width;
    }

    /**
     * To get the height of the game window
     * @return an <code>int</code> value with the height
     */
    public int getHeight() {
        return height;
    }
    
    /**
     * 
     * @return 
     */
    public LinkedList<Enemy> getBricks() {
        return bricks;
    }
    
    /**
     * 
     * @return 
     */
    public Player getPlayer() {
        return player;
    }
    
    /**
     * 
     * @return 
     */
    public Ball getBall() {
        return ball;
    }
    
    /**
     * 
     * @return 
     */
    public LinkedList<PowerUps> getPowerUps() {
        return powerUps;
    }
    
    
    /**
     * 
     * @return 
     */
    public int getLives() {
        return lives;
    }
    
    /**
     * 
     * @return 
     */
    public boolean isEndGame() {
        return endGame;
    }
    
    /**
     * 
     * @return 
     */
    public int getScore() {
        return score;
    }
    
    /**
     * 
     * @param endGame 
     */
    public void setEndGame(boolean endGame) {
        this.endGame = endGame;
    }
    
    /**
     * 
     * @param lives 
     */
    public void setLives(int lives) {
        this.lives = lives;
    }
    
    /**
     * 
     * @param score 
     */
    public void setScore(int score) {
        this.score = score;
    }

    public LinkedList<PowerUps> getPollos() {
        return pollos;
    }
    
    
    /**
     * initializing the display window of the game
     */
    private void init() {
         display = new Display(title, getWidth(), getHeight());
         Assets.init();
         player = new Player(getWidth() / 2, getHeight() -100, 1, 100, 100, this);
         ball = new Ball(player.getX()-50, player.getY()-100, 100, 100, this);
         int iPosX = 0;
         int iPosY = 0;
         for (int i = 1; i <= 30; i++) {
            //create bricks in a row
            bricks.add(new Enemy(iPosX, iPosY, 100, 100, this));
            iPosX +=80;
            
            // create 10 bricks every row
            if(i % 10 == 0 ){
                iPosY +=30;
                iPosX = 0;
            }
            
        }
        for(int i = 1; i <= 3; i++){
            //creating flasks in a row
            powerUps.add(new PowerUps(iPosX, iPosY, 100, 100, 0, 1, this));
            iPosX += 350;
            iPosY = 100;           
        } 
        for(int i = 1;  i <= 2; i++){
            pollos.add(new PowerUps(iPosX, iPosY, 100, 100, 0 ,2, this));
            iPosX += 50;
            iPosY = 80;
        } 
         lives = 3;
         endGame = false;
         display.getJframe().addKeyListener(keyManager);
         pauseGame = false;
    }

    @Override
    public void run() {
        init();
        // frames per second
        int fps = 50;
        // time for each tick in nano segs
        double timeTick = 1000000000 / fps;
        // initializing delta
        double delta = 0;
        // define now to use inside the loop
        long now;
        // initializing last time to the computer time in nanosecs
        long lastTime = System.nanoTime();
        while (running) {
            // setting the time now to the actual time
            now = System.nanoTime();
            // acumulating to delta the difference between times in timeTick units
            delta += (now - lastTime) / timeTick;
            // updating the last time
            lastTime = now;

            // if delta is positive we tick the game
            if (delta >= 1) {
                tick();
                render();
                delta --;
            }
        }
        stop();
    }

    public KeyManager getKeyManager() {
        return keyManager;
    }

    private void tick() {
        keyManager.tick();
        if (getKeyManager().pause) {
            pauseGame = !pauseGame;
        }
        if (getKeyManager().restart) {
            init();
        }
        // avancing player with colision
        if (!endGame && !pauseGame) {
            
            if (getKeyManager().moveBall && !ball.isMoving()) {
                ball.setMoving(true);
                ball.setSpeed(2);
                ball.setDirectionY(-1);
            }
            player.tick();
            ball.tick();
            
            //ticking all powerups
            for(int i = 0; i < powerUps.size(); i++){
                PowerUps powerUp = powerUps.get(i);
                powerUp.tick();
            }
            //icking all pollos
            for(int i = 0; i < pollos.size(); i++){
                PowerUps pollo = pollos.get(i);
                pollo.tick();
            }
            //ticking all bricks
            for (int i = 0; i < bricks.size(); i++) {
                Enemy brick =  bricks.get(i);
                if (ball.intersectaBloque(brick)) {
                    if (ball.getX() + ball.getSpeed() * ball.getDirectionX() <= brick.getX() + brick.getWidth()) {
                        ball.setDirectionX(1);
                    } else if (ball.getX() + ball.getWidth() + ball.getSpeed() * ball.getDirectionX()  >= brick.getX()) {
                        ball.setDirectionX(-1);
                    } else if (ball.getY() > brick.getY()) {
                        ball.setDirectionY(-1);
                    } else {
                        ball.setDirectionY(1);
                    }
                }
            }
            if (ball.getY() + ball.getHeight() >= getHeight()) {
//                lives--;
//                if(lives == 0) {
//                    endGame = true;
//                } else {
                    ball.setX(player.getX() - ball.getWidth() / 2);
                    ball.setY(player.getY() - ball.getHeight());
                    ball.setMoving(false);
//                }
            }
        }
    }

    private void render() {
        // get the buffer strategy from the display
        bs = display.getCanvas().getBufferStrategy();
        /* if it is null, we define one with 3 buffers to display images of
        the game, if not null, then we display every image of the game but
        after clearing the Rectanlge, getting the graphic object from the
        buffer strategy element.
        show the graphic and dispose it to the trash system
        */
        if (bs == null) {
            display.getCanvas().createBufferStrategy(3);
        }
        else
        {
            g = bs.getDrawGraphics();
            g.drawImage(Assets.background, 0, 0, width, height, null);
            if(endGame) {
                //g.drawImage(Assets.endGame, getWidth() / 2 - 131, getHeight() / 2 - 30, 262, 60, null);
            } else {
                player.render(g);
                ball.render(g);
                
                //rendering all bricks
                for (int i = 0; i < bricks.size(); i++) {
                Enemy brick = bricks.get(i);
                brick.render(g);
            }
                //rendering powerups
                for(int i = 0; i < powerUps.size(); i++){
                    PowerUps powerUp = powerUps.get(i);
                    powerUp.render(g);
                }
                //rendering all pollos
                for(int i = 0; i < pollos.size(); i++){
                    PowerUps pollo = pollos.get(i);
                    pollo.render(g);
                }
            }
            bs.show();
            g.dispose();
        }

    }

    /**
     * setting the thread for the game
     */
    public synchronized void start() {
        if (!running) {
            running = true;
            thread = new Thread(this);
            thread.start();
        }
    }

    /**
     * stopping the thread
     */
    public synchronized void stop() {
        if (running) {
            running = false;
            try {
                thread.join();
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }
        }
    }
}
