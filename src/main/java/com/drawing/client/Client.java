package com.drawing.client;

import com.drawing.command.Command;
import com.drawing.command.CommandException;
import com.drawing.command.Invoker;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Invoker invoker = new Invoker();
        Input input = new Input();

        do{
            try {
                String line = scanner.next();
                input = Input.transform(line);
                Command cmd = invoker.lookUpCommand(input.getAction());
                cmd.execute();
            } catch (CommandException e) {
                System.out.println(e.getMessage());
            }

        } while (!input.getAction().equals(Invoker.QUIT));
    }
}
