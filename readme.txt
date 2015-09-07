需要解决的问题：
1.工作流：
业务演示中查看抄送任务记录已阅记录
暂存表中没有记录单据的起草人和起草人账号所属部门id
demo业务列表中没有根据当前用户和超级管理过滤任务

1.规则开发
2.抄送默认抄送已经处理任务的人选型添加

sap驱动在linux下的存放地址：
/lib/libsapjco3.so
 

jgroup linux下部署修改os参数（5M）：
sysctl -w net.core.rmem_max=5000000
sysctl -w net.core.wmem_max=5000000



alter table TD_WF_HI_COPYTASK add TASKTYPE NUMBER(1) default 1;
alter table TD_WF_COPYTASK add TASKTYPE NUMBER(1) default 1;

alter table TD_WF_HI_COPYTASK add TASKTYPE DECIMAL(1) default 1;
alter table TD_WF_COPYTASK add TASKTYPE DECIMAL(1) default 1;
ALTER TABLE td_wf_app
	MODIFY COLUMN TICKETTIME DECIMAL DEFAULT'-2'
	
ALTER TABLE TD_WF_APP MODIFY (TICKETTIME DEFAULT -2)
	
ALTER TABLE td_wf_app ADD (TICKETTIME DECIMAL(19) DEFAULT '-2')	

mysql:
# -----------------------------------------------------------------------
# TD_WF_COPYTASK
# -----------------------------------------------------------------------
drop table if exists TD_WF_COPYTASK;

CREATE TABLE TD_WF_COPYTASK
(
    ID VARCHAR(100) NOT NULL,
    COPERTYPE DECIMAL(1) default 0,
    COPER VARCHAR(255),
    PROCESS_ID VARCHAR(100),
    PROCESS_KEY VARCHAR(200),
    BUSINESSKEY VARCHAR(255),
    COPYTIME TIMESTAMP NULL DEFAULT NULL,
    TASKTYPE DECIMAL(1) default 1,
    PRIMARY KEY(ID)) ENGINE=InnoDB;


# -----------------------------------------------------------------------
# TD_WF_HI_COPYTASK
# -----------------------------------------------------------------------
drop table if exists TD_WF_HI_COPYTASK;

CREATE TABLE TD_WF_HI_COPYTASK
(
    ID VARCHAR(100) NOT NULL,
    COPORG VARCHAR(255),
    COPER VARCHAR(255),
    PROCESS_ID VARCHAR(100),
    PROCESS_KEY VARCHAR(200),
    BUSINESSKEY VARCHAR(255),
    COPYTIME TIMESTAMP NULL DEFAULT NULL,
    READTIME TIMESTAMP NULL DEFAULT NULL,
    PRIMARY KEY(ID)) ENGINE=InnoDB;
ALTER TABLE td_wf_node_worktime ADD (COPYUSERS VARCHAR(4000));
ALTER TABLE td_wf_node_worktime ADD (COPYORGS VARCHAR(4000));
ALTER TABLE td_wf_node_worktime ADD (COPYERSCNNAME VARCHAR(4000));

  <Host name="test.sany.com.cn"  appBase="sany"
            unpackWARs="true" autoDeploy="true">

        <!-- SingleSignOn valve, share authentication between web applications
             Documentation at: /docs/config/valve.html -->
        <!--
        <Valve className="org.apache.catalina.authenticator.SingleSignOn" />
        -->

        <!-- Access log processes all example.
             Documentation at: /docs/config/valve.html
             Note: The pattern used is equivalent to using pattern="common" -->
        <Valve className="org.apache.catalina.valves.AccessLogValve" directory="logs"
               prefix="localhost_access_log." suffix=".txt"
               pattern="%h %l %u %t &quot;%r&quot; %s %b" />

      </Host>
/SanyPDP/params/showParams.page?paramId=323a023a-8516-47c3-9dbd-afea40c23144&paramType=app&handler=cms.siteparamshandler
ip访问控制思路：
CREATE TABLE PDPV108.TD_SM_IPCONTROL
(
  ID           VARCHAR2(100),
  IP           VARCHAR2(1000),
  CONTROLUSER  VARCHAR2(100),
  FILTERTYPE   NUMBER(1),
  IPDESC       VARCHAR2(2000)
)

LOGGING 
NOCOMPRESS 
NOCACHE
NOPARALLEL
NOMONITORING;

