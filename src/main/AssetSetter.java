package main;

import entity.NPC_AnDuongVuong;
import object.OBJ_Heart;

public class AssetSetter {
    GamePanel gp;

    public AssetSetter(GamePanel gp){
        this.gp = gp;
    }
    
    public void setObject(){
        gp.obj[0] = new OBJ_Heart(gp);
        gp.obj[0].worldX = 21 * gp.tileSize;
        gp.obj[0].worldY = 29 * gp.tileSize;

        gp.obj[1] = new OBJ_Heart(gp);
        gp.obj[1].worldX = 28 * gp.tileSize;
        gp.obj[1].worldY = 37 * gp.tileSize;
    }
    
    public void setNPC() {
    	gp.npc[0] = new NPC_AnDuongVuong(gp);
    	gp.npc[0].worldX = 37 * gp.tileSize;
        gp.npc[0].worldY = 13 * gp.tileSize;
    }
}

