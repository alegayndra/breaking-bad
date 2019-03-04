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

    public boolean left;    // flag to move left the player
    public boolean right;   // flag to move right the player
    public boolean pause;
    public boolean save;
    public boolean load;
    public boolean lastPause;
    public boolean lastSave;
    public boolean lastLoad;

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
        /**
         * checks if in the last tick the keys 'p', 'g', and 'c' were pressed and if in the current tick they were released
         * if this is true the corresponding flag of the keyi s true, else it is false
         */
        if (lastPause && !keys[KeyEvent.VK_P]) {
            pause = true;
        } else {
            pause = false;
        }
        
        if (lastSave && !keys[KeyEvent.VK_G]) {
            save = true;
        } else {
            save = false;
        }
        
        if (lastLoad  && !keys[KeyEvent.VK_C]) {
            load = true;
        } else {
            load = false;
        }
        
        left = keys[KeyEvent.VK_LEFT];
        right = keys[KeyEvent.VK_RIGHT];
    }
}
