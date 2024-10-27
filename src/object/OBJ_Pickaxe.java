package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Pickaxe extends Entity{
	
	public OBJ_Pickaxe(GamePanel gp) {
		super(gp);
		type = TYPE_pickaxe;
		name = "Cuốc";
		description = "[ "+ name +" ]\nkhông biết bằng cách nào đào được\n cả miếng sắt";
		
		down1 = setup("/objects/pickaxe", gp.tileSize, gp.tileSize);
	}
}
