package views;

import datastructures.Dictionary;
import models.commands.AbstractCommand;

import java.util.Scanner;

public class TextMenu {

    private Dictionary<String, AbstractCommand> commands;

    public TextMenu() {
        this.commands = new Dictionary<>();
    }

    public void addCommand(AbstractCommand command) {
        this.commands.put(command.getKey(), command);
    }

    private void printMenu() {
        for(AbstractCommand com : commands.values()){
            String line = String.format("%4s : %s", com.getKey(), com.getDescription());
            System.out.println(line);
        }
    }

    public void show(){
        Scanner input = new Scanner(System.in);

        while(true) {
            printMenu();
            System.out.println("Input the option: ");
            String key = input.nextLine();

            AbstractCommand command = this.commands.get(key);
            if(command != null) {
                command.execute();
            } else {
                System.out.println("Invalid command!");
            }
        }
    }


}
