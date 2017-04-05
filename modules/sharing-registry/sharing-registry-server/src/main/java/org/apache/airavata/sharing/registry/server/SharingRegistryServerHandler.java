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
package org.apache.airavata.sharing.registry.server;

import org.apache.airavata.common.exception.ApplicationSettingsException;
import org.apache.airavata.sharing.registry.db.entities.*;
import org.apache.airavata.sharing.registry.db.repositories.*;
import org.apache.airavata.sharing.registry.db.utils.DBConstants;
import org.apache.airavata.sharing.registry.db.utils.JPAUtils;
import org.apache.airavata.sharing.registry.models.*;
import org.apache.airavata.sharing.registry.service.cpi.SharingRegistryService;
import org.apache.thrift.TException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.*;

public class SharingRegistryServerHandler implements SharingRegistryService.Iface{
    private final static Logger logger = LoggerFactory.getLogger(SharingRegistryServerHandler.class);

    public static String OWNER_PERMISSION_NAME = "OWNER";

    public SharingRegistryServerHandler() throws ApplicationSettingsException, TException {
        JPAUtils.initializeDB();
    }

    /**
     * * Domain Operations
     * *
     */
    @Override
    public String createDomain(Domain domain) throws SharingRegistryException, TException {
        try{
            domain.setDomainId(domain.name);
            if((new DomainRepository()).get(domain.domainId) != null)
                throw new SharingRegistryException("There exist domain with given domain id");

            domain.setCreatedTime(System.currentTimeMillis());
            domain.setUpdatedTime(System.currentTimeMillis());
            (new DomainRepository()).create(domain);

            //create the global permission for the domain
            PermissionType permissionType = new PermissionType();
            permissionType.setPermissionTypeId(domain.domainId + ":" + OWNER_PERMISSION_NAME);
            permissionType.setDomainId(domain.domainId);
            permissionType.setName(OWNER_PERMISSION_NAME);
            permissionType.setDescription("GLOBAL permission to " + domain.domainId);
            permissionType.setCreatedTime(System.currentTimeMillis());
            permissionType.setUpdatedTime(System.currentTimeMillis());
            (new PermissionTypeRepository()).create(permissionType);

            return domain.domainId;
        }catch (SharingRegistryException ex){
            logger.error(ex.getMessage(), ex);
            throw ex;
        }
    }

    @Override
    public boolean updateDomain(Domain domain) throws SharingRegistryException, TException {
        try{
            Domain oldDomain = (new DomainRepository()).get(domain.domainId);
            domain.setCreatedTime(oldDomain.createdTime);
            domain.setUpdatedTime(System.currentTimeMillis());
            domain = getUpdatedObject(oldDomain, domain);
            (new DomainRepository()).update(domain);
            return true;
        }catch (SharingRegistryException ex) {
            logger.error(ex.getMessage(), ex);
            throw ex;
        }
    }

    @Override
    public boolean deleteDomain(String domainId) throws SharingRegistryException, TException {
        try{
            (new DomainRepository()).delete(domainId);
            return true;
        }catch (SharingRegistryException ex) {
            logger.error(ex.getMessage(), ex);
            throw ex;
        }
    }

    @Override
    public Domain getDomain(String domainId) throws SharingRegistryException, TException {
        try{
            return (new DomainRepository()).get(domainId);
        }catch (SharingRegistryException ex) {
            logger.error(ex.getMessage(), ex);
            throw ex;
        }
    }

    @Override
    public List<Domain> getDomains(int offset, int limit) throws TException {
        try{
            return (new DomainRepository()).select(new HashMap<>(), offset, limit);
        }catch (SharingRegistryException ex) {
            logger.error(ex.getMessage(), ex);
            throw ex;
        }
    }

    /**
     * * User Operations
     * *
     */
    @Override
    public String createUser(User user) throws SharingRegistryException, TException {
        try{
            UserPK userPK = new UserPK();
            userPK.setUserId(user.getUserId());
            userPK.setDomainId(user.domainId);
            if((new UserRepository()).get(userPK) != null)
                throw new SharingRegistryException("There exist user with given user id");

            user.setCreatedTime(System.currentTimeMillis());
            user.setUpdatedTime(System.currentTimeMillis());
            (new UserRepository()).create(user);

            UserGroup userGroup = new UserGroup();
            userGroup.setGroupId(user.userId);
            userGroup.setDomainId(user.domainId);
            userGroup.setName(user.userName);
            userGroup.setDescription("user " + user.userName + " group");
            userGroup.setOwnerId(user.userId);
            userGroup.setGroupType(GroupType.USER_LEVEL_GROUP);
            userGroup.setGroupCardinality(GroupCardinality.SINGLE_USER);
            (new UserGroupRepository()).create(userGroup);

            return user.userId;
        }catch (SharingRegistryException ex) {
            logger.error(ex.getMessage(), ex);
            throw ex;
        }
    }

