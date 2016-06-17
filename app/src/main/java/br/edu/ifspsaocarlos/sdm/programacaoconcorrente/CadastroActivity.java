package br.edu.ifspsaocarlos.sdm.programacaoconcorrente;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import br.edu.ifspsaocarlos.sdm.model.User;

import android.widget.Button;
import android.widget.EditText;


public class CadastroActivity extends Activity implements View.OnClickListener{

    private Button btnSave;
    private Handler handler;
    private EditText txtCompleteName, txtName, txtPassword, txtPassword2;

    public static CadastroActivity newInstance(Context context) {
        return new CadastroActivity();
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cadastro);
        handler = new Handler();
        this.setupFields();
    }

    private void setupFields(){

        txtCompleteName = (EditText) findViewById(R.id.txtCompleteName);
        txtName = (EditText) findViewById(R.id.txtName);
        txtPassword = (EditText) findViewById(R.id.txtPassword);
        txtPassword2 = (EditText) findViewById(R.id.txtPassword2);

        btnSave = (Button) findViewById(R.id.btnSave);
        btnSave.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        try {
            validateFields();
        }
        catch (RuntimeException e){
            dialog(e.getMessage(), "Error!", null);
            return;
        }

        btnSave.setText("One moment..");

        final long id = Math.round(Math.random() * 100);
        final User user = fillUser(id);

        this.disableForm();
        //this.cleanForm();

        new Thread(){
            public void run() {
                try {
                    sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                handler.post(new Runnable() {
                         public void run() {
                             btnSave.setText("Created!!");
                             dialog("New user created! id: " + id, "Congratulations!", null);
                         }
                 });
            }
        }.start();

    }

    private void validateFields(){
        if(txtCompleteName.getText().toString().isEmpty() ||
                txtName.getText().toString().isEmpty() ||
                txtPassword.getText().toString().isEmpty() ||
                txtPassword2.getText().toString().isEmpty()){
            throw new RuntimeException("All fields are required.");
        }

        if(!txtPassword.getText().toString().equals(txtPassword2.getText().toString()))
            throw new RuntimeException("Password invalid.");
    }

    private User fillUser(long id){
        return new User(id,
            txtCompleteName.getText().toString(),
            txtName.getText().toString(),
            txtPassword.getText().toString());
    }

    private void cleanForm(){
        txtName.setText("");
        txtCompleteName.setText("");
        txtPassword.setText("");
        txtPassword2.setText("");
    }

    private void disableForm(){
        txtName.setEnabled(false);
        txtCompleteName.setEnabled(false);
        txtPassword.setEnabled(false);
        txtPassword2.setEnabled(false);
        btnSave.setEnabled(false);
    }

    private void dialog(CharSequence message, CharSequence title, DialogInterface.OnClickListener click){
        AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(this);
        dlgAlert.setMessage(message);
        dlgAlert.setTitle(title);
        dlgAlert.setPositiveButton("OK", click);
        dlgAlert.setCancelable(true);
        dlgAlert.create().show();
    }
}
