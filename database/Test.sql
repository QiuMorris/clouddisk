# 金晨曦 ZB6115110
# e-mail: cheneyjin@outlook.com 

use cloudiskdb;
#drop database cloudiskdb;

#测试权限样例
insert into pmsn(`name`, `desc`)values('level_1','level_1');
insert into pmsn(`name`, `desc`)values('level_2','level_3');
insert into pmsn(`name`, `desc`)values('level_3','level_2');

#测试角色样例
insert into role(`id`, `role_name`)values(1,'admin');
insert into role(`id`, `role_name`)values(2,'member');

#角色拥有权限样例
insert into role_pmsns(`role_id`, `pmsn_id`)values(1,1);
insert into role_pmsns(`role_id`, `pmsn_id`)values(1,2);
insert into role_pmsns(`role_id`, `pmsn_id`)values(1,3);

insert into role_pmsns(`role_id`, `pmsn_id`)values(2,2);
insert into role_pmsns(`role_id`, `pmsn_id`)values(2,3);

#创建样例用户的根目录
insert into folder(`folder_name`,`user_id`,`creatime`)values('root',1,localtime()); 
insert into folder(`folder_name`,`user_id`,`creatime`)values('root',2,localtime()); 

#创建admin Tom
insert into user(`id`, `name`, `email`, `psd`, `creatime`, `role_id`, `root_id`)
values(1,'Tom','tom@live.com',md5('123456'),'2015-09-20',1,1);

#创建member Sim
insert into user(`id`, `name`, `email`, `psd`, `creatime`, `role_id`, `root_id`)
values(2,'Sim','sim@live.com',md5('123456'),'2015-09-20',2,2);

#创建admin Tom 根目录下子文件样例
insert into file(`md5`, `path`)values('1','1');
insert into file(`md5`, `path`)values('2','2');
insert into file(`md5`, `path`)values('3','3');

# 三个样例txt文件
insert into user_file(`user_id`, `file_name`, `file_type`, `upload_time`, `share`, `folder_id`, `file_id`)values(1,'file1','txt','2016-01-01',false,1,1);
insert into user_file(`user_id`, `file_name`, `file_type`, `upload_time`, `share`, `folder_id`, `file_id`)values(1,'file2','txt','2016-01-01',false,1,2);
insert into user_file(`user_id`, `file_name`, `file_type`, `upload_time`, `share`, `folder_id`, `file_id`)values(1,'file3','txt','2016-01-01',false,1,3);

#三个样例子文件夹
insert into folder(`folder_name`, `fa_id`,`creatime`)values('folder1',1,localtime()); 
insert into folder(`folder_name`, `fa_id`,`creatime`)values('folder2',1,localtime()); 
insert into folder(`folder_name`, `fa_id`,`creatime`)values('folder3',1,localtime()); 




