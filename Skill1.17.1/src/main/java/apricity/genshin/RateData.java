package apricity.genshin;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

public class RateData {
    private static List<String> ATK = new LinkedList<String>(){{
        for(int i=0;i<3;i++)add("1");
        IntStream.range(0,2).forEach(i->add("2"));
        IntStream.range(0,1).forEach(i->add("3"));
    }};
    private static List<String> CRIT_Rate = new LinkedList<String>(){{
        IntStream.range(0,3).forEach(i->add("3.1"));
        IntStream.range(0,2).forEach(i->add("3.5"));
        IntStream.range(0,1).forEach(i->add("3.9"));
    }};
    private static List<String> CRIT_Damage = new LinkedList<String>(){{
        IntStream.range(0,3).forEach(i->add("6.2"));
        IntStream.range(0,2).forEach(i->add("7"));
        IntStream.range(0,1).forEach(i->add("7.8"));
    }};

    public static String getRandomATK(){
        Random r = new Random();
        int i = r.nextInt(ATK.size());
        return "ATK: "+ATK.get(i);
    }

    public static String getRandomCRIT_Rate(){
        Random r = new Random();
        int i = r.nextInt(CRIT_Rate.size());
        return "CRIT_Rate(%): "+CRIT_Rate.get(i);
    }

    public static String getRandomCRIT_Damage(){
        Random r = new Random();
        int i = r.nextInt(CRIT_Damage.size());
        return "CRIT_Damage(%): "+CRIT_Damage.get(i);
    }
}
