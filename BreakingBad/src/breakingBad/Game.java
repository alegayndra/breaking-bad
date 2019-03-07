/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package breakingBad;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    private int score;
    private boolean pauseGame;
    private int cantBricks;
    private String nombreArchivo;
    
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
        wFile = new WriteFile();
        rFile = new ReadFile();
        powerUps = new LinkedList<PowerUps>();
        pollos = new LinkedList<PowerUps>();
        nombreArchivo = "src/breakingbad/archivo.sf";
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
     * To get the list of all the bricks
     * @return an <code>LinkedList<Enemey></code> list with all the bricks
     */
    public LinkedList<Enemy> getBricks() {
        return bricks;
    }
    
    /**
     * To get the player
     * @return an <code>Player</code> player object
     */
    public Player getPlayer() {
        return player;
    }
    
    /**
     * To get the ball
     * @return an <code>Ball</code> ball object
     */
    public Ball getBall() {
        return ball;
    }
    
    /**
     * To get the list of all the power ups
     * @return an <code>LinkedList<PowerUps></code> list with all the power ups
     */
    public LinkedList<PowerUps> getPowerUps() {
        return powerUps;
    }

    /**
     * To get the quantity of bricks that haven't been destroyed
     * @return an <code>int</code> value with the quantity of bricks that haven't been destroyed
     */
    public int getCantBricks() {
        return cantBricks;
    }
    
    /**
     * To get the remaining lives
     * @return an <code>int</code> value of remaining lives
     */
    public int getLives() {
        return lives;
    }
    
    /**
     * To check if the game has ended
     * @return an <code>boolean</code> value of the state of the game
     */
    public boolean isEndGame() {
        return endGame;
    }
    
    /**
     * To get the current score
     * @return an <code>int</code> value with the score
     */
    public int getScore() {
        return score;
    }
    
    /**
     * To set if the game is ended
     * @param endGame to set the state of the game
     */
    public void setEndGame(boolean endGame) {
        this.endGame = endGame;
    }
    
    /**
     * To set the lives
     * @param lives to set the lives
     */
    public void setLives(int lives) {
        this.lives = lives;
    }
    
    /**
     * To set the score
     * @param score to set the score
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * To get the list of all the power downs
     * @return an <code>LinkedList<PowerUps></code> list of all the power downs
     */
    public LinkedList<PowerUps> getPollos() {
        return pollos;
    }
    
    /**
     * initializing the display window of the game
     */
    private void init() {
        display = new Display(title, getWidth(), getHeight());
        Assets.init();
        player = new Player(getWidth() / 2, getHeight() -100, 1, 100, 30, this);
        ball = new Ball(player.getX() + player.getWidth()/2, player.getY()-player.getHeight(), 50, 50, this);
        int iPosX = 0;
        int iPosY = 0;
        for (int i = 1; i <= 30; i++) {
            //create bricks in a row
            bricks.add(new Enemy(iPosX, iPosY, 100, 40, this));
            iPosX +=80;

            // create 10 bricks every row
            if(i % 10 == 0 ){
                iPosY +=30;
                iPosX = 0;
            }

        }
        //creating second row of bricks
        iPosX = 0;
        iPosY = 250;
        for(int i = 1; i <= 20; i++){
            bricks.add(new Enemy(iPosX, iPosY, 100, 40, this));
            iPosX += 80;
            
            //create 10 bricks every row
            if(i % 10 == 0){
                iPosY += 30;
                iPosX = 0;
            }
        }
        
        iPosY = 100;
        for(int i = 1; i <= 3; i++){
            //creating flasks in a row
            int dirX = (int) (Math.random() * 2-1)+1;
            powerUps.add(new PowerUps(iPosX, iPosY, iPosY, 100, 3, dirX, 1, this));
            iPosX += 300;
            
        } 
        iPosX-=200;
        for(int i = 1;  i <= 2; i++){
            int dirX = (int) (Math.random() * 2-1)+1;
            pollos.add(new PowerUps(iPosX, iPosY, 100, 100, 2, dirX ,2, this));
            //iPosX += 100;
            iPosY += 50;
        } 
        cantBricks = bricks.size();
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
        // ticks key manager
        keyManager.tick();
        
        // ckecks flags for pausing, saving, and loading
        if (getKeyManager().pause) {
            pauseGame = !pauseGame;
        }
        if (getKeyManager().save) {
            try {
                grabarArchivo();
            } catch (IOException ex) {
                Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
            }
        } 
        if (getKeyManager().load) {
            try {
                    leeArchivo();
                } catch (IOException ex) {
                    Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
                }
        }
        
        // checks flag for restarting and if that the game has ended
        if (getKeyManager().restart && endGame) {
            
            // resets the paddle position
            player.setX(getWidth() / 2);
            player.setY(getHeight() - player.getHeight());
            
            // resets the ball position and sets it to follow the paddle
            ball.setX(player.getX() + player.getWidth() / 2);
            ball.setY(player.getY() - player.getHeight());
            ball.setMoving(false);
            
            // sets the initial position for the bricks
            int iPosX = 0;
            int iPosY = 0;
            
            // resets all bricks
            for (int i = 0; i < bricks.size(); i++) {
                System.out.println(i + "\n" + iPosX + " " + iPosY);
               //create bricks in a row
               bricks.get(i).setX(iPosX);
               bricks.get(i).setY(iPosY);
               bricks.get(i).setDestroyed(false);
               iPosX +=80;
               System.out.println("" + iPosX + " " + iPosY);
               // create 10 bricks every row
               if(i % 10 == 0 && i != 0){
                   iPosY +=30;
                   iPosX = 0;
               }
           }
            
            iPosY = 100;
            iPosX = 0;
            
           // resets all flasks
           for(int i = 0; i < 3; i++){
               //creating flasks in a row
               PowerUps flask = powerUps.get(i);
               int dirX = (int) (Math.random() * 2-1)+1;
               flask.setX(iPosX);
               flask.setDirection(dirX);
               iPosX += 300;
               iPosY = 100;           
           } 
           
           iPosX-=200;
           
           // reset all chickens
           for(int i = 0;  i < 2; i++){
               PowerUps pollo = pollos.get(i);
               int dirX = (int) (Math.random() * 2-1)+1;
               pollo.setX(iPosX);
               pollo.setDirection(dirX);
               //iPosX += 100;
               iPosY += 50;
           }
           
           // resets game variables
           cantBricks = bricks.size();
           lives = 3;
           endGame = false;
           pauseGame = false;
         }
        
        // checks if the game has ended if it paused
        if (!endGame && !pauseGame) {
            
            // checks flag for start moving the ball at the beginning of a game
            if (getKeyManager().moveBall && !ball.isMoving()) {
                ball.setMoving(true);
                ball.setSpeed(3);
                ball.setDirectionY(-1);
            }
            
            // ticks the player
            player.tick();
            
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
            
            // ticking the ball
            ball.tick();
            
            // checking all collisions of the ball
            // collision with paddle
            if (ball.intersectaPaddle(player)) {
                ball.setDirectionY(-1);
                if (ball.getX() + ball.getWidth()/2 < player.getX() + player.getWidth()/2) {
                    ball.setDirectionX(-1);
                } else {
                    ball.setDirectionX(1);
                }
            }
            //ticking all bricks
            for (int i = 0; i < bricks.size(); i++) {
                Enemy brick =  bricks.get(i);
                // collision with bricks
                if (!brick.isDestroyed()) {
                    if (ball.intersectaBloque(brick)) {
//                    if (ball.getX() + ball.getSpeed() * ball.getDirectionX() <= brick.getX() + brick.getWidth()) {
//                        ball.setDirectionX(1);
//                    } else if (ball.getX() + ball.getWidth() + ball.getSpeed() * ball.getDirectionX()  >= brick.getX()) {
//                        ball.setDirectionX(-1);
//                    } else 
                        if (ball.getY() > brick.getY()) {
                            ball.setDirectionY(-1);
                        } else {
                            ball.setDirectionY(1);
                        }
                        ball.setDirectionY(1);
                        brick.setDestroyed(true);
                        cantBricks--;
                    }
                }
            }
            // if all bricks are destroyed the game is ended
            if (cantBricks == 0) {
                endGame = true;
            }
            // if the ball touches the botton of the screen its position is reset and you lose one live. If you lose all lives the game is ended
            if (ball.getY() + ball.getHeight() >= getHeight()) {
                lives--;
                if(lives == 0) {
                    endGame = true;
                } else {
                    ball.setX(player.getX() - ball.getWidth() / 2);
                    ball.setY(player.getY() - ball.getHeight());
                    ball.setMoving(false);
                }
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
                // we draw a string saying game over and another string giving instructions to the player on how to restart the game
            } else {
                
                // rendering the ball and player
                player.render(g);
                ball.render(g);
                
                //rendering all bricks
                for (int i = 0; i < bricks.size(); i++) {
                    Enemy brick = bricks.get(i);
                    if (!brick.isDestroyed()) {
                        brick.render(g);
                    }
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
    
    // todo lo que tiene que ver con lectura de archivos
    public String toString(){
        return (score+" "+lives+" "+(endGame ? 1:0));
    }
    
    // Carga la información del objeto desde un string
    public void loadFromString(String[] datos){
        this.score = Integer.parseInt(datos[0]);
        this.lives = Integer.parseInt(datos[1]);
        this.endGame = (Integer.parseInt(datos[2]) == 1 ? true : false);
    }
    
    // Se encarga de guardar en un archivo toda la informacion de nuestra partida
    public void grabarArchivo() throws IOException {
        PrintWriter fileOut = new PrintWriter(new FileWriter(nombreArchivo));
        fileOut.println(this.toString());
        fileOut.println(player.toString());
        fileOut.println(ball.toString());
        for(Enemy brick : bricks){
            fileOut.println(brick.toString());
        }
        for (PowerUps flask : powerUps) {
            fileOut.println(flask.toString());
        }
        for (PowerUps pollo : pollos) {
            fileOut.println(pollo.toString());
        }
        fileOut.close();
    }
    
    // Lee toda la información que guardamos sobre la partida y la carga
    public void leeArchivo() throws IOException {
                                                          
        BufferedReader fileIn;
        try {
            fileIn = new BufferedReader(new FileReader(nombreArchivo));
        } catch (FileNotFoundException e){
            File archivo = new File(nombreArchivo);
            PrintWriter fileOut = new PrintWriter(archivo);
            fileOut.println("100,demo");
            fileOut.close();
            fileIn = new BufferedReader(new FileReader(nombreArchivo));
        }
        loadFromString(fileIn.readLine().split("\\s+"));
        this.player.loadFromString(fileIn.readLine().split("\\s+"));
        this.ball.loadFromString(fileIn.readLine().split("\\s+"));
        for(Enemy brick : bricks){
            brick.loadFromString(fileIn.readLine().split("\\s+"));
        }
        for (PowerUps flask : powerUps) {
            flask.loadFromString(fileIn.readLine().split("\\s+"));
        }
        for (PowerUps pollo : pollos) {
            pollo.loadFromString(fileIn.readLine().split("\\s+"));
        }
        fileIn.close();
    }
}