    @Override
    public boolean updatedUser(User user) throws SharingRegistryException, TException {
        try{
            UserPK userPK = new UserPK();
            userPK.setUserId(user.userId);
            userPK.setDomainId(user.domainId);
            User oldUser = (new UserRepository()).get(userPK);
            user.setCreatedTime(oldUser.createdTime);
            user.setUpdatedTime(System.currentTimeMillis());
            user = getUpdatedObject(oldUser, user);
            (new UserRepository()).update(user);

            UserGroupPK userGroupPK = new UserGroupPK();
            userGroupPK.setGroupId(user.getUserId());
            userGroupPK.setDomainId(user.domainId);
            UserGroup userGroup = (new UserGroupRepository()).get(userGroupPK);
            userGroup.setName(user.userName);
            userGroup.setDescription("user " + user.userName + " group");
            updateGroup(userGroup);
            return true;
        }catch (SharingRegistryException ex) {
            logger.error(ex.getMessage(), ex);
            throw ex;
        }
    }

    @Override
    public boolean deleteUser(String domainId, String userId) throws SharingRegistryException, TException {
        try{
            UserPK userPK = new UserPK();
            userPK.setUserId(userId);
            userPK.setDomainId(domainId);
            (new UserRepository()).delete(userPK);

            UserGroupPK userGroupPK = new UserGroupPK();
            userGroupPK.setGroupId(userId);
            userGroupPK.setDomainId(domainId);
            (new UserGroupRepository()).delete(userGroupPK);
            return true;
        }catch (SharingRegistryException ex) {
            logger.error(ex.getMessage(), ex);
            throw ex;
        }
    }

    @Override
    public User getUser(String domainId, String userId) throws SharingRegistryException, TException {
        try{
            UserPK userPK = new UserPK();
            userPK.setUserId(userId);
            userPK.setDomainId(domainId);
            return (new UserRepository()).get(userPK);
        }catch (SharingRegistryException ex) {
            logger.error(ex.getMessage(), ex);
            throw ex;
        }
    }

    @Override
    public List<User> getUsers(String domain, int offset, int limit) throws SharingRegistryException, TException {
        try{
            HashMap<String, String> filters = new HashMap<>();
            filters.put(DBConstants.UserTable.DOMAIN_ID, domain);
            return (new UserRepository()).select(filters, offset, limit);
        }catch (SharingRegistryException ex) {
            logger.error(ex.getMessage(), ex);
            throw ex;
        }
    }

    /**
     * * Group Operations
     * *
     */
    @Override
    public String createGroup(UserGroup group) throws SharingRegistryException, TException {
        try{
            UserGroupPK userGroupPK = new UserGroupPK();
            userGroupPK.setGroupId(group.groupId);
            userGroupPK.setDomainId(group.domainId);
            if((new UserGroupRepository()).get(userGroupPK) != null)
                throw new SharingRegistryException("There exist group with given group id");
            //Client created groups are always of type MULTI_USER
            group.setGroupCardinality(GroupCardinality.MULTI_USER);
            group.setCreatedTime(System.currentTimeMillis());
            group.setUpdatedTime(System.currentTimeMillis());
            (new UserGroupRepository()).create(group);

            addUsersToGroup(group.domainId, Arrays.asList(group.ownerId), group.groupId);
            return group.groupId;
        }catch (SharingRegistryException ex) {
            logger.error(ex.getMessage(), ex);
            throw ex;
        }
    }

    @Override
    public boolean updateGroup(UserGroup group) throws SharingRegistryException, TException {
        try{
            group.setUpdatedTime(System.currentTimeMillis());
            UserGroupPK userGroupPK = new UserGroupPK();
            userGroupPK.setGroupId(group.groupId);
            userGroupPK.setDomainId(group.domainId);
            UserGroup oldGroup = (new UserGroupRepository()).get(userGroupPK);
            //Client created groups are always of type MULTI_USER
            group.setGroupCardinality(GroupCardinality.MULTI_USER);
            group.setCreatedTime(oldGroup.createdTime);
            group = getUpdatedObject(oldGroup, group);

            if(!group.ownerId.equals(oldGroup.ownerId))
                throw new SharingRegistryException("Group owner cannot be changed");

            (new UserGroupRepository()).update(group);
            return true;
        }catch (SharingRegistryException ex) {
            logger.error(ex.getMessage(), ex);
            throw ex;
        }
    }

