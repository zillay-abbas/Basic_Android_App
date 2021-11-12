package pk.edu.uiit.ARID253.database_activity_assignment2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText ettName, ettPhone, ettMail, ettPassword, ettConPass, ettCountry;

    Button btnReg, btnLogin;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inilization();
        performAction();
    }

    private void inilization() {
        db = new DatabaseHelper(this);

        ettName = findViewById(R.id.etName);
        ettPhone = findViewById(R.id.etPhone);
        ettMail = findViewById(R.id.etMainEmail);
        ettPassword = findViewById(R.id.etMainPass);
        ettConPass = findViewById(R.id.etConfirmPass);
        ettCountry = findViewById(R.id.etCountry);

        btnReg = findViewById(R.id.btnMainRegister);
        btnLogin = (Button) findViewById(R.id.btnMainLogin);
    }

    private void performAction() {
        btnReg.setOnClickListener(v -> {
            if(!ettName.getText().toString().matches("")
                    && !ettPhone.getText().toString().matches("")
                    && !ettMail.getText().toString().matches("")
                    && !ettPassword.getText().toString().matches("")
                    && !ettConPass.getText().toString().matches("")
                    && !ettCountry.getText().toString().matches("")
            ){
                if (!ettPassword.getText().toString().equals(ettConPass.getText().toString())){
                    Toast.makeText(MainActivity.this,"Password Not matching",Toast.LENGTH_SHORT).show();
                }
                else {
                    ArrayList<String> userInfo = new ArrayList<>();
                    userInfo.add(ettName.getText().toString());
                    userInfo.add(ettMail.getText().toString());
                    userInfo.add(ettPhone.getText().toString());
                    userInfo.add(ettPassword.getText().toString());
                    userInfo.add(ettCountry.getText().toString());

                    long user_data = db.insertInfo(userInfo);

                    if (user_data == -1) {
                        Toast.makeText(MainActivity.this, "Error while adding user", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MainActivity.this, "User added Successfully!", Toast.LENGTH_SHORT).show();
                    }

                }
            }
            else {
                Toast.makeText(MainActivity.this, "Please fill the form", Toast.LENGTH_SHORT).show();
            }
        });

        btnLogin.setOnClickListener(v -> {
            Toast.makeText(MainActivity.this,"login clicked",Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(MainActivity.this, login.class);
            startActivity(intent);
        });

    }

}