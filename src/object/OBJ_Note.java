package object;

import entity.Entity;
import main.GamePanel;

import java.awt.*;

public class OBJ_Note extends Entity{
    GamePanel gp;

    public OBJ_Note(GamePanel gp) {
        super(gp);
        this.gp = gp;

        name = "Note";
        solidArea = new Rectangle (16, 16, 32, 32);
        collision = true;
        down1 = setup("/objects/note1", 32, 32);
        image1 = setup("/objects/note1", gp.tileSize, gp.tileSize);
    }
}
