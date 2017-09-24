package com.example.prihartadi.dsscompspecs;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by prihartadi on 10/07/2017.
 */

public class H_Data {

    H_Database db;

    public void loadData(Context context, String part) throws IOException {
        db = new H_Database(context);
        String thisLine;
        List<String[]> lines = new ArrayList<String[]>();

        //from dataCompCSV dir on ext
//        String path = Environment.getExternalStorageDirectory().getPath()+"/dataCompCSV/"+part+".csv";
//        FileInputStream fis = new FileInputStream(path);
//        DataInputStream myInput = new DataInputStream(fis);
//        while ((thisLine = myInput.readLine()) != null) {
//            lines.add(thisLine.split(";"));
//        }

        //from assets dir
        AssetManager mg = context.getResources().getAssets();
        InputStream is = null;
        try {
            is = mg.open(part+".csv");
            Log.i("LOG_loadData_iS", "Found");
        } catch (IOException ex) {
            Log.i("LOG_loadData_iS", "Not Found");
        }
        BufferedReader bReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
        while ((thisLine = bReader.readLine()) != null) {
            lines.add(thisLine.split(";"));
        }
        bReader.close();
//        Log.v("LOG_loadData_list",""+lines.size());

        String[][] array = new String[lines.size()][0];
        lines.toArray(array);
//        Log.v("LOG_loadData()",""+array.length);

        switch (part){
            case "processor":
                ArrayList<C_Processor> processor_list = new ArrayList<C_Processor>();
                for(int i=0; i<array.length; i++){
                    processor_list.add(new C_Processor(array[i][0],array[i][1],array[i][2],array[i][3],array[i][4],array[i][5],array[i][6],array[i][7],Long.parseLong(array[i][8]),Double.parseDouble(array[i][9])));
                    db.createProcessor(processor_list.get(i));
                }
                Log.v("LOG_lodData_processor",""+db.getAllProcessors("SELECT  * FROM processor").size());
                break;
            case "motherboard":
                ArrayList<C_Motherboard> motherboard_list = new ArrayList<C_Motherboard>();
                for(int i=0; i<array.length; i++){
                    motherboard_list.add(new C_Motherboard(array[i][0],array[i][1],array[i][2],array[i][3],array[i][4],array[i][5],array[i][6],array[i][7],Long.parseLong(array[i][8]),Double.parseDouble(array[i][9])));
                    db.createMotherboard(motherboard_list.get(i));
                }
                Log.v("LOG_loadData_mboard",""+db.getAllMotherboards("SELECT  * FROM motherboard").size());
                break;
            case "ram":
                ArrayList<C_Ram> ram_list = new ArrayList<C_Ram>();
                for(int i=0; i<array.length; i++){
                    ram_list.add(new C_Ram(array[i][0],array[i][1],array[i][2],array[i][3],array[i][4],array[i][5],array[i][6],Long.parseLong(array[i][7]),Double.parseDouble(array[i][8])));
                    db.createRam(ram_list.get(i));
                }
                Log.v("LOG_loadData_ram",""+db.getAllRams("SELECT  * FROM ram").size());
                break;
            case "gpu":
                ArrayList<C_Gpu> gpu_list = new ArrayList<C_Gpu>();
                for(int i=0; i<array.length; i++){
                    gpu_list.add(new C_Gpu(array[i][0],array[i][1],array[i][2],array[i][3],array[i][4],array[i][5],array[i][6],array[i][7],array[i][8],Long.parseLong(array[i][9]),Double.parseDouble(array[i][10])));
                    db.createGpu(gpu_list.get(i));
                }
                Log.v("LOG_loadData_gpu",""+db.getAllGpus("SELECT  * FROM gpu").size());
                break;
            case "power":
                ArrayList<C_Power> power_list = new ArrayList<C_Power>();
                for(int i=0; i<array.length; i++){
                    power_list.add(new C_Power(array[i][0],array[i][1],array[i][2],array[i][3],Long.parseLong(array[i][4]),Double.parseDouble(array[i][5])));
                    db.createPower(power_list.get(i));
                }
                Log.v("LOG_loadData_power",""+db.getAllPowers("SELECT  * FROM power").size());
                break;
            case "storage":
                ArrayList<C_Storage> storage_list = new ArrayList<C_Storage>();
                for(int i=0; i<array.length; i++){
                    storage_list.add(new C_Storage(array[i][0],array[i][1],array[i][2],array[i][3],array[i][4],Long.parseLong(array[i][5]),Double.parseDouble(array[i][6])));
                    db.createStorage(storage_list.get(i));
                }
                Log.v("LOG_loadData_storage",""+db.getAllStorages("SELECT  * FROM storage").size());
                break;
            case "monitor":
                ArrayList<C_Monitor> monitor_list = new ArrayList<C_Monitor>();
                for(int i=0; i<array.length; i++){
                    monitor_list.add(new C_Monitor(array[i][0],array[i][1],array[i][2],array[i][3],array[i][4],Long.parseLong(array[i][5]),Double.parseDouble(array[i][6])));
                    db.createMonitor(monitor_list.get(i));
                }
                Log.v("LOG_loadData_monitor",""+db.getAllMonitors("SELECT  * FROM monitor").size());
                break;
            case "casing":
                ArrayList<C_Casing> casing_list = new ArrayList<C_Casing>();
                for(int i=0; i<array.length; i++){
                    casing_list.add(new C_Casing(array[i][0],array[i][1],array[i][2],Long.parseLong(array[i][3]),Double.parseDouble(array[i][4])));
                    db.createCasing(casing_list.get(i));
                }
                Log.v("LOG_loadData_casing",""+db.getAllCasings("SELECT  * FROM casing").size());
                break;
            case "mouse":
                ArrayList<C_Mouse> mouse_list = new ArrayList<C_Mouse>();
                for(int i=0; i<array.length; i++){
                    mouse_list.add(new C_Mouse(array[i][0],array[i][1],Long.parseLong(array[i][2]),Double.parseDouble(array[i][3])));
                    db.createMouse(mouse_list.get(i));
                }
                Log.v("LOG_loadData_mouse",""+db.getAllMouses("SELECT  * FROM mouse").size());
                break;
            case "keyboard":
                ArrayList<C_Keyboard> keyboard_list = new ArrayList<C_Keyboard>();
                for(int i=0; i<array.length; i++){
                    keyboard_list.add(new C_Keyboard(array[i][0],array[i][1],Long.parseLong(array[i][2]),Double.parseDouble(array[i][3])));
                    db.createKeyboard(keyboard_list.get(i));
                }
                Log.v("LOG_loadData_key",""+db.getAllKeyboards("SELECT  * FROM keyboard").size());
                break;
        }
    }

    public void writeFile(Context context, String filename, List<Double> array) {
        Gson gs = new Gson();
        FileOutputStream outputStream = null;
        try {
            outputStream = context.openFileOutput(filename, Context.MODE_PRIVATE);
            outputStream.write(gs.toJson(array).getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String readFile(Context context, String filename) {
        BufferedReader input = null;
        File file = null;
        StringBuffer buffer = new StringBuffer();
        try {
            file = new File(context.getFilesDir(), filename);
            input = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            String line;
            while ((line = input.readLine()) != null) {
                buffer.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return buffer.toString();
    }

}
