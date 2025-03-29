package com.kenzie.breadthfirstsearch.countingislands;

import com.kenzie.breadthfirstsearch.countingislands.sharedmodel.Coordinate;
import com.kenzie.breadthfirstsearch.countingislands.sharedmodel.Direction;
import com.kenzie.breadthfirstsearch.countingislands.sharedmodel.Node;

import java.util.*;

/**
 * Counts the number of islands for a map.
 */
public class IslandCounter {
    private final int width;
    private final int height;
    private final int[][]map;

    public IslandCounter(int width, int height, int[][] map) {
        this.width = width;
        this.height = height;
        this.map = map;
    }

    /**
     * Main method for manual testing.
     * @param args - unused
     */
    public static void main(String[] args) {
        IslandCounter islandCounter = new IslandCounter(5, 5, SamplesMaps.FIVE_ISLAND_MAP);
        int islandCount = islandCounter.countIslands();
        System.out.println(String.format("Found %s islands.", islandCount));
    }

    /**
     * Counts the number of islands for the map.
     * @return the number of islands for the map.
     */
    public int countIslands() {
        Set<Coordinate> visitedCoordinates = new HashSet<>();
        Queue<Coordinate> coordinatesToVisit = new LinkedList<>();
        int islandCounter = 0;

        // Select a point of origin and add this as the first
//           node to visit

//        while nodesToVisit is not empty:
        for (int i = 0; i < this.width; i++) {
            for (int j = 0; j < this.height; j++) {
                Coordinate coordinate = new Coordinate(i, j);
                Node<Coordinate> node = new Node<>(coordinate);

               if (map[i][j] == 1 && !visitedCoordinates.contains(coordinate)) {
                   coordinatesToVisit.add(node.getValue());
                       while (!coordinatesToVisit.isEmpty()) {
                           // Retrieve the next node from the list of nodes
                           // to visit
                           Coordinate currentCoordinate = coordinatesToVisit.poll();
                           visitedCoordinates.add(currentCoordinate);

                           // Compare your current node against the condition
                           // you want to check
                           // Add the non-visited nodes connected to your
                           // current node to the end of the queue of nodes
                           // to visit
                           List<Coordinate> landNeighbor = new ArrayList<>();
                           for (Direction direction : Direction.ALL_DIRECTIONS) {
                               Coordinate neighbor = direction.addToCoordinate(currentCoordinate);
                               if (!visitedCoordinates.contains(neighbor) && !coordinatesToVisit.contains(neighbor)) {
                                   if (neighbor.getColumn() >= 0 && neighbor.getColumn() < height && neighbor.getRow() >= 0 && neighbor.getRow() < width) {
                                       if (map[neighbor.getRow()][neighbor.getColumn()] == 1) {
                                           landNeighbor.add(neighbor);
                                       }
                                   }
                               }
                           }
                           for (Coordinate neighbor: landNeighbor) {
                               if (!visitedCoordinates.contains(neighbor) && !coordinatesToVisit.contains(neighbor)){
                                   coordinatesToVisit.add(neighbor);
                               }
                           }
                           landNeighbor.clear();
                       }
                       islandCounter++;
                   }
               }
            }
// If the list of nodes is empty, return an empty
// value and stop.
        return islandCounter;
    }

}
