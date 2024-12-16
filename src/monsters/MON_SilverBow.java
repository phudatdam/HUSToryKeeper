package monsters;

import entity.Monster;
import main.GamePanel;
import object.*;

public class MON_SilverBow extends Monster {
	
	public MON_SilverBow (GamePanel gp) {
		super(gp);
		
		name = "Bạc cung thủ";
		defaultSpeed = 1;
        speed = defaultSpeed;
		maxLife = 22;
		life = maxLife;
		attack = 1;
		rangedAttack = true;
		projectile = new OBJ_Arrow(gp);
		//projectile.speed += 1;
		
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        
        getImage("silver_bow");
        getAttackImage("silver_bow");
	}
	
	public void checkDrop(){
		// SET THE MONSTER DROP
		dropItem(new OBJ_FireStone(gp));
	}
}
