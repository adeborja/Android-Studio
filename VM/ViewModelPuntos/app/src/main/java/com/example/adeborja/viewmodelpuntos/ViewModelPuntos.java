package com.example.adeborja.viewmodelpuntos;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

public class ViewModelPuntos extends ViewModel {

    private int puntosA=0, puntosB=0;
    private MutableLiveData<Integer> vecesPulsado;

    public MutableLiveData<Integer> getVecesPulsado()
    {
        if(vecesPulsado==null)
        {
            vecesPulsado = new MutableLiveData<Integer>();
            vecesPulsado.setValue(0);
        }
        return vecesPulsado;
    }

    public int getPuntosA() {
        return puntosA;
    }

    public void setPuntosA(int puntosA) {
        this.puntosA = puntosA;
    }

    public int getPuntosB() {
        return puntosB;
    }

    public void setPuntosB(int puntosB) {
        this.puntosB = puntosB;
    }
}
