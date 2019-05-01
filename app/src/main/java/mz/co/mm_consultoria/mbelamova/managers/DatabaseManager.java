package mz.co.mm_consultoria.mbelamova.managers;

import android.content.Context;

import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import mz.co.mm_consultoria.mbelamova.R;
import mz.co.mm_consultoria.mbelamova.models.Passageiro;

public class DatabaseManager {
    private Context context;
    private FirebaseFirestore db;

    public DatabaseManager(Context context){
        this.context=context;
        this.db= FirebaseFirestore.getInstance();
    }

    //Set objects
    public void nova(Passageiro passageiro){
        db.collection(getPassageirosCollection()).document().set(passageiro);
    }

    //GetByQuery
    public Task<QuerySnapshot> getPassageiroByNumeroTelefone(int numeroTelefone){
        return db.collection(getPassageirosCollection()).whereEqualTo(getNumeroTelefoneField(), numeroTelefone).get();
    }

    //AUXILIO
    //ERRORS
    private String getNumeroTelefoneError(){
        return getString(R.string.numero_telefone_invalido);
    }

    //Fields
    private String getNumeroTelefoneField(){
        return getString(R.string.field_numero_telefone);
    }

    //Collections
    private String getCaroCollection(){
        return getString(R.string.caro_collection);
    }
    private String getMotoristaCollection(){
        return getString(R.string.motorista_collection);
    }
    private String getPassageirosCollection(){
        return getString(R.string.passageiro_collection);
    }
    private String getViagemCollection(){
        return getString(R.string.viagem_collection);
    }

    //Get string global
    private String getString(int Rstring){
        return context.getString(Rstring);
    }

}
