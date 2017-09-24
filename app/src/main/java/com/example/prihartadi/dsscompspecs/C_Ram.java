package com.example.prihartadi.dsscompspecs;

/**
 * Created by prihartadi on 10/07/2017.
 */

public class C_Ram {

    private int id;
    private String brand,code,ram_type,piece,size,total_size,speed;
    private long price;
    private double performance;

    public C_Ram(){};
    public C_Ram(String brand,String code,String ram_type,String piece,String size,String total_size,String speed,long price,double performance){
        this.brand = brand;
        this.code = code;
        this.ram_type = ram_type;
        this.piece = piece;
        this.size = size;
        this.total_size = total_size;
        this.speed = speed;
        this.price = price;
        this.performance = performance;
    };
    public C_Ram(int id,String brand,String code,String ram_type,String piece,String size,String total_size,String speed,long price,double performance){
        this.id = id;
        this.brand = brand;
        this.code = code;
        this.ram_type = ram_type;
        this.piece = piece;
        this.size = size;
        this.total_size = total_size;
        this.speed = speed;
        this.price = price;
        this.performance = performance;
    };
    //setter
    public void setId(int id){this.id = id;}
    public void setBrand(String brand){this.brand = brand;}
    public void setCode(String code){this.code = code;}
    public void setRamType(String ram_type){this.ram_type = ram_type;}
    public void setPiece(String piece){this.piece = piece;}
    public void setSize(String size){this.size = size;}
    public void setTotalSize(String total_size){this.total_size = total_size;}
    public void setSpeed(String speed){this.speed = speed;}
    public void setPrice(long price){this.price = price;}
    public void setPerformance(double performance){this.performance = performance;}
    //getter
    public long getId(){return this.id;}
    public String getBrand(){return this.brand;}
    public String getCode(){return this.code;}
    public String getRamType(){return this.ram_type;}
    public String getPiece(){return this.piece;}
    public String getSize(){return this.size;}
    public String getTotalSize(){return this.total_size;}
    public String getSpeed(){return this.speed;}
    public long getPrice(){return this.price;}
    public double getPerformance(){return this.performance;}

}
