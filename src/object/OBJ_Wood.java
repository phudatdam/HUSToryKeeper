package object;

import entity.Entity;
import main.GamePanel;

import java.awt.*;

public class OBJ_Wood extends Entity{
	GamePanel gp;

	public OBJ_Wood(GamePanel gp) {
		super(gp);
		this.gp = gp;

		name = "Gỗ";
		solidArea = new Rectangle (16, 16, 32, 32);

		description = "[ "+ name +" ]\nGỗ bình thường, sao chế được đồ\nthần nhỉ ?";
		stackeable = true;
		collision = true;
		down1 = setup("/objects/wood", 32, 32);
		image1 = setup("/objects/wood", gp.tileSize, gp.tileSize);
	}
}
