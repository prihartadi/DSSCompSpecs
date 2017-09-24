package com.example.prihartadi.dsscompspecs;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class A_Search extends AppCompatActivity {

    EditText editSearch;
    ListView listItems;
    ArrayAdapter<String> adapter;
//    List<HashMap<String, String>> productList;
//    List<String> productList;
    List<C_UnItem> productList;
    H_CustomAdapterSearch adapterSearch;
    H_Database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a__search);

        db = new H_Database(this);
        productList = new ArrayList<C_UnItem>();

        editSearch = (EditText)findViewById(R.id.editSearch);
        listItems = (ListView)findViewById(R.id.listItems);

        Bundle extra = getIntent().getExtras();
        int part = extra.getInt("Parts");
        //Toast.makeText(this, "Part Selection "+part, Toast.LENGTH_SHORT).show();


        switch (part){
            case 0:
                List<C_Processor> allProcessors = db.getAllProcessors("SELECT  * FROM processor");
                for (C_Processor item:allProcessors){
                    C_UnItem ii = new C_UnItem();
                    ii.setId(item.getId());
                    ii.setName(item.getBrand()+" "+item.getCode()+" "+item.getSocket());
                    ii.setPrice(item.getPrice());
                    productList.add(ii);
                }
                break;
            case 1:
                List<C_Motherboard> allMotherboard = db.getAllMotherboards("SELECT  * FROM motherboard");
                for (C_Motherboard item:allMotherboard){
                    C_UnItem ii = new C_UnItem();
                    ii.setId(item.getId());
                    ii.setName(item.getBrand()+" "+item.getCode()+" "+item.getSocket()+" "+item.getRamType());
                    ii.setPrice(item.getPrice());
                    productList.add(ii);
                }
                break;
            case 2:
                List<C_Ram> allRam = db.getAllRams("SELECT  * FROM ram");
                for (C_Ram item:allRam){
                    C_UnItem ii = new C_UnItem();
                    ii.setId(item.getId());
                    ii.setName(item.getBrand()+" "+item.getCode()+" "+item.getRamType());
                    ii.setPrice(item.getPrice());
                    productList.add(ii);
                }
                break;
            case 3:
                List<C_Gpu> allGpu = db.getAllGpus("SELECT  * FROM gpu");
                for (C_Gpu item:allGpu){
                    C_UnItem ii = new C_UnItem();
                    ii.setId(item.getId());
                    ii.setName(item.getBrand()+" "+item.getCode()+" "+item.getChipset());
                    ii.setPrice(item.getPrice());
                    productList.add(ii);
                }
                break;
            case 4:
                List<C_Power> allPower = db.getAllPowers("SELECT  * FROM power");
                for (C_Power item:allPower){
                    C_UnItem ii = new C_UnItem();
                    ii.setId(item.getId());
                    ii.setName(item.getBrand()+" "+item.getCode()+" "+item.getPower());
                    ii.setPrice(item.getPrice());
                    productList.add(ii);
                }
                break;
            case 5:
                List<C_Storage> allStorage = db.getAllStorages("SELECT  * FROM storage");
                for (C_Storage item:allStorage){
                    C_UnItem ii = new C_UnItem();
                    ii.setId(item.getId());
                    ii.setName(item.getBrand()+" "+item.getCode()+" "+item.getType()+" "+item.getSize());
                    ii.setPrice(item.getPrice());
                    productList.add(ii);
                }
                break;
            case 6:
                List<C_Monitor> allMonitor = db.getAllMonitors("SELECT  * FROM monitor");
                for (C_Monitor item:allMonitor){
                    C_UnItem ii = new C_UnItem();
                    ii.setId(item.getId());
                    ii.setName(item.getBrand()+" "+item.getCode()+" "+item.getWide()+" "+item.getResX()+"x"+item.getResY());
                    ii.setPrice(item.getPrice());
                    productList.add(ii);
                }
                break;
            case 7:
                List<C_Casing> allCasing = db.getAllCasings("SELECT  * FROM casing");
                for (C_Casing item:allCasing){
                    C_UnItem ii = new C_UnItem();
                    ii.setId(item.getId());
                    ii.setName(item.getBrand()+" "+item.getCode());
                    ii.setPrice(item.getPrice());
                    productList.add(ii);
                }
                break;
            case 8:
                List<C_Mouse> allMouse = db.getAllMouses("SELECT  * FROM mouse");
                for (C_Mouse item:allMouse){
                    C_UnItem ii = new C_UnItem();
                    ii.setId(item.getId());
                    ii.setName(item.getBrand()+" "+item.getCode());
                    ii.setPrice(item.getPrice());
                    productList.add(ii);
                }
                break;
            case 9:
                List<C_Keyboard> allKeyboard = db.getAllKeyboards("SELECT  * FROM keyboard");
                for (C_Keyboard item:allKeyboard){
                    C_UnItem ii = new C_UnItem();
                    ii.setId(item.getId());
                    ii.setName(item.getBrand()+" "+item.getCode());
                    ii.setPrice(item.getPrice());
                    productList.add(ii);
                }
                break;
        }

        adapterSearch = new H_CustomAdapterSearch(this,productList);
        listItems.setAdapter(adapterSearch);

        editSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//                A_Search.this.adapter.getFilter().filter(charSequence);
            }
            @Override
            public void afterTextChanged(Editable editable) {
                String text = editSearch.getText().toString().toLowerCase(Locale.getDefault());
                adapterSearch.filter(text);
            }
        });

    }
}
