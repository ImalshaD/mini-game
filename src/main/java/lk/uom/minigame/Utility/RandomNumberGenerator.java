package lk.uom.minigame.Utility;

import java.util.Random;

public class RandomNumberGenerator {
    private Random random;
    private int max;



    private int min;
    private static RandomNumberGenerator instance = null;
    public RandomNumberGenerator(){
        random = new Random();
    }
    public static RandomNumberGenerator getInstance(){
        if (instance == null){
            instance = new RandomNumberGenerator();
        }
        return instance;
    }
    public void setMax(int max) {
        this.max = max;
    }

    public void setMin(int min) {
        this.min = min;
    }
    public int getRandomNumber(){
        return random.nextInt(max - min + 1) + min;
    }

}
