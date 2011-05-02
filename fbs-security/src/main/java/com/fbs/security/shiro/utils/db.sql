create table users (username varchar(255) primary key,
                    password varchar(255) not null,
                    salt varchar(255) not null,
                    tenant_id integer not null); 
        
create table roles (role_name varchar(255) primary key);

create table user_roles (username varchar(255) not null,
                         role_name varchar(255) not null,
                         constraint user_roles_uq unique (username, role_name));
                         
create table roles_permissions (role_name varchar(255) not null,
                                permission varchar(255) not null,
                                primary key (role_name, permission));
                                
create table tenants(tenant_id integer primary key,
                     description varchar(255));