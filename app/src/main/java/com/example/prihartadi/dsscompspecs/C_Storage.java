package com.example.prihartadi.dsscompspecs;

/**
 * Created by prihartadi on 10/07/2017.
 */

public class C_Storage {

    private int id;
    private String brand,code,type,mem_conn,size;
    private long price;
    private double performance;

    public C_Storage(){}
    public C_Storage(String brand,String code,String type,String mem_conn,String size,long price,double performance){
        this.brand = brand;
        this.code = code;
        this.type = type;
        this.mem_conn = mem_conn;
        this.size = size;
        this.price = price;
        this.performance = performance;
    }
    public C_Storage(int id,String brand,String code,String type,String mem_conn,String size,long price,double performance){
        this.id = id;
        this.brand = brand;
        this.code = code;
        this.type = type;
        this.mem_conn = mem_conn;
        this.size = size;
        this.price = price;
        this.performance = performance;
    }
    //setter
    public void setId(int id){this.id = id;}
    public void setBrand(String brand){this.brand = brand;}
    public void setCode(String code){this.code = code;}
    public void setType(String type){this.type = type;}
    public void setMemConn(String mem_conn){this.mem_conn = mem_conn;}
    public void setSize(String size){this.size = size;}
    public void setPrice(long price){this.price = price;}
    public void setPerformance(double performance){this.performance = performance;}
    //getter
    public long getId(){return this.id;}
    public String getBrand(){return this.brand;}
    public String getCode(){return this.code;}
    public String getType(){return this.type;}
    public String getMemConn(){return this.mem_conn;}
    public String getSize(){return this.size;}
    public long getPrice(){return this.price;}
    public double getPerformance(){return this.performance;}

}
