package entity;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

import main.GamePanel;
import main.UtilityTool;

public class Entity { // lớp cha cho các lớp khác: nhân vật, NPC, monster, object...
	public GamePanel gp;

	// ENTITY IMAGE
	public BufferedImage up1, up2, up3, down1, down2, down3, left1,
			left2, left3, right1, right2, right3;
	public String direction = "down";
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
    public int dialogueIndex=0;
    public int dialogueSet=0;
	public boolean diaEnd = false;
	public int woodneed;
	public int ironneed;
	public int[] spitem = new int[5];

    // ENTITY COUNTERS
    public int spriteCounter = 0;
    public int actionLockCounter = 0;
    public int invincibleCounter = 0; 
    int dyingCounter = 0;
    int hpBarCounter = 0;
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
	public final int TYPE_crossbow = 7;
	public final int TYPE_goldSword = 8;
	public final int TYPE_ironHorse = 9;
	public final int TYPE_wallet = 10;
	
	protected int attackValue;
	protected int defValue;
	protected int spdValue;

    public String name;
    public String description;
    public int defaultSpeed;
    public int speed;
    public int maxLife;
    public int life;
    protected int attack;
	protected int defense;
	protected int motion1_duration;
	protected int motion2_duration;
    public Projectile projectile;
    public Entity currentWeapon;
	public boolean stackeable = false;
	public int amount = 1;

	public Entity(GamePanel gp) {
		this.gp = gp;
	}
	
	protected void getImage(String name) {
		up1 = setup(name + "_up_1", gp.tileSize, gp.tileSize);
		up2 = setup(name + "_up_2", gp.tileSize, gp.tileSize);
		down1 = setup(name + "_down_1", gp.tileSize, gp.tileSize);
		down2 = setup(name + "_down_2", gp.tileSize, gp.tileSize);
		left1 = setup(name + "_left_1", gp.tileSize, gp.tileSize);
		left2 = setup(name + "_left_2", gp.tileSize, gp.tileSize);
		right1 = setup(name + "_right_1", gp.tileSize, gp.tileSize);
		right2 = setup(name + "_right_2", gp.tileSize, gp.tileSize);
	}
	
	protected void setAction() {
		getRandomDirection();
	}	
	void damageReaction() {}	
	void speak() {}
	
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
		}
    }
    
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
		gp.cChecker.checkEntity(this,gp.iTile);
		gp.cChecker.checkPlayer(this);
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
			if (knockBackCounter == 5) {
				knockBackCounter = 0;
				knockBack = false;
				speed = defaultSpeed;
			}
		} else if (attacking == true) {
            attacking();
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
	        if(spriteCounter > 7){ // hình ảnh được thay đổi sau 8 khung hình
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
	}
	protected void attacking() {}
	public void damagePlayer(int attack) {
		if (gp.player.invincible == false) {
			int damage = attack - gp.player.getDef();
			if(damage < 0)damage = 0;
			gp.player.life -= damage;
			gp.ui.addMessage("Bạn vừa dính đòn");
			if (gp.player.life <= 0) {
				gp.ui.randomText = new Random().nextInt(30) + 1;
				gp.gameState = gp.gameoverState;
				gp.stopMusic();
				gp.playSE(6);
			}
			gp.player.invincible = true;
		}
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
		return UtilityTool.setup(imagePath, width, height);
	}

	public void draw(Graphics2D g2) {
		BufferedImage image = null;
		int screenX = worldX - gp.player.worldX + gp.player.screenX;
		int screenY = worldY - gp.player.worldY + gp.player.screenY;

        // Vẽ tile nằm trong phạm vi màn hình và mở rộng thêm tile ngoài viền để tránh "sọc"
        if (worldX + 33*gp.tileSize > gp.player.worldX - gp.player.screenX - gp.tileSize &&
                worldX - 33*gp.tileSize < gp.player.worldX + gp.player.screenX + gp.tileSize &&
                worldY + 33*gp.tileSize > gp.player.worldY - gp.player.screenY - gp.tileSize &&
                worldY - 33*gp.tileSize < gp.player.worldY + gp.player.screenY + gp.tileSize) {
        	int tempScreenX = screenX;
            int tempScreenY = screenY;
        	
	        switch (direction){
	        case "up":
            	if (attacking == false) {
                    if(spriteNum == 1) image = up1;
                    if(spriteNum == 2) image = up2;
            	}
            	if (attacking == true) {
            		tempScreenY = screenY - gp.tileSize;
                    if(spriteNum == 1) image = attackUp1;
                    if(spriteNum == 2) image = attackUp2;
            	}
                break;
            case "down":
            	if (attacking == false) {
            		if(spriteNum == 1) image = down1;
                    if(spriteNum == 2) image = down2;
            	}
            	if (attacking == true) {
                    if(spriteNum == 1) image = attackDown1;
                    if(spriteNum == 2) image = attackDown2;
            	}             
                break;
            case "right":
            	if (attacking == false) {
            		if(spriteNum == 1) image = right1;
                    if(spriteNum == 2) image = right2;
            	}
            	if (attacking == true) {
                    if(spriteNum == 1) image = attackRight1;
                    if(spriteNum == 2) image = attackRight2;
            	}
                break;
            default:
            	if (attacking == false) {
                    if(spriteNum == 1) image = left1;
                    if(spriteNum == 2) image = left2;
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
	        
	      //Make entity half-transparent (%30) when invincible
            if(invincible == true)
            {
                hpBarOn = true;    //when player attacks monster play hpBar
                hpBarCounter = 0;  //reset monster aggro
                changeAlpha(g2,0.4F);
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
}