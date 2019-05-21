package renamer;

import java.io.File;
import java.nio.file.Path;
import java.util.Scanner;


public class Renamer 
{
    private static String getFileExtension(File file)
    {
        String fileName = file.getName();
        String extension = "";
        
        int lastIndex = fileName.lastIndexOf('.');
        if (lastIndex != -1)
            extension = fileName.substring(lastIndex + 1);
        
        return extension;
    }
    
    private static void listFilesRecursive(File dir)
    {
        File[] fileList = dir.listFiles();
        for (File file : fileList)
        {
            if (file.isDirectory())
                listFilesRecursive(file);
            
            else
            {
                String extension = getFileExtension(file);
                
                if (extension.equals("java") || extension.equals("kt"))
                {
                    Path path = file.toPath();
                    File newFile = new File(path + ".2019");
                    
                    boolean renamed = file.renameTo(newFile);
                    if (renamed)
                        System.out.println("Renamed " + file.getName() + 
                                            " to " + newFile.getName());
                    else
                        System.err.println("Can't rename file " + path.toString());
                }
            }
        }
    }
    
    
    public static void main(String[] args) 
    {
        if (args[0].equals("--path"))
        {
            String path = args[1];
            
            File dir = new File(path);
        
            if (dir.isDirectory())
                listFilesRecursive(dir);
            
            else
                System.err.println("Path must point to a directory");
        }
        else
            System.err.println("Incorect arguments\nUse --path [directory path]");
        
        /*Scanner scanner = new Scanner(System.in);
        
        System.out.println("Enter directory path");
        String path = scanner.nextLine();*/
    }
    
}
