package entity;

import main.GamePanel;
import main.KeyHandler;
//import object.OBJ_Axe;
//import object.OBJ_Coin;
//import object.OBJ_Heart;
//import object.OBJ_Iron;
import object.OBJ_Sword;
//import object.OBJ_Wood;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Player extends Entity {
	GamePanel gp;
    KeyHandler keyH;

    public final int screenX;
    public final int screenY;
    int standCounter = 0;

    public int coin = 0;
    public int defense = 0;
    public int strength = 1;
    public int coink = 0;
    public int iron = 0;
    public int wood = 0;
    public int sword = 0;
    public int axe = 0;
    public int pickaxe = 0;
    public Entity currentWeapon;
    
    public ArrayList<Entity> inventory = new ArrayList();
    public int maxInventorySize = 15;

    public Player(GamePanel gp, KeyHandler keyH) {
        super(gp);
        
        this.gp = gp;
        this.keyH = keyH;

        this.screenX = gp.screenWidth / 2 - gp.tileSize / 2;
        this.screenY = gp.screenHeight / 2 - gp.tileSize / 2;

        solidArea = new Rectangle(12, 24, 40, 40);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        
        attackArea.width = 48;
        attackArea.height = 48;

        setDefaultValues();
        getPlayerImage();
        getPlayerAttackImage();
        setItems();
    }

    public void setDefaultValues() {
        worldX = gp.tileSize * 35;
        worldY = gp.tileSize * 13;
        speed = 5;
        direction = "down";
        
        maxLife = 6;
        life = maxLife;
        strength = 1;
        defense = 0;
        currentWeapon = new OBJ_Sword(gp);
    }
    
    public void setItems() {
        inventory.add(currentWeapon);
    }

    public void getPlayerImage(){
        up1 = setup("/player/player_up_1", gp.tileSize, gp.tileSize);
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
    
    public void getPlayerAttackImage(){
        if(currentWeapon.type == TYPE_sword){
            attackUp1 = setup("/player/player_attack_up_1", gp.tileSize, gp.tileSize * 2);
            attackUp2 = setup("/player/player_attack_up_2", gp.tileSize, gp.tileSize * 2);
	    	attackDown1 = setup("/player/player_attack_down_1", gp.tileSize, gp.tileSize * 2);
	    	attackDown2 = setup("/player/player_attack_down_2", gp.tileSize, gp.tileSize * 2);
	    	attackLeft1 = setup("/player/player_attack_left_1", gp.tileSize * 2, gp.tileSize);
	    	attackLeft2 = setup("/player/player_attack_left_2", gp.tileSize * 2, gp.tileSize);
	    	attackRight1 = setup("/player/player_attack_right_1", gp.tileSize * 2, gp.tileSize);
	    	attackRight2 = setup("/player/player_attack_right_2", gp.tileSize * 2, gp.tileSize);
        }
        if(currentWeapon.type == TYPE_axe){
            attackUp1 = setup("/player/player_axe_up_1", gp.tileSize, gp.tileSize * 2);
          attackUp2 = setup("/player/player_axe_up_2", gp.tileSize, gp.tileSize * 2);
		    attackDown1 = setup("/player/player_axe_down_1", gp.tileSize, gp.tileSize * 2);
		    attackDown2 = setup("/player/player_axe_down_2", gp.tileSize, gp.tileSize * 2);
		    attackLeft1 = setup("/player/player_axe_left_1", gp.tileSize * 2, gp.tileSize);
	    	attackLeft2 = setup("/player/player_axe_left_2", gp.tileSize * 2, gp.tileSize);
	    	attackRight1 = setup("/player/player_axe_right_1", gp.tileSize * 2, gp.tileSize);
	    	attackRight2 = setup("/player/player_axe_right_2", gp.tileSize * 2, gp.tileSize);
        }
        if(currentWeapon.type == TYPE_pickaxe){
            attackUp1 = setup("/player/player_pickaxe_up_1", gp.tileSize, gp.tileSize * 2);
            attackUp2 = setup("/player/player_pickaxe_up_2", gp.tileSize, gp.tileSize * 2);
		    attackDown1 = setup("/player/player_pickaxe_down_1", gp.tileSize, gp.tileSize * 2);
	    	attackDown2 = setup("/player/player_pickaxe_down_2", gp.tileSize, gp.tileSize * 2);
		    attackLeft1 = setup("/player/player_pickaxe_left_1", gp.tileSize * 2, gp.tileSize);
		    attackLeft2 = setup("/player/player_pickaxe_left_2", gp.tileSize * 2, gp.tileSize);
		    attackRight1 = setup("/player/player_pickaxe_right_1", gp.tileSize * 2, gp.tileSize);
		    attackRight2 = setup("/player/player_pickaxe_right_2", gp.tileSize * 2, gp.tileSize);
        }
    }

    public void update(){ // được gọi 60 lần trong 1s
    	if (keyH.attackPressed) {
    		attacking = true;
    	}
    		
    	if (attacking == true) {
    		attacking();
    	} else if (keyH.upPressed || keyH.downPressed || keyH.rightPressed || keyH.leftPressed || keyH.enterPressed) {
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
            
            // Check NPCs collision
            int npcIndex = gp.cChecker.checkEntity(this, gp.npc);
            interactNPC(npcIndex);

            // Check monsters collision
            int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
            contactMonster(monsterIndex);
            
            // Nếu collision = false, player có thể di chuyển
            if(collisionOn == false && keyH.enterPressed == false){
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
        
        if (invincible == true) {
        	invincibleCounter++;
        	if (invincibleCounter > 60) {
        		invincible = false;
        		invincibleCounter = 0;
        	}
        }
    }

    public void attacking() {
    	spriteCounter++;
    	
    	if (spriteCounter <= 5) {
    		spriteNum = 1;
    	}
    	if (spriteCounter > 5 && spriteCounter <= 25) {
    		spriteNum = 2;
    		
    		// Save the current worldX, worldY, solidArea
    		int currentWorldX = worldX;
    		int currentWorldY = worldY;
    		int solidAreaWidth = solidArea.width;
    		int solidAreaHeight = solidArea.height;
    		
    		// Adjust player worldX/worldY for the attackArea
    		switch(direction) {
    			case("up"): worldY -= attackArea.height; 
    			case("down"): worldY += attackArea.height; 
    			case("left"): worldY -= attackArea.width;
    			case("right"): worldY += attackArea.width; 
    		}
    		
    		// Change solidArea to the attackArea
    		solidArea.width = attackArea.width;
    		solidArea.height = attackArea.height;
    		
    		// Check monster collision with the updated worldX, worldY and solidArea
    		int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
    		damageMonster(monsterIndex);
    		
    		// Restore the original data
    		worldX = currentWorldX;
    		worldY = currentWorldY;
    		solidArea.width = solidAreaWidth; 
    		solidArea.height = solidAreaHeight;
    	}
    	if (spriteCounter > 25) {
    		spriteNum = 1;
    		spriteCounter = 0;
    		attacking = false;
    	}
    }
    
    // Tương tác với vật phẩm
	public void pickUpObject(int i){
        if(i != 999){
                // Nhặt được tim => hồi máu
                if(gp.obj[gp.currentMap][i].name == "Heart")
                {
                    life +=2;
                    maxLife +=2;
                    gp.obj[gp.currentMap][i]=null;
                }
                // Chuyển đến map tiếp theo khi chạm vào giếng
                else if ( gp.obj[gp.currentMap][i].name == "Well")
                {
                    if (coink == 1) {
                		coink = 0;
                		inventory.removeIf( item -> item.name.equals("Đồng xu"));
                		teleport();
                    }
                }
                // Nhặt gỗ, sắt
                else
                {
                    if( canObtainItem(gp.obj[gp.currentMap][i]) == true)
                    {
                        gp.playSE(1);
                        if( gp.obj[gp.currentMap][i].name == "Sắt")iron++;
                        if( gp.obj[gp.currentMap][i].name == "Gỗ")wood++;
                    }
                    gp.obj[gp.currentMap][i]=null;
                    
                }
        }
    }
    
    public void interactNPC(int i) {
    	if(i != 999) {
    		    gp.gameState = gp.dialogueState;
                gp.npc[gp.currentMap][i].speak();
        }
        gp.keyH.enterPressed=false;
    }
    
    // Player tiếp xúc với quái => nhận sát thương
    public void contactMonster(int i) {
    	if(i != 999){
    		if (invincible == false && life > 0) {
        		life -= 1;
        		invincible = true;
    		}
    	}	
	}
    
    // Đánh thường => gây sát thương
    public void damageMonster(int i) {  
    	if(i != 999){
    		if (gp.monster[gp.currentMap][i].invincible == false) {
    			gp.monster[gp.currentMap][i].life -= 1;
    			gp.monster[gp.currentMap][i].invincible = true;
    			
    			if (gp.monster[gp.currentMap][i].life <= 0) {
    				gp.monster[gp.currentMap][i] = null;
    			}
    		}
    	}
    }
    
    public void selectItem(){
        int itemIndex = gp.ui.getItemIndexOnSlot();
        if( itemIndex < inventory.size())
        {
            Entity selectedItem = inventory.get(itemIndex);
            if( selectedItem.type == TYPE_sword || selectedItem.type == TYPE_axe || selectedItem.type == TYPE_pickaxe)
            {
                currentWeapon= selectedItem;
                getPlayerAttackImage();
            }
            if( selectedItem.type == TYPE_consumable)
            {
                //update
            }
        }
    }

    public int SearchItemInInventoty(String itemName) {
        int itemIndex = 999;
        for(int i = 0 ; i < inventory.size() ; i++ )
        {
            if(inventory.get(i).name.equals(itemName))
            {
                itemIndex = i;
                break;
            }
        }
        return itemIndex;
    }

    public boolean canObtainItem(Entity item )
    {
        boolean canobtain = false;
        // stackable ?
        if(item.stackeable == true)
        {
            int index = SearchItemInInventoty(item.name);
            if( index != 999)
            {
                inventory.get(index).amount++;
                canobtain = true;
            }
            else
            {
                inventory.add(item);
                canobtain = true;
            }
        }
        return canobtain;

    }

    public void teleport() {
    	gp.currentMap++;
    	int col = 0;
    	int row = 0;
    	switch (gp.currentMap) {
		    case 1:
			    col = 35;
			    row = 15;
			    break;
		    case 2:
		    	col = 15;
		    	row = 12;
		    	break;
    	}
    	worldX = gp.tileSize * col;
    	worldY = gp.tileSize * row;
    }

    public void draw(Graphics2D g2){
        BufferedImage image = null;
        int tempScreenX = screenX;
        int tempScreenY = screenY;        

        switch (direction){
            case "up":
            	if (attacking == false) {
                    if(spriteNum == 1) image = up3;
                    if(spriteNum == 2) image = up2;
                    if(spriteNum == 3) image = up1;
            	}
            	if (attacking == true) {
            		tempScreenY = screenY - gp.tileSize;
                    if(spriteNum == 1) image = attackUp1;
                    if(spriteNum == 2) image = attackUp2;
            	}
                break;
            case "down":
            	if (attacking == false) {
            		if(spriteNum == 1) image = down3;
                    if(spriteNum == 2) image = down2;
                    if(spriteNum == 3) image = down1;
            	}
            	if (attacking == true) {
                    if(spriteNum == 1) image = attackDown1;
                    if(spriteNum == 2) image = attackDown2;
            	}             
                break;
            case "right":
            	if (attacking == false) {
            		if(spriteNum == 1) image = right3;
                    if(spriteNum == 2) image = right2;
                    if(spriteNum == 3) image = right1;
            	}
            	if (attacking == true) {
                    if(spriteNum == 1) image = attackRight1;
                    if(spriteNum == 2) image = attackRight2;
            	}
                break;
            default:
            	if (attacking == false) {
                    if(spriteNum == 1) image = left3;
                    if(spriteNum == 2) image = left2;
                    if(spriteNum == 3) image = left1;
            	}
            	if (attacking == true) {
            		tempScreenX = screenX - gp.tileSize;
                    if(spriteNum == 1) image = attackLeft1;
                    if(spriteNum == 2) image = attackLeft2;
            	}
                break;
        }
        
        // Hiệu ứng chịu sát thương từ quái
        if (invincible == true) {
        	g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.4f));
        }
        
        g2.drawImage(image, tempScreenX, tempScreenY, null);
        
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
    }
}