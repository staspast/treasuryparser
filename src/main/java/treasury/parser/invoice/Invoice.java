package treasury.parser.invoice;

public class Invoice {

    private int indexBegin;
    private int indexEnd;

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
}
