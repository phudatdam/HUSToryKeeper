package tile_interactive;

import java.util.Random;

import entity.Entity;
import main.GamePanel;
import object.OBJ_Apple;
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
		int i = new Random().nextInt(100) + 1;

		// SET THE tree DROP
		if(i > 70){
			dropItem(new OBJ_Apple(gp));
		}
		else{
			dropItem(new OBJ_Wood(gp));
		}
	}

}
