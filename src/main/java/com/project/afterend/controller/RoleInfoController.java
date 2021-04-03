package com.project.afterend.controller;

import com.project.afterend.beans.*;
import com.project.afterend.service.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/role")
public class RoleInfoController {
    @Autowired
    RoleInfoService roleInfoService;
    @Autowired
    MenuInfoService menuInfoService;
    @Autowired
    AdminInfoService adminInfoService;//就只给了admin,也就只有这个账号
    @Autowired
    StudentInfoService studentInfoService;//学号
    @Autowired
    SchoolTeacherService schoolTeacherService;//职工号
    @Autowired
    TrainingTeacherService trainingTeacherService;//电话号或者邮箱
    @Autowired
    InternshipCompanyService internshipCompanyService;//公司名称

    //得到所有角色并且包含一级和二级菜单
    @GetMapping(value = "/getAllRoles")
    public List<RoleInfo> getAll() {
        List<RoleInfo> roleInfoList=roleInfoService.getAllRoles();
        for(RoleInfo role:roleInfoList) {
            /*System.out.print("id为"+role.getId()+"的角色，它拥有的菜单为:");*/
            Map<Object, Object> map = new HashMap<Object, Object>();
            map.put("pid", 0);
            map.put("roleid", role.getId());
            List<MenuInfo> menuList = menuInfoService.getMenuList(map);
            for (MenuInfo menu : menuList) {
                if (menu.getPid() == 0) {
                    Map<Object, Object> subMap = new HashMap<Object, Object>();
                    subMap.put("pid", menu.getId());
                    subMap.put("roleid", role.getId());
                    List<MenuInfo> menuListSub = menuInfoService.getMenuList(subMap); //二级菜单
                    menu.setSubMenuList(menuListSub);
                }
            }
            /*System.out.println(menuList);*/
            role.setSubMenuList(menuList);
        }
        return roleInfoList;
    }

    //删除菜单权限(也就是对应着可扩展列下面可以删除菜单权限的功能)
    @DeleteMapping (value ="/deleterolemenu/{id}/{menuid}")
    public List<RoleInfo> delete(@PathVariable(value = "id") Integer id, @PathVariable(value = "menuid") Integer menuid){
        Map<Object, Object> map1 = new HashMap<Object, Object>();
        map1.put("roleid", id);
        map1.put("menuid", menuid);
        roleInfoService.deleteByids(map1);
        return this.getAll();
    }
    //角色授权，也就是角色的权限修改
    @PutMapping(value = "/updaterolemenu/{roleid}/{idStr}")
    public int roleMenuUpdate(@PathVariable(value = "roleid") Integer roleid,@PathVariable(value = "idStr") String idStr){
        //先删除
        roleInfoService.delRoleMenu(roleid);
        //在重新增加
        String[] menuIdList = idStr.split(",");
        for(String menuId:menuIdList){
            RoleMenu roleMenu=new RoleMenu();
            roleMenu.setMenuid(Integer.parseInt(menuId));
            roleMenu.setRoleid(roleid);
            roleInfoService.addRoleMenu(roleMenu);
        }
        return 200;
    }

