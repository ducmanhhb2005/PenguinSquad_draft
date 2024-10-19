package com.penguinsquad.userinterface;

import java.awt.event.KeyEvent;

import com.penguinsquad.object.GameWorld;
import com.penguinsquad.object.MegaMan;

public class InputManager {
	//private GamePanel gamePanel;
	private GameWorld gameWorld;
	public InputManager(GameWorld gameWorld) {
		this.gameWorld = gameWorld;
	}
    public void processKeyPressed(int keyCode){
    
        switch(keyCode){
            case KeyEvent.VK_UP: 
            	//gamePanel.physicalMap.y+=3
                break;
            case KeyEvent.VK_DOWN:
            	//gamePanel.physicalMap.y-=3;
                break;
            case KeyEvent.VK_LEFT:
            	gameWorld.megaman.setDirection(MegaMan.DIR_LEFT);
            	gameWorld.megaman.setSpeedX(-5);
                
            	//gamePanel.physicalMap.x-=3;
            	break;
            case KeyEvent.VK_RIGHT:
            	gameWorld.megaman.setDirection(MegaMan.DIR_RIGHT); // chieu nha dan
            	gameWorld.megaman.setSpeedX(5); //sang phai
            	//gamePanel.physicalMap.x+=3;
                break;
            case KeyEvent.VK_ENTER:
                break;
            case KeyEvent.VK_SPACE:
            	gameWorld.megaman.setSpeedY(-3);
            	gameWorld.megaman.setPosY(gameWorld.megaman.getPosY()-3);
                break;
            case KeyEvent.VK_A:
                break;
        }
    }
    public void processKeyReleased(int keyCode) {

        switch (keyCode) {
            case KeyEvent.VK_UP:
                break;
            case KeyEvent.VK_DOWN:
                break;
            case KeyEvent.VK_LEFT:
            	
            	gameWorld.megaman.setSpeedX(0);
                break;
            case KeyEvent.VK_RIGHT:
            	gameWorld.megaman.setSpeedX(0);
                break;
            case KeyEvent.VK_ENTER:
                break;
            case KeyEvent.VK_SPACE:
                break;
            case KeyEvent.VK_A:
                break;
        }
    }
    
}

