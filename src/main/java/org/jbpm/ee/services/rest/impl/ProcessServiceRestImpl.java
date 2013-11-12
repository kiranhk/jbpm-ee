
package org.jbpm.ee.services.rest.impl;

import java.util.Map;

import javax.inject.Inject;

import org.drools.core.xml.jaxb.util.JaxbMapAdapter;
import org.drools.core.xml.jaxb.util.JaxbStringObjectPair;
import org.jbpm.ee.services.ejb.local.ProcessServiceBean;
import org.jbpm.ee.services.rest.ProcessServiceRest;
import org.jbpm.ee.services.rest.request.JaxbInitializeProcessRequest;
import org.kie.services.client.serialization.jaxb.impl.JaxbProcessInstanceResponse;


public class ProcessServiceRestImpl implements ProcessServiceRest {

	@Inject
	private ProcessServiceBean processRuntimeService;
	
	@Override
	public JaxbProcessInstanceResponse startProcess(String processId, JaxbInitializeProcessRequest request) {
		try {
			if(request.getVariables() == null) {
				return new JaxbProcessInstanceResponse(processRuntimeService.startProcess(request.getReleaseId(), processId));
			}
			else {
				return new JaxbProcessInstanceResponse(processRuntimeService.startProcess(request.getReleaseId(), processId, request.getVariables()));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public JaxbProcessInstanceResponse createProcessInstance(String processId, JaxbInitializeProcessRequest request) {
		try {
			return new JaxbProcessInstanceResponse(processRuntimeService.createProcessInstance(request.getReleaseId(), processId, request.getVariables()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
		
	}

	@Override
	public JaxbProcessInstanceResponse startProcessInstance(long processInstanceId) {
		return new JaxbProcessInstanceResponse(processRuntimeService.startProcessInstance(processInstanceId));
	}

	@Override
	public void signalEvent(String type, Object event, long processInstanceId) {
		this.processRuntimeService.signalEvent(type, event, processInstanceId);
	}

	@Override
	public JaxbProcessInstanceResponse getProcessInstance(long processInstanceId) {
		return new JaxbProcessInstanceResponse(processRuntimeService.getProcessInstance(processInstanceId));
	}

	@Override
	public void abortProcessInstance(long processInstanceId) {
		this.processRuntimeService.abortProcessInstance(processInstanceId);
	}

}