    //角色权限分配，查询所有菜单，根据roleID查询对应的菜单，但是得要先要根绝
    @GetMapping(value = "/getMenuListByRoleid")
    public RoleInfo getMenuByRole(@Valid @RequestParam("username") String username,@Valid @RequestParam("password") String password) {
        RoleInfo role = new RoleInfo();
        Map<String,Object> map1=new HashMap<>();
        map1.put("username",username);
        map1.put("password",password);
        Integer roleid = null;
        if(username.length()==8 &&username.startsWith("1")){//学生
            map1.put("username",Integer.parseInt(username));
            map1.put("password",Integer.parseInt(password));
            StudentInfo studentInfo=studentInfoService.getLogin(map1);
            if(studentInfo!=null){
                roleid=studentInfo.getRoleid();
            }
        }
        else if (username.length()==8 && !username.startsWith("1")){//学校教师，长度8并且不以1开头
            SchoolTeacher schoolTeacher=schoolTeacherService.getLogin(map1);
            if(schoolTeacher!=null){
                roleid=schoolTeacher.getRoleid();
            }
        }else if (username.length()==11||username.endsWith(".com")){//实训公司
            TrainingTeacher trainingTeacher=trainingTeacherService.getLogin(map1);
            if(trainingTeacher!=null){
                roleid=trainingTeacher.getRoleid();
            }
        }else if("admin".equals(username)){//管理员
            AdminInfo adminInfo=adminInfoService.getLogin(map1);
            if(adminInfo!=null){
                roleid=adminInfo.getRoleid();
            }
        }else{//还剩两种情况：没账号和实习公司,没账号这里不用管
            InternshipCompany internshipCompany=internshipCompanyService.getLogin(map1);
            if(internshipCompany!=null){
                roleid=internshipCompany.getRoleid();
            }
            roleid=7;
        }
        role=roleInfoService.getRoleById(roleid);
        Map<Object, Object> map = new HashMap<Object, Object>();
        map.put("pid", 0);
        map.put("roleid", role.getId());
        List<MenuInfo> menuList = menuInfoService.getMenuList(map);
        for (MenuInfo menu : menuList) {
            if (menu.getPid() == 0) {
                Map<Object, Object> subMap = new HashMap<Object, Object>();
                subMap.put("pid", menu.getId());
                subMap.put("roleid", role.getId());
                List<MenuInfo> menuListSub = menuInfoService.getMenuList(subMap); //二级菜单
                menu.setSubMenuList(menuListSub);
            }
        }
        role.setSubMenuList(menuList);
        return role;
    }
    //添加功能
    @GetMapping(value = "/insertrole")
    public String insert(@Valid @RequestParam("rolename") String rolename,
                         @Valid @RequestParam("des") String des){
        if(roleInfoService.getRoleByName(rolename)!=null){
            return "again";//用户名重复校验问题
        }
        RoleInfo roleInfo=new RoleInfo();
        roleInfo.setRolename(rolename);
        roleInfo.setDes(des);
        if(roleInfoService.insert(roleInfo)!=0){
            return "success";//返回成功的标志
        }
        return "fail";
    }
    //根据roleid得到角色
    //根据id查教师
    @GetMapping(value = "/getrolebyid")
    public RoleInfo getOneSchoolTeacher(@Valid @RequestParam("id") Integer id) {
        RoleInfo roleInfo=roleInfoService.getRoleById(id);
        return roleInfo;
    }
    //修改角色信息（弹出修改页面的修改）
    @PutMapping(value = "/updaterole/{id}/{rolename}/{des}")
    public RoleInfo update(@Param("id") @PathVariable(value = "id") Integer id,
                                @PathVariable(value = "rolename") String rolename,
                                @PathVariable(value = "des") String des) {
        RoleInfo roleInfo=new RoleInfo();
        roleInfo.setId(id);
        roleInfo.setRolename(rolename);
        roleInfo.setDes(des);
        roleInfoService.upfateRole(roleInfo);
        return roleInfoService.getRoleById(id);//这里只返回被修改的一条用户数据
    }
    //删除角色，这次是真删，没写假删
    @PutMapping(value = "/deleterole/{id}")
    public String delete(@Param("id") @PathVariable(value = "id") Integer id) {
        if(roleInfoService.delRolebyid(id)!=0){
            return "success";
        }else{
            return "error";
        }
    }
    //得到教师表所有角色
    @GetMapping(value = "/getAllRolesInST")
    public List<RoleInfo> getAll12(){
        return roleInfoService.getAllRolesInST();
    }
    //得到实训教师表所有角色
    @GetMapping(value = "/getAllRolesInTT")
    public List<RoleInfo> getAll13(){
        return roleInfoService.getAllRolesInTT();
    }

}
