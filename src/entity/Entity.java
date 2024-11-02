package entity;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.UtilityTool;

public class Entity { // lớp cha cho các lớp khác: nhân vật, NPC, monster, object...
	public GamePanel gp;

	// ENTITY IMAGE
	public BufferedImage up1, up2, up3, down1, down2, down3, left1,
			left2, left3, right1, right2, right3;
	public String direction = "down"; //
	public BufferedImage attackUp1, attackUp2, attackDown1, attackDown2,
			attackLeft1, attackLeft2, attackRight1, attackRight2;
	public BufferedImage image1, image2, image3;
	public Rectangle solidArea = new Rectangle(0, 0, 64, 64);
	public Rectangle attackArea = new Rectangle(0, 0, 0, 0);
	public int solidAreaDefaultX = solidArea.x;
	public int solidAreaDefaultY = solidArea.y;

	// ENTITY STATUS
	public int worldX, worldY;
    public int spriteNum = 1;
    public boolean collisionOn = false;
    public boolean invincible = false;
    public boolean collision = false;
    public boolean attacking = false;
    public boolean onPath = false;
    public boolean alive = true; // trạng thái mũi tên còn đang bay ko
    public boolean dying = false;
    public boolean hpBarOn = false;
    public boolean knockBack = false;
    public String knockBackDirection;
    
    // DIALOGUES
    public String dialogues[][] = new String[50][50];
    public int dialogueIndex = 0;
    public int dialogueSet = 0;
	public boolean diaEnd = false;
	public int woodneed ;
	public int ironneed ;

    // ENTITY COUNTERS
    public int spriteCounter = 0;
    public int actionLockCounter = 0;
    public int invincibleCounter = 0; 
    int dyingCounter = 0;
    int hpBarCounter = 0;
    public int shotAvailableCounter = 0;
	int knockBackCounter = 0;
 
    // ENTITY ATTRIBUTES
    public int type; // 0=player, 1=npc, 2=monsters
    public final int TYPE_PLAYER = 0;
    public final int TYPE_NPC = 1;
    public final int TYPE_MONSTER = 2;
	public final int TYPE_sword = 3;
	public final int TYPE_axe = 4;
	public final int TYPE_pickaxe = 5;
	public final int TYPE_consumable = 6;

    public String name;
    public String description;
    public int defaultSpeed;
    public int speed;
    public int maxLife;
    public int life;
	public int attack;
	public int defense;
	public int motion1_duration;
	public int motion2_duration;
    public Projectile projectile;
    public int attack;
    public Projectile projectile;
    public Entity currentWeapon;
	public boolean stackeable =false;
	public int amount = 1;

