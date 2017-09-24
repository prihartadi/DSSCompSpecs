package com.example.prihartadi.dsscompspecs;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.google.gson.Gson;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 * Created by prihartadi on 10/07/2017.
 */

public class C_SimulatedAnnealing {

    H_Database db;
    H_Data dataHelper;
    C_State tempState, finalState, bestState, minState;
    Intent intent;
    int flag, a, b, p1, p2, count, loop, flag_mbo, category, inCount;
    int n_processor,n_motherboard,n_ram,n_gpu,n_power,n_storage,n_monitor,n_casing,n_mouse,n_keyboard;
    int[] trigger;
    double t,p,pm;
    double[] weight;
    long budget, nn;

    List<C_Processor> allProcessors;
    List<C_Motherboard> allMotherboards;
    List<C_Ram> allRams;
    List<C_Gpu> allGpus;
    List<C_Power> allPowers;
    List<C_Storage> allStorages;
    List<C_Monitor> allMonitors;
    List<C_Casing> allCasings;
    List<C_Mouse> allMouses;
    List<C_Keyboard> allKeyboards;

    //H_BackgroundTask task;

    public void findRecomendation(Context context, C_State state, int type, int n, double t0, double tn) throws CloneNotSupportedException {

//        task = new H_BackgroundTask(context);
//        task.execute();

        db = new H_Database(context);
        dataHelper = new H_Data();

        intent = new Intent(context, A_Result.class);

        trigger = state.getTrigger();
        weight = state.getWeight();
        budget = state.getTotalBudget();
        flag = 0;
        count = 0;
        inCount = 0;
        loop = 0;
        t = t0;
        nn = n;

        List<Double> performance_array = new ArrayList<Double>();
        List<Double> price_array = new ArrayList<Double>();
        List<Double> temp_array = new ArrayList<Double>();
        List<C_State> state_array = new ArrayList<C_State>();
        Set<C_State> state_set = new HashSet<C_State>();

        Random ra = new Random();
        Random rb = new Random();
        Random rp = new Random();

        category = state.getCategory();

        tempState = (C_State) state.clone();
        finalState = (C_State) state.clone();
        bestState = (C_State) state.clone();
        minState = (C_State) state.clone();

        if(trigger[0]+trigger[1]+trigger[2]==3){
//            allProcessors = db.getAllProcessors("SELECT  * FROM processor");
            allProcessors = db.getAllProcessors("SELECT  * FROM processor where (price between "+ (weight[0]*budget/100/2) +" and "+ (weight[0]*budget/100/2*3) +")");
            n_processor = allProcessors.size();
            Log.v("LOG_processor",n_processor+"");
//            allMotherboards = db.getAllMotherboards("SELECT  * FROM motherboard");
            allMotherboards = db.getAllMotherboards("SELECT  * FROM motherboard where (price between "+ (weight[1]*budget/100/2) +" and "+ (weight[1]*budget/100/2*4) +")");
            n_motherboard = allMotherboards.size();
            Log.v("LOG_motherboard",n_motherboard+"");
//            allRams = db.getAllRams("SELECT  * FROM ram");
            allRams = db.getAllRams("SELECT  * FROM ram where (price between "+ (weight[2]*budget/100/2) +" and "+ (weight[2]*budget/100/2*3) +")");
            n_ram = allRams.size();
            Log.v("LOG_ram",n_ram+"");
        } else if(trigger[0]+trigger[1]+trigger[2]==2){
            if (trigger[0]==0){
                allMotherboards = db.getAllMotherboards("SELECT  * FROM motherboard where socket like '%"+state.getProcessor().getSocket()+"%'");
                n_motherboard = allMotherboards.size();
                Log.v("LOG_motherboard",n_motherboard+"");
                allRams = db.getAllRams("SELECT  * FROM ram");
                n_ram = allRams.size();
                Log.v("LOG_ram",n_ram+"");
            }
            if (trigger[1]==0){
                allProcessors = db.getAllProcessors("SELECT  * FROM processor where '"+state.getMotherboard().getSocket()+"' like '%'||socket||'%'");
                n_processor = allProcessors.size();
                Log.v("LOG_processor",n_processor+"");
                allRams = db.getAllRams("SELECT  * FROM ram where ram_type like '"+state.getMotherboard().getRamType()+"'");
                n_ram = allRams.size();
                Log.v("LOG_ram",n_ram+"");
            }
            if (trigger[2]==0){
                allMotherboards = db.getAllMotherboards("SELECT  * FROM motherboard where ram_type like '"+state.getRam().getRamType()+"'");
                n_motherboard = allMotherboards.size();
                Log.v("LOG_motherboard",n_motherboard+"");
                allProcessors = db.getAllProcessors("SELECT  * FROM processor where socket in (SELECT socket FROM motherboard where ram_type like '"+state.getRam().getRamType()+"')");
                n_processor = allProcessors.size();
                Log.v("LOG_processor",n_processor+"");
            }
        } else if(trigger[0]+trigger[1]+trigger[2]==1){
            if (trigger[0]==1){
                allProcessors = db.getAllProcessors("SELECT  * FROM processor where '"+state.getMotherboard().getSocket()+"' like '%'||socket||'%'");
                n_processor = allProcessors.size();
                Log.v("LOG_processor",n_processor+"");
            }
            if (trigger[1]==1){
                allMotherboards = db.getAllMotherboards("SELECT  * FROM motherboard where ram_type like '"+state.getRam().getRamType()+"' and socket like '%"+state.getProcessor().getSocket()+"%'");
                n_motherboard = allMotherboards.size();
                Log.v("LOG_motherboard",n_motherboard+"");
            }
            if (trigger[2]==1){
                allRams = db.getAllRams("SELECT  * FROM ram where ram_type like '"+state.getMotherboard().getRamType()+"'");
                n_ram = allRams.size();
                Log.v("LOG_ram",n_ram+"");
            }
        } else {
            //SKIP
        }

        if(trigger[3]==1){
//            allGpus = db.getAllGpus("SELECT  * FROM gpu");
            allGpus = db.getAllGpus("SELECT  * FROM gpu where (price between "+ (weight[3]*budget/100/2) +" and "+ (weight[3]*budget/100/2*3) +")");
            n_gpu = allGpus.size();
            Log.v("LOG_gpu",n_gpu+"");
        }
        if(trigger[4]==1){
//            allPowers = db.getAllPowers("SELECT  * FROM power");
            allPowers = db.getAllPowers("SELECT  * FROM power where (price between "+ (weight[4]*budget/100/2) +" and "+ (weight[4]*budget/100/2*3) +")");
            n_power = allPowers.size();
            Log.v("LOG_power",n_power+"");
        }
        if(trigger[5]==1){
//            allStorages = db.getAllStorages("SELECT  * FROM storage");
            allStorages = db.getAllStorages("SELECT  * FROM storage where (price between "+ (weight[5]*budget/100/2) +" and "+ (weight[5]*budget/100/2*3) +")");
            n_storage = allStorages.size();
            Log.v("LOG_storage",n_storage+"");
        }
        if(trigger[6]==1){
//            allMonitors = db.getAllMonitors("SELECT  * FROM monitor");
            allMonitors = db.getAllMonitors("SELECT  * FROM monitor where (price between "+ (weight[6]*budget/100/2) +" and "+ (weight[6]*budget/100/2*3) +")");
            n_monitor = allMonitors.size();
            Log.v("LOG_monitor",n_monitor+"");
        }
        if(trigger[7]==1){
//            allCasings = db.getAllCasings("SELECT  * FROM casing");
            allCasings = db.getAllCasings("SELECT  * FROM casing where (price between "+ (weight[7]*budget/100/2) +" and "+ (weight[7]*budget/100/2*3) +")");
            n_casing = allCasings.size();
            Log.v("LOG_casing",n_casing+"");
        }
        if(trigger[8]==1){
//            allMouses = db.getAllMouses("SELECT  * FROM mouse");
            allMouses = db.getAllMouses("SELECT  * FROM mouse where (price between "+ (weight[8]*budget/100/2) +" and "+ (weight[8]*budget/100/2*3) +")");
            n_mouse = allMouses.size();
            Log.v("LOG_mouse",n_mouse+"");
        }
        if(trigger[9]==1){
//            allKeyboards = db.getAllKeyboards("SELECT  * FROM keyboard");
            allKeyboards = db.getAllKeyboards("SELECT  * FROM keyboard where (price between "+ (weight[9]*budget/100/2) +" and "+ (weight[9]*budget/100/2*3) +")");
            n_keyboard = allKeyboards.size();
            Log.v("LOG_keyboard",n_keyboard+"");
        }

//        allPowers = db.getAllPowers("SELECT  * FROM power");
//        n_power = allPowers.size();
//        Log.v("LOG_power",n_power+"");

        //Log.v("LOG_trigger", Arrays.toString(trigger));

        if(n_processor == 0){
            trigger[0] = 0;
        }
        if(n_motherboard == 0){
            trigger[1] = 0;
        }
        if(n_ram == 0){
            trigger[2] = 0;
        }
        if(n_gpu == 0){
            trigger[3] = 0;
        }
        if(n_power == 0){
            trigger[4] = 0;
        }
        if(n_storage == 0){
            trigger[5] = 0;
        }
        if(n_monitor == 0){
            trigger[6] = 0;
        }
        if(n_casing == 0){
            trigger[7] = 0;
        }
        if(n_mouse == 0){
            trigger[8] = 0;
        }
        if(n_keyboard == 0){
            trigger[9] = 0;
        }

        //Log.v("LOG_trigger", Arrays.toString(trigger));

        //Log.v("LOG_findRec_param ",type+"/"+n+"/"+t0+"/"+tn);
//        for(String part : state.printState())
//            Log.v("LOG_findRec_state ", part);
//        for(int part : trigger)
//            Log.v("LOG_findRec_trigger ", part+"");

//        Log.v("LOG_array ", allProcessors.get(0).getCode()+"");
//        Log.v("LOG_array ", allProcessors.get(5).getCode()+"");
//        Log.v("LOG_array ", allProcessors.get(10).getCode()+"");
//        Log.v("LOG_array ", allProcessors.get(20).getCode()+"");
//        Log.v("LOG_array ", allProcessors.get(30).getCode()+"");
//
//        Log.v("LOG_array ", allMotherboards.get(0).getCode()+"");
//        Log.v("LOG_array ", allMotherboards.get(5).getCode()+"");
//        Log.v("LOG_array ", allMotherboards.get(10).getCode()+"");
//        Log.v("LOG_array ", allMotherboards.get(20).getCode()+"");
//        Log.v("LOG_array ", allMotherboards.get(30).getCode()+"");
//
//        Log.v("LOG_array ", allRams.get(0).getCode()+"");
//        Log.v("LOG_array ", allRams.get(5).getCode()+"");
//        Log.v("LOG_array ", allRams.get(10).getCode()+"");
//        Log.v("LOG_array ", allRams.get(20).getCode()+"");
//        Log.v("LOG_array ", allRams.get(30).getCode()+"");
        //Log.v("LOG_findRec_arrays ",allProcessors.size()+"/"+allMotherboards.size()+"/"+allRams.size()+"/"+allGpus.size()+"/"+allPowers.size()+"/"+allStorages.size()+"/"+allMonitors.size()+"/"+allCasings.size()+"/"+allMouses.size()+"/"+allKeyboards.size());
        int totalTrigger = trigger[0]+trigger[1]+trigger[2]+trigger[3]+trigger[4]+trigger[5]+trigger[6]+trigger[7]+trigger[8]+trigger[9];

        if(totalTrigger>0) {
            do {
                //Log.v("trace_infloop", "1st do");
                do {
                    //Log.v("trace_infloop", "2nd do");
                    do {
                        //Log.v("trace_infloop", "3rd do");
                        a = ra.nextInt(9);
                        b = rb.nextInt(9);
                    } while (a == b);
                    if (a < b) {
                        p1 = a;
                        p2 = b;
                    } else {
                        p1 = b;
                        p2 = a;
                    }
                    //Log.v("trace_infloop", "p1/p2=" + p1 + "/" + p2);

                    for (int i = p1; i <= p2; i++) {
                        //Log.v("trace_infloop", "for");
                        switch (i) {
                            case 0:
                                if (trigger[0] == 1) {
                                    //Log.v("trace_infloop", "case 0/" + i);
                                    findProcessor();
                                } else {
                                    //Log.v("LOG_findProcessor", "SKIP");
                                }
                                break;
                            case 1:
                                if (trigger[1] == 1) {
                                    //Log.v("trace_infloop", "case 1/" + i);
                                    findMotherboard();
                                } else {
                                    //Log.v("LOG_findMotherboard", "SKIP");
                                }
                                break;
                            case 2:
                                if (trigger[2] == 1) {
                                    //Log.v("trace_infloop", "case 2/" + i);
                                    findRam();
                                } else {
                                    //Log.v("LOG_findRam", "SKIP");
                                }
                                break;
                            case 3:
                                if (trigger[3] == 1) {
                                    //Log.v("trace_infloop", "case 3/" + i);
                                    findGpu();
                                } else {
                                    //Log.v("LOG_findGpu", "SKIP");
                                }
                                break;
                            case 4:
                                if (trigger[5] == 1) {
                                    //Log.v("trace_infloop", "case 5/" + i);
                                    findStorage();
                                } else {
                                    //Log.v("LOG_findStorage", "SKIP");
                                }
                                break;
                            case 5:
                                if (trigger[6] == 1) {
                                    //Log.v("trace_infloop", "case 6/" + i);
                                    findMonitor();
                                } else {
                                    //Log.v("LOG_findMonitor", "SKIP");
                                }
                                break;
                            case 6:
                                if (trigger[7] == 1) {
                                    //Log.v("trace_infloop", "case 7/" + i);
                                    findCasing();
                                } else {
                                    //Log.v("LOG_findCasing", "SKIP");
                                }
                                break;
                            case 7:
                                if (trigger[8] == 1) {
                                    //Log.v("trace_infloop", "case 8/" + i);
                                    findMouse();
                                } else {
                                    //Log.v("LOG_findMouse", "SKIP");
                                }
                                break;
                            case 8:
                                if (trigger[9] == 1) {
                                    //Log.v("trace_infloop", "case 9/" + i);
                                    findKeyboard();
                                } else {
                                    //Log.v("LOG_findKeyboard", "SKIP");
                                }
                                break;
                        }
                        if(trigger[4]==1){
                            int minTdp = (int) ((Integer.parseInt(tempState.getProcessor().getTdp()) + Integer.parseInt(tempState.getGpu().getTdp()) + 100) * 1.25);
                            //Log.v("trace_infloop", "power/" + minTdp + "/" + i);
                            findPower(minTdp);
                        }
                        else {
                            //Log.v("LOG_findPower", "SKIP");
                        }
                    }
                    inCount++;
                    //Log.v("trace_infloop", "inCount/" + inCount + "_loop/"+loop+"_money/" + tempState.getTotalPrice() + "/" + state.getTotalBudget());
                    //Log.v("trace_infloop", "money/" + tempState.getTotalPrice() + "/" + state.getTotalBudget());

                    if(inCount>=n){
                        //fuckin bugs
                        Log.v("trace_infloop", "inCountMax/" + inCount);
                        flag = 1;
                        break;
                    }

                } while (tempState.getTotalPrice() > state.getTotalBudget());

                inCount = 0;

                if (tempState.getTotalPerformance() > finalState.getTotalPerformance()) {
                    //Log.v("trace_infloop", "compare temp/final");
                    copyState(tempState, finalState);
                } else {
                    //Log.v("trace_infloop", "else compare temp/final");
                    p = Math.exp((tempState.getTotalPerformance() - finalState.getTotalPerformance()) / t);
                    if (p > Math.random()) {
                        copyState(tempState, finalState);
                    }
                }

                if (finalState.getTotalPerformance() > bestState.getTotalPerformance()) {
                    //Log.v("trace_infloop", "compare final/best");
                    copyState(finalState, bestState);
                    C_State dummy = (C_State) bestState.clone();
                    state_array.add(dummy);
                }

                if (tempState.getTotalPrice() < finalState.getTotalPrice()) {
                    //Log.v("trace_infloop", "compare min temp/final");
                    copyState(tempState, minState);
                }

                price_array.add((double) finalState.getTotalPrice());
                performance_array.add(finalState.getTotalPerformance());
//                C_State dummy = (C_State) finalState.clone();
//                state_array.add(dummy);

                loop++;
                //Log.v("trace_infloop", "loop/" + loop);

                t = coolingSchedules(type, t0, tn, loop, n);
                temp_array.add(t);
                if (t <= tn || loop == (int) n) {
                    flag = 1;
                    Log.v("LOG_loop", "" + loop);
                }

            } while (flag != 1);
        }
        //Log.v("LOG_best",bestState.getTotalPerformance()+"");
        //Log.v("LOG_final",finalState.getTotalPerformance()+"");

        Log.v("LOG_statearray",state_array.size()+"");
        Log.v("LOG_statearray",performance_array.size()+"");
        Log.v("LOG_statearray",price_array.size()+"");

        if(state_array.size()<3){
            do{
                C_State dummy = (C_State) bestState.clone();
                state_array.add(dummy);
            }while(state_array.size()<3);
        }

//        Collections.sort(state_array, new Comparator<C_State>(){
//            public int compare(C_State s1, C_State s2) {
//                return Double.compare(s1.getTotalPerformance(),s2.getTotalPerformance());
//            }
//        });

        Log.v("LOG_statearray",state_array.get(0).getTotalPerformance()+"");
        Log.v("LOG_statearray",state_array.get(state_array.size()-1).getTotalPerformance()+"");

        Gson gs = new Gson();
        String final_state_json = gs.toJson(finalState);
        String best_state_json = gs.toJson(bestState);
        String state1_json = gs.toJson(state_array.get(state_array.size()-1));
        String state2_json = gs.toJson(state_array.get(state_array.size()-2));
        String state3_json = gs.toJson(state_array.get(state_array.size()-3));
        dataHelper.writeFile(context,"finalPerformance_json", performance_array);
        dataHelper.writeFile(context,"price_json", price_array);
        dataHelper.writeFile(context,"temp_json", temp_array);
        intent.putExtra("Final_State", final_state_json);
        intent.putExtra("Best_State", best_state_json);
        intent.putExtra("State1", state1_json);
        intent.putExtra("State2", state2_json);
        intent.putExtra("State3", state3_json);
        context.startActivity(intent);

    }

