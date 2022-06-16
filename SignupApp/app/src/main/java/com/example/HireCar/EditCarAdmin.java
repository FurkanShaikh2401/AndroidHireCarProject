package com.example.HireCar;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;
import java.util.Map;

public class EditCarAdmin extends AppCompatActivity {

    Button submit;
    TextView CarTitle;
    TextInputEditText reg_no, model_name, brand_name, transmission_type, cost;
    ImageView imgview;
    AutoCompleteTextView fuelType, avlFlag, Capcity, CateType;
    FirebaseFirestore db;

    String CarUriString;
    Uri ImgUri;

    ArrayAdapter<String> adapterItems;
    String avi[] ={"true","false"};
    String fuel[] ={"Petrol","Diesel","CNG"};
    String cate[]={"hatchback", "muv", "sedan", "suv"};
    String seats[]={"4","5","6","7","8"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_car);

        CarTitle = findViewById(R.id.AddCarTitle);
        reg_no = findViewById(R.id.Register_no);
        model_name = findViewById(R.id.Model_name);
        brand_name = findViewById(R.id.Brand_name);
        transmission_type =findViewById(R.id.tranmission_name);
        cost = findViewById(R.id.Cost_no);
        CateType=findViewById(R.id.Category_name);
        fuelType = findViewById(R.id.auto_complete_fuel);
        avlFlag = findViewById(R.id.auto_complete_available);
        Capcity = findViewById(R.id.no_seats);
        submit = findViewById(R.id.add_new_car);
        imgview = findViewById(R.id.imageViewCar);

        CarTitle.setText("Edit Car");
        reg_no.setText(getIntent().getStringExtra("regno"));
        model_name.setText(getIntent().getStringExtra("modelname"));
        brand_name.setText(getIntent().getStringExtra("brandname"));
        transmission_type.setText(getIntent().getStringExtra("trans"));
        cost.setText(getIntent().getStringExtra("cost"));
        CateType.setText(getIntent().getStringExtra("catname"));
        fuelType.setText(getIntent().getStringExtra("fuel"));
        avlFlag.setText(getIntent().getStringExtra("avlflag"));
        Capcity.setText(getIntent().getStringExtra("cap"));
//        imgview.setImageURI(Uri.parse(getIntent().getStringExtra("carimage")));
        Glide.with(getApplicationContext()).load(getIntent().getStringExtra("carimage")).into(imgview);


        adapterItems = new ArrayAdapter<String>(this, R.layout.list_item,avi);
        avlFlag.setAdapter(adapterItems);

        adapterItems = new ArrayAdapter<String>(this, R.layout.list_item,fuel);
        fuelType.setAdapter(adapterItems);

        adapterItems = new ArrayAdapter<String>(this, R.layout.list_item,seats);
        Capcity.setAdapter(adapterItems);

        adapterItems = new ArrayAdapter<String>(this, R.layout.list_item,cate);
        CateType.setAdapter(adapterItems);

        Toast.makeText(this, "id: "+ getIntent().getStringExtra("carid"), Toast.LENGTH_SHORT).show();
        Toast.makeText(this, "regno: "+ getIntent().getStringExtra("regno"), Toast.LENGTH_SHORT).show();
        Toast.makeText(this, "brandname: "+ getIntent().getStringExtra("brandname"), Toast.LENGTH_SHORT).show();
        Toast.makeText(this, "modelname: "+ getIntent().getStringExtra("modelname"), Toast.LENGTH_SHORT).show();
        Toast.makeText(this, "fuel: "+ getIntent().getStringExtra("fuel"), Toast.LENGTH_SHORT).show();
        Toast.makeText(this, "trans: "+ getIntent().getStringExtra("trans"), Toast.LENGTH_SHORT).show();
        Toast.makeText(this, "cap: "+ getIntent().getStringExtra("cap"), Toast.LENGTH_SHORT).show();
        Toast.makeText(this, "cost: "+ getIntent().getStringExtra("cost"), Toast.LENGTH_SHORT).show();
        Toast.makeText(this, "catname: "+ getIntent().getStringExtra("catname"), Toast.LENGTH_SHORT).show();
        Toast.makeText(this, "avlflag: "+ getIntent().getStringExtra("avlflag"), Toast.LENGTH_SHORT).show();
        Toast.makeText(this, "carimage: "+ getIntent().getStringExtra("carimage"), Toast.LENGTH_SHORT).show();

