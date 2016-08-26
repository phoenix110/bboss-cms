/*
* File   : $Source: /usr/local/cvs/opencms/src-modules/com/opencms/htmlconverter/CmsHtmlConverterObjectReplaceStrings.java,v $
* Date   : $Date: 2005/05/17 13:47:32 $
* Version: $Revision: 1.1 $
*
* This library is part of OpenCms -
* the Open Source Content Mananagement System
*
* Copyright (C) 2001  The OpenCms Group
*
* This library is free software; you can redistribute it and/or
* modify it under the terms of the GNU Lesser General Public
* License as published by the Free Software Foundation; either
* version 2.1 of the License, or (at your option) any later version.
*
* This library is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
* Lesser General Public License for more details.
*
* For further information about OpenCms, please see the
* OpenCms Website: http://www.opencms.org
*
* You should have received a copy of the GNU Lesser General Public
* License along with this library; if not, write to the Free Software
* Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
*/

package com.frameworkset.platform.cms.driver.htmlconverter;

/**
 * Object for replacing Strings. Contains 4 Strings with String to replace,
 * String with new content and prefix and suffix.<p>
 * 
 * @author Andreas Zahner
 * @version 1.0
 * 
 * @deprecated Will not be supported past the OpenCms 6 release.
 */
final class CmsHtmlConverterObjectReplaceStrings extends CmsHtmlConverterObjectReplaceContent implements java.io.Serializable {

    /** The prefix will be placed in front of every replaced content. */
    private String m_prefix;
    /** The suffix will be placed behind every replaced content. */
    private String m_suffix;

    /**
     * Default constructor creates object with empty Strings.<p>
     */
    protected CmsHtmlConverterObjectReplaceStrings () {
        super();
        m_prefix = "";
        m_suffix = "";
    }

    /**
     * Constructor creates object with parameter values.<p>
     * 
     * @param sS String searchString
     * @param p String prefix
     * @param rS String replaceString
     * @param s String suffix
     */
    protected CmsHtmlConverterObjectReplaceStrings (String sS, String p, String rS, String s) {
        super(sS, rS);
        m_prefix = p;
        m_suffix = s;
    }

    /**
     * Returns the new content.<p>
     * 
     * @return new String with prefix and suffix added
     */
    protected String getReplaceItem() {
        return m_prefix+super.getReplaceItem()+m_suffix;
    }

}
