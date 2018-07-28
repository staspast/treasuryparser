package treasury.parser.processor.file;

import java.util.ArrayList;
import java.util.List;

public class ProcessedDbfFile implements ProcessedFile {

    private List<String> sortedAccounts;

    public ProcessedDbfFile(List<String> sortedAccounts) {
        this.sortedAccounts = sortedAccounts;
    }

    public List<Object> getContents() {
       return new ArrayList<>(sortedAccounts);
    }

    public List<String> getSortedAccounts() {
        return sortedAccounts;
    }

    public void setSortedAccounts(List<String> sortedAccounts) {
        this.sortedAccounts = sortedAccounts;
    }
}
