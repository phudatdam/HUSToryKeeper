package object;

import entity.Entity;
import main.GamePanel;
import java.awt.Rectangle;

public class OBJ_Drink extends Entity{
    GamePanel gp;
    public OBJ_Drink(GamePanel gp){
        super(gp);    	
    	// DEBUG
    	this.gp = gp;
    	
        name = "Nước tăng lực";
		solidArea = new Rectangle (16, 16, 32, 32);
		type = TYPE_consumable;
		description = "[ "+ name +" ]\nHiệu con trâu xanh\nRất uy tín\nDùng để tăng 2 tấn công trong\n30s";
		stackeable = true;
		down1 = setup("/objects/edrink", 32, 32);
		collision = true;
		image1 = setup("/objects/edrink", gp.tileSize, gp.tileSize);
        image2 = setup("/objects/atkbuff", gp.tileSize, gp.tileSize);
        setDialogue();
    }
    public void setDialogue(){
        dialogues[0][0]=" Bạn uống 1 miếng ngụm, sức mạnh sục sôi lên.\n Rất ngon. Bạn tạm thời tăng 2 tấn công";
    }
    public void use(Entity entity){
        startDialogue(this, 0);
        gp.ui.addBuff(image2);
        gp.player.strength += 2;
        gp.player.coffeOn = true;
    }
        
}
