import java.util.Random;

public class RandomNumGenerator {

    Random  random  = new Random();
    
    public int getRandomNumber(int maxValue)
    {
        return random.nextInt(maxValue);
    }
}

