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
     * To get a rectangle with the position, width, and height of the ball
     * @return an <code>Rectangle</code> rectangle with the given position, width, and height
     */
    public Rectangle getPerimetro() {
        return new Rectangle(getX(), getY(), getWidth(), getHeight());
    }
    
    public boolean intersectaPaddle(Object obj) {
        return ((obj instanceof Player) && (getPerimetro().intersects(((Player) obj).getPerimetro())));
     }
    
    public boolean intersectaPowerUp(Object obj) {
        return ((obj instanceof PowerUps) && (getPerimetro().intersects(((PowerUps) obj).getPerimetro())));
     }
    
    public boolean intersectaBloque(Object obj) {
        return ((obj instanceof Enemy) && (getPerimetro().intersects(((Enemy) obj).getPerimetro())));
     }
    
    @Override
    public void tick() {
        // updates the position of the ball
        setX(getX() + getDirectionX() * getSpeed());
        setY(getY() + getDirectionY() * getSpeed());
        
        // checks that the object does not goes out of the bounds
        if (getX() + 60 >= game.getWidth()) {
            setDirectionX(-1);
        }
        else if (getX() <= -30) {
            setDirectionX(1);
        }
        if (getY() + 80 >= game.getHeight()) {
            setDirectionX(-1);
        }
        else if (getY() <= -20) {
            setDirectionX(1);
        }
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.ball, getX(), getY(), getWidth(), getHeight(), null);
    }
}
