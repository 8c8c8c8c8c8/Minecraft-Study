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

### Day 5
<details>
<summary>Advanced Block, Food Item</summary>  

`Sound Block` 을 만들어 봤다.  
우클릭하면 소리가 난다.  

음식 `Strawberry` 를 만들어 봤다.  
0.1의 확률로 이동속도가 10초 빨라진다.  

지난 시간에 `Metal Detector` 를 사용하면 내구도가 감소하지 않은 이유는 `Creative mode` 에서는 체력, 배고픔, 내구도가 감소 하지 않기 때문이였다.  
포만감이 가득 찬 경우 음식을 먹을 수 없다.  

</details>

### Day 6
<details>
<summary>Fuel Item, Tooltips, Tags</summary>

custom `Tags` 를 만들때 `#forge:ores` 의 의미는 forge 에 등록된 모든 ores 이다.  
즉, custom ores 도 여기에 포함된다.  
다른 mod 에서 추가된 ores 도 포함되므로 사용하기 좋다.  
</details>

### Day 7
<details>
<summary>Data generator</summary>

`resource/assets` 에는 custom object 에 대한 정보(.json)가 있다.  
이는 수작업으로 만들고 있었는데 `Provider` 라는 기능이 있어 이것들을 생성해 준다.  
덕분에 우리는 `lang` 과 `texture` 에 신경쓰면 된다.  

</details>

### Day 8
<details>
<summary>Day 7(2)</summary>

`BlockLootTables` 를 만들때 `sapphire` 기반의 `ores` 의 drop 은 `raw sapphire` 가 된다.  
이유는 `ores` 는 환경에 따라 형태가 다를 뿐 채굴 결과는 동일해야 한다는 마인크래프트 기본 로직이 있기 때문이다.  
`end stone sapphire`, `nether sapphire` 는 `sapphire` 의 한 종류이며 drop 이 `raw sapphire` 라면 인벤토리 관리와 제련이 편리하기 때문이다.  

</details>

### Day 9
<details>
<summary>Stairs, Slabs, etc.</summary>

영상에 나온대로 하지 않고 기존의 method 를 활용했다.  
java 문법을 좀 더 공부한다면 `NewItemModelProvider` 를 간단하게 만들 수 있을듯...  

</details>

### Day 10
<details>
<summary>2D Texture with 3D model, custom tools, LangProvider</summary>

`resource/assets/lang/en_us.json` 을 `NewEnLangProvider` 로 대체 하였다.  
수작업으로 item 을 추가하는 부분을 자동으로 바꾸는 방법에 대해 생각해 볼 예정이다.  
예를 들면 `NewCreativeTabs` 에 새로 추가한 item 을 수작업으로 등록해야 한다.  
코드의 간결화를 위해 반드시 수작업은 반드시 바꿔야 한다.  
</details>

### Day 11
<details>
<summary>custom armor</summary>

`trimmable armor` 란 치장 가능한 혹은 외형을 꾸밀 수 있는 방어구를 말한다.  
성능에 영향을 주지 않는, 시각적으로만 변화를 줄 수 있는 방어구이다.  
이에 관한 `models/item` `.json` 을 만들어야 하는데 관련 코드가 약간 복잡하다.  
따라서 제공된 코드를 보고 분석하여 나만의 코드를 만들 예정이며 현재는 빈칸으로 남겨놨다.
</details>

### Day 12
<details>
<summary>full-suit set-bonus effect</summary>

제공받은 예시 코드를 refactoring 할 것.  
server-side, client-side 구분하여 프로그래밍 할 수 있다.  
event bus 에 등록하면 server-side 에서만 실행할 수 있다.  
다만 어려운 점은 어떤 부분을 event bus 에 등록하는 것이냐 인데...  
</details>

### Day 13
<details>
<summary>global loot modifier</summary>

`global loot modifier` 는 모든 block, entity 등 에 대한 전리품 획득을 수정할 수 있다.  
`provider` 는 전리품 규칙을 json 으로 생성한다.  
java object 에서 의미있는 데이터를 모아 통신가능한 형태(json 같이)로 저장한다.  
이를 `직렬화(serializer)` 라 한다(반대는 `역직렬화`).  
전리품 획득 상황이 나오면 앞서 설정한 규칙이 현 상황과 일치하면 새로운 규칙에 의한 전리품을 추가한다.  

#### To-do.

#### Done.

</details>

### Day 14
<details>
<summary>adding item to suspicious sand, custom crops</summary>

`수상한 모래(suspicious sand)` 에 전리품을 추가하는 방법을 배웠다.  
영상에서는 새로운 loot modifier 를 만들었지만 나는 기존의 modifier 를 이용하려고 한다.  
loot modifier 의 doApply 는 전리품을 추가하는 방식이다.  
만약 다른 방식이 필요하다면 새로운 modifier 를 만드는게 좋다.  
수상한 모래에 sapphire 를 추가하여 이를 확인하고 싶었는데 도저히 찾을 수가 없어 보류한다.  
world 를 생성할 때 야생의 수상한 모래만 drop table 에 연결되고 creative mode 에서 얻은 수상한 모래에는 연결되지 않는다.  

이름에 `predicate` 가 있으면 참 혹은 거짓을 반환하는 역할이다.  
`StatePropertiesPredicate` 는 주어진 block 의 상태를 확인하여 properties 가 조건에 맞는지 검사하는 역할을 한다.  
`STRAWBERRY_CROP` 는 BlockItem 을 만들 필요가 없어서 등록할 때 `BLOCKS.register` 를 사용했다.  

</details>

### Day 15
<details>
<summary>block high crops</summary>

`Day 14` 와 다른 점은 2칸 높이의 작물 구현이다.  

_**CornCropBlock**_
- SHAPE_BY_AGE : AGE 에 따른 block 의 시각적 모양
- randomTick : 밝기 조건, 윗 블럭 생성 조건 아니면 AGE 증가
- canSurvive : 아래 block 이 파괴되면 윗 block 도 파괴
- growCrops : `뼛가루(Bonemeal)` 를 사용하여 성장, currentAge 에 따라 윗 block 생성

`noCollission` : 충돌없이 지나갈 수 있음.  
`noOcclusion` : 빛을 가리거나 막을 수 없음.  

#### To-do
- [x] `NewBlockStateProvider` makeStrawberryCrop, strawberryStates 를 refactoring 할 것.
- [ ] `CornCropBlock` randomTick 을 refactoring 해볼 것.

</details>

### Day 16
<details>
<summary>custom flowers</summary>

`commonSetup` 은 게임 초기화 단계에서 생성과 렌더링에 관계없이 서버와 클라이언트 모두에 공통적으로 실행되어야 하는 로직을 실행하는데 사용된다.  
예를 들어 아이템-블록 간의 연결 정의(예: flower-potted_flower), player 에게 특정 recipe 공개 등.  
여기서는 반드시 `event.enqueueWork` 를 사용해야 하는데 병렬로 처리할 수 있기 때문이다.  
`commonSetup` 은 모든 object 들이 registry 에 등록된 후 실행된다.  
main thread 에서 실행하도록 보장한다.  

아직 `commonSetup` 에 대해 이해하지 못함...  

### To-do
- [x] **refactoring:** `NewBlockStateProvider`, `NewItemModelProvider`

</details>

### Day 17
<details>
<summary>refactoring, bug fix</summary>

### Done
- [x] **refactoring:** `NewBlockStateProvider`, `NewItemModelProvider`, `AbsCropBlock`
- [x] **fix:** `NewModBlockLootTables`
</details>