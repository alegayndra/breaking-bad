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
public class PowerUps extends Item{
    private int width;
    private int height;
    private int speed;
    private int direction;
    private int type;
    private Game game;
    
    public PowerUps (int x, int y, int width, int height, int speed , int type, Game game) {
        super(x, y);
        this.width = width;
        this.height = height;
        this.speed = speed;
        this.direction = 1;
        this.type = type;
        this.game = game;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
    
    public int getSpeed() {
        return speed;
    }
    
    public int getDirection() {
        return direction;
    }

    public int getType() {
        return type;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public Rectangle getPerimetro() {
        return new Rectangle(getX(), getY(), getWidth(), getHeight());
    }
    
    @Override
    public void tick() {
        setX(getX() + getSpeed() * getDirection());
        
        // checks that the object does not goes out of the bounds
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

    @Override
    public void render(Graphics g) {
        if (type == 1) {
            g.drawImage(Assets.blueFlask, getX(), getY(), getWidth(), getHeight(), null);
        } else {
            g.drawImage(Assets.pollos, getX(), getY(), getWidth(), getHeight(), null);
        }
        
    }
    
}