	public Entity(GamePanel gp) {
		this.gp = gp;
	}	
	public void setAction() {
		getRandomDirection();
	}	
	public void damageReaction() {
		
	}	
	public void speak() {}	
    public void startDialogue(Entity entity, int setNum){
        gp.gameState = gp.dialogueState;
        gp.ui.npc = entity;
        dialogueSet = setNum;
    }    
    // quay mặt npc ra chỗ mình	
    public void facePlayer(){
        switch( gp.player.direction) {
			case "up":
			direction = "down";
			break;
			case "down":
			direction = "up";
			break;
			case "right":
			direction = "left";
			break;
			case "left":
			direction = "right";
			break;

	public void checkDrop(){}

	public void dropItem (Entity droppedItem){
		for(int i = 0; i < gp.obj[1].length; i++){
			if(gp.obj[gp.currentMap][i] == null){
				gp.obj[gp.currentMap][i] = droppedItem;
				gp.obj[gp.currentMap][i].worldX = worldX; // the dead monster's worldX
				gp.obj[gp.currentMap][i].worldY = worldY; // the dead monster's worldY
				break;
			}
		}
    }  
    public int getXDistance(Entity target) {
    	int xDistance = Math.abs(worldX - target.worldX);
    	return xDistance;
    }
    public int getYDistance(Entity target) {
    	int yDistance = Math.abs(worldY - target.worldY);
    	return yDistance;
    }
    public int getTileDistance(Entity target) {
    	int tileDistance = (getXDistance(target) + getYDistance(target)) / gp.tileSize;
    	return tileDistance;
    }
    public int getGoalCol(Entity target) {
    	int goalCol = (target.worldX + target.solidArea.x) / gp.tileSize;
    	return goalCol;
    }
    public int getGoalRow(Entity target) {
    	int goalRow = (target.worldY + target.solidArea.y) / gp.tileSize;
    	return goalRow;
    }
    
    public void checkCollision() {
    	collisionOn = false;
		gp.cChecker.checkTile(this);
		gp.cChecker.checkObject(this, false);
		gp.cChecker.checkEntity(this, gp.npc);
		gp.cChecker.checkEntity(this, gp.monster);
		boolean contactPlayer = gp.cChecker.checkPlayer(this);
		
		if(type == TYPE_MONSTER && contactPlayer == true) {
			damagePlayer(attack);
		}
    } 
	public void update() {
		if (knockBack == true) {
			checkCollision();
			if (collisionOn == true) {
				knockBackCounter = 0;
				knockBack = false;
				speed = defaultSpeed;
			} else if (collisionOn == false) {
				switch (gp.player.direction) {
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
			
			knockBackCounter++;
			if (knockBackCounter == 10) {
				knockBackCounter = 0;
				knockBack = false;
				speed = defaultSpeed;
			}
		} else {
			setAction();
			checkCollision();
			
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
	        if(spriteCounter > 24){ // hình ảnh được thay đổi sau 8 khung hình
	            if(spriteNum == 1){
	                spriteNum = 2;
	            }
	            else if(spriteNum == 2){
	                spriteNum = 1;
	            }
	            spriteCounter = 0;	    
			}
		}
		 
        if (invincible == true) {
        	invincibleCounter++;
        	if (invincibleCounter > 40) {
        		invincible = false;
        		invincibleCounter = 0;
        	}
        }
        
        if(shotAvailableCounter < 30) { // 30 game loop mới bắn
        	shotAvailableCounter++;
        }
	}
	public void attacking() {
    	spriteCounter++;
    	
    	if (spriteCounter <= motion1_duration) {
    		spriteNum = 1;
    	}
    	if (spriteCounter > motion1_duration && spriteCounter <= motion2_duration) {
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
    		
    		if (this.type == TYPE_MONSTER) {
    			if (gp.cChecker.checkPlayer(this) == true) {
    				damagePlayer(attack);
    			}
    		}
    		
    		// Restore the original data
    		worldX = currentWorldX;
    		worldY = currentWorldY;
    		solidArea.width = solidAreaWidth; 
    		solidArea.height = solidAreaHeight;
    	}
    	if (spriteCounter > motion2_duration) {
    		spriteNum = 1;
    		spriteCounter = 0;
    		attacking = false;
    	}
    }
	public void damagePlayer(int attack) {
		if (gp.player.invincible == false) {
			// gp.playSE(1)
			
			int damage = attack;
			if (damage <= 0) {
				damage = 0;
			}
			gp.player.life -= damage;
			if (gp.player.life <= 0) {
				gp.player.life = 0;
			}
			gp.player.invincible = true;
		}
	}
	public void checkShootOrNot(int rate, int shotInterval) {
		int i = new Random().nextInt(rate); // bắn ngẫu nhiên
    	if(i == 0 && projectile.alive == false && shotAvailableCounter == shotInterval) { //
    		projectile.set(worldX, worldY, direction, true, this);
    		gp.projectileList.add(projectile);
    		shotAvailableCounter = 0;
    	}
	}
	public void checkStartChasingOrNot(Entity target, int distance, int rate) {
		if (getTileDistance(target) < distance) {
			int i = new Random().nextInt(rate);
			if (i == 0) {
				onPath = true;
			}			
		}
	}
	public void checkStopChasingOrNot(Entity target, int distance, int rate) {
		if (getTileDistance(target) > distance) {
			int i = new Random().nextInt(rate);
			if (i == 0) {
				onPath = false;
			}	
		}
	}
	public boolean checkAttackOrNot(int rate, int vertical, int horizontal) {
		boolean targetInRange = false;
		int xDis = getXDistance(gp.player);	
		int yDis = getYDistance(gp.player);
		
		switch(direction) {
		case("up"):
			if (gp.player.worldY <= worldY && yDis <= vertical && xDis <= horizontal) {
				targetInRange = true;
			} 
			if (gp.player.worldY <= worldY && (yDis > vertical || xDis > horizontal)) {
				targetInRange = false;
			}
			break;
		case("down"):
			if (gp.player.worldY > worldY && yDis <= vertical && xDis <= horizontal) {
				targetInRange = true;
			}
			if (gp.player.worldY > worldY && (yDis > vertical || xDis > horizontal)) {
				targetInRange = false;
			}
			break;
		case("left"):
			if (gp.player.worldX <= worldX && yDis <= vertical && xDis <= horizontal) {
				targetInRange = true;
			} 
			if (gp.player.worldX <= worldX && (yDis > vertical || xDis > horizontal)) {
				targetInRange = false;
			}
			break;
		case("right"):
			if (gp.player.worldX > worldX && yDis <= vertical && xDis <= horizontal) {
				targetInRange = true;
			} 
			if (gp.player.worldX > worldX && (yDis > vertical || xDis > horizontal)) {
				targetInRange = false;
			}
			break;
		}
		
		if (targetInRange == true) {
			int i = new Random().nextInt(rate);
			if (i == 0) {
				attacking = true;
				spriteNum = 1;
				spriteCounter = 0;
				shotAvailableCounter = 0;
			}
		}
		
		if (targetInRange == false) {
			attacking = false;
		}
		
		return targetInRange;
	}
	public void getRandomDirection() {
		actionLockCounter++;
    	if (actionLockCounter == 120) {
    		Random random = new Random();
        	int i = random.nextInt(100) + 1;
        	
        	if (i <= 25) {
        		direction = "up";  		
        	}
        	else if ((i > 25)&&(i <= 50)) {
        		direction = "down";
        	}
        	else if ((i > 50)&&(i <= 75)) {
        		direction = "left";
        	}
        	else if ((i > 75)&&(i <= 100)) {
        		direction = "right";
        	}
        	actionLockCounter = 0;
    	}
	}
	
	// Công cụ tạo ảnh entity
	public BufferedImage setup(String imagePath, int width, int height) {
		// Khai báo công cụ scale
		UtilityTool uTool = new UtilityTool();
		BufferedImage image = null;

		try {
			// Fetch ảnh gốc vào entity
			image = ImageIO.read(getClass().getResourceAsStream(imagePath + ".png"));
			// Scale ảnh gốc về kích thước tile
			image = uTool.scaleImage(image, width, height);
		} catch(IOException e) {
			e.printStackTrace();
		}

		return image;
	}

	public void draw(Graphics2D g2) {
		BufferedImage image = null;
		int screenX = worldX - gp.player.worldX + gp.player.screenX;
		int screenY = worldY - gp.player.worldY + gp.player.screenY;

        // Vẽ tile nằm trong phạm vi màn hình và mở rộng thêm tile ngoài viền để tránh "sọc"
        if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX - gp.tileSize &&
                worldX - gp.tileSize < gp.player.worldX + gp.player.screenX + gp.tileSize &&
                worldY + gp.tileSize > gp.player.worldY - gp.player.screenY - gp.tileSize &&
                worldY - gp.tileSize < gp.player.worldY + gp.player.screenY + gp.tileSize) {
        	int tempScreenX = screenX;
            int tempScreenY = screenY;
        	
	        switch (direction){
	        case "up":
            	if (attacking == false) {
                    if(spriteNum == 1) image = up2;
                    if(spriteNum == 2) image = up1;
            	}
            	if (attacking == true) {
            		tempScreenY = screenY - gp.tileSize;
                    if(spriteNum == 1) image = attackUp1;
                    if(spriteNum == 2) image = attackUp2;
            	}
                break;
            case "down":
            	if (attacking == false) {
            		if(spriteNum == 1) image = down2;
                    if(spriteNum == 2) image = down1;
            	}
            	if (attacking == true) {
                    if(spriteNum == 1) image = attackDown1;
                    if(spriteNum == 2) image = attackDown2;
            	}             
                break;
            case "right":
            	if (attacking == false) {
            		if(spriteNum == 1) image = right2;
                    if(spriteNum == 2) image = right1;
            	}
            	if (attacking == true) {
                    if(spriteNum == 1) image = attackRight1;
                    if(spriteNum == 2) image = attackRight2;
            	}
                break;
            default:
            	if (attacking == false) {
                    if(spriteNum == 1) image = left2;
                    if(spriteNum == 2) image = left1;
            	}
            	if (attacking == true) {
            		tempScreenX = screenX - gp.tileSize;
                    if(spriteNum == 1) image = attackLeft1;
                    if(spriteNum == 2) image = attackLeft2;
            	}
                break;
	        }
	        
	        if (type == TYPE_MONSTER && hpBarOn == true) {
	        	// Drawing monsters health bar 
	        	double oneScale = (double)gp.tileSize / maxLife;
	        	double hpBarValue = oneScale * life;
	        	
	        	g2.setColor(new Color(35,35,35));
	        	g2.fillRect(screenX - 1, screenY - 13, gp.tileSize + 2, 12);
	        	
	        	g2.setColor(new Color(255,0,30));
	        	g2.fillRect(screenX, screenY - 12, (int)hpBarValue, 10);
	        	
	        	// If not attacked, health bar will disappear after 5 secs
	        	hpBarCounter++;
	        	if (hpBarCounter > 300) {
	        		hpBarCounter = 0;
	        		hpBarOn = false;
	        	}
	        }
	        
	        // Health bar will appear if the monster is chasing/in aggro
	        if (type == TYPE_MONSTER && this.onPath == true) {
	        	hpBarOn = true;
	        }
	        
	        if (dying == true) {
	        	dyingAnimation(g2);
	        }
            // Vẽ tile nếu nằm trong màn hình hoặc nằm trong lề được mở rộng
            g2.drawImage(image, tempScreenX, tempScreenY, null);
            
            changeAlpha(g2, 1f);
        }
	}
	public void dyingAnimation(Graphics2D g2) {
		dyingCounter++;
		int i = 5;
		
		if (dyingCounter <= i) {
			changeAlpha(g2, 0f);
		}
		if (dyingCounter > i && dyingCounter <= i*2) {
			changeAlpha(g2, 1f);
		}
		if (dyingCounter > i*2 && dyingCounter <= i*3) {
			changeAlpha(g2, 0f);
		}
		if (dyingCounter > i*3 && dyingCounter <= i*4) {
			changeAlpha(g2, 1f);
		}
		if (dyingCounter > i*4 && dyingCounter <= i*5) {
			changeAlpha(g2, 0f);
		}
		if (dyingCounter > i*5 && dyingCounter <= i*6) {
			changeAlpha(g2, 1f);
		}
		if (dyingCounter > i*6 && dyingCounter <= i*7) {
			changeAlpha(g2, 0f);
		}
		if (dyingCounter > i*7 && dyingCounter <= i*8) {
			changeAlpha(g2, 1f);
		}
		if (dyingCounter > i*8) {
			dying = false;
			alive = false;
		}		
	}
	public void changeAlpha(Graphics2D g2, float alphaValue) {
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alphaValue));
	}
	public void searchPath(int goalCol, int goalRow) {
		int startCol = (worldX + solidArea.x) / gp.tileSize;
		int startRow = (worldY + solidArea.y) / gp.tileSize;
		
		gp.pFinder.setNodes(startCol, startRow, goalCol, goalRow);
		
		if (gp.pFinder.search() == true) {
			// Next worldX and worldY
			int nextX = gp.pFinder.pathList.get(0).col * gp.tileSize;
			int nextY = gp.pFinder.pathList.get(0).row * gp.tileSize;
			
			// Entity solidArea's position
			int enLeftX = worldX + solidArea.x;
			int enRightX = worldX + solidArea.x + solidArea.width;
			int enTopY = worldY + solidArea.y;
			int enBottomY = worldY + solidArea.y + solidArea.height;
			
			if (enTopY > nextY && enLeftX >= nextX && enRightX < nextX + gp.tileSize) {
				direction = "up";
			} else if (enTopY < nextY && enLeftX >= nextX && enRightX < nextX + gp.tileSize) {
				direction = "down";
			} else if (enTopY >= nextY && enBottomY < nextY + gp.tileSize) {
				// left or right
				if (enLeftX > nextX) {
					direction = "left";
				}
				if (enLeftX < nextX) {
					direction = "right";
				}
			} else if (enTopY > nextY && enLeftX > nextX) {
				// up or left
				direction = "up";
				checkCollision();
				if (collisionOn == true) {
					direction = "left";
				}
			} else if (enTopY > nextY && enLeftX < nextX) {
				// up or right
				direction = "up";
				checkCollision();
				if (collisionOn == true) {
					direction = "right";
				}
			} else if (enTopY < nextY && enLeftX > nextX) {
				// down or left
				direction = "down";
				checkCollision();
				if (collisionOn == true) {
					direction = "left";
				}
			} else if (enTopY < nextY && enLeftX < nextX) {
				// down or right
				direction = "down";
				checkCollision();
				if (collisionOn == true) {
					direction = "right";
				}
			}
			
			// If reached the goal, stop the search
//			int nextCol = gp.pFinder.pathList.get(0).col;
//			int nextRow = gp.pFinder.pathList.get(0).row;
//			if (nextCol == goalCol && nextRow == goalRow) {
//				onPath = false;
//			}
		}
	}
}