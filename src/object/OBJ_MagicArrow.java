package object;

import entity.Projectile;
import main.GamePanel;

public class OBJ_MagicArrow extends Projectile{

	GamePanel gp;
	
	public OBJ_MagicArrow(GamePanel gp) {
		super(gp);
		this.gp = gp;
		
		speed = 7; // tốc độ mũi tên bay
		//attack = 10;
		maxLife = 60; // thoi gian con hien thi tren man hinh
		life = maxLife;
		alive = false;
		
		getImage();
	}
	
	public void getImage() {
		up1 = up2 = setup("/projectiles/magic_arrow_up", gp.tileSize, gp.tileSize);
		down1 = down2 = setup("/projectiles/magic_arrow_down", gp.tileSize, gp.tileSize);
		right1 = right2 = setup("/projectiles/magic_arrow_right", gp.tileSize, gp.tileSize);
		left1= left2 = setup("/projectiles/magic_arrow_left", gp.tileSize, gp.tileSize);
	}	
}