    @Override
    public boolean deleteGroup(String domainId, String groupId) throws SharingRegistryException, TException {
        try{
            UserGroupPK userGroupPK = new UserGroupPK();
            userGroupPK.setGroupId(groupId);
            userGroupPK.setDomainId(domainId);
            (new UserGroupRepository()).delete(userGroupPK);
            return true;
        }catch (SharingRegistryException ex) {
            logger.error(ex.getMessage(), ex);
            throw ex;
        }
    }

    @Override
    public UserGroup getGroup(String domainId, String groupId) throws SharingRegistryException, TException {
        try{
            UserGroupPK userGroupPK = new UserGroupPK();
            userGroupPK.setGroupId(groupId);
            userGroupPK.setDomainId(domainId);
            return (new UserGroupRepository()).get(userGroupPK);
        }catch (SharingRegistryException ex) {
            logger.error(ex.getMessage(), ex);
            throw ex;
        }
    }

    @Override
    public List<UserGroup> getGroups(String domain, int offset, int limit) throws TException {
        try{
            HashMap<String, String> filters = new HashMap<>();
            filters.put(DBConstants.UserTable.DOMAIN_ID, domain);
            return (new UserGroupRepository()).select(filters, offset, limit);
        }catch (SharingRegistryException ex) {
            logger.error(ex.getMessage(), ex);
            throw ex;
        }
    }

    @Override
    public boolean addUsersToGroup(String domainId, List<String> userIds, String groupId) throws SharingRegistryException, TException {
        try{
            for(int i=0; i < userIds.size(); i++){
                GroupMembership groupMembership = new GroupMembership();
                groupMembership.setParentId(groupId);
                groupMembership.setChildId(userIds.get(i));
                groupMembership.setChildType(GroupChildType.USER);
                groupMembership.setDomainId(domainId);
                groupMembership.setCreatedTime(System.currentTimeMillis());
                groupMembership.setUpdatedTime(System.currentTimeMillis());
                (new GroupMembershipRepository()).create(groupMembership);
            }
            return true;
        }catch (SharingRegistryException ex) {
            logger.error(ex.getMessage(), ex);
            throw ex;
        }
    }

    @Override
    public boolean removeUsersFromGroup(String domainId, List<String> userIds, String groupId) throws SharingRegistryException, TException {
        try{
            for(int i=0; i < userIds.size(); i++){
                GroupMembershipPK groupMembershipPK = new GroupMembershipPK();
                groupMembershipPK.setParentId(groupId);
                groupMembershipPK.setChildId(userIds.get(i));
                groupMembershipPK.setDomainId(domainId);
                (new GroupMembershipRepository()).delete(groupMembershipPK);
            }
            return true;
        }catch (SharingRegistryException ex) {
            logger.error(ex.getMessage(), ex);
            throw ex;
        }
    }

    @Override
    public List<User> getGroupMembersOfTypeUser(String domainId, String groupId, int offset, int limit) throws SharingRegistryException, TException {
        try{
            //TODO limit offset
            List<User> groupMemberUsers = (new GroupMembershipRepository()).getAllChildUsers(domainId, groupId);
            return groupMemberUsers;
        }catch (SharingRegistryException ex) {
            logger.error(ex.getMessage(), ex);
            throw ex;
        }
    }

    @Override
    public List<UserGroup> getGroupMembersOfTypeGroup(String domainId, String groupId, int offset, int limit) throws SharingRegistryException, TException {
        try{
            //TODO limit offset
            List<UserGroup> groupMemberGroups = (new GroupMembershipRepository()).getAllChildGroups(domainId, groupId);
            return groupMemberGroups;
        }catch (SharingRegistryException ex) {
            logger.error(ex.getMessage(), ex);
            throw ex;
        }
    }

    @Override
    public boolean addChildGroupsToParentGroup(String domainId, List<String> childIds, String groupId) throws SharingRegistryException, TException {
        try{
            for(String childId : childIds) {
                //Todo check for cyclic dependencies
                GroupMembership groupMembership = new GroupMembership();
                groupMembership.setParentId(groupId);
                groupMembership.setChildId(childId);
                groupMembership.setChildType(GroupChildType.GROUP);
                groupMembership.setDomainId(domainId);
                groupMembership.setCreatedTime(System.currentTimeMillis());
                groupMembership.setUpdatedTime(System.currentTimeMillis());
                (new GroupMembershipRepository()).create(groupMembership);
            }
            return true;
        }catch (SharingRegistryException ex) {
            logger.error(ex.getMessage(), ex);
            throw ex;
        }
    }

