package co.alexwilkinson.jotter.login;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import co.alexwilkinson.jotter.R;

public class MainActivity extends AppCompatActivity {
    EditText etUsername;
    Button buGo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etUsername = (EditText) findViewById(R.id.etLoginUsername);
        buGo = (Button) findViewById(R.id.buLoginGo);

    }
}
