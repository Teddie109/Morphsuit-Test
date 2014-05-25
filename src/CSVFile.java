import java.io.File;


public class CSVFile {

	private File filename;
	
	public CSVFile(String filename) {
		
		this.filename = new File(filename);
		
	}
	
	public File getFile() {
		return this.filename;
	}
	
}
