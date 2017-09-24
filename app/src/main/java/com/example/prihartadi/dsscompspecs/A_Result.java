package com.example.prihartadi.dsscompspecs;

import android.content.ContextWrapper;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.io.File;
import java.io.FileOutputStream;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class A_Result extends AppCompatActivity {

    TextView best_performance, best_price, final_performance, final_price;
    TextView s1_performance, s1_price, s2_performance, s2_price, s3_performance, s3_price;
    Button rec1,rec2,rec3;
    String category;
    List<Double> performance_array1;
    List<Double> price_array1;
    List<Double> temp_array1;
    List<C_Item> list_final;
    List<C_Item> list_best, s1, s2, s3;
    C_State final_state1, best_state1, state1, state2, state3;
    H_Data dataHelper;
    ListView list1, list2, list3;
    C_Item processor1, processor2, motherboard1, motherboard2, ram1, ram2, gpu1, gpu2, power1, power2, storage1, storage2, monitor1, monitor2, casing1, casing2, mouse1, mouse2, keyboard1, keyboard2;
    H_CustomAdapter adapter1,adapter2,adapter3;
    int flag_save = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a__result);

        dataHelper = new H_Data();

        Gson gs = new Gson();
        Type type = new TypeToken<ArrayList<Double>>() {
        }.getType();
        performance_array1 = gs.fromJson(dataHelper.readFile(getApplicationContext(),"finalPerformance_json"), type);
        price_array1 = gs.fromJson(dataHelper.readFile(getApplicationContext(),"price_json"),type);
        temp_array1 = gs.fromJson(dataHelper.readFile(getApplicationContext(),"temp_json"), type);

        list1 = (ListView) findViewById(R.id.listParts);
//        list2 = (ListView) findViewById(R.id.listPartsx1);
//        list3 = (ListView) findViewById(R.id.listPartsx2);
        rec1 = (Button) findViewById(R.id.rec1);
        rec2 = (Button) findViewById(R.id.rec2);
        rec3 = (Button) findViewById(R.id.rec3);
        //list2 = (ListView) findViewById(R.id.listParts1);

        //list_final = new ArrayList<C_Item>();
        //list_best = new ArrayList<C_Item>();

//        final TabHost host = (TabHost) findViewById(R.id.tabtab);
//        host.setup();
//        TabHost.TabSpec spec;
//        spec = host.newTabSpec("Tab One");
//        spec.setContent(R.id.tab1);
//        spec.setIndicator("1");
//        host.addTab(spec);
//
//        spec = host.newTabSpec("Tab Two");
//        spec.setContent(R.id.tab2);
//        spec.setIndicator("2");
//        host.addTab(spec);
//
//        spec = host.newTabSpec("Tab Three");
//        spec.setContent(R.id.tab3);
//        spec.setIndicator("3");
//        host.addTab(spec);

        String final_state_json = getIntent().getStringExtra("Final_State");
        String best_state_json = getIntent().getStringExtra("Best_State");

        String state1_json = getIntent().getStringExtra("State1");
        String state2_json = getIntent().getStringExtra("State2");
        String state3_json = getIntent().getStringExtra("State3");

        final_state1 = gs.fromJson(final_state_json, C_State.class);
        best_state1 = gs.fromJson(best_state_json, C_State.class);

        state1 = gs.fromJson(state1_json, C_State.class);
        state2 = gs.fromJson(state2_json, C_State.class);
        state3 = gs.fromJson(state3_json, C_State.class);

        for(String part : best_state1.printState())
            Log.v("LOG_bestState ", part);
        for(String part : final_state1.printState())
            Log.v("LOG_finalState ", part);

        for(String part : state1.printState())
            Log.v("LOG_State1 ", part);
        for(String part : state2.printState())
            Log.v("LOG_State2 ", part);
        for(String part : state3.printState())
            Log.v("LOG_State3 ", part);

        //Bundle extras = getIntent().getExtras();

        int idCategory = best_state1.getCategory();

        switch (idCategory){
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

        /*
        best_performance = (TextView) findViewById(R.id.textCategory);
        best_price = (TextView) findViewById(R.id.textPrice);
        best_performance.setText(category+" ("+best_state1.getTotalPerformance()+")");
        if(best_state1.getFlag_update()==2){
            long price = best_state1.getProcessor().getPrice()+
                    best_state1.getMotherboard().getPrice()+
                    best_state1.getRam().getPrice()+
                    best_state1.getGpu().getPrice()+
                    best_state1.getPower().getPrice()+
                    best_state1.getStorage().getPrice()+
                    best_state1.getMonitor().getPrice()+
                    best_state1.getCasing().getPrice()+
                    best_state1.getMouse().getPrice()+
                    best_state1.getKeyboard().getPrice();
//            best_price.setText("Rp. "+price+"/"+best_state1.getTotalBudget());
            best_price.setText("Rp. "+price);
        }
        else {
//            best_price.setText("Rp. "+best_state1.getTotalPrice()+"/"+best_state1.getTotalBudget());
            best_price.setText("Rp. "+best_state1.getTotalPrice());
        }
        */

        s1_performance = (TextView) findViewById(R.id.textCategory);
        s1_price = (TextView) findViewById(R.id.textPrice);
//        s2_performance = (TextView) findViewById(R.id.textCategoryx1);
//        s2_price = (TextView) findViewById(R.id.textPricex1);
//        s3_performance = (TextView) findViewById(R.id.textCategoryx2);
//        s3_price = (TextView) findViewById(R.id.textPricex2);

        s1_performance.setText(category+" ("+state1.getTotalPerformance()+")");
//        s2_performance.setText(category+" ("+state2.getTotalPerformance()+")");
//        s3_performance.setText(category+" ("+state3.getTotalPerformance()+")");

        s1_price.setText("Rp. "+state1.getTotalPrice());
//        s2_price.setText("Rp. "+state2.getTotalPrice());
//        s3_price.setText("Rp. "+state3.getTotalPrice());

        //list_best = getList(best_state1);
        //list_final = getList(final_state1);

        s1 = getList(state1);
        s2 = getList(state2);
        s3 = getList(state3);

        Log.v("LOG_s3",s3.get(0).getName());
        Log.v("LOG_s3",s3.get(1).getName());
        Log.v("LOG_s3",s3.get(2).getName());
        Log.v("LOG_s3",s3.get(3).getName());
        Log.v("LOG_s3",s3.get(4).getName());
        Log.v("LOG_s3",s3.get(5).getName());
        Log.v("LOG_s3",s3.get(6).getName());
        Log.v("LOG_s3",s3.get(7).getName());
        Log.v("LOG_s3",s3.get(8).getName());
        Log.v("LOG_s3",s3.get(9).getName());

        //list1.setAdapter(new H_CustomAdapter(this,list_best));
        //list2.setAdapter(new H_CustomAdapter(this,list_final));

        adapter1 = new H_CustomAdapter(this,s1);
        adapter2 = new H_CustomAdapter(this,s2);
        adapter3 = new H_CustomAdapter(this,s3);


//        list1.setAdapter(new H_CustomAdapter(this,s1));
//        list2.setAdapter(new H_CustomAdapter(this,s2));
//        list3.setAdapter(new H_CustomAdapter(this,s3));
        list1.setAdapter(adapter1);
//        list2.setAdapter(adapter2);
//        list3.setAdapter(adapter3);

//        host.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
//            @Override
//            public void onTabChanged(String s) {
//                Toast.makeText(getApplication(), host.getCurrentTab()+"", Toast.LENGTH_SHORT).show();
//                if(host.getCurrentTab()==0){
//                    adapter1.updateListCustomAdapter(s1);
//                    list1.setAdapter(adapter1);
//                }else if(host.getCurrentTab()==1){
//                    adapter2.updateListCustomAdapter(s2);
//                    list2.setAdapter(adapter2);
//                }else if(host.getCurrentTab()==2){
//                    adapter3.updateListCustomAdapter(s3);
//                    list3.setAdapter(adapter3);
//                }
//            }
//        });

        rec1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                s1_performance.setText(category+" ("+state1.getTotalPerformance()+")");
                s1_price.setText("Rp. "+state1.getTotalPrice());
//                list1.setAdapter(adapter1);
                adapter1.updateListCustomAdapter(s1);
                flag_save = 1;
            }
        });

        rec2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                s1_performance.setText(category+" ("+state2.getTotalPerformance()+")");
                s1_price.setText("Rp. "+state2.getTotalPrice());
