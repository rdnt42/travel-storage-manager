package com.summerdev.travelstoragemanager.utils;

import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;

/**
 * Created with IntelliJ IDEA.
 * User: marowak
 * Date: 10.07.2022
 * Time: 14:53
 */
public class EasyRandomService {
    private final EasyRandom easyRandom;

    public EasyRandomService() {
        EasyRandomParameters parameters = new EasyRandomParameters();
        parameters.collectionSizeRange(10, 10);
        EasyRandom random = new EasyRandom(parameters);

        this.easyRandom = random;
    }

    public <T> T getObjectFromClass(Class<T> tClass) {
        return easyRandom.nextObject(tClass);
    }
}
