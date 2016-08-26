/*
 * File   : $Source: /usr/local/cvs/opencms/src/org/opencms/search/extractors/CmsExtractorMsExcel.java,v $
 * Date   : $Date: 2005/06/23 11:11:28 $
 * Version: $Revision: 1.8 $
 *
 * This library is part of OpenCms -
 * the Open Source Content Mananagement System
 *
 * Copyright (c) 2005 Alkacon Software GmbH (http://www.alkacon.com)
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * For further information about Alkacon Software GmbH, please see the
 * company website: http://www.alkacon.com
 *
 * For further information about OpenCms, please see the
 * project website: http://www.opencms.org
 * 
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */

package com.frameworkset.platform.cms.searchmanager.extractors;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Map;

import org.apache.poi.hssf.extractor.ExcelExtractor;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.extractor.XSSFExcelExtractor;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.frameworkset.platform.cms.driver.util.CmsStringUtil;
import com.frameworkset.platform.cms.searchmanager.handler.ContentHandler;

/**
 * Extracts the text form an MS Excel document.<p>
 * 
 * @author Alexander Kandzior 
 * 
 * @version $Revision: 1.8 $ 
 * 
 * @since 6.0.0 
 */
public final class CmsExtractorMsExcel extends A_CmsTextExtractorMsOfficeBase implements java.io.Serializable {

    /** Static member instance of the extractor. */
//    private static final CmsExtractorMsExcel INSTANCE = new CmsExtractorMsExcel();

    /**
     * Hide the public constructor.<p> 
     */
	public CmsExtractorMsExcel(String version) {
		this.version = version;
		
        // noop
    }

  
    /**
     * @see org.opencms.search.extractors.I_CmsTextExtractor#extractText(java.io.InputStream, java.lang.String)
     */
    public I_CmsExtractionResult extractText(InputStream in, String encoding) throws Exception {

        // first extract the table content
//        String result = extractTableContent(getStreamCopy(in));
//        result = removeControlChars(result);
    	String result = null;
    	Map metaInfo = null;
    	if(this.version == ContentHandler.VERSION_2003)
    	{
    		result = this.readExcel(in);
    		 metaInfo = extractMetaInformation();
    	}
    	else
    	{
    		result = readExcel2007(in);
    		 metaInfo = extractMetaInformation();
    	}
//        // now extract the meta information using POI 
//        POIFSReader reader = new POIFSReader();
//        reader.registerListener(this);
//        reader.read(getStreamCopy(in)); 
//        Map metaInfo = extractMetaInformation();
//        
//        //free some memory
        cleanup();
        
        // return the final result
        return new CmsExtractionResult(result, metaInfo);
    }
    
