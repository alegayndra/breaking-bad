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
public class Enemy extends Item {
    
    private int width;
    private int height;
    private int directionX;
    private int directionY;
    private Game game;
    private int speed;
    
    /**
     * to create direction, width, height, directionX, and directionY and set the enemy is not moving
     * @param x to set the x of the enemy
     * @param y to set the y of the enemy
     * @param width to set the width of the enemy
     * @param height  to set the height of the enemy
     * @param game to set the game of the enemy
     */
    public Enemy(int x, int y, int width, int height, Game game) {
        super(x, y);
        this.width = width;
        this.height = height;
        this.directionX = 0;
        this.directionY = 0;
        this.game = game;
        this.speed = 1;
    }

    /**
     * To get the width of the enemy
     * @return an <code>int</code> value with the width
     */
    public int getWidth() {
        return width;
    }

    /**
     * To get the height of the enemy
     * @return an <code>int</code> value with the height
     */
    public int getHeight() {
        return height;
    }
    
    /**
     * To get the direction of the enemy in the x axis
     * @return an <code>int</code> value with the direction in the x axis
     */
    public int getDirectionX() {
        return directionX;
    }

    /**
     * To get the direction of the enemy in the y axis
     * @return an <code>int</code> value with the direction in the y axis
     */
    public int getDirectionY() {
        return directionY;
    }

    public int getSpeed() {
        return speed;
    }

    /**
     * To set the width of the enemy
     * @param width to set the width of the enemy
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * To set the height of the enemy
     * @param height to set the height of the enemy
     */
    public void setHeight(int height) {
        this.height = height;
    }
    
    /**
     * To set the direction of the enemy in the x axis
     * @param directionX to set the direction of the enemy in the x axis
     */
    public void setDirectionX(int directionX) {
        this.directionX = directionX;
    }
    
    /**
     * To set the direction of the enemy in the y axis
     * @param directionY to set the direction of the enemy in the y axis
     */
    public void setDirectionY(int directionY) {
        this.directionY = directionY;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void tick(Player player) {
        if(player.getX() < getX()) {
            setDirectionX(-1);
        } else if(player.getX() > getX()){
            setDirectionX(1);
        } else {
            setDirectionX(0);
        }
        if(player.getY() < getY()) {
            setDirectionY(-1);
        } else if(player.getY() > getY()){
            setDirectionY(1);
        } else {
            setDirectionY(0);
        }
        
        setX(getX() + getDirectionX() * getSpeed());
        setY(getY() + getDirectionY() * getSpeed());
        
        if (getX() + 60 >= game.getWidth()) {
            setX(game.getWidth() - this.getWidth());
        }
        else if (getX() <= -30) {
            setX(0);
        }
        if (getY() + 80 >= game.getHeight()) {
            setY(game.getHeight() - this.getHeight());
        }
        else if (getY() <= -20) {
            setY(0);
        }
    }
    
    public Rectangle getPerimetro() {
        return new Rectangle(getX(), getY(), getWidth(), getHeight());
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.colision, getX(), getY(), getWidth(), getHeight(), null);
    }

    @Override
    public void tick() {
        // this function exists so all abstracts methods from Item are overriden
    }
    
}
