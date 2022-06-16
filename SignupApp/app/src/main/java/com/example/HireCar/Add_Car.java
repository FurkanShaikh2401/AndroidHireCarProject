package com.example.HireCar;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Add_Car extends AppCompatActivity {

    ImageView imageViewCar;
    Uri ImgUri;
    String CarUriString;

    Button add_new_car;

    TextInputEditText Register_no,Model_name,Brand_name,tranmission_name,Cost_no;

    AutoCompleteTextView auto_complete_available,auto_complete_fuel,Category_name,no_seats;
    ArrayAdapter<String> adapterItems;
//    public List<String> locationlist=[];
    String avi[] ={"true","false"};
    String fuel[] ={"Petrol","Diesel","CNG"};
    String cate[]={"hatchback", "muv", "sedan", "suv"};
    String seats[]={"4","5","6","7","8"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_car);

        Register_no=findViewById(R.id.Register_no);
        Model_name = findViewById(R.id.Model_name);
        Brand_name = findViewById(R.id.Brand_name);
        tranmission_name =findViewById(R.id.tranmission_name);
        Category_name = findViewById(R.id.Category_name);
        Cost_no = findViewById(R.id.Cost_no);
        no_seats = findViewById(R.id.no_seats);

        auto_complete_available =findViewById(R.id.auto_complete_available);
        auto_complete_fuel = findViewById(R.id.auto_complete_fuel);

        adapterItems = new ArrayAdapter<String>(this, R.layout.list_item,avi);
        auto_complete_available.setAdapter(adapterItems);
        adapterItems = new ArrayAdapter<String>(this, R.layout.list_item,fuel);
        auto_complete_fuel.setAdapter(adapterItems);
        adapterItems = new ArrayAdapter<String>(this, R.layout.list_item,cate);
        Category_name.setAdapter(adapterItems);
        adapterItems = new ArrayAdapter<String>(this, R.layout.list_item,seats);
        no_seats.setAdapter(adapterItems);



        setUpImagePickerCar();
        adding_data();


    }

    public void setUpImagePickerCar(){
        imageViewCar = findViewById(R.id.imageViewCar);
        imageViewCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                activityResultLauncher.launch(Intent.createChooser(intent, "Select Picture"));
            }
        });
    }
    ActivityResultLauncher<Intent> activityResultLauncher=registerForActivityResult
            (new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK)
                    {
                        Intent data = result.getData();
                        ImgUri = data.getData();
                        imageViewCar.setImageURI(ImgUri);
                    }
                }
            });

    public void adding_data(){

        add_new_car=findViewById(R.id.add_new_car);
        add_new_car.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Register_no.getText().toString().isEmpty()){
                    Toast.makeText(Add_Car.this, "Enter Register number", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(Model_name.getText().toString().isEmpty()){
                    Toast.makeText(Add_Car.this, "Enter Model Name", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(Brand_name.getText().toString().isEmpty()){
                    Toast.makeText(Add_Car.this, "Enter Brand Name", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(tranmission_name.getText().toString().isEmpty()){
                    Toast.makeText(Add_Car.this, "Enter Tranmission Name", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(Category_name.getText().toString().isEmpty()){
                    Toast.makeText(Add_Car.this, "Enter Category number", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(Cost_no.getText().toString().isEmpty()){
                    Toast.makeText(Add_Car.this, "Enter Cost number", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(auto_complete_fuel.getText().toString().isEmpty()){
                    Toast.makeText(Add_Car.this, " Select Fuel Type", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(auto_complete_available.getText().toString().isEmpty()){
                    Toast.makeText(Add_Car.this, " Select Available Status", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(no_seats.getText().toString().isEmpty()){
                    Toast.makeText(Add_Car.this, " Enter the Seats Capacity ", Toast.LENGTH_SHORT).show();
                    return;
                }
                UploadImgCar();
            }
        });

    }

    public void UploadImgCar(){
        FirebaseStorage firebaseStorage=FirebaseStorage.getInstance();
        StorageReference ref= firebaseStorage.getReference().child("CarImage/"+Category_name.getText().toString()+"/"+Model_name.getText().toString());
        ref.putFile(ImgUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                taskSnapshot.getStorage().getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        CarUriString=uri.toString();
                        add_data(CarUriString);
                        Toast.makeText(Add_Car.this, "---"+CarUriString, Toast.LENGTH_SHORT).show();
                    }
                });

            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                double prog = (snapshot.getBytesTransferred() * 100.0) /snapshot.getTotalByteCount();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Add_Car.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void add_data(String img){
        FirebaseFirestore firebaseFirestore=FirebaseFirestore.getInstance();
        Map<String, String> data = new HashMap<>();

        data.put("available_flag",auto_complete_available.getText().toString());
        data.put("brand_name",Brand_name.getText().toString());
        data.put("capacity",no_seats.getText().toString());
        data.put("car_image",img);
        data.put("category_name",Category_name.getText().toString());
        data.put("cost",Cost_no.getText().toString());
        data.put("fuel",auto_complete_fuel.getText().toString());
        data.put("model_name",Model_name.getText().toString());
        data.put("registration_number",Register_no.getText().toString());
        data.put("transmission",tranmission_name.getText().toString());

        firebaseFirestore.collection("Cars").add(data)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
//                        Toast.makeText(Add_Car.this, "new Car data added", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Add_Car.this, ViewCar.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                    }
                });

    }

}