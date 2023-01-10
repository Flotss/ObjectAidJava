TODO

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

