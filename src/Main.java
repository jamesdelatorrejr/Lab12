import jdk.nashorn.internal.objects.NativeError;
import sun.security.pkcs11.wrapper.CK_CREATEMUTEX;

import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

import static java.nio.file.StandardOpenOption.CREATE;

public class Main
{
    public static void main(String[] args)
    {
        JFileChooser chooser = new JFileChooser();
        File selectedFile;
        String rec = "";

        try
        {
            int words = 0;
            int chars = 0;
            int line = 0;

            File workingDirectory = new File(System.getProperty("user.dir"));

            chooser.setCurrentDirectory(workingDirectory);

            if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
            {
                selectedFile = chooser.getSelectedFile();
                Path file = selectedFile.toPath();

                InputStream in =
                        new BufferedInputStream(Files.newInputStream(file, CREATE));
                BufferedReader reader =
                        new BufferedReader(new InputStreamReader(in));

                line = 0;
                while (reader.ready())
                {
                    rec = reader.readLine();
                    line++;
                    words = rec.split("\\s").length + words;
                    chars = rec.length() + chars;
                    System.out.printf("\nLine %4d %-60s ", line, rec);
                }
                reader.close();

                System.out.println("\nThis file is named " + file + " and has " + line + " lines, " + words + " words, and " + chars + " characters.");
            }
        }
        catch (FileNotFoundException e)
        {
            System.out.println("File not found");
        }
        catch (IOException e) 
        {
            System.out.println("Error");
        }

    }
}