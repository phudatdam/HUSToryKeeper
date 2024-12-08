package monsters;

import java.util.Random;

import entity.Monster;
import main.GamePanel;
import object.OBJ_Heart;
import object.OBJ_Iron;
import object.OBJ_Wood;

public class MON_BronzePolearm extends Monster {
	public MON_BronzePolearm (GamePanel gp) {
		super(gp);
		
		name = "Đồng giáo thủ";
		defaultSpeed = 2;
        speed = defaultSpeed;
		maxLife = 24;
		life = maxLife;
		attack = 1;
		defense = 0;
		motion1_duration = 5;
		motion2_duration = 25;
		
		
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        attackArea.width = 48;
        attackArea.height = 48;
        
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
		if (onPath == true) {
			// Check if it stops chasing
			checkStopChasingOrNot(gp.player, 15, 30);				
			// Search the direction to go
			searchPath(getGoalCol(gp.player), getGoalRow(gp.player));		
		} else {
			// Check if it starts chasing
			checkStartChasingOrNot(gp.player, 5, 30);
			// Get a random direction
			getRandomDirection();
		}
		
		if (attacking == false) {
			checkAttackOrNot(30, gp.tileSize, gp.tileSize);
		}
    }
	
	public void damageReaction() {
		actionLockCounter = 0;
	}

	public void checkDrop(){
		int i = new Random().nextInt(100) + 1;

		if(i < 40){
			dropItem(new OBJ_Heart(gp));
		}
		else if(i < 70){
			dropItem(new OBJ_Wood(gp));
		}
		else{
			dropItem(new OBJ_Iron(gp));
		}
	}
}