package com.example.student.productinventory.models;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by msrabon on 11/29/16.
 */

@Table(name = "sellhistories")
public class SellHistory extends Model {

    @Column(name = "p_id")
    private long product_id;

    @Column(name = "p_uid")
    private  String product_UID;

    @Column(name = "nid")
    private String NID;

    @Column(name = "quantity")
    private long sell_quantity;

    @Column(name = "sold_price")
    private double sold_price;

    @Column(name = "due")
    private double due;

    @Column(name = "timestamp")
    private String timeStamp;

    public SellHistory() {
        super();
    }

    /**
     *
     * @param product_id
     * @param product_UID
     * @param NID
     * @param sell_quantity
     * @param sold_price
     * @param due
     * @param timeStamp
     */
    public SellHistory(long product_id, String product_UID, String NID, long sell_quantity,
                       double sold_price, double due, String timeStamp) {


        this.product_id = product_id;
        this.product_UID = product_UID;
        this.NID = NID;
        this.sell_quantity = sell_quantity;
        this.sold_price = sold_price;
        this.due = due;
        this.timeStamp = timeStamp;
    }

    public long getProduct_id() {
        return product_id;
    }

    public long getSell_quantity() {
        return sell_quantity;
    }

    public double getSold_price() {
        return sold_price;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public String getProduct_UID() {
        return product_UID;
    }

    public String getNID() {
        return NID;
    }

    public double getDue() {
        return due;
    }

    public Product getProduct() {
        return Product.load(Product.class,product_id);
    }


}
