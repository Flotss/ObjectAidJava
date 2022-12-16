## Fonctionnalité 1 : Générer structures de données contenant des classes
##### Difficulté: 1-2

- L'application devra pouvoir, en donnant le chemin d'une d'un fichier java, généré la structure de donnée de la classe.
- On ne gèrera pas les associations.

#### Les étudiants qui se sont occupés de cette partie sont :
- Mangin Florian
- Bernard Julien
- Grossmann Jérémy


### Critères de validation :
- Possibilité d'afficher des classes à l'aide de toString.
- L'affichage des classes peut contenir pour chaque classe : une définition, des constructeurs, des méthodes, des attributs.
- Avoir une structure de données complets pour toutes les classes.


#### Détail de la fonctionnalité :
Nous avons choisi de générer la structure de donnée à l'aide du diagramme de classe réalisé pour l'étude préalable en changeant quelques détails.
La plupart des classes ont été créées et utilisées pour générer la structure de données

#### Patron de conception utilisé :
- Strategy : Pour différencier l'accessibilité (public, private, protected), l'entite (classe, interface, enum, annotation) et l'état (static, final, abstract)
- Factory : Pour créer les Accessibilités, les Entités et les Etats. Par le modifiers.


---
## Fonctionnalité 2 : Afficher les classes
##### Difficulté: 1

En tant qu’étudiants, nous avons développé l'affichage du nom des classes ainsi que l’icône correspondant aux classes dans une interface graphique javaFX nommé Pane à partir de l'introspection faite de chaque classe.

#### Les étudiants qui se sont occupés de cette partie sont :
- Gridel Alexis
- Grossmann Jérémy


### Critère de validation :
- L'application javaFX affichera un Pane contenant les classes (icône +nom).
- Les classes sont des VBox
- Les classes ne doivent être superposées
- L'icône doit changer en fonction de l'entité de la classe (class, interface...)

### Détail de la fonctionnalité :
Nous avons ajoutés des nouvelles classes pour pourvoir générer des icônes différents pour les classes, interfaces, les records et les classes abstraites.

Il y a seulement la VueClasse qui a été développée ainsi que l'application javaFX et le modèle.
La vue est un Pane contenant les classes sous forme de VBox.

#### Patron d'architecture utilisé :
 - MVC : Pour séparer le modèle, la vue et le contrôleur.

#### Patron de conception utilisé :
- Observer/Observé : Pour que la vue puisse observer le modèle et se mettre à jour en fonction des changements.
