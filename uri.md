# URI

## Users

| 메소드 | 경로              | 설명      |
| ------ | ----------------- | --------- |
| GET    | /users/{user_idx} | 회원 조회 |
| POST   | /users            | 회원 가입 |

## Login

| 메소드 | 경로   | 설명   |
| ------ | ------ | ------ |
| POST   | /login | 로그인 |

## Products

| 메소드 | 경로                                                | 설명           |
| ------ | --------------------------------------------------- | -------------- |
| GET    | /products?offset={offset}&limit={limit}&sort={sort} | 상품 전체 조회 |
| GET    | /products/{product_idx}                             | 상품 상세 조회 |

## Payments

| 메소드 | 경로                                                | 설명           |
| ------ | --------------------------------------------------- | -------------- |
| GET    | /payments?offset={offset}&limit={limit}&sort={sort} | 결제 내역 조회 |
| GET    | /payments/{payment_idx}                             | 결제 상세 조회 |
| POST   | /payments                                           | 상품 결제      |

