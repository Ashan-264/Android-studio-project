package viewmodel;

import model.Bat;
import model.Enemy;
import model.Maps;

public class Map1Bat extends Maps {

    public Map1Bat(){};
    @Override
    public Enemy createEnemy() {
        Bat bat = Bat.getEnemy();
        return  bat;
    }


}
