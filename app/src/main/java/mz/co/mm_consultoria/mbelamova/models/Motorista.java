package mz.co.mm_consultoria.mbelamova.models;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.GeoPoint;

import java.util.ArrayList;

public class Motorista{
    private DocumentReference motorista_id;
    private ArrayList<DocumentReference> caros;
    private boolean estado;
    private GeoPoint localizacao_atual;
    private boolean permitido;

    public Motorista() {
    }

    public Motorista(DocumentReference motorista_id, ArrayList<DocumentReference> caros, boolean estado, GeoPoint localizacao_atual, boolean permitido) {
        this.motorista_id = motorista_id;
        this.caros = caros;
        this.estado = estado;
        this.localizacao_atual = localizacao_atual;
        this.permitido = permitido;
    }

    public DocumentReference getMotorista_id() {
        return motorista_id;
    }

    public void setMotorista_id(DocumentReference motorista_id) {
        this.motorista_id = motorista_id;
    }

    public ArrayList<DocumentReference> getCaros() {
        return caros;
    }

    public void setCaros(ArrayList<DocumentReference> caros) {
        this.caros = caros;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public GeoPoint getLocalizacao_atual() {
        return localizacao_atual;
    }

    public void setLocalizacao_atual(GeoPoint localizacao_atual) {
        this.localizacao_atual = localizacao_atual;
    }

    public boolean isPermitido() {
        return permitido;
    }

    public void setPermitido(boolean permitido) {
        this.permitido = permitido;
    }
}
