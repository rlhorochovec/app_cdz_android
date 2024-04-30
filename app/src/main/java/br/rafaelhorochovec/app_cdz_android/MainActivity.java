package br.rafaelhorochovec.app_cdz_android;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import br.rafaelhorochovec.app_cdz_android.database.DBManager;
import br.rafaelhorochovec.app_cdz_android.database.DataBaseHelper;

public class MainActivity extends AppCompatActivity {

    private DBManager dbManager;
    private ListView listView;
    private SimpleCursorAdapter adapter;

    final String[] from = new String[] {
        DataBaseHelper._ID,
        DataBaseHelper.NOME,
        DataBaseHelper.ARMADURA
    };

    final int[] to = new int[] { R.id.id, R.id.name, R.id.armor };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.include_empty_list);

        dbManager = new DBManager(this);
        dbManager.open();
        Cursor cursor = dbManager.fetch();

        listView = findViewById(R.id.list_view);
        listView.setEmptyView(findViewById(R.id.empty));

        adapter = new SimpleCursorAdapter(this, R.layout.activity_view_record, cursor, from, to, 0);
        adapter.notifyDataSetChanged();

        listView.setAdapter(adapter);

        // OnCLickListener For List Items
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long viewId) {
                TextView idTextView = view.findViewById(R.id.id);
                TextView nomeTextView = view.findViewById(R.id.name);
                TextView armaduraTextView = view.findViewById(R.id.armor);

                String id = idTextView.getText().toString();
                String nome = nomeTextView.getText().toString();
                String armadura = armaduraTextView.getText().toString();

                Intent modify_intent = new Intent(getApplicationContext(), ModifyKnightActivity.class);
                modify_intent.putExtra("nome", nome);
                modify_intent.putExtra("armadura", armadura);
                modify_intent.putExtra("id", id);

                startActivity(modify_intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.add_new) {
            Intent add_mem = new Intent(this, AddKnightActivity.class);
            startActivity(add_mem);
        }
        return super.onOptionsItemSelected(item);
    }
}