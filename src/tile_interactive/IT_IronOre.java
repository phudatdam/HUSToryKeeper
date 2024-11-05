package tile_interactive;

import entity.Entity;
import main.GamePanel;
import object.OBJ_Iron;

public class IT_IronOre extends InteractiveTile {
	GamePanel gp;
	
	public IT_IronOre(GamePanel gp, int col, int row) {
		super(gp, col, row);
		this.gp = gp;
		
		this.worldX = gp.tileSize * col;
		this.worldY = gp.tileSize * row;
		
		down1 = setup("/tiles/iron_ore", gp.tileSize, gp.tileSize);
		down2 = setup("/tiles/iron_ore", gp.tileSize, gp.tileSize);
		destructible = true;
		life = 2;
	}
	
	public boolean isCorrectItem(Entity entity) {
		return entity.currentWeapon.type == TYPE_pickaxe;
	}
	
	public void checkDrop(){
		dropItem(new OBJ_Iron(gp));
	}

}
