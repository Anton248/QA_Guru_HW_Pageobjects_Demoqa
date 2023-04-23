package demoqa.utils;

import java.util.concurrent.ThreadLocalRandom;

public class Utils {

    static public <T> T randomValueFromArray(T[] array) {
        return array[ThreadLocalRandom.current().nextInt(0, array.length-1)];
    }

}
