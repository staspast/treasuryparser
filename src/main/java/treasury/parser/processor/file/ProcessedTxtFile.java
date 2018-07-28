package treasury.parser.processor.file;

import treasury.parser.invoice.Invoice;

import java.util.ArrayList;
import java.util.List;

public class ProcessedTxtFile implements ProcessedFile {

    private List<Invoice> invoices;

    public ProcessedTxtFile(List<Invoice> invoices) {
        this.invoices = invoices;
    }

    public List<Object> getContents() {
        return new ArrayList<>(invoices);
    }

    public List<Invoice> getInvoices() {
        return invoices;
    }

    public void setInvoices(List<Invoice> invoices) {
        this.invoices = invoices;
    }
}
