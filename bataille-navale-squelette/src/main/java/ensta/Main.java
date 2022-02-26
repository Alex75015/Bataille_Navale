package ensta;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import ensta.ai.BattleShipsAI;
import ensta.controller.Game;
import ensta.model.Board;
import ensta.model.Coords;
import ensta.model.Hit;
import ensta.model.ship.AbstractShip;
import ensta.model.ship.BattleShip;
import ensta.model.ship.Carrier;
import ensta.model.ship.Destroyer;
import ensta.model.ship.Submarine;
import ensta.util.ColorUtil;

public class Main {

    public static void main(String args[]) 
    {   
        boolean done = false;
        char choice;
        System.out.println("Voulez-vous jouer en Solo ou en Multijoueur (S/M) : ");
        do{
            Scanner sin = new Scanner(System.in);
            choice = sin.next().toLowerCase().charAt(0);
            if(choice == 's' || choice == 'm' ){
                done = true;
            }
            else{
                System.err.println("Veuillez répondre par S pour Solo ou M pour Multijoueur !");
            }
        }while(!done);
        

        Game jeu = new Game();

        switch(choice){
            case 's':
                jeu.init();
                jeu.run();
                break;
            case 'm':
                jeu.initMultijoueur();
                jeu.runMultijoueur();
                break;
            default :
                jeu.init();
                jeu.run();
        }
        
        System.out.println("Fin de la partie");
    } 


}

