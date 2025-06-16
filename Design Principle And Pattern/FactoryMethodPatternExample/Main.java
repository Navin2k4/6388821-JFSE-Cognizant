
/*
 * A Factory Method defines an interface for creating an object,
 * but lets subclasses decide which class to instantiate.
 */

// When to Use 
/*
 * You want to decouple object creation from its usage.
 * You need to create related objects dynamically (e.g., based on user input, config, or external factors).
 * Adding new types should not affect existing code — follow Open/Closed Principle.
 */

public class Main {
    public static void main(String[] args) {
        DocumentFactory wordDocumentFactory = new WordDocumentFactory();
        DocumentFactory pdfDocumentFactory = new PdfDocumentFactory();
        DocumentFactory excelDocumentFactory = new ExcelDocumentFactory();

        Document wordDocument = wordDocumentFactory.createDocument();
        wordDocument.open();

        Document pdfDocument = pdfDocumentFactory.createDocument();
        pdfDocument.open();

        Document excelDocument = excelDocumentFactory.createDocument();
        excelDocument.open();

        /*
        Opening a Word Document
        Opening a Pdf Document
        Opening a Excel Document
         */
    }
}
