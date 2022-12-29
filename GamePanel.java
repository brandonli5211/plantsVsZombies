import java.awt.*;
import javax.swing.*;

public class GamePanel extends JPanel {
    // Declare game elements
    private JButton[][] gridButtons;
//    private Peashooter peashooter;

    public GamePanel() {
        // Set up game board
        setLayout(new GridLayout(5, 9));
        gridButtons = new JButton[5][9];

        // Create buttons for grid
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 9; j++) {
                gridButtons[i][j] = new JButton();
                add(gridButtons[i][j]);
            }
        }

        // Create peashooter
//        peashooter = new Peashooter(2, 3);
    }

    public void displayPeashooter() {
        // Add peashooter to game board
//        gridButtons[peashooter.getX()][peashooter.getY()].add(peashooter.getLabel());
    }
}