COMMENT ON TABLE PDPV108.TD_SM_IPCONTROL IS 'IP访问控制：
对用户访问系统的来源IP进行控制，分为白名单和黑名单
控制逻辑：
1.查找用户是否有白名单和黑名单，判断用户是否属于白名单用户（在白名单中不在黑名单中，不在黑名单中，在白名单中）
2.如果用户没有定义白名单，查找所有与用户无关白名单和黑名单，判断用户是否属于白名单用户（在白名单中不在黑名单中，不在黑名单中，在白名单中）
人员选择可以使用工作流的人员选择功能：/SanyPDP/WebRoot/purviewmanager/common/selectuser.jsp?loginName=xxx&loginID=xxx
获取客户端ip的方法：com.frameworkset.util.StringUtil.getClientIP(HttpServletRequest request)
控制点：
/SanyPDP/src-ldap/com/sany/ldap/LdapLoginModule.java
/SanyPDP/src-sys/com/frameworkset/platform/sysmgrcore/authenticate/UserPasswordLoginModule.java
如果不在白名单：
 throw new LoginException("IPAccessFailed");


';

COMMENT ON COLUMN PDPV108.TD_SM_IPCONTROL.ID IS '自增序号（uuid）';

COMMENT ON COLUMN PDPV108.TD_SM_IPCONTROL.IP IS '多个以逗号分隔，192.168.*.*,192.168.142.1,192.168.142.1-254';

COMMENT ON COLUMN PDPV108.TD_SM_IPCONTROL.CONTROLUSER IS '可选，如果维护了用户，则表示对用户设置的规则，没有则是全局规则';

COMMENT ON COLUMN PDPV108.TD_SM_IPCONTROL.FILTERTYPE IS '规则类型0白名单，1黑名单';

COMMENT ON COLUMN PDPV108.TD_SM_IPCONTROL.IPDESC IS '备注';


ALTER TABLE PDPV108.TD_SM_IPCONTROL ADD (
  CONSTRAINT IPCONTROL_PK
 PRIMARY KEY
 (ID));



用户查询jasperreport报表答应包签名方法
//生成证书文件
keytool -keypass idiots6 -storepass idiots6 -genkey -dname "OU=bpit, O=sany, L=changsha, ST=hunan, C=CN" -validity 55555 
jarsigner -keystore c:\.keystore -keypass idiots6 -storepass idiots6 F:\workspace\bboss-cms\WebRoot\sysmanager\user\ireport\jasperprint.jar mykey 
jarsigner -keystore c:\.keystore -keypass idiots6 -storepass idiots6 F:\workspace\bboss-cms\WebRoot\sysmanager\user\ireport\jasperreports-applet-5.5.1.jar mykey 
jarsigner -keystore c:\.keystore -keypass idiots6 -storepass idiots6 F:\workspace\bboss-cms\WebRoot\sysmanager\user\ireport\commons-logging-1.1.1.jar mykey 
jarsigner -keystore c:\.keystore -keypass idiots6 -storepass idiots6 F:\workspace\bboss-cms\WebRoot\sysmanager\user\ireport\commons-collections-3.1.jar mykey 
jarsigner -keystore c:\.keystore -keypass idiots6 -storepass idiots6 F:\workspace\bboss-cms\WebRoot\sysmanager\user\ireport\jasperreports-5.5.1.jar mykey  
jarsigner -verify -verbose F:\workspace\bboss-cms\WebRoot\sysmanager\user\ireport\jasperprint.jar 

1.扩展字段解析发布测试和bug修复
2.增加扩展字段label展示字段

1.扩展字段插入和修改完善，解决clob字段存储超出长度问题
2.扩展字段读取完善：新增DocExtValue对象用来封装扩展字段信息和值，影响的功能：文档扩展标签，文档预览
3.扩展字段发布：发布文档时，增加对扩展字段内容解析和发布
4.修复扩展字段发布失效问题

java.sql.SQLException: Generated keys not requested. You need to specify Statement.RETURN_GENERATED_KEYS to Statement.executeUpdate() or Connection.prepareStatement().
下午03:16:55: 王胜利: 哎，这个问题原来 是jar包升级导致的
下午03:17:07: 王胜利: mysql只能用老jar包

后续要扩展的功能：
扩展字段可以像新闻正文一样采集新闻内容（富媒体新闻）
ALTER TABLE TD_CMS_CHANNELFIELD
ADD (field_owner NUMBER(2) DEFAULT 0)

