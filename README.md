# NineAttack
2D running game.

## Note
- ResourceManager 에 Coin image 추가
- ItemObject 를 상속하는 (1) Coin (2) Bullet class 생성
    - Collision detection 구현
    - Graphic --> Sprite or Texture
    - Visual effect when it collide with me
        1. 충돌
        2. Object 사라짐.
        3. 충돌 효과 : 충돌 지점을 시작점으로 해서 효과 구현
- [ Coin ]

- [ Bullet ]

- GameObject
    - movement : 등속도외의 속도 적용 검토 (Andengine 참조)

- Pool 기능을 갖는 Factory 를 만들고 → Block, Coin, Bullet 이 이를 상속하도록 구현

##잡담
- Thread를 구현하고는 run 을 안 했어... --> 당연히 run을 안 하지...하하하하하하