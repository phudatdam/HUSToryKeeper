package main;

import entity.NPC_AnDuongVuong;
import entity.NPC_KimQuy;
import monsters.MON_BronzeBow;
import entity.NPC_ThanhGiong;
import monsters.MON_BronzePolearm;
import monsters.MON_BronzeSword;
import object.OBJ_Decoration;
import object.OBJ_Heart;
import object.OBJ_Well;
import tile_interactive.IT_IronOre;
import tile_interactive.IT_Tree;

public class AssetSetter {
    GamePanel gp;
    public int mapNum;

    public AssetSetter(GamePanel gp){
        this.gp = gp;
    }
    
    public void setObject(){
    	mapNum = 1;
        gp.obj[mapNum][0] = new OBJ_Heart(gp);
        gp.obj[mapNum][0].worldX = 16 * gp.tileSize;
        gp.obj[mapNum][0].worldY = 22 * gp.tileSize;

        gp.obj[mapNum][1] = new OBJ_Heart(gp);
        gp.obj[mapNum][1].worldX = 28 * gp.tileSize;
        gp.obj[mapNum][1].worldY = 37 * gp.tileSize;
        
        gp.obj[mapNum][2] = new OBJ_Well(gp);
        gp.obj[mapNum][2].worldX = 40 * gp.tileSize;
        gp.obj[mapNum][2].worldY = 38 * gp.tileSize;
        
        gp.obj[mapNum][3] = new OBJ_Decoration(gp, "/misc/house_co_loa_1", gp.tileSize*2, gp.tileSize*2);
        gp.obj[mapNum][3].worldX = 32 * gp.tileSize;
        gp.obj[mapNum][3].worldY = 10 * gp.tileSize;
        
        gp.obj[mapNum][4] = new OBJ_Decoration(gp, "/misc/house_co_loa_1", gp.tileSize*2, gp.tileSize*2);
        gp.obj[mapNum][4].worldX = 37 * gp.tileSize;
        gp.obj[mapNum][4].worldY = 10 * gp.tileSize;
        
        gp.obj[mapNum][5] = new OBJ_Decoration(gp, "/misc/house_co_loa_2", gp.tileSize*3, gp.tileSize*2);
        gp.obj[mapNum][5].worldX = 34 * gp.tileSize;
        gp.obj[mapNum][5].worldY = 8 * gp.tileSize;
        
        gp.obj[mapNum][6] = new OBJ_Decoration(gp, "/misc/bronze_drum", gp.tileSize, gp.tileSize);
        gp.obj[mapNum][6].worldX = 35 * gp.tileSize;
        gp.obj[mapNum][6].worldY = 11 * gp.tileSize;
        
        gp.obj[mapNum][7] = new OBJ_Decoration(gp, "/misc/house_co_loa_1", gp.tileSize*2, gp.tileSize*2);
        gp.obj[mapNum][7].worldX = 21 * gp.tileSize;
        gp.obj[mapNum][7].worldY = 22 * gp.tileSize;
        
        gp.obj[mapNum][8] = new OBJ_Decoration(gp, "/misc/house_co_loa_2", gp.tileSize*3, gp.tileSize*2);
        gp.obj[mapNum][8].worldX = 19 * gp.tileSize;
        gp.obj[mapNum][8].worldY = 33 * gp.tileSize;
        
        gp.obj[mapNum][9] = new OBJ_Decoration(gp, "/misc/house_co_loa_1", gp.tileSize*2, gp.tileSize*2);
        gp.obj[mapNum][9].worldX = 40 * gp.tileSize;
        gp.obj[mapNum][9].worldY = 33 * gp.tileSize;

        mapNum = 2;
        gp.obj[mapNum][0] = new OBJ_Well(gp);
        gp.obj[mapNum][0].worldX = 10 * gp.tileSize;
        gp.obj[mapNum][0].worldY = 27 * gp.tileSize;

    }
    
