import java.util.ArrayList;
import java.util.LinkedList;
import java.awt.*;

public class Snake {

    //Data
    private static ArrayList<Integer> bodyPartsX = new ArrayList<Integer>();
    private static ArrayList<Integer> bodyPartsY = new ArrayList<Integer>();
    private static ArrayList<SnakeBody> bodyParts = new ArrayList<SnakeBody>();
    private int length = bodyPartsX.size();
    private char direction = 'L';
    private int snakeX;
    private int snakeY;

    //Constructor
    public Snake(int snakeX, int snakeY)
    {    
        this.snakeX = snakeX;
        this.snakeY = snakeY;    
        
        //Adding  2 body parts
        bodyPartsX.add(300);
        bodyPartsY.add(300);
        bodyPartsX.add(300);
        bodyPartsY.add(300);

        bodyParts.add(new SnakeBody(300, 300));
        bodyParts.add(new SnakeBody(300, 300));
        bodyParts.add(new SnakeBody(300, 300));
        bodyParts.add(new SnakeBody(300, 300));
                
       
    }

    //Main Methods
    public void move(int UNIT_SIZE)
    {        
        for (int i = bodyParts.size() - 1; i > 0; i--)
        {    
            //all tail follow previous piece
            if (i != 0)
            {
                //System.out.println("Body ^ " + i + ": " + bodyParts.get(i).getSnakeBodyX() + "");
                bodyParts.set(i, bodyParts.get(i - 1));  
                //System.out.println("Body v" + i + ": " + bodyParts.get(i).getSnakeBodyX() + "\n");
            }                            
                        
        } 
        bodyParts.get(0).setSnakeBodyX(snakeX);

        snakeX -= UNIT_SIZE;    
       
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
    public ArrayList<Integer> getBodyPartsX()
    {
        return bodyPartsX;
    }
    public ArrayList<Integer> getBodyPartsY()
    {
        return bodyPartsY;
    }
    public ArrayList<SnakeBody> getBodyParts()
    {
        return bodyParts;       
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
