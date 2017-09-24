package com.example.prihartadi.dsscompspecs;

/**
 * Created by prihartadi on 10/07/2017.
 */

public class C_Monitor {

    private int id;
    private String brand,code,wide,res_x,res_y;
    private long price;
    private double performance;

    public C_Monitor(){}
    public C_Monitor(String brand,String code,String wide,String res_x,String res_y,long price,double performance){
        this.brand = brand;
        this.code = code;
        this.wide = wide;
        this.res_x = res_x;
        this.res_y = res_y;
        this.price = price;
        this.performance = performance;
    }
    public C_Monitor(int id,String brand,String code,String wide,String res_x,String res_y,long price,double performance){
        this.id = id;
        this.brand = brand;
        this.code = code;
        this.wide = wide;
        this.res_x = res_x;
        this.res_y = res_y;
        this.price = price;
        this.performance = performance;
    }
    //setter
    public void setId(int id){this.id = id;}
    public void setBrand(String brand){this.brand = brand;}
    public void setCode(String code){this.code = code;}
    public void setSize(String size){this.wide = size;}
    public void setResX(String res_x){this.res_x = res_x;}
    public void setResY(String res_y){this.res_y = res_y;}
    public void setPrice(long price){this.price = price;}
    public void setPerformance(double performance){this.performance = performance;}
    //getter
    public long getId(){return this.id;}
    public String getBrand(){return this.brand;}
    public String getCode(){return this.code;}
    public String getWide(){return this.wide;}
    public String getResX(){return this.res_x;}
    public String getResY(){return this.res_y;}
    public long getPrice(){return this.price;}
    public double getPerformance(){return this.performance;}

}
