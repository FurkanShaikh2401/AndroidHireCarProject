package com.example.HireCar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class EditProfileActivity extends AppCompatActivity {
    TextInputEditText email,name;

    TextView emailtxt,nametxt,phonetxt,dlNotxt;
    FirebaseAuth mAuth;

    Button btnsave;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        initUi();
        setData();
        processUpdate();

    }
    private void initUi(){
        email=findViewById(R.id.txt_email);
        name=findViewById(R.id.txt_name);

        emailtxt=findViewById(R.id.textemail);
        nametxt=findViewById(R.id.textname);
        phonetxt=findViewById(R.id.textno);
        dlNotxt=findViewById(R.id.textDlno);

        btnsave=findViewById(R.id.fp_c_button);
    }

    public void setData(){
        final String[] is_admin = new String[4];
        mAuth=FirebaseAuth.getInstance();
        FirebaseFirestore rootref = FirebaseFirestore.getInstance();
        CollectionReference applref = rootref.collection("users");
        DocumentReference appl_id_ref = applref.document(mAuth.getCurrentUser().getUid().toString());

        appl_id_ref.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                DocumentSnapshot documentSnapshot = task.getResult();
                if (documentSnapshot.exists()) {
                    is_admin[0] = (String) documentSnapshot.get("email");
                    is_admin[1] = (String) documentSnapshot.get("full name");
                    is_admin[2] = (String) documentSnapshot.get("DL number");
                    is_admin[3] = (String) documentSnapshot.get("moblie");
//                    Toast.makeText(this, is_admin[0].
//                    toString().trim(), Toast.LENGTH_SHORT).show();
                    email.setText(is_admin[0].trim());
                    name.setText(is_admin[1].trim());

                    emailtxt.setText(is_admin[0].trim());
                    nametxt.setText(is_admin[1].trim());
                    dlNotxt.setText(is_admin[2].trim());
                    phonetxt.setText(is_admin[3].trim());

//                    UserPhone.setText(mAuth.getCurrentUser().getPhoneNumber());



                }
            }

        });
    }

    public  void processUpdate(){

        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(email.getText().toString().isEmpty()){
                    Toast.makeText(EditProfileActivity.this, "Email TextBox is empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(name.getText().toString().isEmpty()){
                    Toast.makeText(EditProfileActivity.this, "Name TextBox is empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                update(email.getText().toString(),name.getText().toString());
            }
        });
    }

    public void update(String Email,String Name){
        FirebaseFirestore db=FirebaseFirestore.getInstance();

        mAuth=FirebaseAuth.getInstance();
        DocumentReference noteRef=db.collection("users").document(mAuth.getCurrentUser().getUid().toString());

        noteRef.update("email",Email,
                "full name",Name).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(EditProfileActivity.this, "Succes", Toast.LENGTH_SHORT).show();
                    Intent intent2 = new Intent(EditProfileActivity.this,ProfileSettingsActivity.class);
                    intent2.setFlags(intent2.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent2);
                }
                else{
                    Toast.makeText(EditProfileActivity.this, "Fails", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

}
