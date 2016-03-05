package br.com.orlandofarias.pdv.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.List;

import br.com.orlandofarias.pdv.R;
import br.com.orlandofarias.pdv.domain.model.Produto;
import se.emilsjolander.sprinkles.Query;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        List<Produto> produtos = Query.all(Produto.class).get().asList();
        if(produtos != null) {
            for (Produto p : produtos) {
                Log.d("Produto: ", p.getDescricao());
                Log.d("Unidade: ", p.getUnidade());
                Log.d("Codigo de Barras: ", p.getCodigoBarras());
                Log.d("Preco: ", p.getPreco().toString());
            }
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        List<Produto> produtos = Query.all(Produto.class).get().asList();
        if(produtos != null) {
            for (Produto p : produtos) {
                Log.d("Produto: ", p.getDescricao());
                Log.d("Unidade: ", p.getUnidade());
                Log.d("Codigo de Barras: ", p.getCodigoBarras());
                Log.d("Preco: ", p.getPreco().toString());
            }
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_novo) {
            Intent intent = new Intent(MainActivity.this, CadastroNovoActivity.class);
            startActivity(intent);
        }
        else if(id == R.id.action_edit) {
            Intent intentEditar = new Intent(MainActivity.this, EditarProdutoActivity.class);
            startActivity(intentEditar);
        }


        return super.onOptionsItemSelected(item);
    }
}
