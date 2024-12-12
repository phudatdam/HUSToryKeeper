package object;

import entity.Entity;
import main.GamePanel;
import java.awt.Rectangle;

public class OBJ_Apple extends Entity{
    GamePanel gp;
    public OBJ_Apple(GamePanel gp){
        super(gp);    	
    	// DEBUG
    	this.gp = gp;
    	
        name = "Táo";
		solidArea = new Rectangle (16, 16, 32, 32);
		type = TYPE_consumable;
		description = "[ "+ name +" ]\nRất ngon, mọng nước\nĐảm bảo an toàn vệ sinh\nDùng để hồi 2 hp";
		stackeable = true;
		down1 = setup("/objects/apple", 32, 32);
		collision = true;
		image1 = setup("/objects/apple", gp.tileSize, gp.tileSize);
        setDialogue();
    }
    public void setDialogue(){
        dialogues[0][0]=" Bạn cắn 1 miếng táo, có vẻ nó đúng như mô tả vậy.\n Rất ngon. Bạn hồi phục 2 sinh lực";
    }
    public void use(Entity entity){
        startDialogue(this, 0);
        entity.life +=2;
        if(gp.player.life > gp.player.maxLife) gp.player.life = gp.player.maxLife;
    }
        
}
