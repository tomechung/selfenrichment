/* 
----------------------------------------------------------------
Djikstra's algorithm for single source shortest path (SSSP)
----------------------------------------------------------------
This algorithm explicitly avoids the decrease-key operation for 
priority queues, so it's performance may vary to comparable 
Djikstra's algorithm implementations. This is due to the fact 
that the priority queue could potentially be populated by many 
unnecessary nodes. However, asymptotic performance should be
comparable.
----------------------------------------------------------------
*/

import java.util.PriorityQueue;
import java.util.HashSet;
import java.util.Set;
import java.util.Arrays;

public class Djikstra
{
	// Node class implementing the Comparable interface which is used by PriorityQueue
	// for ordering operations
	public static class Node implements Comparable<Node>
	{
		// Instance variables
		private int name;         // Node name (an integer)
		private double distance;  // Total distance from source

		// Constructor
		Node(int x, double y)
		{
			this.name = x;
			this.distance = y;
		}

		// Add a mechanism for sorting (needed to implement Comparable)
        public int compareTo(Node other) 
		{
	    	if(this.distance < other.distance)
	    	{
	        	return -1;
	    	}
	    	else if(this.distance > other.distance)
	    	{
	        	return 1;
	    	}
	    	return 0;
    	}

		// Instance methods
		public int getName()
		{
			return this.name;
		}

		public double getDistance()
		{
			return this.distance;
		}

		public void setName(int x)
		{
			this.name = x;
		}

		public void setDistance(double x)
		{
			this.distance = x;
		}
	}

	// Function to run Djikstra's algorithm
	public static double[] djikstraSSSP(int[][] adjNode, double[][] adjWeight, int source)
	{	
		// Initialization
		int len = adjNode.length;                    // The number of nodes
		int[] prev = new int[len];                   // Array for holding previous nodes
		double[] dist = new double[len];             // Array for holding finalized distances
		Arrays.fill(dist, Double.POSITIVE_INFINITY); // Fill dist with positive infinities

		// Initialize a set to keep track of visited nodes
		Set<Integer> visited = new HashSet<Integer>();

		// Initialize a PriorityQueue using edge weights for comparison
		PriorityQueue<Node> nodeHeap = new PriorityQueue<Node>();

		// Add the source node to the PriorityQueue
		nodeHeap.add(new Node(source, 0.0));

		// While at least one node has not yet been visited
		while(visited.size() < len)
		{
			// Pop the node with minimum distance and also get its name
			Node u = nodeHeap.poll();  
			int v = u.getName();

			// If the popped node is not already visited
			if(!visited.contains(v))
			{
				visited.add(v);                 // Add to visited
				dist[v] = u.getDistance();      // Record the final distance
				int[] vrowV = adjNode[v];       // Get neighbor nodes
				double[] vrowW = adjWeight[v];  // Get neighbor weights

				// Iterate through each neighboring node of current node v
				for(int i = 0; i < vrowV.length; i++)
				{
					double alt = dist[v] + vrowW[i]; // Calculate new distance

					// If both alt is shorter than current distance & neighbor is not already visited
					if(alt < dist[vrowV[i]])
					{
						nodeHeap.add(new Node(vrowV[i], alt));
						prev[vrowV[i]] = v;
					}
				}
			}
		}
		// Return the final distances
		return dist;
	}

	// Main function
	public static void main(String[] args)
	{
		// Example data (neighboring vertices and respective weights)
		//// e.g., Node 0 has nodes 1 and 7 as neighbors
		int[][] adjNode = {{1, 7}, 
		                   {0, 2, 7}, 
		                   {1, 3, 5, 8}, 
		                   {2, 4, 5}, 
		                   {3, 5}, 
		                   {2, 3, 4, 6}, 
		                   {5, 7, 8}, 
		                   {0, 1, 6, 8}, 
		                   {2, 6, 7}};
		//// e.g., Weight is 4 and 8 between node 0 and 1, and 0 & 7, respectively
        double[][] adjWeight = {{4.0, 8.0}, 
                                {4.0, 8.0, 11.0}, 
                                {8.0, 7.0, 4.0, 2.0},
                                {7.0, 9.0, 14.0}, 
                                {9.0, 10.0}, 
                                {4.0, 14.0, 10.0, 2.0},
                                {2.0, 1.0, 6.0}, 
                                {8.0, 11.0, 1.0, 7.0}, 
                                {2.0, 6.0, 7.0}};

        // Run the Djikstra function and save output to an array (source is 0)
        double[] output = djikstraSSSP(adjNode, adjWeight, 0);

        // Print the contents of the output
        System.out.println(Arrays.toString(output));
	}
}


