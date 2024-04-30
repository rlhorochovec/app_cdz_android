package br.rafaelhorochovec.app_cdz_android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import br.rafaelhorochovec.app_cdz_android.database.DBManager;

public class AddKnightActivity extends Activity implements OnClickListener {
    private Button btnSalvar;
    private EditText nomeEditText;
    private EditText armaduraEditText;

    private DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTitle("Novo Cavaleiro");

        setContentView(R.layout.activity_add_record);

        nomeEditText = findViewById(R.id.name_edittext);
        armaduraEditText = findViewById(R.id.armor_edittext);

        btnSalvar = findViewById(R.id.add_new);

        dbManager = new DBManager(this);
        dbManager.open();
        btnSalvar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_new:
                final String nome = nomeEditText.getText().toString();
                final String armadura = armaduraEditText.getText().toString();

                dbManager.insert(nome, armadura);

                Intent main = new Intent(AddKnightActivity.this, MainActivity.class)
                        .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                startActivity(main);
                break;
        }
    }
}