//                list1.setAdapter(adapter1);
                adapter1.updateListCustomAdapter(s2);
//                list1.setAdapter(adapter2);
                flag_save = 2;
            }
        });
        rec3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                s1_performance.setText(category+" ("+state3.getTotalPerformance()+")");
                s1_price.setText("Rp. "+state3.getTotalPrice());
//                list1.setAdapter(adapter1);
                adapter1.updateListCustomAdapter(s3);
//                list1.setAdapter(adapter3);
                flag_save = 3;
            }
        });


    }

    public List<C_Item> getList(C_State state){

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

    @Override
    public void onBackPressed(){
        Intent intent = new Intent(getApplicationContext(), A_Main.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.save_menu, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.save) {
//            startActivity(new Intent(this, A_Update.class));

            C_State save = new C_State();
            if(flag_save==1){
                try {
                    save = (C_State) state1.clone();
                } catch (CloneNotSupportedException e) {
                    e.printStackTrace();
                }
            }else if(flag_save==2){
                try {
                    save = (C_State) state2.clone();
                } catch (CloneNotSupportedException e) {
                    e.printStackTrace();
                }
            }else if(flag_save==3){
                try {
                    save = (C_State) state3.clone();
                } catch (CloneNotSupportedException e) {
                    e.printStackTrace();
                }
            }

            Toast.makeText(this, "Save", Toast.LENGTH_LONG).show();
            Log.v("LOG_save","save");

            String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());

            ContextWrapper cw = new ContextWrapper(getApplicationContext());
            File directory = cw.getDir("saved", getApplicationContext().MODE_PRIVATE);
            if (!directory.exists()) {
                directory.mkdir();
            }
            String cat="";
            switch (best_state1.getCategory()){
                case 0:
                    cat = "Umum";
                    break;
                case 1:
                    cat = "Gaming";
                    break;
                case 2:
                    cat = "Multimedia";
                    break;
                case 3:
                    cat = "Profesional";
                    break;

            }
            File mypath = new File(directory,cat+"_"+save.getTotalPrice()+"_"+currentDateTimeString);

            Gson gs = new Gson();

            Log.v("LOG_directory",directory.toString());
            Log.v("LOG_mypath",mypath.toString());

            FileOutputStream outputStream = null;
            String savedFile = gs.toJson(save);
            Log.v("LOG_savedFile",savedFile);
            try {
                outputStream = new FileOutputStream(mypath);
                outputStream.write(savedFile.getBytes());
                outputStream.close();
                Log.v("LOG_writing","works");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return true;
    }
}
