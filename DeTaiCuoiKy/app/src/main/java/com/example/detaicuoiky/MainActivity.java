package com.example.detaicuoiky;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    int RC_SIGN_IN;
    GoogleSignInClient mGoogleSignInClient;
    RecyclerView recyclerView;
    List<Model> models = new ArrayList<>();
    static FirebaseDatabase db;
    static String userID;
    static String userEmail;
    static String userName;
    static Adapter adapter;
    EditText edtAddName;
    EditText edtAddPrice;
    EditText edtAddOrigin;
    Button btnAdd;
    static Model data = new Model();
    String Name = "";
    String Price = "";
    String Origin = "";
    static DatabaseReference myRef;
    static DatabaseReference post;
    @Override
    protected void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        mGoogleSignInClient.signOut()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        // ...
                    }
                });
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        signIn();
        OnInIt();
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        db = FirebaseDatabase.getInstance();
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if(onValidateForm())
               {
                    AddProduct();
                    onClearForm();
               }

            }
        });
        myRef = db.getReference("product");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(models.isEmpty())
                {
                    for(DataSnapshot ds : dataSnapshot.getChildren()){
                        Model value = ds.getValue(Model.class);
                        models.add(value);
                    }
                }else
                {
                    for (DataSnapshot ds : dataSnapshot.getChildren()) {
                        Model value = ds.getValue(Model.class);
                        Boolean check = false;
                        for (int i = 0; i < models.size(); i++)
                            if (value.getProductID().equals(models.get(i).getProductID())) {
                                check = true;
                                break;
                            }
                        if (!check){
                            models.add(value);
                        }
                    }
                }
                adapter.notifyDataSetChanged();
                recyclerView.smoothScrollToPosition(adapter.getItemCount());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        adapter = new Adapter(MainActivity.this,R.layout.custum_recycleview, models);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private void OnInIt() {
        recyclerView = findViewById(R.id.recycleview);
        edtAddName = findViewById(R.id.edt_name_product);
        edtAddPrice = findViewById(R.id.edt_price_product);
        edtAddOrigin = findViewById(R.id.edt_origin_product);
        btnAdd = findViewById(R.id.btnAdd);
    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }
    private void handleSignInResult(Task<GoogleSignInAccount> task) {
        try {
            GoogleSignInAccount account = task.getResult(ApiException.class);
            userID = account.getId();
            userEmail = account.getEmail();
            userName = account.getDisplayName();
        } catch (ApiException e) {
            finish();
        }
    }
    void AddProduct() {
        myRef = db.getReference("product");
        data.setUserEmail(userEmail);
        data.setUserName(userName);
        data.setUserID(userID);
        data.setProductName(edtAddName.getText().toString());
        data.setPrice(edtAddPrice.getText().toString());
        data.setOrigin(edtAddOrigin.getText().toString());
        post = myRef.child(data.getProductName());
        data.setProductID(post.getKey());
        post.setValue(data);
    }

    private boolean onValidateForm()
    {
        Name = edtAddName.getText().toString();
        Price = edtAddPrice.getText().toString();
        Origin = edtAddOrigin.getText().toString();
        if(Name.equals(""))
        {
            edtAddName.setError("Can be null");
            return false;
        }
        if(Price.equals(""))
        {
            edtAddPrice.setError("Can be null");
            return false;
        }
        if(Origin.equals(""))
        {
            edtAddOrigin.setError("Can be null");
            return false;
        }
        return true;
    }

    private void onClearForm()
    {
        edtAddName.setText("");
        edtAddPrice.setText("");
        edtAddOrigin.setText("");
    }
}
