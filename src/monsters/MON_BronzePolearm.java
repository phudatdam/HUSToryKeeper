package monsters;

import java.awt.Rectangle;
import java.util.Random;

import entity.Entity;
import main.GamePanel;

public class MON_BronzePolearm extends Entity{
	public MON_BronzePolearm (GamePanel gp) {
		super(gp);
		
		this.gp = gp;
		type = 2;
		name = "Bronze polearm";
		speed = 1;
		maxLife = 4;
		life = maxLife;
		
		solidArea = new Rectangle(12, 24, 40, 40);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        
        getImage();
        getAttackImage();
	}
	
	public void getImage() {
		up1 = setup("/monsters/bronze_polearm_up_1", gp.tileSize, gp.tileSize);
		up2 = setup("/monsters/bronze_polearm_up_2", gp.tileSize, gp.tileSize);
		down1 = setup("/monsters/bronze_polearm_down_1", gp.tileSize, gp.tileSize);
		down2 = setup("/monsters/bronze_polearm_down_2", gp.tileSize, gp.tileSize);
		left1 = setup("/monsters/bronze_polearm_left_1", gp.tileSize, gp.tileSize);
		left2 = setup("/monsters/bronze_polearm_left_2", gp.tileSize, gp.tileSize);
		right1 = setup("/monsters/bronze_polearm_right_1", gp.tileSize, gp.tileSize);
		right2 = setup("/monsters/bronze_polearm_right_2", gp.tileSize, gp.tileSize);
	}
	
	public void getAttackImage() {
		attackUp1 = setup("/monsters/bronze_polearm_attack_up_1", gp.tileSize, gp.tileSize * 2);
		attackUp2 = setup("/monsters/bronze_polearm_attack_up_2", gp.tileSize, gp.tileSize * 2);
		attackDown1 = setup("/monsters/bronze_polearm_attack_down_1", gp.tileSize, gp.tileSize * 2);
		attackDown2 = setup("/monsters/bronze_polearm_attack_down_2", gp.tileSize, gp.tileSize * 2);
		attackLeft1 = setup("/monsters/bronze_polearm_attack_left_1", gp.tileSize * 2, gp.tileSize);
		attackLeft2 = setup("/monsters/bronze_polearm_attack_left_2", gp.tileSize * 2, gp.tileSize);
		attackRight1 = setup("/monsters/bronze_polearm_attack_right_1", gp.tileSize * 2, gp.tileSize);
		attackRight2 = setup("/monsters/bronze_polearm_attack_right_2", gp.tileSize * 2, gp.tileSize);		
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
}
