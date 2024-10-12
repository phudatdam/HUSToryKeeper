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
        if(code == KeyEvent.VK_UP){ // lên
            upPressed = true;
        }
        if(code == KeyEvent.VK_DOWN){ // xuống
            downPressed = true;
        }
        if(code == KeyEvent.VK_LEFT){ // trái
            leftPressed = true;
        }
        if(code == KeyEvent.VK_RIGHT){ // phải
            rightPressed = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) { // gán nhả các phím điều khiển thì nhân vật không di  chuyển
        int code = e.getKeyCode();
        if(code == KeyEvent.VK_UP){ // lên
            upPressed = false;
        }
        if(code == KeyEvent.VK_DOWN){ // xuống
            downPressed = false;
        }
        if(code == KeyEvent.VK_LEFT){ // trái
            leftPressed = false;
        }
        if(code == KeyEvent.VK_RIGHT){ // phải
            rightPressed = false;
        }
    }
}
