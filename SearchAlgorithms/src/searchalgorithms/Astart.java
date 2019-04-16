/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package searchalgorithms;

import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Stack;

/**
 *
 * @author Mehdi Raza Rajani
 */
public class Astart {
    
    static  class astarNode implements Comparable<astarNode> {
        Integer index;
        Integer totalCost;
        
        public astarNode(Integer index, Integer totalCost) {
            this.index = index;
            this.totalCost = totalCost;
        }
        
        @Override
        public boolean equals(Object o) {
        if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            astarNode n = (astarNode) o;
            return Integer.compare(n.totalCost, totalCost) == 0 && Integer.compare(n.index, index) == 0;
        }

        @Override
        public int hashCode() {
            int hash = 5;
            hash = 73 * hash + Objects.hashCode(this.index);
            hash = 73 * hash + Objects.hashCode(this.totalCost);
            return hash;
        }
        
        @Override
        public int compareTo(astarNode n) {
            if(this.totalCost > n.totalCost) {
                return 1;
            } else if (this.totalCost < n.totalCost) {
                return -1;
            } else {
                return 0;
            }
        }

        @Override
        public String toString() {
            return "inedx: "+ index + " totalcost: " + totalCost; //To change body of generated methods, choose Tools | Templates.
        }

    }
        
    public static void search(Integer[][] graph, Integer size, Integer start, Integer destination, state[] s_list){
        if (Objects.equals(start, destination)) {
            System.out.println("All ready at goal");
            return; 
        }
        
        Boolean[] isVisited = new Boolean[size];
        PriorityQueue<astarNode> frontier = new PriorityQueue<>();
        Integer[] parent = new Integer[size];
        Integer[] distance = new Integer[size];
        for (int i = 0; i < size; i++) {
            isVisited[i] = false;
            parent[i] = -2;
            distance[i] = 0;
        }
        frontier.add(new astarNode(start, s_list[start].heuristic));
        parent[start] = -1;
        distance[start] = 0;

        while(true){
            if (frontier.isEmpty()) {
                System.out.println("(A* Search): No such path exists.");
                break;
            }
            isVisited[frontier.peek().index] = true;
            Integer par = frontier.peek().index;
            for (int i = 0; i < size; i++) {
                if (graph[par][i] != 0){
                    Integer chilDistance = graph[par][i];
                    if (!isVisited[i]){
                        parent[i] = par;
                        distance[i] = distance[par] + chilDistance;
                        frontier.add(new astarNode(i, distance[i]+ s_list[i].heuristic));
                    }
                }
            }
            if (Objects.equals(frontier.peek().index, destination)) {
                System.out.println("The traversal route of A* search from start to end will be as following:");
                Stack<Integer> backTracker = new Stack(); //backtracking using parent array
                backTracker.add(frontier.peek().index);
                while(parent[backTracker.peek()] != -1 ){
                    backTracker.add(parent[backTracker.peek()]);
                }
                while (!backTracker.isEmpty()) {                                
                    System.out.print(backTracker.pop() + ", ");
                }
                System.out.println("\nTotal Cost: " + distance[frontier.remove().index]);
                return;
            } else {
                frontier.remove();
            }
        }
    }

    
}