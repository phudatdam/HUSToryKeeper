package main;

import java.awt.Graphics2D;

public class CutsceneManager {
    GamePanel gp;
    Graphics2D g2;
    public int sceneNum;
    public int scenePhase = 0;

    //scene number
    public final int NA = 0;
    public final int intro = 1;
    public final int ending = 2;

    public CutsceneManager(GamePanel gp){
        this.gp = gp;
    }
    public void draw( Graphics2D g2)
    {
        this.g2 = g2;
        switch (sceneNum) {
            case intro:
                introScene();
                break;
        }
    }
    // có gì đó sai nhưng ko biết sai ở đâu
    public void introScene(){
        if(scenePhase == 0)
        {
            gp.cutsceneStart = true;
            gp.ui.npc = gp.player;
            gp.ui.npc.dialogueSet = 1;
            gp.ui.drawDialogueState();
        }
        if(scenePhase == 1)
        {
            sceneNum = NA;
            scenePhase = 0;
            gp.gameState = gp.playState;
        }
    }
}
