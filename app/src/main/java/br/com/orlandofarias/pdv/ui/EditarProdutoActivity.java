package br.com.orlandofarias.pdv.ui;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import br.com.orlandofarias.pdv.domain.model.Produto;
import br.com.orlandofarias.pdv.R;
import butterknife.Bind;
import se.emilsjolander.sprinkles.CursorList;
import se.emilsjolander.sprinkles.Query;

public class EditarProdutoActivity extends BaseActivity {

    @Bind(R.id.spinner)
    Spinner spinner;

    Produto produto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_produto);
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

        List<Produto> produtos = Query.many(Produto.class,"select * from produto order by codigo_barra").get().asList();

        produto = new Produto();

        List<String> barcodeList = new ArrayList<>();
        List<Produto> produtoList;


        CursorList cursor = Query.many(Produto.class, "select * from produto where ativo = 0").get();
        produtoList = cursor.asList();

        for(Produto produto: produtoList){
            barcodeList.add(produto.getCodigoBarras());
        }

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_spinner_item,barcodeList);

        dataAdapter.setDropDownViewResource
                (android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(dataAdapter);


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String barCode =  parent.getItemAtPosition(position).toString();

                Log.d("BARCODE", "selecionado-->"+ barCode);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

}