    public void findProcessor(){
        //Log.v("trace_infloop_f0","in");
        Random rx = new Random();
        int index = rx.nextInt(n_processor);
        tempState.setProcessor(allProcessors.get(index));
        if(trigger[1]==1){
            if(tempState.getMotherboard().getSocket().equalsIgnoreCase(tempState.getProcessor().getSocket())||tempState.getMotherboard().getSocket().equalsIgnoreCase(tempState.getProcessor().getSocket()+"+")){
                //Log.v("trace_infloop_f0","compatible");
//                findMotherboard();
            }
            else {
                findMotherboard();
            }
        }

        //return processor;
    }

    public void findMotherboard(){
        //Log.v("trace_infloop_f1","in");
        Random rx = new Random();
        int index,i=0;
        flag_mbo = 0;
        do {
            i++;
            //Log.v("trace_infloop_f1", "in/" + i);
            //Log.v("trace_infloop_f1", tempState.getProcessor().getSocket());
            index = rx.nextInt(n_motherboard);
            if(allMotherboards.get(index).getSocket().equalsIgnoreCase(tempState.getProcessor().getSocket())||allMotherboards.get(index).getSocket().equalsIgnoreCase(tempState.getProcessor().getSocket()+"+")){
                flag_mbo=1;
            }
            else if(i==1000){
                findProcessor();
            }
            else {
                flag_mbo=0;
            }
        //}while(!allMotherboards.get(index).getSocket().equalsIgnoreCase(tempState.getProcessor().getSocket())&&!allMotherboards.get(index).getSocket().equalsIgnoreCase(tempState.getProcessor().getSocket()+"+"));
        }while (flag_mbo==0);
        tempState.setMotherboard(allMotherboards.get(index));
        if(trigger[2]==1){
            if(!tempState.getRam().getRamType().equalsIgnoreCase(tempState.getMotherboard().getRamType())){
                findRam();
            }
        }
        //return motherboard;
    }

