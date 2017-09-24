package com.example.prihartadi.dsscompspecs;

import java.util.Arrays;

/**
 * Created by prihartadi on 11/07/2017.
 */

public class C_State implements Cloneable {

    private long totalBudget;
    private int category;
    private C_Processor processor;
    private C_Motherboard motherboard;
    private C_Ram ram;
    private C_Gpu gpu;
    private C_Power power;
    private C_Storage storage;
    private C_Monitor monitor;
    private C_Casing casing;
    private C_Mouse mouse;
    private C_Keyboard keyboard;

    private long totalPrice;
    private double totalPerformance;
    private int[] trigger = {1,1,1,1,1,1,1,1,1,1};
    private int flag_update = 0;

    private double[] weight;

    final double[][] weight1 = {
            {20.0, 10.0, 15.0, 8.0, 4.0, 16.0, 22.0, 1.0, 2.0, 2.0},
            {20.0, 10.0, 12.0, 25.0, 5.0, 10.0, 10.0, 2.0, 3.0, 3.0},
            {13.0, 10.0, 10.0, 11.0, 5.0, 22.0, 25.0, 2.0, 1.0, 1.0},
            {25.0, 10.0, 14.0, 13.0, 4.0, 16.0, 15.0, 1.0, 1.0, 1.0}
    };

//    final double[][] weight1 = {
//            {20.0, 10.0, 17.0, 6.0, 4.0, 16.0, 22.0, 1.0, 2.0, 2.0},
//            {20.0, 10.0, 12.0, 25.0, 5.0, 10.0, 10.0, 2.0, 3.0, 3.0},
//            {10.0, 8.0, 10.0, 11.0, 5.0, 24.0, 28.0, 2.0, 1.0, 1.0},
//            {25.0, 10.0, 14.0, 10.0, 4.0, 18.0, 16.0, 1.0, 1.0, 1.0}
//    };

    final double[][] weight2 = {
            {4.681818182,4.681818182,3.863636364,4.681818182,3.863636364,4.681818182,4.681818182,3.863636364,1.818181818,1.818181818},
            {10.0,2.227272727,3.863636364,10.0,1.818181818,3.863636364,2.636363636,1.409090909,1.409090909,1.409090909},
            {5.909090909,1.818181818,5.909090909,5.909090909,3.863636364,5.909090909,3.863636364,1.818181818,1.818181818,1.818181818},
            {7.954545455,3.863636364,5.909090909,5.909090909,3.045454545,4.681818182,3.454545455,1.0,1.409090909,1.409090909}
    };

    public C_State(){}

    public C_State(long totalBudget, int category, C_Processor processor, C_Motherboard motherboard, C_Ram ram, C_Gpu gpu, C_Power power, C_Storage storage, C_Monitor monitor, C_Casing casing, C_Mouse mouse, C_Keyboard keyboard) {
        this.totalBudget = totalBudget;
        this.category = category;
        this.processor = processor;
        this.motherboard = motherboard;
        this.ram = ram;
        this.gpu = gpu;
        this.power = power;
        this.storage = storage;
        this.monitor = monitor;
        this.casing = casing;
        this.mouse = mouse;
        this.keyboard = keyboard;
    }

    //setter
    public void setTotalBudget (long budget){this.totalBudget = budget;}
    public void setCategory (int category){this.category = category;}
    public void setProcessor(C_Processor processor_x){this.processor = processor_x;}
    public void setMotherboard(C_Motherboard motherboard_x){this.motherboard = motherboard_x;}
    public void setRam(C_Ram ram_x){this.ram = ram_x;}
    public void setGpu(C_Gpu gpu_x){this.gpu = gpu_x;}
    public void setPower(C_Power power_x){this.power = power_x;}
    public void setStorage(C_Storage storage_x){this.storage = storage_x;}
    public void setMonitor(C_Monitor monitor_x){this.monitor = monitor_x;}
    public void setCasing(C_Casing casing_x){this.casing = casing_x;}
    public void setMouse(C_Mouse mouse_x){this.mouse = mouse_x;}
    public void setKeyboard(C_Keyboard keyboard_x){this.keyboard = keyboard_x;}
    public void setWeight(int category){
        this.weight = this.weight1[category];
    }
    public void setWeight(double[] newWeight){
        this.weight = newWeight;
    }
    public void setTrigger(int[] trigger){
        this.trigger = trigger;
    }
    public void setFlag_update(int flag_update){
        this.flag_update = flag_update;
    }
    //getter
    public long getTotalBudget(){return this.totalBudget;}
    public int getCategory(){return this.category;}
    public C_Processor getProcessor(){return this.processor;}
    public C_Motherboard getMotherboard(){return this.motherboard;}
    public C_Ram getRam(){return this.ram;}
    public C_Gpu getGpu(){return this.gpu;}
    public C_Power getPower(){return this.power;}
    public C_Storage getStorage(){return this.storage;}
    public C_Monitor getMonitor(){return this.monitor;}
    public C_Casing getCasing(){return this.casing;}
    public C_Mouse getMouse(){return this.mouse;}
    public C_Keyboard getKeyboard(){return this.keyboard;}
//    public double[] getWeight(int index){
//        return weight1[index];
//    }
    public double[] getWeight(){
        return this.weight;
    }
    public int[] getTrigger(){
        return this.trigger;
    }
    public int getFlag_update(){
        return this.flag_update;
    }


