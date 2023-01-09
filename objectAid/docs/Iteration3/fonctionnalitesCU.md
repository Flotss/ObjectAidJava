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
- Les relations s'affichent / se désaffichent après avoir appuyé sur le bouton à l'écran principal pour pouvoir les afficher en tant que diagramme

#### Détail de la fonctionnalité :
Cette fonctionnalité permet de créer des relations entre les classes.
C'est à dire la création d'associations, d'héritage et d'implémentations pour chaque classe qui a été importé. De plus elle doit permettre de les afficher ou non dans l'affichage graphique de l'application grace à un bouton et des flèches.

### Etat :
Fonctionnalité achevée
Les critères de validation ont été validé avec succès

---
## Fonctionnalité 3 : Perfection de l'apparence 
##### Difficulté: 2
Nous souhaitons améliorer l'apparence de l'affichage des classes et des relations.

#### Les étudiants qui se sont occupés de cette partie sont :
- Mangin Florian
- Grossmann Jérémy

### Critère de validation : 
- L'accessibilité doit être représenter par leur icones respectives
- Les flèches doit avoir 3 point d'accroche par coté
- Pour l'affichage spécifique d'une classe, doit fonctionné en fesant clic droit

#### Détail de la fonctionnalité : 
Cette fonctionnalité permet d'avoir une affichage plus propre et plus lisible pour l'utilisateur.
Pour cela nous avons ajouté des icones pour les différents types d'accès (public, private, protected, et par défaut) et nous avons ajouté des points d'accroche sur les flèches pour les rendre plus lisibles.

### Etat :
Fonctionnalité achevée


---
## Fonctionnalité 4 : Afficher/Cacher des classes
##### Difficulté: 3

En tant qu’étudiant, je souhaiterais pouvoir cacher des classes de mon projet. Cela permettra de ne pas afficher les classes que je ne souhaite pas voir.

#### Les étudiants qui se sont occupés de cette partie sont :
- Mangin Florian

### Critères de validation :
- Bouton pour cacher les classes
- Les classes sont cachées après avoir appuyé sur le bouton dans le menu contextuel du clic droit
- Les flèches qui reliaient les classes cachées sont également cachées

- Bouton pour afficher dans un déroulant les classes cachées
- Les classes cachées sont affichées après avoir appuyé sur une de ces classes dans le déroulant

#### Détail de la fonctionnalité :
Cette fonctionnalité permet de cacher des classes et de les afficher à nouveau.


### Etat :
Fonctionnalité inachevée --> sera prolongé dans le sprint suivant

---

