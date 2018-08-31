# URI

## Users

| 메소드 | 경로              | 설명           |
| ------ | ----------------- | -------------- |
| GET    | /users/{user_idx} | 회원 조회      |
| POST   | /users            | 회원 가입      |
| PUT    | /users/{user_idx} | 회원 정보 수정 |
| DELETE | /users/{user_idx} | 회원 탈퇴      |

## Login

| 메소드 | 경로    | 설명     |
| ------ | ------- | -------- |
| POST   | /login  | 로그인   |
| GET    | /logout | 로그아웃 |

## Products

| 메소드 | 경로                                         | 설명           |
| ------ | -------------------------------------------- | -------------- |
| GET    | /products?offset={page_no}&limit={page_size} | 상품 전체 조회 |
| GET    | /products/{product_idx}                      | 상품 상세 조회 |
| POST   | /products                                    | 상품 등록      |
| PUT    | /products/{product_idx}                      | 상품 수정      |
| DELETE | /products/{product_idx}                      | 상품 삭제      |

## Payments

| 메소드 | 경로                                         | 설명           |
| ------ | -------------------------------------------- | -------------- |
| GET    | /payments?offset={page_no}&limit={page_size} | 결제 내역 조회 |
| GET    | /payements/{payment_idx}                     | 결제 상세 조회 |
| POST   | /payments                                    | 상품 결제      |

