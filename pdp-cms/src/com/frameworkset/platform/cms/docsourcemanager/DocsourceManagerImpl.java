//Source file: D:\\workspace\\cms\\src\\com\\frameworkset\\platform\\cms\\sitemanager\\SiteManagerImpl.java
package com.frameworkset.platform.cms.docsourcemanager;
import java.sql.SQLException;
import java.util.Date;

import com.frameworkset.common.poolman.DBUtil;
import com.frameworkset.common.poolman.PreparedDBUtil;
import com.frameworkset.orm.transaction.TransactionManager;


/**
 * 站点管理
 * @author huaihai.ou
 * 日期:Dec 19, 2006
 * 版本:1.0
 * 版权所有:三一重工
 */
public class DocsourceManagerImpl implements DocsourceManager
{
	public DocsourceManagerImpl(){
	}
	public Docsource getDsrcList(int docsrcid) throws DocsourceManagerException {
		try {
			DBUtil dbUtil = new DBUtil();
			String sql="select * from TD_CMS_DOCSOURCE where DOCSOURCE_ID='"+ docsrcid +"'";
			dbUtil.executeSelect(sql);
			Docsource docsource = new Docsource();
			if(dbUtil.size()>0){
				for (int i = 0; i < dbUtil.size(); i++) {
					docsource.setSRCNAME(dbUtil.getString(i,"srcname"));
					docsource.setSRCDESC(dbUtil.getString(i,"srcdesc"));
					docsource.setSRCLINK(dbUtil.getString(i,"srclink"));
					docsource.setCRUSER(dbUtil.getInt(i,"cruser"));
					docsource.setCRTIME(dbUtil.getString(i,"crtime"));
					docsource.setDOCSOURCE_ID(dbUtil.getInt(i,"docsource_id"));
				}
			}
			return docsource;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DocsourceManagerException(e.getMessage());

		}
	}
	public boolean creatorDsrc(Docsource docsource) throws DocsourceManagerException {
		boolean b=false;
		PreparedDBUtil db = new PreparedDBUtil();
		//sql="select USER_NAME from TD_CMS_USER where USER_ID = '"+ add.getUSER_ID() +"'";
		TransactionManager tm = new TransactionManager();
		
		try {
			tm.begin();
			long docsourceId = db.getNextPrimaryKey("TD_CMS_DOCSOURCE") ;
			
			String sql0 = "select * from TD_CMS_DOCSOURCE where SRCNAME=?";
			db.preparedSelect(sql0);
			db.setString(1, docsource.getSRCNAME());
			db.executePrepared();
			if(db.size()>0){
				b=false;
			}else{

			String sql="insert into TD_CMS_DOCSOURCE(" + 
					"SRCNAME, " + 
					"SRCDESC, " + 
					"SRCLINK, " + 
					"CRUSER, " + 
					"CRTIME,DOCSOURCE_ID) values(?,?,?,?,?,?)";
			db.preparedInsert(sql);
			db.setString(1, docsource.getSRCNAME());
			db.setString(2, docsource.getSRCDESC());
			db.setString(3, docsource.getSRCLINK());
			db.setInt(4, docsource.getCRUSER());
			
			db.setDate(5, new Date());
			db.setLong(6, docsourceId);
			db.executePrepared();
				
				b=true;
			}
			tm.commit();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		finally
		{
			tm.release();
		}
		return b;
	}
	public boolean updateDsrc(Docsource docsource) throws DocsourceManagerException {
		boolean b=false;
		DBUtil db = new DBUtil();
		String sql="update TD_CMS_DOCSOURCE set " +
		   "SRCNAME='"+ docsource.getSRCNAME()+"', " +
   		   "SRCDESC='"+ docsource.getSRCDESC()+"', " +
   		   "SRCLINK='"+ docsource.getSRCLINK()+"' " +
   		   "where DOCSOURCE_ID="+ docsource.getDOCSOURCE_ID() +"";
		try {
			db.executeUpdate(sql);
			b=true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return b;
	}
	public boolean deleteDsrc(int docsrcid) throws DocsourceManagerException {
		boolean b = true;
		DBUtil db = new DBUtil();
		String sql="delete from TD_CMS_DOCSOURCE where DOCSOURCE_ID="+ docsrcid +"";
		try {
			db.executeDelete(sql);
		} catch (SQLException e) {
			b = false;
//			e.printStackTrace();
		}
		return b;
	}
	
	public Docsource getDsrcListBy(String name) throws DocsourceManagerException {
		try {
			DBUtil dbUtil = new DBUtil();
			String sql="select * from TD_CMS_DOCSOURCE where  SRCNAME='"+ name +"'";
			dbUtil.executeSelect(sql);
			Docsource docsource = new Docsource();
			if(dbUtil.size()>0){
				for (int i = 0; i < dbUtil.size(); i++) {
					docsource.setSRCNAME(dbUtil.getString(i,"srcname"));
					docsource.setSRCDESC(dbUtil.getString(i,"srcdesc"));
					docsource.setSRCLINK(dbUtil.getString(i,"srclink"));
					docsource.setCRUSER(dbUtil.getInt(i,"cruser"));
					docsource.setCRTIME(dbUtil.getString(i,"crtime"));
					docsource.setDOCSOURCE_ID(dbUtil.getInt(i,"docsource_id"));
				}
			}
			return docsource;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DocsourceManagerException(e.getMessage());

		}
	}
}
