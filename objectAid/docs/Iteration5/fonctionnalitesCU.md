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
