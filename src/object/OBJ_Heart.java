package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Heart extends Entity {	
	GamePanel gp;
	
    public OBJ_Heart(GamePanel gp){
    	super(gp);
    	
    	// DEBUG
    	this.gp = gp;
    	
        name = "Heart";
        down1 = setup("/objects/heart_full");
        down2 = setup("/objects/heart_full");
        
        image1 = setup("/objects/heart_full");
        image2 = setup("/objects/heart_half");
        image3 = setup("/objects/heart_blank");
        
        collision = true;
    }
}
