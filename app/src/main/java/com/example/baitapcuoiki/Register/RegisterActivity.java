package com.example.baitapcuoiki.Register;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.baitapcuoiki.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {

    EditText edtUsername, edtPassword;
    Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        edtUsername = findViewById(R.id.edt_register_name);
        edtPassword = findViewById(R.id.edt_register_password);
        btnRegister = findViewById(R.id.btn_register);

        btnRegister.setOnClickListener(v -> registerUser());
    }

    private void registerUser() {
        String username = edtUsername.getText().toString();
        String password = edtPassword.getText().toString();

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Users").child(username);
        ref.child("password").setValue(password);
        ref.child("role").setValue("guest"); // mặc định là khách

        Toast.makeText(this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
        finish();
    }
}
