# Simple HTTP Server
날짜 : 2024.03.27
목적 : python 에 SimpleHTTPServer 라는 모듈이 있다. 유사한 프로그램을 자바로 만들어본다.

## 요구 사항
simple-httpd 는 다음과 같이 동작한다.

* Argument로 port를 받는다.
  * port가 주어지지 않을 경우, 80을 사용한다.
* 프로그램이 실행된 디렉토리를 document-root 로하는 웹서버로 동작한다.
* GET / 요청시에 현재 폴더 내부 목록을 html 로 응답한다.
* GET /file-path 요청에 응답한다.
  * 파일이 존재하면 200 OK과 파일 컨텐츠를 응답한다.
  * 파일이 존재하지 않으면 404 Not Found를 응답한다.
  * 응답시에 적절한 response header를 포함해야 한다. (Content-Type, Content-Length)
* document-root 보다 상위 디렉토리를 요청하면 403 Forbidden 을 응답한다.
* 읽기 권한이 없는 파일을 요청하면 403 Forbidden 을 응답한다.
* multipart/form-data 파일 업로드를 구현한다.
  * 실행한 디렉토리에 바로 저장한다.
  * 저장 권한이 없으면 403 Forbidden을 응답한다.
  * 같은 이름의 파일이 이미 있으면, 409 Conflict를 응답한다.
* multipart/form-data 파일 업로드 외의 POST 요청은 405 Method Not Allowed 를 응답한다.
* DELETE를 구현한다.
  * URL에 지정된 파일을 지울 수 있으면 지우고 204 No Content를 응답한다.
  * URL에 지정된 파일이 존재하지 않으면 204 No Content를 응답한다.
  * URL에 지정된 파일을 지울 수 없으면 403 Forbidden 을 응답한다.
* 요청받은 내용을 화면에 출력한다. (access log)
  * 시간, 요청 method, 경로, 응답 코드, 응답 크기, 응답에 걸린 시간

### 파일 목록 가져오기

Directory에서 파일 목록을 가져오기 위해서는 java.io.File을 이용하면 된다.

1. Directory 정보를 이용해 java.io.File object를 생성한다.
2. 생성된 directory file에서 listFiles()를 이용해 파일 목록을 얻어 낸다.
3. 얻어낸 목록에는 directory 정보도 함께 포함되어 있으므로, 이를 제거한다.
4. 남은 파일에서 getName()을 이용해 파일 이름을 얻어낸다.

* link:https://www.baeldung.com/java-list-directory-files[List Files in a Directory in Java]
