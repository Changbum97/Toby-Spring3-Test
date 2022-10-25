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
6. jdbcContextWithStatementStrategy와 try/catch/finally를 적용해 리소스를 반환한 UserDao03 생성
   - UserDao03을 조립하는 UserDaoFactory04 생성
   - ps.exectueUpdate가 고정이기 때문에 add, deleteAll만 가능
   - getCount는 jdbcContextWithStatementStrategy 일단 적용 안하고 테스트 진행
   - UserDaoTest05 에서 테스트 진행
7. DataSource 사용
   - 우리가 직접 만들어 준 ConnectionMaker 기능이 JAVA 에서 이미 DataSource로 구현되어 있음
   - DataSource에 Driver Class, URL, name, password 만 넣고 getConnection으로 받아 사용하면 됨
   - UserDao04, UserDaoFactory05 에서 적용
   - UserDaoTest06 에서 테스트 진행
8. deleteAll 익명 클래스 적용
   - 이제 StatementStrategy Interface를 상속받은 DeleteAllStrategy를 사용 안하고 그 자리에 익명 클래스 적용
   - 이러면 add, deleteAll, getCount 등 각각의 기능마다 클래스 생성할 필요 없어짐
   - UserDao04 에 deleteAll2 메소드에 적용
   - UserDaoTest06 에서 테스트 진행
9. JdbcContext 분리
   - UserDao05는 UserDao04에 있었던 jdbcContextWithStatementStrategy 메소드를 JdbcContext 클래스로 따로 분리한 코드
   - UserDao05의 deleteAll, add에는 익명 클래스 적용
   - getCount에는 아무 아직 StatementStrategy까지 적용된 상황
   - UserFactory06 에서 조립 후 UserDaoTest07 에서 테스트 진행