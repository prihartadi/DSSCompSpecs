package com.example.prihartadi.dsscompspecs;

/**
 * Created by prihartadi on 10/07/2017.
 */

public class C_Processor {

    private int id;
    private String code, brand, socket, core, cache, speed,tdp, benchmark;
    private long price;
    private double performance;

    public C_Processor(){

    }

    public C_Processor(String brand, String code, String socket, String core, String cache, String speed, String tdp, String benchmark, long price, double performance) {
        this.code = code;
        this.brand = brand;
        this.socket = socket;
        this.core = core;
        this.cache = cache;
        this.speed = speed;
        this.tdp = tdp;
        this.benchmark = benchmark;
        this.price = price;
        this.performance = performance;
    }

    public C_Processor(int id, String brand, String code, String socket, String core, String cache, String speed, String tdp, String benchmark, long price, double performance) {
        this.id = id;
        this.code = code;
        this.brand = brand;
        this.socket = socket;
        this.core = core;
        this.cache = cache;
        this.speed = speed;
        this.tdp = tdp;
        this.benchmark = benchmark;
        this.price = price;
        this.performance = performance;
    }

    //setter
    public void setId (int id){
        this.id = id;
    }
    public void setBrand (String brand){
        this.brand = brand;
    }
    public void setCode (String code){
        this.code = code;
    }
    public void setCore (String core){
        this.core = core;
    }
    public void setCache (String cache){
        this.cache = cache;
    }
    public void setSpeed (String speed){
        this.speed = speed;
    }
    public void setTdp (String tdp){
        this.tdp = tdp;
    }
    public void setSocket (String socket){
        this.socket = socket;
    }
    public void setBenchmark (String benchmark){
        this.benchmark = benchmark;
    }
    public void setPrice (long price){
        this.price = price;
    }
    public void setPerformance (double performance){
        this.performance = performance;
    }

    //getter
    public long getId (){
        return this.id;
    }
    public String getBrand (){
        return this.brand;
    }
    public String getCode (){
        return this.code;
    }
    public String getCore (){
        return this.core;
    }
    public String getCache (){
        return this.cache;
    }
    public String getSpeed (){
        return this.speed;
    }
    public String getTdp (){
        return this.tdp;
    }
    public String getSocket (){
        return this.socket;
    }
    public String getBenchmark (){
        return this.benchmark;
    }
    public long getPrice (){
        return this.price;
    }
    public double getPerformance (){
        return this.performance;
    }

}
