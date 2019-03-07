/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package breakingBad;

import java.awt.Graphics;
import java.awt.Rectangle;

/**
 *
 * @author betin
 */
public class Ball extends Item{
    private int width;              // to know the width of the ball
    private int height;             // to know the height of the ball
    private int speed;              // to know the speed of the ball
    private int directionX;         // to know the direction in the x axis of tha ball
    private int directionY;         // to know the direction in the y axis of tha ball
    private Game game;          // to know the game of the player
    private boolean moving;
    
    /**
     * To create position, width, height, direction in the x and y axis, speed and game
     * @param x to set the x of the ball
     * @param y to set the y of the ball
     * @param width to set the width of the ball
     * @param height to set the height of the ball
     * @param game to ser the game of the ball
     */
    public Ball (int x, int y, int width, int height, Game game) {
        super(x, y);
        this.width = width;
        this.height = height;
        this.speed = 0;
        this.directionX = 1;
        this.directionY = 0;
        this.game = game;
        moving = false;
    }

    /**
     * To get the width of the ball
     * @return an <code>int</code> value with the width
     */
    public int getWidth() {
        return width;
    }

    /**
     * To get the height of the ball
     * @return an <code>int</code> value with the height
     */
    public int getHeight() {
        return height;
    }
    
   /**
     * To get the speed of the ball
     * @return an <code>int</code> value with the speed
     */
    public int getSpeed() {
        return speed;
    }
    
   /**
     * To get the direction of the ball in the x axis
     * @return an <code>int</code> value with the direction in the axis
     */
    public int getDirectionX() {
        return directionX;
    }
    
   /**
     * To get the direction of the ball in the y axis
     * @return an <code>int</code> value with the direction in the axis
     */
    public int getDirectionY() {
        return directionY;
    }

    /**
     * To set the width of the ball
     * @param width to set the width of the ball
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * To set the height of the ball
     * @param height to set the height of the ball
     */
    public void setHeight(int height) {
        this.height = height;
    }

     /**
     * To set the speed of the ball
     * @param speed to set the speed of the ball
     */
    public void setSpeed(int speed) {
        this.speed = speed;
    }
    
     /**
     * To set the direction of the ball
     * @param directionX to set the direction of the ball
     */
    public void setDirectionX(int directionX) {
        this.directionX = directionX;
    }
    
     /**
     * To set the direction of the ball
     * @param directionY to set the direction of the ball
     */
    public void setDirectionY(int directionY) {
        this.directionY = directionY;
    }
    
    /**
     * 
     * @return 
     */
    public boolean isMoving() {
        return moving;
    }
    
    /**
     * 
     * @param moving 
     */
    public void setMoving(boolean moving) {
        this.moving = moving;
    }
    
    /**
     * To get a rectangle with the position, width, and height of the ball
     * @return an <code>Rectangle</code> rectangle with the given position, width, and height
     */
    public Rectangle getPerimetro() {
        return new Rectangle(getX(), getY(), getWidth(), getHeight());
    }
    /**
     * 
     * @param obj
     * @return 
     */
    public boolean intersectaPaddle(Object obj) {
        return ((obj instanceof Player) && (getPerimetro().intersects(((Player) obj).getPerimetro())));
     }
    
    /**
     * 
     * @param obj
     * @return 
     */
    public boolean intersectaPowerUp(Object obj) {
        return ((obj instanceof PowerUps) && (getPerimetro().intersects(((PowerUps) obj).getPerimetro())));
     }
    
    /**
     * 
     * @param obj
     * @return 
     */
    public boolean intersectaBloque(Object obj) {
        return ((obj instanceof Enemy) && (getPerimetro().intersects(((Enemy) obj).getPerimetro())));
     }
    
    // Carga la información del objeto desde un string
    /**
     * To set the value of position and the direction both in the 'x' and 'y' axis of the ball from the file that was loaded
     * @param datos to set all the variables
     */
    public void loadFromString(String[] datos){
        this.x = Integer.parseInt(datos[0]);
        this.y = Integer.parseInt(datos[1]);
        this.directionX = Integer.parseInt(datos[2]);
        this.directionY = Integer.parseInt(datos[3]);
    }
    
    /**
     * To get all the variable that need to be stored in the file as a string
     * @return an <code>String</code> value with all the information of the variables
     */
    public String toString(){
        return (x+" "+y+" "+directionX + " " + directionY);
    }
    
    @Override
    public void tick() {
        /*
        checks if the ball is already moving
        if it is it moves freely
        else it follow the paddle
        */
        if (moving) {
             // updates the position of the ball
            setX(getX() + getDirectionX() * getSpeed());
            setY(getY() + getDirectionY() * getSpeed());

            // checks that the ball bounces from the walls
            if (getX() + getWidth() >= game.getWidth()) {
                setDirectionX(-1);
            }
            else if (getX() <= 0) {
                setDirectionX(1);
            }
            else if (getY() <= 0) {
                setDirectionY(1);
            }
        } else {
            // follows the paddle
            setX(game.getPlayer().getX() + getWidth() / 2);
            setY(game.getPlayer().getY() - getHeight());
        }
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.ball, getX(), getY(), getWidth(), getHeight(), null);
    }
}
