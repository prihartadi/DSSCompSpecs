package com.example.prihartadi.dsscompspecs;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class A_CleanResult extends AppCompatActivity {

    TextView price, performance;
    C_State savedState;
    ListView specification;
    String savedFile;
    String category;
    List<C_Item> list_saved;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a__clean_result);
        Gson gs = new Gson();
        performance = (TextView)findViewById(R.id.textCategory2);
        price = (TextView)findViewById(R.id.textPrice2);
        specification = (ListView)findViewById(R.id.listClean);
        savedFile = getIntent().getStringExtra("Saved");
        savedState = gs.fromJson(savedFile,C_State.class);
        for(String part : savedState.printState())
            Log.v("LOG_oS_state ", part);
        switch (savedState.getCategory()){
            case 0:
                category = "Umum";
                break;
            case 1:
                category = "Gaming";
                break;
            case 2:
                category = "Multimedia";
                break;
            case 3:
                category = "Profesional";
                break;
        }
        performance.setText(category+" ("+savedState.getTotalPerformance()+")");
//        price.setText("Rp. "+savedState.getTotalPrice()+"/"+savedState.getTotalBudget());

        if(savedState.getFlag_update()==2){
            long prices = savedState.getProcessor().getPrice()+
                    savedState.getMotherboard().getPrice()+
                    savedState.getRam().getPrice()+
                    savedState.getGpu().getPrice()+
                    savedState.getPower().getPrice()+
                    savedState.getStorage().getPrice()+
                    savedState.getMonitor().getPrice()+
                    savedState.getCasing().getPrice()+
                    savedState.getMouse().getPrice()+
                    savedState.getKeyboard().getPrice();
//            best_price.setText("Rp. "+price+"/"+best_state1.getTotalBudget());
            price.setText("Rp. "+prices);
        }
        else {
//            best_price.setText("Rp. "+best_state1.getTotalPrice()+"/"+best_state1.getTotalBudget());
            price.setText("Rp. "+savedState.getTotalPrice());
        }

        list_saved = new ArrayList<C_Item>();
        list_saved = getList(savedState);
        Log.v("LOG_listSaved", ""+list_saved.size());
        specification.setAdapter(new H_CustomAdapter(this,list_saved));
    }

    public List<C_Item> getList(C_State state){

        C_Item processor1, motherboard1, ram1, gpu1, power1, storage1, monitor1, casing1, mouse1, keyboard1;

        List<C_Item> listItems = new ArrayList<C_Item>();

        processor1 = new C_Item();
        processor1.setPart("Processor");
        processor1.setName(state.getProcessor().getBrand()+" "+state.getProcessor().getCode());
        processor1.setDetail(state.getProcessor().getCore()+" Cores "+state.getProcessor().getSpeed()+"GHz "+state.getProcessor().getCache()+"Mb Cache "+state.getProcessor().getSocket()+" "+state.getProcessor().getTdp()+"Watt");
        processor1.setPrice("Rp. "+state.getProcessor().getPrice());
        listItems.add(processor1);

        motherboard1 = new C_Item();
        motherboard1.setPart("Motherboard");
        motherboard1.setName(state.getMotherboard().getBrand()+" "+state.getMotherboard().getCode());
        motherboard1.setDetail(state.getMotherboard().getChipset()+" "+state.getMotherboard().getSocket()+" "+state.getMotherboard().getRamType()+" "+state.getMotherboard().getMemConn()+" "+state.getMotherboard().getPcie());
        motherboard1.setPrice("Rp. "+state.getMotherboard().getPrice());
        listItems.add(motherboard1);

        ram1 = new C_Item();
        ram1.setPart("RAM");
        ram1.setName(state.getRam().getBrand()+" "+state.getRam().getCode());
        ram1.setDetail(state.getRam().getTotalSize()+"Gb "+state.getRam().getRamType()+" "+state.getRam().getSpeed()+"MHz");
        ram1.setPrice("Rp. "+state.getRam().getPrice());
        listItems.add(ram1);

        gpu1 = new C_Item();
        gpu1.setPart("Graphic Card");
        gpu1.setName(state.getGpu().getBrand()+" "+state.getGpu().getChipset()+" "+state.getGpu().getCode());
        gpu1.setDetail(state.getGpu().getVramSize()+"Gb "+state.getGpu().getVramType()+" "+state.getGpu().getTdp()+"Watt");
        gpu1.setPrice("Rp. "+state.getGpu().getPrice());
        listItems.add(gpu1);

        power1 = new C_Item();
        power1.setPart("Power Supply");
        power1.setName(state.getPower().getBrand()+" "+state.getPower().getCode());
        power1.setDetail(state.getPower().getPower()+" Watt");
        power1.setPrice("Rp. "+state.getPower().getPrice());
        listItems.add(power1);

        storage1 = new C_Item();
        storage1.setPart("Storage Memory");
        storage1.setName(state.getStorage().getBrand()+" "+state.getStorage().getCode());
        storage1.setDetail(state.getStorage().getSize()+"Gb "+state.getStorage().getMemConn()+" "+state.getStorage().getType());
        storage1.setPrice("Rp. "+state.getStorage().getPrice());
        listItems.add(storage1);

        monitor1 = new C_Item();
        monitor1.setPart("Monitor");
        monitor1.setName(state.getMonitor().getBrand()+" "+state.getMonitor().getCode());
        monitor1.setDetail(state.getMonitor().getWide()+" Inch "+state.getMonitor().getResX()+"x"+state.getMonitor().getResY());
        monitor1.setPrice("Rp. "+state.getMonitor().getPrice());
        listItems.add(monitor1);

        casing1 = new C_Item();
        casing1.setPart("Casing");
        casing1.setName(state.getCasing().getBrand()+" "+state.getCasing().getCode());
        casing1.setDetail(state.getCasing().getForm());
        casing1.setPrice("Rp. "+state.getCasing().getPrice());
        listItems.add(casing1);

        mouse1 = new C_Item();
        mouse1.setPart("Mouse");
        mouse1.setName(state.getMouse().getBrand()+" "+state.getMouse().getCode());
        mouse1.setDetail("");
        mouse1.setPrice("Rp. "+state.getMouse().getPrice());
        listItems.add(mouse1);

        keyboard1 = new C_Item();
        keyboard1.setPart("Keyboard");
        keyboard1.setName(state.getKeyboard().getBrand()+" "+state.getKeyboard().getCode());
        keyboard1.setDetail("");
        keyboard1.setPrice("Rp. "+state.getKeyboard().getPrice());
        listItems.add(keyboard1);

        return listItems;
    }
}
