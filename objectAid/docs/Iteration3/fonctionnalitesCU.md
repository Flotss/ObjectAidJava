## Fonctionnalité 1 : Importation
##### Difficulté: 4

En tant qu’étudiant, je souhaiterais pouvoir ajouter la fonctionnalité d'importation par un utilisateur :
L'utilisateur pourra importer ses classes en fournissant le chemin de son fichier ou de son dossier.


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

### Critères de validation :
- Bouton pour réaliser cet affichage. 
- Les relations s'affichent / se désaffichent après avoir appuyé sur le boutonà l'écran principal pour pouvoir les afficher en tant que diagramme

#### Détail de la fonctionnalité :
Cette fonctionnalité permet de créer des relations entre les classes.
C'est à dire la création d'associations, d'héritage et d'implémentations pour chaque classe qui a été importé. De plus elle doit permettre de les afficher ou non dans l'affichage graphique de l'application grace à un bouton et des flèches.

### Etat :
Fonctionnalité inachevée --> sera prolongé dans le sprint suivant
Les association s'affichent sur le rendu, mais sans sens et sans type de relation.


## Fonctionnalité 3 : Afficher/Cacher les Attributs classe sélectionnée
##### Difficulté: 1

En tant qu’étudiant, je souhaiterais pouvoir cacher ou non les constructeurs d'une classe sélectionnée pour pouvoir dégager l’essentiel. Cet affichage se fera à l'aide d'un bouton.

#### Les étudiants qui se sont occupés de cette partie sont :
- Bernard Julien
- Grossmann Jérémy


### Critère de validation :
- Bouton pour réaliser cet affichage
- Les attributs de la classe sélectionnée s'affichent / se désaffichent après avoir appuyé sur le bouton

### Détail de la fonctionnalité :
Nous avons ajouté des boutons avec des controleurs pour ces derniers et nous sommes servit de l'attribut boolean, dans la classe,
correspondant à si cette partie doit être affichée ou non. Le controleur appelle le modele qui va changer
cet attribut de la classe selectionnée, lors de l'actualisation de la classe, l'attribut sera controlé pour vérifié si les attributs doivent
être affichés.

#### Patron d'architecture utilisé :
- MVC : Pour séparer le modèle, la vue et le contrôleur.

#### Patron de conception utilisé :
- Observer/Observé : Pour que la vue puisse observer le modèle et se mettre à jour en fonction des changements.

### Etat :
Fonctionnalité achevée


## Fonctionnalité 4-5 : Afficher les méthodes et les constructeurs de la classe selectionnée
Même principe que pour la fonctionnalitée 3

#### Les étudiants qui se sont occupés de ces partie sont :
- Bernard Julien
- Grossman Jérémy

### Etat :
Fonctionnalité achevée



## Tâche supplémentaire : Correction des problèmes de performances

Lors du sprint précédent, nous actualisions l'ensemble de l'affichage à chaque modification,
que ce soit les bordures, les déplacements ou les modification de l'affichage généraux.
Or, pour les 2 premiers cas, nous ne voulions actualiser l'affichage que de la classe 
que nous modifions.

#### Les étudiants qui se sont occupés de cette partie sont :
- Bernard Julien

### Etat :
Tâche terminée

