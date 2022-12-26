import javax.swing.*;
import java.awt.*;

public class GameScreen {
    public static void main(String[] args) {
        JFrame f = new JFrame("PVZ");
        f.setSize(600, 500);

        Container cont = f.getContentPane();
        cont.setLayout(new BorderLayout());

        JPanel top = new JPanel();
        top.setLayout(new FlowLayout());

        JLabel image = new JLabel(new ImageIcon("src/images/java.png"));
        top.add(image);

        JLabel text = new JLabel("PVZ");
        top.add(text);

        cont.add(top, BorderLayout.NORTH);

        JPanel grid = new JPanel();
        grid.setLayout(new GridLayout(5, 9));



        for (int i = 0; i < 45; i++) {
            ImageIcon img = new ImageIcon("");
            JButton b = new JButton();
            if (i % 2 == 0)  img = new ImageIcon("images/tiles/darkGreenTile.PNG");
            else  img = new ImageIcon("images/tiles/greenTile.PNG");
            b.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
            b.setIcon(img);
            grid.add(b);
        }

        cont.add(grid, BorderLayout.CENTER);

        JPanel bottomNav = new JPanel();
        GridLayout bottomGrid = new GridLayout(1, 3);
        bottomNav.setLayout(bottomGrid);

        JButton b1 = new JButton("aaaa");
        JButton b2 = new JButton("bbbb");
        JButton b3 = new JButton("cccc");

        bottomNav.add(b1);
        bottomNav.add(b2);
        bottomNav.add(b3);

        cont.add(bottomNav, BorderLayout.SOUTH);

        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    }

    private static Icon resizeIcon(ImageIcon icon, int resizedWidth, int resizedHeight) {
        Image img = icon.getImage();
        Image resizedImage = img.getScaledInstance(resizedWidth, resizedHeight, java.awt.Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImage);
    }
}