    @Override
    public boolean removeChildGroupFromParentGroup(String domainId, String childId, String groupId) throws SharingRegistryException, TException {
        try{
            GroupMembershipPK groupMembershipPK = new GroupMembershipPK();
            groupMembershipPK.setParentId(groupId);
            groupMembershipPK.setChildId(childId);
            groupMembershipPK.setDomainId(domainId);
            (new GroupMembershipRepository()).delete(groupMembershipPK);
            return true;
        }catch (SharingRegistryException ex) {
            logger.error(ex.getMessage(), ex);
            throw ex;
        }
    }

    /**
     * * EntityType Operations
     * *
     */
    @Override
    public String createEntityType(EntityType entityType) throws SharingRegistryException, TException {
        try{
            EntityTypePK entityTypePK = new EntityTypePK();
            entityTypePK.setDomainId(entityType.domainId);
            entityTypePK.setEntityTypeId(entityType.entityTypeId);
            if((new EntityTypeRepository()).get(entityTypePK) != null)
                throw new SharingRegistryException("There exist EntityType with given EntityType id");

            entityType.setCreatedTime(System.currentTimeMillis());
            entityType.setUpdatedTime(System.currentTimeMillis());
            (new EntityTypeRepository()).create(entityType);
            return entityType.entityTypeId;
        }catch (SharingRegistryException ex) {
            logger.error(ex.getMessage(), ex);
            throw ex;
        }
    }

    @Override
    public boolean updateEntityType(EntityType entityType) throws SharingRegistryException, TException {
        try{
            entityType.setUpdatedTime(System.currentTimeMillis());
            EntityTypePK entityTypePK = new EntityTypePK();
            entityTypePK.setDomainId(entityType.domainId);
            entityTypePK.setEntityTypeId(entityType.entityTypeId);
            EntityType oldEntityType = (new EntityTypeRepository()).get(entityTypePK);
            entityType.setCreatedTime(oldEntityType.createdTime);
            entityType = getUpdatedObject(oldEntityType, entityType);
            (new EntityTypeRepository()).update(entityType);
            return true;
        }catch (SharingRegistryException ex) {
            logger.error(ex.getMessage(), ex);
            throw ex;
        }
    }

    @Override
    public boolean deleteEntityType(String domainId, String entityTypeId) throws SharingRegistryException, TException {
        try{
            EntityTypePK entityTypePK = new EntityTypePK();
            entityTypePK.setDomainId(domainId);
            entityTypePK.setEntityTypeId(entityTypeId);
            (new EntityTypeRepository()).delete(entityTypePK);
            return true;
        }catch (SharingRegistryException ex) {
            logger.error(ex.getMessage(), ex);
            throw ex;
        }
    }

    @Override
    public EntityType getEntityType(String domainId, String entityTypeId) throws SharingRegistryException, TException {
        try{
            EntityTypePK entityTypePK = new EntityTypePK();
            entityTypePK.setDomainId(domainId);
            entityTypePK.setEntityTypeId(entityTypeId);
            return (new EntityTypeRepository()).get(entityTypePK);
        }catch (SharingRegistryException ex) {
            logger.error(ex.getMessage(), ex);
            throw ex;
        }
    }

    @Override
    public List<EntityType> getEntityTypes(String domain, int offset, int limit) throws TException {
        try{
            HashMap<String, String> filters = new HashMap<>();
            filters.put(DBConstants.EntityTypeTable.DOMAIN_ID, domain);
            return (new EntityTypeRepository()).select(domain, offset, limit);
        }catch (SharingRegistryException ex) {
            logger.error(ex.getMessage(), ex);
            throw ex;
        }
    }

    /**
     * * Permission Operations
     * *
     */
    @Override
    public String createPermissionType(PermissionType permissionType) throws SharingRegistryException, TException {
        try{
            PermissionTypePK permissionTypePK =  new PermissionTypePK();
            permissionTypePK.setDomainId(permissionType.domainId);
            permissionTypePK.setPermissionTypeId(permissionType.permissionTypeId);
            if((new PermissionTypeRepository()).get(permissionTypePK) != null)
                throw new SharingRegistryException("There exist PermissionType with given PermissionType id");
            permissionType.setCreatedTime(System.currentTimeMillis());
            permissionType.setUpdatedTime(System.currentTimeMillis());
            (new PermissionTypeRepository()).create(permissionType);
            return permissionType.permissionTypeId;
        }catch (SharingRegistryException ex) {
            logger.error(ex.getMessage(), ex);
            throw ex;
        }
    }

