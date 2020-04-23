INSERT INTO `auth_role` (`auth_role_id`, `role_desc`, `role_name`)
Select 1, 'This user have all rights', 'ADMIN'
WHERE NOT EXISTS (SELECT * FROM `auth_role` WHERE `auth_role_id`=1);

INSERT INTO `auth_role` (`auth_role_id`, `role_desc`, `role_name`)
Select 2, 'This user can ask for assisstance', 'POULAIN'
WHERE NOT EXISTS (SELECT * FROM `auth_role` WHERE `auth_role_id`=2);

INSERT INTO `auth_role` (`auth_role_id`, `role_desc`, `role_name`)
Select 3, 'This user can offer help', 'MENTOR'
WHERE NOT EXISTS (SELECT * FROM `auth_role` WHERE `auth_role_id`=3);