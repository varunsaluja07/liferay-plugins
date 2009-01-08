/**
 * Copyright (c) 2000-2008 Liferay, Inc. All rights reserved.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.liferay.workflow.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ClassLoaderProxy;

/**
 * <a href="WorkflowInstanceServiceUtil.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class WorkflowInstanceServiceUtil {
	public static com.liferay.workflow.model.WorkflowInstance addInstance(
		long definitionId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		return getService().addInstance(definitionId);
	}

	public static void signalInstance(long instanceId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		getService().signalInstance(instanceId);
	}

	public static void signalToken(long instanceId, long tokenId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		getService().signalToken(instanceId, tokenId);
	}

	public static WorkflowInstanceService getService() {
		if (_service == null) {
			Object obj = PortletBeanLocatorUtil.locate("workflow-portlet",
					WorkflowInstanceServiceUtil.class.getName());
			ClassLoader portletClassLoader = (ClassLoader)PortletBeanLocatorUtil.locate("workflow-portlet",
					"portletClassLoader");

			ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(obj,
					portletClassLoader);

			_service = new WorkflowInstanceServiceClp(classLoaderProxy);

			ClpSerializer.setClassLoader(portletClassLoader);
		}

		return _service;
	}

	public void setService(WorkflowInstanceService service) {
		_service = service;
	}

	private static WorkflowInstanceService _service;
}