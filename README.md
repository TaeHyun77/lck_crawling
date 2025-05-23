## lck_crawling
리그오브레전드 게임 Lck 경기 크롤링 프로젝트

## 목적 
선호하는 팀의 경기 일정과 순위를 확인하기 위해 여러 사이트를 일일이 방문해야 하는 불편함을 해소하고자 이 서비스를 개발

## 사용 기술
- BackEnd : SpringBoot, Spring Data Jpa
- FrontEnd : React.js, JavaScript
- DataBase : MySQL , Redis
- ETC : Firebase

## 기능
- Oauth를 통한 구글 로그인
- 월 별 경기 일정 파악 ( 네이버 e스포츠 크롤링 )
- Lck 팀들의 순위 파악 ( op.gg Lck 크롤링 )
- 선호하는 팀 선택 
- Fcm 알림 서비스를 통한 선호하는 팀 경기 알림 기능 ( 자정에 한 번, 경기 일정 3시간 전부터 1시간마다 )

## 개선 여부
- 일정, 순위 데이터 크롤링 시간 단축을 위해 redis를 활용한 캐싱으로 시간 단축 
- 일정 데이터, 순위 데이터를 동기적으로 크롤링 함으로써 시간 소요가 심하기에 CompletableFuture 통한 비동기 크롤링 실행<br>
  → ( 크롤링 10번 평균 ) 동기 : 11,190ms , 비동기 : 7,408ms -> 시간 소요 약 33.8% 감소<br><br>
- React.js interceptor를 통한 jwt 토큰 재발급

## 페이지 ( 4/20 업데이트)
- 홈 페이지<br>
  <img width="1383" alt="Image" src="https://github.com/user-attachments/assets/4ea4f242-e241-41b2-8622-672adf2a3612" width=600/><br><br>

- 순위 페이지<br>
  <img src="https://github.com/user-attachments/assets/20da48ee-5ca3-4e8f-91c3-1b44c31eff7f"><br><br>

- 로그인 시 화면<br>
  선호하는 팀 선택 가능, 선호하는 팀의 경기를 필터링 해서 볼 수 있음<br>
  <img width="1382" alt="Image" src="https://github.com/user-attachments/assets/f535ae0d-63af-4044-bfbc-3438b012860c" /><br><br>

- 알림 기능<br>
  알림 허용 기능을 구현하여 사용자 임의대로 수정 가능<br>
  알림 허용의 경우 FCM을 통해 크롬 알림 구현<br>
  <br>
  <img width="1288" alt="Image" src="https://github.com/user-attachments/assets/1d524cdc-6856-4490-9a73-3c35889e459a" /><br><br>

## DB 
![Image](https://github.com/user-attachments/assets/48657990-8fff-42e9-9c1f-dd46c848f3e6)



