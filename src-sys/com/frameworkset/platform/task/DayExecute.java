package com.frameworkset.platform.task;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

//import com.frameworkset.platform.mq.RemindManagerImpl;
import com.frameworkset.util.StringUtil;

public class DayExecute implements Execute, Serializable
{

    public void execute(Map parameters)
    {
        String content = "";
        String userID = (String) parameters.get("userID");
        String topic = (String) parameters.get("topic");
        String place = (String) parameters.get("place");
        String beginTime = StringUtil.getFormatDate((Date) parameters
                .get("beginTime"), "yyyy-MM-dd HH:mm:ss");
        String endTime = StringUtil.getFormatDate((Date) parameters
                .get("endTime"), "yyyy-MM-dd HH:mm:ss");
        String isSys = (String) parameters.get("isSys");
        String isEmail = (String) parameters.get("isEmail");
        String isMessage = (String) parameters.get("isMessage");
       
        String type = (String)parameters.get("type");
        if(type == null || type.equals(""))
        	type = "schedule";
        
        
        String typeDesc = (String)parameters.get("typeDesc");
        if(typeDesc == null || typeDesc.equals(""))
        	typeDesc = "日程安排";
        String url = (String)parameters.get("url");
        if(url == null || url.equals(""))
        	url = "/sysmanager/remind_list.jsp";
        content = "主题：" + topic + '\n' + " 地点：" + place + '\n' + "日程开始时间："
                + beginTime + '\n' + "日程结束时间:" + endTime;
//        RemindManagerImpl rmi = new RemindManagerImpl();
//        //发送提醒
//        rmi.sendRemindInfoByUserId("日程",isSys,isEmail,isMessage,Integer.parseInt(userID),content,type,typeDesc,url);
    }

}
