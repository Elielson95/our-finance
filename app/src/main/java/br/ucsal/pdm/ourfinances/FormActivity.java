package br.ucsal.pdm.ourfinances;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import java.text.SimpleDateFormat;

public class FormActivity extends AppCompatActivity {

    private TextInputEditText valor;
    private TextInputEditText data;
    private TextInputEditText descricao;
    private RadioButton tipo;

    private Launch lancamento;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        valor = findViewById(R.id.input_launch_value);
        data = findViewById(R.id.input_launch_date);
        descricao = findViewById(R.id.input_launch_description);

        Intent intent = getIntent();
        if (intent.hasExtra("LANÇAMENTO")) {
            setTitle("Editar Lançamento");
            lancamento = (Launch) intent.getSerializableExtra("LANÇAMENTO");
            valor.setText(lancamento.getValor()+"");
            data.setText(lancamento.getData());
            descricao.setText(lancamento.getDescricao());
            tipo.setText(lancamento.getTipo());
        } else {
            setTitle("Novo Lançamento");
            lancamento = new Launch();
        }
    }

    public void mainActivity (View view) {
        finish();
    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.launch_rec:
                if (checked)
                    tipo = findViewById(R.id.launch_rec);
                    valor = findViewById(R.id.input_launch_value);
                    break;
            case R.id.launch_exp:
                if (checked)
                    tipo = findViewById(R.id.launch_exp);
                    break;
        }
    }

    public void adicionar (View view) {
        if (!MainActivity.lancamentos.contains(lancamento)) {
            MainActivity.lancamentos.add(lancamento);
        } else {
            int index = MainActivity.lancamentos.indexOf(lancamento);
            lancamento = MainActivity.lancamentos.get(index);
        }
        if(tipo.getText().toString().equals("Receita")) {
            Double receita = Double.parseDouble(valor.getText().toString());
            if (receita < 0) {
                lancamento.setValor(receita = receita * -1);
            } else {
                lancamento.setValor(receita);
            }
        } else if (tipo.getText().toString().equals("Despesa")) {
            Double despesa = Double.parseDouble(valor.getText().toString());
            if (despesa > 0) {
                lancamento.setValor(despesa = despesa * -1);
            } else {
                lancamento.setValor(despesa);
            }
        }
        lancamento.setData(data.getText().toString());
        lancamento.setDescricao(descricao.getText().toString());
        lancamento.setTipo(tipo.getText().toString());
        finish();
    }
}