package com.example.attendance;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class Main2Activity extends AppCompatActivity {

    int a,b,z;
   // List<String>st3;
   // ArrayAdapter<String>arr;
   // char Z;
    String[] st3={};
    String st ,st1;
    final ArrayList<String> selectedItems = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // final ArrayList<String> selectedItems = new ArrayList<>();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        st = getIntent().getExtras().getString("Value");
        st1 = getIntent().getExtras().getString("Value1");
        a = Integer.parseInt(st);
        b = Integer.parseInt(st1);
        ListView chl = (ListView) findViewById(R.id.checkable_list);
        chl.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        StringBuilder g = new StringBuilder();
        String f = "";
        String d = "";
        Dictionary p = new Hashtable();
        //roll no generating:
        for (int i = a; i < b+1; i++) {
            g.append( String.valueOf(i+",")+"\n");
            f = (String.valueOf( g));
        }

        String[] t=f.split(",");

                  ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.rowlayout, R.id.textView,t );
                chl.setAdapter(adapter);
                chl.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        String selectedItem = ((TextView) view).getText().toString();

                        if (selectedItems.contains(selectedItem)) {
                            selectedItems.remove(selectedItem);
                        } else {
                            selectedItems.add(selectedItem);

                        }

                    }
                });
            }






    public void export(View view) {
        //generate data
        StringBuilder data = new StringBuilder();

        data.append("Roll No:,attendance: ");
        StringBuilder dat = new StringBuilder();
        StringBuilder da = new StringBuilder();
        // dat.append("\n"+selectedItems+"\n \t \n");
       // dat.append(selectedItems);
        //data.append("\n"+da);
       // data.append(String.valueOf(da));
        // da.append("\n"+selectedItems+" , " );





        for (int i = a; i < b; i++) {
            da.append("\n" + String.valueOf(i));
           // data.append(selectedItems);
             //   data.append("\n"+i);
           // data.append("\n"+selectedItems+"\n \t \n");
              //  data.append("\n"+String.valueOf(selectedItems)+","+"\n"+String.valueOf( i));

            }

        data.append(da+","+"\n"+String.valueOf(selectedItems));
      //  data.append("\n,\n"+(selectedItems));
        //data.append("\n"+String.valueOf(i)+","+String.valueOf(selectedItems));
        // data.append("\n"+selectedItems+"\n \t \n");




        try {
            //saving the file into device
            FileOutputStream out = openFileOutput("data.csv", Context.MODE_PRIVATE);
            out.write((data.toString()).getBytes());
            out.close();

            //exporting
            Context context = getApplicationContext();
            File filelocation = new File(getFilesDir(), "data.csv");
            Uri path = FileProvider.getUriForFile(context, "  com.example.attendance.Provider", filelocation);
            Intent fileIntent = new Intent(Intent.ACTION_SEND);
            fileIntent.setType("text/csv");
            fileIntent.putExtra(Intent.EXTRA_SUBJECT, "Data");
            fileIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            fileIntent.putExtra(Intent.EXTRA_STREAM, path);
            startActivity(Intent.createChooser(fileIntent, "Send mail"));

        } catch (Exception e) {
            e.printStackTrace();
        }




    }
    public void showSelectItems (View view){
        String items= "";
        for (String item : selectedItems) {
            items += "-" + item + "\n";
        }
       // Toast.makeText(this, "you have selected", Toast.LENGTH_LONG).show();

    }

 }


