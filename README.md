# Test.Hibernate

## 1: MySQL in docker container
    
    docker run --name test.db.mysql --rm -p 127.0.0.1:3306:3306 -v c:/Projects/Test.db.MySQL/db/data:/var/lib/mysql -e MYSQL_ROOT_PASSWORD=mysql -d mysql
    docker ps -a
    docker stop test.db.mysql
    docker exec -it test.db.mysql mysql -uroot -pmysql
    mysqlsh -uroot -pmysql -h 127.0.0.1 -P3306
        \sql 
			show databases;
			create database test_hibernate;
		


