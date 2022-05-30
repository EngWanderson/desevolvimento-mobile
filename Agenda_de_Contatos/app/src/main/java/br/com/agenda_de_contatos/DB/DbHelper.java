package br.com.agenda_de_contatos.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VESRION = 2;
    private static final String DATABASE_NOME = "agenda.db";
    public static final String TABLE_CONTATOS = "t_contactos";

    public DbHelper(@Nullable Context context) {
        super(context, DATABASE_NOME, null, DATABASE_VESRION);
    }

    @Override
    //criar base de dados
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_CONTATOS + "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nome TEXT NOT NULL," +
                "telefone TEXT NOT NULL," +
                "correio_eletronico TEXT)");
    }

    @Override
    //elminar table
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {


        sqLiteDatabase.execSQL("DROP TABLE " + TABLE_CONTATOS);
        onCreate(sqLiteDatabase);
    }
}