    public void findRam(){
        //Log.v("trace_infloop_f2","in");
        Random rx = new Random();
        int index,i=0;
        do{
            i++;
            //Log.v("trace_infloop_f2","in/"+i);
            index = rx.nextInt(n_ram);
        }while(!allRams.get(index).getRamType().equalsIgnoreCase(tempState.getMotherboard().getRamType()));
        tempState.setRam(allRams.get(index));
        //return ram;
    }

    public void findGpu(){
        //Log.v("trace_infloop_f3","in");
        Random rx = new Random();
        int index = rx.nextInt(n_gpu);
        tempState.setGpu(allGpus.get(index));
        //return gpu;
    }

    public void findPower(int minTdp){
        //Log.v("trace_infloop_f4","in/"+minTdp);
        Random rx = new Random();
        int index,i=0;
        do{
            i++;
            //Log.v("trace_infloop_f4","in/"+i);
            index = rx.nextInt(n_power);
        }while (Integer.parseInt(allPowers.get(index).getPower()) < minTdp);
        tempState.setPower(allPowers.get(index));
        //return power;
    }

    public void findStorage(){
        //Log.v("trace_infloop_f5","in");
        Random rx = new Random();
        int index = rx.nextInt(n_storage);
        tempState.setStorage(allStorages.get(index));
        //return storage;
    }

