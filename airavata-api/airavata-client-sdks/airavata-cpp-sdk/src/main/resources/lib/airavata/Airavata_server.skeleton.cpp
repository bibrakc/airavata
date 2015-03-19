/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

// This autogenerated skeleton file illustrates how to build a server.
// You should copy it to another filename to avoid overwriting it.

#include "Airavata.h"
#include <thrift/protocol/TBinaryProtocol.h>
#include <thrift/server/TSimpleServer.h>
#include <thrift/transport/TServerSocket.h>
#include <thrift/transport/TBufferTransports.h>

using namespace ::apache::thrift;
using namespace ::apache::thrift::protocol;
using namespace ::apache::thrift::transport;
using namespace ::apache::thrift::server;

using boost::shared_ptr;

using namespace  ::apache::airavata::api;

class AiravataHandler : virtual public AiravataIf {
 public:
  AiravataHandler() {
    // Your initialization goes here
  }

  void getAPIVersion(std::string& _return) {
    // Your implementation goes here
    printf("getAPIVersion\n");
  }

  void addGateway(std::string& _return, const  ::apache::airavata::model::workspace::Gateway& gateway) {
    // Your implementation goes here
    printf("addGateway\n");
  }

  void updateGateway(const std::string& gatewayId, const  ::apache::airavata::model::workspace::Gateway& updatedGateway) {
    // Your implementation goes here
    printf("updateGateway\n");
  }

  void getGateway( ::apache::airavata::model::workspace::Gateway& _return, const std::string& gatewayId) {
    // Your implementation goes here
    printf("getGateway\n");
  }

  bool deleteGateway(const std::string& gatewayId) {
    // Your implementation goes here
    printf("deleteGateway\n");
  }

  void getAllGateways(std::vector< ::apache::airavata::model::workspace::Gateway> & _return) {
    // Your implementation goes here
    printf("getAllGateways\n");
  }

  bool isGatewayExist(const std::string& gatewayId) {
    // Your implementation goes here
    printf("isGatewayExist\n");
  }

  void generateAndRegisterSSHKeys(std::string& _return, const std::string& gatewayId, const std::string& userName) {
    // Your implementation goes here
    printf("generateAndRegisterSSHKeys\n");
  }

  void getSSHPubKey(std::string& _return, const std::string& airavataCredStoreToken) {
    // Your implementation goes here
    printf("getSSHPubKey\n");
  }

  void getAllUserSSHPubKeys(std::map<std::string, std::string> & _return, const std::string& userName) {
    // Your implementation goes here
    printf("getAllUserSSHPubKeys\n");
  }

  void createProject(std::string& _return, const std::string& gatewayId, const  ::apache::airavata::model::workspace::Project& project) {
    // Your implementation goes here
    printf("createProject\n");
  }

  void updateProject(const std::string& projectId, const  ::apache::airavata::model::workspace::Project& updatedProject) {
    // Your implementation goes here
    printf("updateProject\n");
  }

  void getProject( ::apache::airavata::model::workspace::Project& _return, const std::string& projectId) {
    // Your implementation goes here
    printf("getProject\n");
  }

  bool deleteProject(const std::string& projectId) {
    // Your implementation goes here
    printf("deleteProject\n");
  }

  void getAllUserProjects(std::vector< ::apache::airavata::model::workspace::Project> & _return, const std::string& gatewayId, const std::string& userName) {
    // Your implementation goes here
    printf("getAllUserProjects\n");
  }

  void searchProjectsByProjectName(std::vector< ::apache::airavata::model::workspace::Project> & _return, const std::string& gatewayId, const std::string& userName, const std::string& projectName) {
    // Your implementation goes here
    printf("searchProjectsByProjectName\n");
  }

  void searchProjectsByProjectDesc(std::vector< ::apache::airavata::model::workspace::Project> & _return, const std::string& gatewayId, const std::string& userName, const std::string& description) {
    // Your implementation goes here
    printf("searchProjectsByProjectDesc\n");
  }

