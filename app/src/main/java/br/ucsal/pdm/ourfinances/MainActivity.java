package br.ucsal.pdm.ourfinances;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    public static ArrayList<Launch> lancamentos = new ArrayList<Launch>();
    private ListView listaLancamentos;
    private LaunchAdapter adapter;
    private TextView balanceView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Visão Geral");

        lancamentos.addAll(
                Arrays.asList(
                        new Launch(500.00,"30/03/2021","Prestação de Serviço TI","Receita"),
                        new Launch(-200.00,"31/03/2021","Alimentação","Despesa")
                )
        );

        listaLancamentos = findViewById(R.id.main_list_view);
        balanceView = findViewById(R.id.main_balance);
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter = new LaunchAdapter(this, new ArrayList<>(lancamentos));
        listaLancamentos.setAdapter(adapter);
        balanceAvailable();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    public void formActivity(View view) {
        Intent form = new Intent(this, FormActivity.class);
        startActivity(form);
    }

    public void balanceAvailable() {
        int posicao = adapter.getCount();
        Log.d("Quantidade de posições", posicao+"");
        double balance = 0;
        Log.d("Saldo antes do laço", balance+"");

        for (int i = 0; i < posicao; i++) {
            balance += lancamentos.get(i).getValor();
        }

        Log.d("Saldo após o laço: ", balance+"");

        balanceView.setText(balance+"");
    }
}
