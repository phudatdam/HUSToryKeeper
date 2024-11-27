package main;

import java.util.*;

import entity.*;
import monsters.*;
import object.*;
import tile_interactive.*;

public class AssetSetter {
    GamePanel gp;

    public AssetSetter(GamePanel gp){
        this.gp = gp;
    }
    
    public void setObject(){
    	int mapNum = 0;
    	gp.obj[mapNum][0] = new OBJ_Decoration(gp, "/misc/dorm_bed", gp.tileSize, gp.tileSize*3);
        gp.obj[mapNum][0].worldX = gp.tileSize;
        gp.obj[mapNum][0].worldY = 0;

        gp.obj[mapNum][1] = new OBJ_Decoration(gp, "/misc/dorm_bookcase", gp.tileSize*2, gp.tileSize*2);
        gp.obj[mapNum][1].worldX = 2 * gp.tileSize;
        gp.obj[mapNum][1].worldY = 0;

        gp.obj[mapNum][2] = new OBJ_Decoration(gp, "/misc/dorm_table", gp.tileSize*2, gp.tileSize*2);
        gp.obj[mapNum][2].worldX = 4 * gp.tileSize;
        gp.obj[mapNum][2].worldY = 0;

        gp.obj[mapNum][3] = new OBJ_Decoration(gp, "/misc/dorm_chair", gp.tileSize, gp.tileSize);
        gp.obj[mapNum][3].worldX = 5 * gp.tileSize;
        gp.obj[mapNum][3].worldY = 2 * gp.tileSize;

        gp.obj[mapNum][4] = new OBJ_Decoration(gp, "/misc/dorm_bed", gp.tileSize, gp.tileSize*3);
        gp.obj[mapNum][4].worldX = 6 * gp.tileSize;
        gp.obj[mapNum][4].worldY = 0;

    	mapNum = 1;
        gp.obj[mapNum][0] = new OBJ_Heart(gp);
        gp.obj[mapNum][0].worldX = 16 * gp.tileSize;
        gp.obj[mapNum][0].worldY = 22 * gp.tileSize;
        
        gp.obj[mapNum][1] = new OBJ_Well(gp);
        gp.obj[mapNum][1].worldX = 32 * gp.tileSize;
        gp.obj[mapNum][1].worldY = 14 * gp.tileSize;
        
        gp.obj[mapNum][2] = new OBJ_Decoration(gp, "/misc/house_co_loa_1", gp.tileSize*2, gp.tileSize*2);
        gp.obj[mapNum][2].worldX = 32 * gp.tileSize;
        gp.obj[mapNum][2].worldY = 10 * gp.tileSize;
        
        gp.obj[mapNum][3] = new OBJ_Decoration(gp, "/misc/house_co_loa_1", gp.tileSize*2, gp.tileSize*2);
        gp.obj[mapNum][3].worldX = 37 * gp.tileSize;
        gp.obj[mapNum][3].worldY = 10 * gp.tileSize;
        
        gp.obj[mapNum][4] = new OBJ_Decoration(gp, "/misc/house_co_loa_2", gp.tileSize*3, gp.tileSize*2);
        gp.obj[mapNum][4].worldX = 34 * gp.tileSize;
        gp.obj[mapNum][4].worldY = 8 * gp.tileSize;
        
        gp.obj[mapNum][5] = new OBJ_Decoration(gp, "/misc/bronze_drum", gp.tileSize, gp.tileSize);
        gp.obj[mapNum][5].worldX = 35 * gp.tileSize;
        gp.obj[mapNum][5].worldY = 11 * gp.tileSize;
        
        gp.obj[mapNum][6] = new OBJ_Decoration(gp, "/misc/house_co_loa_1", gp.tileSize*2, gp.tileSize*2);
        gp.obj[mapNum][6].worldX = 21 * gp.tileSize;
        gp.obj[mapNum][6].worldY = 22 * gp.tileSize;
        
        gp.obj[mapNum][7] = new OBJ_Decoration(gp, "/misc/house_co_loa_2", gp.tileSize*3, gp.tileSize*2);
        gp.obj[mapNum][7].worldX = 19 * gp.tileSize;
        gp.obj[mapNum][7].worldY = 33 * gp.tileSize;
        
        gp.obj[mapNum][8] = new OBJ_Decoration(gp, "/misc/house_co_loa_1", gp.tileSize*2, gp.tileSize*2);
        gp.obj[mapNum][8].worldX = 40 * gp.tileSize;
        gp.obj[mapNum][8].worldY = 33 * gp.tileSize;
        
        gp.obj[mapNum][9] = new OBJ_Forest(gp, "/misc/forest_map_1_1", gp.tileSize*32, gp.tileSize*32);
        gp.obj[mapNum][9].worldX = 0 * gp.tileSize;
        gp.obj[mapNum][9].worldY = 0 * gp.tileSize;
        gp.obj[mapNum][10] = new OBJ_Forest(gp, "/misc/forest_map_1_2", gp.tileSize*18, gp.tileSize*32);
        gp.obj[mapNum][10].worldX = 32 * gp.tileSize;
        gp.obj[mapNum][10].worldY = 0 * gp.tileSize;
        gp.obj[mapNum][11] = new OBJ_Forest(gp, "/misc/forest_map_1_3", gp.tileSize*32, gp.tileSize*18);
        gp.obj[mapNum][11].worldX = 0 * gp.tileSize;
        gp.obj[mapNum][11].worldY = 32 * gp.tileSize;

        mapNum = 2;
        gp.obj[mapNum][0] = new OBJ_Well(gp);
        gp.obj[mapNum][0].worldX = 15 * gp.tileSize;
        gp.obj[mapNum][0].worldY = 10 * gp.tileSize;
        
        gp.obj[mapNum][1] = new OBJ_Decoration(gp, "/misc/house_phu_dong_1", gp.tileSize*2, gp.tileSize*2);
        gp.obj[mapNum][1].worldX = 9 * gp.tileSize;
        gp.obj[mapNum][1].worldY = 11 * gp.tileSize;
        
        gp.obj[mapNum][2] = new OBJ_Decoration(gp, "/misc/house_phu_dong_2", gp.tileSize*3, gp.tileSize*2);
        gp.obj[mapNum][2].worldX = 12 * gp.tileSize;
        gp.obj[mapNum][2].worldY = 8 * gp.tileSize;
        
        gp.obj[mapNum][3] = new OBJ_Decoration(gp, "/misc/house_phu_dong_2", gp.tileSize*3, gp.tileSize*2);
        gp.obj[mapNum][3].worldX = 17 * gp.tileSize;
        gp.obj[mapNum][3].worldY = 8 * gp.tileSize;
        
        gp.obj[mapNum][4] = new OBJ_Decoration(gp, "/misc/house_phu_dong_2", gp.tileSize*3, gp.tileSize*2);
        gp.obj[mapNum][4].worldX = 37 * gp.tileSize;
        gp.obj[mapNum][4].worldY = 5 * gp.tileSize;
        
        gp.obj[mapNum][5] = new OBJ_Decoration(gp, "/misc/house_phu_dong_1", gp.tileSize*2, gp.tileSize*2);
        gp.obj[mapNum][5].worldX = 39 * gp.tileSize;
        gp.obj[mapNum][5].worldY = 18 * gp.tileSize;
        
        gp.obj[mapNum][6] = new OBJ_Decoration(gp, "/misc/house_phu_dong_2", gp.tileSize*3, gp.tileSize*2);
        gp.obj[mapNum][6].worldX = 36 * gp.tileSize;
        gp.obj[mapNum][6].worldY = 21 * gp.tileSize;
        
        gp.obj[mapNum][7] = new OBJ_Decoration(gp, "/misc/house_phu_dong_1", gp.tileSize*2, gp.tileSize*2);
        gp.obj[mapNum][7].worldX = 39 * gp.tileSize;
        gp.obj[mapNum][7].worldY = 35 * gp.tileSize;
        
        gp.obj[mapNum][8] = new OBJ_Decoration(gp, "/misc/house_phu_dong_2", gp.tileSize*3, gp.tileSize*2);
        gp.obj[mapNum][8].worldX = 32 * gp.tileSize;
        gp.obj[mapNum][8].worldY = 39 * gp.tileSize;
        
        gp.obj[mapNum][9] = new OBJ_Decoration(gp, "/misc/house_phu_dong_1", gp.tileSize*2, gp.tileSize*2);
        gp.obj[mapNum][9].worldX = 20 * gp.tileSize;
        gp.obj[mapNum][9].worldY = 39 * gp.tileSize;
        
        gp.obj[mapNum][10] = new OBJ_Decoration(gp, "/misc/house_phu_dong_2", gp.tileSize*3, gp.tileSize*2);
        gp.obj[mapNum][10].worldX = 16 * gp.tileSize;
        gp.obj[mapNum][10].worldY = 38 * gp.tileSize;
        
        gp.obj[mapNum][11] = new OBJ_Decoration(gp, "/misc/house_phu_dong_2", gp.tileSize*3, gp.tileSize*2);
        gp.obj[mapNum][11].worldX = 12 * gp.tileSize;
        gp.obj[mapNum][11].worldY = 25 * gp.tileSize;
        
        mapNum = 3;
        gp.obj[mapNum][0] = new OBJ_Well(gp);
        gp.obj[mapNum][0].worldX = 14 * gp.tileSize;
        gp.obj[mapNum][0].worldY = 12 * gp.tileSize;
        
        gp.obj[mapNum][1] = new OBJ_Decoration(gp, "/misc/house_lam_son_1", gp.tileSize*2, gp.tileSize*2);
        gp.obj[mapNum][1].worldX = 8 * gp.tileSize;
        gp.obj[mapNum][1].worldY = 9 * gp.tileSize;
        
        gp.obj[mapNum][2] = new OBJ_Decoration(gp, "/misc/house_lam_son_2", gp.tileSize*3, gp.tileSize*2);
        gp.obj[mapNum][2].worldX = 11 * gp.tileSize;
        gp.obj[mapNum][2].worldY = 7 * gp.tileSize;
        
        gp.obj[mapNum][3] = new OBJ_Decoration(gp, "/misc/house_lam_son_2", gp.tileSize*3, gp.tileSize*2);
        gp.obj[mapNum][3].worldX = 18 * gp.tileSize;
        gp.obj[mapNum][3].worldY = 9 * gp.tileSize;
        
        gp.obj[mapNum][4] = new OBJ_Decoration(gp, "/misc/house_lam_son_2", gp.tileSize*3, gp.tileSize*2);
        gp.obj[mapNum][4].worldX = (int) (27.5 * gp.tileSize);
        gp.obj[mapNum][4].worldY = 16 * gp.tileSize;
        
        gp.obj[mapNum][5] = new OBJ_Decoration(gp, "/misc/house_lam_son_1", gp.tileSize*2, gp.tileSize*2);
        gp.obj[mapNum][5].worldX = 36 * gp.tileSize;
        gp.obj[mapNum][5].worldY = 25 * gp.tileSize;
        
        gp.obj[mapNum][6] = new OBJ_Decoration(gp, "/misc/house_lam_son_2", gp.tileSize*3, gp.tileSize*2);
        gp.obj[mapNum][6].worldX = 9 * gp.tileSize;
        gp.obj[mapNum][6].worldY = 29 * gp.tileSize;
        
        gp.obj[mapNum][7] = new OBJ_Decoration(gp, "/misc/house_lam_son_1", gp.tileSize*2, gp.tileSize*2);
        gp.obj[mapNum][7].worldX = 13 * gp.tileSize;
        gp.obj[mapNum][7].worldY = 30 * gp.tileSize;
        
        gp.obj[mapNum][8] = new OBJ_Decoration(gp, "/misc/house_lam_son_2", gp.tileSize*3, gp.tileSize*2);
        gp.obj[mapNum][8].worldX = 39 * gp.tileSize;
        gp.obj[mapNum][8].worldY = 38 * gp.tileSize;

    }
    