COMMENT ON COLUMN 
TD_CMS_CHANNELFIELD.field_owner IS 
'扩展字段类型文档和频道：0-频道 ，channel_id表示频道id 1-文档  channel_id表示文档id'
'扩展字段类型文档和频道：0-频道 1-文档 '

1.解决问题：
内容管理：普通页面发布因异常失败

内容管理需要实现的功能：
1.文档模板引用功能完善
2.文档内容可以在首页和概览首页上发布，为更加灵活的内容发布奠定基础
3.站内检索

ALTER TABLE TD_WF_NODEVARIABLE
 ADD (ROWNO_  NUMBER                                DEFAULT 0);

ALTER TABLE TD_WF_ACTIVITI_NODE_INFO
 ADD (node_type  VARCHAR2(100)                      DEFAULT 'userTask');




补丁脚本：

CREATE TABLE TD_SM_ORGPARAMETERS
(
  PARAM_TYPE  VARCHAR2(50 BYTE)                 NOT NULL,
  NODE_ID     VARCHAR2(50 BYTE)                 NOT NULL,
  NAME        VARCHAR2(50 BYTE)                 NOT NULL,
  RN          NUMBER(10)                        NOT NULL,
  VALUE       VARCHAR2(200 BYTE),
  ISBIGDATA   NUMBER(1),
  BIGDATA     BLOB
);


ALTER TABLE TD_SM_ORGPARAMETERS ADD (
  CONSTRAINT TD_SM_ORGPARAMETERS_PK
 PRIMARY KEY
 (PARAM_TYPE, NODE_ID, NAME, RN));
 
 CREATE TABLE TD_SM_USERPARAMETERS
(
  PARAM_TYPE  VARCHAR2(50 BYTE)                 NOT NULL,
  NODE_ID     VARCHAR2(50 BYTE)                 NOT NULL,
  NAME        VARCHAR2(50 BYTE)                 NOT NULL,
  RN          NUMBER(10)                        NOT NULL,
  VALUE       VARCHAR2(200 BYTE),
  ISBIGDATA   NUMBER(1),
  BIGDATA     BLOB
);


ALTER TABLE TD_SM_USERPARAMETERS ADD (
  CONSTRAINT TD_SM_USERPARAMETERS_PK
 PRIMARY KEY
 (PARAM_TYPE, NODE_ID, NAME, RN));




url权限控制：
url控制原理：将url对应的相关的菜单与角色的关系转换为url与角色的关系，将url相关的所有菜单
1.在模板视图中编辑模板时，distribute标签丢失

1.内容管理中文档正文增加以下自定义标签：
[file][/file]
[flashplayer][/flashplayer]
[img][/img]
用来支持在文档正文中任意放置文件下载，电影播放，图片展示信息

flash播放
缓存资源管理


口令失效控制相关开关	
	<!-- 密码过期时间，大于0时有效，小于等于0时无效  ，默认全局配置-->
	<property name="password_dualtime" value="90"/>
	
	<!-- 是否启用登录验证码  -->
	<property name="enable_login_validatecode" value="true"/>
	<!-- 是否启用用户级的口令过期时间 ，如果启用用户级，默认全局配置失效 -->
	<property name="enableUserScopePasswordExpiredDays" value="true"/>

1.登录页增加校验码功能
2.增加登录密码过期检查功能，并提供过期密码修改功能，修改密码时记录历史密码，密码不能重复
3.权限管理和用户查询及离散用户列表中增加密码过期时间属性的展示
4.工作流扩展自由流功能
5.td_sm_user表增加字段 PASSWORD_UPDATETIME  TIMESTAMP(6)
6.增加记录历史密码表
CREATE TABLE TD_SM_PASSWORDHIS
(
  USER_ID        NUMBER,
  PASSWORD_      VARCHAR2(100 BYTE),
  PASSWORD_TIME  TIMESTAMP(6)
)
7.添加数据库更新脚本文件D:\workspace\microcredit-1.0.1\doc\02_设计\01_DB\02_sql\2013-06-16-0.sql

8.每个用户独立设置密码过期时间
9.增加密码过期策略
用户密码有效时间大于0则启用
是否启用每个用户独立设置密码有效期开关

增加用户缓冲信息获取标签和缓冲数据刷新功能：
<%@ taglib uri="/WEB-INF/dictionary.tld" prefix="dict"%>

<dict:user user="1" isAccount="false" attribute="userName"/>
<dict:user user="1" isAccount="false"/>
<dict:user user="admin" attribute="userName" />
<dict:user user="admin"  />

