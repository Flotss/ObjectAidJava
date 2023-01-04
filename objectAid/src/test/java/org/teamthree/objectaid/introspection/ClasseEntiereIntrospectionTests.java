package org.teamthree.objectaid.introspection;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.teamtree.objectaid.Classe.Attribut;
import org.teamtree.objectaid.Classe.ClasseEntiere;
import org.teamtree.objectaid.Classe.Methode;

import java.util.Collection;
import java.util.List;

class ClasseEntiereIntrospectionTests {

    private static final Collection<String> CLAZZ_ATTRIBUTES = List.of("x", "y", "staticAttribut");
    private static final Collection<String> CLAZZ_METHODS = List.of("methodeAbstraiteProtected",
        "MethodepublicAbstract", "methodePrivee", "methodeAvecParametrePublic",
        "methodeAvecParametresDefault", "methodeStatic");
    private ClasseEntiere clazz;

    @BeforeEach
    public void setUp() throws ClassNotFoundException {
        clazz = new ClasseEntiere("org.teamtree.objectaid.classes.ClassePourLesTests");
    }

    @Test
    @DisplayName("Should not be null")
    void shouldNotBeNull() {
        // La classe entière introspectée ne doit pas être nulle.
        assertThat(clazz).isNotNull();
    }

    @Test
    @DisplayName("Should have a name")
    void shouldHaveAName() {
        // La classe entière introspectée doit avoir le nom de la classe.
        assertThat(clazz.getClass().getSimpleName()).isEqualTo("ClasseEntiere");
    }

    @Test
    @DisplayName("Should have a correct list of attributes")
    void shouldHaveACorrectListOfAttributes() {
        // La classe entière introspectée doit avoir la liste des attributs de la classe.
        // il doit notamment avoir une taille de 3
        assertThat(clazz.getAttributs()).hasSize(3);

        // il doit avoir les attributs x, y et staticAttribut
        assertThat(clazz.getAttributs().stream().map(Attribut::getNom)).containsAll(
            CLAZZ_ATTRIBUTES);
    }

    @Test
    @DisplayName("Should have a correct list of methods")
    void shouldHaveACorrectListOfMethods() {
        // La classe entière introspectée doit avoir la liste des méthodes de la classe.
        // il doit notamment avoir une taille de 6
        assertThat(clazz.getMethods()).hasSize(6);

        // il doit avoir les méthodes methodeAbstraiteProtected, MethodepublicAbstract,
        assertThat(clazz.getMethods().stream().map(Methode::getNom)).containsAll(CLAZZ_METHODS);
    }
}
