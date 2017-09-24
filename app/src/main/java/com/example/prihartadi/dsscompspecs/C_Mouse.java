package com.example.prihartadi.dsscompspecs;

/**
 * Created by prihartadi on 10/07/2017.
 */

public class C_Mouse {

    private int id;
    private String brand,code;
    private long price;
    private double performance;

    public C_Mouse(){}
    public C_Mouse(String brand,String code,long price,double performance){
        this.brand = brand;
        this.code = code;
        this.price = price;
        this.performance = performance;
    }
    public C_Mouse(int id,String brand,String code,long price,double performance){
        this.id = id;
        this.brand = brand;
        this.code = code;
        this.price = price;
        this.performance = performance;
    }
    //setter
    public void setId(int id){this.id = id;}
    public void setBrand(String brand){this.brand = brand;}
    public void setCode(String code){this.code = code;}
    public void setPrice(long price){this.price = price;}
    public void setPerformance(double performance){this.performance = performance;}
    //getter
    public long getId(){return this.id;}
    public String getBrand(){return this.brand;}
    public String getCode(){return this.code;}
    public long getPrice(){return this.price;}
    public double getPerformance(){return this.performance;}

}
