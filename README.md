# Mojuk-project

- application.yml
```
spring:
  application:
    name: mojuk

  datasource:
    url: jdbc:postgresql://:5432/mojuk
    username:
    password:
    driver-class-name: org.postgresql.Driver

  jpa:
    hibernate:
      ddl-auto: none #DDL 동작 설정(update: 없으면 생성, 있으면 수정 / none: 아무것도 안함)
    properties:
      hibernate:
        format_sql: true #SQL 쿼리 보기좋게 출력
        type: trace #SQL 쿼리 파라미터 값 출력
    show-sql: true #실행되는 쿼리 출력
    database-platform: org.hibernate.dialect.PostgreSQLDialect

  thymeleaf:
    prefix: classpath:/templates/
    suffix: .html
    cache: false

logging:
  level:
    org:
      hibernate:
        SQL: debug #SQL 쿼리 로그 출력
        type:
          descriptor:
            sql: trace #바인딩 된 파라미터 값 출력
```
