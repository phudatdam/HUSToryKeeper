package main;

import object.OBJ_Heart;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import entity.Entity;

public class UI {
    GamePanel gp;
    Graphics2D g2;
    Font determinationSans, retron2000, maruMonica;
    BufferedImage heart_full, heart_half, heart_blank;
    BufferedImage heartImage;
    public boolean messageOn = false;
    //public String message = "";
    //int messageCounter = 0;
    ArrayList<String> message = new ArrayList<>();
    ArrayList<Integer> messageCounter = new ArrayList<>();
    public boolean gameFinished = false;
    public String currentDialogue = "";
    public int commandNum = 0;
    public int slotCol = 0;
    public int slotRow = 0;
    public Entity npc;
    int subState = 0;
    int pauState = 0;

    public UI(GamePanel gp){
        this.gp = gp;

        try {
            InputStream is = getClass().getResourceAsStream("/font/SVN-Determination Sans.otf");
            determinationSans = Font.createFont(Font.TRUETYPE_FONT, is);
            InputStream is1 = getClass().getResourceAsStream("/font/SVN-Retron 2000.otf");
            retron2000 = Font.createFont(Font.TRUETYPE_FONT, is1);
            InputStream is2 = getClass().getResourceAsStream("/font/SVN-MaruMonica.ttf");
            maruMonica = Font.createFont(Font.TRUETYPE_FONT, is2);
        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        OBJ_Heart heart = new OBJ_Heart(gp);
        heartImage = heart.image1;

        heart_full = heart.image1;
        heart_half = heart.image2;
        heart_blank = heart.image3;
    }

    public void addMessage(String text){
        message.add(text);
        messageCounter.add(0);
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
            drawPlayerLife();
            drawMessage();
        }

        // PAUSE STATE
        if(gp.gameState == gp.pauseState){
            drawPlayerLife();
            drawPauseScreen();
        }

        // DIALOGUE STATE
        if(gp.gameState == gp.dialogueState){
            drawPlayerLife();
            drawDialogueState();
        }

        // CHARACTER STATE: MISSIONS + INVENTORY
        if(gp.gameState == gp.characterState) {
            drawCharacterScreen();
            drawInventory();
        }

        // OPTIONS STATE
        if(gp.gameState == gp.optionsState){
            drawOptionsState();
        }
        // Game over STATE
        if(gp.gameState == gp.gameoverState){
            drawOverScreen();
        }
    }

