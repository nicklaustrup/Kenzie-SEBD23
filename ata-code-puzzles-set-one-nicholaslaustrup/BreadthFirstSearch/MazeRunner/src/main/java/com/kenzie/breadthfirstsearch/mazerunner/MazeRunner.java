package com.kenzie.breadthfirstsearch.mazerunner;

import com.amazonaws.services.dynamodbv2.xspec.M;
import com.kenzie.breadthfirstsearch.mazerunner.model.MazePattern;
import com.kenzie.breadthfirstsearch.mazerunner.model.MazeSpace;
import com.kenzie.breadthfirstsearch.mazerunner.sharedmodel.Node;
import com.kenzie.breadthfirstsearch.mazerunner.utils.MazeGenerator;

import javax.swing.text.html.Option;
import java.util.*;

import static com.kenzie.breadthfirstsearch.mazerunner.SampleMazes.MAZE_ONE_EXIT;

/**
 * Class for running through mazes.
 */
public class MazeRunner {

    /**
     * Private constructor, as all methods are static.
     */
    private MazeRunner() {}

    /**
     * Utility main method, to run MazeRunner methods without adding tests.
     *
     * @param args - Method arguments (unused)
     */
    public static void main(String[] args) {
        System.out.println(MazeRunner.findClosestExit(MAZE_ONE_EXIT));
    }

    /**
     * Finds the exit out of the maze closest to its entrance.
     *
     * @param pattern - the pattern of maze being run through
     * @return the closest reachable exit to the maze, or empty if there are no reachable exits
     */
    public static Optional<MazeSpace> findClosestExit(MazePattern pattern) {
        Optional<Node<MazeSpace>> entrance = MazeGenerator.generateMaze(pattern);
        Set<Node<MazeSpace>> visitedNodes = new HashSet<>();
        Queue<Node<MazeSpace>> nodesToVisit = new LinkedList<>();

        // Select a point of origin and add this as the first
//           node to visit
        entrance.ifPresent(nodesToVisit::add);
//        while nodesToVisit is not empty:
        while(!nodesToVisit.isEmpty()) {
            // Retrieve the next node from the list of nodes
            // to visit
            Node<MazeSpace> currentNode = nodesToVisit.poll();

            // Mark the current node as travelled
            visitedNodes.add(currentNode);

            // Compare your current node against the condition
            // you want to check
            if (currentNode.getValue().isExit()) {
                return Optional.of(currentNode.getValue());
            } else {
                // Add the non-visited nodes connected to your
                // current node to the end of the queue of nodes
                // to visit
                for (Node<MazeSpace> neighbor : currentNode.getNeighbors()) {
                    if (!visitedNodes.contains(neighbor)) {
                        nodesToVisit.add(neighbor);
                    }
                }
            }
        }
// If the list of nodes is empty, return an empty
// value and stop.
        return Optional.empty();
    }

    /**
     * Finds the path to the exit out of the maze closest to its entrance.
     *
     * @param pattern - the pattern of maze being run through
     * @return the path closest reachable exit to the maze, or an empty list if there are no reachable exits
     */
    public static List<MazeSpace> findShortestPathToExit(MazePattern pattern) {
        Optional<Node<MazeSpace>> entrance = MazeGenerator.generateMaze(pattern);
        Set<Node<MazeSpace>> visitedNodes = new HashSet<>();
        Queue<List<Node<MazeSpace>>> currentLevel = new LinkedList<>();
        Queue<List<Node<MazeSpace>>> nextLevel = new LinkedList<>();

// Select a point of origin and add this as the first
// node to visit
        List<Node<MazeSpace>> currentPath = new LinkedList<>();
        if (entrance.isPresent()){
            currentPath.add(entrance.get());
        } else if (entrance.isEmpty()) {
            return Collections.emptyList();
        }
        currentLevel.add(currentPath);

//        while currentLevel is not empty
        while(!currentLevel.isEmpty()) {
            // Retrieve the next path from the list of paths
            // to visit

//        currentPath = next path in pathsToVisit
//        currentNode = get last node from currentPath
            currentPath = currentLevel.poll();
            Node<MazeSpace> currentNode = currentPath.get(currentPath.size() -1);

            // Mark the current node as travelled
            visitedNodes.add(currentNode);

            // Compare your current node against the condition
            // you want to check

                //if the current node is the exit
                if (currentNode.getValue().isExit()) {
                    List<MazeSpace> solution = new ArrayList<>();
                    //loop over the current path adding the mazespaces to the solution and return it
                    for (Node<MazeSpace> nodePath : currentPath) {
                        solution.add(nodePath.getValue());
                    }
                    return solution;
                } else {
                    // Add the non-visited nodes connected to your
                    // current node to the end of the queue of nodes
                    // to visit
                    for (Node<MazeSpace> neighbor : currentNode.getNeighbors()) {
                        if (!visitedNodes.contains(neighbor)) {
                            List<Node<MazeSpace>> newPath = new ArrayList<>(currentPath);
                            newPath.add(neighbor);
                            nextLevel.add(newPath);
                        }
                    }
                }

            if (currentLevel.isEmpty()){
                currentLevel.addAll(nextLevel);
                nextLevel.clear();
            }
        }
// If the list of nodes is empty, return an empty
// value and stop.
        return Collections.emptyList();
    }
}
