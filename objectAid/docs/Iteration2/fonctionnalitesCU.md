## Fonctionnalité 1 : Sélection de classe
##### Difficulté: 1

En tant qu’étudiant, je souhaiterais pouvoir sélectionner une classe spécifique. Il faudra afficher cette sélection.

#### Les étudiants qui se sont occupés de cette partie sont :
- Gridel Alexis
- Mangin Florian (petite partie)

### Critères de validation :
- Lors d'un click sur une classe, cette dernière se met en évidence.

### Etat :
Fonctionnalité achevée

#### Détail de la fonctionnalité :
Quand on sélectionne une classe avec le clic gauche de la sours, elle est censée "s'illuminer" d'un contour bleu
Pour cela, j'ai du crée un controller qui gère le click sur un VBox (ClasseAffichage) s'appellant ClasseEntiereClickedController

Le controller va appeler la méthode setCurrentClickedClass dans le modèle qui va set le nom de la classe dans une propriété String pour ensuite actualiser les observateurs.


---
## Fonctionnalité 2 : Afficher les attributs
##### Difficulté: 1

En tant qu’étudiant, je souhaiterais pouvoir afficher ou non les différents attributs de l'ensemble des classes. Cet affichage se fera après avoir appuyé un bouton.

#### Les étudiants qui se sont occupés de cette partie sont :
- Gridel Alexis
- Bernard Julien


### Critère de validation :
- Bouton pour réaliser cet affichage
- Les attributs de toutes les classes s'affichent / se désaffichent après avoir appuyé sur le bouton

### Détail de la fonctionnalité :
Nous avons ajouté des boutons avec des controleurs pour ces derniers ainsi qu'un attribut boolean dans chaque classe
correspondant à si cette partie doit être affichée ou non. Le controleur appelle le modele qui va changer 
cet attribut de toute les classes, lors de l'affichage, l'attribut sera controlé pour vérifié si les attributs doivent 
être affichés.

#### Patron de conception utilisé :
 - fabrique : Pour fabriquer l'affichage

---
## Fonctionnalité 3-4 : Afficher les méthodes et les constructeurs
Même principe que pour la fonctionnalitée 2

#### Les étudiants qui se sont occupés de ces partie sont :
- Gridel Alexis
- Bernard Julien

### Etat :
Fonctionnalité achevée

---
## Fonctionnalité 5 : Afficher les relations
##### Difficulté: 3

En tant qu’étudiant, je souhaiterais pouvoir afficher ou non les différentes relations d’héritage/ d’implémentation entre les différentes classes, ainsi que les relation d'association. Cet affichage se fera après avoir appuyé un bouton.
Il faudra donc générer des flèches et autres relations entre les classes.

#### Les étudiants qui se sont occupés de cette partie sont :
- Mangin Florian
- Grossmann Jérémy


### Critères de validation :
Bouton pour réaliser cet affichage.
Les relations s'affichent / se désaffichent après avoir appuyé sur le bouton


#### Détail de la fonctionnalité :
Cette fonctionnalité permet de créer des relations entre les classes.
C'est à dire la création d'associations, d'héritage et d'implémentations pour chaque classe qui a été importé. De plus elle doit permettre de les afficher ou non dans l'affichage graphique de l'application grace à un bouton et des flèches.

### Etat :
Fonctionnalité inachevée --> sera prolongé dans le sprint suivant
Nous avons réalisé la structure de données permettant de stocker les relations entre les classes.
C'est-à-dire que maintenant lorque l'on crée une classe, ses relations sont stockées dans une liste de relations donc pour les associations, les implémentations et pour l'héritage.
Les associations, héritage et implémentation sont affichées dans la console, mais il reste à les afficher sur l'interface graphique

#### Patron de conception utilisé :
- Strategy : Pour la gestion des relations entre Héritage, Implémentation et Association


---
## Fonctionnalité 6 : Déplacer les classes
##### Difficulté: 3

En tant qu'étudiant, je souhaiterais pouvoir déplacer les différentes classes dans le diagramme pour pouvoir les agencer comme bon me semble afin de pouvoir dégager par exemple les différents patrons de conception.

#### Les étudiants qui se sont occupés de cette partie sont :
- Grossmann Jérémy

### Critères de validation :
- Lors d'un click gauche maintenu sur une classe sélectionnée, je peux la déplacer avec la souris.
- Lorsque je relache le click gauche, la classe reste à l'endroit où je l'ai lâché.

### Etat :
Tâche terminée

---
## Tâche supplémentaire : Commentaire et JavaDoc du projet

Nous n'avions pas commenté le code lors de la précédente itération et nous devions commenté la nouvelle itération

#### Les étudiants qui se sont occupés de cette partie sont :
- Mangin Florian
- Bernard Julien

### Etat :
Tâche terminée