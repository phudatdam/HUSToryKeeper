package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
	GamePanel gp;

    public boolean upPressed; // nhấn lên
    public boolean downPressed; // nhấn xuống
    public boolean rightPressed; // nhấn phải
    public boolean leftPressed; // nhấn trái

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
            if(gp.gameState == gp.playState){
                gp.gameState = gp.pauseState;
            }
            else if(gp.gameState == gp.pauseState){
                gp.gameState = gp.playState;
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
    }
}
