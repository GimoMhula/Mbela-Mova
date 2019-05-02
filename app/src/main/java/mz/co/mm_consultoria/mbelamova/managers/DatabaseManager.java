package mz.co.mm_consultoria.mbelamova.managers;

import android.content.Context;
import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.SetOptions;

import mz.co.mm_consultoria.mbelamova.R;
import mz.co.mm_consultoria.mbelamova.models.Caro;
import mz.co.mm_consultoria.mbelamova.models.ContaPassageiro;
import mz.co.mm_consultoria.mbelamova.models.Motorista;
import mz.co.mm_consultoria.mbelamova.models.Passageiro;

public class DatabaseManager {
    private final SharedPreferencesManager sharedPreferencesManager;
    private Context context;
    private FirebaseFirestore db;

    public DatabaseManager(Context context){
        this.context=context;
        this.db= FirebaseFirestore.getInstance();
        sharedPreferencesManager = new SharedPreferencesManager(context);
    }

    //Set objects
    public Task<DocumentReference> addNewPassageiro(Passageiro passageiro){
        return db.collection(getPassageirosCollection()).add(passageiro);
    }
    public Task<DocumentReference> addNewCaro(Caro caro){
        return db.collection(getCaroCollection()).add(caro);
    }
    public Task<DocumentReference> addNewMotorista(Motorista motorista){
        return db.collection(getMotoristaCollection()).add(motorista);
    }
    //GetByQuery
    public Task<QuerySnapshot> getPassageiroByConta(ContaPassageiro contaPassageiro){
        return db.collection(getPassageirosCollection()).whereEqualTo(getConta(), contaPassageiro).get();
    }

    public DocumentReference getPassageiroOnlineDocumentReference(){
        return db.collection(getPassageirosCollection()).document(sharedPreferencesManager.getPassageiroDocumentIdSharedPrefs());
    }

    public void changeMotoristaEstado(boolean estado){
        DocumentReference motoristaRef = db.collection(getMotoristaCollection()).document(sharedPreferencesManager.getMotoristaDocumentIdSharedPrefs());
        motoristaRef.update(context.getString(R.string.field_estado), estado)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        //sucesso
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        //falhou
                    }
                });
    }

    //AUXILIO
    //ERRORS
    private String getNumeroTelefoneError(){
        return getString(R.string.numero_telefone_invalido);
    }

    //Fields
    //Field Object
    private String getConta(){
        return getString(R.string.field_conta);
    }
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
