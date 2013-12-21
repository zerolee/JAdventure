package com.jadventure.game;

import com.jadventure.game.entities.Entity;
import com.jadventure.game.entities.Player;
import com.jadventure.game.monsters.Monster;
import com.jadventure.game.monsters.MonsterFactory;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * The main game class. This class contains the main loop that takes the input and
 * does the according actions.
 */
public class Game {
    
    public ArrayList<Monster> monsterList = new ArrayList<Monster>();
    public MonsterFactory monsterFactory = new MonsterFactory(); 
    public CommandParser parser = new CommandParser();
    public Scanner input = new Scanner(System.in);
    public Monster monster;
    Player player = null;

    public Game(Player player, String playerType) {
        if (playerType.equals("new")) {
            this.player = player;
            newGameStart(player);
        } else if (playerType.equals("old")) {
            this.player = player;
            System.out.println("Welcome back " + player.getName() + "!");
            System.out.println();
            player.getLocation().print();
            gamePrompt(player);
        } else {
            System.out.println("Invalid player type");
        }
    }
    
    /**
     * Starts a new game.
     * It prints the intro first and asks for the name of the players character
     * and welcomes him/her. After that it goes to the normal game prompt.
     */
    public void newGameStart(Player player) {
        System.out.println(player.getIntro());
        String userInput = input.next();
        player.setName(userInput);
        System.out.println("Welcome to Silliya " + this.player.getName() + ".");
        System.out.println();
        player.getLocation().print();
        
        gamePrompt(player);
    }

    /**
     * This is the main loop for the player-game interaction. It gets input from the
     * command line and checks if it is a recognised command.
     *
     * This keeps looping as long as the player didn't type an exit command.
     */
    public void gamePrompt(Player player) {
        boolean continuePrompt = true;
        while (continuePrompt) {
            System.out.println("Prompt:");
            String command = input.next().toLowerCase();
            continuePrompt = parser.parse(player, command, continuePrompt);
        }
    }

    // COMMANDS
    // Command is being used for debugging at this time
    // is this still used?
    private void attackStats(Entity player, Entity monster) {
        System.out.println("----------------------------------");
        System.out.println("p_damage: " + this.player.getDamage());
        System.out.println("m_armour: " + monster.getArmour());
        System.out.println("mh_before: " + monster.getArmour());
        System.out.println("mh_after: " + monster.getHealth());
        System.out.println("----------------------------------");
        System.out.println("----------------------------------");
        System.out.println("m_damage: " + monster.getDamage());
        System.out.println("p_armour: " + this.player.getArmour());
        System.out.println("ph_before: " + this.player.getHealth());
        System.out.println("ph_after: " + this.player.getHealth());
        System.out.println("----------------------------------");
    }
}
