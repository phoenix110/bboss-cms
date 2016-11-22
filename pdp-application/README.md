SELECT A.ID,
			       A.SYSTEM_ID,
			       A.SYSTEM_NAME,
			       A.WF_PUBLISHED_URL,
			       A.WF_MANAGE_URL,
			       A.APP_MODE_TYPE,
			       A.CREATOR,
			       A.CREATE_DATE,
			       A.UPDATE_PERSON,
			       A.UPDATE_DATE,
			       A.TODO_URL,
			       A.APP_URL,
			       A.SYSTEM_SECRET,
			       A.SYSTEM_SECRET_TEXT,
			       A.PENDING_TYPE,
			       A.PENDING_USED,
			       A.PIC_NAME,
			        A.TICKETTIME ,
			         A.needsign,key_.privateKey,key_.publicKey
			FROM TD_WF_APP A  left join eckeypairs key_ on A.SYSTEM_ID = key_.appid