  void searchExperimentsByName(std::vector< ::apache::airavata::model::workspace::experiment::ExperimentSummary> & _return, const std::string& gatewayId, const std::string& userName, const std::string& expName) {
    // Your implementation goes here
    printf("searchExperimentsByName\n");
  }

  void searchExperimentsByDesc(std::vector< ::apache::airavata::model::workspace::experiment::ExperimentSummary> & _return, const std::string& gatewayId, const std::string& userName, const std::string& description) {
    // Your implementation goes here
    printf("searchExperimentsByDesc\n");
  }

  void searchExperimentsByApplication(std::vector< ::apache::airavata::model::workspace::experiment::ExperimentSummary> & _return, const std::string& gatewayId, const std::string& userName, const std::string& applicationId) {
    // Your implementation goes here
    printf("searchExperimentsByApplication\n");
  }

  void searchExperimentsByStatus(std::vector< ::apache::airavata::model::workspace::experiment::ExperimentSummary> & _return, const std::string& gatewayId, const std::string& userName, const  ::apache::airavata::model::workspace::experiment::ExperimentState::type experimentState) {
    // Your implementation goes here
    printf("searchExperimentsByStatus\n");
  }

  void searchExperimentsByCreationTime(std::vector< ::apache::airavata::model::workspace::experiment::ExperimentSummary> & _return, const std::string& gatewayId, const std::string& userName, const int64_t fromTime, const int64_t toTime) {
    // Your implementation goes here
    printf("searchExperimentsByCreationTime\n");
  }

  void getAllExperimentsInProject(std::vector< ::apache::airavata::model::workspace::experiment::Experiment> & _return, const std::string& projectId) {
    // Your implementation goes here
    printf("getAllExperimentsInProject\n");
  }

  void getAllUserExperiments(std::vector< ::apache::airavata::model::workspace::experiment::Experiment> & _return, const std::string& gatewayId, const std::string& userName) {
    // Your implementation goes here
    printf("getAllUserExperiments\n");
  }

  void createExperiment(std::string& _return, const std::string& gatewayId, const  ::apache::airavata::model::workspace::experiment::Experiment& experiment) {
    // Your implementation goes here
    printf("createExperiment\n");
  }

  void getExperiment( ::apache::airavata::model::workspace::experiment::Experiment& _return, const std::string& airavataExperimentId) {
    // Your implementation goes here
    printf("getExperiment\n");
  }

  void updateExperiment(const std::string& airavataExperimentId, const  ::apache::airavata::model::workspace::experiment::Experiment& experiment) {
    // Your implementation goes here
    printf("updateExperiment\n");
  }

  void updateExperimentConfiguration(const std::string& airavataExperimentId, const  ::apache::airavata::model::workspace::experiment::UserConfigurationData& userConfiguration) {
    // Your implementation goes here
    printf("updateExperimentConfiguration\n");
  }

  void updateResourceScheduleing(const std::string& airavataExperimentId, const  ::apache::airavata::model::workspace::experiment::ComputationalResourceScheduling& resourceScheduling) {
    // Your implementation goes here
    printf("updateResourceScheduleing\n");
  }

  bool validateExperiment(const std::string& airavataExperimentId) {
    // Your implementation goes here
    printf("validateExperiment\n");
  }

  void launchExperiment(const std::string& airavataExperimentId, const std::string& airavataCredStoreToken) {
    // Your implementation goes here
    printf("launchExperiment\n");
  }

  void getExperimentStatus( ::apache::airavata::model::workspace::experiment::ExperimentStatus& _return, const std::string& airavataExperimentId) {
    // Your implementation goes here
    printf("getExperimentStatus\n");
  }

  void getExperimentOutputs(std::vector< ::apache::airavata::model::appcatalog::appinterface::OutputDataObjectType> & _return, const std::string& airavataExperimentId) {
    // Your implementation goes here
    printf("getExperimentOutputs\n");
  }

  void getIntermediateOutputs(std::vector< ::apache::airavata::model::appcatalog::appinterface::OutputDataObjectType> & _return, const std::string& airavataExperimentId) {
    // Your implementation goes here
    printf("getIntermediateOutputs\n");
  }