    public void setNPC() {
    	mapNum = 1;
    	gp.npc[mapNum][0] = new NPC_AnDuongVuong(gp);
    	gp.npc[mapNum][0].worldX = 37 * gp.tileSize;
        gp.npc[mapNum][0].worldY = 12 * gp.tileSize;
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
    	mapNum = 1;
    	gp.monster[mapNum][0] = new MON_BronzeSword(gp);
        gp.monster[mapNum][0].worldX = 19 * gp.tileSize;
        gp.monster[mapNum][0].worldY = 14 * gp.tileSize;
        
    	gp.monster[mapNum][1] = new MON_BronzeSword(gp);
        gp.monster[mapNum][1].worldX = 21 * gp.tileSize;
        gp.monster[mapNum][1].worldY = 16 * gp.tileSize;
        
        gp.monster[mapNum][2] = new MON_BronzePolearm(gp);
        gp.monster[mapNum][2].worldX = 9 * gp.tileSize;
        gp.monster[mapNum][2].worldY = 28 * gp.tileSize;
        
        gp.monster[mapNum][3] = new MON_BronzePolearm(gp);
        gp.monster[mapNum][3].worldX = 11 * gp.tileSize;
        gp.monster[mapNum][3].worldY = 30 * gp.tileSize;
        
        gp.monster[mapNum][4] = new MON_BronzeBow(gp);
        gp.monster[mapNum][4].worldX = 18 * gp.tileSize;
        gp.monster[mapNum][4].worldY = 42 * gp.tileSize;
        
        gp.monster[mapNum][5] = new MON_BronzeBow(gp);
        gp.monster[mapNum][5].worldX = 21 * gp.tileSize;
        gp.monster[mapNum][5].worldY = 43 * gp.tileSize;

        mapNum = 2;
        gp.monster[mapNum][0] = new MON_BronzeSword(gp);
        gp.monster[mapNum][0].worldX = 19 * gp.tileSize;
        gp.monster[mapNum][0].worldY = 14 * gp.tileSize;

        gp.monster[mapNum][1] = new MON_BronzeSword(gp);
        gp.monster[mapNum][1].worldX = 21 * gp.tileSize;
        gp.monster[mapNum][1].worldY = 16 * gp.tileSize;

        gp.monster[mapNum][2] = new MON_BronzePolearm(gp);
        gp.monster[mapNum][2].worldX = 9 * gp.tileSize;
        gp.monster[mapNum][2].worldY = 28 * gp.tileSize;

        gp.monster[mapNum][3] = new MON_BronzePolearm(gp);
        gp.monster[mapNum][3].worldX = 11 * gp.tileSize;
        gp.monster[mapNum][3].worldY = 30 * gp.tileSize;

        gp.monster[mapNum][4] = new MON_BronzeBow(gp);
        gp.monster[mapNum][4].worldX = 18 * gp.tileSize;
        gp.monster[mapNum][4].worldY = 42 * gp.tileSize;

        gp.monster[mapNum][5] = new MON_BronzeBow(gp);
        gp.monster[mapNum][5].worldX = 21 * gp.tileSize;
        gp.monster[mapNum][5].worldY = 43 * gp.tileSize;
    }
    
    public void setInteractiveTile() {
    	mapNum = 1;
    	int i = 0;
    	while (mapNum < gp.maxMap) {
    	    for (int row = 0; row < gp.maxWorldRow; row++) {
    		    for (int col = 0; col < gp.maxWorldCol; col++) {
    			    // Draw tree on top of chopped tree tiles
        		    if (gp.tileM.mapTileNum[mapNum][col][row] == 12) {
        			    gp.iTile[mapNum][i] = new IT_Tree(gp, col, row);
        			    i++;
        		    }
        		    // Draw iron ore on top of mined iron ore tiles
        		    if (gp.tileM.mapTileNum[mapNum][col][row] == 13) {
        			    gp.iTile[mapNum][i] = new IT_IronOre(gp, col, row);
        			    i++;
        		    }
        	    }
    	    }
    	    i = 0;
            mapNum++;
    	}
    }
}

