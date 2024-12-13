package object;

import entity.Projectile;
import main.GamePanel;

public class OBJ_Fireball extends Projectile{

	GamePanel gp;
	
	public OBJ_Fireball(GamePanel gp) {
		super(gp);
		this.gp = gp;
		
		speed = 9; // tốc độ mũi tên bay
		//attack = 10;
		maxLife = 48;
		life = maxLife;
		alive = false;
		
		getImage();
	}
	
	public void getImage() {
		up1 = up2 = setup("/projectiles/fireball_up", gp.tileSize, gp.tileSize);
		down1 = down2 = setup("/projectiles/fireball_down", gp.tileSize, gp.tileSize);
		right1 = right2 = setup("/projectiles/fireball_right", gp.tileSize, gp.tileSize);
		left1 = left2 = setup("/projectiles/fireball_left", gp.tileSize, gp.tileSize);
	}	
}