<dict:user user="1" isAccount="false" attribute="userName"/>
<dict:user user="1" isAccount="false"/>
<dict:user user="admin" attribute="userName" />
<dict:user user="admin"  />

<dict:user colName="userAccount" />
<dict:user colName="userAccount"  attribute="userName" />

<dict:user colName="userId" isAccount="false"/>
<dict:user colName="userId"  isAccount="false" attribute="userName" />

attribute的值可以为：

			 userName（默认值）
			 userID 
			 password
			 orgId
             logincount
             userAccount
             remark1
             remark2
             remark3
             remark4
             remark5
             userAddress
             userEmail
             userFax
             userHometel
             userIdcard
             userMobiletel1
             userMobiletel2
             userOicq
             userPinyin
             userPostalcode
             userSex
             userType
             userWorknumber
             userWorktel
             userBirthday
             userRegdate
             userSn
             userIsvalid

o 内容管理修复严重bug
当频道切换模板偶尔会出现发布无法按照新的模板进行发布的问题原因查明：
每个频道的发布时缓存的模板时间戳机制，以临时文件路径为key，模板时间戳为值，所用的临时文件的路径是直接根据频道
发布首页存放的路径+频道首页名称，并没有包含模板id，所以不管怎么切换模板，这个临时文件的路径不会改变，检测
模板有没有发生变化，有发生变化时才重新生成临时文件，按道理讲切换模板后，前后两个模板文件的时间戳会不一样，但是
在linux环境下却出现了一样的情况导致临时文件没有按照新的模板重新生成。

解决办法：修改key的机制为【首页存放的路径+频道首页名称+"_" +模板id】，问题得到解决

o 内网宣传平台  
todolist：
1.评论顺序问题(ok)
2.访问页面来源不准确(ok)
3.定时统计没起作用
4.点播此时统计优化-播放时才计数
5.将视屏播放页面拉入访问统计中(ok)
6.将模板中的publishedtime统一改为docwtime(ok)
7.专题首页、首页高度问题（ok）
8.视屏点击表删除表外键并添加站点、频道、文档字段索引，删除浏览统计表外键并添加站点、频道、文档字段索引，解决回收站中已删除文档无法清空的问题
9.视屏和新闻列表中出现已经删除的文档
/SanyPDP/src-cms/com/frameworkset/platform/cms/documentmanager/document.xml
10.首页图片新闻banner提示不对
/SanyPDP/WebRoot/cms/siteResource/sanyIPP/_template/index.html
11.新建站点时，组件包没有解开
ALTER TABLE TD_CMS_VIDIO_HITS
 DROP CONSTRAINT TD_CMS_VIDIO_HITS_FK_1;

ALTER TABLE TD_CMS_VIDIO_HITS
 DROP CONSTRAINT TD_CMS_VIDIO_HITS_FK_2;

ALTER TABLE TD_CMS_VIDIO_HITS
 DROP CONSTRAINT TD_CMS_VIDIO_HITS_FK_3;

create index vhits_docid on TD_CMS_VIDIO_HITS(doc_id)
create index vhits_channelid on TD_CMS_VIDIO_HITS(CHANNEL_ID)
create index vhits_siteid on TD_CMS_VIDIO_HITS(SITE_ID)

ALTER TABLE TD_CMS_BROWSER_COUNTS
 DROP CONSTRAINT TD_CMS_BROWSER_COUNTS_FK_1;

ALTER TABLE TD_CMS_BROWSER_COUNTS
 DROP CONSTRAINT TD_CMS_BROWSER_COUNTS_FK_2;

ALTER TABLE TD_CMS_BROWSER_COUNTS
 DROP CONSTRAINT TD_CMS_BROWSER_COUNTS_FK_3;
 
 create index brwcounts_docid on TD_CMS_BROWSER_COUNTS(doc_id)
 create index brwcounts_channelid on TD_CMS_BROWSER_COUNTS(CHANNEL_ID)
 create index brwcounts_siteid on TD_CMS_BROWSER_COUNTS(SITE_ID)
