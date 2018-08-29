# URI

## Users

| 메소드 | 경로              | 설명      |
| ------ | ----------------- | --------- |
| GET    | /users/{user_idx} | 회원 조회 |
| POST   | /users            | 회원 가입 |

## Login

| 메소드 | 경로    | 설명     |
| ------ | ------- | -------- |
| POST   | /login  | 로그인   |
| GET    | /logout | 로그아웃 |

## Items

| 메소드 | 경로                                      | 설명           |
| ------ | ----------------------------------------- | -------------- |
| GET    | /items?offset={page_no}&limit={page_size} | 상품 전체 조회 |
| GET    | /items/{item_idx}                         | 상품 상세 조회 |
| POST   | /items                                    | 상품 등록      |
| PUT    | /items/{item_idx}                         | 상품 수정      |
| DELETE | /items/{item_idx}                         | 상품 삭제      |

## Payments

| 메소드 | 경로                     | 설명           |
| ------ | ------------------------ | -------------- |
| GET    | /payments                | 결제 내역 조회 |
| GET    | /payements/{payment_idx} | 결제 상세 조회 |
| POST   | /payments                | 상품 결제      |

