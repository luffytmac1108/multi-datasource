-- mysql sql
CREATE TABLE `user_data` (
    `id` int NOT NULL AUTO_INCREMENT,
    `username` varchar(50) NOT NULL,
    `email` varchar(100) NOT NULL,
    `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`),
    UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB;

INSERT INTO multi_datasource.user_data (id, username, email, created_at) VALUES(1, 'huangxiaoxiao', '989098987@qq.com', '2025-08-04 17:55:49');
INSERT INTO multi_datasource.user_data (id, username, email, created_at) VALUES(2, 'mysql', 'mysql@lalalal.com', '2025-08-04 18:12:56');


-- postgre sql
CREATE TABLE public.user_data (
    id int4 DEFAULT nextval('user_id_seq'::regclass) NOT NULL,
    username varchar(50) NOT NULL,
    email varchar(100) NOT NULL,
    created_at timestamp DEFAULT now() NULL,
    CONSTRAINT user_email_key UNIQUE (email),
    CONSTRAINT user_pkey PRIMARY KEY (id)
);

INSERT INTO public.user_data (id, username, email, created_at) VALUES(1, 'yangxiaowei', 'xiaowyang@deloitte.com.cn', '2025-08-04 17:55:28.235');
INSERT INTO public.user_data (id, username, email, created_at) VALUES(2, 'postgre', 'postgre@lalalalal.com', '2025-08-04 18:12:41.777');