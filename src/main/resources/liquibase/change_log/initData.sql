--liquibase formatted sql
--changeset JasonYe:Release0100-1
INSERT INTO `blog`.`t_contents` (`cid`, `title`, `slug`, `created`, `modified`, `content`, `author_id`, `type`, `status`, `tags`, `categories`, `hits`, `comments_num`, `allow_comment`, `allow_ping`, `allow_feed`) VALUES ('1', 'about my blog', 'about', '1487853610', '1487872488', '### Hello World\r\n\r\nabout me\r\n\r\n### ...\r\n\r\n...', '1', 'page', 'publish', NULL, NULL, '0', '0', b'1', b'1', b'1');
INSERT INTO `blog`.`t_contents` (`cid`, `title`, `slug`, `created`, `modified`, `content`, `author_id`, `type`, `status`, `tags`, `categories`, `hits`, `comments_num`, `allow_comment`, `allow_ping`, `allow_feed`) VALUES ('2', 'Hello 河岸飞流', NULL, '1487861184', '1487872798', '## Hello  World.\r\n\r\n> ...\r\n\r\n----------\r\n\r\n\r\n<!--more-->\r\n\r\n```java\r\npublic static void main(String[] args){\r\n    System.out.println(\"Hello 河岸飞流.\");\r\n}\r\n```', '1', 'post', 'publish', '', 'default', '29', '0', b'1', b'1', b'1');
INSERT INTO `blog`.`t_logs` (`id`, `action`, `data`, `author_id`, `ip`, `created`) VALUES ('1', '登录后台', NULL, '1', '0:0:0:0:0:0:0:1', '1521595669');
INSERT INTO `blog`.`t_options` (`name`, `value`, `description`) VALUES ('site_description', '河岸飞流', NULL);
INSERT INTO `blog`.`t_options` (`name`, `value`, `description`) VALUES ('site_keywords', '河岸飞流', NULL);
INSERT INTO `blog`.`t_options` (`name`, `value`, `description`) VALUES ('site_record', '', '备案号');
INSERT INTO `blog`.`t_options` (`name`, `value`, `description`) VALUES ('site_theme', 'default', NULL);
INSERT INTO `blog`.`t_options` (`name`, `value`, `description`) VALUES ('site_title', '河岸飞流', '');
INSERT INTO `blog`.`t_options` (`name`, `value`, `description`) VALUES ('social_github', '', NULL);
INSERT INTO `blog`.`t_options` (`name`, `value`, `description`) VALUES ('social_twitter', '', NULL);
INSERT INTO `blog`.`t_options` (`name`, `value`, `description`) VALUES ('social_weibo', '', NULL);
INSERT INTO `blog`.`t_options` (`name`, `value`, `description`) VALUES ('social_zhihu', '', NULL);
INSERT INTO `blog`.`t_relationships` (`cid`, `mid`) VALUES ('2', '1');
INSERT INTO `blog`.`t_users` (`uid`, `username`, `password`, `email`, `home_url`, `screen_name`, `created`, `activated`, `logged`, `group_name`) VALUES ('1', 'admin', 'a66abb5684c45962d887564f08346e8d', '346022421@qq.com', NULL, 'admin', '1490756162', '0', '0', 'visitor');


