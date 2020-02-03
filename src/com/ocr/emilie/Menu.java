package com.ocr.emilie;

import java.util.Scanner;

public class Menu {


    public int afficherMenu() {
        System.out.println( "Bienvenue" ); //Affiche "Bienvenue" à l'écran
        String nomUtilisateur = "Saisir un nom d'utilisateur"; // La chaine de caractere String nomUtilisateur va stocker le pseudo
        System.out.println( nomUtilisateur );//Affichage du pseudo
        Scanner sc = new Scanner( System.in ); //sc va stocker l'entree clavier qui est recuperee par new Scanner(System.in)
        String str = sc.nextLine(); //Récupère la saisie clavier
        System.out.println( "Vous avez saisi: " + str ); //Affiche "Vous avez saisi + resultat saisie clavier
        System.out.println( "Choix du mode de jeu: 1 - Challenger" ); // Affiche "Choix du mode de jeu:"
        System.out.println( "1 - Challenger" ); // Affiche: 1-Challenger
        System.out.println( "2 - Défenseur" ); // Affiche: 2- Défenseur
        System.out.println( "3 - Duel" ); // Affiche 3- Duel
        System.out.println( "4 - Quitter" ); // Affiche 4 - Quitter
        System.out.println( "Saisissez un mode de jeu" ); // Affiche "Saisissez un mode de jeu"
        int i = sc.nextInt(); // Récupère la saisie clavier du joueur
        displaySelectedGamingMode(i);
        return i;
    }

    public void displaySelectedGamingMode(int nbGamingMode) {
        if (nbGamingMode == 1) { // Si le joueur choisit le mode de jeu numero 1
            System.out.println( "Vous avez choisi le mode de jeu: Challenger" ); // Le choix du mode de jeu 1-Challenger s'affiche

               /* String Response = "";
                System.out.println("Votre proposition : ");
                Scanner scanner = new Scanner(System.in);
                String proposition = scanner.nextLine();
                String cleSecrete= "";
                System.out.println( "cleSecrete: ");
                cleSecrete=scanner.nextLine();*/



                /*for (int i = 0; i < cleSecrete.length(); i++) {
                    if ((int) cleSecrete.charAt(i) > (int) proposition.charAt(i)) {
                        Response += "+";
                        System.out.println((cleSecrete) + "Proposition : " + proposition + " -> Réponse : " + Response );
                    } else if ((int) cleSecrete.charAt(i) < (int) proposition.charAt(i)) {
                        Response += "-";
                        System.out.println("Proposition : " + proposition + " -> Réponse : " + Response);
                    } else {
                        Response += "=";
                        System.out.println( "Bravo, vous avez trouvé la cle secrete!" );
                    }

                }
                System.out.println("cle secrete: "+ cleSecrete + " proposition : " + proposition + " -> Réponse : " + Response );*/
            Game challenger = new Game();
            challenger.launchGame( nbGamingMode );



        } else if (nbGamingMode == 2) { //Si le joueur choisit le mode de jeu numero 2
            System.out.println( "Vous avez choisi le mode de jeu: Défenseur" ); // Le choix du mode de jeu 2-Defenseur s'affiche
            Game defenseur = new Game();
            defenseur.launchGame( nbGamingMode );

        } else if (nbGamingMode == 3) { // Si le joueur choisit le mode de jeu numero 3
            System.out.println( "Vous avez choisi le mode de jeu: Duel" ); // Le choix du mode de jeu 3-Duel s'affiche
          /*  System.out.println( ("Veuillez saisir la taille de la cle avec laquelle vous souhaitez jouer: ") ); // Affichage du choix de la taille de la combinaison de jeu: 2, 4 ou 6
            Scanner sc = new Scanner( System.in ); // Le Scanner permet de récupérer la saisie clavier
            int str = sc.nextInt(); //la string str va stocker l'entier (int) saisi par le joueur (sc: scanner récupère nextInt() à savoir la saisie du joueur
           */
            Game duel = new Game();
            duel.launchGame( nbGamingMode );
        } else if (nbGamingMode == 4) { // Si le joueur choisit le numero 4-Quitter
            System.out.println( "Merci d'avoir joué. Au revoir et à très bientôt!" ); //Le message "Merci d'avoir joué. Au revoir et à très bientôt s'affiche
            Game quit = new Game();
            quit.launchGame( nbGamingMode );
        } else { // Au cas où le joueur a clique sans avoir saisi de mode de jeu ou s'il saisit un autre nombre que celui proposé dans le menu
            System.out.println( "Vous n'avez pas choisi de mode de jeu parmi les choix proposes" ); //Le message "Vous n'avez pas choisi..." s'affiche
        }

    }

    public void restartGame(int nbGamingMode){
        Scanner sc = new Scanner( System.in );
        System.out.println("Voulez-vous rejouer ce mode de jeu? : " + " Oui / Non" );
        String rejouer=sc.nextLine();
        if(rejouer.equals("oui"))
        {
            System.out.println("Nouvelle partie: ");
            this.displaySelectedGamingMode(nbGamingMode);
        }
        else if(rejouer.equals("non")) {
            this.afficherMenu();
        }
    }

    /*private void InputUser(String proposition) {
    }*/

}