  void getJobStatuses(std::map<std::string,  ::apache::airavata::model::workspace::experiment::JobStatus> & _return, const std::string& airavataExperimentId) {
    // Your implementation goes here
    printf("getJobStatuses\n");
  }

  void getJobDetails(std::vector< ::apache::airavata::model::workspace::experiment::JobDetails> & _return, const std::string& airavataExperimentId) {
    // Your implementation goes here
    printf("getJobDetails\n");
  }

  void getDataTransferDetails(std::vector< ::apache::airavata::model::workspace::experiment::DataTransferDetails> & _return, const std::string& airavataExperimentId) {
    // Your implementation goes here
    printf("getDataTransferDetails\n");
  }

  void cloneExperiment(std::string& _return, const std::string& existingExperimentID, const std::string& newExperimentName) {
    // Your implementation goes here
    printf("cloneExperiment\n");
  }

  void terminateExperiment(const std::string& airavataExperimentId) {
    // Your implementation goes here
    printf("terminateExperiment\n");
  }

  void registerApplicationModule(std::string& _return, const std::string& gatewayId, const  ::apache::airavata::model::appcatalog::appdeployment::ApplicationModule& applicationModule) {
    // Your implementation goes here
    printf("registerApplicationModule\n");
  }

  void getApplicationModule( ::apache::airavata::model::appcatalog::appdeployment::ApplicationModule& _return, const std::string& appModuleId) {
    // Your implementation goes here
    printf("getApplicationModule\n");
  }

  bool updateApplicationModule(const std::string& appModuleId, const  ::apache::airavata::model::appcatalog::appdeployment::ApplicationModule& applicationModule) {
    // Your implementation goes here
    printf("updateApplicationModule\n");
  }

  void getAllAppModules(std::vector< ::apache::airavata::model::appcatalog::appdeployment::ApplicationModule> & _return, const std::string& gatewayId) {
    // Your implementation goes here
    printf("getAllAppModules\n");
  }

  bool deleteApplicationModule(const std::string& appModuleId) {
    // Your implementation goes here
    printf("deleteApplicationModule\n");
  }

  void registerApplicationDeployment(std::string& _return, const std::string& gatewayId, const  ::apache::airavata::model::appcatalog::appdeployment::ApplicationDeploymentDescription& applicationDeployment) {
    // Your implementation goes here
    printf("registerApplicationDeployment\n");
  }

  void getApplicationDeployment( ::apache::airavata::model::appcatalog::appdeployment::ApplicationDeploymentDescription& _return, const std::string& appDeploymentId) {
    // Your implementation goes here
    printf("getApplicationDeployment\n");
  }

  bool updateApplicationDeployment(const std::string& appDeploymentId, const  ::apache::airavata::model::appcatalog::appdeployment::ApplicationDeploymentDescription& applicationDeployment) {
    // Your implementation goes here
    printf("updateApplicationDeployment\n");
  }

  bool deleteApplicationDeployment(const std::string& appDeploymentId) {
    // Your implementation goes here
    printf("deleteApplicationDeployment\n");
  }

  void getAllApplicationDeployments(std::vector< ::apache::airavata::model::appcatalog::appdeployment::ApplicationDeploymentDescription> & _return, const std::string& gatewayId) {
    // Your implementation goes here
    printf("getAllApplicationDeployments\n");
  }

  void getAppModuleDeployedResources(std::vector<std::string> & _return, const std::string& appModuleId) {
    // Your implementation goes here
    printf("getAppModuleDeployedResources\n");
  }

  void registerApplicationInterface(std::string& _return, const std::string& gatewayId, const  ::apache::airavata::model::appcatalog::appinterface::ApplicationInterfaceDescription& applicationInterface) {
    // Your implementation goes here
    printf("registerApplicationInterface\n");
  }

  void getApplicationInterface( ::apache::airavata::model::appcatalog::appinterface::ApplicationInterfaceDescription& _return, const std::string& appInterfaceId) {
    // Your implementation goes here
    printf("getApplicationInterface\n");
  }

