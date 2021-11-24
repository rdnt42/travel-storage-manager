create table info_tasks
(
    info_tasks_id bigserial primary key,
    cursor_id     int,
    task_type_id  int not null references task_types
);

