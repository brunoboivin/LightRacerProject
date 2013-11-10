package GridPkg;

import java.io.File;

//import java.util.Vector;

public class Grid 
{

  public int difficulty;

  public int width;

  public int length;


    /**
   * 
   * @element-type GridCell
   */
  

  public Grid ( int difficulty ,  int width,  int length ) 
  {
  this.difficulty=difficulty;
  this.width=width;
  this.length=length;
  }

  public Grid( File file) 
  {
	  this.difficulty=0;
			  
	  
  }
  
  

}