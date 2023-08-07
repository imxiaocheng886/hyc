create database 学生信息查询系统;

use 学生信息查询系统;
create table 学生信息表(
学号 char(30) primary key 王二学生信息视图not null,
姓名 char(30) not null,
性别 enum('男','女') not null,
年龄 int default 21,
联系电话 char(30) not null,
邮箱 char(30) not null
);

create table 课程信息表(
课程编号 char(30) primary key not null,
课程名称 char(30) not null,
学分 int not null
);

create table 选修信息表(
学号 char(30) not null,
课程编号 char(30) not null,
成绩 int not null,
foreign key (学号) references 学生信息表(学号),
foreign key (课程编号) references 课程信息表(课程编号)
);

insert into `学生信息查询系统`.`学生信息表` (`学号`, `姓名`, `性别`, `年龄`,`联系电话`,`邮箱`)values('s01','王一','男','20','12345678911','123456@qq.com');
insert into `学生信息查询系统`.`学生信息表` (`学号`, `姓名`, `性别`, `年龄`,`联系电话`,`邮箱`)values('s02','王二','女','21','12345678912','123457@qq.com');
insert into `学生信息查询系统`.`学生信息表` (`学号`, `姓名`, `性别`, `年龄`,`联系电话`,`邮箱`)values('s03','王三','男','20','12345678913','123458@qq.com');
insert into `学生信息查询系统`.`学生信息表` (`学号`, `姓名`, `性别`, `年龄`,`联系电话`,`邮箱`)values('s04','王四','男','21','12345678914','123459@qq.com');

insert into `学生信息查询系统`.`课程信息表` (`课程编号`, `课程名称`, `学分`)values('x01','微积分','4');
insert into `学生信息查询系统`.`课程信息表` (`课程编号`, `课程名称`, `学分`)values('x02','大学英语','4');
insert into `学生信息查询系统`.`课程信息表` (`课程编号`, `课程名称`, `学分`)values('x03','经济学','3');
insert into `学生信息查询系统`.`课程信息表` (`课程编号`, `课程名称`, `学分`)values('x04','运筹学','3');

insert into `学生信息查询系统`.`选修信息表` (`学号`, `课程编号`, `成绩`)values('s01','x01','64');
insert into `学生信息查询系统`.`选修信息表` (`学号`, `课程编号`, `成绩`)values('s02','x02','74');
insert into `学生信息查询系统`.`选修信息表` (`学号`, `课程编号`, `成绩`)values('s03','x03','71');
insert into `学生信息查询系统`.`选修信息表` (`学号`, `课程编号`, `成绩`)values('s04','x04','70');

create view 王一学生信息视图 as select 学生信息表.学号,姓名,性别,年龄,联系电话,邮箱,课程名称,成绩 from 学生信息表,课程信息表,选修信息表 where 选修信息表.学号=学生信息表.学号 and 选修信息表.课程编号=课程信息表.课程编号 and 姓名='王一';
create view 王二学生信息视图 as select 学生信息表.学号,姓名,性别,年龄,联系电话,邮箱,课程名称,成绩 from 学生信息表,课程信息表,选修信息表 where 选修信息表.学号=学生信息表.学号 and 选修信息表.课程编号=课程信息表.课程编号 and 姓名='王二';
create view 王三学生信息视图 as select 学生信息表.学号,姓名,性别,年龄,联系电话,邮箱,课程名称,成绩 from 学生信息表,课程信息表,选修信息表 where 选修信息表.学号=学生信息表.学号 and 选修信息表.课程编号=课程信息表.课程编号 and 姓名='王三';
select * from 王一学生信息视图;
select * from 王二学生信息视图;
select * from 王三学生信息视图;