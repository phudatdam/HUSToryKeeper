package tile_interactive;

import entity.Entity;
import main.GamePanel;
import object.OBJ_Wood;

public class IT_Tree extends InteractiveTile {
	GamePanel gp;
	
	public IT_Tree(GamePanel gp, int col, int row) {
		super(gp, col, row);
		this.gp = gp;
		
		down1 = setup("/tiles/tree_1", gp.tileSize, gp.tileSize);
		destructible = true;
		life = 2;
	}
	
	public boolean isCorrectItem(Entity entity) {
		return entity.currentWeapon.type == TYPE_axe;
	}
	
	public void checkDrop(){
		dropItem(new OBJ_Wood(gp));
	}

}
