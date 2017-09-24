package com.example.prihartadi.dsscompspecs;

import android.Manifest;
import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v4.media.session.PlaybackStateCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import java.io.File;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.Currency;
import java.util.List;
import java.util.Locale;

public class A_Main extends AppCompatActivity {

    H_Permission permissionHelper;
    H_Database databaseHelper;
    H_Data dataHelper;
    C_SimulatedAnnealing sa;

    int selection, minTdp;
    Intent intent;
    final String[] categories = {"Umum", "Gaming", "Multimedia", "Professional"};
    String category;
    ArrayAdapter<String> adapterOptions;
    double[] weight;

    Spinner v_categories;
    EditText v_budget;
    Button v_start;
//    EditText v_t0,v_tn,v_n,v_type;

    long budget;
    int[] trigger = {1,1,1,1,1,1,1,1,1,1};

    int type;
    int n;
    //int count, flag;
    double t0;
    double tn;

    CarouselView carouselView;
    int[] pics = {R.drawable.pc0, R.drawable.pc1, R.drawable.pc2, R.drawable.pc3};

    H_BackgroundTask task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a__main);

        permissionHelper = new H_Permission();
        databaseHelper = new H_Database(this);
        dataHelper = new H_Data();
        sa = new C_SimulatedAnnealing();

        carouselView = (CarouselView) findViewById(R.id.carouselView);
        carouselView.setPageCount(pics.length);
        carouselView.setImageListener(imageListener);

        v_categories = (Spinner) findViewById(R.id.spinCategory);
        adapterOptions = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);
        adapterOptions.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        v_categories.setAdapter(adapterOptions);
        v_categories.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapter, View v,
                                       int position, long id) {
                category = adapter.getItemAtPosition(position).toString();
                for (int i = 0; i < categories.length; i++) {
                    if (category.equalsIgnoreCase(categories[i])) {
                        selection = i;
                    }
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });
        v_budget = (EditText)findViewById(R.id.editButget);
//        v_t0 = (EditText)findViewById(R.id.editT0);
//        v_tn = (EditText)findViewById(R.id.editTN);
//        v_n = (EditText)findViewById(R.id.editN);
//        v_type = (EditText)findViewById(R.id.editType);
        v_start = (Button)findViewById(R.id.btnStart);

