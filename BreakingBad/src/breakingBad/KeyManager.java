/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package breakingBad;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author antoniomejorado
 */
public class KeyManager implements KeyListener {
    
//    public boolean up;      // flag to move up the player
//    public boolean down;    // flag to move down the player
    public boolean left;    // flag to move left the player
    public boolean right;   // flag to move right the player
//    public boolean increaseUp; // flag to increase player's speed upwards
//    public boolean increaseDown; // flag to increase player's speed downwards
//    public boolean increaseLeft; // flag to increase player's speed to the left
//    public boolean increaseRight; // flag to increase player's speed to the right

    private boolean keys[];  // to store all the flags for every key
    
    public KeyManager() {
        keys = new boolean[256];
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // set true to every key pressed
        keys[e.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // set false to every key released
        keys[e.getKeyCode()] = false;
    }
    
    /**
     * to enable or disable moves on every tick
     */
    public void tick() {
//        if (up == true && keys[KeyEvent.VK_UP] == false) {
//            increaseUp = true;
//        } else {
//            increaseUp = false;
//        }
//        if (down == true && keys[KeyEvent.VK_DOWN] == false) {
//            increaseDown = true;
//        } else {
//            increaseDown = false;
//        }
//        if (left == true && keys[KeyEvent.VK_LEFT] == false) {
//            increaseLeft = true;
//        } else {
//            increaseLeft = false;
//        }
//        if (right == true && keys[KeyEvent.VK_RIGHT] == false) {
//            increaseRight = true;
//        } else {
//            increaseRight = false;
//        }
        
        //up = keys[KeyEvent.VK_UP];
//        down = keys[KeyEvent.VK_DOWN];
        left = keys[KeyEvent.VK_LEFT];
        right = keys[KeyEvent.VK_RIGHT];
    }
}
