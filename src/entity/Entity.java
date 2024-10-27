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
    public int speed;
    public int maxLife;
    public int life;
    
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
    public boolean invincible = false;
    public boolean collision = false;
    public boolean attacking = false;

	public Entity(GamePanel gp) {
		this.gp = gp;
	}
	
	public void setAction() {
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
	public void speak() {
        
		
    }
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
	public void update() {
		setAction();
		
		collisionOn = false;
		gp.cChecker.checkTile(this);
		gp.cChecker.checkObject(this, false);
		gp.cChecker.checkEntity(this, gp.npc);
		gp.cChecker.checkEntity(this, gp.monster);		
		boolean contactPlayer = gp.cChecker.checkPlayer(this);
		
		if((this.type == TYPE_MONSTER)&&(contactPlayer == true)) {
			if (gp.player.invincible == false) {
				// quái tấn công => gây sát thương lên player
				gp.player.life -= 1;
				gp.player.invincible = true;
			}
		}
		
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
        
        if (invincible == true) {
        	invincibleCounter++;
        	if (invincibleCounter > 40) {
        		invincible = false;
        		invincibleCounter = 0;
        	}
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
        	
	        switch (direction){
	            case "up":
	                if(spriteNum == 1) image = up2;
	                if(spriteNum == 2) image = up1;
	                break;
	            case "down":
	                if(spriteNum == 1) image = down2;
	                if(spriteNum == 2) image = down1;
	                break;
	            case "right":
	                if(spriteNum == 1) image = right2;
	                if(spriteNum == 2) image = right1;
	                break;
	            default:
	                if(spriteNum == 1) image = left2;
	                if(spriteNum == 2) image = left1;
	                break;
	        }
	        
	        if (invincible == true) {
	        	g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.4f));
	        }
	        
            // Vẽ tile nếu nằm trong màn hình hoặc nằm trong lề được mở rộng
            g2.drawImage(image, screenX, screenY, null);
            
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
        }
	}
}