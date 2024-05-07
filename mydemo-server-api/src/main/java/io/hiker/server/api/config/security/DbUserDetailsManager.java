package io.hiker.server.api.config.security;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import io.hiker.server.api.mapper.DbUserMapper;
import io.hiker.server.api.model.bo.DbUserBo;
import io.hiker.server.api.model.entity.DbUserAuthorityEntity;
import io.hiker.server.api.model.entity.DbUserEntity;
import io.hiker.server.api.service.DbUserAuthorityService;
import io.hiker.server.api.service.DbUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.GroupManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
public class DbUserDetailsManager implements UserDetailsManager, GroupManager {

    private final DbUserService dbUserService;
    private final PasswordEncoder passwordEncoder;
    private final DbUserAuthorityService userAuthorityService;

    public DbUserDetailsManager(DbUserService dbUserService,
                                PasswordEncoder passwordEncoder,
                                DbUserAuthorityService userAuthorityService
    ) {
        this.dbUserService = dbUserService;
        this.passwordEncoder = passwordEncoder;
        this.userAuthorityService = userAuthorityService;
    }

    @Override
    public List<String> findAllGroups() {
        return null;
    }

    @Override
    public List<String> findUsersInGroup(String groupName) {
        return null;
    }

    @Override
    public void createGroup(String groupName, List<GrantedAuthority> authorities) {

    }

    @Override
    public void deleteGroup(String groupName) {

    }

    @Override
    public void renameGroup(String oldName, String newName) {

    }

    @Override
    public void addUserToGroup(String username, String group) {

    }

    @Override
    public void removeUserFromGroup(String username, String groupName) {

    }

    @Override
    public List<GrantedAuthority> findGroupAuthorities(String groupName) {
        return null;
    }

    @Override
    public void addGroupAuthority(String groupName, GrantedAuthority authority) {

    }

    @Override
    public void removeGroupAuthority(String groupName, GrantedAuthority authority) {

    }

    @Override
    public void createUser(UserDetails user) {
        dbUserService.save(user);
    }

    @Override
    public void updateUser(UserDetails user) {

    }

    @Override
    public void deleteUser(String username) {

    }

    @Override
    public void changePassword(String oldPassword, String newPassword) {

    }

    @Override
    public boolean userExists(String username) {
        return false;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LambdaQueryWrapper<DbUserEntity> userQuery =
                new LambdaQueryWrapper<DbUserEntity>().eq(DbUserEntity::getUsername, username);
        DbUserEntity dbUserEntity = dbUserService.getOne(userQuery);

        if (dbUserEntity == null) {
            log.debug("Query returned no results for user '" + username + "'");
            throw new UsernameNotFoundException("Username {username} not found");
        }

        LambdaQueryWrapper<DbUserAuthorityEntity> authorityQuery = new LambdaQueryWrapper<DbUserAuthorityEntity>()
                .eq(DbUserAuthorityEntity::getUserid, dbUserEntity.getId());
        List<DbUserAuthorityEntity> authorityEntities = userAuthorityService.list(authorityQuery);
        String[] authorities = new String[authorityEntities.size()];
        authorities = authorityEntities.stream()
                .map(DbUserAuthorityEntity::getAuthority).toList().toArray(authorities);
        List<GrantedAuthority> authorityList = AuthorityUtils.createAuthorityList(authorities);

        return new DbUserBo(dbUserEntity.getId(), dbUserEntity.getUsername(), dbUserEntity.getPassword(),
                authorityList);
    }
}
