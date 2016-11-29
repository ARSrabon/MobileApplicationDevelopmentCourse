package com.example.msrabon.activeandroidtest.models;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by msrabon on 11/29/16.
 */

@Table(name = "sellhistories")
public class SellHistory extends Model {

    @Column(name = "p_id")
    private int product_id;

    @Column(name = "quantity")
    private int sell_quantity;

    @Column(name = "sold_price")
    private double sold_price;

    @Column(name = "timestamp")
    private String timeStamp;

    public SellHistory(){
        super();
    }

    public SellHistory(int product_id, int sell_quantity, double sold_price) {
        this.product_id = product_id;
        this.sell_quantity = sell_quantity;
        this.sold_price = sold_price;
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.YYY hh:mm:ss a");
        this.timeStamp = simpleDateFormat.format(date);
    }

    public int getProduct_id() {
        return product_id;
    }

    public int getSell_quantity() {
        return sell_quantity;
    }

    public double getSold_price() {
        return sold_price;
    }

    public String getTimeStamp() {
        return timeStamp;
    }
}
