import javax.swing.JPanel;
import javax.swing.plaf.DimensionUIResource;


import java.awt.event.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.*;
import javax.swing.*;


public class GamePanel extends JPanel implements ActionListener {

    
    static final int SCREEN_WIDTH = 600;
    static final int SCREEN_HEIGHT = 600;
    static final int UNIT_SIZE = 50;
    static final int GAME_UNITS = SCREEN_WIDTH * SCREEN_HEIGHT / UNIT_SIZE;
    static final int DELAY =  120;
    int appleX = 100;
    int appleY = 400;
    Timer timer;    
    RandomNumGenerator rng;
    Snake snake = new Snake(300, 300);

    //----------------------------------Running  the  Game------------------------------
    public GamePanel()
    {
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.black);
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAdapter());
        startGame();
    }
   
    public void startGame()
    {
        timer = new Timer(DELAY, this);
        timer.start();

        try {
            System.out.println("Current HighScore: " + getHighScore());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
    public void checkEatenApple()
    {
        if (snake.getSnakeX() == appleX && snake.getSnakeY() == appleY)
        {
            snake.grow();
            newApple();           
            System.out.println("Length: " + snake.getLength());
        }
        
    }
    public void checkWallCollisionEasyMode()
    {
        
        if (snake.getSnakeX() < 0)
        {
            snake.setSnakeX(SCREEN_WIDTH - UNIT_SIZE);
        }
        else if (snake.getSnakeX() > SCREEN_WIDTH - UNIT_SIZE)
        {
            snake.setSnakeX(0);
        }
        if (snake.getSnakeY() < 0)
        {
            snake.setSnakeY(SCREEN_HEIGHT - UNIT_SIZE);
        }
        else if (snake.getSnakeY() > SCREEN_HEIGHT - UNIT_SIZE)
        {
            snake.setSnakeY(0);
        }
    }
    public void checkWallCollisionHardMode()
    {
        
        if (snake.getSnakeX() < 0)
        {
            gameOver();
        }
        else if (snake.getSnakeX() > SCREEN_WIDTH - UNIT_SIZE)
        {
            gameOver();
        }
        if (snake.getSnakeY() < 0)
        {
            gameOver();
        }
        else if (snake.getSnakeY() > SCREEN_HEIGHT - UNIT_SIZE)
        {
            gameOver();
        }
    }
    public void newApple()
    {
        rng = new RandomNumGenerator();    
          
        appleX = rng.getRandomNumber(SCREEN_WIDTH / UNIT_SIZE)*UNIT_SIZE;        
        appleY = rng.getRandomNumber(SCREEN_HEIGHT / UNIT_SIZE)*UNIT_SIZE;
      
    }
    public void gameOver()
    {
        timer.stop();

        try 
        {
            checkHighScore();
        } catch (IOException e) 
        {            
            e.printStackTrace();
        }
    }
    public void superAwsomeRainbowMode(Graphics g)
    {
       
        for (int i = 0; i < snake.getBodyParts().size(); i++)
        {         
            g.setColor(new Color(rng.getRandomNumber(256), rng.getRandomNumber(150), rng.getRandomNumber(256)));       
            g.fillRect(snake.getBodyParts().get(i).getSnakeBodyX(), snake.getBodyParts().get(i).getSnakeBodyY(), UNIT_SIZE, UNIT_SIZE);       
        }
    }

    //----------Painting Game-------------------------
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
   
    public void drawSnake(Graphics g)
    {
        //Tail of Snake
        if (snake.getLength() > 30)
        {
            superAwsomeRainbowMode(g);
        }
        else
        {
            g.setColor(new Color(100, 180, 50));
            for (int i = 0; i < snake.getBodyParts().size(); i++)
            {                
                g.fillRect(snake.getBodyParts().get(i).getSnakeBodyX(), snake.getBodyParts().get(i).getSnakeBodyY(), UNIT_SIZE, UNIT_SIZE);       
            }
        }
        
        //Heah of Snake
        g.setColor(Color.green);           
        g.fillRect(snake.getSnakeX(), snake.getSnakeY(), UNIT_SIZE, UNIT_SIZE);    
        
    }

    //----------------------File Handling------------------------------
    public void checkHighScore() throws IOException
    {
        BufferedWriter bw;
        BufferedReader br = new BufferedReader(new FileReader("HighScore.txt"));
        
        String text = br.readLine();
        int highScore = Integer.parseInt(text);
        if (highScore < snake.getLength())
        {            
            System.out.println("NEW HIGHSCORE!!! : " + snake.getLength());

            bw = new BufferedWriter(new FileWriter("HighScore.txt"));
            bw.write(String.valueOf(snake.getLength()));
            bw.close();
            
            
        }        

        br.close();       
    }
    public int getHighScore() throws IOException
    {
        BufferedReader br = new BufferedReader(new FileReader("HighScore.txt"));
        
        String text = br.readLine();
        return Integer.parseInt(text);
    }

    //----------------------Event  Handling----------------------------
    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub

        snake.move(UNIT_SIZE); 
        checkWallCollisionHardMode();
        if (snake.checkTailCollision() == true)
        {
            gameOver();
        }

        checkEatenApple();

        repaint();
       
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
