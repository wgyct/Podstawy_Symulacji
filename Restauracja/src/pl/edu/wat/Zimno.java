package pl.edu.wat;

import java.util.Random;

public class Zimno {
    int random = (int)Math.floor(Math.random()*(70-20+1)+20);
    public int getZimno (){
        return random;
    }
}
