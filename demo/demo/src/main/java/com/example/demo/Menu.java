package com.example.demo;

import com.example.demo.services.ActionService;
import com.example.demo.services.ComedyService;
import com.example.demo.services.DramaService;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.util.Scanner;

@Component
public class Menu implements CommandLineRunner {

    private final ComedyService comedyService;
    private final ActionService actionService;
    private final DramaService dramaService;
    private final Scanner teclado = new Scanner(System.in);

    public Menu(ComedyService comedyService, ActionService actionService, DramaService dramaService) {
        this.comedyService = comedyService;
        this.actionService = actionService;
        this.dramaService = dramaService;
    }

    @Override
    public void run(String... args) throws Exception {
        int opcion = -1;
        
        while (opcion != 0) {
            System.out.println("\n--- SISTEMA DE CONSULTA TVMAZE ---");
            System.out.println("1. Ver Comedia");
            System.out.println("2. Ver Acción");
            System.out.println("3. Ver Drama");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");

            try {
                opcion = Integer.parseInt(teclado.nextLine());

                switch (opcion) {
                    case 1:
                        System.out.println("\nCargando series de comedia...");
                        comedyService.getComedyShowsList(0).forEach(s -> 
                            System.out.println("- " + s.name() + " (" + s.language() + ")"));

                        System.out.println("\n Cargando Top 5 de Comedia...");
                        comedyService.getTopRankingList(0).forEach(s -> 
                            System.out.println("- " + s.name() + " | Rating: " + s.rating().average()));

                        System.out.println("\nCargando series recientes...");
                        comedyService.getRecientComedyList(0).forEach(s -> 
                            System.out.println("- " + s.name() + " | Estreno: " + s.premiered()));
                        break;
                    case 2:
                        System.out.println("\nCargando series de comedia...");
                        actionService.get20EnglishActionShows().forEach(s -> 
                            System.out.println("- " + s.name() + " (" + s.language() + ")"));

                        System.out.println("\n Cargando Top 5 de Comedia...");
                        actionService.getTop5BestEvaluatedActionShows().forEach(s -> 
                            System.out.println("- " + s.name() + " | Rating: " + s.rating().average()));

                        System.out.println("\nCargando series recientes...");
                        actionService.getActionShowsFrom2014().forEach(s -> 
                            System.out.println("- " + s.name() + " | Estreno: " + s.premiered()));
                        break;
                    case 3:
                        System.out.println("\nCargando series de comedia...");
                        dramaService.get20EnglishDramaShows().forEach(s -> 
                            System.out.println("- " + s.name() + " (" + s.language() + ")"));

                        System.out.println("\n Cargando Top 5 de Comedia...");
                        dramaService.getTop5BestEvaluatedDramaShows().forEach(s -> 
                            System.out.println("- " + s.name() + " | Rating: " + s.rating().average()));

                        System.out.println("\nCargando series recientes...");
                        dramaService.getDramaShowsPriorTo2014().forEach(s -> 
                            System.out.println("- " + s.name() + " | Estreno: " + s.premiered()));
                        break;
                    case 0:
                        System.out.println("\nSaliendo del sistema...");
                        break;
                    default:
                        System.out.println("Opción no válida.");
                }
            } catch (Exception e) {
                System.out.println("Error: Por favor ingrese un número válido.");
            }
        }
    }
}