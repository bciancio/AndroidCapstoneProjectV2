//package com.bciancio.androidcapstoneprojectv2;
//
//
//
//import com.bciancio.androidcapstoneprojectv2.entity.Transactions;
//
//import javax.xml.bind.JAXBContext;
//import javax.xml.bind.JAXBException;
//import javax.xml.bind.Marshaller;
//import javax.xml.bind.PropertyException;
//import java.io.File;
//
///**
// * Created by Student on 11/3/2015.
// */
//public class JaxBMarshaller {
//
//    /**
//     * Creates xml file for all transactions
//     * @param transactions object includes a List of both Sold/Purchased transaction objects
//     */
//    public static void createAllTransactionsXML(Transactions transactions) {
//        try {
//            JAXBContext jaxbContext = JAXBContext.newInstance(Transactions.class);
//            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
//
//            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
//
//            //Marshal the transactions list in file
//            jaxbMarshaller.marshal(transactions, new File("allTransactions.xml"));
//            } catch (PropertyException e) {
//            e.printStackTrace();
//        } catch (JAXBException e) {
//            e.printStackTrace();
//        }
//    }
//}
