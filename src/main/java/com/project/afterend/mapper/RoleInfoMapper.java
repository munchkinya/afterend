package com.project.afterend.mapper;

import com.project.afterend.beans.MenuInfo;
import com.project.afterend.beans.RoleInfo;
import com.project.afterend.beans.RoleMenu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface RoleInfoMapper {
    public List<RoleInfo> getAllRoles();//得到所有角色列表
    public RoleInfo getRoleById(int id);//根据id查role
    public List<RoleMenu> getRoleMenuId(int roleid);//通过角色id找到对应的菜单
    public int deleteByids (Map<Object, Object> map);//通过roidid和menuid删除权限菜单
    public int delRoleMenu(int roleId);//删除角色对应的菜单
    public int addRoleMenu(RoleMenu roleMenu);//增加角色和对应的菜单    [int roleId ,int menuId]
    public int insert(RoleInfo roleInfo);//增加角色
    public RoleInfo getRoleByName(String rolename);//重复校验
    public int upfateRole(RoleInfo roleInfo);//修改角色
    public int delRolebyid(int id);//删除角色
    public List<RoleInfo> getAllRolesInST();//查教师表角色
    public List<RoleInfo> getAllRolesInTT();//得到实训教师角色
}