    @Override
    public boolean updatePermissionType(PermissionType permissionType) throws SharingRegistryException, TException {
        try{
            permissionType.setUpdatedTime(System.currentTimeMillis());
            PermissionTypePK permissionTypePK =  new PermissionTypePK();
            permissionTypePK.setDomainId(permissionType.domainId);
            permissionTypePK.setPermissionTypeId(permissionType.permissionTypeId);
            PermissionType oldPermissionType = (new PermissionTypeRepository()).get(permissionTypePK);
            permissionType = getUpdatedObject(oldPermissionType, permissionType);
            (new PermissionTypeRepository()).update(permissionType);
            return true;
        }catch (SharingRegistryException ex) {
            logger.error(ex.getMessage(), ex);
            throw ex;
        }
    }

    @Override
    public boolean deletePermissionType(String domainId, String permissionTypeId) throws SharingRegistryException, TException {
        try{
            PermissionTypePK permissionTypePK =  new PermissionTypePK();
            permissionTypePK.setDomainId(domainId);
            permissionTypePK.setPermissionTypeId(permissionTypeId);
            (new PermissionTypeRepository()).delete(permissionTypePK);
            return true;
        }catch (SharingRegistryException ex) {
            logger.error(ex.getMessage(), ex);
            throw ex;
        }
    }

    @Override
    public PermissionType getPermissionType(String domainId, String permissionTypeId) throws SharingRegistryException, TException {
        try{
            PermissionTypePK permissionTypePK =  new PermissionTypePK();
            permissionTypePK.setDomainId(domainId);
            permissionTypePK.setPermissionTypeId(permissionTypeId);
            return (new PermissionTypeRepository()).get(permissionTypePK);
        }catch (SharingRegistryException ex) {
            logger.error(ex.getMessage(), ex);
            throw ex;
        }
    }

    @Override
    public List<PermissionType> getPermissionTypes(String domain, int offset, int limit) throws SharingRegistryException, TException {
        try{
            HashMap<String, String> filters = new HashMap<>();
            filters.put(DBConstants.PermissionTypeTable.DOMAIN_ID, domain);
            return (new PermissionTypeRepository()).select(filters, offset, limit);
        }catch (SharingRegistryException ex) {
            logger.error(ex.getMessage(), ex);
            throw ex;
        }
    }

    /**
     * * Entity Operations
     * *
     */
    @Override
    public String createEntity(Entity entity) throws SharingRegistryException, TException {
        try{
            EntityPK entityPK = new EntityPK();
            entityPK.setDomainId(entity.domainId);
            entityPK.setEntityId(entity.entityId);
            if((new EntityRepository()).get(entityPK) != null)
                throw new SharingRegistryException("There exist Entity with given Entity id");

            UserPK userPK = new UserPK();
            userPK.setDomainId(entity.domainId);
            userPK.setUserId(entity.ownerId);
            if(!(new UserRepository()).isExists(userPK)){
                //Todo this is for Airavata easy integration. Proper thing is to throw an exception here
                User user = new User();
                user.setUserId(entity.getOwnerId());
                user.setDomainId(entity.domainId);
                user.setUserName(user.userId.split("@")[0]);

                createUser(user);
            }
            entity.setCreatedTime(System.currentTimeMillis());
            entity.setUpdatedTime(System.currentTimeMillis());

            if(entity.originalEntityCreationTime==0){
                entity.originalEntityCreationTime = entity.createdTime;
            }
            (new EntityRepository()).create(entity);

            //Assigning global permission for the owner
            Sharing newSharing = new Sharing();
            newSharing.setPermissionTypeId((new PermissionTypeRepository()).getOwnerPermissionTypeIdForDomain(entity.domainId));
            newSharing.setEntityId(entity.entityId);
            newSharing.setGroupId(entity.ownerId);
            newSharing.setSharingType(SharingType.DIRECT_CASCADING);
            newSharing.setInheritedParentId(entity.entityId);
            newSharing.setDomainId(entity.domainId);
            newSharing.setCreatedTime(System.currentTimeMillis());
            newSharing.setUpdatedTime(System.currentTimeMillis());

            (new SharingRepository()).create(newSharing);

            //creating records for inherited permissions
            if(entity.getParentEntityId() != null && entity.getParentEntityId() != ""){
                List<Sharing> sharings = (new SharingRepository()).getCascadingPermissionsForEntity(entity.domainId, entity.parentEntityId);
                for(Sharing sharing : sharings){
                    newSharing = new Sharing();
                    newSharing.setPermissionTypeId(sharing.permissionTypeId);
                    newSharing.setEntityId(entity.entityId);
                    newSharing.setGroupId(sharing.groupId);
                    newSharing.setInheritedParentId(sharing.inheritedParentId);
                    newSharing.setSharingType(SharingType.INDIRECT_CASCADING);
                    newSharing.setDomainId(entity.domainId);
                    newSharing.setCreatedTime(System.currentTimeMillis());
                    newSharing.setUpdatedTime(System.currentTimeMillis());

                    (new SharingRepository()).create(newSharing);
                }
            }

            return entity.entityId;
        }catch (SharingRegistryException ex) {
            logger.error(ex.getMessage(), ex);
            throw ex;
        }
    }

