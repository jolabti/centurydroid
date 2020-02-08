package com.example.century;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.century.API.ApiInterface;
import com.example.century.API.RetrofitClient;
import com.example.century.GSON.GSONResponseLogin;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    AppCompatEditText edEmail, edPassword = null;
    AppCompatButton btnLogin, btnSignup = null;

    AppCompatTextView tvForgot = null;

    ApiInterface apiInterface=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        declareComponent();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Rest api di proses di sini beserta inputan email dan password

                Log.d("test_email", edEmail.getText().toString().trim());
                Log.d("test_password", edPassword.getText().toString());
                startRequestLogin(edEmail.getText().toString().trim(), edPassword.getText().toString());
            }
        });

    }

    private void declareComponent(){

        edEmail = findViewById(R.id.ed_email_id);
        edPassword= findViewById(R.id.ed_passwrod_id);

        btnLogin = findViewById(R.id.button_login_id);
        btnSignup = findViewById(R.id.button_login_id);

        tvForgot = findViewById(R.id.tv_forgot_id);

        apiInterface = RetrofitClient.getClient().create(ApiInterface.class);

    }


    private void startRequestLogin(String email, String password){

            if(email==null){

                showMessage("Email belum diisi");

            }

            else if(password == null){

                showMessage("Password belum diisi");
            }

            else if(email ==null && password==null){

                showMessage("Form authentikan belum diisi");
            }

            else{


                Call<GSONResponseLogin> postAuthServer= apiInterface.postAuth(email,password);

                postAuthServer.enqueue(new Callback<GSONResponseLogin>() {
                    @Override
                    public void onResponse(Call<GSONResponseLogin> call, Response<GSONResponseLogin> response) {

                        if(response.isSuccessful()){

                            showMessage("Anda berhasil login");
                        }
                        else{

                            if(response.code()==500){

                                showMessage("Response server mati");
                            }
                            else{
                                showMessage("Anda tidak berhak masuk sistem");
                            }

                        }

                    }

                    @Override
                    public void onFailure(Call<GSONResponseLogin> call, Throwable t) {

                         if(call.isCanceled()){

                                showMessage("Kegagalan server");
                         }

                    }
                });


            }


    }

    private void showMessage(String message){

            runOnUiThread(new Runnable() {
                @Override
                public void run() {

                    if(message!=null){

                        Toast.makeText(getApplicationContext(),message, Toast.LENGTH_SHORT).show();
                    }

                }
            });



    }




}
