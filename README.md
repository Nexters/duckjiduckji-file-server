### 파일서버 구축

- yml에 파일저장경로 및 요청 contextPath 설정 ( yml파일을 dev, prod로 구분해서 상황에 맞게 설정하면됨)
- WebMvcConfig를 통해 apache, nginx와 같은 서버없이 특정 폴더에 있는 파일을 url요청 가능

  - 위 코드 파일 저장 경로 : /mnt/Plogging_server/images/profile/profileImg.png ( userId를 파라미터로 받아 폴더 없으면 생성해줌 )
  - 위 코드 파일 요청 경로 : http://localhost:8082/profile/userId/profileImg.png

### 테스트 방법
- http method : POST
- req url : http://localhost:8082/upload/img
- request header 
   - content-type : form-data
- request body
   - userId ( String )
   - profileImge ( file )
   - imgType ( Integer ) -> 0 : 프로필 이미지, 1 : 플로깅(산책) 이미지
- response 
  - 성공 Case 1 ( 프로필 이미지 )
  ```
     {
      "code": "200",
      "msg": "이미지 업도르에 성공하였습니다.",
      "body": {
          "img_url": "http://localhost:8082/profile/xowns4817/profileImg.png"
      }
  ```    
  - 성공 Case 2 ( 플로깅 이미지 )
  ```
     {
      "code": "200",
      "msg": "이미지 업도르에 성공하였습니다.",
      "body": {
          "img_url": "http://localhost:8082/profile/xowns4817/plogging_2021030612000000.png"
      }
  ```  
  
  - 파라미터 오류
  ```
     {
    "code": "400",
    "msg": "요청 파라미터를 확인해주세요.",
    "body": { }
  ```
   
   - 실패
  ```
    "code": "500",
    "msg": "이미지 업로드에 실패하였습니다.",
    "body": { }
  }
  ```
