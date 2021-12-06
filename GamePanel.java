import javax.swing.JPanel;
import javax.swing.plaf.DimensionUIResource;


import java.awt.event.*;
import java.awt.*;
import javax.swing.*;


public class GamePanel extends JPanel implements ActionListener {

    static final int SCREEN_WIDTH = 600;
    static final int SCREEN_HEIGHT = 600;
    static final int UNIT_SIZE = 25;
    static final int GAME_UNITS = SCREEN_WIDTH * SCREEN_HEIGHT / UNIT_SIZE;
    static final int DELAY =  600;
    Timer timer;    
    RandomNumGenerator rng;
    Snake snake = new Snake(300, 300);

    public GamePanel()
    {
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.black);
        this.setFocusable(true);
        startGame();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        snake.move(UNIT_SIZE);
        repaint();
       
    }
    public void startGame()
    {
        timer = new Timer(DELAY, this);
        timer.start();
    }
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        draw(g);
    }
    public void draw(Graphics g)
    {
        drawGrid(g);        
        drawApple(g);
        drawSnake(g);
        
    }
    public void drawGrid(Graphics g)
    {
        for (int i = 0; i < SCREEN_WIDTH / UNIT_SIZE; i++)
        {
            g.drawLine(i*UNIT_SIZE, 0, i*UNIT_SIZE, SCREEN_HEIGHT);
            g.drawLine(0, i*UNIT_SIZE, SCREEN_WIDTH, i*UNIT_SIZE);
        }
    }
    public void drawApple(Graphics g)
    {
        rng = new RandomNumGenerator();      
        
        g.setColor(Color.red);      
        g.fillRect(100, 400, UNIT_SIZE, UNIT_SIZE);
    }
    public void newApple(Graphics g)
    {
        rng = new RandomNumGenerator();      
        
        g.setColor(Color.red);      
        g.fillRect(rng.getRandomNumber(SCREEN_WIDTH / UNIT_SIZE)*UNIT_SIZE, rng.getRandomNumber(SCREEN_HEIGHT / UNIT_SIZE)*UNIT_SIZE, UNIT_SIZE, UNIT_SIZE);
    }
    public void drawSnake(Graphics g)
    {
        //Heah of Snake
        g.setColor(Color.green);           
        g.fillRect(snake.getSnakeX(), snake.getSnakeY(), UNIT_SIZE, UNIT_SIZE);            
        
        //Body of snake
        g.setColor(new Color(100, 220, 50));
        for (int i = 0; i < snake.getBodyParts().size(); i++)
        {                
            g.fillRect(snake.getBodyParts().get(i).getSnakeBodyX(), snake.getBodyParts().get(i).getSnakeBodyY(), UNIT_SIZE, UNIT_SIZE);       
        }
        
    }

    public class MyKeyAdapter extends KeyAdapter {
       
        public void KeyPressed(KeyEvent e)
        {
            switch (e.getKeyCode()) 
            {
                case KeyEvent.VK_LEFT:
                snake.setDirection('L');                                    
                break;     
                case KeyEvent.VK_UP:
                snake.setDirection('U');                                    
                break;        
                
            }
        }
        
    }
    
}