    public void setNPC() {
    	int mapNum = 1;
    	gp.npc[mapNum][0] = new NPC_AnDuongVuong(gp);
    	gp.npc[mapNum][0].worldX = 37 * gp.tileSize;
        gp.npc[mapNum][0].worldY = 12 * gp.tileSize;
        gp.npc[mapNum][1] = new NPC_KimQuy(gp, mapNum);
        gp.npc[mapNum][1].worldX = 40 * gp.tileSize;
        gp.npc[mapNum][1].worldY = 36 * gp.tileSize;
        
        mapNum = 2;
    	gp.npc[mapNum][0] = new NPC_ThanhGiong(gp);
    	gp.npc[mapNum][0].worldX = 18 * gp.tileSize;
        gp.npc[mapNum][0].worldY = 10 * gp.tileSize;
        gp.npc[mapNum][1] = new NPC_KimQuy(gp, mapNum);
        gp.npc[mapNum][1].worldX = 15 * gp.tileSize;
        gp.npc[mapNum][1].worldY = 28 * gp.tileSize;
        
        mapNum = 3;
        gp.npc[mapNum][0] = new NPC_LeLoi(gp);
    	gp.npc[mapNum][0].worldX = 12 * gp.tileSize;
        gp.npc[mapNum][0].worldY = 9 * gp.tileSize;
        gp.npc[mapNum][1] = new NPC_KimQuy(gp, mapNum);
        gp.npc[mapNum][1].worldX = 40 * gp.tileSize;
        gp.npc[mapNum][1].worldY = 40 * gp.tileSize;
    }
    
