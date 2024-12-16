package monsters;

import java.util.Random;

import entity.Monster;
import main.GamePanel;
import object.OBJ_Heart;
import object.OBJ_Iron;
import object.OBJ_Wood;

public class MON_BronzeSword extends Monster {
	public MON_BronzeSword (GamePanel gp) {
		super(gp);
		
		name = "Đồng kiếm thủ";
		defaultSpeed = 2;
        speed = defaultSpeed;
		maxLife = 20;
		life = maxLife;
		attack = 2;
		defense = 0;
		
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        attackArea.width = 48;
        attackArea.height = 48;
        
        getImage("bronze_sword");
        getAttackImage("bronze_sword");
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
