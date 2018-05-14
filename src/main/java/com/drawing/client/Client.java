package com.drawing.client;

import com.drawing.command.Command;
import com.drawing.command.CommandException;
import com.drawing.command.Invoker;

import java.util.Scanner;

public class Client {

    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            Invoker invoker = null;
            invoker = new Invoker();
            Input input = new Input();

            do{
                try {
                    System.out.print("enter command: ");
                    String line = scanner.nextLine();
                    input = Input.transform(line);
                    Command cmd = invoker.lookUpCommand(input.getAction());
                    cmd.setParams(input.getParameters());
                    cmd.execute();
                } catch (CommandException e) {
                    System.out.println(e.getMessage());
                }

            } while (!input.getAction().equals(Invoker.QUIT));
        } catch (CommandException e) {
            System.out.println(e.getMessage());
        }
    }
}
