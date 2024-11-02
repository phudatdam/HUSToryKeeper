package tile_interactive;

import entity.Entity;
import main.GamePanel;

public class IT_Tree extends InteractiveTile {
	GamePanel gp;
	
	public IT_Tree(GamePanel gp, int col, int row) {
		super(gp, col, row);
		this.gp = gp;
		
		this.worldX = gp.tileSize * col;
		this.worldY = gp.tileSize * row;
		
		down1 = setup("/tiles/tree_1", gp.tileSize, gp.tileSize);
		destructible = true;
		life = 3;
	}
	
	public boolean isCorrectItem(Entity entity) {
		return entity.currentWeapon.type == TYPE_axe;
	}

}
