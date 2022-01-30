### 파일서버 구축

- yml에 파일저장경로 및 요청 contextPath 설정 ( yml파일을 local, dev, prod로 구분해서 상황에 맞게 설정하면됨)
- WebMvcConfig를 통해 apache, nginx와 같은 서버없이 특정 폴더에 있는 파일을 url요청 가능

  - 파일 저장 경로 : /{basePath}/{roomId}/{contentId}/{timestamp}.png (roomId와 contentId가 없으면 해당 폴더 생성)
  - 파일 요청 경로 : http://localhost:8082/{roomId}/{contentId}/{timestamp}.png

### 이미지 업로드 API
- http method : POST
- req url : http://localhost:8082/upload/img
- request header 
   - content-type : form-data
- request body
   - roomId ( String )
   - contentId ( String )
   - img( multipart-form -> file)
- response 
  - 성공 Case
  ```
   {
      "code": "200",
      "msg": "이미지 업도르에 성공하였습니다.",
      "body": {
          "img_url": "http://localhost:8082/aa/bbb/20220112312353.png"
      }
    }
  ```    
  
  - 파라미터 오류
  ```
  {
    "code": "400",
    "msg": "요청 파라미터를 확인해주세요.",
  }
  ```
   
   - 실패
  ```
  {
    "code": "500",
    "msg": "이미지 업로드에 실패하였습니다.",
   }
  ```

### 이미지 삭제 API
- http method : DELETE
- req url : http://localhost:8082/img
- request header 
   - content-type : application/x-www-form-urlencoded
- request param
   - roomId ( String )
   - contentId ( String )
- response 
  - 성공 Case
  ```
   {
      "code": "200",
      "msg": "이미지 삭제를 완료하였습니다..",
    }
  ```    
  
  - 파라미터 오류
  ```
  {
    "code": "400",
    "msg": "요청 파라미터를 확인해주세요.",
  }
  ```
   
   - 실패
  ```
  {
    "code": "500",
    "msg": "이미지 삭제에 실패하였습니다.",
   }
  ```
