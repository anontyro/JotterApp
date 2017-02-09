package co.alexwilkinson.jotter.login;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import co.alexwilkinson.jotter.R;
import co.alexwilkinson.jotter.main.MainJotterActivity;
import co.alexwilkinson.jotter.util.DBManager;
import co.alexwilkinson.jotter.util.SharedRef;

public class MainActivity extends AppCompatActivity {

    private AutoCompleteTextView loginName;
    private DBManager dbManager;
    private static final String TAG ="LoginDebug";
    private SharedRef sharedRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbManager = new DBManager(getApplicationContext());
        sharedRef = new SharedRef(getApplicationContext());


        Log.v(TAG,"Does database exist? " +dbManager.databaseExists(getApplicationContext()));
        Log.v(TAG,sharedRef.toString());

        String[]userSuggestions = sharedRef.toString().split(",");
        ArrayAdapter<String> userAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line,userSuggestions);
        loginName = (AutoCompleteTextView) findViewById(R.id.tvAutoLoginName);
        loginName.setAdapter(userAdapter);




    }

    public void loginUser(View view) {
        String userToAdd = loginName.getText().toString();

        if(sharedRef.loadUser().equalsIgnoreCase("none")){
            addUser(userToAdd);

        }else{

            if(dbManager.doesUserExist(userToAdd)){
                loadNextPage(getApplicationContext());

            }else{
                addUser(userToAdd);
            }

        }

    }

    private void loadNextPage(Context context){
        Intent intent = new Intent(context, MainJotterActivity.class);
        startActivity(intent);
    }

    private long addUser(String userToAdd){
        ContentValues values = new ContentValues();
        values.put(DBManager.userColUsername, userToAdd);
        values.put(DBManager.userColPages, 0);
        long added = dbManager.insertNewUser(values);
        if(added > 0){
            sharedRef.saveData(userToAdd);
            Toast.makeText(getApplicationContext(),
                    "Successfully added" + userToAdd,
                    Toast.LENGTH_LONG).show();
            loadNextPage(getApplicationContext());
        }else {
            Toast.makeText(getApplicationContext(),
                    "Error adding "+userToAdd + " Please try again",
                    Toast.LENGTH_LONG).show();
            loginName.setText("");
        }

        return added;
    }


















}
