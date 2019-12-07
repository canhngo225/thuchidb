package com.example.moneymanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ActivityKhoanChi extends AppCompatActivity {
    private Button btnADD,btnBack,btnReset;
    private ListView lvKC;
    private EditText edtND,edtST;
    private List<KhoanThuChi> arrayKhoanChi;
    private CustomAdapter adapter;
    public static DatabaseHandler db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_khoan_chi2);
        db = new DatabaseHandler(this);
        btnBack=findViewById(R.id.btnback);
        btnADD=findViewById(R.id.btnADD);
        lvKC=findViewById(R.id.lvKC);
        edtND=findViewById(R.id.edtND);
        edtST=findViewById(R.id.edtST);
        arrayKhoanChi=new ArrayList<>();
        arrayKhoanChi=db.getAllKC();
        setAdapter();
        btnADD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                KhoanThuChi khoanThuChi = create();
                if(khoanThuChi!=null){
                    db.themkhoanchi(khoanThuChi);
                    setAdapter();
                }
                arrayKhoanChi.clear();
                arrayKhoanChi.addAll(db.getAllKC());
                setAdapter();
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ActivityKhoanChi.this,MainActivity.class);
                startActivity(intent);
            }
        });
        btnReset=findViewById(R.id.btnReset);
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edtND.setText("");
                edtST.setText("");
            }
        });
    }

    public KhoanThuChi create(){
        String noidung = edtND.getText().toString();
        String sotien=edtST.getText().toString();
        KhoanThuChi khoanThuChi= new KhoanThuChi(noidung,sotien);
        return khoanThuChi;
    }

    public void setAdapter(){
        if(adapter==null){
            adapter=new CustomAdapter(this,R.layout.item_listview,arrayKhoanChi);
            lvKC.setAdapter(adapter);
        }
        else{
            adapter.notifyDataSetChanged();
             lvKC.setSelection(0);
        }
    }
}
