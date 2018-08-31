# URI

## Users

| 메소드 | 경로              | 설명           | 필수 |
| ------ | ----------------- | -------------- | ---- |
| GET    | /users/{user_idx} | 회원 조회      | O    |
| POST   | /users            | 회원 가입      | O    |
| PUT    | /users/{user_idx} | 회원 정보 수정 | X    |
| DELETE | /users/{user_idx} | 회원 탈퇴      | X    |

## Login

| 메소드 | 경로    | 설명     | 필수 |
| ------ | ------- | -------- | ---- |
| POST   | /login  | 로그인   | O    |
| GET    | /logout | 로그아웃 | X    |

## Products

| 메소드 | 경로                                         | 설명           | 필수 |
| ------ | -------------------------------------------- | -------------- | ---- |
| GET    | /products?offset={page_no}&limit={page_size} | 상품 전체 조회 | O    |
| GET    | /products/{product_idx}                      | 상품 상세 조회 | O    |
| POST   | /products                                    | 상품 등록      | X    |
| PUT    | /products/{product_idx}                      | 상품 수정      | X    |
| DELETE | /products/{product_idx}                      | 상품 삭제      | X    |

## Payments

| 메소드 | 경로                                         | 설명           | 필수 |
| ------ | -------------------------------------------- | -------------- | ---- |
| GET    | /payments?offset={page_no}&limit={page_size} | 결제 내역 조회 | O    |
| GET    | /payments/{payment_idx}                      | 결제 상세 조회 | O    |
| POST   | /payments                                    | 상품 결제      | O    |

