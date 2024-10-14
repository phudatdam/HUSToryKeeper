package main;

import object.OBJ_Heart;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

public class UI {
    GamePanel gp;
    Graphics2D g2;
    Font arial_30, arial_80B;
    BufferedImage heartImage;
    //public boolean messageOn = false;
    // public String message = "";
    // int messageCounter = 0;
    // public boolean gameFinished = false;

    // double playTime;
    DecimalFormat dFormat = new DecimalFormat("#0.00");

    public UI(GamePanel gp){
        this.gp = gp;
        arial_30 = new Font("Arial", Font.PLAIN, 30);
        // arial_80B = new Font("Arial", Font.BOLD, 80);
        OBJ_Heart heart = new OBJ_Heart(gp);
        heartImage = heart.image;
    }

    /*public void showMessage(String text){
        message = text;
        //messageOn = true;
    }*/

    public void draw(Graphics2D g2){
        this.g2 = g2;
        g2.setFont(arial_30);
        g2.setColor(Color.white);
        g2.drawImage(heartImage, gp.tileSize / 2, gp.tileSize / 2, 26, 26, null);
        g2.drawString("x " + gp.player.hasHeart, 49, 39);

        if(gp.gameState == gp.playState){

        }
        if(gp.gameState == gp.pauseState){
            drawPauseScreen();
        }
    }

    public void drawPauseScreen(){

        //g2.getFont(g2.getFont().deriveFont(Font.PLAIN, 80F));
        String text = "PAUSED";
        int x = getXforCenteredText(text);
        int y = gp.screenHeight / 2;

        g2.drawString(text, x, y); // set chữ ở giữa màn hình

    }

    public int getXforCenteredText(String text){ // để tọa độ x ở giữa màn hình
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth / 2 - length / 2;
        return x;
    }


}

