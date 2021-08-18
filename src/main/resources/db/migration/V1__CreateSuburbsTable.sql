CREATE SEQUENCE suburb_seq_gen START WITH 10 INCREMENT BY 1;
CREATE TABLE `suburb`
(
    `id`        int NOT NULL AUTO_INCREMENT,
    `name`      varchar(50),
    `post_code` varchar(5) NOT NULL,
    `state_code` varchar(5),
    PRIMARY KEY (`id`)
);

insert into `suburb`(`name`,`post_code`, `state_code`) values ( 'Cordon', '3136' , 'VIC');
insert into `suburb`(`name`,`post_code`, `state_code`) values ( 'Baandee', '6412' , 'WA');
insert into `suburb`(`name`,`post_code`, `state_code`) values ( 'Ballarat West', '3350' , 'VIC');
insert into `suburb`(`name`,`post_code`, `state_code`) values ( 'LAKE CAMM', '6355' , 'WA');
insert into `suburb`(`name`,`post_code`, `state_code`) values ( 'TALLEGALLA', '4340' , 'QLD');
insert into `suburb`(`name`,`post_code`, `state_code`) values ( 'IRON KNOB', '5611' , 'SA');
insert into `suburb`(`name`,`post_code`, `state_code`) values ( 'Officer', '3809' , 'VIC');
insert into `suburb`(`name`,`post_code`, `state_code`) values ( 'Officer South', '3809' , 'VIC');
insert into `suburb`(`name`,`post_code`, `state_code`) values ( 'GEORGES HALL', '2198' , 'NSW');