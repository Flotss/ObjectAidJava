package org.tonel;

import org.tonel.exception.GradeOutOfRangeException;

public enum Main {
    ;

    public static void main(final String[] args) throws GradeOutOfRangeException {
        final var identite = new Identite("dfdsf", "ddfg", "gfg");
        final var formation = new Formation("dfdsf");
        final var matiere = new Matiere("math");
        formation.ajouterMatiere(matiere, 2.0f);

        final var etud = new Etudiant(identite, formation);
        etud.ajouterNote(matiere, 2.0);

    }
}