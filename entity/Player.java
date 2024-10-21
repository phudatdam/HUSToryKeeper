package entity;

import main.GamePanel;
import main.KeyHandler;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends Entity {
	GamePanel gp;
    KeyHandler keyH;
    public int npcIndex;
    public int objIndex;
    public final int screenX;
    public final int screenY;
    public int hasHeart = 2;
    int standCounter = 0;


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
    }

    public void setDefaultValues() {
        worldX = gp.tileSize * 24;
        worldY = gp.tileSize * 48;
        speed = 4;
        direction = "up";
        
        maxLife = 6;
        life = maxLife;
    }

    public void getPlayerImage(){
        up1 = setup("/player/player_up_1");
		up2 = setup("/player/player_up_2");
		up3 = setup("/player/player_up_3");
		down1 = setup("/player/player_down_1");
		down2 = setup("/player/player_down_2");
		down3 = setup("/player/player_down_3");
		left1 = setup("/player/player_left_1");
		left2 = setup("/player/player_left_2");
		left3 = setup("/player/player_left_3");
		right1 = setup("/player/player_right_1");
		right2 = setup("/player/player_right_2");
		right3 = setup("/player/player_right_3");
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
            objIndex = gp.cChecker.checkObject(this, true);
            pickUpObject(objIndex);
            npcIndex = gp.cChecker.checkEntity(this, gp.npc);
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