    @Override
    public boolean updateEntity(Entity entity) throws SharingRegistryException, TException {
        try{
            //checks whether the entity is shared with anyone
            entity.setShared((new UserGroupRepository()).isShared(entity.domainId, entity.entityId));

            entity.setUpdatedTime(System.currentTimeMillis());
            EntityPK entityPK = new EntityPK();
            entityPK.setDomainId(entity.domainId);
            entityPK.setEntityId(entity.entityId);
            Entity oldEntity = (new EntityRepository()).get(entityPK);
            entity.setCreatedTime(oldEntity.createdTime);
            entity = getUpdatedObject(oldEntity, entity);
            (new EntityRepository()).update(entity);
            return true;
        }catch (SharingRegistryException ex) {
            logger.error(ex.getMessage(), ex);
            throw ex;
        }
    }

    @Override
    public boolean deleteEntity(String domainId, String entityId) throws SharingRegistryException, TException {
        try{
            //TODO Check for permission changes
            EntityPK entityPK = new EntityPK();
            entityPK.setDomainId(domainId);
            entityPK.setEntityId(entityId);
            (new EntityRepository()).delete(entityPK);
            return true;
        }catch (SharingRegistryException ex) {
            logger.error(ex.getMessage(), ex);
            throw ex;
        }
    }

    @Override
    public Entity getEntity(String domainId, String entityId) throws SharingRegistryException, TException {
        try{
            EntityPK entityPK = new EntityPK();
            entityPK.setDomainId(domainId);
            entityPK.setEntityId(entityId);
            return (new EntityRepository()).get(entityPK);
        }catch (SharingRegistryException ex) {
            logger.error(ex.getMessage(), ex);
            throw ex;
        }
    }

    @Override
    public List<Entity> searchEntities(String domainId, String userId, List<SearchCriteria> filters,
                                       int offset, int limit) throws SharingRegistryException, TException {
        try{
            List<String> groupIds = new ArrayList<>();
            groupIds.add(userId);
            (new GroupMembershipRepository()).getAllParentMembershipsForChild(domainId, userId).stream().forEach(gm -> groupIds.add(gm.parentId));
            return (new EntityRepository()).searchEntities(domainId, groupIds, filters, offset, limit);
        }catch (SharingRegistryException ex) {
            logger.error(ex.getMessage(), ex);
            throw ex;
        }
    }

    @Override
    public List<User> getListOfSharedUsers(String domainId, String entityId, String permissionTypeId) throws SharingRegistryException, TException {
        try{
            return (new UserRepository()).getAccessibleUsers(domainId, entityId, permissionTypeId);
        }catch (SharingRegistryException ex) {
            logger.error(ex.getMessage(), ex);
            throw ex;
        }
    }

    @Override
    public List<UserGroup> getListOfSharedGroups(String domainId, String entityId, String permissionTypeId) throws SharingRegistryException, TException {
        try{
            return (new UserGroupRepository()).getAccessibleGroups(domainId, entityId, permissionTypeId);
        }catch (SharingRegistryException ex) {
            logger.error(ex.getMessage(), ex);
            throw ex;
        }
    }

    /**
     * * Sharing Entity with Users and Groups
     * *
     *
     * @param entityId
     * @param userList
     * @param permissionType
     */
    @Override
    public boolean shareEntityWithUsers(String domainId, String entityId, List<String> userList, String permissionTypeId, boolean cascadePermission) throws SharingRegistryException, TException {
        try{
            boolean result = shareEntity(domainId, entityId, userList, permissionTypeId, cascadePermission);
            EntityPK entityPK = new EntityPK();
            entityPK.setEntityId(entityId);
            entityPK.setDomainId(domainId);
            Entity entity = (new EntityRepository()).get(entityPK);
            entity.setShared(true);
            (new EntityRepository()).update(entity);
            return result;
        }catch (SharingRegistryException ex) {
            logger.error(ex.getMessage(), ex);
            throw ex;
        }
    }

