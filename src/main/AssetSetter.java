package main;

import entity.NPC_AnDuongVuong;
import entity.NPC_KimQuy;
import monsters.MON_BronzeBow;
import monsters.MON_BronzePolearm;
import monsters.MON_BronzeSword;
import object.OBJ_Coin;
import object.OBJ_Heart;
import object.OBJ_Wood;

public class AssetSetter {
    GamePanel gp;

    public AssetSetter(GamePanel gp){
        this.gp = gp;
    }
    
    public void setObject(){
        int i = 0;
        gp.obj[i] = new OBJ_Wood(gp);
        gp.obj[i].worldX = 16 * gp.tileSize;
        gp.obj[i].worldY = 22 * gp.tileSize;
        i++;

        gp.obj[i] = new OBJ_Coin(gp);
        gp.obj[i].worldX = 28 * gp.tileSize;
        gp.obj[i].worldY = 37 * gp.tileSize;
        i++;

        gp.obj[i] = new OBJ_Coin(gp);
        gp.obj[i].worldX = 18 * gp.tileSize;
        gp.obj[i].worldY = 20 * gp.tileSize;

    }
    
    public void setNPC() {
    	gp.npc[0] = new NPC_AnDuongVuong(gp);
    	gp.npc[0].worldX = 37 * gp.tileSize;
        gp.npc[0].worldY = 13 * gp.tileSize;
        gp.npc[1] = new NPC_KimQuy(gp);
        gp.npc[1].worldX = 40 * gp.tileSize;
        gp.npc[1].worldY = 40 * gp.tileSize;
    }
    
    public void setMonsters() {    	
    	gp.monster[0] = new MON_BronzeSword(gp);
        gp.monster[0].worldX = 30 * gp.tileSize;
        gp.monster[0].worldY = 33 * gp.tileSize;
        
    	gp.monster[1] = new MON_BronzeSword(gp);
        gp.monster[1].worldX = 30 * gp.tileSize;
        gp.monster[1].worldY = 35 * gp.tileSize;
        
        gp.monster[2] = new MON_BronzePolearm(gp);
        gp.monster[2].worldX = 13 * gp.tileSize;
        gp.monster[2].worldY = 20 * gp.tileSize;
        
        gp.monster[3] = new MON_BronzePolearm(gp);
        gp.monster[3].worldX = 13 * gp.tileSize;
        gp.monster[3].worldY = 22 * gp.tileSize;
        
        gp.monster[4] = new MON_BronzeBow(gp);
        gp.monster[4].worldX = 9 * gp.tileSize;
        gp.monster[4].worldY = 29 * gp.tileSize;
        
        gp.monster[5] = new MON_BronzeBow(gp);
        gp.monster[5].worldX = 20 * gp.tileSize;
        gp.monster[5].worldY = 43 * gp.tileSize;
    }
}

