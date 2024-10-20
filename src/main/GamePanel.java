package main;

import entity.Entity;
import entity.Player;
import object.SuperObject;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{
    // Cài đặt màn hình
    final int originalTileSize = 32; // 32 x 32 tile
    final int scale = 2;

    public final int tileSize = originalTileSize * 2; // 64 x 64 tile
   // public final int tileSize1 = originalTileSize * 2;
    public final int maxScreenCol = 16; // dài 16 ô
    public final int maxScreenRow = 10; // rộng 12 ô
    public final int screenWidth = maxScreenCol * tileSize; // 768 pixels
    public final int screenHeight = maxScreenRow * tileSize;// 576 pixels

    // WORLD SETTINGS
    public final int maxWorldCol = 50; // bản đồ dài 50 ô
    public final int maxWorldRow = 50; // bản đồ rộng 50 ô


    // FPS : set Thời gian lặp lại
    int FPS = 60;

    // SYSTEM
    TileManager tileM = new TileManager(this);
    Sound sound = new Sound();
    KeyHandler keyH = new KeyHandler(this);
    public AssetSetter aSetter  = new AssetSetter(this);
    public UI ui = new UI(this);
    Thread gameThread;
    public CollisionChecker cChecker = new CollisionChecker(this);

    // ENTITY AND OBJECT
    public Player player = new Player(this, keyH);
    public SuperObject obj[] = new SuperObject[20];
    public Entity npc[] = new Entity[5];

    // GAME STATE
    public int gameState;
    public final int titleState = 0;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int dialogueState = 3;
    public final int characterState = 4;

    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void setupGame(){
        aSetter.setObject();
        aSetter.setNPC();
        playMusic(0);
        // stopMusic();

        gameState = titleState;
    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() { // Game loop

        double drawInterval = 1000000000 / FPS; // vẽ screen: cứ sau 1/60s để có thể vẻ màn hình 60 lần
        double nextDrawTime = System.nanoTime() + drawInterval;

        while(gameThread != null){

            // 1. Cập nhật: vị trí nhân vật,...
            update();

            // 2. Vẽ: vẽ lại screen with the update information
            repaint();

            try {
                double remainingTime = nextDrawTime - System.nanoTime(); // trả lại thời gian cho lần vẽ tiếp theo
                remainingTime = remainingTime / 1000000; // chuyển từ nano -> mili

                if(remainingTime < 0){
                    remainingTime = 0;
                }

                Thread.sleep((long) remainingTime);

                nextDrawTime += drawInterval; // 1/60s tiếp theo sẽ thực hiện tiếp tiếp tiếp...

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
    }

    public void update(){ // update nhân vật sau mỗi vòng loop
        if(gameState == playState){
            player.update();
            
            for(int i = 0; i < npc.length; i++){
                if(npc[i] != null){
                    npc[i].update();
                }
            }
        }
  
        if(gameState == pauseState){

        }
    }

    public void paintComponent(Graphics g){ // vẽ lại screen
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;

     // TITLE SCREEN
        if(gameState == titleState){
            ui.draw(g2);
        }

        // OTHERS
        else{
            // TILE
            tileM.draw(g2);

            // OBJECT
            for(int i = 0; i < obj.length; i++){
                if(obj[i] != null){
                    obj[i].draw(g2, this);
                }
            }

            // NPC
            for(int i = 0; i < npc.length; i++){
                if(npc[i] != null){
                    npc[i].draw(g2);
                }
            }

            // PLAYER
            player.draw(g2);

            // UI
            ui.draw(g2);
        }
        g2.dispose();
    }

    public void playMusic(int i){
        sound.setFile(i);
        sound.play();
        sound.loop();
    }

    public void stopMusic(){
        sound.stop();
    }

    public void playSE(int i){
        sound.setFile(i);
        sound.play();
    }
}
