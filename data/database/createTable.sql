DROP TABLE IF EXISTS `book`;
CREATE TABLE `book` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `oid` int(11) DEFAULT NULL,
  `title` varchar(128) DEFAULT NULL,
  `scores` decimal(4,1) DEFAULT NULL,
  `prices` varchar(32) DEFAULT NULL,
  `publishing` varchar(128) DEFAULT NULL,
  `url` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=110689 DEFAULT CHARSET=utf8 COMMENT='title是书名\r\nscores是评分\r\nprices是价格\r\npublishing是出版社\r\nurl是在豆瓣上的路径';



DROP TABLE IF EXISTS `film`;
CREATE TABLE `film` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `oid` varchar(32) DEFAULT NULL,
  `title` varchar(128) DEFAULT NULL,
  `year` varchar(4) DEFAULT NULL,
  `type` varchar(64) DEFAULT NULL,
  `star` decimal(4,1) DEFAULT NULL,
  `directors` varchar(32) DEFAULT NULL,
  `actors` varchar(256) DEFAULT NULL,
  `commenter` int(11) DEFAULT NULL,
  `duration` varchar(64) DEFAULT NULL,
  `url` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=51544 DEFAULT CHARSET=utf8 COMMENT='title是电影名称\r\nyear是上映时间\r\ntype是影片类型\r\nstar是评分\r\ndirectors是导演\r\nactors是演员\r\ncommenter是评分人数\r\nduration是电影时长，由于数据源问题，存在不是数字的情况\r\nurl是指在豆瓣上的路径';



DROP TABLE IF EXISTS `item_list`;
CREATE TABLE `item_list` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `item` varchar(32) DEFAULT NULL,
  `item_id` varchar(32) DEFAULT NULL,
  `item_level` int(11) DEFAULT NULL,
  `item_pre` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COMMENT='item表示标签名\r\nitem_id表示标签的id\r\nitem_level表示标签所属的层级\r\nitem_pre表示当前标签的父标签，父标签为0表示其实顶级标签节点，也就是item_level=0';
