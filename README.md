# JAVA, DB 연동 및 순차적 리팩토링
0. User 객체 생성
1. ConnectionMaker Interface 및 이를 구현하는 LocalConnectionMaker 생성
   - 만약 local DB와의 연결이 아닌 AWS DB와 연결하고 싶으면 ConnectionMaker Interface를 상속받아 AWSConnectionMaker 생성해서 사용하면 됨
2. LocalConnectionMaker을 주입받아 사용하는 UserDao01 생성
   - 기능은 add, deleteAll, findById, findAll, getCount 
   - UserDaoTest01 에서 테스트 진행
3. UserDaoFactory01 생성
   - UserDao01과 LocalConnectionMaker 조립
   - UserDaoTest02 에서 테스트 진행
4. UserDaoFactory01에 Spring을 적용시킨 UserDaoFactory02 생성
   - Bean 등록 후 불러와서 사용
   - UseDaoTest03 에서 테스트 진행
5. Statement Strategy를 적용한 UserDao02 생성
   - UserDao02를 조립하는 UserDaoFactory03 생성
   - UserDaoTest04 에서 테스트 진행