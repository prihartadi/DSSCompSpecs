package com.example.prihartadi.dsscompspecs;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Arrays;

public class A_Custom extends AppCompatActivity {

    ListView customListParts;
    Spinner customCategories;
    EditText customBudget;
    ArrayAdapter<String> adapter;
    Button customStart;
    CheckBox checkUpdate;

    H_Database databaseHelper;
    H_Data dataHelper;
    C_SimulatedAnnealing sa;

    private int act_search_req;
    private static final int act_search_res = 1;
    String category1;
    int selection1;
    double[] weight;
    long budget;

    int[] trigger = {1,1,1,1,1,1,1,1,1,1};
    long[] partId = {0,0,0,0,0,0,0,0,0,0};
    long[] partPrice = {0,0,0,0,0,0,0,0,0,0};
    int flag_update = 0;

    final String[] categories1 = {"Umum", "Gaming", "Multimedia", "Professional"};
    String[] listParts = {"Pilih Processor","Pilih Motherboard","Pilih Ram","Pilih Gpu","Pilih Power","Pilih Storage","Pilih Monitor","Pilih Casing","Pilih Mouse","Pilih Keyboard"};
    ArrayAdapter<String> customAdapterOptions;

    H_BackgroundTask task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a__custom);

        databaseHelper = new H_Database(this);
        dataHelper = new H_Data();
        sa = new C_SimulatedAnnealing();

        customCategories = (Spinner) findViewById(R.id.spinCategory1);
        customAdapterOptions = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories1);
        customAdapterOptions.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        customCategories.setAdapter(customAdapterOptions);
        customCategories.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapter, View v,
                                       int position, long id) {
                category1 = adapter.getItemAtPosition(position).toString();
                for (int i = 0; i < categories1.length; i++) {
                    if (category1.equalsIgnoreCase(categories1[i])) {
                        selection1 = i;
                    }
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });

        customBudget = (EditText)findViewById(R.id.editButget1);
        checkUpdate = (CheckBox)findViewById(R.id.checkUpdate);

        customListParts = (ListView) findViewById(R.id.listParts2);
        adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_list_item_1, listParts);
        customListParts.setAdapter(adapter);
        customListParts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(), A_Search.class);
                act_search_req = i;
                intent.putExtra("Parts", i);
