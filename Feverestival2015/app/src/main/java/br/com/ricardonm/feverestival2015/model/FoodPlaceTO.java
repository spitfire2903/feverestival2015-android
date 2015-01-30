package br.com.ricardonm.feverestival2015.model;

import com.orm.SugarRecord;

/**
 * Created by ricardomiranda on 30/01/15.
 */
public class FoodPlaceTO extends SugarRecord<FoodPlaceTO> {
    private String name;
    private String address;
    private String phone;
    private String site;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public FoodPlaceTO() {

    }
}
