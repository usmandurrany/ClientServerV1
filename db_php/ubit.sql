/*
Created with MyDB Studio
http://www.mydb-studio.com

SERVER localhost
DATABASE ubit
*/


/* 
administrator
*/
CREATE TABLE `administrator` (
  `adminid` varchar(45) NOT NULL DEFAULT '0',
  `name` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  `acc_lvl` int(1) DEFAULT NULL,
  PRIMARY KEY (`adminid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `administrator` VALUES ('admin','System Admin','rmsadmin','2');
INSERT INTO `administrator` VALUES ('usmandgreat','Usman Durrani','iamamuslim','2');

/* 
announcements
*/
CREATE TABLE `announcements` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `message` varchar(45) DEFAULT NULL,
  `author` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

INSERT INTO `announcements` VALUES ('1','Welcome to result management system','Administrator');

/* 
course
*/
CREATE TABLE `course` (
  `c_no` varchar(12) NOT NULL,
  `c_name` varchar(255) DEFAULT NULL,
  `c_hr` varchar(5) DEFAULT NULL,
  `c_semester` int(11) DEFAULT NULL,
  PRIMARY KEY (`c_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `course` VALUES ('BSCS-301','Introduction to Computer Science - I','2+1','1');
INSERT INTO `course` VALUES ('BSCS-302','Introduction to Computer Science - II','2+1','2');
INSERT INTO `course` VALUES ('BSCS-303','Mathematics - I (Calculus)','3','1');
INSERT INTO `course` VALUES ('BSCS-304','Mathematics - II (Differential Equations)','3','2');
INSERT INTO `course` VALUES ('BSCS-305','Statistics and Data Analysis','2+1','1');
INSERT INTO `course` VALUES ('BSCS-306','Probability and Statistical Methods','2+1','2');
INSERT INTO `course` VALUES ('BSCS-307','Physics - I (General Physics)','2+1','1');
INSERT INTO `course` VALUES ('BSCS-308','Physics - II (Electricity and Magnetism)','2+1','2');
INSERT INTO `course` VALUES ('BSCS-309','English','3','1');
INSERT INTO `course` VALUES ('BSCS-310','English','3','2');
INSERT INTO `course` VALUES ('BSCS-311','Islamic Learning & Pakistan Studies or Ethics & Pakistan Studies','3','1');
INSERT INTO `course` VALUES ('BSCS-312','Urdu','3','2');
INSERT INTO `course` VALUES ('BSCS-401','Digital Computer Design Fundamentals','2+1','3');
INSERT INTO `course` VALUES ('BSCS-402','Data Structures','2+1','4');
INSERT INTO `course` VALUES ('BSCS-403','Assembly Language Programming','2+1','3');
INSERT INTO `course` VALUES ('BSCS-404','System Design with Microprocessors','2+1','4');
INSERT INTO `course` VALUES ('BSCS-405','Mathematics - III (Linear Algebra and Analytical Geometry)','3','3');
INSERT INTO `course` VALUES ('BSCS-406','Mathematics - IV (Numerical Computing)','2+1','4');
INSERT INTO `course` VALUES ('BSCS-409','Materials, Semiconductors and Devices','2+1','3');
INSERT INTO `course` VALUES ('BSCS-410','Electronics','2+1','4');
INSERT INTO `course` VALUES ('BSCS-411','Discrete Mathematics','3','3');
INSERT INTO `course` VALUES ('BSCS-412','Software Engineering & Project Management','2+1','4');
INSERT INTO `course` VALUES ('BSCS-413','Object Oriented Programming','2+1','3');
INSERT INTO `course` VALUES ('BSCS-414','Communication Skills and Report Writing','3','4');
INSERT INTO `course` VALUES ('BSCS-501','Theory of Computer Science','3','5');
INSERT INTO `course` VALUES ('BSCS-502','Concepts of Operating Systems','2+1','6');
INSERT INTO `course` VALUES ('BSCS-503','Data Communication and Networking - I','2+1','5');
INSERT INTO `course` VALUES ('BSCS-504','Compiler Construction - I','2+1','6');
INSERT INTO `course` VALUES ('BSCS-505','Stochastic Processes and Inference (Optional)','2+1','5');
INSERT INTO `course` VALUES ('BSCS-506','Modeling and Simulation (Optional)','2+1','6');
INSERT INTO `course` VALUES ('BSCS-507','Operations Research - I (Optional)','2+1','5');
INSERT INTO `course` VALUES ('BSCS-508','Operations Research - II (Optional)','2+1','6');
INSERT INTO `course` VALUES ('BSCS-509','Database Systems','2+1','5');
INSERT INTO `course` VALUES ('BSCS-510','Microcomputer Design and Interfacing - I (Optional)','2+1','6');
INSERT INTO `course` VALUES ('BSCS-511','Computer Organization and Architecture','2+1','5');
INSERT INTO `course` VALUES ('BSCS-512','Data Communication and Networking - II','2+1','6');
INSERT INTO `course` VALUES ('BSCS-513','Advanced Numerical Analysis (Optional)','2+1','5');
INSERT INTO `course` VALUES ('BSCS-514','Computer Graphics','2+1','6');
INSERT INTO `course` VALUES ('BSCS-515','Artificial Intelligence','2+1','5');
INSERT INTO `course` VALUES ('BSCS-517','System Analysis & Design (Optional)','2+1','5');
INSERT INTO `course` VALUES ('BSCS-519','Business Programming Language','2+1','5');
INSERT INTO `course` VALUES ('BSCS-520','Advanced Software Engineering','2+1','6');
INSERT INTO `course` VALUES ('BSCS-522','Expert Systems','2+1','6');
INSERT INTO `course` VALUES ('BSCS-601','Theory of Operating Systems','2+1','7');
INSERT INTO `course` VALUES ('BSCS-602','Operating System Case Study (Optional)','2+1','8');
INSERT INTO `course` VALUES ('BSCS-603','Compiler Construction - II','2+1','7');
INSERT INTO `course` VALUES ('BSCS-604','Natural Language Processing','2+1','8');
INSERT INTO `course` VALUES ('BSCS-605','Advanced Computer Graphics (Optional)','2+1','7');
INSERT INTO `course` VALUES ('BSCS-606','Distributed Database Systems','2+1','8');
INSERT INTO `course` VALUES ('BSCS-607','Financial Accounting','3','7');
INSERT INTO `course` VALUES ('BSCS-609','Microcomputer Design & Interfacing - II (Optional)','2+1','7');
INSERT INTO `course` VALUES ('BSCS-610','Design and Analysis of Algorithms','2+1','8');
INSERT INTO `course` VALUES ('BSCS-611','Parallel Computing (Optional)','2+1','7');
INSERT INTO `course` VALUES ('BSCS-612','Financial Management','3','8');
INSERT INTO `course` VALUES ('BSCS-613','Management Information System','2+1','7');
INSERT INTO `course` VALUES ('BSCS-616','Multimedia Systems (Optional)','2+1','8');
INSERT INTO `course` VALUES ('BSCS-618','Computational Linear Algebra (Optional)','2+1','8');
INSERT INTO `course` VALUES ('BSCS-619','Thesis','3','7');
INSERT INTO `course` VALUES ('BSCS-620','Thesis','3','8');
INSERT INTO `course` VALUES ('BSCS-621','Topics of Current/Special Interest. Introduction to Machine and recent trends in Software Development. (Optional)','3','7');
INSERT INTO `course` VALUES ('BSCS-624','Project','3','8');
INSERT INTO `course` VALUES ('BSCS-625','VLSI Design Techniques (Optional)','2+1','7');
INSERT INTO `course` VALUES ('BSCS-633','Internet Application Development (Optional)','2+1','7');

/* 
faculty
*/
CREATE TABLE `faculty` (
  `tid` varchar(45) NOT NULL,
  `t_name` varchar(50) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `qualification` varchar(45) DEFAULT NULL,
  `f_of` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT 'ubit',
  `acc_lvl` int(1) DEFAULT '1',
  PRIMARY KEY (`tid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `faculty` VALUES ('1','BADI-UR-REHMAN','badirehman@gmail.com','Ph.D','Both','badirehman','1');

/* 
result
*/
CREATE TABLE `result` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `seatno` varchar(45) DEFAULT NULL,
  `c_no` varchar(40) DEFAULT NULL,
  `max_th` varchar(45) DEFAULT NULL,
  `obt_th` varchar(45) DEFAULT NULL,
  `max_lab` int(11) DEFAULT NULL,
  `obt_lab` int(11) DEFAULT NULL,
  `total` varchar(45) DEFAULT NULL,
  `t_id` varchar(40) DEFAULT NULL,
  `section` varchar(45) DEFAULT NULL,
  `year` int(11) DEFAULT NULL,
  `semester` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=181 DEFAULT CHARSET=utf8;

INSERT INTO `result` VALUES ('83','EP1249001','BSCS-308','100','48','20','14','14','1','A','2013','3');
INSERT INTO `result` VALUES ('84','EP1249003','BSCS-308','100','62','20','16','16','1','A','2013','3');
INSERT INTO `result` VALUES ('85','EP1249004','BSCS-308','100','9','20','12','12','1','A','2013','3');
INSERT INTO `result` VALUES ('86','EP1249005','BSCS-308','100','70','20','15','15','1','A','2013','3');
INSERT INTO `result` VALUES ('87','EP1249006','BSCS-308','100','70','20','18','18','1','A','2013','3');
INSERT INTO `result` VALUES ('88','EP1249007','BSCS-308','100','54','20','18','18','1','A','2013','3');
INSERT INTO `result` VALUES ('89','EP1249008','BSCS-308','100','70','20','20','20','1','A','2013','3');
INSERT INTO `result` VALUES ('90','EP1249009','BSCS-308','100','67','20','18','18','1','A','2013','3');
INSERT INTO `result` VALUES ('91','EP1249010','BSCS-308','100','64','20','16','16','1','A','2013','3');
INSERT INTO `result` VALUES ('92','EP1249011','BSCS-308','100','68','20','12','12','1','A','2013','3');
INSERT INTO `result` VALUES ('93','EP1249012','BSCS-308','100','68','20','12','12','1','A','2013','3');
INSERT INTO `result` VALUES ('94','EP1249013','BSCS-308','100','62','20','13','13','1','A','2013','3');
INSERT INTO `result` VALUES ('95','EP1249014','BSCS-308','100','67','20','13','13','1','A','2013','3');
INSERT INTO `result` VALUES ('96','EP1249015','BSCS-308','100','68','20','17','17','1','A','2013','3');
INSERT INTO `result` VALUES ('97','EP1249016','BSCS-308','100','47','20','14','14','1','A','2013','3');
INSERT INTO `result` VALUES ('98','EP1249017','BSCS-308','100','60','20','17','17','1','A','2013','3');
INSERT INTO `result` VALUES ('99','EP1249018','BSCS-308','100','68','20','14','14','1','A','2013','3');
INSERT INTO `result` VALUES ('100','EP1249019','BSCS-308','100','66','20','14','14','1','A','2013','3');
INSERT INTO `result` VALUES ('101','EP1249020','BSCS-308','100','45','20','14','14','1','A','2013','3');
INSERT INTO `result` VALUES ('102','EP1249021','BSCS-308','100','47','20','18','18','1','A','2013','3');
INSERT INTO `result` VALUES ('103','EP1249022','BSCS-308','100','26','20','12','12','1','A','2013','3');
INSERT INTO `result` VALUES ('104','EP1249023','BSCS-308','100','67','20','13','13','1','A','2013','3');
INSERT INTO `result` VALUES ('105','EP1249024','BSCS-308','100','62','20','13','13','1','A','2013','3');
INSERT INTO `result` VALUES ('106','EP1249025','BSCS-308','100','65','20','13','13','1','A','2013','3');
INSERT INTO `result` VALUES ('107','EP1249026','BSCS-308','100','8','20','10','10','1','A','2013','3');
INSERT INTO `result` VALUES ('108','EP1249027','BSCS-308','100','61','20','14','14','1','A','2013','3');
INSERT INTO `result` VALUES ('109','EP1249028','BSCS-308','100','45','20','14','14','1','A','2013','3');
INSERT INTO `result` VALUES ('110','EP1249029','BSCS-308','100','53','20','13','13','1','A','2013','3');
INSERT INTO `result` VALUES ('111','EP1249030','BSCS-308','100','69','20','16','16','1','A','2013','3');
INSERT INTO `result` VALUES ('112','EP1249031','BSCS-308','100','54','20','15','15','1','A','2013','3');
INSERT INTO `result` VALUES ('113','EP1249033','BSCS-308','100','52','20','14','14','1','A','2013','3');
INSERT INTO `result` VALUES ('114','EP1249034','BSCS-308','100','28','20','14','14','1','A','2013','3');
INSERT INTO `result` VALUES ('115','EP1249036','BSCS-308','100','62','20','13','13','1','A','2013','3');
INSERT INTO `result` VALUES ('116','EP1249037','BSCS-308','100','69','20','18','18','1','A','2013','3');
INSERT INTO `result` VALUES ('117','EP1249038','BSCS-308','100','59','20','16','16','1','A','2013','3');
INSERT INTO `result` VALUES ('118','EP1249039','BSCS-308','100','54','20','15','15','1','A','2013','3');
INSERT INTO `result` VALUES ('119','EP1249040','BSCS-308','100','62','20','15','15','1','A','2013','3');
INSERT INTO `result` VALUES ('120','EP1249041','BSCS-308','100','46','20','15','15','1','A','2013','3');
INSERT INTO `result` VALUES ('121','EP1249042','BSCS-308','100','46','20','15','15','1','A','2013','3');
INSERT INTO `result` VALUES ('122','EP1249043','BSCS-308','100','63','20','13','13','1','A','2013','3');
INSERT INTO `result` VALUES ('123','EP1249044','BSCS-308','100','69','20','17','17','1','A','2013','3');
INSERT INTO `result` VALUES ('124','EP1249045','BSCS-308','100','58','20','18','18','1','A','2013','3');
INSERT INTO `result` VALUES ('125','EP1249046','BSCS-308','100','64','20','17','17','1','A','2013','3');
INSERT INTO `result` VALUES ('126','EP1249047','BSCS-308','100','65','20','13','13','1','A','2013','3');
INSERT INTO `result` VALUES ('127','EP1249048','BSCS-308','100','16','20','15','15','1','A','2013','3');
INSERT INTO `result` VALUES ('128','EP1249049','BSCS-308','100','67','20','20','20','1','A','2013','3');
INSERT INTO `result` VALUES ('129','EP1249050','BSCS-308','100','68','20','12','12','1','A','2013','3');
INSERT INTO `result` VALUES ('130','EP1249051','BSCS-308','100','69','20','13','13','1','A','2013','3');
INSERT INTO `result` VALUES ('131','EP1249052','BSCS-308','100','56','20','15','15','1','A','2013','3');
INSERT INTO `result` VALUES ('132','EP1249053','BSCS-308','100','57','20','18','18','1','A','2013','3');
INSERT INTO `result` VALUES ('133','EP1249054','BSCS-308','100','57','20','18','18','1','A','2013','3');
INSERT INTO `result` VALUES ('134','EP1249055','BSCS-308','100','65','20','17','17','1','A','2013','3');
INSERT INTO `result` VALUES ('135','EP1249056','BSCS-308','100','69','20','16','16','1','A','2013','3');
INSERT INTO `result` VALUES ('136','EP1249057','BSCS-308','100','67','20','18','18','1','A','2013','3');
INSERT INTO `result` VALUES ('137','EP1249059','BSCS-308','100','65','20','15','15','1','A','2013','3');
INSERT INTO `result` VALUES ('138','EP1249060','BSCS-308','100','69','20','16','16','1','A','2013','3');
INSERT INTO `result` VALUES ('139','EP1249061','BSCS-308','100','57','20','12','12','1','A','2013','3');
INSERT INTO `result` VALUES ('140','EP1249062','BSCS-308','100','67','20','18','18','1','A','2013','3');
INSERT INTO `result` VALUES ('141','EP1249064','BSCS-308','100','69','20','18','18','1','A','2013','3');
INSERT INTO `result` VALUES ('142','EP1249065','BSCS-308','100','60','20','13','13','1','A','2013','3');
INSERT INTO `result` VALUES ('143','EP1249066','BSCS-308','100','55','20','12','12','1','A','2013','3');
INSERT INTO `result` VALUES ('144','EP1249067','BSCS-308','100','64','20','13','13','1','A','2013','3');
INSERT INTO `result` VALUES ('145','EP1249069','BSCS-308','100','64','20','13','13','1','A','2013','3');
INSERT INTO `result` VALUES ('146','EP1249070','BSCS-308','100','7','20','10','10','1','A','2013','3');
INSERT INTO `result` VALUES ('147','EP1249071','BSCS-308','100','65','20','15','15','1','A','2013','3');
INSERT INTO `result` VALUES ('148','EP1249072','BSCS-308','100','65','20','18','18','1','A','2013','3');
INSERT INTO `result` VALUES ('149','EP1249073','BSCS-308','100','65','20','12','12','1','A','2013','3');
INSERT INTO `result` VALUES ('150','EP1249074','BSCS-308','100','59','20','13','13','1','A','2013','3');
INSERT INTO `result` VALUES ('151','EP1249076','BSCS-308','100','45','20','17','17','1','A','2013','3');
INSERT INTO `result` VALUES ('152','EP1249077','BSCS-308','100','57','20','12','12','1','A','2013','3');
INSERT INTO `result` VALUES ('153','EP1249001','BSCS-302','80','56','20','14','70','1','A','2013','3');
INSERT INTO `result` VALUES ('154','EP1249002','BSCS-302','80','58','20','15','73','1','A','2013','3');
INSERT INTO `result` VALUES ('155','EP1249003','BSCS-302','80','66','20','17','83','1','A','2013','3');
INSERT INTO `result` VALUES ('156','EP1249004','BSCS-302','80','67','20','0','67','1','A','2013','3');
INSERT INTO `result` VALUES ('157','EP1249001','BSCS-402','80','12','20','10','22','1','A','2013','3');
INSERT INTO `result` VALUES ('158','EP1249002','BSCS-402','80','23','20','23','46','1','A','2013','3');
INSERT INTO `result` VALUES ('159','EP1249003','BSCS-402','80','45','20','12','57','1','A','2013','3');
INSERT INTO `result` VALUES ('160','EP1249004','BSCS-402','80','75','20','20','95','1','A','2013','3');
INSERT INTO `result` VALUES ('161','EP1249005','BSCS-402','80','43','20','17','60','1','A','2013','3');
INSERT INTO `result` VALUES ('162','EP1249001','BSCS-413','80','50','20','16','66','1','A','2013','3');
INSERT INTO `result` VALUES ('163','EP1249002','BSCS-413','80','0','20','0','0','1','A','2013','3');
INSERT INTO `result` VALUES ('164','EP1249003','BSCS-413','80','0','20','0','0','1','A','2013','3');
INSERT INTO `result` VALUES ('165','EP1249004','BSCS-413','80','0','20','0','0','1','A','2013','3');
INSERT INTO `result` VALUES ('166','EP1249005','BSCS-413','80','0','20','0','0','1','A','2013','3');
INSERT INTO `result` VALUES ('167','EP1249006','BSCS-413','80','0','20','0','0','1','A','2013','3');
INSERT INTO `result` VALUES ('168','EP1249007','BSCS-413','80','0','20','0','0','1','A','2013','3');
INSERT INTO `result` VALUES ('169','EP1249008','BSCS-413','80','0','20','0','0','1','A','2013','3');
INSERT INTO `result` VALUES ('170','EP1249009','BSCS-413','80','0','20','0','0','1','A','2013','3');
INSERT INTO `result` VALUES ('171','EP1249070','BSCS-413','80','70','20','15','85','1','A','2013','3');
INSERT INTO `result` VALUES ('172','EP1249071','BSCS-413','80','76','20','16','92','1','A','2013','3');
INSERT INTO `result` VALUES ('173','EP1249072','BSCS-413','80','46','20','17','63','1','A','2013','3');
INSERT INTO `result` VALUES ('174','EP1249073','BSCS-413','80','53','20','18','71','1','A','2013','3');
INSERT INTO `result` VALUES ('175','EP1249074','BSCS-413','80','52','20','15','67','1','A','2013','3');
INSERT INTO `result` VALUES ('176','EP1249075','BSCS-413','80','66','20','15','81','1','A','2013','3');
INSERT INTO `result` VALUES ('177','EP1249076','BSCS-413','80','75','20','14','89','1','A','2013','3');
INSERT INTO `result` VALUES ('178','EP1249077','BSCS-413','80','42','20','13','55','1','A','2013','3');
INSERT INTO `result` VALUES ('179','EP1249071','BSCS-308','100','78','0','0','78','1','B','2013','4');
INSERT INTO `result` VALUES ('180','EP1249072','BSCS-308','10034','69','0','0','69','1','B','2013','4');

/* 
student
*/
CREATE TABLE `student` (
  `seatno` varchar(45) NOT NULL,
  `s_name` varchar(45) DEFAULT NULL,
  `f_name` varchar(45) DEFAULT NULL,
  `dob` varchar(50) DEFAULT NULL,
  `field` varchar(45) DEFAULT NULL,
  `sec` varchar(45) DEFAULT NULL,
  `semester` int(11) DEFAULT NULL,
  `yoe` int(11) DEFAULT NULL,
  `password` varchar(45) DEFAULT 'ubit',
  `acc_lvl` varchar(45) DEFAULT '0',
  PRIMARY KEY (`seatno`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `student` VALUES ('EP1249001','Abbas UR Rehman','','','bscs','a','2','2012','demo','0');
INSERT INTO `student` VALUES ('EP1249002','Abdul Moeed','','','bscs','a','2','2012','demo','0');
INSERT INTO `student` VALUES ('EP1249003','Abdullah Waqas','','','bscs','a','2','2012','demo','0');
INSERT INTO `student` VALUES ('EP1249004','Aftab Sardaruddin Momin','','','bscs','a','2','2012','demo','0');
INSERT INTO `student` VALUES ('EP1249005','Amad Bin Mumtaz','','','bscs','a','2','2012','demo','0');
INSERT INTO `student` VALUES ('EP1249006','Amna Khan','','','bscs','a','2','2012','demo','0');
INSERT INTO `student` VALUES ('EP1249007','Aneeq UR Rehman Khan','','','bscs','a','2','2012','demo','0');
INSERT INTO `student` VALUES ('EP1249008','Anum Javed','','','bscs','a','2','2012','demo','0');
INSERT INTO `student` VALUES ('EP1249009','Areeba Jabeen','','','bscs','a','2','2012','demo','0');
INSERT INTO `student` VALUES ('EP1249010','Arsalan Hussain','','','bscs','a','2','2012','demo','0');
INSERT INTO `student` VALUES ('EP1249011','Bilal Ahmed','','','bscs','a','2','2012','demo','0');
INSERT INTO `student` VALUES ('EP1249012','Bushra Mansoor','','','bscs','a','2','2012','\'demo','0');
INSERT INTO `student` VALUES ('EP1249013','Dawood Zubair','','','bscs','a','2','2012','demo','0');
INSERT INTO `student` VALUES ('EP1249014','Ehtesham Ahmed Ansari','','','bscs','a','2','2012','demo','0');
INSERT INTO `student` VALUES ('EP1249015','Faiza Khan','','','bscs','a','2','2012','demo','0');
INSERT INTO `student` VALUES ('EP1249016','Gulfham Haider','','','bscs','a','2','2012','demo','0');
INSERT INTO `student` VALUES ('EP1249017','Hafiz Abdul Wahab','','','bscs','a','2','2012','demo','0');
INSERT INTO `student` VALUES ('EP1249018','Hafiz Muhammad Adeel Akbar','','','bscs','a','2','2012','demo','0');
INSERT INTO `student` VALUES ('EP1249019','Hafiz Muhammad Owais Alam','','','bscs','a','2','2012','demo','0');
INSERT INTO `student` VALUES ('EP1249020','Hafiz Uzair Hashmi','','','bscs','a','2','2012','demo','0');
INSERT INTO `student` VALUES ('EP1249021','Hammad Minhajullah Hafiz','','','bscs','a','2','2012','demo','0');
INSERT INTO `student` VALUES ('EP1249022','Haseeb Uddin Shaikh','','','bscs','a','2','2012','demo','0');
INSERT INTO `student` VALUES ('EP1249023','Hazik Anees','','','bscs','a','2','2012','demo','0');
INSERT INTO `student` VALUES ('EP1249024','Hood Khizer','','','bscs','a','2','2012','demo','0');
INSERT INTO `student` VALUES ('EP1249025','Hudaib Riaz','','','bscs','a','2','2012','demo','0');
INSERT INTO `student` VALUES ('EP1249026','Hunain Ahmed Khan','','','bscs','a','2','2012','demo','0');
INSERT INTO `student` VALUES ('EP1249027','Jhanzaib Mazhar','','','bscs','a','2','2012','demo','0');
INSERT INTO `student` VALUES ('EP1249028','Jazaib Ali Shah','','','bscs','a','2','2012','demo','0');
INSERT INTO `student` VALUES ('EP1249029','Jhangir Alam','','','bscs','a','2','2012','demo','0');
INSERT INTO `student` VALUES ('EP1249030','Madiha Fatima','','','bscs','a','2','2012','demo','0');
INSERT INTO `student` VALUES ('EP1249031','Manzoor Ahmed','','','bscs','a','2','2012','demo','0');
INSERT INTO `student` VALUES ('EP1249033','Mirza Zaki UL Hasan','','','bscs','a','2','2012','demo','0');
INSERT INTO `student` VALUES ('EP1249034','Moeed Akber','','','bscs','a','2','2012','demo','0');
INSERT INTO `student` VALUES ('EP1249035','Mohit Raj Jesawni','','','bscs','a','2','2012','demo','0');
INSERT INTO `student` VALUES ('EP1249037','Muhammad AbuBakr Hussain','','','bscs','a','2','2012','demo','0');
INSERT INTO `student` VALUES ('EP1249038','M.Akbar Muhammar Siddiq','','','bscs','a','2','2012','demo','0');
INSERT INTO `student` VALUES ('EP1249039','Muhammad Asad','','','bscs','a','2','2012','demo','0');
INSERT INTO `student` VALUES ('EP1249040','Muhammad Aslam','','','bscs','a','2','2012','demo','0');
INSERT INTO `student` VALUES ('EP1249041','Muhammad Asjad Amin','','','bscs','a','2','2012','demo','0');
INSERT INTO `student` VALUES ('EP1249042','Muhammad Awais','','','bscs','a','2','2012','demo','0');
INSERT INTO `student` VALUES ('EP1249044','Muhammad Bilal','','','bscs','a','2','2012','demo','0');
INSERT INTO `student` VALUES ('EP1249045','Muhammad Ebad','','','bscs','a','2','2012','demo','0');
INSERT INTO `student` VALUES ('EP1249046','Muhammad Ebad','','','bscs','a','2','2012','demo','0');
INSERT INTO `student` VALUES ('EP1249047','Muhammad Ehsan','','','bscs','a','2','2012','demo','0');
INSERT INTO `student` VALUES ('EP1249049','M.Fahad Ahmed Qureshi','','','bscs','a','2','2012','demo','0');
INSERT INTO `student` VALUES ('EP1249050','Muhammad Faisal Ghufran','','','bscs','a','2','2012','demo','0');
INSERT INTO `student` VALUES ('EP1249051','Muhammad Faizan Khan','','','bscs','a','2','2012','demo','0');
INSERT INTO `student` VALUES ('EP1249052','Muhammad Faran','','','bscs','a','2','2012','demo','0');
INSERT INTO `student` VALUES ('EP1249053','Muhammad Faraz','','','bscs','a','2','2012','demo','0');
INSERT INTO `student` VALUES ('EP1249054','Muhammad Hammad Khan','','','bscs','a','2','2012','demo','0');
INSERT INTO `student` VALUES ('EP1249055','Muhammad Haris','','','bscs','a','2','2012','demo','0');
INSERT INTO `student` VALUES ('EP1249056','Muhammad Hasan Haider','','','bscs','a','2','2012','demo','0');
INSERT INTO `student` VALUES ('EP1249057','Muhammad Hassan Khan','','','bscs','a','2','2012','demo','0');
INSERT INTO `student` VALUES ('EP1249058','Muhammad Iftikahr','','','bscs','a','2','2012','demo','0');
INSERT INTO `student` VALUES ('EP1249061','Muhammad Junaid Ali Khan','','','bscs','a','2','2012','demo','0');
INSERT INTO `student` VALUES ('EP1249062','Muhammad Mustafa Gaziani','','','bscs','a','2','2012','demo','0');
INSERT INTO `student` VALUES ('EP1249063','Muhammad Muzammil Sarwar','','','bscs','a','2','2012','demo','0');
INSERT INTO `student` VALUES ('EP1249064','Muhammad Nauman','','','bscs','a','2','2012','demo','0');
INSERT INTO `student` VALUES ('EP1249065','Muhammad Qasim Fahim','','','bscs','a','2','2012','demo','0');
INSERT INTO `student` VALUES ('EP1249066','Muhammad Saad','','','bscs','a','2','2012','demo','0');
INSERT INTO `student` VALUES ('EP1249067','Muhammad Saad Munazzam','','','bscs','a','2','2012','demo','0');
INSERT INTO `student` VALUES ('EP1249070','Muhammad Sufyan Bin Ikram','','','bscs','a','2','2012','demo','0');
INSERT INTO `student` VALUES ('EP1249071','Muhammad Usman Akbar Durarni','Muhammad Akbar Durrani','1992-10-29','BSCS','A','3','2012','test','0');
INSERT INTO `student` VALUES ('EP1249072','Muhammad Usman Saeed','','','bscs','a','2','2012','demo','0');
INSERT INTO `student` VALUES ('EP1249073','Muhammad Usman Shaheen','','','bscs','a','2','2012','demo','0');
INSERT INTO `student` VALUES ('EP1249074','Muhammad Usman Tariq Alvi','','','bscs','a','2','2012','demo','0');
INSERT INTO `student` VALUES ('EP1249075','Muhammad Wajhat Shamsi','','','bscs','a','2','2012','demo','0');
INSERT INTO `student` VALUES ('EP1249076','Muhammad Waris Ahsan Ullah','','','bscs','a','2','2012','demo','0');
INSERT INTO `student` VALUES ('EP1249077','Muhammad Yousuf Sadiq','','','bscs','a','2','2012','demo','0');
INSERT INTO `student` VALUES ('MP209755','Faizan Durrani','Akbar Durrani','12-07-2000','BSSE','B','3','2012','ubit','0');

