# MicroBlog-Spring

!["School project"](https://img.shields.io/badge/PROJECT%20TYPE-SCHOOL-yellow?style=for-the-badge)
!["IDE"](https://img.shields.io/badge/IDE-INTELLIJ-blue?style=for-the-badge&logo=intellij-idea)
!["Language"](https://img.shields.io/badge/LANGUAGE-JAVA-orange?style=for-the-badge)

This is the same project of my ```MicroBlog``` repo, but I decided to implement it with Spring Boot

## Swagger Documentation

Too see the API's documentation with Swagger, download and launch the repo and visit

```http://localhost:8080/swagger-ui.html```

Inside the Swagger Documentation is missing the path to get the JWT; the path to get the token is ```http://localhost:8080/Microblog/api/login```

### Client
Visit the repository for my mobile client for this project made with flutter!
[Mobile client](https://github.com/teddyedo/Microblog-mobile).

### JavaDoc
If you want to see the javaDoc, please check the ```javaDoc``` folder (the link will be implemented soon).

## Quick Guide

If you want to use the ```Microblog```, you can download the project and launch it with your IDE. It's also possible to 
extract a jar file and launch it.

After the application has started, visit ```http://localhost:8080``` on your favourite browser and should appear the main 
page of the Microblog. (If you enable HTTPS inside the project, you have to visit ```https://localhost:8443```). 

Here you can navigate inside the blog, deciding whether to log in or not. Without login permissions you can only see posts.

You can choose if you want to create a new User (by default the user that you will create have only ```USER``` 
permissions, so you could only see and comment posts, not create them), or log in with an existing user; below, are 
listed the users already created, with username and password.

|Username|Password|Roles|
|--------|--------|--------|
|Rodney|admin1|ADMIN|
|Mortak|admin2|ADMIN|
|Everne1978|admin3|ADMIN|
|Admin|admintest|ADMIN|
|Bosion|user1|USER|
|Glant1980|user2|USER|
|Wasken|user3|USER|
|Oldn1978|user4|USER|
|Trainty|user5|USER|
|Tionant|user6|USER|
|Agay1994|user7|USER|
|User|usertest|USER|

Users can ONLY comments posts, Admin can both comments and creates posts.

### Sources
Some of the posts are taken from the [```Focus Facebook pages```](https://www.facebook.com/focus.it/).
 Also comments are real, but I don't report the real name of the users.
 
### Security 
This application implements two security level: 

- **FormBased authentication**,used to handle sessions and permissions inside the web app.
    - login at ```/login```; 
    - logout at ```/logout```.
- **JWT authentication**, used to handle security and permissions with some restAPI, used by 
[my Flutter Application](https://github.com/teddyedo/Microblog-mobile).
To request JWT auth, the path is ```/Microblog/api/login```. For more information about the format of the restAPI, 
visit Swagger documentation. The ```logout``` is not implemented, cause JWT handle **STATELESS** connections. To perform JWT login, you have to specifiy inside the body of the request the **username** and the **password** of an existing user. If you have inserted rights credential, you will receive a response that contain the JWT inside the header, under **Authotization**.

The JWT would be like ```Bearer JWT```; To use it, you have to insert the entire token, also with the ```Bearer ``` index, in every request you send.

### ORM Diagram
![ORM Diagram](https://raw.githubusercontent.com/teddyedo/MicroBlog-Spring/master/img/Microblog.png)

### H2 Database
This project implement 2 different databases: MySQL and H2. The default one is H2, which console is reachable at 
```http://localhost:8080/h2``` and the password is **USER1**. Normally H2 is used only for tests, because it's an in memory
database, but I implemented it in such a way that the data are permanently saved inside the ```data``` folder.

Uncommenting some lines into the ```application.properties``` file we can enable MySQL as database. It is necessary that locally
is present a database with the same specifics of the H2's one.

### HTTPS
If you want, exists the possibility to enable HTTPS for connections to the Microblog server with a self signed certificate
(By default, HTTPS is disabled to avoid connection problems with the ```Microblog - Mobile ``` client).

To enable HTTPS, it is only necessary to uncomment all the rows inside ```MicroblogApplication.java``` and rerun the project.
