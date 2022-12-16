package org.teamtree.objectaid;

import org.teamtree.objectaid.Classe.ClasseEntiere;

import java.lang.reflect.*;

public class FabriqueClasse {
    public FabriqueClasse(){

    }

    public static ClasseEntiere fabriquer(String path) throws ClassNotFoundException {
        ClasseEntiere classeEntiere = new ClasseEntiere();


        Class<?> c = Class.forName(path);
        String accessibilite = Modifier.toString(c.getModifiers()).split(" ")[0];
        String typeClasse = c.toString().split(" ")[0];
        String nomClasse = c.toString().split(" ")[1].split("\\.")[c.toString().split(" ")[1].split("\\.").length - 1];
        Field[] fields = c.getFields();
        Method[] methods = c.getDeclaredMethods();
        Constructor[] constructors = c.getConstructors();
        Class<?> parent = c.getSuperclass();

        ;

        String affichage = "";

        //DefinitionClasse definitionClasse = new DefinitionClasse(nom,,,);
        affichage +=  accessibilite + " " + typeClasse + " " + nomClasse + "\n";
        for(Constructor constructor : constructors){

            //affichage de la signature du constructeur

            affichage += Modifier.toString(constructor.getModifiers());
            String nomConstructeur = constructor.getName().split("\\.")[(constructor.getName().split("\\.").length) - 1];
            affichage += " " + nomConstructeur + "(";

            //affichage des parametres

            Parameter[] parameters = constructor.getParameters();
            for (int i = 0; i < parameters.length; i++) {
                Parameter parameter = parameters[i];
                if (i != 0) {
                    affichage += " ";
                }
                affichage += parameter.getName() + ": "+ parameter.getType().getName().split("\\.")[(parameter.getType().getName().split("\\.").length) - 1];
                if(i < parameters.length - 1 && parameters.length > 1){
                    affichage += ", ";
                }
            }
            affichage += ")\n";

        }

        for(Method method : methods){

            //affichage de la signature de la methode

            affichage += Modifier.toString(method.getModifiers()).split(" ")[0];
            affichage += " "+method.getName()+ "(";

            //affichage des parametres

            Parameter[] parameters = method.getParameters();
            for (int i = 0; i < parameters.length; i++) {
                Parameter parameter = parameters[i];
                if (i != 0) {
                    affichage += " ";
                }
                affichage += parameter.getName() + ": "+ parameter.getType().getName().split("\\.")[(parameter.getType().getName().split("\\.").length) - 1];
                if(i < parameters.length - 1 && parameters.length > 1){
                    affichage += ",";
                }
            }
            affichage += ")";

            //affichage du type de retour

            affichage+= ": " + method.getReturnType().getName().split("\\.")[(method.getReturnType().getName().split("\\.").length) - 1] +"\n";

        }
        System.out.println(affichage);


        return classeEntiere;
    }
}
