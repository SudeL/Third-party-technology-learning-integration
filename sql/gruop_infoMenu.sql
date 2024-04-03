-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('管理用户组', '3', '1', 'gruop_info', 'ruoyi-facedom/gruop_info/index', 1, 0, 'C', '0', '0', 'ruoyi-facedom:gruop_info:list', '#', 'admin', sysdate(), '', null, '管理用户组菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('管理用户组查询', @parentId, '1',  '#', '', 1, 0, 'F', '0', '0', 'ruoyi-facedom:gruop_info:query',        '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('管理用户组新增', @parentId, '2',  '#', '', 1, 0, 'F', '0', '0', 'ruoyi-facedom:gruop_info:add',          '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('管理用户组修改', @parentId, '3',  '#', '', 1, 0, 'F', '0', '0', 'ruoyi-facedom:gruop_info:edit',         '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('管理用户组删除', @parentId, '4',  '#', '', 1, 0, 'F', '0', '0', 'ruoyi-facedom:gruop_info:remove',       '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('管理用户组导出', @parentId, '5',  '#', '', 1, 0, 'F', '0', '0', 'ruoyi-facedom:gruop_info:export',       '#', 'admin', sysdate(), '', null, '');