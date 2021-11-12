package pk.edu.uiit.ARID253.database_activity_assignment2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class login extends AppCompatActivity {

    EditText etMail, etPassword;

    Button btnReg, btnLogin;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);


        inilization();
        performAction();
    }

    private void performAction() {

        btnLogin.setOnClickListener(v -> {
            if( !etMail.getText().toString().matches("")
                    && !etPassword.getText().toString().matches("")
            ){
                Cursor cur = db.login(etMail.getText().toString(), etPassword.getText().toString());

                if (cur.getCount() > 0) {

                    Toast.makeText(this, "Login Successfully", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(login.this, student.class);
                    intent.putExtra("email",etMail.getText().toString());
                    intent.putExtra("pass",etPassword.getText().toString());

                    startActivity(intent);

                } else {
                    Toast.makeText(this, "Invalid Password", Toast.LENGTH_SHORT).show();
                }

                cur.close();
                db.close();

            }
            else {
                Toast.makeText(this, "Please fill the form", Toast.LENGTH_SHORT).show();
            }
        });

        btnReg.setOnClickListener(v -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });
    }

    private void inilization() {
        db = new DatabaseHelper(this);

        etMail = (EditText) findViewById(R.id.etEmail);
        etPassword = (EditText) findViewById(R.id.etPass);

        btnReg = (Button) findViewById(R.id.btnRegister);
        btnLogin = (Button) findViewById(R.id.btnLogin);
    }
}