package main;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // đóng cửa sổ game bằng dấu "x"
        window.setResizable(false); // thay đổi kích thước: Không
        window.setTitle("HUSTory Keeper"); // set tiêu đề game: HUSTory Keeper

        GamePanel gamepanel = new GamePanel();
        window.add(gamepanel);


        window.pack();

        window.setLocationRelativeTo(null); // Set vị trí cửa sổ game: chính giữa
        window.setVisible(true);

        gamepanel.setupGame();
        gamepanel.startGameThread(); // Khởi chạy game  
    }
}
