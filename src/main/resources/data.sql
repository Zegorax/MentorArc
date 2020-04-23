INSERT INTO `auth_role` (`auth_role_id`, `role_desc`, `role_name`)
Select 1, 'This user have all rights', 'ADMIN'
WHERE NOT EXISTS (SELECT * FROM `auth_role` WHERE `auth_role_id`=1);

INSERT INTO `auth_role` (`auth_role_id`, `role_desc`, `role_name`)
Select 2, 'This user can ask for assisstance', 'POULAIN'
WHERE NOT EXISTS (SELECT * FROM `auth_role` WHERE `auth_role_id`=2);

INSERT INTO `auth_role` (`auth_role_id`, `role_desc`, `role_name`)
Select 3, 'This user can offer help', 'MENTOR'
WHERE NOT EXISTS (SELECT * FROM `auth_role` WHERE `auth_role_id`=3);

INSERT INTO `auth_user`(`auth_user_id`, `email`, `password`, `username`) 
Select 1, 'admin@admin.admin', '$2a$10$mHJ3ElBZ.BZkneNA25fEVOJdqEjwnXS9MduHsO5Dpr2R0.4W0/o0G', 'admin' 
WHERE NOT EXISTS (SELECT * FROM `auth_user` WHERE `auth_user_id`=1);

INSERT INTO `auth_user_role` (`auth_user_id`, `auth_role_id`) SELECT 1,1 WHERE NOT EXISTS (SELECT * FROM `auth_user_role` WHERE `auth_user_id`=1);