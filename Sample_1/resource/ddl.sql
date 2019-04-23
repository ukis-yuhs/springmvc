CREATE TABLE `book` (
  `book_number` decimal(10,0) NOT NULL DEFAULT '0' COMMENT '图书号',
  `book_name` varchar(20) DEFAULT NULL COMMENT '书名',
  `author` varchar(200) DEFAULT NULL COMMENT '作者',
  `publisher` varchar(20) DEFAULT NULL COMMENT '出版社',
  `publish_date` varchar(30) DEFAULT NULL COMMENT '出版日期',
  `price` decimal(10,0) DEFAULT NULL COMMENT '单价',
  `book_type` varchar(30) DEFAULT NULL COMMENT '所属类型',
  `store_location` varchar(30) DEFAULT NULL COMMENT '存放位置',
  `store_date` varchar(30) DEFAULT NULL COMMENT '入库日期',
  `borrowed_number` int(11) DEFAULT NULL COMMENT '借出数量',
  `number` int(11) DEFAULT NULL COMMENT '图书数量',
  PRIMARY KEY (`book_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8


