package entity;

import main.GamePanel;

public class NPC extends Entity {
	// DIALOGUES
    public String dialogues[][] = new String[50][50];
    public int dialogueIndex = 0;
    public int dialogueSet = 0;
	public boolean diaEnd = false;
	public int woodneed;
	public int ironneed;
	
	public NPC(GamePanel gp) {
		super(gp);
	}
	
	public void startDialogue(NPC npc, int setNum){
        gp.gameState = gp.dialogueState;
        gp.ui.npc = npc;
        dialogueSet = setNum;
    }
	
	// quay mặt npc ra chỗ mình	
    public void facePlayer(){
        switch( gp.player.direction) {
			case "up":
			direction = "down";
			break;
			case "down":
			direction = "up";
			break;
			case "right":
			direction = "left";
			break;
			case "left":
			direction = "right";
			break;
		}
    }
}
