package br.com.unisys.persistenciadedadossqlite;

import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    private CadastroPreferencias preferencias;
    private EditText editNome;
    private ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editNome = findViewById(R.id.editNome);
        listView = findViewById(R.id.listView);

        preferencias = new CadastroPreferencias(getApplicationContext());

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String textoNome = editNome.getText().toString();

                //Validar Campos
                if(textoNome.equals("")){
                    Snackbar.make(view, "Preencha o seu nome!", Snackbar.LENGTH_LONG).show();

                }else{
                    preferencias.salvarCadastro(textoNome);
                    Snackbar.make(view, "Cadastro salvo com sucesso!", Snackbar.LENGTH_LONG).show();
                }
            }
        });

        //Recuperar dados do cadastro
       String recuperaNome = preferencias.recuperarCadastro();
       if(!recuperaNome.equals("")){
           editNome.setText(recuperaNome);

       }
    }
}
