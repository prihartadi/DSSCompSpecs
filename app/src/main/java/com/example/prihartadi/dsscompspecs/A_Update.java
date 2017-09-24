package com.example.prihartadi.dsscompspecs;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class A_Update extends AppCompatActivity {

    Button v_update;
    List<String> dataList;
    ListView v_dataList;
    ArrayAdapter adapter;
    EditText v_address;
    H_Database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a__update);

        db = new H_Database(this);
        dataList = new ArrayList<String>();
        v_address = (EditText)findViewById(R.id.editAddress);
        v_update = (Button)findViewById(R.id.btnUpdate);
        v_dataList = (ListView)findViewById(R.id.listData);
        adapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1,dataList);
        v_dataList.setAdapter(adapter);

        v_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                H_ServerAccess conn = new H_ServerAccess();
                String server = v_address.getText().toString();
                Log.v("LOG_oC_update",server);
                if(server.isEmpty()){
                    Toast.makeText(getApplicationContext(),"INPUT SERVER",Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(A_Update.this,
                            "Connecting to Server", Toast.LENGTH_SHORT).show();
                    try {
                        Toast.makeText(A_Update.this,
                                "Downloading Data", Toast.LENGTH_SHORT).show();
                        C_Processor[] processor = (C_Processor[]) conn.getData(server, C_Processor[].class, "cpu");
                        C_Motherboard[] motherboard = (C_Motherboard[]) conn.getData(server, C_Motherboard[].class, "mbo");
                        C_Ram[] ram = (C_Ram[]) conn.getData(server, C_Ram[].class, "ram");
                        C_Gpu[] gpu = (C_Gpu[]) conn.getData(server, C_Gpu[].class, "gpu");
                        C_Power[] power = (C_Power[]) conn.getData(server, C_Power[].class, "psu");
                        C_Storage[] storage = (C_Storage[]) conn.getData(server, C_Storage[].class, "mem");
                        C_Monitor[] monitor = (C_Monitor[]) conn.getData(server, C_Monitor[].class, "dsp");
                        C_Casing[] casing = (C_Casing[]) conn.getData(server, C_Casing[].class, "cas");
                        C_Mouse[] mouse = (C_Mouse[]) conn.getData(server, C_Mouse[].class, "mos");
                        C_Keyboard[] keyboard = (C_Keyboard[]) conn.getData(server, C_Keyboard[].class, "key");

                        dataList.add("Processor : "+processor.length+"");
                        dataList.add("Motherboard : "+motherboard.length+"");
                        dataList.add("Ram : "+ram.length+"");
                        dataList.add("Gpu : "+gpu.length+"");
                        dataList.add("Power Supply : "+power.length+"");
                        dataList.add("Storage : "+storage.length+"");
                        dataList.add("Monitor : "+monitor.length+"");
                        dataList.add("Casing : "+casing.length+"");
                        dataList.add("Mouse : "+mouse.length+"");
                        dataList.add("Keyboard : " + keyboard.length+"");

                        adapter.notifyDataSetChanged();

                        db.deleteAllTable();

                        Log.v("log_deleteCPU",db.getAllProcessors("SELECT  * FROM processor").size()+"");
                        Log.v("log_deleteMBO",db.getAllMotherboards("SELECT  * FROM motherboard").size()+"");
                        Log.v("log_deleteRAM",db.getAllRams("SELECT  * FROM ram").size()+"");
                        Log.v("log_deleteGPU",db.getAllGpus("SELECT  * FROM gpu").size()+"");
                        Log.v("log_deletePSU",db.getAllPowers("SELECT  * FROM power").size()+"");
                        Log.v("log_deleteMEM",db.getAllStorages("SELECT  * FROM storage").size()+"");
                        Log.v("log_deleteDSP",db.getAllMonitors("SELECT  * FROM monitor").size()+"");
                        Log.v("log_deleteCAS",db.getAllCasings("SELECT  * FROM casing").size()+"");
                        Log.v("log_deleteMOS",db.getAllMouses("SELECT  * FROM mouse").size()+"");
                        Log.v("log_deleteKEY",db.getAllKeyboards("SELECT  * FROM keyboard").size()+"");

                        Toast.makeText(A_Update.this,
                                "Updating Database", Toast.LENGTH_SHORT).show();

                        for(C_Processor baru:processor){
                            db.createProcessor(baru);
                        }
                        for(C_Motherboard baru:motherboard){
                            db.createMotherboard(baru);
                        }
                        for(C_Ram baru:ram){
                            db.createRam(baru);
                        }
                        for(C_Gpu baru:gpu){
                            db.createGpu(baru);
                        }
                        for(C_Power baru:power){
                            db.createPower(baru);
                        }
                        for(C_Storage baru:storage){
                            db.createStorage(baru);
                        }
                        for(C_Monitor baru:monitor){
                            db.createMonitor(baru);
                        }
                        for(C_Casing baru:casing){
                            db.createCasing(baru);
                        }
                        for(C_Mouse baru:mouse){
                            db.createMouse(baru);
                        }
                        for(C_Keyboard baru:keyboard){
                            db.createKeyboard(baru);
                        }

                        Log.v("log_updateCPU",db.getAllProcessors("SELECT  * FROM processor").size()+"");
                        Log.v("log_updateMBO",db.getAllMotherboards("SELECT  * FROM motherboard").size()+"");
                        Log.v("log_updateRAM",db.getAllRams("SELECT  * FROM ram").size()+"");
                        Log.v("log_updateGPU",db.getAllGpus("SELECT  * FROM gpu").size()+"");
                        Log.v("log_updatePSU",db.getAllPowers("SELECT  * FROM power").size()+"");
                        Log.v("log_updateMEM",db.getAllStorages("SELECT  * FROM storage").size()+"");
                        Log.v("log_updateDSP",db.getAllMonitors("SELECT  * FROM monitor").size()+"");
                        Log.v("log_updateCAS",db.getAllCasings("SELECT  * FROM casing").size()+"");
                        Log.v("log_updateMOS",db.getAllMouses("SELECT  * FROM mouse").size()+"");
                        Log.v("log_updateKEY",db.getAllKeyboards("SELECT  * FROM keyboard").size()+"");

                        Toast.makeText(A_Update.this,
                                "Update Complete", Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }
        });

    }
}
