package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Wood extends Entity{
	
	public OBJ_Wood(GamePanel gp) {
		super(gp);
		
		name = "Gỗ";
		description = "[ "+ name +" ]\nGỗ bình thường, sao chế được đồ thần\nnhỉ ?";
		stackeable = true;
		collision = true;
		down1 = setup("/objects/wood", 48, 48);
		image1 = setup("/objects/wood", gp.tileSize, gp.tileSize);
	}
}
