import javax.swing.*;

/**
 * <h1>Icone</h1>
 *
 * <p>Icone is a simple icons maker letting you create icons from any png, jpg or svg image files.</p>
 *
 * @author  Francis Chabot
 * @version 0.1.0
*/
public class Icone {
    private JPanel mainWindow;

    public Icone() {
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Icone");
        frame.setContentPane(new Icone().mainWindow);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
