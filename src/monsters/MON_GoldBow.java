package monsters;

import entity.Monster;
import main.GamePanel;
import object.*;

public class MON_GoldBow extends Monster {
	
	public MON_GoldBow (GamePanel gp) {
		super(gp);
		
		name = "Vàng cung thủ";
		defaultSpeed = 1;
        speed = defaultSpeed;
		maxLife = 28;
		life = maxLife;
		attack = 2;
		rangedAttack = true;
		projectile = new OBJ_Arrow(gp);
		//projectile.speed += 2;
		
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        
        getImage("gold_bow");
        getAttackImage("gold_bow");
	}
	
	public void checkDrop(){
		// SET THE MONSTER DROP
		dropItem(new OBJ_Gem(gp));
	}
}