    public void setMonsters() {
    	int mapNum = 1;
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
        for (int i = 0; i < 5; i++) {
        	gp.originalWorldX[mapNum][i] = gp.monster[mapNum][i].worldX;
        	gp.originalWorldY[mapNum][i] = gp.monster[mapNum][i].worldY;
        }
        	
        mapNum = 2;
        gp.monster[mapNum][0] = new MON_SilverSword(gp);
        gp.monster[mapNum][0].worldX = 38 * gp.tileSize;
        gp.monster[mapNum][0].worldY = 9 * gp.tileSize;
    	gp.monster[mapNum][1] = new MON_SilverSword(gp);
        gp.monster[mapNum][1].worldX = 30 * gp.tileSize;
        gp.monster[mapNum][1].worldY = 16 * gp.tileSize;      
        gp.monster[mapNum][2] = new MON_SilverSword(gp);
        gp.monster[mapNum][2].worldX = 29 * gp.tileSize;
        gp.monster[mapNum][2].worldY = 13 * gp.tileSize;
        
        gp.monster[mapNum][3] = new MON_SilverPolearm(gp);
        gp.monster[mapNum][3].worldX = 33 * gp.tileSize;
        gp.monster[mapNum][3].worldY = 30 * gp.tileSize;      
        gp.monster[mapNum][4] = new MON_SilverPolearm(gp);
        gp.monster[mapNum][4].worldX = 33 * gp.tileSize;
        gp.monster[mapNum][4].worldY = 32 * gp.tileSize;
        gp.monster[mapNum][5] = new MON_SilverPolearm(gp);
        gp.monster[mapNum][5].worldX = 35 * gp.tileSize;
        gp.monster[mapNum][5].worldY = 34 * gp.tileSize;
        
        gp.monster[mapNum][6] = new MON_SilverBow(gp);
        gp.monster[mapNum][6].worldX = 19 * gp.tileSize;
        gp.monster[mapNum][6].worldY = 39 * gp.tileSize;
        gp.monster[mapNum][7] = new MON_SilverBow(gp);
        gp.monster[mapNum][7].worldX = 16 * gp.tileSize;
        gp.monster[mapNum][7].worldY = 41 * gp.tileSize;
        gp.monster[mapNum][8] = new MON_SilverBow(gp);
        gp.monster[mapNum][8].worldX = 24 * gp.tileSize;
        gp.monster[mapNum][8].worldY = 40 * gp.tileSize;
        for (int i = 0; i < 8; i++) {
        	gp.originalWorldX[mapNum][i] = gp.monster[mapNum][i].worldX;
        	gp.originalWorldY[mapNum][i] = gp.monster[mapNum][i].worldY;
        }
        
        mapNum = 3;
        gp.monster[mapNum][0] = new MON_GoldSword(gp);
        gp.monster[mapNum][0].worldX = 12 * gp.tileSize;
        gp.monster[mapNum][0].worldY = 34 * gp.tileSize;
    	gp.monster[mapNum][1] = new MON_GoldSword(gp);
        gp.monster[mapNum][1].worldX = 29 * gp.tileSize;
        gp.monster[mapNum][1].worldY = 15 * gp.tileSize;      
        gp.monster[mapNum][2] = new MON_GoldSword(gp);
        gp.monster[mapNum][2].worldX = 30 * gp.tileSize;
        gp.monster[mapNum][2].worldY = 11 * gp.tileSize;
        gp.monster[mapNum][3] = new MON_GoldSword(gp);
        gp.monster[mapNum][3].worldX = 31 * gp.tileSize;
        gp.monster[mapNum][3].worldY = 11 * gp.tileSize;
        
        gp.monster[mapNum][4] = new MON_GoldPolearm(gp);
        gp.monster[mapNum][4].worldX = 40 * gp.tileSize;
        gp.monster[mapNum][4].worldY = 18 * gp.tileSize;      
        gp.monster[mapNum][5] = new MON_GoldPolearm(gp);
        gp.monster[mapNum][5].worldX = 30 * gp.tileSize;
        gp.monster[mapNum][5].worldY = 28 * gp.tileSize;
        gp.monster[mapNum][6] = new MON_GoldPolearm(gp);
        gp.monster[mapNum][6].worldX = 31 * gp.tileSize;
        gp.monster[mapNum][6].worldY = 28 * gp.tileSize;
        
        gp.monster[mapNum][7] = new MON_GoldBow(gp);
        gp.monster[mapNum][7].worldX = 29 * gp.tileSize;
        gp.monster[mapNum][7].worldY = 41 * gp.tileSize;
        gp.monster[mapNum][8] = new MON_GoldBow(gp);
        gp.monster[mapNum][8].worldX = 32 * gp.tileSize;
        gp.monster[mapNum][8].worldY = 41 * gp.tileSize;
        gp.monster[mapNum][9] = new MON_GoldBow(gp);
        gp.monster[mapNum][9].worldX = 14 * gp.tileSize;
        gp.monster[mapNum][9].worldY = 35 * gp.tileSize;   
        for (int i = 0; i < 9; i++) {
        	gp.originalWorldX[mapNum][i] = gp.monster[mapNum][i].worldX;
        	gp.originalWorldY[mapNum][i] = gp.monster[mapNum][i].worldY;
        }
    }
    
