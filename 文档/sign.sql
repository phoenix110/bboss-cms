ALTER TABLE TD_WF_APP
ADD (needsign NUMBER(1) DEFAULT 1)

COMMENT ON COLUMN 
TD_WF_APP.needsign IS 
'���ơ�Ʊ���Ƿ���Ҫǩ�� 1 ��Ҫ 0 ����Ҫ Ĭ��Ϊ0'


ALTER TABLE td_wf_app ADD (needsign DECIMAL(1) DEFAULT '1')