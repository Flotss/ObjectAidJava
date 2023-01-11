## Fonctionnalité 1 : Afficher/Cacher classe parent
##### Difficulté : 1

En tant qu’étudiant, je souhaiterais pouvoir cacher ou non les classes parentes
d'une classes sélectionnée pour pouvoir dégager les choses pertinentes de mon projet.

#### Les étudiants qui se sont occupés de cette partie sont :
- Bernard Julien

### Critères de validation :
- Après un clique droit sur une classe, il y a la possibilité de masquer ses classes parentes
-
### Etat :
Fonctionnalité achevée

#### Détail de la fonctionnalité :
Cette fonctionnalité permet de cacher des classes parentes d'une autre classe
et de les afficher à nouveau.
Pour ce faire, lors du click sur le bouton correspondant, on récupère les relations
d'héritage de la classe correspondante et on masque la classe dont elle hérite 
(avec les relation allant avec)

## Fonctionnalité 2 : Afficher/Cacher les interfaces
##### Difficulté : 1

En tant qu’étudiant, je souhaiterais pouvoir cacher ou non les classes parentes de type 
interface d'une classes sélectionnée pour pouvoir montrer 
les choses pertinentes de mon projet.

#### Les étudiants qui se sont occupés de cette partie sont :
- Bernard Julien

### Critères de validation :
- Après un clique droit sur une classe, il y a la possibilité de masquer ses interfaces

### Etat :
Fonctionnalité achevée

#### Détail de la fonctionnalité :
Cette fonctionnalité permet de cacher des classes interfaces d'une autre classe
et de les afficher à nouveau.
Pour ce faire, lors du click sur le bouton correspondant, on récupère les relations
d'implémentation de la classe correspondante et on masque la classe qu'elle extend
(avec les relation allant avec)