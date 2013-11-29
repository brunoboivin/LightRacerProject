package GridPkg;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.junit.* ;

import static org.junit.Assert.* ;

public class Test_Grid {

	private Grid grid;
	private GridCell [][] gridCells;

	@Before
	public void initilaization (){
		this.grid = new Grid();
		this.gridCells = this.grid.getGridCells();
	}

	/* ------------------
	Tests for addObstacle
	------------------ */
		
	@Test
	public void test_addObstacles1 () {
		testEdge_addObstacles ("(0,0) (1,1)", 0, 49);
	}
	
	@Test
	public void test_addObstacles2 () {
		testEdge_addObstacles ("(75,50) (74,49)", 74, 0);
	}
	
	@Test
	public void test_addObstacles3 () {
		testEdge_addObstacles ("(75,0) (74,1)", 74, 49);
	}
	
	@Test
	public void test_addObstacles4 () {
		testEdge_addObstacles ("(0,50) (1,49)", 0, 0);
	}
	
	@Test
	public void test_addObstacles5 () {
		testInvalid_addObstacles ("(-1,-1) (0,0)", 0, 49);
	}
	
	@Test
	public void test_addObstacles6 () {
		testInvalid_addObstacles ("(76,51) (75,50)", 74, 0);
	}
	
	//True if edge obstacle coordinate is added correctly to grid
	public void testEdge_addObstacles (String coords, int x, int y) {
		String path = "tests/unit_tests/tmp.txt";
		File file = new File(path);

        try {
        BufferedWriter out = new BufferedWriter(new FileWriter(path));
			out.write(coords);
			out.newLine();
            out.close();
        } catch (IOException e) {}
        
		this.grid.addObstacles(path);
		
		assertTrue(this.grid.getGridCells()[x][y] == GridCell.Obstacle);
		file.delete();
	}
	
	//True if invalid obstacle coordinate is correctly left out of grid
	public void testInvalid_addObstacles (String coords, int x, int y) {
		String path = "tests/unit_tests/tmp.txt";
		File file = new File(path);

        try {
        BufferedWriter out = new BufferedWriter(new FileWriter(path));
			out.write(coords);
			out.newLine();
            out.close();
        } catch (IOException e) {}
        
		this.grid.addObstacles(path);
		
		assertTrue(this.grid.getGridCells()[x][y] == GridCell.Empty);
		file.delete();
	}
	
	/* ----------------
	Tests for resetGrid
	---------------- */
	
	@Test
	public void test_resetGrid1 (){
		test_resetGrid(8,8,GridCell.Obstacle, GridCell.Obstacle);
	}
	
	@Test
	public void test_resetGrid2 (){
		test_resetGrid(8,8,GridCell.Empty, GridCell.Empty);
	}
	
	@Test
	public void test_resetGrid3 (){
		test_resetGrid(8,8,GridCell.DarthVaderIcon, GridCell.Empty);
	}
	
	@Test
	public void test_resetGrid4 (){
		test_resetGrid(8,8,GridCell.GreenLight, GridCell.Empty);
	}
	
	@Test
	public void test_resetGrid5 (){
		test_resetGrid(8,8,GridCell.RedLight, GridCell.Empty);
	}
	
	@Test
	public void test_resetGrid7 (){
		test_resetGrid(8,8,GridCell.YodaIcon, GridCell.Empty);
	}
	
	//True if GridCell is correctly reset to Empty or Obstacle
	public void test_resetGrid (int x, int y, GridCell before, GridCell after){
		this.gridCells [x][y] = before;	
		Grid.resetGrid(this.gridCells);
		assertTrue(this.gridCells[x][y] == after);	
	}
}
