package io.hiker.server.api.config;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import io.hiker.server.api.mapper.DbUserMapper;
import io.hiker.server.api.model.bo.DbUserBo;
import io.hiker.server.api.model.entity.DbUserEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.GroupManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Component
public class DbUserDetailsManager implements UserDetailsManager, GroupManager {

    private final DbUserMapper dbUserMapper;
    private final PasswordEncoder passwordEncoder;

    public DbUserDetailsManager(DbUserMapper dbUserMapper, PasswordEncoder passwordEncoder) {
        this.dbUserMapper = dbUserMapper;
        this.passwordEncoder = passwordEncoder;
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
        DbUserBo dbUserBo = (DbUserBo) user;
        dbUserBo.setPassword(passwordEncoder.encode(dbUserBo.getPassword()));
        dbUserMapper.insert(dbUserBo.toEntity());
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
        LambdaQueryWrapper<DbUserEntity> queryWrapper =
                new LambdaQueryWrapper<DbUserEntity>().eq(DbUserEntity::getUsername, username);
        DbUserEntity dbUserEntity = dbUserMapper.selectOne(queryWrapper);

        if (dbUserEntity == null) {
            log.debug("Query returned no results for user '" + username + "'");
            throw new UsernameNotFoundException("Username {username} not found");
        }

        return dbUserEntity.toBo();
    }
}
