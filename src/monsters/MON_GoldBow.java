package monsters;

import java.util.Random;

import entity.Monster;
import main.GamePanel;
import object.*;

public class MON_GoldBow extends Monster {
	
	public MON_GoldBow (GamePanel gp) {
		super(gp);
		
		name = "Vàng cung thủ";
		defaultSpeed = 1;
        speed = defaultSpeed;
		maxLife = 24;
		life = maxLife;
		attack = 2;
		rangedAttack = true;
		motion1_duration = 5;
		motion2_duration = 25;
		projectile = new OBJ_Arrow(gp);
		projectile.speed += 2;
		
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        
        getImage();
        getAttackImage();
	}
	
	public void getImage() {
		up1 = setup("/monsters/gold_bow_up_1", gp.tileSize, gp.tileSize);
		up2 = setup("/monsters/gold_bow_up_2", gp.tileSize, gp.tileSize);
		down1 = setup("/monsters/gold_bow_down_1", gp.tileSize, gp.tileSize);
		down2 = setup("/monsters/gold_bow_down_2", gp.tileSize, gp.tileSize);
		left1 = setup("/monsters/gold_bow_left_1", gp.tileSize, gp.tileSize);
		left2 = setup("/monsters/gold_bow_left_2", gp.tileSize, gp.tileSize);
		right1 = setup("/monsters/gold_bow_right_1", gp.tileSize, gp.tileSize);
		right2 = setup("/monsters/gold_bow_right_2", gp.tileSize, gp.tileSize);
	}
	
	public void getAttackImage() {
		attackUp1 = setup("/monsters/gold_bow_attack_up_1", gp.tileSize, gp.tileSize * 2);
		attackUp2 = setup("/monsters/gold_bow_attack_up_2", gp.tileSize, gp.tileSize * 2);
		attackDown1 = setup("/monsters/gold_bow_attack_down_1", gp.tileSize, gp.tileSize * 2);
		attackDown2 = setup("/monsters/gold_bow_attack_down_2", gp.tileSize, gp.tileSize * 2);
		attackLeft1 = setup("/monsters/gold_bow_attack_left_1", gp.tileSize * 2, gp.tileSize);
		attackLeft2 = setup("/monsters/gold_bow_attack_left_2", gp.tileSize * 2, gp.tileSize);
		attackRight1 = setup("/monsters/gold_bow_attack_right_1", gp.tileSize * 2, gp.tileSize);
		attackRight2 = setup("/monsters/gold_bow_attack_right_2", gp.tileSize * 2, gp.tileSize);		
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
			checkAttackOrNot(30, gp.tileSize * 4, gp.tileSize * 4);
		}
    }
	
	public void checkDrop(){
		int i = new Random().nextInt(100) + 1;

		// SET THE MONSTER DROP
		if(i < 70){
			dropItem(new OBJ_Gem(gp));
		}
		else if(i < 80){
			dropItem(new OBJ_Heart(gp));
		}
		else if(i < 90){
			dropItem(new OBJ_Wood(gp));
		}
		else{
			dropItem(new OBJ_Iron(gp));
		}
	}
	
	public void damageReaction() {
		actionLockCounter = 0;
	}
}
