package br.com.agenda_de_contatos.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import java.util.ArrayList;

import br.com.agenda_de_contatos.entidades.Contatos;

public class DbContatos extends DbHelper {

    Context context;
    public DbContatos(@Nullable Context context) {
        super(context);

        this.context=context;
    }
    public long inserirContatos(String nome, String telefone, String correio_eletronico){
        long id = 0;

        try {
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("nome", nome);
            values.put("telefone", telefone);
            values.put("correio_eletronico", correio_eletronico);

            id = db.insert(TABLE_CONTATOS, null, values);
        } catch (Exception ex) {
            ex.toString();
        }

        return id;
    }

    public ArrayList<Contatos> mostrarContatos(){

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ArrayList<Contatos> listaContatos=new ArrayList<>();
        Contatos contato = null;
        Cursor cursorContatos=null;

        //fazer cosulta na tabela no banco
        cursorContatos = db.rawQuery("SELECT * FROM " + TABLE_CONTATOS + " ORDER BY nome ASC", null);
       //validar os dados consultados do banco
        if (cursorContatos.moveToFirst()) {
            do {
                contato = new Contatos();
                contato.setId(cursorContatos.getInt(0));
                contato.setNome(cursorContatos.getString(1));
                contato.setTelefone(cursorContatos.getString(2));
                contato.setCorreio_eletronico(cursorContatos.getString(3));
                listaContatos.add(contato);
            } while (cursorContatos.moveToNext());
        }

        cursorContatos.close();

        return listaContatos;

    }
    public Contatos verContato(int id) {

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        Contatos contato = null;
        Cursor cursorContatos;

        cursorContatos = db.rawQuery("SELECT * FROM " + TABLE_CONTATOS + " WHERE id = " + id + " LIMIT 1", null);

        if (cursorContatos.moveToFirst()) {
            contato = new Contatos();
            contato.setId(cursorContatos.getInt(0));
            contato.setNome(cursorContatos.getString(1));
            contato.setTelefone(cursorContatos.getString(2));
            contato.setCorreio_eletronico(cursorContatos.getString(3));
        }

        cursorContatos.close();

        return contato;
    }
    public boolean editarContato(int id, String nome, String telefone, String correio_eletronico) {

        boolean correto = false;

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            db.execSQL("UPDATE " + TABLE_CONTATOS + " SET nome = '" + nome + "', telefone = '" + telefone + "', correio_eletronico = '" + correio_eletronico + "' WHERE id='" + id + "' ");
            correto = true;
        } catch (Exception ex) {
            ex.toString();
            correto = false;
        } finally {
            db.close();
        }

        return correto;
    }
    public boolean eliminarContato(int id) {

        boolean correto = false;

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            db.execSQL("DELETE FROM " + TABLE_CONTATOS + " WHERE id = '" + id + "'");
            correto = true;
        } catch (Exception ex) {
            ex.toString();
            correto = false;
        } finally {
            db.close();
        }

        return correto;
    }
}








