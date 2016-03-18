package healthsignz.com.myownarraylist;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    LinkList linkList ;String id;
    String name;
    TextView textview_linklistdata,textview_position,textview_element,textview_size,textview_label_size,textview_username;
    EditText edittext_element,edittext_position;
    LoginButton loginButton;
    CallbackManager callbackManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_main);
        callbackManager = CallbackManager.Factory.create();

        textview_linklistdata = (TextView)findViewById(R.id.textview_linklistdata);
        edittext_element = (EditText)findViewById(R.id.edittext_element);
        edittext_position = (EditText)findViewById(R.id.edittext_pos);
        textview_position = (TextView)findViewById(R.id.textview_position);
        textview_element =(TextView)findViewById(R.id.textview_element);
        textview_label_size = (TextView)findViewById(R.id.label_size);
        textview_size = (TextView)findViewById(R.id.label_textview_size);
        textview_position.setBackgroundColor(Color.parseColor("#76FF03"));
        textview_element.setBackgroundColor(Color.parseColor("#76FF03"));
        textview_label_size.setBackgroundColor(Color.parseColor("#76FF03"));
        linkList = new LinkList();
        loginButton = (LoginButton) findViewById(R.id.login_button);
        textview_username =(TextView) findViewById(R.id.user_name);

        List < String > permissionNeeds = Arrays.asList("user_photos", "email",
                "user_birthday", "public_profile", "AccessToken");
        loginButton.registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {

                    GraphRequest request = GraphRequest.newMeRequest(
                            loginResult.getAccessToken(),
                            new GraphRequest.GraphJSONObjectCallback() {

                                @Override
                                public void onCompleted(JSONObject object, GraphResponse response) {

                                Log.i("LoginActivity", response.toString());

                                try {
                                    id = object.getString("id");
                                    name = object.getString("name");
                                   /* String email = object.getString("email");
                                    String gender = object.getString("gender");
                                    String birthday = object.getString("birthday");*/
                                    textview_username.setText(name);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                            });
                    Bundle parameters = new Bundle();
                    parameters.putString("fields",
                            "id,name,email,gender, birthday");
                    request.setParameters(parameters);
                    request.executeAsync();
                }

                    @Override
                    public void onCancel() {
                        System.out.println("onCancel");
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        System.out.println("onError");
                        Log.v("LoginActivity", exception.getCause().toString());
                    }
                });



    }
    @Override
    protected void onActivityResult(int requestCode, int responseCode,
                                    Intent data) {
        super.onActivityResult(requestCode, responseCode, data);
        callbackManager.onActivityResult(requestCode, responseCode, data);
    }

    @Override
    public void onClick(View v) {
        int element=0,position=0;
        try {
             element = Integer.parseInt(edittext_element.getText().toString());
             position = Integer.parseInt(edittext_position.getText().toString());
        }catch (Exception ex){

        }
        switch (v.getId()){
            case R.id.textview_btn_addToFirst : insertAtFirst(element);
                                                break;
            case R.id.textview_btn_addToLast :  insertAtEnd(element);
                                               break;
            case R.id.textview_btn_addToPos :   insertAtPos(element,position);
                                                 break;
            case R.id.textview_btn_deleteAtPos : deleteAtPosition(position);
                                                 break;
            case R.id.textview_deleteFirst    :  deleteAtFirst();
                                                break;
            case R.id.textview_deleteLast  :    deleteAtEnd();
                                                break;
        }
        linkList.display();
        textview_linklistdata.setText(LinkList.resultentLinklist);
        textview_size.setText(linkList.size+"");
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Logs 'install' and 'app activate' App Events.
        AppEventsLogger.activateApp(this);
    }
    void insertAtFirst(int element){

        linkList.insertAtFirst(element);
    }

    void insertAtEnd(int element){
        linkList.insertAtEnd(element);
    }

    void insertAtPos(int element ,int position){
        if(position<0){
            Toast.makeText(MainActivity.this,"INVALID POSITION",Toast.LENGTH_SHORT).show();
            return;
        }
        if(linkList.posExcedsSize(position)){
            Toast.makeText(MainActivity.this,"Position Exceeds Size",Toast.LENGTH_SHORT).show();
            return;}
        linkList.insertAtPos(element, position);
    }
    void deleteAtPosition(int position){
        if(position<0){
            Toast.makeText(MainActivity.this,"INVALID POSITION",Toast.LENGTH_SHORT).show();
            return;
        }
        if(!linkList.isEmpty()){
            if(linkList.posExcedsSize(position)){
                Toast.makeText(MainActivity.this,"Position Exceeds Size",Toast.LENGTH_SHORT).show();
               return;}
            linkList.deleteAtPos(position);}
        else
            Toast.makeText(MainActivity.this,"LinkList is Empty",Toast.LENGTH_SHORT).show();


    }
    void deleteAtFirst(){
        if(!linkList.isEmpty())
            linkList.deleteAtFirst();
        else
            Toast.makeText(MainActivity.this,"LinkList is Empty",Toast.LENGTH_SHORT).show();
    }

    void deleteAtEnd(){
        if(!linkList.isEmpty())
            linkList.deleteAtEnd();
        else
            Toast.makeText(MainActivity.this,"LinkList is Empty",Toast.LENGTH_SHORT).show();
    }

}
