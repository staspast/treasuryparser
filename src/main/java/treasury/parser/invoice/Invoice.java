package treasury.parser.invoice;

import java.util.List;

public class Invoice {
    private int indexBegin;
    private int indexEnd;
    private String text;
    private String account;
    private int sortIndex;

    public Invoice(int indexBegin, int indexEnd, String account) {
        this.indexBegin = indexBegin;
        this.indexEnd = indexEnd;
        this.account = account;
    }

    public Invoice(int indexBegin, int indexEnd) {
        this.indexBegin = indexBegin;
        this.indexEnd = indexEnd;
    }

    public int getIndexBegin() {
        return indexBegin;
    }

    public int getIndexEnd() {
        return indexEnd;
    }

    public String getText() {
        return text;
    }

    public String getOutputText(){
        return text + "\n" + text;
    }

    public void setText(List<String> accountList) {
        this.text = "";
        accountList.subList(indexBegin, indexEnd).forEach(line -> this.text += line + "\n");
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public int getSortIndex() {
        return sortIndex;
    }

    public void setSortIndex(int sortIndex) {
        this.sortIndex = sortIndex;
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "indexBegin=" + indexBegin +
                ", indexEnd=" + indexEnd +
                ", account=" + account +
                '}';
    }
}