    @Override
    public boolean shareEntityWithGroups(String domainId, String entityId, List<String> groupList, String permissionTypeId, boolean cascadePermission) throws SharingRegistryException, TException {
        try{
            boolean result = shareEntity(domainId, entityId, groupList, permissionTypeId, cascadePermission);
            EntityPK entityPK = new EntityPK();
            entityPK.setEntityId(entityId);
            entityPK.setDomainId(domainId);
            Entity entity = (new EntityRepository()).get(entityPK);
            entity.setShared(true);
            (new EntityRepository()).update(entity);
            return result;
        }catch (SharingRegistryException ex) {

            logger.error(ex.getMessage(), ex);
            throw ex;
        }
    }

    private boolean shareEntity(String domainId, String entityId, List<String> groupOrUserList, String permissionTypeId, boolean cascadePermission)  throws SharingRegistryException, TException {
        try{
            if(permissionTypeId.equals((new PermissionTypeRepository()).getOwnerPermissionTypeIdForDomain(domainId))){
                throw new SharingRegistryException(OWNER_PERMISSION_NAME + " permission cannot be assigned or removed");
            }

            List<Sharing> sharings = new ArrayList<>();

            //Adding permission for the specified users/groups for the specified entity
            LinkedList<Entity> temp = new LinkedList<>();
            for(String userId : groupOrUserList){
                Sharing sharing = new Sharing();
                sharing.setPermissionTypeId(permissionTypeId);
                sharing.setEntityId(entityId);
                sharing.setGroupId(userId);
                sharing.setInheritedParentId(entityId);
                sharing.setDomainId(domainId);
                if(cascadePermission) {
                    sharing.setSharingType(SharingType.DIRECT_CASCADING);
                }else {
                    sharing.setSharingType(SharingType.DIRECT_NON_CASCADING);
                }
                sharing.setCreatedTime(System.currentTimeMillis());
                sharing.setUpdatedTime(System.currentTimeMillis());

                sharings.add(sharing);
            }

            if(cascadePermission){
                //Adding permission for the specified users/groups for all child entities
                (new EntityRepository()).getChildEntities(domainId, entityId).stream().forEach(e -> temp.addLast(e));
                while(temp.size() > 0){
                    Entity entity = temp.pop();
                    String childEntityId = entity.entityId;
                    for(String userId : groupOrUserList){
                        Sharing sharing = new Sharing();
                        sharing.setPermissionTypeId(permissionTypeId);
                        sharing.setEntityId(childEntityId);
                        sharing.setGroupId(userId);
                        sharing.setInheritedParentId(entityId);
                        sharing.setSharingType(SharingType.INDIRECT_CASCADING);
                        sharing.setInheritedParentId(entityId);
                        sharing.setDomainId(domainId);
                        sharing.setCreatedTime(System.currentTimeMillis());
                        sharing.setUpdatedTime(System.currentTimeMillis());
                        sharings.add(sharing);
                        (new EntityRepository()).getChildEntities(domainId, childEntityId).stream().forEach(e -> temp.addLast(e));
                    }
                }
            }
            (new SharingRepository()).create(sharings);
            return true;
        }catch (SharingRegistryException ex) {
            logger.error(ex.getMessage(), ex);
            throw ex;
        }
    }

    @Override
    public boolean revokeEntitySharingFromUsers(String domainId, String entityId, List<String> userList, String permissionTypeId) throws SharingRegistryException, TException {
        try{
            if(permissionTypeId.equals((new PermissionTypeRepository()).getOwnerPermissionTypeIdForDomain(domainId))){
                throw new SharingRegistryException(OWNER_PERMISSION_NAME + " permission cannot be assigned or removed");
            }
            boolean result = revokeEntitySharing(domainId, entityId, userList, permissionTypeId);
            EntityPK entityPK = new EntityPK();
            entityPK.setEntityId(entityId);
            entityPK.setDomainId(domainId);
            Entity entity = (new EntityRepository()).get(entityPK);
            entity.setShared((new UserGroupRepository()).isShared(domainId, entityId));
            (new EntityRepository()).update(entity);
            return result;
        }catch (SharingRegistryException ex) {
            logger.error(ex.getMessage(), ex);
            throw ex;
        }
    }


