create table users (username varchar(255) primary key,
                    password varchar(255) not null,
                    salt varchar(255) not null,
                    tenant_id integer not null); 
        
create table roles (role_name varchar(255) primary key);

create table user_roles (username varchar(255) not null,
                         role_name varchar(255) not null,
                         constraint user_roles_uq unique (username, role_name));
                         
CREATE TABLE roles_permissions (role_name VARCHAR(255) NOT NULL,
                                permission VARCHAR(255) NOT NULL,
                                PRIMARY KEY (role_name, permission));
                                
CREATE TABLE tenants(id INTEGER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
                     description VARCHAR(255));