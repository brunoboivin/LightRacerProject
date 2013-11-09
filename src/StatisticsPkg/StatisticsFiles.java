package StatisticsPkg;

/** Enum used to list all files (filename and path) used in StatisticsPkg.
 * 
 * @author Bruno Boivin <bruno.boivin@mail.mcgill.ca>
 * @version 1.0
 * @since 2013-11-08
 */
enum StatisticsFiles {
	PLAYER_RECORDS("player_records.csv"),
	PAIR_RECORDS("pair_records.csv");
	
	private String filename;
	private String path;
	
	/**
	 * Constructor
	 * @param filename
	 */
	StatisticsFiles (String filename){
		this.filename = filename;
		this.path = "csv/" + filename;
	}
	
	//Getters
	/**
	 * @return filename
	 */
	public String getFilename(){
		return filename;
	}
	
	/**
	 * @return path to file
	 */
	public String getPath(){
		return path;
	}
}
