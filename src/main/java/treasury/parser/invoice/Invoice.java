package treasury.parser.invoice;

import java.util.List;

public class Invoice {

    private int indexBegin;
    private int indexEnd;
    private String text;

    public Invoice(int indexBegin, int indexEnd, String text) {
        this.indexBegin = indexBegin;
        this.indexEnd = indexEnd;
        this.text = text;
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

    public void setText(List<String> accountList) {
        this.text = "";
        accountList.subList(indexBegin, indexEnd).forEach(line -> this.text += line);
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "indexBegin=" + indexBegin +
                ", indexEnd=" + indexEnd +
                ", text='" + text + '\'' +
                '}';
    }
}
