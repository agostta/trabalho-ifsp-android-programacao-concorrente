package br.edu.ifspsaocarlos.sdm.programacaoconcorrente;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class PrincipalActivity extends ListActivity {

    private final String SIGN_IN = "Sign in user";

    private final String[] activities = new String[]{
            "Mensagem -> Handler",
            "Runnable -> Handler",
            "runOnUIThread",
            SIGN_IN,
            "Sair"};

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ArrayAdapter<String> adaptador =
                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, activities);
        this.setListAdapter(adaptador);
    }
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        Class clazz = null;

        switch (getListAdapter().getItem(position).toString()) {
            case "Mensagem -> Handler": clazz = MensagemHandlerActivity.class; break;
            case "Runnable -> Handler": clazz = RunnableHandlerActivity.class; break;
            case "runOnUIThread": clazz = RunOnUIThreadActivity.class; break;
            case SIGN_IN: clazz = CadastroActivity.class; break;
            case "Sair":
            default:
                finish();
                return;
        }

        startActivity(new Intent(this, clazz));
    }

}
