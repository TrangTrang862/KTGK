package com.example.ktgk;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText email, name, age, phone;
    Button insert, update, delete, view;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email = findViewById(R.id.editEmail);
        name = findViewById(R.id.editName);
        age = findViewById(R.id.editAge);
        phone = findViewById(R.id.editPhone);

        insert = findViewById(R.id.btnAdd);
        update = findViewById(R.id.btnEdit);
        delete = findViewById(R.id.btnDelete);
        view = findViewById(R.id.btnShow);
        DB = new DBHelper(this);

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailTXT = email.getText().toString();
                String nameTXT = name.getText().toString();
                String ageTXT = age.getText().toString();
                String phoneTXT = phone.getText().toString();

                Boolean checkinsertdata = DB.insertuserdata(emailTXT, nameTXT, ageTXT, phoneTXT);
                if(checkinsertdata==true) {
                    Toast.makeText(MainActivity.this, "Successfully added members", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Add member failed", Toast.LENGTH_SHORT).show();
                }
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailTXT = email.getText().toString();
                String nameTXT = name.getText().toString();
                String ageTXT = age.getText().toString();
                String phoneTXT = phone.getText().toString();

                Boolean checkupdatedata = DB.updateuserdata(emailTXT, nameTXT, ageTXT, phoneTXT);
                if(checkupdatedata==true) {
                    Toast.makeText(MainActivity.this, "Update member successfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Member update failed", Toast.LENGTH_SHORT).show();
                }
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailTXT = email.getText().toString();

                Boolean checkdeletedata = DB.deletedata(emailTXT);
                if(checkdeletedata==true) {
                    Toast.makeText(MainActivity.this, "Delete member successfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Delete member failed", Toast.LENGTH_SHORT).show();
                }
            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = DB.getdata();
                if(res.getCount()==0) {
                    Toast.makeText(MainActivity.this, "No Entry Exists", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()) {
                    buffer.append("Email: "+res.getString(0)+"\n");
                    buffer.append("Name: "+res.getString(1)+"\n");
                    buffer.append("Age: "+res.getString(2)+"\n");
                    buffer.append("Phone number: "+res.getString(3)+"\n\n");
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setCancelable(true);
                builder.setTitle("Information");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });
    }
}