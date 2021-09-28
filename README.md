WeSpring
MVC구조는 Model View Controller로 사용자가 localhost:8080/ 로 치고 들어오면 Controller가 먼저 받아서 View를 연결해줌!! 만약 View한테 넘길 데이터가 있다면 Model 이란 객체에 담아서 보내야함!

Controller

사용자가 Url을 치고 들어오면 가장 먼저 여기에 걸림!
그 다음에 service를 호출 하고 service에서는 repository 또는 dao를 호출함!
controller -> service -> repository or dao 순으로 호출 후 repository -> service -> controller 로 결과 값을 받아 return에 나타낸 view 파일을 연결시켜주며 데이터를 Model에 담아 넘김
Service

비즈니스적인걸 처리함 !!
보통 인터페이스파일로 만들고 구현 클래스를 또 만든형태로 가져감 ex) UserInterface , UserInterFaceImpl
Controller 입장에서는 UserInterFace가 신한은행에 업무서비스인지 국민은행에 업무서비스인지 알필요없이 그냥 은행업무 서비스 그 자체만 호출하면 되겠금 만든다라 뭐라나 ...
Repository or DAO

여기서 보통 DB에 접근함 !!
DAO(Data Access Object)란 데이터베이스에 데이터에 접근하기 위한 객체를 말함!!
DB에 접근해서 가져온 데이터 결과를 DTO(Data Transfer Object)에 담아 service에 넘겨주면 service는 controller에게 전달함!!
DTO

Data Transfer Object 의 약자로 계층간에 데이터를 전달하기 위해서 사용하는 객체!!
DB에서 가져온 데이터 형식에 맞춰서 데이터를 받는 객체?
DB에서 가져온 데이터 형식이 domain과 맞지 않는다면 domain에서 막 이것저것 만들어야하는데 그러면 복잡해짐
domain에 메소드를 막 나두면 나중에 보기 힘들어서 DTO를 만든다고함
