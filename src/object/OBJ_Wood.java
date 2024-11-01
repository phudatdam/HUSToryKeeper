package object;

import entity.Entity;
import main.GamePanel;

import java.awt.*;

public class OBJ_Wood extends Entity{
	GamePanel gp;

	public OBJ_Wood(GamePanel gp) {
		super(gp);
		this.gp = gp;
		value = 1;

		type = TYPE_pickupOnly;
		name = "Gỗ";
		solidArea = new Rectangle (16, 16, 32, 32);
		down2 = setup("/objects/wood", 40, 40);

		description = "[ "+ name +" ]\nGỗ bình thường, sao chế được đồ thần\nnhỉ ?";
<<<<<<< HEAD
		down1 = setup("/objects/wood", gp.tileSize, gp.tileSize);
=======
		stackeable = true;
		collision = true;
		down1 = setup("/objects/wood", 48, 48);
		image1 = setup("/objects/wood", gp.tileSize, gp.tileSize);
>>>>>>> a3915806a98e49299a677734f40ced597357acb7
	}

	public void use(Entity entity){
		gp.playSE(3);
		gp.player.wood += value;
	}
}
