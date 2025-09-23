### Minecraft forge study
- version
    - `minecraft - 1.20.1`
    - `forge - 47.0.19`
- reference
    - [ModdingByKaupenjoe](https://www.youtube.com/@ModdingByKaupenjoe)
- image
    - 각 영상의 정보란에 images 를 다운 받을 수 있음.

### Day 1
<details>
<summary> Item, CreativeModeTab 추가하기</summary>

`DeferredRegister`는 게임 로딩 시점에 forge 가 알아서 object 를 등록 해준다.  
이전에는 개발자가 eventListener 에 직접 등록해야 했다.

forge 대략적인 로딩 순서
1. `mods` 파일을 탐색하여 모든  `.jar` 파일을 읽는다.
2. `resources/META-INF` 의 `mods.toml` 을 읽어 mod 의 기본 정보를 읽는다.
3. 이때 `DeferredRegister` 가 생성되며 아직 등록한 object 는 생성되지 않는다.
4. 모든 mod 가 로딩된 후 `DeferredRegister` 가 활성화 되어 추가한 object 를 생성한다.
5. 모든 초기화와 등록이 된 후 게임이 실행된다.

위 순서 중 생략한 부분이 있는데 추후 관련된 부분을 개발할 경우 추가할 예정.

</details>

### Day 2
<details>
<summary>Block 생성</summary>

forge 는 instance 생성을 피하고 method, member variable 을 static 으로 구현을 권장한다.  
`Block` 을 구현할 때 `BlockItem` 도 등록하는 게 일반적이다.

</details>

### Day 3
<details>
<summary>Custom Recipes, Loot tables</summary>

custom block 을 생성할 때 다음 3가지 과정이 필요하다.
1. `[backend]` `DeferredRegister` 에 `Block` 을 등록한다.
2. `[frontend]` `blockstates` 에 어떤 block model 을 사용할지 정의한다.
3. `[frontend]` `models/block` 에 block model 을 정의한다.

</details>

### Day 4
<details>
<summary>Advanced Items</summary>

Custom Item `Metal Detector` 를 생성했다.  
이 Item을 사용하면 y축 `+` 방향으로 64칸을 탐색하여 사전에 정의한 Valuable block에 해당되면 그 좌표를 출력한다.  
내구도 100으로 설정하고 한번 사용하면 1씩 감소하도록 했지만 감소되지 않는다.  
추후 해결할 예정이다.  

</details>