    public void setInteractiveTile() {
    	int mapNum = 1;
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
    
    public Entity respawnMonster(int i) {
    	Entity newMonster = null;
		newMonster = generateMonster(gp.currentMap);
		newMonster.worldX = gp.originalWorldX[gp.currentMap][i];
		newMonster.worldY = gp.originalWorldY[gp.currentMap][i];
		return newMonster;
    }
    
    public Entity generateMonster(int mapNum) {
    	Entity monster = null;
    	Random random = new Random();
    	int generateIndex = random.nextInt(100) + 1;
    	
    	if (mapNum == 1) {
        	if (generateIndex <= 70) {
        		monster = new MON_BronzeSword(gp);
        	}
        	else if ((generateIndex > 70)&&(generateIndex <= 100)) {
        		monster = new MON_BronzePolearm(gp);
        	}
    	}
    	if (mapNum == 2) {
    		if (generateIndex <= 55) {
        		monster = new MON_SilverSword(gp);
        	}
        	else if ((generateIndex > 55)&&(generateIndex <= 90)) {
        		monster = new MON_SilverPolearm(gp);
        	}
        	else if ((generateIndex > 90)&&(generateIndex <= 100)) {
        		monster = new MON_SilverBow(gp);
        	}
    	}
    	if (mapNum == 3) {
    		if (generateIndex <= 45) {
        		monster = new MON_GoldSword(gp);
        	}
        	else if ((generateIndex > 45)&&(generateIndex <= 85)) {
        		monster = new MON_GoldPolearm(gp);
        	}
        	else if ((generateIndex > 85)&&(generateIndex <= 100)) {
        		monster = new MON_GoldBow(gp);
        	}
    	}
    	
    	return monster;
    }
}