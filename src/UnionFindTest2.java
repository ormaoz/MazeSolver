import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class UnionFindTest2 {
	UnionFind uf;

	@Before
	public void init() {
		uf = new UnionFind(10);
	}

	@Test
	public void initTest() {
		for (int i = 0; i < 10; i++) {
			assertEquals(i, uf.find(i));
		}
	}

	@Test
	public void unionTest() {
		uf.union(1, 2);
		int lead1 = uf.find(1);
		// Assert union worked
		assertEquals(lead1, uf.find(2));
		assertEquals(9, uf.getNumSets());

		// Assert union takes the representative with higher weight.
		uf.union(lead1, 3);
		assertEquals(lead1, uf.find(3));
		// Assert size was changed
		assertEquals(8, uf.getNumSets());

		// Unite 4-10
		uf.union(4, 5);
		int lead2 = uf.find(4);
		for (int i = 5; i < 10; i++) {
			uf.union(lead2, i);
		}
		assertEquals(2, uf.getNumSets());

		// Unite all elements 1-10 to a single set
		uf.union(lead1, lead2);
		assertEquals(1, uf.getNumSets());
		assertEquals(lead2, uf.find(lead1));
	}

	@Test
	public void unionCompressTest() {
		final int SIZE = 1024 * 1024 * 4;
		uf = new UnionFind(SIZE);

		for (int step = 2; step < SIZE; step *= 2) {
			for (int i = 1; i < SIZE; i += step) {
				uf.union(uf.find(i), uf.find(i + step - 1));
			}
			assertEquals(SIZE / step, uf.getNumSets());
		}
		assertEquals(uf.find(1),uf.find(SIZE));
	}
	
}
