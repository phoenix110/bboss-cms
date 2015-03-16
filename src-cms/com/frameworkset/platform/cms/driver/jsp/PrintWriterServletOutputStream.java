package com.frameworkset.platform.cms.driver.jsp;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletOutputStream;



public class PrintWriterServletOutputStream extends ServletOutputStream implements java.io.Serializable {

	
	
	 /**
     * The PrintWriter that is wrapped on top of the base input stream
     */
    PrintWriter mPrintWriter;

    /**
     * Construct a ServletOutputStream that coordinates output using a base
     * ServletOutputStream and a PrintWriter that is wrapped on top of that
     * OutputStream.
     */
    public PrintWriterServletOutputStream(PrintWriter pO) {
        super();
        
        mPrintWriter = pO;
    }

    /**
     * Writes an array of bytes
     * @param pBuf the array to be written
     * @throws IOException if an I/O error occurred
     */
    public void write(byte[] pBuf) throws IOException {
//        char[] cbuf = new char[pBuf.length];
//        for (int i = 0; i < cbuf.length; i++) {
//            cbuf[i] = (char) (pBuf[i] & 0xff);
//        }
        //FIXED weblogic 采用以下方式，中文字符会多出空格
//    	ByteToCharGBK s = new ByteToCharGBK();
//    	int pLength = pBuf.length;
//        char[] cbuf = new char[pLength];
//        s.convert(pBuf,0,pLength,cbuf,0,pLength);
//        mPrintWriter.write(cbuf, 0, cbuf.length);
        String cbuf = new String(pBuf,"UTF-8");
        mPrintWriter.write(cbuf);
        
    }

    /**
     * Writes a single byte to the output stream
     */
    public void write(int pVal) throws IOException {
        mPrintWriter.write(pVal);
    }

    /**
     * Writes a subarray of bytes
     * @param pBuf    the array to be written
     * @param pOffset the offset into the array
     * @param pLength the number of bytes to write
     * @throws IOException if an I/O error occurred
     */
    public void write(byte[] pBuf, int pOffset, int pLength)
        throws IOException {
        //FIXED weblogic 采用以下方式，中文字符会多出空格
//    	ByteToCharGBK s = new ByteToCharGBK();
//    	
//        char[] cbuf = new char[pLength];
//        s.convert(pBuf,pOffset,pLength,cbuf,pOffset,pLength);
////        for (int i = 0; i < pLength; i++) {
////            cbuf[i] = (char) (pBuf[i + pOffset] & 0xff);
////        }
//        mPrintWriter.write(cbuf);
        String cbuf = new String(pBuf,pOffset,pLength,"utf-8");
        mPrintWriter.write(cbuf);
    }

    /**
     * Flushes the stream, writing any buffered output bytes
     * @throws IOException if an I/O error occurred
     */
    public void flush() throws IOException {
        mPrintWriter.flush();
    }

    /**
     * Closes the stream
     * @throws IOException if an I/O error occurred
     */
    public void close() throws IOException {
        mPrintWriter.close();
    }

    /**
     * Prints a string.
     * @param pVal the String to be printed
     * @throws IOException if an I/O error has occurred
     */
    public void print(String pVal) throws IOException {
        mPrintWriter.print(pVal);
    }

    /**
     * Prints an string followed by a CRLF.
     * @param pVal the String to be printed
     * @throws IOException if an I/O error has occurred
     */
    public void println(String pVal) throws IOException {
        mPrintWriter.println(pVal);
    }

    /**
     * Prints a CRLF
     * @throws IOException if an I/O error has occurred
     */
    public void println() throws IOException {
        mPrintWriter.println();
    }


}