12.文档相关附件处理由jspsmartupload切换为common upload机制，解决参数和文件件名中文乱码问题
o 单独打开菜单(不带一级导航条和top页面)的方法：
contextpath + "sanydesktop/frame.page?sany_menupath=dp::menu://sysmenu$root/products$module/xxxx$item"
contextpath + "sanydesktop/webframe.page?sany_menupath=dp::menu://sysmenu$root/products$module/xxxx$item"
o 单独打开菜单的方法：
contextpath + "sanydesktop/index.page?sany_menupath=dp::menu://sysmenu$root/products$module/xxxx$item"
contextpath + "sanydesktop/webindex.page?sany_menupath=dp::menu://sysmenu$root/products$module/xxxx$item"

o 单独打开菜单，并且指定打开url的方法
contextpath + "sanydesktop/index.page?sany_menupath=dp::menu://sysmenu$root/products$module/xxxx$item&sany_selecturl=/xxx.page?aaa=xxx"
contextpath + "sanydesktop/webindex.page?sany_menupath=dp::menu://sysmenu$root/products$module/xxxx$item&sany_selecturl=/xxx.page?aaa=xxx"

http://localhost:8081/smc-desktop/sanydesktop/webindex.page?sany_menupath=dp::menu://sysmenu$root/products$module/machine$item&sany_selecturl=%2Fsanydps%2Fnews%2FshowProductMainNews.page%3Fcid%3D38
这里需要对参数&sany_selecturl=/xxx.page?aaa=xxx的值进行url编码，否则无法正常解析,编码方法为：
java.net.URLEncoder.encode("/xxx.page?aaa=xxx");
o module元素增加url属性用来指定打开并点击模块时，默认打开的工作区域地址


alter table td_sm_user add index idx_username(user_name);
o 增加子系统配置注销调整页面的方法 
<subsystem name="内容管理" i18n:en_US="Content Manager" id="cms" template="cms" module="module-content.xml"
	 	logoutredirect="/login.jsp"
o module.xml文件中item元素添加area属性的原因
平台老模块（系统管理，字典管理）中的item需要设置area="main"属性，其他模块不需要
o 构建脚本完善
o 增加左侧菜单标签
增加module和item增加showleftmenu属性，具备传递性，子元素没有指定则继承父元素的属性值,子元素覆盖父元素值
sysmeny元素增加showrootleftmenu属性，具备传递性，子元素没有指定则继承父元素的属性值
module.xml中配置左侧菜单：
配置个性化菜单组全部出现左侧菜单-showrootleftmenu="true"
<sysmenu name="spplatform" left_width="12%" top_height="70"
		showhidden="true" showhidden_width="10" showrootleftmenu="true">
		
配置module中出现左侧菜单-showleftmenu="true"：
<module name="应用台账" id="appbommanagermodule" used="true" showleftmenu="true">

frame.jsp中设置标签：
 <sany:leftmenu target="rightFrame"/>
 
 target属性指定页面链接打开的窗口名称    		

******************************************************
***     请求服务平台    2012.02.02     qian.wang          ***
******************************************************
o 日志查询bug
1.部门管理员查询日志失败，修改程序
/smc-desktop/src-sys/com/frameworkset/platform/sysmgrcore/web/tag/LogSearchList.java
2.日志按模块统计选机构失败
/smc-desktop/WebRoot/sysmanager/dictmanager/orgSelectTree.jsp
******************************************************
***     请求服务平台    2011.09.16     qian.wang          ***
******************************************************
1.desktop的框架中，新增了快捷方式排序功能和用户自定义窗口大小功能
在td_sm_deskmenu表中增加了iter_order字段，用来桌面方式快捷排序
新增了td_sm_menucustom表，用来存储用户自定义窗口大小的字段
修改文件：
CreatorESB-3.0\WebRoot\WEB-INF\conf\commons\bboss-esb-desktop.xml
creatorESBS3.0\CreatorESB-3.0\WebRoot\desktop\setting.jsp
新增文件：
creatorESBS3.0\CreatorESB-3.0\WebRoot\desktop\desktopsort.jsp
creatorESBS3.0\CreatorESB-3.0\WebRoot\desktop-4.0\menucustom\deskitemcustomtree.jsp
creatorESBS3.0\CreatorESB-3.0\WebRoot\desktop-4.0\menucustom\main.jsp
creatorESBS3.0\CreatorESB-3.0\WebRoot\desktop-4.0\menucustom\setdesktopwindowsize.jsp
2. 修改了用户登录按照传统风格和desktop风格登入系统
creatorESBS3.0\CreatorESB-3.0\WebRoot\login.jsp


修改菜单生成控制器
直接使用ResponseBody将List对象返回，然后通过mvc框架将该list转换为json串对象返回给客服端

