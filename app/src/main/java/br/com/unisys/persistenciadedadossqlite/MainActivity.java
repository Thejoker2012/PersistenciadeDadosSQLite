package br.com.unisys.persistenciadedadossqlite;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
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

        try{
            //Criando o banco de dados
            SQLiteDatabase bancoDados = openOrCreateDatabase("cadastro",MODE_PRIVATE,null);

            //Criando tabela e colunas
            bancoDados.execSQL("CREATE TABLE IF NOT EXISTS pessoas(id INTEGER PRIMARY KEY AUTOINCREMENT ,nome VARCHAR, idade INT(3))");
            //bancoDados.execSQL("DROP TABLE pessoas");

            //Inserindo dados
            //bancoDados.execSQL("INSERT INTO pessoas(nome,idade) VALUES('Joao',24)");
            //bancoDados.execSQL("INSERT INTO pessoas(nome,idade) VALUES('Julia',30)");

           //Update de valores da Tabela
            bancoDados.execSQL("UPDATE pessoas SET idade = 19, nome = 'Iago Santos' WHERE id = 3");

            //Deletar valores da Tabela
            bancoDados.execSQL("DELETE FROM pessoas ");

            //Recuperar pessoas
           /* String consulta = "SELECT nome,idade FROM pessoas" +
                                " WHERE nome ='Iago' AND idade = 26";*/

            /*String consulta = "SELECT nome,idade FROM pessoas " +
                              "WHERE idade >= 20 OR idade = 18";*/

            /*String consulta = "SELECT nome,idade FROM pessoas " +
                    "WHERE idade IN(18,23)";*/

            /*String consulta = "SELECT nome,idade FROM pessoas " +
                    "WHERE idade BETWEEN 18 AND 23";*/

           /* String filtro = "o";//Pegar resultados da tela
            String consulta = "SELECT nome FROM pessoas " +
                    "WHERE nome LIKE '%"+ filtro +"%'";*/

            String consulta = "SELECT * FROM pessoas " +
                    "WHERE 1=1 ";

            Cursor cursor = bancoDados.rawQuery(consulta,null);

            int indiceid = cursor.getColumnIndex("id");
            int indiceNome = cursor.getColumnIndex("nome");
            int indiceIdade = cursor.getColumnIndex("idade");

            cursor.moveToFirst();
            while (cursor != null){

                String id = cursor.getString(indiceid);
                String nome = cursor.getString(indiceNome);
                String idade = cursor.getString(indiceIdade);

                Log.i("RESULTADO -id ",id+" -nome: "+ nome +" -idade: "+idade);
                cursor.moveToNext();
            }


        }catch(Exception e){
            e.printStackTrace();
        }

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
