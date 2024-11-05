package object;

import entity.Projectile;
import main.GamePanel;

public class OBJ_Arrow extends Projectile{

	GamePanel gp;
	
	public OBJ_Arrow(GamePanel gp) {
		super(gp);
		this.gp = gp;
		
		speed = 5; // tốc độ mũi tên bay
		attack = 1;
		maxLife = 75;
		life = maxLife;
		alive = false;
		
		getImage();
	}
	
	public void getImage() {
		up1 = up2 = setup("/projectiles/arrow_up", gp.tileSize, gp.tileSize);
		down1 = down2 = setup("/projectiles/arrow_down", gp.tileSize, gp.tileSize);
		right1 = right2 = setup("/projectiles/arrow_right", gp.tileSize, gp.tileSize);
		left1= left2 = setup("/projectiles/arrow_left", gp.tileSize, gp.tileSize);
	}	
}
