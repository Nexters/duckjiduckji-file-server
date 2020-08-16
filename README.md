### 파일서버 구축

- yml에 파일저장경로 및 요청 contextPath 설정 ( yml파일을 dev, prod로 구분해서 상황에 맞게 설정하면됨)
- WebMvcConfig를 통해 apache, nginx와 같은 서버없이 특정 폴더에 있는 파일을 url요청 가능
  - 위 코드 파일 저장 경로 : E:\user_profile\userId\profileImg.png ( userId를 파라미터로 받아 폴더 없으면 생성해줌 )
  - 위 코드 파일 요청 경로 : http://localhost:8082/profile/userId/profileImg.png

### 테스트 방법
- http method : POST
- req url : http://localhost:8082/upload/profileImg
- request header 
   - content-type : form-data
- request body
   - userId ( String )
   - profileImge ( file )
- response 
   - 
   {
    "code": "200",
    "msg": "이미지 업도르에 성공하였습니다.",
    "body": {
        "profile_url": "http://localhost:8082/profile/xowns4817/profileImg.png"
    }
  }
