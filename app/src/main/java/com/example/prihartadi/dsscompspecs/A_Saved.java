package com.example.prihartadi.dsscompspecs;

import android.content.ContextWrapper;
import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import static android.R.attr.path;

public class A_Saved extends AppCompatActivity {

    ListView listSaved;
    ArrayAdapter adapter;
    File[] files;
    String[] filenames;
    File directory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a__saved);

        Gson gs = new Gson();

        ContextWrapper cw = new ContextWrapper(getApplicationContext());
        directory = cw.getDir("saved", getApplicationContext().MODE_PRIVATE);
        if (!directory.exists()) {
            directory.mkdir();
        }
        Log.v("LOG_file", "Path: " + directory.toString());

        files = directory.listFiles();
        filenames = new String[files.length];
        Log.d("Files", "Size: "+ files.length);
        for (int i = 0; i < files.length; i++)
        {
            filenames[i] = files[i].getName();
            Log.d("Files", "FileName:" + files[i].getName());
            Log.d("Files", "FileContent:" + files[i]);
        }
        listSaved = (ListView)findViewById(R.id.listSaved);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, filenames);
        listSaved.setAdapter(adapter);
        registerForContextMenu(listSaved);
        listSaved.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(), A_CleanResult.class);

                String savedFile = "";
                try {
                    File mypath = new File(directory,filenames[i]);
                    BufferedReader input = new BufferedReader(new InputStreamReader(new FileInputStream(mypath)));
                    String receiveString;
                    StringBuilder stringBuilder = new StringBuilder();
                    while ( (receiveString = input.readLine()) != null ) {
                        stringBuilder.append(receiveString);
                    }
                    savedFile = stringBuilder.toString();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Log.v("LOG_savedState",savedFile);

                intent.putExtra("Saved",savedFile);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo)
    {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add(0, v.getId(), 0, "Hapus");
    }
    @Override
    public boolean onContextItemSelected(MenuItem item){
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        if(item.getTitle()=="Hapus"){
            //Toast.makeText(getApplicationContext(),info.id+"/"+info.position,Toast.LENGTH_LONG).show();
            files[info.position].delete();
            Toast.makeText(getApplicationContext(),"Hapus",Toast.LENGTH_LONG).show();
            Intent intent = new Intent(getApplicationContext(), A_Main.class);
            startActivity(intent);
            finish();
        }else{
            return false;
        }
        adapter.notifyDataSetChanged();
        return true;
    }

}
