package com.drawing.client;

import com.drawing.command.Command;
import com.drawing.command.CommandException;
import com.drawing.command.Invoker;

import java.util.Scanner;
import java.util.StringTokenizer;

public class Client {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StringTokenizer st = new StringTokenizer(scanner.next(), " ");
        Invoker invoker = new Invoker();

        while(st.hasMoreElements()){
            try {
                Command cmd = invoker.lookUpCommand(st.nextToken());
                cmd.execute();
            } catch (CommandException e) {
                e.printStackTrace();
            }
        }
    }
}
