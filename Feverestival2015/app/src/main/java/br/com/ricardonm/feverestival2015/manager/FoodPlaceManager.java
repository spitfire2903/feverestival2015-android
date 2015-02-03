package br.com.ricardonm.feverestival2015.manager;

import com.google.common.collect.Lists;

import java.util.List;

import br.com.ricardonm.feverestival2015.model.FoodPlaceTO;

/**
 * Created by ricardomiranda on 30/01/15.
 */
public class FoodPlaceManager {
    public static List<FoodPlaceTO> getFoodPlaces(){
        List<FoodPlaceTO> list = null;

        list = Lists.newArrayList(FoodPlaceTO.findAll(FoodPlaceTO.class));
        //list = null;//IteratorUtils.toList(FoodPlaceTO.findAll(FoodPlaceTO.class));

        return list;
    }
}
