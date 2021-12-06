import java.util.ArrayList;
import java.util.LinkedList;
import java.awt.*;

public class Snake {

    //Data    
    private static ArrayList<SnakeBody> bodyParts = new ArrayList<SnakeBody>();
    private int length;
    private char direction = 'L';
    private int snakeX;
    private int snakeY;

    //Constructor
    public Snake(int snakeX, int snakeY)
    {    
        this.snakeX = snakeX;
        this.snakeY = snakeY;       

        bodyParts.add(new SnakeBody(300, 300));
        bodyParts.add(new SnakeBody(300, 300));                     
       
    }

    //Main Methods
    public void move(int UNIT_SIZE)
    {     
        //Tails Following head   
        for (int i = bodyParts.size() - 1; i > 0; i--)
        {    
            
            if (i != 0)
            {         
                bodyParts.get(i).setSnakeBodyX(bodyParts.get(i - 1).getSnakeBodyX());   
                bodyParts.get(i).setSnakeBodyY(bodyParts.get(i - 1).getSnakeBodyY());               
            }                            
                        
        } 
        bodyParts.get(0).setSnakeBodyX(snakeX);
        bodyParts.get(0).setSnakeBodyY(snakeY);

        //Moving Head
        if (direction == 'L') snakeX -= UNIT_SIZE;
        if (direction == 'U') snakeY -= UNIT_SIZE;
        if (direction == 'R') snakeX += UNIT_SIZE;
        if (direction == 'D') snakeY += UNIT_SIZE;

    }
    public void grow()
    {
        bodyParts.add(new SnakeBody(snakeX, snakeY));
        length = bodyParts.size() + 1; // +1 To account  for head
    }   
    public boolean checkTailCollision()
    {
        for (int i = 0; i < bodyParts.size(); i++)
        {
            if (snakeX == bodyParts.get(i).snakeBodyX && snakeY == bodyParts.get(i).snakeBodyY)
            {
                return true;
            }
        }
        return false;
    } 

    //Get Methods
    public int getSnakeX()
    {
        return snakeX;
    }
    public int getSnakeY()
    {
        return snakeY;
    }    
    public ArrayList<SnakeBody> getBodyParts()
    {
        return bodyParts;       
    }
    public char getDirection()
    {
        return direction;
    }
    public int getLength()
    {
        return length;
    }

    //Set Methods
    public void setDirection(char direction)
    {
        this.direction = direction;
    }
    public void setSnakeX(int snakeX)
    {
        this.snakeX = snakeX;
    }
    public void setSnakeY(int snakeY)
    {
        this.snakeY = snakeY;
    }
   
    //Sub Class
    public class SnakeBody 
    {
        private int snakeBodyX;
        private int snakeBodyY;
        
        SnakeBody(int snakeBodyX, int snakeBodyY)
        {
            this.snakeBodyX = snakeBodyX;
            this.snakeBodyY = snakeBodyY;
        }
      
        public int getSnakeBodyX()
        {
            return snakeBodyX;
        }
        public int getSnakeBodyY()
        {
            return snakeBodyY;
        }
        public void setSnakeBodyX(int snakeBodyX)
        {
            this.snakeBodyX = snakeBodyX;
        }
        public void setSnakeBodyY(int snakeBodyY)
        {
            this.snakeBodyY = snakeBodyY;
        }
    }
    
}
