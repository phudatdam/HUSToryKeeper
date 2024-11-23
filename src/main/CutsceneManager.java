package main;

import java.awt.Graphics2D;

public class CutsceneManager {
    GamePanel gp;
    Graphics2D g2;
    public int sceneNum;
    public int scenePhase = 0;
    int counter = 0;
    float alpha = 0f;
    int y;

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
            case ending:
                endingScene();
                break;
        }
    }
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
    public void endingScene()
    {

    }
}
