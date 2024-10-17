package object;

import javax.imageio.IIOException;
import javax.imageio.ImageIO;

import main.GamePanel;

import java.io.IOException;

public class OBJ_Heart extends SuperObject {	
	GamePanel gp;
	
    public OBJ_Heart(GamePanel gp){
    	super();
    	
    	this.gp = gp;
        name = "Heart";
        try{
            image1 = ImageIO.read(getClass().getResourceAsStream("/objects/heart_full.png"));
            image2 = ImageIO.read(getClass().getResourceAsStream("/objects/heart_half.png"));
            image3 = ImageIO.read(getClass().getResourceAsStream("/objects/heart_blank.png"));
            image1 = uTool.scaleImage(image1, gp.tileSize, gp.tileSize);
            image2 = uTool.scaleImage(image2, gp.tileSize, gp.tileSize);
            image3 = uTool.scaleImage(image3, gp.tileSize, gp.tileSize);
        } catch (IIOException e){
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        collision = true;
    }
}
