package main;

import entity.NPC_AnDuongVuong;
import entity.NPC_KimQuy;
import entity.NPC_ThanhGiong;
import monsters.MON_BronzePolearm;
import monsters.MON_BronzeSword;
import object.OBJ_Heart;
import object.OBJ_Well;

public class AssetSetter {
    GamePanel gp;

    public AssetSetter(GamePanel gp){
        this.gp = gp;
    }
    
    public void setObject(){
    	int mapNum = 1;
        gp.obj[mapNum][0] = new OBJ_Heart(gp);
        gp.obj[mapNum][0].worldX = 16 * gp.tileSize;
        gp.obj[mapNum][0].worldY = 22 * gp.tileSize;

        gp.obj[mapNum][1] = new OBJ_Heart(gp);
        gp.obj[mapNum][1].worldX = 28 * gp.tileSize;
        gp.obj[mapNum][1].worldY = 37 * gp.tileSize;
        
        gp.obj[mapNum][2] = new OBJ_Well(gp);
        gp.obj[mapNum][2].worldX = 40 * gp.tileSize;
        gp.obj[mapNum][2].worldY = 38 * gp.tileSize;
        
        mapNum = 2;
        gp.obj[mapNum][0] = new OBJ_Well(gp);
        gp.obj[mapNum][0].worldX = 10 * gp.tileSize;
        gp.obj[mapNum][0].worldY = 27 * gp.tileSize;
    }
    
    public void setNPC() {
    	int mapNum = 1;
    	gp.npc[mapNum][0] = new NPC_AnDuongVuong(gp);
    	gp.npc[mapNum][0].worldX = 37 * gp.tileSize;
        gp.npc[mapNum][0].worldY = 13 * gp.tileSize;
        gp.npc[mapNum][1] = new NPC_KimQuy(gp);
        gp.npc[mapNum][1].worldX = 40 * gp.tileSize;
        gp.npc[mapNum][1].worldY = 36 * gp.tileSize;
        
        mapNum = 2;
    	gp.npc[mapNum][0] = new NPC_ThanhGiong(gp);
    	gp.npc[mapNum][0].worldX = 18 * gp.tileSize;
        gp.npc[mapNum][0].worldY = 10 * gp.tileSize;
        gp.npc[mapNum][1] = new NPC_KimQuy(gp);
        gp.npc[mapNum][1].worldX = 14 * gp.tileSize;
        gp.npc[mapNum][1].worldY = 28 * gp.tileSize;
    }
    
    public void setMonsters() {
    	int mapNum = 1;
    	gp.monster[mapNum][0] = new MON_BronzeSword(gp);
        gp.monster[mapNum][0].worldX = 30 * gp.tileSize;
        gp.monster[mapNum][0].worldY = 32 * gp.tileSize;
        
    	gp.monster[mapNum][1] = new MON_BronzeSword(gp);
        gp.monster[mapNum][1].worldX = 30 * gp.tileSize;
        gp.monster[mapNum][1].worldY = 35 * gp.tileSize;
        
        gp.monster[mapNum][2] = new MON_BronzePolearm(gp);
        gp.monster[mapNum][2].worldX = 13 * gp.tileSize;
        gp.monster[mapNum][2].worldY = 20 * gp.tileSize;
        
        gp.monster[mapNum][3] = new MON_BronzePolearm(gp);
        gp.monster[mapNum][3].worldX = 13 * gp.tileSize;
        gp.monster[mapNum][3].worldY = 22 * gp.tileSize;
    }
}

