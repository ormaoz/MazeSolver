import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class SegmentationTest2 {
	Segmentation seg;

	@Test
	public void testSeg() {
		for (int i = 2; i <= 8; i++) {
			seg = new Segmentation("C:\\maze\\maze" + i + ".PNG");
			assertEquals("Bad solution for image: " + i, i%2==1?true:false, seg.mazeHasSolution());
			if(i != 6) {
				assertEquals("Bad segmentation for image: " + i,3, seg.getNumComponents());
			} else if(i == 6) {
				assertEquals("Bad segmentation for image: " + i,4, seg.getNumComponents());
			}
		}
	}
}
