create extension pgcrypto;

create domain id_uuid uuid;
create domain id_int int;

create table t
(
    id1    uuid       default gen_random_uuid(),
    id2    id_uuid    default gen_random_uuid(),
    id3    int        default floor(random() * 10 + 1)::int,
    id4    id_int     default floor(random() * 10 + 1)::int
);

insert into t default values;
insert into t default values;
insert into t default values;
insert into t default values;
insert into t default values;
