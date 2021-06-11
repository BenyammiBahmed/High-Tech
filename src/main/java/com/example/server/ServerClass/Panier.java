package com.example.server.ServerClass;

import java.util.ArrayList;

public class Panier {
    ArrayList<PanierIthem> panierIthems;
    public Panier(){
        panierIthems=new ArrayList<>();
    }

    public ArrayList<PanierIthem> getPanierIthems() {
        return panierIthems;
    }
}