    public void findMonitor(){
        //Log.v("trace_infloop_f6","in");
        Random rx = new Random();
        int index = rx.nextInt(n_monitor);
        tempState.setMonitor(allMonitors.get(index));
        //return monitor;
    }

    public void findCasing(){
        //Log.v("trace_infloop_f7","in");
        Random rx = new Random();
        int index = rx.nextInt(n_casing);
        tempState.setCasing(allCasings.get(index));
        //return casing;
    }

    public void findMouse(){
        //Log.v("trace_infloop_f8","in");
        Random rx = new Random();
        int index = rx.nextInt(n_mouse);
        tempState.setMouse(allMouses.get(index));
        //return mouse;
    }

    public void findKeyboard(){
        //Log.v("trace_infloop_f9","in");
        Random rx = new Random();
        int index = rx.nextInt(n_keyboard);
        tempState.setKeyboard(allKeyboards.get(index));
        //return keyboard;
    }

    public double coolingSchedules(int type, double T0, double TN, int loop, double N) {
        double temp = 0.0d, A = 0.0d, B = 0.0d;
        switch (type) {
            case 0:
                temp = T0 - (double) loop * (T0 - TN) / N;
                break;
            case 1:
                temp = T0 * Math.pow((TN / T0), ((double) loop / N));
                break;
            case 2:
                A = (T0 - TN) * (N + (double) 1) / N;
                B = T0 - A;
                temp = A / ((double) loop + 1) + B;
                break;
            case 3:
                A = Math.log(T0 - TN) / Math.log(N);
                temp = T0 - Math.pow((double) loop, A);
                break;
            case 4:
                temp = (T0 - TN) / (1 + Math.exp((double) 3 * ((double) loop - (N / (double) 2)))) + TN;
                break;
            case 5:
                temp = 0.5 * (T0 - TN) * (1 + Math.cos((double) loop * Math.PI / N)) + TN;
                break;
            case 6:
                temp = 0.5 * (T0 - TN) * (1 - Math.tanh((double) 10 * (double) loop / N - (double) 5)) + TN;
                break;
            case 7:
                temp = (T0 - TN) / (Math.cosh((double) 10 * (double) loop / N)) + TN;
                break;
            case 8:
                A = ((double) 1 / N) * Math.log(T0 / TN);
                temp = T0 * Math.exp(-1 * A * (double) loop);
                break;
        }
        return temp;
    }

    public void copyState(C_State a, C_State b){
        b.setProcessor(a.getProcessor());
        b.setMotherboard(a.getMotherboard());
        b.setRam(a.getRam());
        b.setGpu(a.getGpu());
        b.setPower(a.getPower());
        b.setStorage(a.getStorage());
        b.setMonitor(a.getMonitor());
        b.setCasing(a.getCasing());
        b.setMouse(a.getMouse());
        b.setKeyboard(a.getKeyboard());

    }
}
