# 📖 리팩터링 (2판)

리팩터링 2판을 읽고 이해한 것을 자바로 나름대로 정리해 본 내용입니다. 정확하게 이해한 내용이 아닐 수 있습니다.

----
## 목차

- [x] [01 리팩터링: 첫 번째 예시](https://github.com/songsimo/refactoring2/tree/master/src/main/ch01)
- [ ] 02 리팩터링 원칙
- [ ] 03 코드에서 나는 악취
- [ ] 04 테스트 구축하기
- [ ] 05 리팩터링 카탈로그 보는 법
- [ ] 06 기본적인 리팩터링
- [ ] 07 캡슐화
- [ ] 08 기능 이동
- [ ] 09 데이터 조직화
- [ ] 10 조건부 로직 간소화
- [ ] 11 API 리팩터링
- [ ] 12 상속 다루기

----
## 느낀점

- [x] 01 리팩터링: 첫 번째 예시

예시가 자바스크립트로 구현되어 있어 자바로 비슷하게 만들기가 어려웠다. 최대한 비슷하게 만들기는 했지만 챕터에서 강조한 리팩터링 &rarr; 테스트 과정에 더 신경써서 작업했다.

코드를 작성하면서 실무에서 안 써본 데이터 클래스(Record)를 썼다. 그 때문에 도메인 클래스와 제대로 분리하지 못하고 챕터 예를 끝냈다.

리팩터링2 정리와 별개로 데이터 클래스, 엔티티 클래스, 도메인 클래스에 대한 고민을 해봐야겠다.