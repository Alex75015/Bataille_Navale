# Bataille_Navale

Exécution du projet avec commande dans le dossier 'bataille navale squelette' :
mvn exec:java

J'ai créée un objet Tile pour les tableaux 2D (gridShips et gridHits)
pour avoir une logique à l'intérieur des cases du tableau (orienté Objet)
C'est une initiative de ma part que j'ai poursuivi tout le sujet

# Quelques commentaires :

# Exercice 2 
Pour remédier au problème d'augmentation des fichiers, on peut créer/utiliser des packages pour plus d'organisation

# Exercice 5
Pour l'exercice 5, ma grille des frappes est une grille de tuiles (j'ai fait à ma façon)
donc j'avais  déjà modifié cette partie et donc pas besoin de classe shipstate pour moi

# Exercice 6 : 
Par rapport à la méthode hasShip, je n'ai pas besoin de faire de modif car
dans tous les cas si un bateau est coulé c'est que j'ai touché toutes ses cases, et je ne
peux donc plus relancé de frappes sur ces mêmes cases.