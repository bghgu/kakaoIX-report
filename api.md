# API

### QueryString 설명

| 파라미터 | 설명                            | 예시            | 값 범위                 |
| -------- | ------------------------------- | --------------- | ----------------------- |
| offset   | 시작 번호(기본값 = 0)           | offset=0        | 0이상 정수              |
| limit    | 가져올 데이터 갯수(기본값 = 10) | limit=10        | 1이상 정수              |
| sort     | 정렬 기준(기본값 = paymentIdx)  | sort=paymentIdx | paymentIdx, price, name |

## 결제 목록 조회

| 메소드 | 경로                                                | 짧은 설명      |
| ------ | --------------------------------------------------- | -------------- |
| GET    | /payments?offset={offset}&limit={limit}&sort={sort} | 결제 목록 조회 |

### 응답 바디

#### 상품 목록 조회 성공

```json
{
    "resultCode": 2040,
    "resultMessage": "Success Delete User",
    "result": null
}
```
#### 상품 목록 조회 실패

```json
{
    "resultCode": 4000,
    "resultMessage": "Fail delete User",
    "result": null
}
```
#### INTERNAL SERVER ERROR

```json
{
    "statusCode": 500,
    "responseMessage": "Fail",
    "responseData": null
}
```
------
## 결제 내역 조회

| 메소드 | 경로                    | 짧은 설명      |
| ------ | ----------------------- | -------------- |
| GET    | /payments/{payment_idx} | 결제 내역 조회 |

### 응답 바디

#### 상품 목록 조회 성공

```json
{
    "resultCode": 2040,
    "resultMessage": "Success Delete User",
    "result": null
}
```
#### 상품 목록 조회 실패

```json
{
    "resultCode": 4000,
    "resultMessage": "Fail delete User",
    "result": null
}
```
#### INTERNAL SERVER ERROR

```json
{
    "statusCode": 500,
    "responseMessage": "Fail",
    "responseData": null
}
```
------
## 결제

| 메소드 | 경로      | 짧은 설명 |
| ------ | --------- | --------- |
| POST   | /payments | 결제      |

### 응답 바디

#### 상품 목록 조회 성공

```json
{
    "resultCode": 2040,
    "resultMessage": "Success Delete User",
    "result": null
}
```
#### 상품 목록 조회 실패

```json
{
    "resultCode": 4000,
    "resultMessage": "Fail delete User",
    "result": null
}
```
#### INTERNAL SERVER ERROR

```json
{
    "statusCode": 500,
    "responseMessage": "Fail",
    "responseData": null
}
```
------