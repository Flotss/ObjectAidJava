package org.teamtree.objectaid;

import java.lang.reflect.*;

public class FabriqueClasse {
    public FabriqueClasse(){

    }

    public static ClasseEntiere fabriquer(String path) throws ClassNotFoundException {
        ClasseEntiere classeEntiere = new ClasseEntiere("");


        Class<?> c = Class.forName(path);
        String temporaire[] = path.split(".");
        String nom = temporaire[temporaire.length-1];
        Field[] fields = c.getFields();
        Method[] methods = c.getDeclaredMethods();
        Constructor[] constructors = c.getConstructors();
        Class<?> parent = c.getSuperclass();

        String affichage = "";

        DefinitionClasse definitionClasse = new DefinitionClasse(nom,,,);

        affichage += Modifier.toString(c.getModifiers());

        affichage +=" "+ nomClasse + "\n";
        for(Constructor constructor : constructors){
            affichage += Modifier.toString(constructor.getModifiers());
            affichage += " "+constructor.getName()+ "(";
            Parameter[] parameters = constructor.getParameters();
            for (Parameter parameter : parameters){
                affichage += parameter.getName() + ": "+ parameter.getType() +" ";
            }
            affichage += ")\n";

        }

        for(Method method : methods){
            affichage += Modifier.toString(method.getModifiers());
            affichage += " "+method.getName()+ "(";
            Parameter[] parameters = method.getParameters();
            for (Parameter parameter : parameters){
                affichage += parameter.getName() + ": "+ parameter.getType().getName() +" ";
            }
            affichage += ")";
            Class<?> returnType = method.getReturnType();
            affichage+= ": "+ returnType.getName() +"\n";

        }
        System.out.println(affichage);


        return classeEntiere;
    }
}
