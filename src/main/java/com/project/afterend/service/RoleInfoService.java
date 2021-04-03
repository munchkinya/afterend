package com.project.afterend.service;

import com.project.afterend.beans.RoleInfo;
import com.project.afterend.beans.RoleMenu;
import com.project.afterend.mapper.RoleInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class RoleInfoService {
    @Autowired
    RoleInfoMapper roleInfoMapper;
    //查全表
    public List<RoleInfo> getAllRoles(){
        return roleInfoMapper.getAllRoles();
    }
    //通过角色id找到对应的菜单
    public List<RoleMenu> getRoleMenuId(int roleid){
        return roleInfoMapper.getRoleMenuId(roleid);
    }
    //通过roidid和menuid删除权限菜单
    public int deleteByids(Map<Object, Object> map){
        return roleInfoMapper.deleteByids(map);
    }
    //删除角色对应的菜单
    public int delRoleMenu(int roleId){
        return roleInfoMapper.delRoleMenu(roleId);
    }
    //增加角色和对应的菜单    [int roleId ,int menuId]
    public int addRoleMenu(RoleMenu roleMenu){
        return roleInfoMapper.addRoleMenu(roleMenu);
    }
    //根据id查role
    public RoleInfo getRoleById(int id){
        return roleInfoMapper.getRoleById(id);
    }
    //增加角色
    public int insert(RoleInfo roleInfo){
        return roleInfoMapper.insert(roleInfo);
    }
    //重复校验
    public RoleInfo getRoleByName(String rolename){
        return roleInfoMapper.getRoleByName(rolename);
    }
    //修改角色
    public int upfateRole(RoleInfo roleInfo){
        return roleInfoMapper.upfateRole(roleInfo);
    }
    //删除角色
    public int delRolebyid(int id){
        return roleInfoMapper.delRolebyid(id);
    }
    //查教师表角色
    public List<RoleInfo> getAllRolesInST(){
        return roleInfoMapper.getAllRolesInST();
    }
    //得到实训教师角色
    public List<RoleInfo> getAllRolesInTT(){
        return roleInfoMapper.getAllRolesInTT();
    }

}
