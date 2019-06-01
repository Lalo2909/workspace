import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class DividirArchivo {
	private static String  FILEPATH = "C:\\Users\\edmoe\\OneDrive\\Escritorio\\Prueba\\MMMCA_D02_20190219_ac_ma_efictes.txt";
	private static int NUMFILES = 3;
	private static int SIZEFILE = 135;
	
	public static void main(String[] args) throws IOException {
		splitFile(new File(FILEPATH));
		//List<File> arrayList = listOfFilesToMerge(new File(FILEPATH));
	}
	
	public static void splitFile(File f) throws IOException {
        int partCounter = 1;
        int sizeOfFile = (int)f.length()/NUMFILES;  
        String fileName = f.getName();
        sizeOfFile /= SIZEFILE;
        sizeOfFile *= SIZEFILE;
        
        ArrayList<byte[]> arr = new ArrayList<>(NUMFILES);

        for(int i = 0; i < NUMFILES; i++) {
        	if(i == NUMFILES-1) {
        		arr.add(new byte[(int)f.length()-sizeOfFile*i]);
        	}else{
        		arr.add(new byte[sizeOfFile]);
        	}
        }

        try (FileInputStream fis = new FileInputStream(f);
             BufferedInputStream bis = new BufferedInputStream(fis)) {
            int bytesAmount = 0;
            for(int i = 0; i < NUMFILES; i++) {
            	bytesAmount = bis.read(arr.get(i));
                String filePartName = String.format("%s%03d.%s", fileName.substring(0, fileName.length()-4),partCounter++,"txt");
                File newFile = new File(f.getParent(), filePartName);
                
                try (FileOutputStream out = new FileOutputStream(newFile)) {
                    out.write(arr.get(i), 0, bytesAmount);
                }
            }
        }
    }
	/*
	public static List<File> listOfFilesToMerge(File oneOfFiles) {
	    String tmpName = oneOfFiles.getName();//{name}.{number}
	    String destFileName = tmpName.substring(0, tmpName.lastIndexOf('.'));//remove .{number}
	    File[] files = oneOfFiles.getParentFile().listFiles(
	            (File dir, String name) -> name.matches(destFileName + "[.]\\d+"));
	    Arrays.sort(files);//ensuring order 001, 002, ..., 010, ...
	    return Arrays.asList(files);
	}
	*/
}
