package com.example.fryderyk.receiptcarer.Model;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import static org.junit.Assert.*;

public class ReceiptModelTest {

    /**
     * Obiekt ten służy jako podstawa do testowania metod validacyjnych w klasie ReceiptModel
     */
    private ReceiptModel baseReceipt;

    @Before
    public void start(){
        baseReceipt = new ReceiptModel("Valid name",
                1,
                1,
                "01/01/2015",
                "10.2",
                "Valid notes");
    }

    @Category(ReceiptModel.class)
    @Test
    public void checkNotes() {
//        Base Test
        assertFalse(baseReceipt.checkNotes());

        ReceiptModel receipt = new ReceiptModel(baseReceipt);

//        Is Null
        receipt.setReceipt_notes(null);
        assertTrue(receipt.checkNotes());

//        Is Empty
        receipt.setReceipt_notes("");
        assertTrue(receipt.checkNotes());

//        Is Valid
        receipt.setReceipt_notes("Interesting notes");
        assertFalse(receipt.checkNotes());
    }

    @Category(ReceiptModel.class)
    @Test
    public void checkDateFormat() {
//        Base Test
        assertTrue(baseReceipt.checkDateFormat());

        ReceiptModel receipt = new ReceiptModel(baseReceipt);

//        Is Null
        receipt.setReceipt_date_of_purchase(null);
        assertFalse(receipt.checkDateFormat());

//        Is Empty
        receipt.setReceipt_date_of_purchase("");
        assertFalse(receipt.checkDateFormat());

//        Is Correct Format ( 00/00/0000 - 99/99/9999 )
        receipt.setReceipt_date_of_purchase("02/1/1994");
        assertFalse(receipt.checkDateFormat());

        receipt.setReceipt_date_of_purchase("2/01/1994");
        assertFalse(receipt.checkDateFormat());

        receipt.setReceipt_date_of_purchase("02/01/14");
        assertFalse(receipt.checkDateFormat());

        receipt.setReceipt_date_of_purchase("02.01.1994");
        assertFalse(receipt.checkDateFormat());

        receipt.setReceipt_date_of_purchase("1994/02/02");
        assertFalse(receipt.checkDateFormat());

        receipt.setReceipt_date_of_purchase("02/01/124");
        assertFalse(receipt.checkDateFormat());

//        Is Possible Date
        // False
        receipt.setReceipt_date_of_purchase("50/50/1994");
        assertFalse(receipt.checkDateFormat());

        receipt.setReceipt_date_of_purchase("52/12/1999");
        assertFalse(receipt.checkDateFormat());

        receipt.setReceipt_date_of_purchase("01/52/1999");
        assertFalse(receipt.checkDateFormat());

        // True
        receipt.setReceipt_date_of_purchase("01/01/2019");
        assertTrue(receipt.checkDateFormat());

        receipt.setReceipt_date_of_purchase("01/10/2019");
        assertTrue(receipt.checkDateFormat());

        receipt.setReceipt_date_of_purchase("10/01/2019");
        assertTrue(receipt.checkDateFormat());

        receipt.setReceipt_date_of_purchase("01/01/9999");
        assertTrue(receipt.checkDateFormat());
    }

    @Category(ReceiptModel.class)
    @Test
    public void checkPriceFormat() {
//        Base Test
        assertTrue(baseReceipt.checkPriceFormat());

        ReceiptModel receipt = new ReceiptModel(baseReceipt);

//        Is Null
        receipt.setReceipt_price(null);
        assertFalse(receipt.checkPriceFormat());

//        Is Empty
        receipt.setReceipt_price("");
        assertFalse(receipt.checkPriceFormat());

//        Is Invalid
        receipt.setReceipt_price("12,2");
        assertFalse(receipt.checkPriceFormat());

        receipt.setReceipt_price("dwanaście piędziesiąc zł");
        assertFalse(receipt.checkPriceFormat());

        receipt.setReceipt_price("14.4 zł");
        assertFalse(receipt.checkPriceFormat());

        receipt.setReceipt_price("32. 32");
        assertFalse(receipt.checkPriceFormat());

//        Is Valid
        receipt.setReceipt_price("10.2");
        assertTrue(receipt.checkPriceFormat());

        receipt.setReceipt_price("   10.2    ");
        assertTrue(receipt.checkPriceFormat());

        receipt.setReceipt_price("10");
        assertTrue(receipt.checkPriceFormat());

        receipt.setReceipt_price("0");
        assertTrue(receipt.checkPriceFormat());
    }

    @Category(ReceiptModel.class)
    @Test
    public void validateData() {
//        Base Test
        assertEquals(ReceiptModel.VALIDATION_OK, baseReceipt.validateData());

        ReceiptModel receipt = new ReceiptModel(baseReceipt);

//        Data Of Pursuing Test
        receipt.setReceipt_date_of_purchase("421-231-205");
        assertEquals(ReceiptModel.VALIDATION_WRONG_RECEIPT_DATA_OF_PURCHASE, receipt.validateData());

//        Name Test
        receipt = new ReceiptModel(baseReceipt);
        receipt.setReceipt_name(null);
        assertEquals(ReceiptModel.VALIDATION_WRONG_RECEIPT_NAME, receipt.validateData());

//        Notes Test
        receipt = new ReceiptModel(baseReceipt);
        receipt.setReceipt_notes(null);
        assertEquals(ReceiptModel.VALIDATION_WRONG_RECEIPT_NOTES, receipt.validateData());

//        Price Test
        receipt = new ReceiptModel(baseReceipt);
        receipt.setReceipt_price(null);
        assertEquals(ReceiptModel.VALIDATION_WRONG_RECEIPT_PRICE, receipt.validateData());
    }


}