    @Override
    public boolean revokeEntitySharingFromGroups(String domainId, String entityId, List<String> groupList, String permissionTypeId) throws SharingRegistryException, TException {
        try{
            if(permissionTypeId.equals((new PermissionTypeRepository()).getOwnerPermissionTypeIdForDomain(domainId))){
                throw new SharingRegistryException(OWNER_PERMISSION_NAME + " permission cannot be assigned or removed");
            }
            boolean result = revokeEntitySharing(domainId, entityId, groupList, permissionTypeId);
            EntityPK entityPK = new EntityPK();
            entityPK.setEntityId(entityId);
            entityPK.setDomainId(domainId);
            Entity entity = (new EntityRepository()).get(entityPK);
            entity.setShared((new UserGroupRepository()).isShared(domainId, entityId));
            (new EntityRepository()).update(entity);
            return result;
        }catch (SharingRegistryException ex) {
            logger.error(ex.getMessage(), ex);
            throw ex;
        }
    }

    @Override
    public boolean userHasAccess(String domainId, String userId, String entityId, String permissionTypeId) throws SharingRegistryException, TException {
        try{
            //check whether the user has permission directly or indirectly
            List<GroupMembership> parentMemberships = (new GroupMembershipRepository()).getAllParentMembershipsForChild(domainId, userId);
            List<String> groupIds = new ArrayList<>();
            parentMemberships.stream().forEach(pm->groupIds.add(pm.parentId));
            groupIds.add(userId);
            return (new SharingRepository()).hasAccess(domainId, entityId, groupIds, Arrays.asList(permissionTypeId,
                    (new PermissionTypeRepository()).getOwnerPermissionTypeIdForDomain(domainId)));
        }catch (SharingRegistryException ex) {
            logger.error(ex.getMessage(), ex);
            throw ex;
        }
    }

    public boolean revokeEntitySharing(String domainId, String entityId, List<String> groupOrUserList, String permissionTypeId) throws SharingRegistryException {
        try{
            if(permissionTypeId.equals((new PermissionTypeRepository()).getOwnerPermissionTypeIdForDomain(domainId))){
                throw new SharingRegistryException(OWNER_PERMISSION_NAME + " permission cannot be removed");
            }

            SharingRepository sharingRepository = new SharingRepository();

            //revoking permission for the entity
            for(String groupId : groupOrUserList){
                SharingPK sharingPK = new SharingPK();
                sharingPK.setEntityId(entityId);
                sharingPK.setGroupId(groupId);
                sharingPK.setPermissionTypeId(permissionTypeId);
                sharingPK.setInheritedParentId(entityId);
                sharingPK.setDomainId(domainId);

                sharingRepository.delete(sharingPK);
            }

            //revoking permission from inheritance
            List<Sharing> temp = new ArrayList<>();
            (new SharingRepository()).getIndirectSharedChildren(domainId, entityId, permissionTypeId).stream().forEach(s -> temp.add(s));
            for(Sharing sharing : temp){
                String childEntityId = sharing.entityId;
                for(String groupId : groupOrUserList){
                    SharingPK sharingPK = new SharingPK();
                    sharingPK.setEntityId(childEntityId);
                    sharingPK.setGroupId(groupId);
                    sharingPK.setPermissionTypeId(permissionTypeId);
                    sharingPK.setInheritedParentId(entityId);
                    sharingPK.setDomainId(domainId);

                    sharingRepository.delete(sharingPK);
                }
            }
            return true;
        }catch (SharingRegistryException ex) {
            logger.error(ex.getMessage(), ex);
            throw ex;
        }

    }



    private <T> T getUpdatedObject(T oldEntity, T newEntity) throws SharingRegistryException {
        Field[] newEntityFields = newEntity.getClass().getDeclaredFields();
        Hashtable newHT = fieldsToHT(newEntityFields, newEntity);

        Class oldEntityClass = oldEntity.getClass();
        Field[] oldEntityFields = oldEntityClass.getDeclaredFields();

        for (Field field : oldEntityFields){
            field.setAccessible(true);
            Object o = newHT.get(field.getName());
            if (o != null){
                Field f = null;
                try {
                    f = oldEntityClass.getDeclaredField(field.getName());
                    f.setAccessible(true);
                    logger.debug("setting " + f.getName());
                    f.set(oldEntity, o);
                } catch (Exception e) {
                    throw new SharingRegistryException(e.getMessage());
                }
            }
        }
        return oldEntity;
    }

    private static Hashtable<String, Object> fieldsToHT(Field[] fields, Object obj){
        Hashtable<String,Object> hashtable = new Hashtable<>();
        for (Field field: fields){
            field.setAccessible(true);
            try {
                Object retrievedObject = field.get(obj);
                if (retrievedObject != null){
                    logger.debug("scanning " + field.getName());
                    hashtable.put(field.getName(), field.get(obj));
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return hashtable;
    }
}