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
    private Game game;
    private boolean destroyed;
    
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
        this.game = game;
        this.destroyed = false;
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

    public boolean isDestroyed() {
        return destroyed;
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

    public void setDestroyed(boolean destroyed) {
        this.destroyed = destroyed;
    }

    public void tick(Player player) {
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
        g.drawImage(Assets.brick, getX(), getY(), getWidth(), getHeight(), null);
    }

    @Override
    public void tick() {
        // this function exists so all abstracts methods from Item are overriden
    }
    
}
