package com.example.prihartadi.dsscompspecs;

/**
 * Created by prihartadi on 10/07/2017.
 */

public class C_Power {

    private int id;
    private String brand,code,power,certification;
    private long price;
    private double performance;

    public C_Power(){}
    public C_Power(String brand,String code,String power,String certification,long price,double performance){
        this.brand = brand;
        this.code = code;
        this.power = power;
        this.certification = certification;
        this.price = price;
        this.performance = performance;
    }
    public C_Power(int id,String brand,String code,String power,String certification,long price,double performance){
        this.id = id;
        this.brand = brand;
        this.code = code;
        this.power = power;
        this.certification = certification;
        this.price = price;
        this.performance = performance;
    }
    //setter
    public void setId(int id){this.id = id;}
    public void setBrand(String brand){this.brand = brand;}
    public void setCode(String code){this.code = code;}
    public void setPower(String power){this.power = power;}
    public void setCertification(String certification){this.certification = certification;}
    public void setPrice(long price){this.price = price;}
    public void setPerformance(double performance){this.performance = performance;}
    //getter
    public long getId(){return this.id;}
    public String getBrand(){return this.brand;}
    public String getCode(){return this.code;}
    public String getPower(){return this.power;}
    public String getCertification(){return this.certification;}
    public long getPrice(){return this.price;}
    public double getPerformance(){return this.performance;}

}
