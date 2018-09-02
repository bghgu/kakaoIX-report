# kakaoIX Report

전체 API : https://github.com/bghgu/kakaoIX-report/wiki

## URI

### Users

Users API : https://github.com/bghgu/kakaoIX-report/wiki/Users

| 메소드 | 경로             | 설명      |
| ------ | ---------------- | --------- |
| GET    | /users/{userIdx} | 회원 조회 |
| POST   | /users           | 회원 가입 |

### Login

Login API : https://github.com/bghgu/kakaoIX-report/wiki/Login

| 메소드 | 경로   | 설명   |
| ------ | ------ | ------ |
| POST   | /login | 로그인 |

### Products

Products API : https://github.com/bghgu/kakaoIX-report/wiki/Products

| 메소드 | 경로                                                | 설명           |
| ------ | --------------------------------------------------- | -------------- |
| GET    | /products?offset={offset}&limit={limit}&sort={sort} | 상품 목록 조회 |
| GET    | /products/{productIdx}                              | 상품 정보 조회 |

### Payments

Payments API : https://github.com/bghgu/kakaoIX-report/wiki/Payments

| 메소드 | 경로                                                | 설명           |
| ------ | --------------------------------------------------- | -------------- |
| GET    | /payments?offset={offset}&limit={limit}&sort={sort} | 결제 목록 조회 |
| GET    | /payments/{paymentIdx}                              | 결제 내역 조회 |
| POST   | /payments                                           | 상품 결제      |

## 시작하기

모든 소스코드는 IntelliJ IDEA + Window10 + JAVA 8 환경에서 작성되었습니다.

이 프로젝트에서는 아래 같은 **MAVEN 의존성 프로젝트**가 포함되어있습니다. 

```
<dependencies>
	<dependency>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-data-jpa</artifactId>
	</dependency>
	<dependency>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-web</artifactId>
	</dependency>
	<dependency>
		<groupId>mysql</groupId>
		<artifactId>mysql-connector-java</artifactId>
		<scope>runtime</scope>
	</dependency>
	<dependency>
		<groupId>org.projectlombok</groupId>
		<artifactId>lombok</artifactId>
		<optional>true</optional>
	</dependency>
	<dependency>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-tomcat</artifactId>
		<scope>provided</scope>
	</dependency>
	<dependency>
		<groupId>com.auth0</groupId>
		<artifactId>java-jwt</artifactId>
		<version>3.4.0</version>
	</dependency>
</dependencies>
```

## 실행하기

window 10 환경

- `jdk8` 과 `maven` 을 설치합니다.
- `JAVA_JOME` 환경변수 설정을 합니다.
- `Path`에 `maven` 환경변수 설정을 합니다.
- 내장 톰캣을 이용해 서버를 배포 합니다.
- spring boot 앱 실행
- `application.properties` 파일이 필요합니다.

```
mvn spring-boot:run
```

- 중지하려면, 키보드에서 `Crtl + C`를 누릅니다.
- `application.properties` 파일이 필요합니다.

AWS EC2 Ubuntu 환경

- `jdk8` 과 `maven` 을 설치합니다.
- 백 그라운드 spring boot 앱 실행
- 내장 톰캣을 사용해 배포합니다.

```
nohup mvn spring-boot:run&
```

- 중지하려면,  `netstat -tnlp` 명령어를 통해 프로세스를 kill 하십시오.

## 배포

- AWS EC2 - 애플리케이션 서버
- AWS RDS - Mysql DB 5.7
- AWS S3 - Cloud File Storage
- Docker

## 사용된 도구

- [Spring-boot](https://projects.spring.io/spring-boot/) - Spring-boot 웹 프레임워크
- [Maven](https://maven.apache.org/) - 의존성 관리 프로그램
- [Tomcat](http://tomcat.apache.org/) - 웹 애플리케이션 서버
- [IntelliJ IDEA](https://www.jetbrains.com/idea/) - IDE
- [MySql](https://www.mysql.com/) - DataBase
- [AWS EC2](https://aws.amazon.com/ko/ec2/?sc_channel=PS&sc_campaign=acquisition_KR&sc_publisher=google&sc_medium=english_ec2_b&sc_content=ec2_e&sc_detail=aws%20ec2&sc_category=ec2&sc_segment=177228231544&sc_matchtype=e&sc_country=KR&s_kwcid=AL!4422!3!177228231544!e!!g!!aws%20ec2&ef_id=WkRozwAAAnO-lPWy:20180412120123:s) - 클라우드 환경 컴퓨팅 시스템
- [AWS RDS](https://aws.amazon.com/ko/rds/) - 클라우드 환경 데이터베이스 관리 시스템
- [AWS S3](https://aws.amazon.com/ko/s3/) - 클라우드 환경 객체 스토리지
- [Docker](https://www.docker.com/) - 컨테이너 기반의 오픈소스 가상화 플랫폼

## 저자

- **배다슬** - [bghgu](https://github.com/bghgu)