  bool updateApplicationInterface(const std::string& appInterfaceId, const  ::apache::airavata::model::appcatalog::appinterface::ApplicationInterfaceDescription& applicationInterface) {
    // Your implementation goes here
    printf("updateApplicationInterface\n");
  }

  bool deleteApplicationInterface(const std::string& appInterfaceId) {
    // Your implementation goes here
    printf("deleteApplicationInterface\n");
  }

  void getAllApplicationInterfaceNames(std::map<std::string, std::string> & _return, const std::string& gatewayId) {
    // Your implementation goes here
    printf("getAllApplicationInterfaceNames\n");
  }

  void getAllApplicationInterfaces(std::vector< ::apache::airavata::model::appcatalog::appinterface::ApplicationInterfaceDescription> & _return, const std::string& gatewayId) {
    // Your implementation goes here
    printf("getAllApplicationInterfaces\n");
  }

  void getApplicationInputs(std::vector< ::apache::airavata::model::appcatalog::appinterface::InputDataObjectType> & _return, const std::string& appInterfaceId) {
    // Your implementation goes here
    printf("getApplicationInputs\n");
  }

  void getApplicationOutputs(std::vector< ::apache::airavata::model::appcatalog::appinterface::OutputDataObjectType> & _return, const std::string& appInterfaceId) {
    // Your implementation goes here
    printf("getApplicationOutputs\n");
  }

  void getAvailableAppInterfaceComputeResources(std::map<std::string, std::string> & _return, const std::string& appInterfaceId) {
    // Your implementation goes here
    printf("getAvailableAppInterfaceComputeResources\n");
  }

  void registerComputeResource(std::string& _return, const  ::apache::airavata::model::appcatalog::computeresource::ComputeResourceDescription& computeResourceDescription) {
    // Your implementation goes here
    printf("registerComputeResource\n");
  }

  void getComputeResource( ::apache::airavata::model::appcatalog::computeresource::ComputeResourceDescription& _return, const std::string& computeResourceId) {
    // Your implementation goes here
    printf("getComputeResource\n");
  }

  void getAllComputeResourceNames(std::map<std::string, std::string> & _return) {
    // Your implementation goes here
    printf("getAllComputeResourceNames\n");
  }

  bool updateComputeResource(const std::string& computeResourceId, const  ::apache::airavata::model::appcatalog::computeresource::ComputeResourceDescription& computeResourceDescription) {
    // Your implementation goes here
    printf("updateComputeResource\n");
  }

  bool deleteComputeResource(const std::string& computeResourceId) {
    // Your implementation goes here
    printf("deleteComputeResource\n");
  }

  void addLocalSubmissionDetails(std::string& _return, const std::string& computeResourceId, const int32_t priorityOrder, const  ::apache::airavata::model::appcatalog::computeresource::LOCALSubmission& localSubmission) {
    // Your implementation goes here
    printf("addLocalSubmissionDetails\n");
  }

  bool updateLocalSubmissionDetails(const std::string& jobSubmissionInterfaceId, const  ::apache::airavata::model::appcatalog::computeresource::LOCALSubmission& localSubmission) {
    // Your implementation goes here
    printf("updateLocalSubmissionDetails\n");
  }

  void getLocalJobSubmission( ::apache::airavata::model::appcatalog::computeresource::LOCALSubmission& _return, const std::string& jobSubmissionId) {
    // Your implementation goes here
    printf("getLocalJobSubmission\n");
  }

  void addSSHJobSubmissionDetails(std::string& _return, const std::string& computeResourceId, const int32_t priorityOrder, const  ::apache::airavata::model::appcatalog::computeresource::SSHJobSubmission& sshJobSubmission) {
    // Your implementation goes here
    printf("addSSHJobSubmissionDetails\n");
  }

  void getSSHJobSubmission( ::apache::airavata::model::appcatalog::computeresource::SSHJobSubmission& _return, const std::string& jobSubmissionId) {
    // Your implementation goes here
    printf("getSSHJobSubmission\n");
  }

