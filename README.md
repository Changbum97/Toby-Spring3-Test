# JAVA, DB 연동 및 순차적 리팩토링
0. User 객체 생성
1. ConnectionMaker Interface 및 이를 구현하는 LocalConnectionMaker 생성
   - 만약 local DB와의 연결이 아닌 AWS DB와 연결하고 싶으면 ConnectionMaker Interface를 상속받아 AWSConnectionMaker 생성해서 사용하면 됨
2. LocalConnectionMaker을 주입받아 사용하는 UserDao01 생성
   - 기능은 add, deleteAll, findById, findAll, getCount 
   - UserDaoTest01에서 테스트 진행