package monsters;

import entity.Monster;
import main.GamePanel;
import object.*;

public class MON_BronzeBow extends Monster {
	
	public MON_BronzeBow (GamePanel gp) {
		super(gp);
		
		name = "Đồng cung thủ";
		defaultSpeed = 1;
        speed = defaultSpeed;
		maxLife = 16;
		life = maxLife;
		attack = 1; //
		rangedAttack = true;
		projectile = new OBJ_Arrow(gp); //
		
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        
        getImage("bronze_bow");
        getAttackImage("bronze_bow");
	}
	
	public void checkDrop(){
		// SET THE MONSTER DROP
		dropItem(new OBJ_Claw(gp));
	}
}