//        task = new H_BackgroundTask(A_Main.this);

    }

    @Override
    public void onStart(){
            super.onStart();
        //Log.v("LOG_oS_main","This is onStart()");

        try {
            if(!checkDatabase()){
                Toast.makeText(this, "Preparing Database", Toast.LENGTH_SHORT).show();
                loadAllData();
            }
            else {
                Toast.makeText(this, "Database Ready", Toast.LENGTH_SHORT).show();
                Log.v("LOG_oS_main","Database Ready");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        v_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               //task.execute();

                String bb = v_budget.getText().toString();
                if(!bb.isEmpty()){
                    budget = Long.parseLong(v_budget.getText().toString());
                    if(budget>=4000000){
//                        NumberFormat format = NumberFormat.getCurrencyInstance(Locale.getDefault());
//                        format.setCurrency(Currency.getInstance("INA"));
//                        Log.v("LOG_currency",format.format(budget));
//                        t0 = Double.parseDouble(v_t0.getText().toString());
//                        tn = Double.parseDouble(v_tn.getText().toString());
//                        n = Integer.parseInt(v_n.getText().toString());
//                        type = Integer.parseInt(v_type.getText().toString());
//                        if(budget>=5000000){
                        t0 = 10000.0;
                        tn = 0.01;
                        n = 100000;
                        type = 2;



                        C_State initialState = setupState();
//                        try {
//                            sa.findRecomendation(getApplicationContext(),initialState,type,n,t0,tn);
//                        } catch (CloneNotSupportedException e) {
//                            e.printStackTrace();
//                        }
                        task = new H_BackgroundTask(A_Main.this,initialState);
                        task.execute();

                    }else {
                        Toast.makeText(getApplication(), "Untuk spesifikasi lengkap budget minimal Rp. 4.000.000,-", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(getApplication(), "Budget Kosong", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    ImageListener imageListener = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
            imageView.setImageResource(pics[position]);
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.main) {
            Toast.makeText(this, "Main", Toast.LENGTH_LONG).show();
        }
        if (item.getItemId() == R.id.custom) {
            startActivity(new Intent(this, A_Custom.class));
        }
        if (item.getItemId() == R.id.update) {
            startActivity(new Intent(this, A_Update.class));
        }
        if (item.getItemId() == R.id.saved) {
            startActivity(new Intent(this, A_Saved.class));
        }
        if (item.getItemId() == R.id.help) {
            startActivity(new Intent(this, A_Help.class));
        }
        if (item.getItemId() == R.id.about) {
            startActivity(new Intent(this, A_About.class));
        }

        return true;
    }

    public C_State setupState(){
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
        state.setWeight(selection);
        //getting weight
        weight = state.getWeight();
        Log.v("LOG_weightArray", Arrays.toString(weight));

        processor = databaseHelper.getProcessor("select * from processor order by abs (price - " + weight[0]*budget/100 +") asc limit 1 ");
        motherboard = databaseHelper.getMotherboard("select * from motherboard where socket = '"+ processor.getSocket() +"' or socket = '"+ processor.getSocket() +"+' order by abs (price - " + weight[1]*budget/100 +") asc limit 1 ");
        ram = databaseHelper.getRam("select * from ram where ram_type = '"+ motherboard.getRamType() +"' order by abs (price - " + weight[2]*budget/100 +") asc limit 1 ");
        gpu = databaseHelper.getGpu("select * from gpu order by abs (price - " + weight[3]*budget/100 +") asc limit 1 ");
        storage = databaseHelper.getStorage("select * from storage order by abs (price - " + weight[5]*budget/100 +") asc limit 1 ");
        monitor = databaseHelper.getMonitor("select * from monitor order by abs (price - " + weight[6]*budget/100 +") asc limit 1 ");
        casing = databaseHelper.getCasing("select * from casing order by abs (price - " + weight[7]*budget/100 +") asc limit 1 ");
        mouse = databaseHelper.getMouse("select * from mouse order by abs (price - " + weight[8]*budget/100 +") asc limit 1 ");
        keyboard = databaseHelper.getKeyboard("select * from keyboard order by abs (price - " + weight[9]*budget/100 +") asc limit 1 ");
        minTdp = (int)((Integer.parseInt(processor.getTdp())+Integer.parseInt(gpu.getTdp())+100)*1.25);
        power = databaseHelper.getPower("select * from power where power+0 >= "+ minTdp +" order by abs (price - " + weight[4]*budget/100 +") asc limit 1 ");

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
        state.setCategory(selection);
        state.setTrigger(trigger);
        state.setFlag_update(0);

        return state;
    }

    public boolean checkDatabase() throws IOException {
        File database=getApplicationContext().getDatabasePath(H_Database.DATABASE_NAME);
        if (!database.exists()) {
            Toast.makeText(A_Main.this,
                    "Database NOT EXIST", Toast.LENGTH_LONG).show();
            Log.v("LOG_checkDatabase", "NOT EXIST");
            return false;
        } else {
            Toast.makeText(A_Main.this,
                    "Database EXIST", Toast.LENGTH_LONG).show();
            Log.v("LOG_checkDatabase", "EXIST");
            return true;
        }
    }
    public void loadAllData() throws IOException {
        dataHelper.loadData(getApplicationContext(),"processor");
        dataHelper.loadData(getApplicationContext(),"motherboard");
        dataHelper.loadData(getApplicationContext(),"ram");
        dataHelper.loadData(getApplicationContext(),"gpu");
        dataHelper.loadData(getApplicationContext(),"power");
        dataHelper.loadData(getApplicationContext(),"storage");
        dataHelper.loadData(getApplicationContext(),"monitor");
        dataHelper.loadData(getApplicationContext(),"casing");
        dataHelper.loadData(getApplicationContext(),"mouse");
        dataHelper.loadData(getApplicationContext(),"keyboard");
        Toast.makeText(this, "Database Ready", Toast.LENGTH_SHORT).show();
    }
}
