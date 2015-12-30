
public class UnionFind {
	
	int[] up;
	int[] weight;
	int numOfSets;
	
	/**
	 * A constructor creating two arrays:
	 * 1. up array - the array that implements the up-tree ADT (each index of
	 * the array points to its leader (representative), when -1 means the 
	 * current index is the leader)
	 * 2. weight array - the array the "holds" the weight of each tree in the 
	 * leaders indexes
	 * @param numElements - the number of elements
	 */
	public UnionFind (int numElements) {

		// Initializing both arrays. 
		up = new int[numElements];
		weight = new int[numElements];
		
		// When starting: each element stands alone (it is its own leader)
		// so its weight is 1 and its leader is itself (marked by -1)
		for (int i = 0; i < numElements; i++) {
			up[i] = -1;
			weight[i] = 1;
		}

		// Initialize the variable that holds the number of sets accordingly
		numOfSets = numElements;
	}
	
	/**
	 * unites the sets that contain i and j, or does nothing if they are already 
	 * in the same set. i and j must be the representatives of their sets. 
	 * The method uses the weighted union methodology.
	 * @param i - the leader of one of the groups the be unioned
	 * @param j - the leader of the second group to be unioned
	 */
	public void union (int i, int j) {
		
		// If they're already in the same set, break process
		if (i == j) {
			return;
		}
		
		// first make sure the indexes are leaders (and they're not part of the
		// same set)
		if (up[i] == -1 && up[j] == -1) {
			
			// Creates the combined weight of the new set
			int combinedWeight = weight[i] + weight[j];
			
			// Checks which set is bigger and attach the smaller one to it (and
			// update the weight).
			if (weight[i] < weight[j]) {
				up[i] = j;
				weight[j] = combinedWeight;
			} else {
				up[j] = i;
				weight[i] = combinedWeight;
			}
		}
		
		// update the number of sets
		numOfSets--;
	}
	
	/**
	 * returns the representative of the set that contains i, and applied path 
	 * compression to the traversed path.
	 * @param i - element to be found
	 * @return
	 */
	public int find (int i) {
		int leader = i;
		
		//Simple find the leader loop
		while (up[leader] != -1) {
			leader = up[leader];
		}
		
		// In case the element isn't the leader, start path compression
		if (i != leader) {
			
			// Save i's up-tree
			int element = up[i];
			
			// Connect all the elements in the path directly to the leader
			while (element != leader) {
				
				// Connect to leader
				up[i] = leader;
				
				// Move to the next element up-tree
				i = element;
				element = up[element];
			}
		}
		return leader;
	}
	
	/**
	 * returns the current number of sets
	 * @return numOfSets the current number of sets
	 */
	public int getNumSets() {
		return numOfSets;
		
	}
}
