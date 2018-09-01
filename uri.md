# URI

## Users

| 메소드 | 경로              | 설명      | 필수 | 구현 |
| ------ | ----------------- | --------- | ---- | ---- |
| GET    | /users/{user_idx} | 회원 조회 | O    | O    |
| POST   | /users            | 회원 가입 | O    | O    |

## Login

| 메소드 | 경로   | 설명   | 필수 | 구현 |
| ------ | ------ | ------ | ---- | ---- |
| POST   | /login | 로그인 | O    | O    |

## Products

| 메소드 | 경로                                                | 설명           | 필수 | 구현 |
| ------ | --------------------------------------------------- | -------------- | ---- | ---- |
| GET    | /products?offset={offset}&limit={limit}&sort={sort} | 상품 전체 조회 | O    | ?    |
| GET    | /products/{product_idx}                             | 상품 상세 조회 | O    | O    |

## Payments

| 메소드 | 경로                                                | 설명           | 필수 | 구현 |
| ------ | --------------------------------------------------- | -------------- | ---- | ---- |
| GET    | /payments?offset={offset}&limit={limit}&sort={sort} | 결제 내역 조회 | O    | ?    |
| GET    | /payments/{payment_idx}                             | 결제 상세 조회 | O    | O    |
| POST   | /payments                                           | 상품 결제      | O    | O    |