//                startActivity(intent);
                startActivityForResult(intent,act_search_req);
            }
        });

        customStart = (Button)findViewById(R.id.btnStart1);
        customStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String b = customBudget.getText().toString();
                if(b.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Budget Kosong", Toast.LENGTH_SHORT).show();

                }else {
//                    budget = Long.parseLong(customBudget.getText().toString());
                    budget = Long.parseLong(b);
                    if(!checkUpdate.isChecked()){
                        Log.v("LOG_update", "unchecked");
                        for(int i=0; i<trigger.length; i++){
                            budget = budget - partPrice[i];
                        }
                        flag_update = 2;
                    } else{
                        flag_update = 1;
                        Log.v("LOG_update", "checked");
                    }
                    if(budget>0){
                        Log.v("LOG_trigger", Arrays.toString(trigger));
                        Log.v("LOG_partId", Arrays.toString(partId));
                        C_State customState = setupCustomState();
                        for(String part : customState.printState())
                            Log.v("LOG_Custom_state ", part);
                        /////////
//                        try {
//                            //state,type,n,t0,tn
//                            sa.findRecomendation(getApplicationContext(),customState,2,100000,10000.0,0.01);
//                        } catch (CloneNotSupportedException e) {
//                            e.printStackTrace();
//                        }
                        task = new H_BackgroundTask(A_Custom.this,customState);
                        task.execute();
                    }else {
                        Toast.makeText(getApplicationContext(), "Over Budget", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == act_search_req) {
            if (resultCode == act_search_res) {
                if (data != null) {
                    long id = data.getExtras().getLong("id");
                    String name = data.getExtras().getString("name");
                    long price = data.getExtras().getLong("price");
//                    listParts[act_search_req] = id + " " + name + " " +price;
                    listParts[act_search_req] = name + " " +price;
                    //Toast.makeText(getApplicationContext(), "Result "+id + " " + name + " " +price, Toast.LENGTH_SHORT).show();
                    trigger[act_search_req] = 0;
                    partId[act_search_req] = id;
                    partPrice[act_search_req] = price;
                    adapter.notifyDataSetChanged();
                }
            }
        }
    }

    public C_State setupCustomState(){
        //make part's object
        C_Processor processor = new C_Processor();
        C_Motherboard motherboard = new C_Motherboard();
        C_Ram ram = new C_Ram();
        C_Gpu gpu = new C_Gpu();
        C_Power power = new C_Power();
        C_Storage storage = new C_Storage();
        C_Monitor monitor = new C_Monitor();
        C_Casing casing = new C_Casing();
        C_Mouse mouse = new C_Mouse();
        C_Keyboard keyboard = new C_Keyboard();
        C_State state = new C_State();

        //setting weight
        state.setWeight(selection1);
        //getting weight
        weight = state.getWeight();
        Log.v("LOG_beforeWeight", Arrays.toString(weight));

        //recalculate weight
        double sum = 0;
        for(int i=0; i<trigger.length; i++){
            sum = sum + ((double)trigger[i]*weight[i]);
        }
        double[] customWeight = {0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0};
        for(int i=0; i<trigger.length; i++){
            if(sum!=0){
                customWeight[i] = weight[i]/sum*100.0*trigger[i];
            }else {
                customWeight[i] = 0;
            }

        }
        //setting weight
        state.setWeight(customWeight);
        //getting weight
        weight = state.getWeight();
        Log.v("LOG_afterWeight", Arrays.toString(weight));

        if(trigger[0]==0){
            processor = databaseHelper.getProcessor("select * from processor where id = "+partId[0]);
        }else{
            processor = databaseHelper.getProcessor("select * from processor order by abs (price - " + weight[0]*budget/100 +") asc limit 1 ");
        }
        if(trigger[1]==0){
            motherboard = databaseHelper.getMotherboard("select * from motherboard where id = "+partId[1]);
        }else{
            motherboard = databaseHelper.getMotherboard("select * from motherboard where socket = '"+ processor.getSocket() +"' or socket = '"+ processor.getSocket() +"+' order by abs (price - " + weight[1]*budget/100 +") asc limit 1 ");
        }
        if(trigger[2]==0){
            ram = databaseHelper.getRam("select * from ram where id = "+partId[2]);
        }else{
            ram = databaseHelper.getRam("select * from ram where ram_type = '"+ motherboard.getRamType() +"' order by abs (price - " + weight[2]*budget/100 +") asc limit 1 ");
        }
        if(trigger[3]==0){
            gpu = databaseHelper.getGpu("select * from gpu where id = "+partId[3]);
        }else{
            gpu = databaseHelper.getGpu("select * from gpu order by abs (price - " + weight[3]*budget/100 +") asc limit 1 ");
        }
        if(trigger[5]==0){
            storage = databaseHelper.getStorage("select * from storage where id = "+partId[5]);
        }else{
            storage = databaseHelper.getStorage("select * from storage order by abs (price - " + weight[5]*budget/100 +") asc limit 1 ");
        }
        if(trigger[6]==0){
            monitor = databaseHelper.getMonitor("select * from monitor where id = "+partId[6]);
        }else{
            monitor = databaseHelper.getMonitor("select * from monitor order by abs (price - " + weight[6]*budget/100 +") asc limit 1 ");
        }
        if(trigger[7]==0){
            casing = databaseHelper.getCasing("select * from casing where id = "+partId[7]);
        }else{
            casing = databaseHelper.getCasing("select * from casing order by abs (price - " + weight[7]*budget/100 +") asc limit 1 ");
        }
        if(trigger[8]==0){
            mouse = databaseHelper.getMouse("select * from mouse where id = "+partId[8]);
        }else{
            mouse = databaseHelper.getMouse("select * from mouse order by abs (price - " + weight[8]*budget/100 +") asc limit 1 ");
        }
        if(trigger[9]==0){
            keyboard = databaseHelper.getKeyboard("select * from keyboard where id = "+partId[9]);
        }else{
            keyboard = databaseHelper.getKeyboard("select * from keyboard order by abs (price - " + weight[9]*budget/100 +") asc limit 1 ");
        }
        if(trigger[4]==0){
            power = databaseHelper.getPower("select * from power where id = "+partId[4]);
        }else{
            int minTdp = 0;
            minTdp = (int)((Integer.parseInt(processor.getTdp())+Integer.parseInt(gpu.getTdp())+100)*1.25);
            power = databaseHelper.getPower("select * from power where power+0 >= "+ minTdp +" order by abs (price - " + weight[4]*budget/100 +") asc limit 1 ");
        }

        state.setProcessor(processor);
        state.setMotherboard(motherboard);
        state.setRam(ram);
        state.setGpu(gpu);
        state.setPower(power);
        state.setStorage(storage);
        state.setMonitor(monitor);
        state.setCasing(casing);
        state.setMouse(mouse);
        state.setKeyboard(keyboard);
        state.setTotalBudget(budget);
        state.setCategory(selection1);
        state.setTrigger(trigger);
        state.setFlag_update(flag_update);

        return state;
    }

}
