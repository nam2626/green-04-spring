## prompt
MainComponent 작성
1. 컴포넌트 로드시 전체 ProductDTO 정보를 출력
2. 전체 데이터 출력된 표 상단에 ProductDTO 입력해서 추가하는 폼 작성.
3. ProductDTO 형태는 아래 정보로 확인.
4. 전체 조회하는 요청 경로, 데이터 추가하는 요청 경로도 아래 정보로 확인
5. API Server 요청시 axios 활용할 것.

## ProductDTO 형태
```
{
    "id": 4,
    "name": "바닐라라떼",
    "price": 6000,
    "category": "음료"
}
```

## 호출 경로
1. 전체 조회
- url : http://localhost:8888/step08-mvc-rest/api/products
- method : GET

2. 데이터 추가
- url : http://localhost:8888/step08-mvc-rest/api/products
- method : POST

