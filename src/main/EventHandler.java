package main;


public class EventHandler {
    GamePanel gp;
    EventRect eventRect[][][];
    int previousEventX,previousEventY;
    boolean eventTouch = true;
    public EventHandler( GamePanel gp)
    {
        this.gp = gp;
        eventRect = new EventRect[gp.maxMap][gp.maxWorldCol][gp.maxWorldRow];
        int map=0;
        int col=0;
        int row=0;
        while(map <gp.maxMap && col<gp.maxWorldCol && row < gp.maxWorldRow)
        {
            eventRect[map][col][row] = new EventRect();
            eventRect[map][col][row].x = 32;
            eventRect[map][col][row].y = 32;
            eventRect[map][col][row].height = 16;
            eventRect[map][col][row].width = 16;
            eventRect[map][col][row].ERDefautX = eventRect[map][col][row].x;
            eventRect[map][col][row].ERDefautY = eventRect[map][col][row].y;
            col++;
            if(col == gp.maxWorldCol)
            {
                col=0;
                row++;
                if(row == gp.maxWorldRow)
                {
                    row=0;
                    map++;
                }
            }
        }
        
    }
    public void checkEvent()
    {
        // check player 1 title away
        int xDistance = Math.abs(gp.player.worldX-previousEventX);
        int yDistance = Math.abs(gp.player.worldY-previousEventY);
        int distance = Math.max(xDistance,yDistance);
        if( distance > gp.tileSize)
        {
            eventTouch = true;
        }
        if(eventTouch == true)
        {
            if(hit(0,4,5) == true){teleportEvent(1, 35, 12);}
            if(hit(0,4,4) == true){checkScene(1);}
        }
    }
    public boolean hit(int map,int col, int row)
    {
        boolean hit = false;
        if(map == gp.currentMap){
            gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
            gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;
            eventRect[map][col][row].x = col * gp.tileSize + eventRect[map][col][row].x;
            eventRect[map][col][row].y = row * gp.tileSize + eventRect[map][col][row].y;
            if(gp.player.solidArea.intersects(eventRect[map][col][row]) && eventRect[map][col][row].eventDone == false)
            {
                hit = true;
                previousEventX = gp.player.worldX;
                previousEventY = gp.player.worldY;
            }
            gp.player.solidArea.x = gp.player.solidAreaDefaultX;
            gp.player.solidArea.y = gp.player.solidAreaDefaultY;
            eventRect[map][col][row].x = eventRect[map][col][row].ERDefautX;
            eventRect[map][col][row].y = eventRect[map][col][row].ERDefautY;
        }
        return hit;
    }
    public void teleportEvent(int map, int col, int row)
    {
        gp.currentMap = map;
        gp.player.worldX = gp.tileSize * col;
        gp.player.worldY = gp.tileSize * row;
        previousEventX = gp.player.worldX;
        previousEventY = gp.player.worldY;
        eventTouch = false;
    }
    public void checkScene(int scenenum)
    {
        if(gp.cutsceneStart == false){
            gp.gameState = gp.cutsceneState;
            gp.csManager.sceneNum = scenenum;
        }
    }
}
