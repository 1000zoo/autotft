### Repository

> Json 파일로 부터 데이터를 불러와서 Entity를 생성한다.

- [X] Synergies Repository
    - [X] Synergy 정보를 기록 및 반환
- [ ] Champion Repository
    - [ ] Champion 정보를 기록 및 반환

### Domain

#### Entity

- [ ] Champion
    - [ ] equals, hashCode 필요
- [ ] Synergy
    - [ ] Origin
    - [ ] Class
    - [ ] equals, hashCode 필요
- [ ] ActiveConditions
    - 활성 조건을 의미하는 객체
- [ ] ChampionSynergy
    - Champion과 Synergy의 다대다 관계를 풀어내는 엔티티

- Entity 생성 순서
    - Synergy -> ActivateConditions -> Champion -> ChampionSynergy -> Synergy.champions