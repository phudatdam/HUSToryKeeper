package object;

import javax.imageio.IIOException;
import javax.imageio.ImageIO;

import main.GamePanel;

import java.io.IOException;

public class OBJ_Heart extends SuperObject {	
	GamePanel gp;
	
    public OBJ_Heart(GamePanel gp){
        name = "Heart";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/objects/heart_full.png"));
            uTool.scaleImage(image, gp.tileSize, gp.tileSize);
        } catch (IIOException e){
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        collision = true;
    }
}
