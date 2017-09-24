package com.example.prihartadi.dsscompspecs;

/**
 * Created by prihartadi on 10/07/2017.
 */

public class C_Gpu {

    private int id;
    private String brand,code,chipset,pcie,vram_type,vram_size,bitrate,tdp,benchmark;
    private long price;
    private double performance;

    public C_Gpu(){}
    public C_Gpu(String brand,String code,String chipset,String pcie,String vram_type,String vram_size,String bitrate,String tdp,String benchmark,long price,double performance){
        this.brand = brand;
        this.code = code;
        this.chipset = chipset;
        this.pcie = pcie;
        this.vram_type = vram_type;
        this.vram_size = vram_size;
        this.bitrate = bitrate;
        this.tdp = tdp;
        this.benchmark = benchmark;
        this.price = price;
        this.performance = performance;
    }
    public C_Gpu(int id,String brand,String code,String chipset,String pcie,String vram_type,String vram_size,String bitrate,String tdp,String benchmark,long price,double performance){
        this.id = id;
        this.brand = brand;
        this.code = code;
        this.chipset = chipset;
        this.pcie = pcie;
        this.vram_type = vram_type;
        this.vram_size = vram_size;
        this.bitrate = bitrate;
        this.tdp = tdp;
        this.benchmark = benchmark;
        this.price = price;
        this.performance = performance;
    }
    //setter
    public void setId(int id){this.id = id;}
    public void setBrand(String brand){this.brand = brand;}
    public void setCode(String code){this.code = code;}
    public void setChipset(String chipset){this.chipset = chipset;}
    public void setPcie(String pcie){this.pcie = pcie;}
    public void setVramType(String vram_type){this.vram_type = vram_type;}
    public void setVramSize(String vram_size){this.vram_size = vram_size;}
    public void setBitrate(String bitrate){this.bitrate = bitrate;}
    public void setTdp(String tdp){this.tdp = tdp;}
    public void setBenchmark(String benchmark){this.benchmark = benchmark;}
    public void setPrice(long price){this.price = price;}
    public void setPerformance(double performance){this.performance = performance;}
    //getter
    public long getId(){return this.id;}
    public String getBrand(){return this.brand;}
    public String getCode(){return this.code;}
    public String getChipset(){return this.chipset;}
    public String getPcie(){return this.pcie;}
    public String getVramType(){return this.vram_type;}
    public String getVramSize(){return this.vram_size;}
    public String getBitrate(){return this.bitrate;}
    public String getTdp(){return this.tdp;}
    public String getBenchmark(){return this.benchmark;}
    public long getPrice(){return this.price;}
    public double getPerformance(){return this.performance;}

}
