/*
 * File   : $Source: /usr/local/cvs/opencms/src/org/opencms/main/I_CmsThrowable.java,v $
 * Date   : $Date: 2005/06/23 11:11:38 $
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

package com.frameworkset.platform.cms.driver.util;

import java.util.Locale;

import com.frameworkset.platform.cms.driver.i18n.CmsMessageContainer;



/**
 * Provides localized Exception handling based on the OpenCms default locale.<p>
 * 
 * Instances of this class are assumed to have full localized exception messages.<p>
 * 
 * @author Alexander Kandzior 
 * 
 * @version $Revision: 1.5 $ 
 * 
 * @since 6.0.0 
 */
public interface I_CmsThrowable extends java.io.Serializable {

    /**
     * Returns a localized exception message based on the OpenCms default locale.<p>
     * 
     * @return a localized exception message based on the OpenCms default locale
     * @see Throwable#getLocalizedMessage()
     */
    String getLocalizedMessage();

    /**
     * Returns a localized exception message based on the given Locale.<p>
     * 
     * @param locale the Locale to get the message for
     * 
     * @return a localized exception message based on the given Locale
     */
    String getLocalizedMessage(Locale locale);

    /**
     * Returns a localized exception message based on the OpenCms default locale.<p>
     * 
     * @return a localized exception message based on the OpenCms default locale
     * @see Throwable#getMessage()
     */
    String getMessage();

    /**
     * Returns the localized message container used to build this localized exception.<p>
     * 
     * @return the localized message container used to build this localized exception
     */
    CmsMessageContainer getMessageContainer();
}
