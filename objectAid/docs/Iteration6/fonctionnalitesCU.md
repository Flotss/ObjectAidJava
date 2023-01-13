## Fonctionnalité 1 : Modifier et ajouter des classes

##### Difficulté : 2

En tant qu’étudiant, je souhaiterais pouvoir modifier les différentes classes de mon
diagramme pour pouvoir permettre une personnalisation adéquate du projet. Pour ce
faire, il faut pouvoir ajouter des méthodes, des attributs et des constructeurs.
Nous devrons aussi pouvoir ajouter des classes

#### Les étudiants qui se sont occupés de cette partie sont :

- Bernard Julien

### Critères de validation :

- Après un clic droit sur une classe et en selectionnant "ajouter", il y a la possibilité d'ajouter des attributs/ des
  méthodes/ des constructeurs.
- Depuis la liste des classes, il y a la possibilité d'en ajouté une nouvelle

### Etat :

Fonctionnalité achevée

#### Détail de la fonctionnalité :

Cette fonctionnalité permet d'ajouter des attributs, des méthodes et des constructeurs dans une classe, ainsi qu'une
classe complète.
Pour ce faire, lors d'un clic droit sur une classe puis sur le bouton "ajouter", un menue
apparait pour ajouter des attributs ou méthodes ou constructeurs. Lorsqu'on en sélectionne un,
une fenètre apparait pour nous permettre d'inséré les données. Une fois les données inséré, elles sont transmise au
modèle
qui va ajouté la partie correspondante dans la bonne classe. Puis la classe et les fleches allant avec se mettent à
jour.

Pour ajouter une classse, il faut cliquer sur le menu pour accédé à la liste des classes, puis sur le bouton "ajouter
une classe".
Une fenètre apparaitra alors, pour nous permettre d'ajouté une classe vierge avec ses héritages et implémentations.

---

## Fonctionnalité 2 : Exporter image

##### Difficulté : 1/2

En tant qu’étudiant, je souhaiterais pouvoir exporter une image de mon diagramme, pour pouvoir la montrer à mon équipe.

#### Les étudiants qui se sont occupés de cette partie sont :

- Gridel Alexis

### Critères de validation :

- Après un clic sur le bouton correspondant, nous pouvons exporter le graphique en PNG.
- Pouvoir choisir l’endroit où on souhaite l’enregistrer

### Etat :

Fonctionnalité achevée

#### Détail de la fonctionnalité :

Cette fonctionnalité permet d'exporter une image du diagramme en PNG. Pour ce faire, il faut cliquer sur le bouton "
exporter" puis choisir l'emplacement où l'on souhaite enregistrer l'image. Une fois l'emplacement choisi, l'image est
enregistrée. Pour ce faire j'ai dû utilisé une classe utilitaire du module Swing, SwingFxUtils, qui contient une fabrique pour générer une image.

---

## Fonctionnalité 3 : Exporter sourceUML

##### Difficulté : 1

En tant qu’étudiant, je souhaiterais pouvoir exporter le diagramme en sourceUML pour pouvoir la montrer aux
collaborateurs de mon projet.

#### Les étudiants qui se sont occupés de cette partie sont :

- Mangin Florian

### Critères de validation :

- Après un clic sur le bouton correspondant, nous pouvons exporter le graphique en sourceUML.
- Avoir une structure de donnée qui permet de générer le code sourceUML
- Pouvoir choisir l’endroit où on souhaite l’enregistrer
- Pouvoir choisir le nom du fichier

### Etat :

Fonctionnalité achevée

#### Détail de la fonctionnalité :
La structure de donnée doit alors s'exporter en puml c'est à dire que la structure de donnée à du être modifié et ainsi être exportable en puml.
Pour cela la ClasseEntiere demande à tout ses composants de s'exporter en uml et ainsi de suite jusqu'à ce que tout soit exporté.
La classeEntiere est donc la classe qui va exporter tout le reste.

---

## Fonctionnalité 4 : Ajout de la relation de composition
##### Difficulté : 2

En tant qu'étudiant, nous voulons pouvoir voir les relations de composition, puisqu'il n'y en a pas actuellement.

#### Les étudiants qui se sont occupés de cette partie sont :

- Mangin Florian

### Critères de validation :
- La relation de composition est visible sur le diagramme
- La relation de composition est caractérisée par un losange au départ de la flèche
- Avoir les bonnes cardinalités

### Etat :

Fonctionnalité achevée

#### Détail de la fonctionnalité :

//TODO

---
## Fonctionnalité 5 : Importation - refactor du code et merge

##### Difficulté : 4

En tant qu’étudiant, je souhaiterais pouvoir ajouter la fonctionnalité d'importation par un utilisateur :
L'utilisateur pourra importer ses classes en fournissant le chemin de son fichier ou de son dossier.

#### Les étudiants qui se sont occupés de cette partie sont :

- Gridel Alexis

### Critères de validation :

- Bouton qui ouvre l’explorateur de fichier pour sélectionner un dossier
- L’affiche dans une arborescence (à gauche de l’application)
- Pouvoir glisser et déposer les classes de l’arborescence à l'écran principal pour pouvoir les afficher en tant que diagramme

### Etat :

Fonctionnalité achevée

#### Détail de la fonctionnalité :
La relation n'est juste qu'un ajout de la relation d'association, il suffit donc de rajouter un losange au départ de la flèche.
Il faut donc mettre à jour la création de l'affichage pour cela, il nous faut juste crée une nouvelle flèche avec un losange au départ.


---
## Tâche supplémentaire : Correction et amélioration des fonctionnalités

##### Difficulté : 1

TODO (descriptif)

#### Les étudiants qui se sont occupés de cette partie sont :

- Grossmann Jérémy

### Etat :

Tâche achevée

