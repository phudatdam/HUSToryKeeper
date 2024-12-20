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
    public boolean shotPressed; // bắn xa

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
            titleState(code);
        }

        // PLAY STATE
        else if(gp.gameState ==  gp.playState) {
            playState(code);
        }

        // PAUSE STATE
        else if(gp.gameState == gp.pauseState) {
            pauseState(code);
        }

        // CHARACTER SCREEN (STATUS SCREEN)
        else if(gp.gameState == gp.characterState) {
            characterState(code);
        }

        // DIALOGUE STATE
        else if (gp.gameState == gp.dialogueState || gp.gameState == gp.cutsceneState) {
            dialogueState(code);
        }

        // NOTE STATE
        else if(gp.gameState == gp.noteState){
            noteState(code);
        }

        // OPTIONS STATE
        else if (gp.gameState == gp.optionsState) {
            optionsState(code);
        }
        // Over STATE
        else if (gp.gameState == gp.gameoverState) {
            OverState(code);
        }
        
        // RECEIVE WEAPON STATE
        else if (gp.gameState == gp.receiveWeaponState) {
        	receiveWeaponState(code);
        }
    }

    public void OverState(int code)
    {
        if (code == KeyEvent.VK_W) { // lên
            gp.ui.commandNum--;
            if (gp.ui.commandNum < 0) {
                gp.ui.commandNum = 1;
            }
            gp.playSE(2);
        }
        if (code == KeyEvent.VK_S) { // xuống
            gp.ui.commandNum++;
            if (gp.ui.commandNum > 1) {
                gp.ui.commandNum = 0;
            }
            gp.playSE(2);
        }
        if (code == KeyEvent.VK_E) {
            if (gp.ui.commandNum == 0) {
                gp.gameState = gp.playState;
                gp.retry();
                gp.ui.addMessage("Bạn vừa được quay ngược thời gian");
            }
            if (gp.ui.commandNum == 1) {
                gp.gameState = gp.titleState;
                gp.restart();
                gp.ui.addMessage("Bạn bắt đầu hành trình mới");
                gp.ui.commandNum = 0;
            }
            gp.playSE(2);
        }
    }

    public void titleState(int code) {
        if (code == KeyEvent.VK_W) { // lên
            gp.ui.commandNum--;
            if (gp.ui.commandNum < 0) {
                gp.ui.commandNum = 2;
            }
            gp.playSE(2);
        }
        if (code == KeyEvent.VK_S) { // xuống
            gp.ui.commandNum++;
            if (gp.ui.commandNum > 2) {
                gp.ui.commandNum = 0;
            }
            gp.playSE(2);
        }
        if (code == KeyEvent.VK_E) {
            if (gp.ui.commandNum == 0) {
                gp.gameState = gp.cutsceneState;
                gp.csManager.sceneNum = gp.csManager.intro;
            }
            if (gp.ui.commandNum == 1) {
                gp.gameState = gp.optionsState;
            }
            if (gp.ui.commandNum == 2) {
                System.exit(0);
            }
            gp.playSE(2);
        }
    }

    public void playState(int code) {
        if (code == KeyEvent.VK_W) { // lên
            upPressed = true;
        }
        if (code == KeyEvent.VK_S) { // xuống
            downPressed = true;
        }
        if (code == KeyEvent.VK_A) { // trái
            leftPressed = true;
        }
        if (code == KeyEvent.VK_D) { // phải
            rightPressed = true;
        }
        if (code == KeyEvent.VK_P) {
            gp.playSE(2);
            gp.gameState = gp.pauseState;
        }
        if (code == KeyEvent.VK_I) {
            gp.playSE(2);
            gp.gameState = gp.characterState;
        }
        if (code == KeyEvent.VK_E) {
            enterPressed = true;
        }
        if(code == KeyEvent.VK_J) {
            if(gp.player.currentWeapon.type == 3){ gp.playSE(4);}
            if(gp.player.currentWeapon.type == 4){ gp.playSE(1);}
            if(gp.player.currentWeapon.type == 5){ gp.playSE(5);}
            if(gp.player.currentWeapon.type == 7){ gp.playSE(11);}
            if(gp.player.currentWeapon.type == 8){ gp.playSE(12);}
            if(gp.player.currentWeapon.type == 9){ gp.playSE(10);}
            attackPressed = true;
            shotPressed = true;
        }
    }

    public void pauseState(int code) {
        if (code == KeyEvent.VK_E) {
            enterPressed = true;
        }
        gp.playSE(2);
        if (code == KeyEvent.VK_W) { // lên
            gp.ui.commandNum--;
            if (gp.ui.commandNum < 0) {
                gp.ui.commandNum = 1;
            }
            gp.playSE(2);
        }
        if (code == KeyEvent.VK_S) { // xuống
            gp.ui.commandNum++;
            if (gp.ui.commandNum > 1) {
                gp.ui.commandNum = 0;
            }
            gp.playSE(2);
        }

    }

    public void dialogueState(int code) {
        if (code == KeyEvent.VK_E) {
            enterPressed = true;
        }
        gp.playSE(2);
    }

    public void noteState(int code){
        if(code == KeyEvent.VK_E){
            gp.ui.npc.dialogueIndex = 2;
            gp.gameState = gp.dialogueState;
        }
        gp.playSE(2);
    }

    public void characterState(int code) {
        if (code == KeyEvent.VK_I) {
            gp.gameState = gp.playState;
        }
        gp.playSE(2);
        if (code == KeyEvent.VK_W) {
            if (gp.ui.slotRow != 0) {
                gp.ui.slotRow--;
            }
            gp.playSE(2);
        }
        if (code == KeyEvent.VK_S) {
            if (gp.ui.slotRow != 2) {
                gp.ui.slotRow++;
            }
            gp.playSE(2);
        }
        if (code == KeyEvent.VK_D) {
            if (gp.ui.slotCol != 4) {
                gp.ui.slotCol++;
            }
            gp.playSE(2);
        }
        if (code == KeyEvent.VK_A) {
            if (gp.ui.slotCol != 0) {
                gp.ui.slotCol--;
            }
            gp.playSE(2);
        }
        if (code == KeyEvent.VK_E) {
            gp.player.selectItem();
        }
    }

    public void optionsState(int code) {
        if (code == KeyEvent.VK_E) {
            enterPressed = true;
        }
        gp.playSE(2);
        int maxCommandNum = 0;
        int minCommandNum = 0;
        switch (gp.ui.subState) {
            case 0:
                maxCommandNum = 5;
                minCommandNum = 1;
                break;
            case 2:
                maxCommandNum = 1;
                minCommandNum = 0;
                break;
        }
        if (code == KeyEvent.VK_W) { // lên
            gp.ui.commandNum--;
            if (gp.ui.commandNum < minCommandNum) {
                gp.ui.commandNum = maxCommandNum;
            }
            gp.playSE(2);
        }
        if (code == KeyEvent.VK_S) { // xuống
            gp.ui.commandNum++;
            if (gp.ui.commandNum > maxCommandNum) {
                gp.ui.commandNum = minCommandNum;
            }
            gp.playSE(2);
        }
        if (code == KeyEvent.VK_A) { // trái
            if (gp.ui.subState == 0) {
                if (gp.ui.commandNum == 1 && gp.music.volumeScale > -1) {
                    gp.music.volumeScale--;
                    if (gp.music.volumeScale < 0) gp.music.volumeScale = 5;
                    gp.music.checkVolume();
                    gp.playSE(2);
                }
                if (gp.ui.commandNum == 2 && gp.se.volumeScale >= 0) {
                    gp.se.volumeScale--;
                    if (gp.se.volumeScale < 0) gp.se.volumeScale = 5;
                    gp.playSE(2);
                }
            }
        }
        if (code == KeyEvent.VK_D) { // phải
            if (gp.ui.subState == 0) {
                if (gp.ui.commandNum == 1 && gp.music.volumeScale >= 0) {
                    gp.music.volumeScale++;
                    if (gp.music.volumeScale > 5) gp.music.volumeScale = 0;
                    gp.music.checkVolume();
                    gp.playSE(2);
                }
                if (gp.ui.commandNum == 2 && gp.se.volumeScale >= 0) {
                    gp.se.volumeScale++;
                    if (gp.se.volumeScale > 5) gp.se.volumeScale = 0;
                    gp.playSE(2);
                }
            }
        }
    }
    
    public void receiveWeaponState(int code) {
    	if (code == KeyEvent.VK_E) {
    		gp.ui.finalWeapon = null;
            gp.gameState = gp.playState;
            gp.playSE(2);
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
            shotPressed = false;
        }
    }
}

