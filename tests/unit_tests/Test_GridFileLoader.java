package unit_tests;
import static org.junit.Assert.assertTrue;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.junit.*;

import GridPkg.GridFileLoader;

public class Test_GridFileLoader {
	
	private GridFileLoader gridFileLoader;

	@Before
	public void initialization (){
		this.gridFileLoader = new GridFileLoader();
	}
	
	@Test
	public void test_readFile1 (){
		test_readFile (1,2,3,4);	
	}
	
	@Test
	public void test_readFile2 (){
		test_readFile (4,3,2,1);	
	}
	
	@Test
	public void test_readFile3 (){
		test_readFile (0,0,0,0);	
	}
	
	@Test
	public void test_readFile4 (){
		test_readFile (-1,-2,-3,-4);	
	}
	
	@Test
	public void test_readFile5 (){
		test_readFile (88,88,88,88);	
	}
	
	//True if coordinates read from file are identical to expected chosen values
	public void test_readFile (int x1, int y1, int x2, int y2) {
		String path = "tests/unit_tests/tmp.txt";
		File file = new File(path);

        try {
        BufferedWriter out = new BufferedWriter(new FileWriter(path));
			out.write("(" + x1 + "," + y1 + ") " + "(" + x2 + "," + y2 +")");
			out.newLine();
            out.close();
        } catch (IOException e) {}
        
		int[] coords = null;
		try {
			coords = GridFileLoader.readFile(path);
		} catch (IOException e) {
			e.printStackTrace();
		}
	
		assertTrue (coords [0] == x1 && coords [1] == y1 &&
			coords [2] == x2 && coords [3] == y2);
		
		file.delete();
	}

}
