## Fonctionnalité 1 : Sélection de classe
##### Difficulté: 2/3

En tant qu’étudiant, je souhaiterais pouvoir ajouter la fonctionnalité d'importation par un utilisateur :
L'utilisateur pourra importer ses classes en fournissant le chemin de son fichier ou de son dossier.


#### Les étudiants qui se sont occupés de cette partie sont :
- Gridel Alexis

### Critères de validation :
- Bouton qui ouvre l’explorateur de fichier pour sélectionner un dossier
- L’affiche dans une arborescence (à gauche de l’application)
- Pouvoir glisser et déposer les classes de l’arborescence à l'écran principal pour pouvoir les afficher en tant que diagramme

### Etat :
Fonctionnalité inachevée

#### Détail de la fonctionnalité :
Quand on sélectionne une classe avec le clic gauche de la sours, elle est censée "s'illuminer" d'un contour bleu
Pour cela, j'ai du crée un controller qui gère le click sur un VBox (ClasseAffichage) s'appellant ClasseEntiereClickedController

Le controller va appeler la méthode setCurrentClickedClass dans le modèle qui va set le nom de la classe dans une propriété String pour ensuite actualiser les observateurs.


---
