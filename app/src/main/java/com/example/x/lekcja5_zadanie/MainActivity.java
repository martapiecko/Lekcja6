package com.example.x.lekcja5_zadanie;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ListView lv;
    private String[] tele;
    private Class[] activities={Tele1Activity.class, Tele2Activity.class, Tele3Activity.class};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv = (ListView) findViewById(R.id.telephones);

        initResources();
        initTelephonesListView();
    }

    private void initResources(){
        Resources res = getResources();
        tele = res.getStringArray(R.array.telephones);
    }

    private void initTelephonesListView(){
        lv.setAdapter(new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_list_item_1,tele));
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            public void onItemClick(AdapterView<?> parent, View v, int pos, long id){
                Context context;
                context = getApplicationContext();
                Intent intent = new Intent(context,activities[pos]);
                startActivity(intent);
            }
        });
    }

    private Boolean exit = false;

    @Override
    public void onBackPressed() {
        if(exit) {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        } else {
            Toast.makeText(this, "Press Back again to Exit.", Toast.LENGTH_SHORT).show();
            exit = true;
        }
    }
}

