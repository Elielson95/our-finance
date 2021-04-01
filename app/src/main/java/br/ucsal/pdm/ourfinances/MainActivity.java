package br.ucsal.pdm.ourfinances;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    public static ArrayList<Launch> lancamentos = new ArrayList<Launch>();
    public static ArrayList<Launch> lancamentosReceitas = new ArrayList<Launch>();
    public static ArrayList<Launch> lancamentosDespesas = new ArrayList<Launch>();

    private ListView listaLancamentos;

    private LaunchAdapter adapter;

    private TextView balanceView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Visão Geral");

//        lancamentos.addAll(
//                Arrays.asList(
//                        new Launch(1, 500.00,"30/03/2021","Prestacao de Servico TI","Receita"),
//                        new Launch(2, -200.00,"31/03/2021","Alimentacao","Despesa"),
//                        new Launch(3, 400.00,"31/03/2021","Prestacao de Servico Contábeis","Receita"),
//                        new Launch(4, -300.00,"01/04/2021","Material de Escritorio","Despesa")
//                )
//        );

        listaLancamentos = findViewById(R.id.main_list_view);
        //onlyReceitas = findViewById(R.id.checkbox_recipes);
        //onlyDespesas = findViewById(R.id.checkbox_recipes);
        balanceView = findViewById(R.id.main_balance);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(lancamentosReceitas.size() > 0 && lancamentosDespesas.size() == 0){
            adapter = new LaunchAdapter(this, new ArrayList<>(lancamentosReceitas));
        } else if(lancamentosDespesas.size() > 0 && lancamentosReceitas.size() == 0){
            adapter = new LaunchAdapter(this, new ArrayList<>(lancamentosDespesas));
        } else {
            adapter = new LaunchAdapter(this, new ArrayList<>(lancamentos));
        }
        listaLancamentos.setAdapter(adapter);
        balanceAvailable();
    }

    public void formActivity(View view) {
        Intent form = new Intent(this, FormActivity.class);
        startActivity(form);
    }

    public void balanceAvailable() {
        int posicao = adapter.getCount();
        //Log.d("Quantidade bA", posicao+"");
        double balance = 0;
        //Log.d("Saldo antes do laço bA", balance+"");

        for (int i = 0; i < posicao; i++) {
            balance += lancamentos.get(i).getValor();
        }
        //Log.d("Saldo após o laço bA", balance+"");
        balanceView.setText(balance+"");
    }

    public void onCheckboxClicked(View view) {
        int posicao = adapter.getCount();
        boolean checked = ((CheckBox) view).isChecked();
        switch(view.getId()) {
            case R.id.checkbox_expenses:
                if (!checked) {
                    for (int i = 0; i < posicao; i++) {
                        if(lancamentos.get(i).getTipo().equals("Receita")) {
                            lancamentosReceitas.add(lancamentos.get(i));
                        }
                    }
                } else {
                    lancamentosReceitas.clear();
                }
                break;
            case R.id.checkbox_recipes:
                if (!checked) {
                    for (int y = 0; y < posicao; y++) {
                        if(lancamentos.get(y).getTipo().equals("Despesa")) {
                            lancamentosDespesas.add(lancamentos.get(y));
                        }
                    }
                } else {
                    lancamentosDespesas.clear();
                }
                break;
        }
        onResume();
    }
}
