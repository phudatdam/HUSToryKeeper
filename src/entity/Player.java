package entity;

import main.GamePanel;
import main.KeyHandler;
import object.OBJ_Coin;
import object.OBJ_Heart;
import object.SuperObject;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Player extends Entity {
	GamePanel gp;
    KeyHandler keyH;

    public final int screenX;
    public final int screenY;
    int standCounter = 0;
    
    public int hasHeart;
    public int strength;
    public int coin;
    
    public ArrayList<Entity> inventory = new ArrayList();
    public int maxInventorySize = 15;

    public Player(GamePanel gp, KeyHandler keyH) {
        super(gp);
        
        this.gp = gp; // DEBUG
        this.keyH = keyH;

        this.screenX = gp.screenWidth / 2 - gp.tileSize / 2;
        this.screenY = gp.screenHeight / 2 - gp.tileSize / 2;

        solidArea = new Rectangle(12, 24, 40, 40);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        setDefaultValues();
        getPlayerImage();
        setItems();
    }

    public void setDefaultValues() {
        worldX = gp.tileSize * 35;
        worldY = gp.tileSize * 13;
        speed = 5;
        direction = "down";
        
        maxLife = 6;
        life = maxLife;
        hasHeart = 0;
        strength = 0;
        coin = 0;
    }
    
    public void setItems() {
    	inventory.add(new OBJ_Coin(gp));
    	inventory.add(new OBJ_Coin(gp));
    }

    public void getPlayerImage(){
        up1 = setup("/player/player_up_1", gp.tileSize, gp.tileSize); //
		up2 = setup("/player/player_up_2", gp.tileSize, gp.tileSize);
		up3 = setup("/player/player_up_3", gp.tileSize, gp.tileSize);
		down1 = setup("/player/player_down_1", gp.tileSize, gp.tileSize);
		down2 = setup("/player/player_down_2", gp.tileSize, gp.tileSize);
		down3 = setup("/player/player_down_3", gp.tileSize, gp.tileSize);
		left1 = setup("/player/player_left_1", gp.tileSize, gp.tileSize);
		left2 = setup("/player/player_left_2", gp.tileSize, gp.tileSize);
		left3 = setup("/player/player_left_3", gp.tileSize, gp.tileSize);
		right1 = setup("/player/player_right_1", gp.tileSize, gp.tileSize);
		right2 = setup("/player/player_right_2", gp.tileSize, gp.tileSize);
		right3 = setup("/player/player_right_3", gp.tileSize, gp.tileSize);
    }

    public void update(){ // được gọi 60 lần trong 1s
        if(keyH.upPressed || keyH.downPressed || keyH.rightPressed || keyH.leftPressed || keyH.enterPressed){
            if(keyH.upPressed){
                direction = "up";
            }
            else if(keyH.downPressed){
                direction = "down";
            }
            else if (keyH.rightPressed){
                direction = "right";
            }
            else if(keyH.leftPressed){
                direction = "left";
            }

            // Check va chạm
            collisionOn = false;
            gp.cChecker.checkTile(this);
            
            // Check objects collision
            int objIndex = gp.cChecker.checkObject(this, true);
            pickUpObject(objIndex);
            
            int npcIndex = gp.cChecker.checkEntity(this, gp.npc);
            interactNPC(npcIndex);

            // Nếu collision = false, player có thể di chuyển
            if(collisionOn == false){
                switch (direction) {
                    case "up":
                        worldY -= speed;
                        break;
                    case "down":
                        worldY += speed;
                        break;
                    case "right":
                        worldX += speed;
                        break;
                    case "left":
                        worldX -= speed;
                        break;
                }
            }
            spriteCounter++;
            if(spriteCounter > 5){ // hình ảnh được thay đổi sau 6 khung hình
                if(spriteNum == 1){
                    spriteNum = 2;
                }
                else if(spriteNum == 2){
                    spriteNum = 3;
                }
                else if(spriteNum == 3){
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }
        else {
        	spriteNum = 1;
        }
    }

    public void pickUpObject(int i){
        if(i != 999){
            String objectName = gp.obj[i].name;
            switch (objectName){
                case "Heart":
                    hasHeart += 2;
                    gp.obj[i] = null;
                    break;
            }

        }
    }
    
    public void interactNPC(int i) {
    	if(i != 999){
    		    gp.gameState = gp.dialogueState;
                gp.npc[i].speak();
    	}
        gp.keyH.enterPressed = false;
    }
    
    public void draw(Graphics2D g2){
        BufferedImage image = null;

        switch (direction){
            case "up":
                if(spriteNum == 1){
                    image = up3;
                }
                if(spriteNum == 2){
                    image = up2;
                }
                if(spriteNum == 3){
                    image = up1;
                }
                break;
            case "down":
                if(spriteNum == 1){
                    image = down3;
                }
                if(spriteNum == 2){
                    image = down2;
                }
                if(spriteNum == 3){
                    image = down1;
                }
                break;
            case "right":
                if(spriteNum == 1){
                    image = right3;
                }
                if(spriteNum == 2){
                    image = right2;
                }
                if(spriteNum == 3){
                    image = right1;
                }
                break;
            default:
                if(spriteNum == 1){
                    image = left3;
                }
                if(spriteNum == 2){
                    image = left2;
                }
                if(spriteNum == 3){
                    image = left1;
                }
                break;
        }
        g2.drawImage(image, screenX, screenY, null);

    }
}