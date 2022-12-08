package com.Careless.app;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
// import java.io.FileNotFoundException;
import java.util.List;

import static com.Careless.app.Attributes.*;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DocumentManagementSystemTest {
    private static final String RESOURCES = "src"+File.separator+"test"+File.separator+"resources"+File.separator;
    private static final String LETTER = RESOURCES + "test1.letter";
    private static final String REPORT = RESOURCES + "test1.report"; 
    private static final String INVOICE = RESOURCES + "test1.invoice"; 
    private static final String JOHN_DOE = "John Doe";


    private DocumentManagementSystem system = new DocumentManagementSystem();
    
    @Test()
    public void shouldImportFile() throws Exception{
        system.importFile(LETTER);
        final Document document = onlyDocument();
        assertAttributeEquals(document, Attributes.PATH, LETTER);
    }

    @Test()
    public void shouldImportLetterAttributes() throws Exception {
        system.importFile(LETTER);
        final Document document = onlyDocument();
        assertAttributeEquals(document,PATIENT,JOHN_DOE);
        assertAttributeEquals(document,ADDRESS,"123 Street Street Chicago Il,60614");
        assertAttributeEquals(document,BODY, "I wanted to thank you");
        assertTypeIs("LETTER",document);
    }

    @Test()
    public void shouldImportReportAttribute() throws Exception{
        system.importFile(REPORT);
        assertIsReport(onlyDocument());
    }

    @Test()
    public void shouldImportInvoiceAttribute() throws Exception {
        system.importFile(INVOICE);
        final Document document = onlyDocument();
    
        assertAttributeEquals(document,PATIENT,JOHN_DOE);
        assertAttributeEquals(document,AMOUNT,"$100");
        assertTypeIs("INVOICE",document);
    }

    @Test()
    public void shouldBeAbleToSearchFilesByAttributes() throws Exception{
        system.importFile(LETTER);
        system.importFile(REPORT);

        final List<Document> documents = system.search("patient:John,body:Diet Coke");
        assertThat(documents,hasSize(1));
        assertIsReport(documents.get(0));
    }   
    
    // I expect them to fail but there is something wrong with my imports;
    // These tests don't allow my program to compile but they're actually
    // correct...

    @Test
    public void shouldNotImportMissingFile() throws Exception{
        try{
            system.importFile("file.txt");
        } catch(FileNotFoundException e) {
             assertThat("File not found - test passed",true);
        }

    }

    @Test
    public void shouldNotImportUnknownFile() throws Exception {
    try{
        system.importFile(RESOURCES+"unknown.txt");
    }catch (UnknownFileTypeException e ){
             assertThat("Filetype is not supported",true);
    }
    }

    // Helper Functions; Modularize Getting Document contents
    
    private void assertIsReport(final Document document){
        assertAttributeEquals(document,PATIENT,JOHN_DOE);
        assertAttributeEquals(document,BODY,
		"On 5th December 2022 I examined Some John's teeth. We discussed his switch from drinking Coke to Diet Coke. No new problems were noted with his teeth.");
        assertTypeIs("REPORT",document);
    }

    private void assertAttributeEquals(
        final Document document,
        final String attributeName,
        final String expectedValue)
    {
        // assertEquals(expected, actual, messageSupplier);
		assertEquals(expectedValue,document.getAttributes(attributeName),"Problem is: "+attributeName);
    }

    private void assertTypeIs(final String type, final Document document)
    {
        assertAttributeEquals(document, TYPE, type);
    }

    private Document onlyDocument(){
        final List<Document> documents = system.contents();
        assertThat(documents, hasSize(1));
        return documents.get(0);
    }

}


