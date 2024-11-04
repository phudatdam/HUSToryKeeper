package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Sword extends Entity{
	
	public OBJ_Sword(GamePanel gp) {
		super(gp);
		type = TYPE_sword;
		name = "Kiếm";
		description = "[ "+ name +" ]\nKhông phải kiếm thần nhưng đủ dùng";
		attackValue = 3;
		image1 = setup("/objects/sword", gp.tileSize, gp.tileSize);
	}
}
