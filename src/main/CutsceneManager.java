package main;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;

public class CutsceneManager {
    GamePanel gp;
    Graphics2D g2;
    public int sceneNum;
    public int scenePhase = 0;
    int counter = 0;
    float alpha = 0f;
    int y;
    String Credit;

    //scene number
    public final int NA = 0;
    public final int intro = 1;
    public final int ending = 2;

    public CutsceneManager(GamePanel gp){
        this.gp = gp;
        Credit = "Một sản phẩm của Nhóm 18\n"
                +"\n\n\n\n\n\n\n\n\n\n\n\n"
                +"Đàm Phú Đạt\n\n"
                +"Nguyễn Thanh Dương\n\n"
                +"Đoàn Thanh Hải\n\n"
                +"Nguyễn Văn Huy\n\n"
                +"Trịnh Thanh Quang\n\n"
                +"\n\n\n\n\n\n\n\n\n\n\n\n"
                +"Thank you for playing !";
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
        if(scenePhase == 0)
        {
            gp.stopMusic();
            gp.ui.npc = gp.player;
            gp.ui.npc.dialogueSet = 2;
            scenePhase++;
        }
        if(scenePhase == 1)
        {
            //dialogue
            gp.ui.drawDialogueState();
        }
        if(scenePhase == 2)
        {
            alpha += 0.005f;
            if(alpha > 1f){
                alpha = 1f;
            }
            drawBlackbackground(alpha);
            if(alpha == 1f)
            {
                alpha = 0;
                scenePhase++;
            }
        }
        if(scenePhase == 3)
        {
            drawBlackbackground(1f);
            alpha += 0.005f;
            if(alpha > 1f){
                alpha = 1f;
            }
            String text = "Bạn tập trung vào làm bài nghiên cứu.\n"
                        + "Có vẻ như cuộc hành trình giúp bạn trong bài báo cáo.\n"
                        + "Lời chúc của rùa vàng cũng ứng nghiệm, bạn đua top học bổng.\n"
                        + "Happy Ending";
            drawString( alpha , 36 , 200, text ,60);
            if(counterReached(720))
            {
                scenePhase++;
            }
        }
        if(scenePhase == 4)
        {
            drawBlackbackground(1f);
            drawString( 1f , 120f , gp.screenHeight/2 , "HUSTory Keeper" ,40);
            if(counterReached(360))
            {
                scenePhase++;
            }
        }
        if(scenePhase == 5)
        {
            drawBlackbackground(1f);
            y =gp.screenHeight/2;
            drawString( 1f , 36f , y , Credit , 40);
            if(counterReached(300))
            {
                scenePhase++;
            }
        }
        if(scenePhase == 6)
        {
            //credit chay len
            drawBlackbackground(1f);
            y--;
            drawString( 1f , 36f , y , Credit , 40);
        }
    }
    public boolean counterReached( int taget)
    {
        boolean counterReached = false;
        counter++;
        if( counter > taget)
        {
            counterReached = true;
            counter = 0;
        }
        return counterReached;
    }
    public void drawBlackbackground(float alpha){
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
        g2.setColor(Color.BLACK);
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
    }
    public void drawString(float alpha, float fontSize, int y, String text, int lineHeight){
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
        g2.setColor(Color.WHITE);
        g2.setFont(g2.getFont().deriveFont(fontSize));

        for(String line : text.split("\n"))
        {
            int x = gp.ui.getXforCenteredText(line);
            g2.drawString(line, x, y);
            y += lineHeight;
        }
    }
}
