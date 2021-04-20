package cat.itb.testing;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class WelcomeActivity extends AppCompatActivity {

    Button button;
    TextView welcome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        welcome = findViewById(R.id.welcomeText);

        if(savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            welcome.setText("Welcome back " + extras.getString("name"));
        }

        button = findViewById(R.id.buttonBack);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(WelcomeActivity.this, MainActivity.class);
                startActivity(i);

            }
        });

    }
}