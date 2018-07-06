# Spider

# Java版本 java 9.0.4
# MySQL版本 mysql-8.0.11-winx64

# 基于java的爬虫，实现抓取豆瓣网电影top200信息，下载电影海报并存入MySQL数据库

# 数据库films表各字段如下

    Film类       films字段   films字段类型

    id         --  id         int
    title      --  title	  varchar(64)
    director   --  director	  char(64)	
    actors     --  actors	  char(128)
    year       --  year	  int
    area       --  area       char(32)
    style	   --  style	  char(32)
    star       --  star	  double
    rating     --  rating	  int
    poster     --  poster	  varchar(128)
    quote	   --  quote      char(64)

数据库初始化代码如下
drop database spider;
create database spider;
use spider;

create table films(
id int primary key auto_increment,
title varchar(64),
director char(64),actors char(128),year int,area char(32),style char(32),star double,
rating int,

poster varchar(128),
quote varchar(64)
);

程序运行结果如下
![image](https://github.com/limcislimc/Spider/blob/master/running.png)

数据库查询结果
![image](https://github.com/limcislimc/Spider/blob/master/database.png)

电影海报下载结果
![image](https://github.com/limcislimc/Spider/blob/master/pics.jpg)
