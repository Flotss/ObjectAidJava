package org.tonel;

import java.io.Serializable;
import java.util.Comparator;

public class EtudiantComparatorMoyenne implements Comparator<Etudiant>, Serializable {
    @Override
    public int compare(final Etudiant e1, final Etudiant e2) {
        if (e1.calculerMoyGenerale() > e2.calculerMoyGenerale()) {
            return 1;
        } else if (e1.calculerMoyGenerale() < e2.calculerMoyGenerale()) {
            return -1;
        } else {
            return 0;
        }
    }
}
