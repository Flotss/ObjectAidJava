# ObjectAidJava

Participant : 
- [ ] [Mangin Florian](https://github.com/Flotss)
- [ ] [Gridel Alexis](https://github.com/Inerska)
- [ ] [Bernard Julien](https://github.com/julienBernard3)
- [ ] [Grossmann Jérémy](https://github.com/T1Boomer)

## Description

Ce project a pour but de faire une application similaire à [ObjectAid UML Explorer](https://marketplace.eclipse.org/content/objectaid-uml-explorer) en Java.
<br>Pour exécuter la démo, veuillez lancer le fichier ObjectAidApplication se trouvant dans le dossier: [ObjectAid/src/main/org/teamtree/objectaid](https://github.com/Flotss/ObjectAidJava/blob/main/objectAid/src/main/java/org/teamtree/objectaid/ObjectAidApplication.java).

## Comment installer et lancer l'application ?
### Depuis le terminal:
- Cloner le projet avec la commande: `git clone`
- Se rendre dans le dossier du projet: `cd ObjectAidJava`
- Vérifier que l'exécutable gradlew est bien présent: `ls`
- Lancer l'application depuis le CLI Gradlew: `./gradlew run`
- L'application se lance et vous pouvez l'utiliser.

### Depuis Intellij IDEA:
- Cloner le projet avec la commande: `git clone`
- Ouvrir le projet dans Intellij IDEA
- Vérifier que le JDK est bien configuré dans les paramètres de l'IDE
- Ouvrir le menu Gradle et cliquer sur `Tasks > application > run`

### Depuis Intellij IDEA 2):
- Cloner le projet avec la commande: `git clone`
- Ouvrir le projet dans Intellij IDEA
- Vérifier que le JDK est bien configuré dans les paramètres de l'IDE
- Ouvrir le menu Gradle (l'éléphant à droite), cliquer sur actualiser en haut à gauche ou bien passer par le fichier `build.gradle` et cliquer sur `refresh all gradle projects` (ou le bouton refresh volant)
- Aller dans le dossier `objectAid > src > main > java > org.teamtree.objectaid` et cliquer sur le fichier `ObjectAidApplication.java`
- Cliquer sur le bouton vert `Run ObjectAidApplication.main()`

Note: Si vous n'arrivez toujours pas, on peut éventuellement générer un .jar avec un fatjar/shadow/uberjar et l'exécuter depuis le terminal, plus facilement.

## Exemple

![](https://i.imgur.com/QWYGMcI.png)