  void addUNICOREJobSubmissionDetails(std::string& _return, const std::string& computeResourceId, const int32_t priorityOrder, const  ::apache::airavata::model::appcatalog::computeresource::UnicoreJobSubmission& unicoreJobSubmission) {
    // Your implementation goes here
    printf("addUNICOREJobSubmissionDetails\n");
  }

  void getUnicoreJobSubmission( ::apache::airavata::model::appcatalog::computeresource::UnicoreJobSubmission& _return, const std::string& jobSubmissionId) {
    // Your implementation goes here
    printf("getUnicoreJobSubmission\n");
  }

  void addCloudJobSubmissionDetails(std::string& _return, const std::string& computeResourceId, const int32_t priorityOrder, const  ::apache::airavata::model::appcatalog::computeresource::CloudJobSubmission& cloudSubmission) {
    // Your implementation goes here
    printf("addCloudJobSubmissionDetails\n");
  }

  void getCloudJobSubmission( ::apache::airavata::model::appcatalog::computeresource::CloudJobSubmission& _return, const std::string& jobSubmissionId) {
    // Your implementation goes here
    printf("getCloudJobSubmission\n");
  }

  bool updateSSHJobSubmissionDetails(const std::string& jobSubmissionInterfaceId, const  ::apache::airavata::model::appcatalog::computeresource::SSHJobSubmission& sshJobSubmission) {
    // Your implementation goes here
    printf("updateSSHJobSubmissionDetails\n");
  }

  bool updateCloudJobSubmissionDetails(const std::string& jobSubmissionInterfaceId, const  ::apache::airavata::model::appcatalog::computeresource::CloudJobSubmission& sshJobSubmission) {
    // Your implementation goes here
    printf("updateCloudJobSubmissionDetails\n");
  }

  bool updateUnicoreJobSubmissionDetails(const std::string& jobSubmissionInterfaceId, const  ::apache::airavata::model::appcatalog::computeresource::UnicoreJobSubmission& unicoreJobSubmission) {
    // Your implementation goes here
    printf("updateUnicoreJobSubmissionDetails\n");
  }

  void addLocalDataMovementDetails(std::string& _return, const std::string& computeResourceId, const int32_t priorityOrder, const  ::apache::airavata::model::appcatalog::computeresource::LOCALDataMovement& localDataMovement) {
    // Your implementation goes here
    printf("addLocalDataMovementDetails\n");
  }

  bool updateLocalDataMovementDetails(const std::string& dataMovementInterfaceId, const  ::apache::airavata::model::appcatalog::computeresource::LOCALDataMovement& localDataMovement) {
    // Your implementation goes here
    printf("updateLocalDataMovementDetails\n");
  }

  void getLocalDataMovement( ::apache::airavata::model::appcatalog::computeresource::LOCALDataMovement& _return, const std::string& dataMovementId) {
    // Your implementation goes here
    printf("getLocalDataMovement\n");
  }

  void addSCPDataMovementDetails(std::string& _return, const std::string& computeResourceId, const int32_t priorityOrder, const  ::apache::airavata::model::appcatalog::computeresource::SCPDataMovement& scpDataMovement) {
    // Your implementation goes here
    printf("addSCPDataMovementDetails\n");
  }

  bool updateSCPDataMovementDetails(const std::string& dataMovementInterfaceId, const  ::apache::airavata::model::appcatalog::computeresource::SCPDataMovement& scpDataMovement) {
    // Your implementation goes here
    printf("updateSCPDataMovementDetails\n");
  }

  void getSCPDataMovement( ::apache::airavata::model::appcatalog::computeresource::SCPDataMovement& _return, const std::string& dataMovementId) {
    // Your implementation goes here
    printf("getSCPDataMovement\n");
  }

  void addUnicoreDataMovementDetails(std::string& _return, const std::string& computeResourceId, const int32_t priorityOrder, const  ::apache::airavata::model::appcatalog::computeresource::UnicoreDataMovement& unicoreDataMovement) {
    // Your implementation goes here
    printf("addUnicoreDataMovementDetails\n");
  }