    public void drawOverScreen(){
        g2.setColor( new Color(0,0,0,150));
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

        int x;
        int y;
        String text;
        g2.setFont(retron2000);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,80));
        text = "Haiza, thua rồi";
        // shadow
        g2.setColor(Color.BLACK);
        x = getXforCenteredText(text);
        y = gp.tileSize * 4;
        g2.drawString(text, x, y);
        // main
        g2.setColor(Color.WHITE);
        x = getXforCenteredText(text);
        y = gp.tileSize * 4;
        g2.drawString(text, x-4, y-4);
        // vài câu cổ động
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,24));
        y += gp.tileSize;
        // random 
        if(gp.player.randomtext < 10){
           text = "Bạn thua à, lạ nhỉ, trò này dễ thế mà ?";
        }
        else if(gp.player.randomtext < 20){
           text = "Thôi nào bạn qua hết mấy môn đại cương được cơ mà.";
            }
        else{
            text = "“Sinh tồn” ở bách khoa khó hơn mà, cố lên";
        }
        x = getXforCenteredText(text);
        g2.drawString(text , x, y);
        // retry
        text = "Thử lại nào";
        x = getXforCenteredText(text);
        y += gp.tileSize;
        g2.drawString(text , x, y);
        if( commandNum == 0)
        {
            g2.drawString(">", x-40, y);
        }
        // nghỉ
        text = "Thôi vậy";
        x = getXforCenteredText(text);
        y += gp.tileSize;
        g2.drawString(text , x, y);
        if( commandNum == 1)
        {
            g2.drawString(">", x-40, y);
        }
    }
    

    private void drawOptionsState() {
        g2.setColor(Color.white);
        g2.setFont(retron2000);
        g2.setFont(g2.getFont().deriveFont(23f));

        // SUB WINDOW
        int frameX = gp.tileSize * 5;
        int frameY = gp.tileSize;
        int frameWidth = gp.tileSize * 6;
        int frameHeight = gp.tileSize * 8;

        drawSubWindow(frameX, frameY, frameWidth, frameHeight);

        switch (subState){
            case 0: options_top(frameX, frameY); break;
            case 1: options_control(frameX, frameY); break;
            case 2: options_endGame(frameX, frameY); break;

        }
        gp.keyH.enterPressed = false;
    }

    public void options_top(int frameX, int frameY){
        int textX;
        int textY;

        // TITLE
        g2.setFont(determinationSans);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,32f));
        String text = "CÀI ĐẶT";
        textX = getXforCenteredText(text);
        textY = frameY + gp.tileSize;
        g2.drawString(text, textX, textY);

        // MUSIC
        g2.setFont(retron2000); // set font
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,24f));

        textX = frameX + gp.tileSize / 2 + 20;
        textY += gp.tileSize;
        g2.drawString("Âm nhạc", textX, textY);

        if(commandNum == 1){
            g2.drawString(">", textX - 30, textY);
        }

        // SE
        textY += gp.tileSize;
        g2.drawString("Âm thanh", textX, textY);
        if(commandNum == 2){
            g2.drawString(">", textX - 30, textY);
        }

        // CONTROL
        textY += gp.tileSize;
        g2.drawString("Hướng dẫn chơi", textX, textY);
        if(commandNum == 3){
            g2.drawString(">", textX - 30, textY);
            if(gp.keyH.enterPressed == true){
                subState = 1;
                commandNum = 0;
            }
        }

        // END GAME
        textY += gp.tileSize;
        g2.drawString("Thoát game", textX, textY);
        if(commandNum == 4){
            g2.drawString(">", textX - 30, textY);
            if(gp.keyH.enterPressed == true){
                subState = 2;
                commandNum = 0;
            }
        }

        // BACK
        textY += gp.tileSize * 2;
        g2.drawString("Quay lại", textX, textY);
        if(commandNum == 5){
            g2.drawString(">", textX - 30, textY);
            if(gp.keyH.enterPressed == true){
                if(pauState == 1){
                    pauState = 0;
                    gp.gameState = gp.pauseState;
                    commandNum = 1;
                }
                else if(subState == 0){
                    gp.gameState = gp.titleState;
                    commandNum = 1;
                }
            }
        }

        // MUSIC VOLUME
        textX = frameX + gp.tileSize * 3 + 6;
        textY = frameY + gp.tileSize * 2 - 23;
        g2.setStroke(new BasicStroke(3));
        g2.drawRect(textX, textY, 150, 23); // 150/5 = 30
        int volumeWidth = 30 * gp.music.volumeScale;
        g2.fillRect(textX, textY, volumeWidth, 23);

        // SE
        textY += gp.tileSize;
        g2.drawRect(textX, textY, 150, 23);
        volumeWidth = 30 * gp.se.volumeScale;
        g2.fillRect(textX, textY, volumeWidth, 23);
    }

    public void options_control(int frameX, int frameY){
        int textX;
        int textY;

        // TITLE
        String text = "HƯỚNG DẪN CHƠI";
        g2.setFont(g2.getFont().deriveFont(Font.BOLD));
        textX = getXforCenteredText(text);
        textY = frameY + gp.tileSize;
        g2.drawString(text, textX, textY);

        textX = frameX + gp.tileSize / 2 - 15;
        textY += gp.tileSize;
        g2.setFont(retron2000);
        g2.setFont(g2.getFont().deriveFont(17f));
        g2.drawString("Di chuyển:", textX, textY); textY += gp.tileSize;
        g2.drawString("Chọn option/ mở hộp thoại:", textX, textY); textY += gp.tileSize;
        g2.drawString("Tấn công/ Sử dụng công cụ:", textX, textY); textY += gp.tileSize;
        g2.drawString("Tạm dừng:", textX, textY); textY += gp.tileSize;
        g2.drawString("Mở ba lô:", textX, textY); textY += gp.tileSize;

        textX = frameX + gp.tileSize * 5 - 32;
        textY = frameY + gp.tileSize * 2;
        g2.drawString("W A S D", textX, textY); textY += gp.tileSize;
        g2.drawString("E", textX + 16, textY); textY += gp.tileSize;
        g2.drawString("J", textX + 16, textY); textY += gp.tileSize;
        g2.drawString("P", textX + 16, textY); textY += gp.tileSize;
        g2.drawString("I", textX + 16, textY); textY += gp.tileSize;

        // BACK
        textX = frameX + gp.tileSize / 2 - 15;
        textY = frameY + gp.tileSize * 7;
        g2.drawString("Quay lại", textX + 20, textY);
        if(commandNum == 0){
            g2.drawString(">", textX - 10, textY);
            if(gp.keyH.enterPressed == true){
                subState = 0;
                commandNum = 3;
            }
        }
    }

    public void options_endGame(int frameX, int frameY){
        int x = frameX + 5;
        int y = frameY + gp.tileSize * 3 / 2;

        g2.drawImage(gp.player.down3, x, y, 40, 40, null);
        int textX = frameX + gp.tileSize - 20;
        int textY = frameY + gp.tileSize * 2;
        currentDialogue = ": Game hay như này mà nỡ/n thoát à :)??";

        for(String line: currentDialogue.split("/n")){
            g2.drawString(line, textX, textY);
            textY += 40;
        }

        // Chơi tiếp
        String text = "Nghĩ lại rồi, chơi tiếp :D";
        textX = getXforCenteredText(text);
        textY += gp.tileSize * 5 / 2;
        g2.drawString(text, textX,textY);
        if(commandNum == 0){
            g2.drawString(">", textX - 30, textY);
            if(gp.keyH.enterPressed == true){
                subState = 0;
                commandNum = 4;
            }
        }


        // Thoát
        text = "Ừ =.=";
        textX = getXforCenteredText(text);
        textY += gp.tileSize;
        g2.drawString(text, textX,textY);
        if(commandNum == 1){
            g2.drawString(">", textX - 30, textY);
            if(gp.keyH.enterPressed == true){
                System.exit(0);
            }
        }

    }

    public void drawMessage()
    {
        int messX = gp.tileSize;
        int messY = gp.tileSize*4;
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN,24));
        for( int i = 0; i < message.size() ; i++)
        {
            if( message.get(i) != null)
            {
                g2.setColor(Color.black);
                g2.drawString(message.get(i), messX+2, messY+2);
                g2.setColor(Color.white);
                g2.drawString(message.get(i), messX, messY);

                int counter = messageCounter.get(i) + 1;
                messageCounter.set(i,counter);
                messY += 32;

                if(messageCounter.get(i) > 120)
                {
                    message.remove(i);
                    messageCounter.remove(i);
                }
            }
        }
    }

    public void drawPlayerLife() {
        int x = gp.tileSize / 2;
        int y = gp.tileSize / 2;
        int i = 0;

        // DRAW MAX LIFE
        while (i < gp.player.maxLife / 2) {
            g2.drawImage(heart_blank, x, y, null);
            i++;
            x += gp.tileSize;
        }

        // RESET
        x = gp.tileSize / 2;
        y = gp.tileSize / 2;
        i = 0;

        // DRAW CURRENT LIFE
        while (i < gp.player.life) {
            g2.drawImage(heart_half, x, y, null);
            i++;
            if (i < gp.player.life) {
                g2.drawImage(heart_full, x, y, null);
            }
            i++;
            x += gp.tileSize;
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
        text = "BẮT ĐẦU CHƠI";
        x = getXforCenteredText(text);
        y += (int) gp.tileSize * 3;
        g2.drawString(text, x ,y);
        if(commandNum == 0){
            g2.drawString(">", x - gp.tileSize, y);
        }

        text = "CÀI ĐẶT";
        x = getXforCenteredText(text);
        y += (int) gp.tileSize * 1.5;
        g2.drawString(text, x ,y);
        if(commandNum == 1){
            g2.drawString(">", x - gp.tileSize, y);
        }

        text = "THOÁT";
        x = getXforCenteredText(text);
        y += (int) gp.tileSize * 1.5;

        g2.drawString(text, x ,y);
        if(commandNum == 2){
            g2.drawString(">", x - gp.tileSize, y);
        }

        g2.setFont(g2.getFont().deriveFont(Font.BOLD,25F));
        text = "(Nhấn E để chọn)";
        x = getXforCenteredText(text);
        y += gp.tileSize;
        g2.drawString(text, x ,y);
    }

    public void drawPauseScreen(){
        g2.setColor(Color.white);
        g2.setFont(retron2000);
        g2.setFont(g2.getFont().deriveFont(23f));

        // SUB WINDOW
        int frameX = gp.tileSize * 4;
        int frameY = gp.tileSize * 5 / 2;
        int frameWidth = gp.tileSize * 8;
        int frameHeight = gp.tileSize * 4;

        drawSubWindow(frameX, frameY, frameWidth, frameHeight);

        switch (pauState){
            case 0: ; pause_0(frameX, frameY); break;
            case 1:
                gp.gameState = gp.optionsState;
                break;
        }
        gp.keyH.enterPressed = false;
    }

    public void pause_0(int frameX, int frameY){
        int textX;
        int textY;

        // Pause screen
        g2.setFont(determinationSans);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,32f));
        String text = "TẠM DỪNG";
        textX = getXforCenteredText(text);
        textY = frameY + gp.tileSize;
        g2.drawString(text, textX, textY);

        // Continue
        g2.setFont(retron2000); // set font
        g2.setFont(g2.getFont().deriveFont(25f));

        textX = frameX + gp.tileSize / 2;
        textY += gp.tileSize;
        g2.drawString("Tiếp tục chơi", textX, textY);
        if(commandNum == 0){
            g2.drawString(">", textX - 20, textY);
            if(gp.keyH.enterPressed == true){
                gp.gameState = gp.playState;
            }
        }

        // Setting
        textY += gp.tileSize;
        g2.drawString("Cài đặt", textX, textY);
        if(commandNum == 1){
            g2.drawString(">", textX - 20, textY);
            if(gp.keyH.enterPressed == true){
                pauState = 1;
                commandNum = 1;
            }
        }
    }

    public void drawDialogueState(){
        // WINDOW
        int x = gp.tileSize * 2;
        int y = gp.tileSize / 2;
        int width = gp.screenWidth - (gp.tileSize * 4);
        int height = gp.tileSize * 4;
        drawSubWindow(x, y, width, height);
        // dialogue
        g2.setFont(retron2000);
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN,24F));
        x += gp.tileSize;
        y += gp.tileSize;
        if(npc.dialogues[npc.dialogueSet][npc.dialogueIndex] != null){
            currentDialogue=npc.dialogues[npc.dialogueSet][npc.dialogueIndex];
            if(gp.keyH.enterPressed == true)
            {
                if(gp.gameState == gp.dialogueState || gp.gameState == gp.cutsceneState){
                    npc.dialogueIndex++;
                    gp.keyH.enterPressed=false;
                }
            }
        }
        else{
            npc.dialogueIndex --;
            if(gp.gameState==gp.dialogueState) {
                gp.gameState=gp.playState;
            }
            if(gp.gameState==gp.cutsceneState)
            {
                gp.csManager.scenePhase++;
            }
            npc.diaEnd=true;
        }
        for ( String line : currentDialogue.split("\n")) {
            g2.drawString(line, x, y);
            y += 40;
        };

        g2.setFont(retron2000);
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 20f));
        g2.drawString("Nhấn E để tiếp tục >>", width - gp.tileSize * 2, height);

    }

    public void drawCharacterScreen() {
        // FRAME
        int frameX = gp.tileSize*1;
        int frameY = gp.tileSize*1;
        int frameWidth = gp.tileSize*5;
        int frameHeight = gp.tileSize*7;
        int textX = frameX + 9;
        int textY = frameY + 24;
        drawSubWindow(frameX, frameY, frameWidth, frameHeight);

        // TEXT
        g2.setFont(retron2000);
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN,20F));
    	int lineHeight = 20 + 6; // độ cao dòng = cỡ font + khoảng cách các dòng
    			
    	// names
    	g2.drawString("Hp:", textX, textY);
        textY += lineHeight;
        g2.drawString("Sức mạnh:", textX, textY);
        textY += lineHeight;
        g2.drawString("Phòng thủ:", textX, textY);
        textY += lineHeight;
        g2.drawString("Cấp độ:", textX, textY);
        textY += lineHeight;
        g2.drawString("kinh nghiệm:", textX, textY);
        textY += 2*lineHeight;
        g2.drawString("Đang dùng", textX + 48, textY);
        textY += lineHeight;

        // values
        textY = frameY + 24; // reset textY
        int tailX = frameX + frameWidth - 9;
        String value;

        value = String.valueOf(gp.player.life + "/" + String.valueOf(gp.player.maxLife));
        textX = getXforAlignRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.getAttack());
        textX = getXforAlignRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.defense);
        textX = getXforAlignRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.Lv);
        textX = getXforAlignRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.exp +" / "+gp.player.expNeed);
        textX = getXforAlignRightText(value, tailX);
        g2.drawString(value, textX, textY);
        g2.drawImage(gp.player.currentWeapon.image1, tailX - gp.tileSize - 48, textY + 8, null);
        textY += 4*lineHeight;
        textX = 23 + (frameX + frameWidth)/2
                - (int)g2.getFontMetrics().getStringBounds("NHIỆM VỤ", g2).getWidth()/2;
        textY += 6;
        g2.setFont(g2.getFont().deriveFont(24F)); // to hơn tí
        g2.drawString("NHIỆM VỤ", textX, textY);
        textY += lineHeight + 2;
        g2.setFont(g2.getFont().deriveFont(20F)); // reset
        textX = frameX + 9;
        switch (gp.currentMap) {
            case 0:
                g2.drawString("Ra ngoài mua nước uống đi", textX, textY);
                textY += lineHeight;
                break;
		    case 1:
                drawQuest(value, textX, textY, lineHeight, tailX);
			    break;
		    case 2:
                drawQuest(value, textX, textY, lineHeight, tailX);
		    	break;
		    case 3:
                drawQuest(value, textX, textY, lineHeight, tailX);
		    	break;
    	}
        
    }

    public void drawQuest(String value,int textX, int textY, int lineHeight, int tailX){
        g2.drawString("Kiếm đủ tài nguyên yêu cầu", textX, textY);
        textY += lineHeight;
        g2.drawString("Yêu cầu Gỗ", textX, textY);
        textY += lineHeight;
        g2.drawString("Yêu cầu sắt", textX, textY);
        textY -= lineHeight;
        value = String.valueOf(gp.player.wood + " / " + gp.npc[gp.currentMap][0].woodneed);
    	textX = getXforAlignRightText(value, tailX);
    	g2.drawString(value, textX, textY);
    	textY += lineHeight;
        value = String.valueOf(gp.player.iron + " / " + gp.npc[gp.currentMap][0].ironneed);
    	textX = getXforAlignRightText(value, tailX);
    	g2.drawString(value, textX, textY);
    	textY += lineHeight;
    }

    public void drawInventory() {
        // FRAME
        int frameX = gp.tileSize*9;
        int frameY = gp.tileSize*1;
        int frameWidth = gp.tileSize*6 - 20;
        int frameHeight = gp.tileSize*4 - 20;
        drawSubWindow(frameX, frameY, frameWidth, frameHeight);

        // SLOT
        int slotXStart = frameX + 20;
        int slotYStart = frameY + 20;
        int slotX = slotXStart;
        int slotY = slotYStart;

        // DRAW ITEMS
        for(int i = 0; i < gp.player.inventory.size(); i++)
        {
            //Equip cursor
            if(gp.player.inventory.get(i) == gp.player.currentWeapon){
                g2.setColor(new Color(240,190,90));
                g2.fillRoundRect( slotX, slotY, gp.tileSize, gp.tileSize, 10 , 10);
            }
    		g2.drawImage(gp.player.inventory.get(i).image1, slotX, slotY, null);
    		slotX += gp.tileSize;
    		
    		
            if(gp.player.inventory.get(i).amount > 1)
            {
                g2.setFont(g2.getFont().deriveFont(32f));
                int amountX;
                int amountY;
                String s = ""+ gp.player.inventory.get(i).amount;
                amountX = getXforAlignRightText( s , slotX );
                amountY = slotY + gp.tileSize;
                
                //shadow 
                g2.setColor(new Color(60,60,60));
                g2.drawString(s, amountX, amountY);
                // number
                g2.setColor(Color.white);
                g2.drawString(s , amountX-3 , amountY-3);
            }

            if(i == 4 || i == 9 || i == 14) {
    			slotX = slotXStart;
    			slotY += gp.tileSize;
    		}
    	}
    	
    	// CURSOR
    	int cursorX = slotXStart + (gp.tileSize * slotCol);
    	int cursorY = slotYStart + (gp.tileSize * slotRow);
    	
    	// DRAW CURSOR
    	g2.setColor(Color.white);
    	g2.setStroke(new BasicStroke(3));
    	g2.drawRoundRect(cursorX, cursorY, gp.tileSize, gp.tileSize, 25, 25);
    	
    	// DESCRIPTION
    	int dFrameX = frameX;
    	int dFrameY = frameY + frameHeight + 20;
    	int dFrameWidth = frameWidth;
    	int dFrameHeight = frameHeight - 50;
    	
    	int dTextX = dFrameX + 10;
    	int dTextY = dFrameY + 29;
    	g2.setFont(retron2000);
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN,16F));
        int itemIndex = getItemIndexOnSlot();
        if(itemIndex < gp.player.inventory.size()) {
            drawSubWindow(dFrameX, dFrameY, dFrameWidth, dFrameHeight);
            for(String line : gp.player.inventory.get(itemIndex).description.split("\n"))
            { // vì drawString bỏ qua xuống dòng
                g2.drawString(line, dTextX, dTextY);
                dTextY += 28; // xuống dòng
            }
        }
    }

    void drawSubWindow(int x, int y, int width, int height) {
        g2.setColor(new Color(0, 0, 0, 210));
        g2.fillRoundRect(x, y, width, height, 35, 35);

        g2.setColor(new Color(235, 235, 235, 235));
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x, y, width, height, 25, 25);
    }

    public int getItemIndexOnSlot() {
        int itemIndex = slotCol + (slotRow*5);
        return itemIndex;
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