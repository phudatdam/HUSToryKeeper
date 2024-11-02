package object;

import java.awt.*;

import entity.Entity;
import main.GamePanel;

public class OBJ_Heart extends Entity {	
	GamePanel gp;
	
    public OBJ_Heart(GamePanel gp){
    	super(gp);    	
    	// DEBUG
    	this.gp = gp;
    	
        name = "Heart";
        solidArea = new Rectangle (16, 16, 32, 32);
        down1 = setup("/objects/heart_full", 32, 32);
        down2 = setup("/objects/heart_full", 32, 32);
        
        image1 = setup("/objects/heart_full", gp.tileSize, gp.tileSize);
        image2 = setup("/objects/heart_half", gp.tileSize, gp.tileSize);
        image3 = setup("/objects/heart_blank", gp.tileSize, gp.tileSize);
        
        collision = true;
    }
}
