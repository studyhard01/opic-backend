## opic-backend  

### 파일 구조  
- /opic/config/ExcelQuestionLoader : 엑셀 추출 -> json  
- /opic/controller/OpicQuestionController  
- /opic/domain/OpicQuestion  
- /opic/repository/OpicQuestionRepository  
- /opic/DemoApplication 
- /resoruces/questions : 질문 목록  

### 구현  
```
GET /api/questions/random?level=5&part=part1&conditionText=교사/교육자
```
- 조건 따른 질문 불러오기  
```
GET /api/questions/all
```  
- 모든 질문 목록 확인하기  