  bool updateUnicoreDataMovementDetails(const std::string& dataMovementInterfaceId, const  ::apache::airavata::model::appcatalog::computeresource::UnicoreDataMovement& unicoreDataMovement) {
    // Your implementation goes here
    printf("updateUnicoreDataMovementDetails\n");
  }

  void getUnicoreDataMovement( ::apache::airavata::model::appcatalog::computeresource::UnicoreDataMovement& _return, const std::string& dataMovementId) {
    // Your implementation goes here
    printf("getUnicoreDataMovement\n");
  }

  void addGridFTPDataMovementDetails(std::string& _return, const std::string& computeResourceId, const int32_t priorityOrder, const  ::apache::airavata::model::appcatalog::computeresource::GridFTPDataMovement& gridFTPDataMovement) {
    // Your implementation goes here
    printf("addGridFTPDataMovementDetails\n");
  }

  bool updateGridFTPDataMovementDetails(const std::string& dataMovementInterfaceId, const  ::apache::airavata::model::appcatalog::computeresource::GridFTPDataMovement& gridFTPDataMovement) {
    // Your implementation goes here
    printf("updateGridFTPDataMovementDetails\n");
  }

  void getGridFTPDataMovement( ::apache::airavata::model::appcatalog::computeresource::GridFTPDataMovement& _return, const std::string& dataMovementId) {
    // Your implementation goes here
    printf("getGridFTPDataMovement\n");
  }

  bool changeJobSubmissionPriority(const std::string& jobSubmissionInterfaceId, const int32_t newPriorityOrder) {
    // Your implementation goes here
    printf("changeJobSubmissionPriority\n");
  }

  bool changeDataMovementPriority(const std::string& dataMovementInterfaceId, const int32_t newPriorityOrder) {
    // Your implementation goes here
    printf("changeDataMovementPriority\n");
  }

  bool changeJobSubmissionPriorities(const std::map<std::string, int32_t> & jobSubmissionPriorityMap) {
    // Your implementation goes here
    printf("changeJobSubmissionPriorities\n");
  }

  bool changeDataMovementPriorities(const std::map<std::string, int32_t> & dataMovementPriorityMap) {
    // Your implementation goes here
    printf("changeDataMovementPriorities\n");
  }

  bool deleteJobSubmissionInterface(const std::string& computeResourceId, const std::string& jobSubmissionInterfaceId) {
    // Your implementation goes here
    printf("deleteJobSubmissionInterface\n");
  }

  bool deleteDataMovementInterface(const std::string& computeResourceId, const std::string& dataMovementInterfaceId) {
    // Your implementation goes here
    printf("deleteDataMovementInterface\n");
  }

  void registerResourceJobManager(std::string& _return, const  ::apache::airavata::model::appcatalog::computeresource::ResourceJobManager& resourceJobManager) {
    // Your implementation goes here
    printf("registerResourceJobManager\n");
  }

  bool updateResourceJobManager(const std::string& resourceJobManagerId, const  ::apache::airavata::model::appcatalog::computeresource::ResourceJobManager& updatedResourceJobManager) {
    // Your implementation goes here
    printf("updateResourceJobManager\n");
  }

  void getResourceJobManager( ::apache::airavata::model::appcatalog::computeresource::ResourceJobManager& _return, const std::string& resourceJobManagerId) {
    // Your implementation goes here
    printf("getResourceJobManager\n");
  }

  bool deleteResourceJobManager(const std::string& resourceJobManagerId) {
    // Your implementation goes here
    printf("deleteResourceJobManager\n");
  }

  bool deleteBatchQueue(const std::string& computeResourceId, const std::string& queueName) {
    // Your implementation goes here
    printf("deleteBatchQueue\n");
  }

  void registerGatewayResourceProfile(std::string& _return, const  ::apache::airavata::model::appcatalog::gatewayprofile::GatewayResourceProfile& gatewayResourceProfile) {
    // Your implementation goes here
    printf("registerGatewayResourceProfile\n");
  }

