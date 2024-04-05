package br.rafaelhorochovec.app_cdz_android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import br.rafaelhorochovec.app_cdz_android.database.DBManager;

public class ModifyKnightActivity extends Activity implements OnClickListener {
    private EditText nomeText;
    private Button btnAlterar, btnDeletar;
    private EditText armaduraText;

    private long id;

    private DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTitle("Editar Cavaleiro");

        setContentView(R.layout.activity_modify_record);

        dbManager = new DBManager(this);
        dbManager.open();

        nomeText = findViewById(R.id.name_edittext);
        armaduraText = findViewById(R.id.armor_edittext);

        btnAlterar = findViewById(R.id.btn_update);
        btnDeletar = findViewById(R.id.btn_delete);

        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        String nome = intent.getStringExtra("nome");
        String armadura = intent.getStringExtra("armadura");

        this.id = Long.parseLong(id);

        nomeText.setText(nome);
        armaduraText.setText(armadura);

        btnAlterar.setOnClickListener(this);
        btnDeletar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_update:
                String nome = nomeText.getText().toString();
                String armadura = armaduraText.getText().toString();

                dbManager.update(id, nome, armadura);
                this.returnHome();
                break;

            case R.id.btn_delete:
                dbManager.delete(id);
                this.returnHome();
                break;
        }
    }

    public void returnHome() {
        Intent home_intent = new Intent(getApplicationContext(), MainActivity.class)
                .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(home_intent);
    }
}
