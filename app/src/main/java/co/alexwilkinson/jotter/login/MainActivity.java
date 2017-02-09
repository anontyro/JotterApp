package co.alexwilkinson.jotter.login;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import co.alexwilkinson.jotter.R;
import co.alexwilkinson.jotter.util.DBManager;

public class MainActivity extends AppCompatActivity {
    EditText etUsername;
    Button buGo;
    DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etUsername = (EditText) findViewById(R.id.etLoginUsername);


    }

    public void loginUser(View view) {
        dbManager = new DBManager(getApplicationContext());
    }
}
