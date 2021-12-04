import java.lang.reflect.Constructor;

import javax.swing.JFrame;

public class GameFrame extends JFrame {

    //Constructor
    public GameFrame(){
        this.add(new GamePanel());
        this.setTitle("Snek!");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        this.setVisible(true);
        this.setLocation(null);
    }
     
}