        setUpImagePickerCar();
        cheData();
    }

    public void setdata(){
        FirebaseFirestore fd=FirebaseFirestore.getInstance();
        fd.collection("Cars").document(getIntent().getStringExtra("carid")).get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {


                    }
                });

    }


    public void setUpImagePickerCar(){
        imgview.setOnClickListener(new View.OnClickListener() {
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
                        imgview.setImageURI(ImgUri);
                    }
                }
            });

    public void cheData(){
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(reg_no.getText().toString().isEmpty()){
                    Toast.makeText(EditCarAdmin.this, "Enter Register number", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(model_name.getText().toString().isEmpty()){
                    Toast.makeText(EditCarAdmin.this, "Enter Model Name", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(brand_name.getText().toString().isEmpty()){
                    Toast.makeText(EditCarAdmin.this, "Enter Brand Name", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(transmission_type.getText().toString().isEmpty()){
                    Toast.makeText(EditCarAdmin.this, "Enter Tranmission Name", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(CateType.getText().toString().isEmpty()){
                    Toast.makeText(EditCarAdmin.this, "Enter Category number", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(cost.getText().toString().isEmpty()){
                    Toast.makeText(EditCarAdmin.this, "Enter Cost number", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(fuelType.getText().toString().isEmpty()){
                    Toast.makeText(EditCarAdmin.this, " Select Fuel Type", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(avlFlag.getText().toString().isEmpty()){
                    Toast.makeText(EditCarAdmin.this, " Select Available Status", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(Capcity.getText().toString().isEmpty()){
                    Toast.makeText(EditCarAdmin.this, " Enter the Seats Capacity ", Toast.LENGTH_SHORT).show();
                    return;
                }
                EidtUploadImgCar();
            }
        });
    }


    public void  EidtUploadImgCar(){
        FirebaseStorage firebaseStorage=FirebaseStorage.getInstance();
        StorageReference ref= firebaseStorage.getReference().child("CarImage/"+CateType.getText().toString()+"/"+model_name.getText().toString());
        ref.putFile(ImgUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                taskSnapshot.getStorage().getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        CarUriString=uri.toString();
                        edit_data(CarUriString);
                        Toast.makeText(EditCarAdmin.this, "---"+CarUriString, Toast.LENGTH_SHORT).show();

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
                Toast.makeText(EditCarAdmin.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void edit_data(String UI){

        final DocumentReference docRef = FirebaseFirestore.getInstance()
                .collection("Cars").document(getIntent().getStringExtra("carid"));

        Map<String, Object> updatedData = new HashMap<>();
        updatedData.put("brand_name", brand_name.getText().toString());
        updatedData.put("capacity", Capcity.getText().toString());
        updatedData.put("car_image",UI);
        updatedData.put("category_name",CateType.getText().toString());
        updatedData.put("cost",cost.getText().toString());
        updatedData.put("fuel",fuelType.getText().toString());
        updatedData.put("model_name",model_name.getText().toString());
        updatedData.put("registration_number",reg_no.getText().toString());
        updatedData.put("transmission",transmission_type.getText().toString());
        updatedData.put("available_flag", avlFlag.getText().toString());

        docRef.update(updatedData).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(EditCarAdmin.this, "Car Data is updated!!!", Toast.LENGTH_SHORT).show();
                Intent intent1 = new Intent(EditCarAdmin.this, ViewCar.class);
                intent1.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent1);
            }
        });
//        db = FirebaseFirestore.getInstance();
//
//        Toast.makeText(this, "Card_id--"+getIntent().getStringExtra("carid"), Toast.LENGTH_SHORT).show();
//        submit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                CarModelAdmin cma = new CarModelAdmin();
//
////                cma.setBrand_name(brand_name.getText().toString());
////                cma.setCapacity(Capcity.getText().toString());
////                cma.setAvailable_flag(avlFlag.getText().toString());
////                cma.setCar_image(UI);
////                cma.setCategory_name(CateType.getText().toString());
////                cma.setCost(cost.getText().toString());
////                cma.setFuel(fuelType.getText().toString());
////                cma.setModel_name(model_name.getText().toString());
////                cma.setRegistration_number(reg_no.getText().toString());
////                cma.setTransmission(transmission_type.getText().toString());
//            }
//        });
    }

}
