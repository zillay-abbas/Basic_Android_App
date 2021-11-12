package pk.edu.uiit.ARID253.database_activity_assignment2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class student extends AppCompatActivity {

    TextView name, email, phone, password;
    Button btnchoose;
    ImageView img;
    DatabaseHelper db;
    private static final int PICK_IMAGE = 100;
    Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);

        initialized();
        performAction();
    }



    private void initialized(){
        db = new DatabaseHelper(this);
        name = (TextView) findViewById(R.id.name);
        email = (TextView) findViewById(R.id.email);
        phone = (TextView) findViewById(R.id.phone);
        password = (TextView) findViewById(R.id.pass);
        btnchoose = (Button) findViewById(R.id.btnChoose);
        img = (ImageView) findViewById(R.id.img);
    }

    private void performAction(){
        Bundle bundle = getIntent().getExtras();
        String getEmail = bundle.getString("email");
        String getPass = bundle.getString("pass");

        Cursor res = db.login(getEmail,getPass);

        if(res.getCount() == 0){
            Toast.makeText(student.this, "No data found", Toast.LENGTH_SHORT ).show();
        }else{
            while(res.moveToNext()){
                name.setText(res.getString(1));
                email.setText(res.getString(2));
                phone.setText(res.getString(3));
                password.setText(res.getString(4));
            }
        }
        btnchoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                startActivityForResult(gallery, PICK_IMAGE);
            }

        });
    }

}