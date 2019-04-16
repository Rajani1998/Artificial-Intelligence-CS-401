/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package searchalgorithms;

import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;
import java.util.Stack;

/**
 *
 * @author Mehdi Raza Rajani
 */
public class BFS {
    
    public static void search(Integer[][] graph, Integer size, Integer start, Integer destination, state[] s_list){
        if (Objects.equals(start, destination)) {
            System.out.println("All ready at goal");
            return; 
        }
        Boolean[] isVisited = new Boolean[size];
        for (int i = 0; i < size; i++) {
            isVisited[i] = false;
        }
        Queue<Integer> frontier = new LinkedList<>();
        Integer[] parent = new Integer[size]; //used for backtracking
        for (int i = 0; i < size; i++) {
            parent[i] = -2;
        }
        frontier.add(start);
        parent[start] = -1; //-1 represents root node
        while(true){
            if (frontier.isEmpty()) {
                System.out.println("(BFS): No such path exists.");
                break;
            }
            isVisited[frontier.peek()] = true;
            int par = frontier.peek();
            for (int i = 0; i < size; i++) {
                if (graph[frontier.peek()][i] != 0){
                    if (!isVisited[i]){
                        frontier.add(i);
                        parent[i] = par;
                        if (Objects.equals(i, destination)) {
                            
                            System.out.println("The BFS traversal route from start to end will be as following:");
                            
                            Stack<Integer> backTracker = new Stack(); //backtracking using parent array
                            backTracker.add(i);
                            
                            while(parent[backTracker.peek()] != -1 ){
                                backTracker.add(parent[backTracker.peek()]);
                            }
                            Integer totalCost = 0;
                            while (!backTracker.isEmpty()) {                                
                                System.out.print(s_list[backTracker.peek()].name + ", ");
                                if (backTracker.size() > 1 ){
                                    totalCost += graph[backTracker.pop()][backTracker.peek()];
                                } else{
                                    backTracker.pop();
                                }
                            }
                            System.out.println();
                            System.out.println("Total Cost = " + totalCost);
                            return;
                        }
                    }
                }
            }
            frontier.remove();
        }
    }
    
}