    public long getTotalPrice() {
        if(flag_update == 0){
            this.totalPrice =
                    this.processor.getPrice() +
                            this.motherboard.getPrice() +
                            this.ram.getPrice() +
                            this.gpu.getPrice() +
                            this.power.getPrice() +
                            this.storage.getPrice() +
                            this.monitor.getPrice() +
                            this.casing.getPrice() +
                            this.mouse.getPrice() +
                            this.keyboard.getPrice()
            ;
        }else if(flag_update == 1|| flag_update == 2){
            this.totalPrice =
                    (this.processor.getPrice()*this.trigger[0]) +
                            (this.motherboard.getPrice()*this.trigger[1]) +
                            (this.ram.getPrice()*this.trigger[2]) +
                            (this.gpu.getPrice()*this.trigger[3]) +
                            (this.power.getPrice()*this.trigger[4]) +
                            (this.storage.getPrice()*this.trigger[5]) +
                            (this.monitor.getPrice()*this.trigger[6]) +
                            (this.casing.getPrice()*this.trigger[7]) +
                            (this.mouse.getPrice()*this.trigger[8]) +
                            (this.keyboard.getPrice()*this.trigger[9])
            ;
        }
        return this.totalPrice;
    }

    public double getTotalPerformance() {
        this.totalPerformance =
                this.processor.getPerformance() * this.weight[0] +
                        this.motherboard.getPerformance() * this.weight[1] +
                        this.ram.getPerformance() * this.weight[2] +
                        this.gpu.getPerformance() * this.weight[3] +
                        this.power.getPerformance() * this.weight[4] +
                        this.storage.getPerformance() * this.weight[5] +
                        this.monitor.getPerformance() * this.weight[6] +
                        this.casing.getPerformance() * this.weight[7] +
                        this.mouse.getPerformance() * this.weight[8] +
                        this.keyboard.getPerformance() * this.weight[9];
        return this.totalPerformance;
    }

    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public String[] printState(){
        String[] msg = {
                "total_budget = "+totalBudget,
                "category = "+category,
                "trigger = "+Arrays.toString(trigger),
                "weight = "+Arrays.toString(weight),
                "flag_update = "+flag_update,
                "processor = "+processor.getBrand()+"/"+processor.getCode()+"/"+processor.getSocket()+"/"+processor.getCore()+"/"+processor.getCache()+"/"+processor.getSpeed()+"/"+processor.getTdp()+"/"+processor.getBenchmark()+"/"+processor.getPrice()+"/"+processor.getPerformance(),
                "motherboard = "+motherboard.getBrand()+"/"+motherboard.getCode()+"/"+motherboard.getSocket()+"/"+motherboard.getChipset()+"/"+motherboard.getRamType()+"/"+motherboard.getPcie()+"/"+motherboard.getMemConn()+"/"+motherboard.getPrice()+"/"+motherboard.getPerformance(),
                "ram = "+ram.getBrand()+"/"+ram.getCode()+"/"+ram.getRamType()+"/"+ram.getPiece()+"/"+ram.getSize()+"/"+ram.getTotalSize()+"/"+ram.getSpeed()+"/"+ram.getPrice()+"/"+ram.getPerformance(),
                "gpu = "+gpu.getBrand()+"/"+gpu.getCode()+"/"+gpu.getChipset()+"/"+gpu.getPcie()+"/"+gpu.getVramType()+"/"+gpu.getVramSize()+"/"+gpu.getBitrate()+"/"+gpu.getTdp()+"/"+gpu.getBenchmark()+"/"+gpu.getPrice()+"/"+gpu.getPerformance(),
                "power = "+power.getBrand()+"/"+power.getCode()+"/"+power.getPower()+"/"+power.getPrice()+"/"+power.getPerformance(),
                "storage = "+storage.getBrand()+"/"+storage.getCode()+"/"+storage.getType()+"/"+storage.getMemConn()+"/"+storage.getSize()+"/"+storage.getPrice()+"/"+storage.getPerformance(),
                "monitor = "+monitor.getBrand()+"/"+monitor.getCode()+"/"+monitor.getWide()+"/"+monitor.getResX()+"/"+monitor.getResY()+"/"+monitor.getPrice()+"/"+monitor.getPerformance(),
                "casing = "+casing.getBrand()+"/"+casing.getCode()+"/"+casing.getPrice()+"/"+casing.getPerformance(),
                "mouse = "+mouse.getBrand()+"/"+mouse.getCode()+"/"+mouse.getPrice()+"/"+mouse.getPerformance(),
                "keyboard = "+keyboard.getBrand()+"/"+keyboard.getCode()+"/"+keyboard.getPrice()+"/"+keyboard.getPerformance(),
                "total_price = "+getTotalPrice(),
                "total_performance = "+getTotalPerformance()
        };
        return msg;
    }

}