  void getGatewayResourceProfile( ::apache::airavata::model::appcatalog::gatewayprofile::GatewayResourceProfile& _return, const std::string& gatewayID) {
    // Your implementation goes here
    printf("getGatewayResourceProfile\n");
  }

  bool updateGatewayResourceProfile(const std::string& gatewayID, const  ::apache::airavata::model::appcatalog::gatewayprofile::GatewayResourceProfile& gatewayResourceProfile) {
    // Your implementation goes here
    printf("updateGatewayResourceProfile\n");
  }

  bool deleteGatewayResourceProfile(const std::string& gatewayID) {
    // Your implementation goes here
    printf("deleteGatewayResourceProfile\n");
  }

  bool addGatewayComputeResourcePreference(const std::string& gatewayID, const std::string& computeResourceId, const  ::apache::airavata::model::appcatalog::gatewayprofile::ComputeResourcePreference& computeResourcePreference) {
    // Your implementation goes here
    printf("addGatewayComputeResourcePreference\n");
  }

  void getGatewayComputeResourcePreference( ::apache::airavata::model::appcatalog::gatewayprofile::ComputeResourcePreference& _return, const std::string& gatewayID, const std::string& computeResourceId) {
    // Your implementation goes here
    printf("getGatewayComputeResourcePreference\n");
  }

  void getAllGatewayComputeResourcePreferences(std::vector< ::apache::airavata::model::appcatalog::gatewayprofile::ComputeResourcePreference> & _return, const std::string& gatewayID) {
    // Your implementation goes here
    printf("getAllGatewayComputeResourcePreferences\n");
  }

  void getAllGatewayComputeResources(std::vector< ::apache::airavata::model::appcatalog::gatewayprofile::GatewayResourceProfile> & _return) {
    // Your implementation goes here
    printf("getAllGatewayComputeResources\n");
  }

  bool updateGatewayComputeResourcePreference(const std::string& gatewayID, const std::string& computeResourceId, const  ::apache::airavata::model::appcatalog::gatewayprofile::ComputeResourcePreference& computeResourcePreference) {
    // Your implementation goes here
    printf("updateGatewayComputeResourcePreference\n");
  }

  bool deleteGatewayComputeResourcePreference(const std::string& gatewayID, const std::string& computeResourceId) {
    // Your implementation goes here
    printf("deleteGatewayComputeResourcePreference\n");
  }

  void getAllWorkflows(std::vector<std::string> & _return, const std::string& gatewayId) {
    // Your implementation goes here
    printf("getAllWorkflows\n");
  }

  void getWorkflow( ::Workflow& _return, const std::string& workflowTemplateId) {
    // Your implementation goes here
    printf("getWorkflow\n");
  }

  void deleteWorkflow(const std::string& workflowTemplateId) {
    // Your implementation goes here
    printf("deleteWorkflow\n");
  }

  void registerWorkflow(std::string& _return, const std::string& gatewayId, const  ::Workflow& workflow) {
    // Your implementation goes here
    printf("registerWorkflow\n");
  }

  void updateWorkflow(const std::string& workflowTemplateId, const  ::Workflow& workflow) {
    // Your implementation goes here
    printf("updateWorkflow\n");
  }

  void getWorkflowTemplateId(std::string& _return, const std::string& workflowName) {
    // Your implementation goes here
    printf("getWorkflowTemplateId\n");
  }

  bool isWorkflowExistWithName(const std::string& workflowName) {
    // Your implementation goes here
    printf("isWorkflowExistWithName\n");
  }

};

int main(int argc, char **argv) {
  int port = 9090;
  shared_ptr<AiravataHandler> handler(new AiravataHandler());
  shared_ptr<TProcessor> processor(new AiravataProcessor(handler));
  shared_ptr<TServerTransport> serverTransport(new TServerSocket(port));
  shared_ptr<TTransportFactory> transportFactory(new TBufferedTransportFactory());
  shared_ptr<TProtocolFactory> protocolFactory(new TBinaryProtocolFactory());

  TSimpleServer server(processor, serverTransport, transportFactory, protocolFactory);
  server.serve();
  return 0;
}

