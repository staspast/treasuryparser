package treasury.parser.processor;

import com.linuxense.javadbf.DBFReader;
import treasury.parser.processor.file.ProcessedDbfFile;
import treasury.parser.processor.file.ProcessedFile;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class DbfFileProcessor extends FileProcessor{

    @Override
    public ProcessedFile processFile(Path filePath) {

        List<String> accounts = new ArrayList<>();
        try {
            DBFReader dbfReader = new DBFReader(new FileInputStream(filePath.toFile()), Charset.forName("windows-1251"));
            Object[] rowObjects;
            while ((rowObjects = dbfReader.nextRecord()) != null) {
                String account = (String) rowObjects[2];
                if(!account.equals("") && !accounts.contains(account)){
                    accounts.add(account);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return new ProcessedDbfFile(accounts);
    }
}
