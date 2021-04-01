package br.ucsal.pdm.ourfinances;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class LaunchAdapter extends BaseAdapter {

    private List<Launch> lista;
    private Context contexto;

    public LaunchAdapter (Context contexto, List<Launch> lancamentos) {
        this.contexto = contexto;
        this.lista = lancamentos;
    }

    @Override
    public int getCount() {
        return lista.size();
    }

    @Override
    public Launch getItem(int posicao) {
        return lista.get(posicao);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int posicao, View view, ViewGroup parent) {

        if (view == null) {
            view = LayoutInflater.from(contexto).inflate(R.layout.launch_list_item, parent, false);
        }

        TextView valor = view.findViewById(R.id.list_launch_value);
        TextView data = view.findViewById(R.id.list_launch_date);
        TextView descricao = view.findViewById(R.id.list_launch_description);
        TextView tipo = view.findViewById(R.id.list_launch_type);

        Launch lancamentos = getItem(posicao);

        valor.setText(lancamentos.getValor()+"");
        data.setText(lancamentos.getData());
        descricao.setText(lancamentos.getDescricao());
        tipo.setText(lancamentos.getTipo());

        return view;
    }
}
