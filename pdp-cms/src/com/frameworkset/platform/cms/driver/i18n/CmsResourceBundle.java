/*
 * File   : $Source: /usr/local/cvs/opencms/src/org/opencms/i18n/CmsResourceBundle.java,v $
 * Date   : $Date: 2006/03/27 14:53:01 $
 * Version: $Revision: 1.2 $
 *
 * This library is part of OpenCms -
 * the Open Source Content Mananagement System
 *
 * Copyright (C) 2002 - 2005 Alkacon Software (http://www.alkacon.com)
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
 * For further information about Alkacon Software, please see the
 * company website: http://www.alkacon.com
 *
 * For further information about OpenCms, please see the
 * project website: http://www.opencms.org
 * 
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */

package com.frameworkset.platform.cms.driver.i18n;

import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

/**
 * A property based resource bundle with increased visibility of some key methods.<p>
 * 
 * This class is required because the methods {@link #setParent(ResourceBundle)} and
 * {@link #setLocale(Locale)} are not visible in the standard implementation. However,
 * access to these methods is required by the {@link com.frameworkset.platform.cms.driver.i18n.CmsResourceBundleLoader}.<p>
 * 
 * @author  Alexander Kandzior 
 * 
 * @version $Revision: 1.2 $ 
 * 
 * @since 6.2.0 
 * 
 * @see com.frameworkset.platform.cms.driver.i18n.CmsResourceBundleLoader
 */
public class CmsResourceBundle extends PropertyResourceBundle implements java.io.Serializable {

    /** The locale to use. */
    protected Locale m_locale;

    /**
     * Default constructer from parent class.<p>
     * 
     * @param stream property file to read from
     * 
     * @throws IOException in case the file could not be read from
     */
    public CmsResourceBundle(InputStream stream)
    throws IOException {

        super(stream);
    }

    /**
     * @see java.util.ResourceBundle#getLocale()
     */
    public Locale getLocale() {

        return m_locale;
    }

    /**
     * Sets the locale used for this resource bundle.<p>
     * 
     * @param l the locale to set
     */
    protected void setLocale(Locale l) {

        m_locale = l;
    }

    /**
     * @see java.util.ResourceBundle#setParent(java.util.ResourceBundle)
     */
    protected void setParent(ResourceBundle p) {

        super.setParent(p);
    }
}