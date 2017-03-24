package com.zcorp.app.stocksearch;

/**
 * Created by Mankirat on 12-03-2017.
 */

public class Stocks {

    private String sticker,name,currency,change;


    public Stocks(String name,String sticker,String currency,String change){
        this.sticker=sticker;
        this.name=name;
        this.currency=currency;
        this.change=change;
    }


    public Stocks(){

    }


    public String getSticker() {
        return sticker;
    }

    public String getName() {
        return name;
    }

    public String getCurrency() {
        return currency;
    }

    public String getChange() {
        return change;
    }

    public void setSticker(String sticker) {
        this.sticker = sticker;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public void setChange(String change) {
        this.change = change;
    }
}
