### Repository

> Json 파일로 부터 데이터를 불러와서 Entity를 생성한다.

- [X] Synergies Repository
    - [X] Synergy 정보를 기록 및 반환
- [X] Champion Repository
    - [X] Champion 정보를 기록 및 반환
- [X] JsonRepositoryController
    - [X] Synergy와 Champion 간의 연결관계를 설정
    - [X] Synergy load, Champion load
        - 각 repository를 반환하여 구현

### Domain

#### Entity

- [X] Champion
    - [X] equals, hashCode 필요
- [X] Synergy
    - [X] Origin
    - [X] Class
    - [X] equals, hashCode 필요
- [X] ActiveConditions
    - 활성 조건을 의미하는 객체
- [X] ChampionSynergy
    - Champion과 Synergy의 다대다 관계를 풀어내는 엔티티

