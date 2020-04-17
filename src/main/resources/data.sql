DELETE from `auth_user_role` where 1;
DELETE from `auth_role` where 1;
DELETE from `auth_user` where 1;


INSERT INTO `auth_role` (`auth_role_id`, `role_desc`, `role_name`) VALUES
(1, 'This user have all rights', 'ADMIN'),
(2, 'This user can ask for assisstance', 'POULAIN'),
(3, 'This user can offer help', 'MENTOR');
 
INSERT INTO `auth_user` (`auth_user_id`, `email`, `password`, `username`) VALUES
(1, 'admin@admin.admin', '$2a$10$mHJ3ElBZ.BZkneNA25fEVOJdqEjwnXS9MduHsO5Dpr2R0.4W0/o0G', 'admin'),
(2, 'poulain@poulain.poulain', '$2a$10$TWx7iNFOOgJpcp2.o56Sf.H0d9Qj/JQErrUSpVO.R9ilfckDEd18a', 'poulain'),
(3, 'mentor@mentor.mentor', '$2a$10$isiJ7bXkYi7.6SHW9XRcAeQGXO1bWSLYZBVFr7Oljwn3BjzIBcPEm', 'mentor');

INSERT INTO `auth_user_role` (`auth_user_id`, `auth_role_id`) VALUES
(1, 1),
(2, 2),
(3, 3);