# Test.Hibernate

#### 1: PostgresSQL in docker container

Имя сервера: test.db.postgres

Адресс: 127.0.0.1:5432

БД: test_hibernate

User:password: postgres:password

Локальный путь: c:/Projects/Test.db.PostgresSQL/db/data

    docker run --name test.db.postgres --rm -p 127.0.0.1:5432:5432 -v c:/Projects/Test.db.PostgresSQL/db/data:/var/lib/postgresql/data -e TZ=Asia/Novosibirsk -e POSTGRES_PASSWORD=password -d postgres
    docker ps -a
    docker stop test.db.postgres
    docker exec -it test.db.postgres psql -U postgres
		create database test_hibernate;


#### 2: MySQL in docker container
    
    docker run --name test.db.mysql --rm -p 127.0.0.1:3306:3306 -v c:/Projects/Test.db.MySQL/db/data:/var/lib/mysql -e MYSQL_ROOT_PASSWORD=mysql -d mysql
    docker ps -a
    docker stop test.db.mysql
    docker exec -it test.db.mysql mysql -uroot -pmysql
    mysqlsh -uroot -pmysql -h 127.0.0.1 -P3306
        \sql 
			show databases;
			create database test_hibernate;
		