    /** 
         * 处理excel2003 
         * @param path 
         * @return 
         * @throws IOException 
         */  
        public String readExcel(InputStream in) throws IOException {  
           
            String content = null;  
            try {  
              
                HSSFWorkbook wb = new HSSFWorkbook(in);  
                ExcelExtractor extractor = new ExcelExtractor(wb);  
                extractor.setFormulasNotResults(true);  
               extractor.setIncludeSheetNames(false);  
               content = extractor.getText();  
               this.m_documentSummary = extractor.getDocSummaryInformation();
               this.m_summary = extractor.getSummaryInformation();
            } catch (FileNotFoundException e) {  
                e.printStackTrace();  
            }  
            return content;  
        }  
        /** 
            * 处理excel2007 
            * @param path 
             * @return 
             * @throws IOException 
            */  
           public String readExcel2007(InputStream in) throws IOException {  
//                StringBuffer content = new StringBuffer();  
               // 构造 XSSFWorkbook 对象，strPath 传入文件路径  
        	   String content = null;
                XSSFWorkbook xwb = new XSSFWorkbook(in);  
                XSSFExcelExtractor extractor = new XSSFExcelExtractor(xwb);
                extractor.setFormulasNotResults(true);  
                extractor.setIncludeSheetNames(false); 
                content =  extractor.getText();
                this.cp = extractor.getCoreProperties();
                return content;
//               // 循环工作表Sheet  
//               for (int numSheet = 0; numSheet < xwb.getNumberOfSheets(); numSheet++) {  
//                  XSSFSheet xSheet = xwb.getSheetAt(numSheet);  
//                    if (xSheet == null) {  
//                        continue;  
//                  }  
//                    // 循环行Row  
//                 for (int rowNum = 0; rowNum <= xSheet.getLastRowNum(); rowNum++) {  
//                       XSSFRow xRow = xSheet.getRow(rowNum);  
//                        if (xRow == null) {  
//                          continue;  
//                        }  
//                        // 循环列Cell  
//                        for (int cellNum = 0; cellNum <= xRow.getLastCellNum(); cellNum++) {  
//                            XSSFCell xCell = xRow.getCell(cellNum);  
//                            if (xCell == null) {  
//                                continue;  
//                           }  
//                         if (xCell.getCellType() == XSSFCell.CELL_TYPE_BOOLEAN) {  
//                                content.append(xCell.getBooleanCellValue());  
//                            } else if (xCell.getCellType() == XSSFCell.CELL_TYPE_NUMERIC) {  
//                                content.append(xCell.getNumericCellValue());  
//                            } else {  
//                                content.append(xCell.getStringCellValue());  
//                            }  
//                        }  
//                   }  
//                }  
//          
//                return content.toString();  
            } 


    /**
     * Extracts the text from the Excel table content.<p>
     * 
     * @param in the document input stream
     * @return the extracted text
     * @throws IOException if something goes wring
     */
    protected String extractTableContent(InputStream in) throws IOException {

        HSSFWorkbook excelWb = new HSSFWorkbook(in);
        StringBuffer result = new StringBuffer(4096);
        
        int numberOfSheets = excelWb.getNumberOfSheets();

        for (int i = 0; i < numberOfSheets; i++) {
            HSSFSheet sheet = excelWb.getSheetAt(i);
            int numberOfRows = sheet.getPhysicalNumberOfRows();
            if (numberOfRows > 0) {

                if (CmsStringUtil.isNotEmpty(excelWb.getSheetName(i))) {
                    // append sheet name to content
                    if (i > 0) {
                        result.append("\n\n");
                    }
                    result.append(excelWb.getSheetName(i).trim());
                    result.append(":\n\n");
                }

                Iterator rowIt = sheet.rowIterator();
                while (rowIt.hasNext()) {
                    HSSFRow row = (HSSFRow)rowIt.next();
                    if (row != null) {
                        boolean hasContent = false;
                        Iterator it = row.cellIterator();
                        while (it.hasNext()) {
                            HSSFCell cell = (HSSFCell)it.next();
                            String text = null;
                            try {
                                switch (cell.getCellType()) {
                                    case HSSFCell.CELL_TYPE_BLANK:
                                    case HSSFCell.CELL_TYPE_ERROR:
                                        // ignore all blank or error cells
                                        break;
                                    case HSSFCell.CELL_TYPE_NUMERIC:
                                        text = Double.toString(cell.getNumericCellValue());
                                        break;
                                    case HSSFCell.CELL_TYPE_BOOLEAN:
                                        text = Boolean.toString(cell.getBooleanCellValue());
                                        break;
                                    case HSSFCell.CELL_TYPE_STRING:
                                    default:
                                        text = cell.getStringCellValue();
                                        break;
                                }
                            } catch (Exception e) {
                                // ignore this cell
                            }
                            if (CmsStringUtil.isNotEmpty(text)) {
                                result.append(text.trim());
                                result.append(' ');
                                hasContent = true;
                            }
                        }
                        if (hasContent) {
                            // append a newline at the end of each row that has content                            
                            result.append('\n');
                        }
                    }
                }
            }
        }

        return result.toString();
    }

}