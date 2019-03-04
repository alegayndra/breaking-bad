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
    private int width;
    private int height;
    private int speed;
    private int directionX;
    private int directionY;
    private int type;
    private Game game;
    
    public Ball (int x, int y, int width, int height, Game game) {
        super(x, y);
        this.width = width;
        this.height = height;
        this.speed = 0;
        this.directionX = 1;
        this.directionY = 0;
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
    
    public int getDirectionX() {
        return directionX;
    }
    
    public int getDirectionY() {
        return directionY;
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

    public void setDirectionX(int directionX) {
        this.directionX = directionX;
    }
    
    public void setDirectionY(int directionY) {
        this.directionY = directionY;
    }

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
