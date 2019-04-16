/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package searchalgorithms;

import java.util.Scanner;

/**
 *
 * @author Mehdi Raza Rajani
 */
public class SearchAlgorithms {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       
        Integer size = 20;
        state[] s_list = new state[size];
        for (int i = 0; i < size; i++) {
            s_list[i] = new state();
        }
        s_list[0].index = 0;    s_list[0].name = "Arad";    s_list[0].heuristic = 366;
        s_list[1].index = 1;    s_list[1].name = "Bucharest";    s_list[1].heuristic = 0;
        s_list[2].index = 2;    s_list[2].name = "Craiova";    s_list[2].heuristic = 160;
        s_list[3].index = 3;    s_list[3].name = "Drobeta";    s_list[3].heuristic = 242;
        s_list[4].index = 4;    s_list[4].name = "Eforie";    s_list[4].heuristic = 161;
        s_list[5].index = 5;    s_list[5].name = "Fagaras";    s_list[5].heuristic = 176;
        s_list[6].index = 6;    s_list[6].name = "Giurgiu";    s_list[6].heuristic = 77;
        s_list[7].index = 7;    s_list[7].name = "Hirsova";    s_list[7].heuristic = 151;
        s_list[8].index = 8;    s_list[8].name = "Iasi";    s_list[8].heuristic = 226;
        s_list[9].index = 9;    s_list[9].name = "Lugoj";    s_list[9].heuristic = 244;
        s_list[10].index = 10;    s_list[10].name = "Mehadia";    s_list[10].heuristic = 241;
        s_list[11].index = 11;    s_list[11].name = "Neamt";    s_list[11].heuristic = 234;
        s_list[12].index = 12;    s_list[12].name = "Oradea";    s_list[12].heuristic = 380;
        s_list[13].index = 13;    s_list[13].name = "Pitesti";    s_list[13].heuristic = 100;
        s_list[14].index = 14;    s_list[14].name = "Rimnicu-Vilcea";    s_list[14].heuristic = 193;
        s_list[15].index = 15;    s_list[15].name = "Sibiu";    s_list[15].heuristic = 253;
        s_list[16].index = 16;    s_list[16].name = "Timisoara";    s_list[16].heuristic = 329;
        s_list[17].index = 17;    s_list[17].name = "Urziceni";    s_list[17].heuristic = 80;
        s_list[18].index = 18;    s_list[18].name = "Vaslui";    s_list[18].heuristic = 199;
        s_list[19].index = 19;    s_list[19].name = "Zerind";    s_list[19].heuristic = 374;        
        
        Integer[][] graph = new Integer[20][20];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                graph[i][j] = 0;
            }
        }
        graph[0][19] = 75;  graph[0][16] = 118;     graph[0][15] = 140;
        graph[1][5] = 211;  graph[1][13] = 101;     graph[1][6] = 90;       graph[1][17] = 85;
        graph[2][3] = 120;  graph[2][14] = 146;     graph[2][13] = 138;
        graph[3][2] = 120;  graph[3][10] = 75;
        graph[4][7] = 86;
        graph[5][15] = 99;  graph[5][1] = 211;
        graph[6][1] = 90;
        graph[7][4] = 86;   graph[7][17] = 98;
        graph[8][11] = 87;  graph[8][18] = 92;
        graph[9][16] = 111; graph[9][10] = 70;
        graph[10][9] = 70;  graph[10][3] = 75;
        graph[11][8] = 87;
        graph[12][19] = 71; graph[12][15] = 151;
        graph[13][1] = 101; graph[13][14] = 97; graph[13][2] = 138;
        graph[14][13] = 97; graph[14][2] = 146; graph[14][15] = 80;
        graph[15][14] = 80; graph[15][0] = 140; graph[15][12] = 151;    graph[15][5] = 99;
        graph[16][0] = 118; graph[16][9] = 111;
        graph[17][1] = 85;  graph[17][18] = 142;    graph[17][7] = 98;
        graph[18][17] = 142;    graph[18][8] = 92;
        graph[19][0] = 75;  graph[19][12] = 71;
        
        System.out.println("List of cities");
        for (int i = 0; i < size; i++) {
            System.out.println(s_list[i].name);
        }
        
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the starting city");
        String start = sc.next();
        System.out.println("Enter the destination city");
        String dest = sc.next();
        
        Integer st = -1 , ds = -1 ;
        for (int i = 0; i < size; i++) {
            if (start.toLowerCase().equals(s_list[i].name.toLowerCase())){
                st = i;
            }
            if (dest.toLowerCase().equals(s_list[i].name.toLowerCase())) {
                ds = i;
            }
            if (st != -1 && ds != -1) {
                long a = System.currentTimeMillis();
                BFS.search(graph, size, st, ds,s_list);
                long b = System.currentTimeMillis() - a;
                System.out.println("Execution time for BFS is " + b + " msec");
                a = System.currentTimeMillis();
                DFS.search(graph, size, st, ds,s_list);
                b = System.currentTimeMillis() - a;
                System.out.println("Execution time for DFS is " + b + " msec");
                a = System.currentTimeMillis();
                Astart.search(graph, size, st, ds, s_list);
                b = System.currentTimeMillis() - a;
                System.out.println("Execution time for A* Search is " + b + "msec");
                return;
            }
        }
        if (st == -1 && ds == -1) {
            System.out.println("Invalid starting and destination city entered.");
        }
        else if (st == -1) {
            System.out.println("Invalid starting city entered.");
        }
        else if (ds == -1) {
            System.out.println("Invalid destination city entered.");
        }        
    }
    
}
