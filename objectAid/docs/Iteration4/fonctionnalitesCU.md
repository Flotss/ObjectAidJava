## Fonctionnalité 1 : Importation
##### Difficulté : 4

En tant qu’étudiant, je souhaiterais pouvoir ajouter la fonctionnalité d'importation par un utilisateur :
L'utilisateur pourra importer ses classes en fournissant le chemin de son fichier ou de son dossier.
Pour éviter différents bugs dû à la précipitation, nous avons décidé de prolonger la tâche d'un
sprint. En effet, cette fonctionnalité est terminée, mais la branche n'est pas encore Merge avec la branche
principale. Nous préférons prendre notre temps pour merge, afin d'éviter tout gros bugs.

#### Les étudiants qui se sont occupés de cette partie sont :
- Gridel Alexis

### Critères de validation :
- Bouton qui ouvre l’explorateur de fichier pour sélectionner un dossier
- L’affiche dans une arborescence (à gauche de l’application)
- Pouvoir glisser et déposer les classes de l’arborescence à l'écran principal pour pouvoir les afficher en tant que diagramme

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

---
## Fonctionnalité 2 : Afficher les relations
##### Difficulté: 4

En tant qu’étudiant, je souhaiterais pouvoir afficher ou non les différentes relations d’héritage/ d’implémentation entre les différentes classes, ainsi que les relation d'association. Cet affichage se fera après avoir appuyé un bouton.
Il faudra donc générer des flèches et autres relations entre les classes.

#### Les étudiants qui se sont occupés de cette partie sont :
- Mangin Florian
- Bernard Julien

### Critères de validation :
- Bouton pour réaliser cet affichage. 
- Les relations s'affichent / se désaffichent après avoir appuyé sur le boutonà l'écran principal pour pouvoir les afficher en tant que diagramme

#### Détail de la fonctionnalité :
Cette fonctionnalité permet de créer des relations entre les classes.
C'est à dire la création d'associations, d'héritage et d'implémentations pour chaque classe qui a été importé. De plus elle doit permettre de les afficher ou non dans l'affichage graphique de l'application grace à un bouton et des flèches.

### Etat :
Fonctionnalité inachevée --> sera prolongé dans le sprint suivant
Les association s'affichent sur le rendu, mais sans sens et sans type de relation.
Pour pouvoir différencier les relations, les fleches ont des couleurs : Héritage = rouge, Implémentation = vert, Association = bleu.

---
## Fonctionnalité 3 : Afficher/cacher des classes
##### Difficulté: 1

En tant qu’étudiant, je souhaiterais pouvoir cacher ou non les classes sélectionnés pour pouvoir dégager les cas les plus pertinents. Cet affichage se fera à l'aide d'un bouton.

#### Les étudiants qui se sont occupés de cette partie sont :
- Bernard Julien
- Mangin Florian

### Critère de validation :
- Dans le menu contextuel du clic droit un bouton cacher classe
- La classe se cache avec ses relations
- Un bouton pour afficher les classes cachées
- La possibilité de les réafficher en cliquant sur le nom d'une classe

#### Détail de la fonctionnalité :
Cette fonctionnalité permet de cacher des classes et de les réafficher.
C'est à dire la création d'un bouton dans le menu contextuel du clic droit qui permet de cacher une classe et ses relations. De plus il faut pouvoir les réafficher en cliquant sur le nom d'une classe qui se trouve dans une nouvelle fenêtre.

### Etat :
Fonctionnalité achevée 

---
## Tâche supplémentaire : Perfection de l'apparence 
##### Difficulté: 2

En tant qu’étudiant, je souhaiterais pouvoir ajuster / améliorer, parfaire l’apparence de mon application.

#### Les étudiants qui se sont occupés de cette partie sont :
- Grossmann Jérémy
- Bernard Julien

### Critères de validation :
- Centrer le titre de chaque classe
- Pouvoir passer la souris sur une méthode/attribut/constructeur/nom pour voir le label complet(tooltip)
- Réajuster l’application par rapport à la maquette (commencement)
- Afficher un ContextMenu lors d'un clique droit sur une classe au lieu d'afficher des boutons 
- Remplacer les boutons d'affichage spécifique par des MenuItem dans le ContextMenu
- Ajouter couleurs pour les symboles

#### Détail de la tâche supplémentaire :
La tâche supplémentaire consiste à améliorer l'apparence de l'application.
Nous avons choisi de changer l'affichage de l'application afin de rendre l'utilisation plus facile.
Nous avons créé deux controleurs : un controleur pour gérer les MenuItem et un autre pour le click droit sur une classe.
Le contenu d'une classe est maintenat plus lisible grâce aux tooltips rajoutés.


### Etat :
Tâche supplémentaire terminée