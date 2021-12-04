import java.util.LinkedList;
import java.awt.*;

public class Snake {

    //Data
    private static LinkedList<Snake> body = new LinkedList<Snake>();
    private int length = body.size();
    private char direction = 'L';
    private int snakeX;
    private int snakeY;

    //Constructor
    public Snake(int snakeX, int snakeY)
    {    
        this.snakeX = snakeX;
        this.snakeY = snakeY;   
       
    }

    //Main Methods
    public void move(int UNIT_SIZE)
    {
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
    public LinkedList<Snake> getBody()
    {
        return body;
    }
    
}
