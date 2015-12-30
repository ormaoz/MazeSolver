import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class UnionFindTest {
	UnionFind uf;
	
	@Before
	/**
	 * Before
	 */
	public void Setup() {
		uf = new UnionFind(10);
		
	}
	
	@After
	/**
	 * After
	 */
	public void CleanUp() {
		uf = null;
		
	}
	
	/**
	 * Test  the union
	 */
	@Test
	public void TestUnion() {
		uf.union(1, 2);
		assertEquals(uf.up[2], 1);
		uf.union(1, 3);
		assertEquals(uf.up[3], 1);
		uf.union(2, 3);
		assertEquals(uf.up[2], 1);
		uf.union(4, 3);
		assertEquals(uf.up[3], 1);
		uf.union(4, 5);
		assertEquals(uf.up[5], 4);
		uf.union(1, 4);
		assertEquals(uf.up[4], 1);
		uf.union(9, 8);
		assertEquals(uf.up[8], 9);
		uf.union(9, 1);
		assertEquals(uf.up[9], 1);		
	}
	
	/**
	 * Test  the find
	 */
	@Test
	public void TestFind() {
		
		assertEquals(uf.find(1), 1);
		uf.union(1, 2);
		assertEquals(uf.find(2), 1);
		uf.union(3, 2);
		assertEquals(uf.find(3), 3);
		uf.union(3, 1);
		uf.union(4, 1);
		uf.union(6, 5);
		uf.union(7, 6);
		uf.union(1, 6);
		assertEquals(uf.find(7), 1);
		
		
			
	}
	
}
