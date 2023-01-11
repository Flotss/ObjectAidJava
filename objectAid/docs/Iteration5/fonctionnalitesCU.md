## Fonctionnalité 1 : Afficher/Cacher classe parent
##### Difficulté : 1

En tant qu’étudiant, je souhaiterais pouvoir cacher ou non les classes parentes
d'une classes sélectionnée pour pouvoir dégager les choses pertinentes de mon projet.

#### Les étudiants qui se sont occupés de cette partie sont :
- Bernard Julien

### Critères de validation :
- Après un clic droit sur une classe, il y a la possibilité de masquer ses classes parentes
-
### Etat :
Fonctionnalité achevée

#### Détail de la fonctionnalité :
Cette fonctionnalité permet de cacher des classes parentes d'une autre classe
et de les afficher à nouveau.
Pour ce faire, lors du clic sur le bouton correspondant, on récupère les relations
d'héritage de la classe correspondante et on masque la classe dont elle hérite 
(avec les relations allant avec)


---
## Fonctionnalité 2 : Afficher/Cacher les interfaces
##### Difficulté : 1

En tant qu’étudiant, je souhaiterais pouvoir cacher ou non les classes parentes de type 
interface d'une classes sélectionnée pour pouvoir montrer les choses pertinentes de mon projet.

#### Les étudiants qui se sont occupés de cette partie sont :
- Bernard Julien

### Critères de validation :
- Après un clic droit sur une classe, il y a la possibilité de masquer ses interfaces

### Etat :
Fonctionnalité achevée

#### Détail de la fonctionnalité :
Cette fonctionnalité permet de cacher des classes interfaces d'une autre classe
et de les afficher à nouveau.
Pour ce faire, lors du clic sur le bouton correspondant, on récupère les relations
d'implémentation de la classe correspondante et on masque la classe qu'elle extend
(avec les relation allant avec)

---
## Fonctionnalité 3 : Générer le squelette d'une classe ou de plusieurs classes
##### Difficulté : 2

En tant qu’étudiant, je souhaiterais pouvoir générer le squelette d'une classe ou de plusieurs classes, pour pouvoir implémenter les classes générer comme l'on veut.

#### Les étudiants qui se sont occupés de cette partie sont :
- Mangin Florian

### Critères de validation :
- Après un clic droit sur une classe, il y a la possibilité de générer le squelette de la classe
- Un explorateur de fichier s'ouvre pour choisir le dossier de destination
- Le fichier est généré dans le dossier de destination

- Un bouton générer squelette en haut de l'application permet de générer le squelette de toutes les classes présente dans le diagramme

- Une alert est affiché pour montrer a l'utilisateur que le/les squelette(s) a/ont été généré(s)


### Etat :
Fonctionnalité achevée


#### Détail de la fonctionnalité :
La fonctionnalité utilise un service de génération de squelette de classe. Lors du clic sur la génération, le service est appelé, ce service demande un dossier de destination depuis l'explorateur de fichiers, puis génère le squelette de la classe en format java et l'écris dans le fichier du nom de la classe dans le dossier de destination. Puis le service affiche une alert.



## Fonctionnalité 4 : Importation
##### Difficulté : 4

En tant qu’étudiant, je souhaiterais pouvoir ajouter la fonctionnalité d'importation par un utilisateur :
L'utilisateur pourra importer ses classes en fournissant le chemin de son fichier ou de son dossier.
Pour éviter différents bugs dû à la précipitation, nous avons décidé de prolonger la tâche d'un
sprint. En effet, cette fonctionnalité est terminée, mais la branche n'est pas encore Merge avec la branche
principale. Nous préférons prendre notre temps pour merge, afin d'éviter tout gros bugs.

#### Les étudiants qui se sont occupés de cette partie sont :
- Gridel Alexis

### Critères de validation :
x Bouton qui ouvre l’explorateur de fichier pour sélectionner un dossier
x L’affiche dans une arborescence (à gauche de l’application)
x Pouvoir glisser et déposer les classes de l’arborescence à l'écran principal pour pouvoir les afficher en tant que diagramme

### Etat :
Fonctionnalité inachevée --> sera prolongé dans le sprint suivant

#### Détail de la fonctionnalité :
Cette fonctionnalité permet de séparer l'application en deux états:
- L'état d'importation où l'utilisateur peut importer son projet
- L'état d'affichage où l'utilisateur peut afficher son projet

L'importation se fait via un explorateur de fichier, où on peut sélectionner un dossier. Ce dossier sera affiché dans une arborescence à gauche de l'application. L'utilisateur peut ensuite glisser et déposer les classes de l'arborescence à l'écran principal pour pouvoir les afficher en tant que diagramme.
Les classes à l'intérieur du projet se font compiler et sont stockées dans une liste de classes.
Plus précisemment dans un dictionnaire qui associe un nom à chaque classe, le but étant de pouvoir introspecté les classes pour pouvoir les afficher dans le diagramme.

Quant à l'affiche:
Une fois les classes à gauche dans l'arborescence, on peut glisser-déposer (drag n drop) la classe au menu d'à droite (vueclasse) pour le "transformer" en un diagramme.

### Avancement par rapport aux autres itérations :
Importation avec succès, manque plus qu'à merge avec la branche principale.

Reste à fixer le bug lors du merge main -> branche qui s'est imposé.
+ Voir si on peut modulariser l'importation d'un quelconque projet.


## Fonctionnalité 5 : Supprimer des classes
##### Difficulté : 1

En tant qu’étudiant, je souhaiterais pouvoir supprimer des classes de mon diagramme pour pouvoir permettre une personnalisation adéquate du projet.

#### Les étudiants qui se sont occupés de cette partie sont :
- Grossmann Jérémy

### Critères de validation :
- Après un clic droit sur une classe, il y a la possibilité de supprimer cette classe avec un MenuItem
- Un MenuItem doit etre ajouté afin de supprimer toutes les classes présentes dans le diagramme

### Etat :
Fonctionnalité achevée


#### Détail de la fonctionnalité :
La fonctionnalité permet de supprimer une classe seléctionnée ou toutes les classes. 


