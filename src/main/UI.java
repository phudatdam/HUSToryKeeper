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
    Font determinationSans;
    BufferedImage heartImage;
    public boolean messageOn = false;
    public String message = "";
   // int messageCounter = 0;
   // public boolean gameFinished = false;
    public int commandNum = 0;

    public UI(GamePanel gp){
        this.gp = gp;

        try {
            InputStream is = getClass().getResourceAsStream("/font/SVN-Determination Sans.otf");
            determinationSans = Font.createFont(Font.TRUETYPE_FONT, is);
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

        g2.setFont(determinationSans);
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
        
        // CHARACTER STATE
        if(gp.gameState == gp.characterState) {
        	drawCharacterScreen();
        }
    }

    public void drawTitleScreen(){

        g2.setColor(new Color(120, 119, 70));
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

        // TITLE NAME
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,100F));
        String text = "HUSTory Keeper";
        int x = getXforCenteredText(text);
        int y = gp.tileSize * 3 / 2;

        // SHADOW
        g2.setColor(Color.black);
        g2.drawString(text, x + 5, y + 5);

        // MAIN COLOR
        g2.setColor(Color.white);
        g2.drawString(text, x, y);

        // HUSTer
        x = gp.screenWidth / 2 - (gp.tileSize) - 20;
        y += gp.tileSize;
        g2.drawImage(gp.player.down3, x, y, gp.tileSize * 2, gp.tileSize * 2, null);

        // MENU
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,50F));
        text = "NEW GAME";
        x = getXforCenteredText(text);
        y += (int) gp.tileSize * 3;
        g2.drawString(text, x ,y);
        if(commandNum == 0){
            g2.drawString(">", x - gp.tileSize, y);
        }

        text = "SETTING";
        x = getXforCenteredText(text);
        y += (int) gp.tileSize * 1.5;
        g2.drawString(text, x ,y);
        if(commandNum == 1){
            g2.drawString(">", x - gp.tileSize, y);
        }

        text = "QUIT";
        x = getXforCenteredText(text);
        y += (int) gp.tileSize * 1.5;

        g2.drawString(text, x ,y);
        if(commandNum == 2){
            g2.drawString(">", x - gp.tileSize, y);
        }

        g2.setFont(g2.getFont().deriveFont(Font.BOLD,25F));
        text = "(Press E to choose)";
        x = getXforCenteredText(text);
        y += gp.tileSize;
        g2.drawString(text, x ,y);
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
    
    public void drawCharacterScreen() {
    	// FRAME
    	int frameX = gp.tileSize*1;
    	int frameY = gp.tileSize*1;
    	int frameWidth = gp.tileSize*3;
    	int frameHeight = gp.tileSize*6;
    	int textX = frameX + 9;
    	int textY = frameY + 24;
    	drawSubScreen(frameX, frameY, frameWidth, frameHeight);
    	
    	// TEXT
    	g2.setFont(g2.getFont().deriveFont(16F));
    	int lineHeight = 16 + 4; // độ cao dòng = cỡ font + khoảng cách các dòng
    			
    	// names
    	g2.drawString("Level:", textX, textY);
    	textY += lineHeight;
    	g2.drawString("Strength:", textX, textY);
    	textY += lineHeight;
    	g2.drawString("Coin:", textX, textY);
    	textY += lineHeight;
    	g2.drawString("Life:", textX, textY);
    	textY += lineHeight;
    	
    	// values
    	textY = frameY + 24; // reset textY
    	int tailX = frameX + frameWidth - 9;
    	String value;
    	
    	value = String.valueOf(gp.player.level);
    	textX = getXforAlignRightText(value, tailX);
    	g2.drawString(value, textX, textY);
    	textY += lineHeight;
    	
    	value = String.valueOf(gp.player.strength);
    	textX = getXforAlignRightText(value, tailX);
    	g2.drawString(value, textX, textY);
    	textY += lineHeight;
    	
    	value = String.valueOf(gp.player.coin);
    	textX = getXforAlignRightText(value, tailX);
    	g2.drawString(value, textX, textY);
    	textY += lineHeight;
    	
    	value = String.valueOf(gp.player.life+ "/" + String.valueOf(gp.player.maxLife));
    	textX = getXforAlignRightText(value, tailX);
    	g2.drawString(value, textX, textY);
    	textY += lineHeight;
    }
    
    void drawSubScreen(int x, int y, int width, int height) {
    	g2.setColor(new Color(0, 0, 0, 210));
    	g2.fillRoundRect(x, y, width, height, 35, 35);
    	
    	g2.setColor(new Color(235, 235, 235, 235));
    	g2.setStroke(new BasicStroke(5));
    	g2.drawRoundRect(x, y, width, height, 25, 25);
    }

    public int getXforCenteredText(String text){ // để tọa độ x ở giữa màn hình
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth / 2 - length / 2;
        return x;
    }
    
    public int getXforAlignRightText(String text, int tailX){ // căn lề phải
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = tailX - length;
        return x;
    }

    
}

