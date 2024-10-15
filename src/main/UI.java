package main;

import object.OBJ_Heart;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;

public class UI {
    GamePanel gp;
    Graphics2D g2;
    Font maruMonica;
    BufferedImage heartImage;
    public boolean messageOn = false;
    public String message = "";
   // int messageCounter = 0;
   // public boolean gameFinished = false;
    public int commandNum = 0;

    public UI(GamePanel gp){
        this.gp = gp;

        try {
            InputStream is = getClass().getResourceAsStream("/font/x12y16pxMaruMonica.ttf");
            maruMonica = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        OBJ_Heart heart = new OBJ_Heart(gp);
        heartImage = heart.image;
    }

    public void showMessage(String text){
        message = text;
        messageOn = true;
    }

    public void draw(Graphics2D g2){
        this.g2 = g2;

        g2.setFont(maruMonica);
        g2.setColor(Color.white);

        /*g2.drawImage(heartImage, gp.tileSize / 2, gp.tileSize / 2, 26, 26, null);
        g2.drawString("x " + gp.player.hasHeart, 49, 39);*/

        // TITLE STATE
        if(gp.gameState == gp.titleState){
            drawTitleScreen();
        }

        // PLAY STATE
        if(gp.gameState == gp.playState){

        }

        // PAUSE STATE
        if(gp.gameState == gp.pauseState){
            drawPauseScreen();
        }

        // DIALOGUE STATE
        if(gp.gameState == gp.dialogueState){
            drawDialogueState();
        }
    }

    public void drawTitleScreen(){

        g2.setColor(new Color(120, 119, 70));
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

        // TITLE NAME
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,100F));
        String text = "HUSTory Keeper";
        int x = getXforCenteredText(text);
        int y = gp.tileSize * 4;

        // SHADOW
        g2.setColor(Color.black);
        g2.drawString(text, x + 5, y + 5);

        // MAIN COLOR
        g2.setColor(Color.white);
        g2.drawString(text, x, y);

        // HUSTer
        x = gp.screenWidth / 2 - (gp.tileSize) - 20;
        y += gp.tileSize * 2 - 10;
        g2.drawImage(gp.player.down3, x, y, gp.tileSize * 3, gp.tileSize * 3, null);

        // MENU
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,50F));
        text = "NEW GAME";
        x = getXforCenteredText(text);
        y += gp.tileSize * 5;
        g2.drawString(text, x ,y);
        if(commandNum == 0){
            g2.drawString(">", x - gp.tileSize, y);
        }

        text = "TUTORIAL";
        x = getXforCenteredText(text);
        y += gp.tileSize * 2;
        g2.drawString(text, x ,y);
        if(commandNum == 1){
            g2.drawString(">", x - gp.tileSize, y);
        }

        text = "SETTING";
        x = getXforCenteredText(text);
        y += gp.tileSize * 2 ;
        g2.drawString(text, x ,y);
        if(commandNum == 2){
            g2.drawString(">", x - gp.tileSize, y);
        }

        text = "QUIT";
        x = getXforCenteredText(text);
        y += gp.tileSize * 2 ;
        g2.drawString(text, x ,y);
        if(commandNum == 3){
            g2.drawString(">", x - gp.tileSize, y);
        }

    }

    public void drawPauseScreen(){

        g2.setFont(g2.getFont().deriveFont(Font.BOLD,50F));
        g2.setColor(Color.RED);
        String text = "PAUSED";
        int x = getXforCenteredText(text);
        int y = gp.screenHeight / 2;

        g2.drawString(text, x, y); // set chữ ở giữa màn hình

    }

    public void drawDialogueState(){
        // WINDOW
        int x = gp.tileSize * 2;
        int y;
        int width;
        int height;
    }

    public int getXforCenteredText(String text){ // để tọa độ x ở giữa màn hình
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth / 2 - length / 2;
        return x;
    }



}

