import java.util.Random;

public class RandomNumGenerator {

    Random  random;
    
    public int getRandomNumber(int maxValue)
    {
        random  = new Random();
        return random.nextInt(maxValue);
    }
}

