import java.util.ArrayList;
import java.util.LinkedList;
import java.awt.*;

public class Snake {

    //Data    
    private static ArrayList<SnakeBody> bodyParts = new ArrayList<SnakeBody>();
    private int length = bodyParts.size();
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
        
        
        //Temp Collision
        if (snakeX < 0)
        {
            snakeX = 600;
        }
        else if (snakeX > 600)
        {
            snakeX = 0;
        }
        if (snakeY < 0)
        {
            snakeY = 600;
        }
        else if (snakeY > 600)
        {
            snakeY = 0;
        }
       
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

    //Set Methods
    public void setDirection(char direction)
    {
        this.direction = direction;
    }

   
    
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
