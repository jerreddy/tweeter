
INSERT INTO roles (id, name, created_at) VALUES
(1, 'ROLE_ADMIN', CURRENT_TIMESTAMP()),
(2, 'ROLE_USER', CURRENT_TIMESTAMP())
;

INSERT INTO users (email, password, name, created_at) VALUES
('siva@gmail.com', '$2a$10$CIXGKN9rPfV/mmBMYas.SemoT9mfVUUwUxueFpU3DcWhuNo5fexYC', 'Siva',  CURRENT_TIMESTAMP())
;

INSERT INTO user_role (user_id, role_id) VALUES
(1, 1),
(1, 2)
;


INSERT INTO tweets(content, created_by,created_at) VALUES
('Hello Tweeter',1,CURRENT_TIMESTAMP())
;