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
    static final int DELAY =  200;
    int appleX = 100;
    int appleY = 400;
    Timer timer;    
    RandomNumGenerator rng;
    Snake snake = new Snake(300, 300);

    public GamePanel()
    {
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.black);
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAdapter());
        startGame();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub

        snake.move(UNIT_SIZE); 
        if (eatenApple())
        {
            snake.grow();
            newApple();
        } 

        repaint();
       
    }
    public void startGame()
    {
        timer = new Timer(DELAY, this);
        timer.start();
    }
    public boolean eatenApple()
    {
        if (snake.getSnakeX() == appleX && snake.getSnakeY() == appleY)
        {
            return true;
        }
        return  false;
    }

    //Painting Game
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
        g.fillRect(appleX, appleY, UNIT_SIZE, UNIT_SIZE);
    }
    public void newApple()
    {
        rng = new RandomNumGenerator();      
        
        //g.setColor(Color.red);      
        appleX = rng.getRandomNumber(SCREEN_WIDTH / UNIT_SIZE)*UNIT_SIZE;        
        appleY = rng.getRandomNumber(SCREEN_HEIGHT / UNIT_SIZE)*UNIT_SIZE;
        //g.fillRect(appleX, appleY, UNIT_SIZE, UNIT_SIZE);
    }
    public void drawSnake(Graphics g)
    {
        //Body of snake
        g.setColor(new Color(100, 180, 50));
        for (int i = 0; i < snake.getBodyParts().size(); i++)
        {                
            g.fillRect(snake.getBodyParts().get(i).getSnakeBodyX(), snake.getBodyParts().get(i).getSnakeBodyY(), UNIT_SIZE, UNIT_SIZE);       
        }
        //Heah of Snake
        g.setColor(Color.green);           
        g.fillRect(snake.getSnakeX(), snake.getSnakeY(), UNIT_SIZE, UNIT_SIZE);    
        
    }

    public class MyKeyAdapter extends KeyAdapter
     {
        @Override
        public void keyPressed(KeyEvent e)
        {
            switch (e.getKeyCode()) 
            {
                case KeyEvent.VK_A:
                if (snake.getDirection() != 'R') snake.setDirection('L');                                                    
                break;     
                case KeyEvent.VK_W:
                if (snake.getDirection() != 'D') snake.setDirection('U');                                    
                break;   
                case KeyEvent.VK_D:
                if (snake.getDirection() != 'L') snake.setDirection('R');                                    
                break;     
                case KeyEvent.VK_S:
                if (snake.getDirection() != 'U') snake.setDirection('D');                                    
                break;       
                
            }
        }
        
    }
    
}
