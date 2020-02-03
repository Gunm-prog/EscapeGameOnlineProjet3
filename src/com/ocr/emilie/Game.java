package com.ocr.emilie;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Game extends Exception{


    private static int length;
    private static int i;

    public void launchGame(int choixUtilisateur) {
        Scanner sc=new Scanner( System.in );
        /*char Plus='+';
        char Moins='-';
        char Egal='=';*/
        String proposition="";
        String cleSecrete="";
        String Reponse="";
        String tentative="";
        int tailleCle=-1;
        int tailleproposition=-1;
        String rejouer="";
        int nbGamingMode=choixUtilisateur;


        if (choixUtilisateur == 1) {//mode challenger

            boolean test=false;
            do {
                System.out.println( "Saisissez la taille de la cle: " );
                tailleCle=sc.nextInt();
                sc.nextLine();
                String strTailleCle=Integer.toString(tailleCle);
                test=testIntInputUser(strTailleCle,1,4) ;
            }while(test);

            for (i=0; i < tailleCle; i++) {
                cleSecrete=cleSecrete + Integer.toString( getRandomNumberInRange( 0, 9 ) );
            }
            System.out.println( "Afficher le randomNumber: " + cleSecrete );


            for (int i=0; i < 6; i++) {
                do{
                    System.out.println( "Veuillez saisir une proposition: " );
                    proposition=sc.nextLine();
                }while(!isIntValid( proposition ));

                if (proposition.equals( cleSecrete )) {
                    System.out.println( "Bravo! Vous avez gagné!" );
                    Reponse="====";
                    break;
                } else if (proposition != cleSecrete) {//tranformer en else
                    System.out.println( "Oops! La proposition est fausse!" );//dommage votre prop est fausse
                } else {// supprimer le else pas necessaire
                    System.out.println( "Too Bad! Vous avez utilisé les 6 essais" );
                }
                Reponse="";
                for (int j=0; j < tailleCle; j++) {
                    if ((int) cleSecrete.charAt( j ) > (int) proposition.charAt( j )) {
                        Reponse+="+";
                    } else if ((int) cleSecrete.charAt( j ) < (int) proposition.charAt( j )) {
                        Reponse+="-";
                    } else {
                        Reponse+="=";
                    }
                }
                System.out.println( cleSecrete + "Proposition : " + proposition + " -> Réponse : " + Reponse );
            }
            System.out.println( "cle secrete: " + cleSecrete + " proposition : " + proposition + " -> Réponse : " + Reponse );
            System.out.println( "Too Bad! Vous avez utilisé les 6 essais + /n" );
        }


        if (choixUtilisateur == 2) {//Mode defenseur
            //////////// Debut Initialisation //:::::::::::::::::::::::
                System.out.println( "Veuillez saisir la taille de la cleSecrete: " );
                tailleCle=sc.nextInt();
                sc.nextLine();

            boolean test=false;
            int testMax=(int) (1*Math.pow(10,tailleCle)-1);//Definit le maximum en fonction de la tailleCle (si tailleCle=1, le max sera egal à 1*10^1-1
            // si tailleclé = 2 , max = 1*10^2 -1
            do {
                System.out.println( "Saisissez votre cleSecrete: " );
                cleSecrete= sc.nextLine();
                sc.nextLine();
                test=testIntInputUser(cleSecrete, 0,testMax);//max étant définit au cas où la taille cle change
            }while(test);

            //on donne à tentative sa valeur de base, en fonction de la tailleClé
            for (i=0; i < tailleCle; i++) {
                tentative=tentative + "09"; //tentative = 09090909
            }

            //////////// Fin Initialisation //:::::::::::::::::::::::

            //////////// Début boucle des Rounds //:::::::::::::::::::::::

            for (i=0; i < 6; i++) {
                //Pour afficher un essai N° 1 et non pas un essai N° 0
                System.out.println( "Round numéro " + (i + 1) + "/6" );

            /*
                remise à vide de la chaine modifTentative à chaque début de Round
                puisqu'elle contient(sauf au premier round, la valeur de la modifTentative du round précédent)
                elle est déclarée avant la boucle dans laquelle on va l'utiliser
            */
                String modifTentative="";

                /*
                    si Reponse n'est pas vide (donc que l'on a déjà fait un tour de jeu et que le joueur à donné des indices pour l'odrinateur)

                    bloc  qui modifie modifTentative avec les indices donnés par le joueur
                    modifTentative (varaible de transition) ecrasera juste après la boucle, tentative
                */
                if (Reponse != "") {
                    for (int j=0; j < tailleCle; j++) {

                        //  recupération du char dans reponse ex:([-]+=+)pour j==0
                        char joueurReponse=Reponse.charAt( j );

                        //  recupération du char dans ordiProposition ex:([3]095)pour j==0
                        char ordiProposition=proposition.charAt( j );

                        char min=0;// stockera la valeur minimale pour le nouvelle intervalle
                        char max=0;// stockera la valeur maximale pour le nouvelle intervalle
                        switch (joueurReponse) {// joueurReponse contient un char: + ou - ou =
                            case '+':
                            /*
                                si l'indice dit que la valeur à trouver est plus grande que la valeur proposée:
                                on place la valeur proposée +1 en minimum du nouvelle intervalle
                                et en maximum, on reprend la valeur stockée en maximum dans tentative
                            */

                                ordiProposition++;
                            /*
                                    +1 pour exclure le chiffre proposé de l'intervalle de recherche
                                    ex: pour plus que 4 (choisi dans la proposition de l'ordi, 4 ne peux pas etre inclu dans l' intervalle, je veux l'intevalle [(4+1), 9] pour avoir un chiffre entre 5 et 9

                                variable++; revient à faire, variable=variable+1; (dans le cas d'une adition mathématique)
                            */
                                min=ordiProposition;

                                //  on ne change pas le max, on le recupère alors dans tentative
                                max=tentative.charAt( 1 + j * 2 );

                                break;
                            case '-':
                        /*
                            si l'indice dit que la valeur à trouver est moins grande que la valeur proposée:
                            on place la valeur proposée -1 en maximum du nouvelle intervalle
                            et en minimum, on reprend la valeur stockée en minimum dans tentative
                        */

                                //  on ne change pas le min, on le récupère dans tentative
                                min=tentative.charAt( j * 2 );

                                ordiProposition--;
                            /*
                                -1 pour exclure le chiffre de l'intervalle de recherche
                                variable--; revient à faire variable=variable -1; (dans le cas d'une soustraction mathématique)
                            */
                                max=ordiProposition;

                                break;
                            case '=':
                        /*
                            si l'indice dit que la valeur à trouver est égale à la valeur proposée:
                            on place la valeur proposée en minimum et en maximum dans le nouvelle intervalle
                        */
                                min=ordiProposition;
                                max=ordiProposition;
                                break;

                        }
                /*
                    toujours le probleme des char convertis en int retourne nombre à deux chiffres
                    solution convertir char en string puis string en int
                    ici la conversion en int se fera juste avant le random dans la suite du code
                */
                        String strMin=String.valueOf( min );
                        String strMax=String.valueOf( max );

                        modifTentative+=strMin + strMax;
                        //  On concatène le nouvelle intervalle à la suite de la chaine modifTentative

                        //   System.out.println("[Test] concaténation de modifTentative: "+modifTentative);
                    }

                    //System.out.println("[Test] modifTentative = "+modifTentative);
                    tentative=modifTentative;
                    //  On remplace l'ancienne valeur du String tentative par la nouvelle valeur dans modifTentative
                }

                //  System.out.println("[Test] tentative ligne 104 = "+tentative);

                proposition="";
            /*
                remise à zero de proposition
                (puisqu'elle contient (sauf au premier round), proposition du round précédent)
            */
                for (int j=0; j < tailleCle; j++) {
            /*
                bloc for qui va générer la proposition de l'ordinateur
                on utilise les [min-max] stockes dans tentative
                min et max iront dans la méthode random
            */
                    char minTentative=tentative.charAt( j * 2 );
                    char maxTentative=tentative.charAt( 1 + 2 * j );
                /*
                    la valeur de String tentative etant suos la forme 09090909
                    pour minmaxminmaxminmaxminmax
                    on récupère les valeurs par charAt en déplaçant les'index de 2 en 2
                */

                /* la convertion des char en int, retourne des valeurs à deux chiffres du fait que le char, stocke     sous la forme d'unicode, ASCII, ce qui fausse le random
                    pour contourner ca, je convertis d'abord mes char en String, puis je les convertis en Int
                */
                    String strMin=String.valueOf( minTentative );
                    String strMax=String.valueOf( maxTentative );

                    //  System.out.println("[Test] pos "+j+" de la clef, minTentative = "+strMin);
                    //  System.out.println("[Test] pos "+j+" de la clef, maxTentative = "+strMax);

                    int r=getRandomNumberInRange( Integer.valueOf( strMin ), Integer.valueOf( strMax ) );
                    //min et max convertis en int directement dans les paramètre de la méthode.

                    proposition=proposition + r;
                    //  concaténation dans le String proposition à chaque tour de boucle

                    //System.out.println( "[Tet] ramdom, lancement num " + j + " avec intervalle [" + strMin + "," + strMax + "] = " + r );
                }

                //System.out.println("[Test] variable tentative = "+ tentative);
                System.out.println( "L'ordinateur propose: " + proposition );

            /*
                Vérification des conditions de victoire
            */
                if (proposition.equals( cleSecrete )) {
                    System.out.println( "L'ordinateur à trouvé!! au " + (i + 1) + "ème essai! Vous avez perdu :(" );
                    break;
                } else if (proposition != cleSecrete) {
                    System.out.println( "Oops! c'est raté!" );
                    //joueur doit entrer les indices variable reponse
                }
                System.out.println( "Saisissez l'indice + - ou = pour chaque chiffre: " );
                System.out.println( "L'ordinateur à proposé: " + proposition );
                System.out.println( "Votre clé secrète:      " + cleSecrete );

                Reponse=sc.nextLine();
                sc.nextLine();
                //il faut ajouter un controle des reponse du joueur,
                // si le joueur se trompe dans ces indices,
                // cela peux faire planter le random par transmission d'un min > au max
            }
            System.out.println( "C'est fini!" );
            System.out.println( "cle secrete: " + cleSecrete + " proposition : " + proposition );

        }


        if (choixUtilisateur == 3) { //mode Duel
            tailleCle=-1;
            String cleSecreteJoueur="";
            String cleSecreteOrdi="";
            String reponseJoueur="";
            String reponseOrdi="";
            String tentativeJoueur="";
            String tentativeOrdi="";
            String modifTentativeJoueur="";
            String modifTentativeOrdi="";
            String propositionOrdi="";
            String modifPropositionOrdi="";
            String propositionJoueur="";
            String winner="";//victoire joueur: winner = joueur  victoire ordi: winner = ordi  egalite: ordiJoueur
            boolean endGame=false;


            System.out.println( "Veuillez saisir la taille de la cleSecrete: " );
            tailleCle=sc.nextInt();
            sc.nextLine();
            System.out.println( "Saisissez votre cleSecrete: " );
            cleSecreteJoueur=sc.nextLine();
            sc.nextLine();

            for (i=0; i < tailleCle; i++) {//Génère la clesecreteOrdi et cree la chaine de recherche (tentativeOrdi) avec min-max (min possible 0 et max possible 9)
                cleSecreteOrdi=cleSecreteOrdi + Integer.toString( getRandomNumberInRange( 0, 9 ) );
                tentativeOrdi=tentativeOrdi + "09";// chaine tentative c'est le min-max [0-9] de chaque chiffre de la cleSecrete
                // 09090909 pour une taille clé de 4 //affiche 09090909 parce qu'au premier tour il n'y a pas d'indice
                //ordi a besoin de ses intervalles pour pouvoir faire une proposition
            }
            System.out.println( "Afficher la cleSecreteOrdi: " + cleSecreteOrdi );

            for (int j=0; j < 6; j++) {
                System.out.println( " Round n° " + (j + 1) + "/6" );
                System.out.println( "Tour du joueur " );
                System.out.println( "Veuillez saisir une propositionJoueur: " );
                if (j != 0) {
                    System.out.println( " ancienne propositionJoueur: " + propositionJoueur + "  reponseOrdi: " + reponseOrdi );
                }
                propositionJoueur=sc.nextLine();


                reponseOrdi="";
                if (j != 0) {//Traitement des indices joueur pour modifier la chaîne tentative. j!=0 ne va pas être lu au premier tour de boucle parce qu'il n'y a pas encore d'indices
                    modifTentativeOrdi="";
                    for (int k=0; k < tailleCle; k++) {
                        //Génération proposition ordinateur// k++ revient à dire k=k+1
                        //modification intervalle recherche de lordi selon indices joueur
                        System.out.println( "modifTentativeOrdi = " + modifTentativeOrdi );
                        System.out.println( "propositionOrdi: " + propositionOrdi );
                        System.out.println( "reponseJoueur: " + reponseJoueur );
                        char min=tentativeOrdi.charAt( k * 2 );
                        char max=tentativeOrdi.charAt( k * 2 + 1 );
                        String strMin=String.valueOf( min );
                        String strMax=String.valueOf( max );
                        int intMin=Integer.valueOf( strMin );
                        int intMax=Integer.valueOf( strMax );

                        char caracPropOrdi=propositionOrdi.charAt( k );//recupération d'un carac (carac par carac) dans la proposition de l'ordi;
                        //   System.out.println( "caracPropOrdi = " + caracPropOrdi );//debug
                        String strCaracPropOrdi=String.valueOf( caracPropOrdi );
                        //   System.out.println( "strCaracPropOrdi = " + strCaracPropOrdi );//debug
                        //   String strCaracReponseJoueur=String.valueOf( caracReponseJoueur );
                        int intCaracPropOrdi=Integer.valueOf( strCaracPropOrdi );
                        //   int intCaracReponseJoueur=Integer.valueOf( strCaracReponseJoueur ); bon exemple: à ne pas mettre parce que comme ce n'est pas un chiffre on ne peut pas le
                        //convertir en valeur numérique (en int)

                        char caracReponseJoueur=reponseJoueur.charAt( k );

                        System.out.println( "intCaracPropOrdi = " + intCaracPropOrdi );//debug
                        System.out.println( "intMax = " + intMax );//debug
                        System.out.println( "intMin = " + intMin );//debug sur les variables utilisées
                        switch (caracReponseJoueur) {
                            case '+':
                                intCaracPropOrdi=intCaracPropOrdi + 1;
                                System.out.println( "dans + intCaracPropOrdi = " + intCaracPropOrdi );//debug
                                modifTentativeOrdi+=Integer.toString( intCaracPropOrdi ) + Integer.toString( intMax );
                                System.out.println( "dans + modifTentativeOrdi = " + modifTentativeOrdi );
                                break;
                            case '-':
                                intCaracPropOrdi=intCaracPropOrdi - 1;
                                System.out.println( "dans - intCaracPropOrdi = " + intCaracPropOrdi );//debug
                                modifTentativeOrdi+=Integer.toString( intMin ) + Integer.toString( intCaracPropOrdi );
                                break;
                            case '=':
                                modifTentativeOrdi+=Integer.toString( intCaracPropOrdi ) + Integer.toString( intCaracPropOrdi ); //on place la même valeur intCaracPropOrdi (car =) en min et max;
                                break;
                        }
                    }
                    tentativeOrdi=modifTentativeOrdi;
                    System.out.println( "tentative ordi après modif : " + tentativeOrdi );
                }


                System.out.println( "propositionJoueur: " + propositionJoueur );
                System.out.println( "cleSecreteOrdi: " + cleSecreteOrdi );

                modifPropositionOrdi="";
                for (int k=0; k < tailleCle; k++) {// boucle qui va générer la propositionOrdi et comparer la proposition du joueur et la cleSecrete de l'ordi
                    // generation proposition ordi
                    char min=tentativeOrdi.charAt( k * 2 ); //récupère le min dans tentative [0]9090909 pour k=0
                    char max=tentativeOrdi.charAt( k * 2 + 1 );//récupère le min dans tentative 0[9]090909 pour k=0
                    ////récupère le min dans tentative 0909[0]909 pour k=2
                    //convertir les char en int mais pour ça d'abord convertir les char en int;
                    //parce que la conversion de char directement en int nous renvoie des paires de chiffres;
                    String strMin=String.valueOf( min );
                    String strMax=String.valueOf( max );
                    int intMin=Integer.valueOf( strMin );
                    int intMax=Integer.valueOf( strMax );

                    modifPropositionOrdi=modifPropositionOrdi + Integer.toString( getRandomNumberInRange( intMin, intMax ) );//TODO: min plus grand que max

                    //comparaison proposition joueur et cléSecreteOrdi

                    char caracPropJoueur=propositionJoueur.charAt( k );//String qui va permettre l'extraction caractere par caractere de la propositionJoueur;
                    // propositopn : [5]468 avec crochet à la position k=0; et ainsi de suite (carac par carac)
                    char caracCleSecreteOrdi=cleSecreteOrdi.charAt( k );//String qui va permettre l'extraction caractere par caractere de la propositionOrdi;

                    //Maudits char à retransformer en string pour pouvoir ensuite transformer les string en int
                    String strCaracPropJoueur=String.valueOf( caracPropJoueur );
                    String strCaracCleSecreteOrdi=String.valueOf( caracCleSecreteOrdi );
                    int intCaracPropJoueur=Integer.valueOf( strCaracPropJoueur );
                    int intCaracCleSecreteOrdi=Integer.valueOf( strCaracCleSecreteOrdi );
                    //L'intérêt d'avoir converti en int est de pouvoir utiliser les opérateurs <, >, = dans la structure conditionnelle ci-dessous (qui ne passait pas sans cette conversion);

                        /*System.out.println( "intCaracPropJoueur = " + intCaracPropJoueur );//affichage variable pour debug
                        System.out.println( "intCaracCleSecreteOrdi= " + intCaracCleSecreteOrdi );//affichage variable debug*/

                    if (intCaracPropJoueur < intCaracCleSecreteOrdi) {//comparer les carac recupérés ci dessus. Si +; si -; si =
                        reponseOrdi+="+"; //revient à faire reponseOrdi = reponseOrdi + "+" ("+" étant l'indice)
                    } else if (intCaracPropJoueur > intCaracCleSecreteOrdi) {
                        reponseOrdi+="-";
                    } else {
                        reponseOrdi+="=";

                    }

                }

                //   propositionOrdi=cleSecreteJoueur;// pour tester victoire Ordinateur //##################################"
                if (propositionOrdi.equals( cleSecreteJoueur )) {
                    winner="ordi";
                    System.out.println( "afficher winner " + winner );
                }
                if (propositionJoueur.equals( cleSecreteOrdi )) {
                    winner=winner + "joueur";//si le joueur a trouve la cleSecreteOrdi, on ajoute à winner le texte joueur. Concaténation car possible que
                    //l'ordi ait également gagné précedemment dans le code. on définit donc soit une victoire du joueur, soit une egalite
                }
                if (j == 5 && winner == "") {//si on est au dernier tour et qu'il n'y a pas de gagnant//prise en compte de la valeur du case "nbEssaiEpuise"
                    winner="nbEssaiEpuise";// afin de pouvoir afficher par la suite que le nombre d'essais est epuise
                }

                switch (winner) {
                    case "ordi":
                        System.out.println( "Perdu, l'ordinateur a gagné en " + (j + 1) + "/6 essais" );
                        endGame=true;
                        break;
                    case "joueur":
                        System.out.println( "Bravo! C'est gagné en " + (j + 1) + "/6 essais" );
                        endGame=true;
                        break;
                    case "ordijoueur":
                        System.out.println( "Egalité! En " + (j + 1) + "/6 essais" );
                        endGame=true;
                        break;
                    case "nbEssaiEpuise":
                        System.out.println( "Too bad! Le nombre d'essais est épuisé!" );
                        endGame=true;
                        break;
                    default:// si pas de gagnant il fait le traitement du tour suivant
                        propositionOrdi=modifPropositionOrdi;

                        System.out.println( "winner: " + winner );
                        System.out.println( "reponseOrdi: " + reponseOrdi ); //tour joueur
                        System.out.println( " Round n°: " + (j + 1) + "/6" );
                        System.out.println( "Tour de l'ordinateur" );
                        System.out.println( "PropositionOrdi: " + propositionOrdi ); //fin tour joueur et Debut tour de l'ordinateur
                        System.out.println( "cleSecreteJoueur:" + cleSecreteJoueur );
                        System.out.println( " Veuillez saisir  votre reponseJoueur:    " );
                        reponseJoueur=sc.nextLine();

                }
                if (endGame == true) {
                    break;//condition d'arrêt de jeu
                }
            }

        }
    }



           /* private int minGreaterThanMaxException ( int min){
                System.out.println( "min is greater than max" );
                return min;
            }*/


    private int getRandomNumberInRange(int min, int max) {
      //  min=0;
      //  max=0;
        if (min > max) {
            throw new IllegalArgumentException( "max must be greater than min [" + min + "/" + max + "]" );
        }
        if (min == max) {// gestion des cas ou min = max =>plus d'intervalle
            return max;
        } else {
            Random r=new Random();
            int n=min + r.nextInt( max - min + 1 );
            return n;
        }
    }


    public boolean isIntValid(String inputUser){
        boolean test=false;
        try{
            Integer.valueOf(inputUser);
            test=true;
            //tentative de conversion de la valeur donnée par l'utilisateur, ne fonctionnera que si c'est une valeur numérique
            // exemple, "grut", "a" ect... ne passeront pas le test.

        } catch(NumberFormatException e) {
            System.err.println( "This is not a number: " + e);
            System.out.println( "Please enter again");
        }
        return test;
    }

    public boolean testIntInputUser(String inputUser,int min,int max){

        boolean testReponse=false;
        if(min>max) {
                /*
                 test  de la cohérence du min max imposé dans le test:
                 si je veux vérifier que l'utilisateur entre bien une valeur dans l'intervale proposé.
                 exemple: le menu choix de 1 à 4, éviter un mauvais choix utilisateur de 0 ou 5 ou +

                 ici je vérifie juste que mon intervalle donné à la méthode testIntInputUser, est cohérent pour les test qui suiveront
                */
            throw new IllegalArgumentException( "max must be greater than min [" + min + "/" + max + "]" );
        }
       try{
            int input=Integer.valueOf(inputUser);
                //tentative de conversion de la valeur donnée par l'utilisateur, ne fonctionnera que si c'est une valeur numérique
                // exemple, "grut", "a" ect... ne passeront pas le test.
            if(input<min || input>max){
                /*
                    ici test effectif de la valeur donnée par l'utilisateur avec l'intervale des possibles transmis en arguments de la méthode.
                 */
                throw new IllegalArgumentException("Please, respect number interval");
            }
        } catch(InputMismatchException e) {
            System.err.println( "This is not a number: " + e);
            System.out.println( "Please enter again");
        }
       /*finally{
           testReponse=true;
       }*/
       return testReponse;
    }
    /*
    public boolean testIndiceInputUser(String input,String cleSecrete){
       try {
            String input=String.valueOf( inputUser );
        }catch(InputMismatchException e1){
            System.err.println("This is not ")
        }


    }
    */
}




