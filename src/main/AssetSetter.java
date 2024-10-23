package main;

import entity.NPC_AnDuongVuong;
import monsters.MON_BronzePolearm;
import monsters.MON_BronzeSword;
import object.OBJ_Heart;

public class AssetSetter {
    GamePanel gp;

    public AssetSetter(GamePanel gp){
        this.gp = gp;
    }
    
    public void setObject(){
        gp.obj[0] = new OBJ_Heart(gp);
        gp.obj[0].worldX = 16 * gp.tileSize;
        gp.obj[0].worldY = 22 * gp.tileSize;

        gp.obj[1] = new OBJ_Heart(gp);
        gp.obj[1].worldX = 28 * gp.tileSize;
        gp.obj[1].worldY = 37 * gp.tileSize;
    }
    
    public void setNPC() {
    	gp.npc[0] = new NPC_AnDuongVuong(gp);
    	gp.npc[0].worldX = 37 * gp.tileSize;
        gp.npc[0].worldY = 13 * gp.tileSize;
    }
    
    public void setMonsters() {    	
    	gp.monster[0] = new MON_BronzeSword(gp);
        gp.monster[0].worldX = 30 * gp.tileSize;
        gp.monster[0].worldY = 32 * gp.tileSize;
        
    	gp.monster[1] = new MON_BronzeSword(gp);
        gp.monster[1].worldX = 30 * gp.tileSize;
        gp.monster[1].worldY = 35 * gp.tileSize;
        
        gp.monster[2] = new MON_BronzePolearm(gp);
        gp.monster[2].worldX = 13 * gp.tileSize;
        gp.monster[2].worldY = 20 * gp.tileSize;
        
        gp.monster[3] = new MON_BronzePolearm(gp);
        gp.monster[3].worldX = 13 * gp.tileSize;
        gp.monster[3].worldY = 22 * gp.tileSize;
    }
}

