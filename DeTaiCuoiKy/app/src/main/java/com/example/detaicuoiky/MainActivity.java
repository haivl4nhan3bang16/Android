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
    final Model data = new Model();
    static FirebaseDatabase db;
    static String userID;
    static String userEmail;
    static String userName;
    static Adapter adapter;
    EditText edtAddSubcode;
    EditText edtAddSubName;
    EditText edtAddCredits;
    EditText edtAddDescrip;
    Button btnAdd;
    static DatabaseReference myRef;
    String SubCode = "";
    String SubName = "";
    String Credits = "";
    String Descrip = "";
    static DatabaseReference post;
    @Override
    protected void onResume() {
        super.onResume();
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
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        db = FirebaseDatabase.getInstance();
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onValidateForm()) {
                    AddProduct();
                    onClearForm();
                }
            }
        });
        myRef = db.getReference("AdvancedAndroidFinalTest");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                models = new ArrayList<>();
                for(DataSnapshot ds: dataSnapshot.getChildren())
                {
                    Model model = ds.getValue(Model.class);
                    models.add(model);
                }
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
                            if (value.getSubject_code().equals(models.get(i).getSubject_code())) {
                                check = true;
                                break;
                            }
                        if (!check){
                            models.add(value);
                        }
                    }
                }
                adapter = new Adapter(MainActivity.this,R.layout.custum_recycleview, models);
                recyclerView.setAdapter(adapter);
                recyclerView.smoothScrollToPosition(adapter.getItemCount());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void OnInIt() {
        recyclerView = findViewById(R.id.recycleview);
        edtAddSubName = findViewById(R.id.edt_subname);
        edtAddSubcode = findViewById(R.id.edt_subcode);
        edtAddCredits = findViewById(R.id.edt_credits);
        edtAddDescrip = findViewById(R.id.edt_descrip);
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
    private boolean onValidateForm()
    {
        SubCode = edtAddSubcode.getText().toString();
        SubName = edtAddSubName.getText().toString();
        Credits = edtAddCredits.getText().toString();
        Descrip = edtAddDescrip.getText().toString();
        if(SubCode.equals(""))
        {
            edtAddSubcode.setError("Can be null");
            return false;
        }
        if(SubName.equals(""))
        {
            edtAddSubName.setError("Can be null");
            return false;
        }
        if(Credits.equals(""))
        {
            edtAddCredits.setError("Can be null");
            return false;
        }
        if(Descrip.equals(""))
        {
            edtAddDescrip.setError("Can be null");
            return false;
        }
        return true;
    }

    void AddProduct() {
        myRef = db.getReference("AdvancedAndroidFinalTest");
        data.setUserEmail(userEmail);
        data.setUserName(userName);
        data.setUserID(userID);
        data.setSubject_code(edtAddSubcode.getText().toString());
        data.setSubject_name(edtAddSubName.getText().toString());
        data.setCredits(Integer.parseInt(edtAddCredits.getText().toString()));
        data.setDescription(edtAddDescrip .getText().toString());
        post = myRef.child(data.getSubject_code());
        post.setValue(data);
    }
    private void onClearForm()
    {
        edtAddSubcode.setText("");
        edtAddSubName.setText("");
        edtAddCredits.setText("");
        edtAddDescrip.setText("");
    }
}
