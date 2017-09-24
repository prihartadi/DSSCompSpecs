package com.example.prihartadi.dsscompspecs;

/**
 * Created by prihartadi on 10/07/2017.
 */

public class C_Motherboard {

    private int id;
    private String brand,code,socket,chipset,ram_type,pcie,mem_conn,form;
    private long price;
    private double performance;

    public C_Motherboard(){

    }
    public C_Motherboard(String brand,String code,String socket,String chipset,String ram_type,String pcie,String mem_conn,String form,long price,double performance){
        this.brand = brand;
        this.code = code;
        this.socket = socket;
        this.chipset = chipset;
        this.ram_type = ram_type;
        this.pcie = pcie;
        this.mem_conn = mem_conn;
        this.form = form;
        this.price = price;
        this.performance = performance;
    }
    public C_Motherboard(int id,String brand,String code,String socket,String chipset,String ram_type,String pcie,String mem_conn,String form,long price,double performance){
        this.id = id;
        this.brand = brand;
        this.code = code;
        this.socket = socket;
        this.chipset = chipset;
        this.ram_type = ram_type;
        this.pcie = pcie;
        this.mem_conn = mem_conn;
        this.form = form;
        this.price = price;
        this.performance = performance;
    }
    //setter
    public void setId(int id){this.id = id;};
    public void setBrand(String brand){this.brand = brand;};
    public void setCode(String code){this.code = code;};
    public void setSocket(String socket){this.socket = socket;};
    public void setChipset(String chipset){this.chipset = chipset;};
    public void setRamType(String ram_type){this.ram_type = ram_type;};
    public void setPcie(String pcie){this.pcie = pcie;};
    public void setMemConn(String mem_conn){this.mem_conn = mem_conn;};
    public void setForm(String form){this.form = form;};
    public void setPrice(long price){this.price = price;};
    public void setPerformance(double performance){this.performance = performance;};
    //getter
    public long getId(){return this.id;}
    public String getBrand(){return this.brand ;}
    public String getCode(){return this.code;}
    public String getSocket(){return this.socket;}
    public String getChipset(){return this.chipset ;}
    public String getRamType(){return this.ram_type;}
    public String getPcie(){return this.pcie;}
    public String getMemConn(){return this.mem_conn;}
    public String getForm(){return this.form;}
    public long getPrice(){return this.price;}
    public double getPerformance(){return this.performance ;}

}
