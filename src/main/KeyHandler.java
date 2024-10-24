package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
	GamePanel gp;

    public boolean upPressed; // nhấn lên
    public boolean downPressed; // nhấn xuống
    public boolean rightPressed; // nhấn phải
    public boolean leftPressed; // nhấn trái
    public boolean enterPressed; // nhấn tương tác
	public boolean attackPressed; // đánh thường
	
	public KeyHandler(GamePanel gp) {
        this.gp = gp;
	}

	@Override
    public void keyTyped(KeyEvent e) {
		
	}

    @Override
    public void keyPressed(KeyEvent e) { // gán nhấn các phím điều khiển lên/xuống/trái/phải.
        int code = e.getKeyCode();

        // TITLE STATE
        if(gp.gameState == gp.titleState){
            if(code == KeyEvent.VK_W){ // lên
                gp.ui.commandNum--;
                if(gp.ui.commandNum < 0){
                    gp.ui.commandNum = 2;
                }
            }
            if(code == KeyEvent.VK_S){ // xuống
                gp.ui.commandNum++;
                if(gp.ui.commandNum > 2){
                    gp.ui.commandNum = 0;
                }
            }
            if(code == KeyEvent.VK_E){
                if(gp.ui.commandNum == 0){
                    gp.gameState = gp.playState;
                }
                if(gp.ui.commandNum == 1){
                    // add later
                }
                if(gp.ui.commandNum == 2){
                    System.exit(0);
                }
            }
        }

        // PLAY STATE
        else if(gp.gameState ==  gp.playState) {
        	if(code == KeyEvent.VK_W){ // lên
                upPressed = true;
            }
            if(code == KeyEvent.VK_S){ // xuống
                downPressed = true;
            }
            if(code == KeyEvent.VK_A){ // trái
                leftPressed = true;
            }
            if(code == KeyEvent.VK_D){ // phải
                rightPressed = true;
            }
            if(code == KeyEvent.VK_P){
                    gp.gameState = gp.pauseState;
            }
            if(code == KeyEvent.VK_I) {
            	gp.gameState = gp.characterState;
            }
            if(code == KeyEvent.VK_E) {
                enterPressed = true;
            }
            if(code == KeyEvent.VK_J) {
                attackPressed = true;
            }
        }
        
        // PAUSE STATE
        else if(gp.gameState == gp.pauseState) {
        	if(code == KeyEvent.VK_P) {
        		gp.gameState = gp.playState;
        	}
        }
        
        // CHARACTER SCREEN (STATUS SCREEN)
        else if(gp.gameState == gp.characterState) {
        	if(code == KeyEvent.VK_I) {
        		gp.gameState = gp.playState;
        	}
        	if(code == KeyEvent.VK_W) {
        		if(gp.ui.slotRow != 0) {
        			gp.ui.slotRow --;
        		}
        	}
        	if(code == KeyEvent.VK_S) {
        		if(gp.ui.slotRow != 2) {
        			gp.ui.slotRow ++;
        		}
        	}
        	if(code == KeyEvent.VK_D) {
        		if(gp.ui.slotCol != 4) {
        			gp.ui.slotCol ++;
        		}
        	}
        	if(code == KeyEvent.VK_A) {
        		if(gp.ui.slotCol != 0) {
        			gp.ui.slotCol --;
        		}
        	}
        }
        // DIALOGUE STATE
        else if (gp.gameState == gp.dialogueState) {
            if( code == KeyEvent.VK_E ) {
                enterPressed = true;
            }
            if(code == KeyEvent.VK_A){ // trái
                leftPressed = true;
            }
            if(code == KeyEvent.VK_D){ // phải
                rightPressed = true;
            }
            if(code == KeyEvent.VK_P){
                if(gp.gameState == gp.playState){
                    gp.gameState = gp.pauseState;
                }
                else if(gp.gameState == gp.pauseState){
                    gp.gameState = gp.playState;
                }
            }
            if(code == KeyEvent.VK_I) {
            	gp.gameState = gp.characterState;
            }
        }
        
        // PAUSE STATE
        else if(gp.gameState == gp.pauseState) {
        	if(code == KeyEvent.VK_P) {
        		gp.gameState = gp.playState;
        	}
        }
        
        // CHARACTER SCREEN (STATUS SCREEN)
        else if(gp.gameState == gp.characterState) {
        	if(code == KeyEvent.VK_I) {
        		gp.gameState = gp.playState;
        	}
        	if(code == KeyEvent.VK_UP) {
        		if(gp.ui.slotRow != 0) {
        			gp.ui.slotRow --;
        		}
        	}
        	if(code == KeyEvent.VK_DOWN) {
        		if(gp.ui.slotRow != 2) {
        			gp.ui.slotRow ++;
        		}
        	}
        	if(code == KeyEvent.VK_RIGHT) {
        		if(gp.ui.slotCol != 4) {
        			gp.ui.slotCol ++;
        		}
        	}
        	if(code == KeyEvent.VK_LEFT) {
        		if(gp.ui.slotCol != 0) {
        			gp.ui.slotCol --;
        		}
        	}
        }
    }

    @Override
    public void keyReleased(KeyEvent e) { // gán nhả các phím điều khiển thì nhân vật không di  chuyển
        int code = e.getKeyCode();
        if(code == KeyEvent.VK_W){ // lên
            upPressed = false;
        }
        if(code == KeyEvent.VK_S){ // xuống
            downPressed = false;
        }
        if(code == KeyEvent.VK_A){ // trái
            leftPressed = false;
        }
        if(code == KeyEvent.VK_D){ // phải
            rightPressed = false;
        }
        if(code == KeyEvent.VK_E){
        	enterPressed = false;
        }
        if(code == KeyEvent.VK_J){
        	attackPressed = false;
        }
    }
}