package service;

import java.util.ArrayList;
import java.util.List;
import corbaBanque.Compte;
import corbaBanque.IBanqueRemotePOA;

public class BanqueImpl extends IBanqueRemotePOA {

    private static final double TAUX_EURO_DT = 3.43;
    private List<Compte> comptes = new ArrayList<Compte>();

    private Compte chercher(int code) {
        for (Compte c : comptes) {
            if (c.code == code) return c;
        }
        return null;
    }

    public void creerCompte(Compte cpte) {
        if (chercher(cpte.code) == null) {
            comptes.add(new Compte(cpte.code, cpte.solde));
        }
    }

    public void verser(float mt, int code) {
        Compte c = chercher(code);
        if (c != null) c.solde += mt;
    }

    public void retirer(float mt, int code) {
        Compte c = chercher(code);
        if (c != null && c.solde >= mt) c.solde -= mt;
    }

    public Compte getCompte(int code) {
        Compte c = chercher(code);
        if (c == null) return new Compte(-1, 0);
        return new Compte(c.code, c.solde);
    }

    public Compte[] getComptes() {
        return comptes.toArray(new Compte[0]);
    }

    public double conversion(float mt) {
        return mt * TAUX_EURO_DT;
    }
}