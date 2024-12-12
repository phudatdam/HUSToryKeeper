package object;

import entity.Entity;
import main.GamePanel;
import java.awt.Rectangle;

public class OBJ_Coffee extends Entity{
    GamePanel gp;
    public OBJ_Coffee(GamePanel gp){
        super(gp);    	
    	// DEBUG
    	this.gp = gp;
    	
        name = "Cà phê";
		solidArea = new Rectangle (16, 16, 32, 32);
		type = TYPE_consumable;
		description = "[ "+ name +" ]\nRất đậm đà\nNhưng không nên dùng nhiều\nDùng để tăng 1 phòng thủ trong\n30s";
		stackeable = true;
		down1 = setup("/objects/coffe", 32, 32);
		collision = true;
		image1 = setup("/objects/coffe", gp.tileSize, gp.tileSize);
        image2 = setup("/objects/defbuff", gp.tileSize, gp.tileSize);
        setDialogue();
    }
    public void setDialogue(){
        dialogues[0][0]=" Bạn uống 1 miếng ngụm, tỉnh cả ngủ.\n Rất ngon. Bạn tạm thời tăng 1 phòng thủ";
    }
    public void use(Entity entity){
        startDialogue(this, 0);
        gp.ui.addBuff(image2);
        gp.player.def++;
        gp.player.coffeOn = true;
    }
        
}
