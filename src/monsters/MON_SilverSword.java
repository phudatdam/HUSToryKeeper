package monsters;

import java.util.Random;

import entity.Monster;
import main.GamePanel;
import object.OBJ_Heart;
import object.OBJ_Iron;
import object.OBJ_Wood;

public class MON_SilverSword extends Monster {
	public MON_SilverSword (GamePanel gp) {
		super(gp);
		
		name = "Bạc kiếm thủ";
		defaultSpeed = 2;
        speed = defaultSpeed;
		maxLife = 26;
		life = maxLife;
		attack = 3;
		defense = 0;
		
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        attackArea.width = 48;
        attackArea.height = 48;
        
        getImage("silver_sword");
        getAttackImage("silver_sword");
	}

	public void checkDrop(){
		int i = new Random().nextInt(100) + 1;

		// SET THE MONSTER DROP
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
