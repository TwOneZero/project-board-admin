-- 테스트 계정
-- TODO: 테스트용이지만 비밀번호가 노출된 데이터 세팅. 개선하는 것이 좋을 지 고민해 보자.
insert into user_account (user_id, user_password, role_types, nickname, email, memo, created_at, created_by,
                          modified_at, modified_by)
values ('twonezero', '{noop}asdf1234', 'ADMIN', 'Twonezero', 'twonezero@mail.com', 'I am Twonezero.', now(), 'twonezero', now(), 'twonezero'),
       ('mark', '{noop}asdf1234', 'MANAGER', 'Mark', 'mark@mail.com', 'I am Mark.', now(), 'twonezero', now(), 'twonezero'),
       ('susan', '{noop}asdf1234', 'MANAGER,DEVELOPER', 'Susan', 'Susan@mail.com', 'I am Susan.', now(), 'twonezero', now(),
        'twonezero'),
       ('jim', '{noop}asdf1234', 'USER', 'Jim', 'jim@mail.com', 'I am Jim.', now(), 'twonezero', now(), 'twonezero')
;