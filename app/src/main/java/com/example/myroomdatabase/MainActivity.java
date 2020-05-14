package com.example.myroomdatabase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    //initialize variable
    EditText editText;
    Button btnAdd, btnReset;
    List<MainData> listMain= new ArrayList<>();
    //hello
    LinearLayoutManager linearLayoutManager;
    RoomBD roomDB;
    MainAdapter mainAdapter;
    //initalize recycleview
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

    }

    private void init() {
        editText= findViewById(R.id.edTextInput);
        btnAdd = findViewById(R.id.btnAdd);
        btnReset= findViewById(R.id.btnReset);
        roomDB= RoomBD.getInstan(this);
        recyclerView= findViewById(R.id.recycle_View);
        listMain= roomDB.mainDao().getAllData();
        linearLayoutManager= new LinearLayoutManager(this);
        mainAdapter= new MainAdapter(listMain,this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(mainAdapter);


        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String etext= editText.getText().toString().trim();
                if (!etext.equals("")){
                    MainData maindata= new MainData();
                    maindata.setText(etext);
                    roomDB.mainDao().insert(maindata);
                    editText.setText("");
                    listMain.clear();
                    listMain.addAll(roomDB.mainDao().getAllData());
                    mainAdapter.notifyDataSetChanged();
                }
            }
        });

        //set up reset click
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //goij hamf del
                roomDB.mainDao().reset(listMain);
                listMain.clear();
                listMain.addAll(roomDB.mainDao().getAllData());
                //thoong bao thay doi
                mainAdapter.notifyDataSetChanged();
                //helelo
                //hello
            }
        });


    }


}
