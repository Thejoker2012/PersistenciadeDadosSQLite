package br.com.unisys.persistenciadedadossqlite;

import android.content.Context;
import android.content.SharedPreferences;

public class CadastroPreferencias {

    private Context context;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    private final String NOME_ARQUIVO = "cadastro.preferencias";
    private final String CHAVE_NOME = "nome";


    public CadastroPreferencias(Context c){
        this.context = c;
        preferences = context.getSharedPreferences(NOME_ARQUIVO,0);
        editor = preferences.edit();
    }

    public void salvarCadastro(String nome){
        editor.putString(CHAVE_NOME, nome);
        editor.commit();



    }

    public String recuperarCadastro(){
        return preferences.getString(CHAVE_NOME,"");
    }

}
