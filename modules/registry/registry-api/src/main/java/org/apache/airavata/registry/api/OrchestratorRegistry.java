/*
 *
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 *
*/
package org.apache.airavata.registry.api;

import org.apache.airavata.common.utils.AiravataJobState;
import org.apache.airavata.registry.api.exception.RegException;

import java.util.List;
import java.util.Map;

public interface OrchestratorRegistry extends AiravataSubRegistry {


    /**
     * this return information about GFAC instances running in the system.
     * @return
     * @throws org.apache.airavata.registry.api.exception.RegException
     */
    Map<String, Integer> getGFACNodeList() throws RegException;

    /**
     * This is the method to use to add a GFAC Node to registry,
     * during embedded mode it should add a single gfac node
     * @param uri
     * @param nodeID
     * @return
     * @throws org.apache.airavata.registry.api.exception.RegException
     */
    boolean addGFACNode(String uri, int nodeID)throws RegException;


    /**
     * This can be used to change the status to any valid status
     * @param experimentID
     * @param state
     * @param gfacEPR
     * @return
     * @throws org.apache.airavata.registry.api.exception.RegException
     */
    boolean changeStatus(String experimentID,AiravataJobState.State state, String gfacEPR)throws RegException;
    
    /**
     * This can be used to change the status to any valid status
     * @param experimentID
     * @param state
     * @return
     * @throws org.apache.airavata.registry.api.exception.RegException
     */
    boolean changeStatus(String experimentID,AiravataJobState.State state)throws RegException;


    /**
     * This method can be used to seek the status of a given experiment
     * @param experimentID
     * @return
     * @throws org.apache.airavata.registry.api.exception.RegException
     */
    AiravataJobState getState(String experimentID)throws RegException;

    /**
     * This returns all the jobs with the given state
     * @param state give any state user wants to retrieve all the experiment IDs
     * @return
     * @throws RuntimeException
     */
    List<String> getAllJobsWithState(AiravataJobState state) throws RuntimeException;

    /**
     * This will return list of experimentID of jobs which are in
     * ACCEPTED state
     * @return
     * @throws org.apache.airavata.registry.api.exception.RegException
     */
    List<String> getAllAcceptedJobs()throws RegException;


    /**
     * This will return all the hanged jobs, the logic to determine
     * whether job is hanged or not is depend on the implementation
     * @return
     * @throws org.apache.airavata.registry.api.exception.RegException
     */
    List<String> getAllHangedJobs()throws RegException;

    /**
     * return the number of jobs hanged, it not normal that jobs are hanged
     * all the time, so users can use this method before try to retrieve
     * list of hanged jobs
     * @return  number of hanged jobs
     * @throws org.apache.airavata.registry.api.exception.RegException
     */
    int getHangedJobCount()throws RegException;

    /**
     * reset hanged jobs based on previous state
     * @param experimentID
     * @return true if operation is successful
     * @throws org.apache.airavata.registry.api.exception.RegException
     */
    boolean resetHangedJob(String experimentID)throws RegException;

}
