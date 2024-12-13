package object;

import entity.Projectile;
import main.GamePanel;

public class OBJ_Slash extends Projectile{

	GamePanel gp;
	
	public OBJ_Slash(GamePanel gp) {
		super(gp);
		this.gp = gp;
		
		speed = 8; // tốc độ mũi tên bay
		//attack = 10;
		maxLife = 48;
		life = maxLife;
		alive = false;
		
		getImage();
	}
	
	public void getImage() {
		up1 = up2 = setup("/projectiles/slash_up", gp.tileSize, gp.tileSize);
		down1 = down2 = setup("/projectiles/slash_down", gp.tileSize, gp.tileSize);
		right1 = right2 = setup("/projectiles/slash_right", gp.tileSize, gp.tileSize);
		left1= left2 = setup("/projectiles/slash_left", gp.tileSize, gp.tileSize);
	}	
}