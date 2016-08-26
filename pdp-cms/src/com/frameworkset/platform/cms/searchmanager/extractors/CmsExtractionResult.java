/*
 * File   : $Source: /usr/local/cvs/opencms/src/org/opencms/search/extractors/CmsExtractionResult.java,v $
 * Date   : $Date: 2005/07/29 12:13:00 $
 * Version: $Revision: 1.5 $
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

import java.util.Collections;
import java.util.Map;

/**
 * The result of a document text extraction.<p>
 * 
 * This data structure contains the extracted text as well as (optional) 
 * meta information extracted from the document.<p>
 * 
 * @author Alexander Kandzior
 * 
 * @version $Revision: 1.5 $ 
 * 
 * @since 6.0.0 
 */
public class CmsExtractionResult implements I_CmsExtractionResult {

    /** The extracted content. */
    private String m_content;

    /** The extracted meta information. */
    private Map m_metaInfo;

    /**
     * Creates a new extration result without meta information.<p>
     * 
     * @param content the extracted content
     */
    public CmsExtractionResult(String content) {

        this(content, null);
    }

    /**
     * Creates a new extration result.<p>
     * 
     * @param content the extracted content
     * @param metaInfo the extracted documnet meta information
     */
    public CmsExtractionResult(String content, Map metaInfo) {

        m_content = content;
        m_metaInfo = metaInfo;

        if (m_metaInfo == null) {
            m_metaInfo = Collections.EMPTY_MAP;
        }
    }

    /**
     * @see org.opencms.search.extractors.I_CmsExtractionResult#getContent()
     */
    public String getContent() {

        return m_content;
    }

    /**
     * @see org.opencms.search.extractors.I_CmsExtractionResult#getMetaInfo()
     */
    public Map getMetaInfo() {

        return m_metaInfo;
    }

    /**
     * @see org.opencms.search.extractors.I_CmsExtractionResult#release()
     */
    public void release() {

        if (!m_metaInfo.isEmpty()) {
            m_metaInfo.clear();
        }
        m_metaInfo = null;
        m_content = null;
    }
}