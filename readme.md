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

### Day 18
<details>
<summary>trades, villager, sound</summary>

- trades for villager and wandering trader
- custom villagers
- custom sounds

`@Mod.EventBusSubscriber` 가 붙은 class 는 해당 class 의 `@SubscribeEvent` 가 붙은 `static` method 를 forge 의 event system 에 등록한다.  
위 annotation 이 붙었다고 해서 server-side 에서 실행되는 게 아니다.  
event 에 따라서 어디서 실행될지 결정되는 것.  

`POI(Point Of Interest)` 는 villager 가 상호작용할 수 있는 특정 위치를 말한다.  
예를 들어 침대 POI 는 잠자는 장소이다.  
이들은 POI 를 기준으로 경로를 탐색하고 활동한다.  
주의점은 이미 POI type 을 가지고 있는 block 에 새로운 POI type 을 만들 수 없다.

`assets/sounds.json` 은 `datagen` 대상이 아니다.  
이는 client assets 에 속하기 때문이다.  

`sapphire staff` 는 gui, 1인칭, 3인칭에 따라 모델링이 다르다.  
이런 복합 모델은 manual 정의를 한다.  
이를 생성하는 datagen helper 는 없다.  

마크의 사운드 확장자는 `ogg` 만 가능하다.  
다른 확장자(예: mp3) 는 라이센스 문제가 발생할 수 있다.  

</details>

### Day 19
<details>
<summary>disc</summary>

- custom disc
  - Item 이 특수한 상호작용을 하는 경우(예: trimmable, sound event) 관련 tag 를 추가한다.  
  - tag 를 추가한다고 작동되는 게 아니라 동작도 구현해야 한다.

mod 를 개발할 때 일반적으로 DB 를 사용하지 않는다고 한다.  
예를 들어 tooltip 을 가져오기 위해 DB 를 사용하는 건 복잡한 과정이라고 한다.  

### Done
- [x] **feat:** `NewEnLangProvider` Item, Block 에 description 추가하는 method 구현  

</details>

### Day 20
<details>
<summary>mob</summary>

- custom mobs

- `EntityModel` vs `HierarchicalModel`
  - 전자는 단순한, 후자는 복잡한 model 에 사용하기 적합하다.  
  - 전자는 entity 의 모든 part 를 수동으로 설정해야 됨, 후자는 root part 를 설정하면 나머지 부분이 이를 따라 움직인다.  
  - 요구되는 resource 크기 차이는 없다.  
  - resource 는 model 을 구성하는 part 가 많을수록 gpu 요구량이 커진다.  
  - 또한 생성해야 하는 model 이 많을수록 cpu, memory 요구량이 커진다.  
- `NewEventBus` vs `NewClientEventBus`
  - 전자는 entity 의 attribute 을 server, client 가 모두 알아야 해서 양쪽에서 실행된다.  
  - 후자는 entity 의 rendering 관련으로 server 에서 실행할 필요가 없다.  

`RhinoModel` 을 보면 코드의 양이 많다.  
이는 하드코딩이 아니며 `BlockBench` 에서 modeling 하고 java 로 변환한 것이다.  
modeling 이 복잡할수록 양이 많아 진다.  

minecraft 에서 `Entity` 는 living object(예: player, monster...), 움직이지만 생명체가 아닌 object(예: arrow, dropped item, falling block...), special object(예: orb, ...) 를 말한다.  

</details>

### Day 21
<details>
<summary>attack animation</summary>

player 가 monster 에게 damage 를 주거나 받을 때 log file 생성하는 것이 더 효율적이다.  
db 연결하여 저장하는 것은 비추천한다.  

`static initialization block` 은 jvm 이 해당 class 를 load 할 때 단 한 번 실행된다.  
이것은 static field 의 initialization 을 위해 존재하므로 이 다음으로 static block 이 실행된다.  

class field 는 상속되지 않는다.  
같은 이름을 사용할 경우 `hiding` 이라 부른다.  
자식 class 에서 상속받은 method 를 사용하면 method 가 정의된 scope 내 field 를 참조한다.  
즉 hiding 한 자식 class 의 field 를 참조하는 것이 아닌 부모 class 의 field 를 참조한다.   

### Done
- [x] **feat:** `NewItemModelProvider.trimmableArmorItem` 구현
- [x] **refactoring:** `NewEvents`, `MetalDetectorItem`, `NewCreativeTabs`

</details>

### Day 22
<details>
<summary>block entity</summary>

- custom block entity

`NewBlocks` 에서 blockItem 을 만드는 block 과 그렇지 않은 block 을 구분했다(`BLOCKS`, `NO_BLOCK_ITEMS`).  
`NewBlockLootTables.getKnownBlocks` 는 loot table 을 만들어야 할 모든 custom block 들을 반환해야 한다.  
기존은 `BLOCKS` 만 반환하여 오류가 발생했다.  
`NO_BLOCK_ITEMS` 를 추가하여 해결했다.  

`RhinoModel.setupAnim` 의 logic 중 idleAnimationState 에 idle, attack state 가 입력되고 있다.  
논리적으로 문제가 있어 이를 수정했다.  
</details>

### Day 23
<details>
<summary>recipe type</summary>

- custom recipe types

### To-do
- [ ] **bug:** `GemPolishingStationBlockEntity` 에 문제가 생겨 polishing 작동이 되지 않는다.  
- [ ] **refactoring:** `GemPolishing` 관련 모든 코드(하드코딩, method 분리)
- [ ] **feat:** custom recipe type 나머지 부분 완성

</details>

### Day 24
<details>
<summary>Day 22, 23</summary>

- Day 22, 23 일부 수정.
- Day 23 미흡한 부분 추가.

### Done
- [x] **fix:** `GemPolishingStationBlockEntity.tick` 이 부분 중 `resetProgress()` 의 위치가 잘못 되어 수정함.  
- [x] **fix:** `IntStream.of` - > `IntStream.range` 로 수정

</details>

### Day 25
<details>
<summary>JEI, block entity renderer</summary>

- JEI(just enough item) compatibility
  - inventory, recipe 탐색을 도와주는 mod. 
- block entity renderer
  - gem polishing station 에 recipe 에 맞는 input 을 넣으면 station 에 icon 이 나온다.  
  - output 이 생성되면 icon 이 교체된다.  
  - 매 tick 마다 rendering 한다.  
  - rendering 에 난수가 사용되는데 이는 객체에 미묘한 불규칙성을 부여한다.  
  - 예를 들어 동일한 block 이라도 드랍시 난수 기반으로 약간 회전을 시키거나 반복되는 패턴을 피하기 위함이다.  
  - 모든 요소를 완벽히 계산하는 것 보다 난수를 이용해 효율적으로 계산할 수 있다.  

`NBT(Named Binary Tag)` 게임 데이터를 저장하기 위해 NBT 형식을 사용한다.  

`chunk` world 데이터를 관리하고 로드하는데 사용되는 가장 기본적인 구획 단위.  
가로x세로x높이 16x16x256 블록.  
해당 chunk 에 있는 모든 block의 위치, 종류, 상태와 entity, block entity 등을 파일로 저장한다.  
`GemPolishingStationBlockEntity` 는 memory 에 해당 chunk 가 있을 때만 `tick` method 를 실행한다.  
chunk 데이터가 release 되면 block entity 는 멈추고 상태를 저장한다.  
chunk 를 release 하고 block entity 만 실행하게 할 수 없다.  
영구적으로 block entity 를 실행하고 싶으면 강제로 chunk load 를 하여야 한다(IChunkloader 혹은 ForgeHooks.onChunkLoader 사용).  

</details>

### Day 26
<details>
<summary>custom wood</summary>

- custom wood
  - 영상에서 pine log -> pine planks 를 만드는 recipe 생략했다.  
  - `BlockBehaviour.Properties.strength`
    - `hardness` 높을수록 손이나 도구로 파괴하는데 오래 걸림.  
    - `resistance` 높을수록 폭발 저항이 높음.  
  - `NewFlammableRotatedPillarBlock` 에 의해 불이 붙는 기능을 구현했더라도 `BlockTags.LOGS_THAT_BURN` tag 가 없으면 동작하지 않는다.  

`renderType`
  - `cutout` 특정 pixel 을 투명하게 처리하여 leaves block 의 구멍 뚫린 texture 를 표현해준다. 주변 빛을 투과할 수 있다.  
  - `solid` 기본값으로 불투명하고 단단한 것처럼 표현해준다.  

`Wrapper` primitive type 을 객체로 변환하여 기존 type 에 없는 기능을 **동적으로** 추가할 수 있다.  

```java
// example
LazyOptional<IItemHandler> itemHandler = LazyOptional.of(() -> itemHandler);
```
`IItemHandler` 는 핵심 기능이고 추가로 `LazyOptional` 에 의해 지연 초기화 및 안전성을 제공받는다.  

</details>

### Day 27
<details>
<summary>custom sign</summary>

- custom sign
  - 표지판, 걸이형 표지판 블럭

</details>

### Day 28
<details>
<summary>custom boat</summary>

- custom boat
  - custom boat entity 정의
  - entity 등록
  - boat item 생성 및 등록
  - boat renderer 생성 및 등록, texture asset 경로 추가
  - provider 에 등록

</details>

### Day 29
<details>
<summary>throwable projectiles</summary>

- throwable projectiles

### Done
- [x] `NewBoatEntity`, `NewChestBoatEntity` 중복되는 코드가 많지만 고민 끝에 지금 방법이 최선이라 판단했다.  

</details>

### Day 30
<details>
<summary>bug fix, refactoring, ore generation</summary>

- ore generation
  - `NewConfiguredFeatures` over-world, nether, end 차원에서 어떤 ore 가 sapphire ore 로 대체되는지, 어떤 크기로 생성할지에 대한 정보를 나타낸다.  
  - `RuleTest` ore 가 어떤 block 을 대체할 수 있는지 검사하는 조건이다.  

### Done
- **refactoring**
  - [x] `GemPolishingStationBlockEntity`, `GemPolishingStationMenu` 일부 hard-coding 제거. 
- [x] **fix:** `NewBlocks` 의 `DICE` block 을 `NO_BLOCK_ITEM` 으로 등록했더니 해결했다.  

</details>

### Day 31
<details>
<summary>ore generation</summary>

- ore generation
  - `NewOrePlacements` 의 method 는 `OrePlacements` 에서 가져왔으며 private 이라 그대로 가져왔다.
  - `Registry` vanilla minecraft 의 중앙 데이터베이스이며 모든 object 의 id 를 갖고 있다.  
  - `ForgeRegistries` vanilla registry 사용을 편리하게 하기 위한 helper class 이다.
  - `DeferredRegister` `Registry` 에 안전하게 등록 해준다.  
  - `ResourceKey` 객체의 id 이며 `RegistryObject` 등록할 때 내부적으로 생성된다.
  - `ConfiguredFeature` 무엇을 생성할 것인가, `PlacedFeature` 어디에 생성할 것인가, `BiomeModifier` 어떤 biome 에 무엇을 어디에 생성할 지 결정한다.  
  - `BiomeFeature` 아닌 `BiomeModifier` 이유 : 기존 vanilla system 을 건드리지 않고 추가하거나 변경하는 방식이다.
- `Data-Driven` 예를 들어 곡괭이에 관한 데이터를 코드와 함께 유지한다면 수정하고 나서 다시 compile 해야 된다.  
하지만 데이터를 코드와 분리한다면 json 과 같은 데이터 파일을 수정하면 된다.  
이런 방식으로 기존 데이터를 건드리지 않고 추가하거난 덮어쓰는 것으로 사용할 수 있다.  

</details>

### Day 32
<details>
<summary>custom tree, tree generation</summary>

- custom tree
- tree generation
  - `NewPlacedFeatures` 중 `pine tree` 를 등록할때 contextExtra 의 `extraChance` 값은 반드시 `1 / N` 꼴이어야 한다.  
  그렇지 않으면 exception 발생한다.  
  `float (1 / N)` 이런 식으로 사용 가능하다.  

### Done
- [x] **refactoring:** `NewItemModelProvider` `functional interface` 사용했다.

</details>

### Day 33
<details>
<summary>refactoring</summary>

- `@FunctionalInterface` 는 `->` 와 `::` 를 지원해준다.  
오직 하나의 추상 method 를 가진다.  
- `Builder.of` 는 `Builder` pattern 이며 `of` method 는 보통 instance 를 생성하는 의미로 쓴다.  
constructor 로 생성하는 것보다 더 가독성이 좋고 특정 타입의 객체를 반환함을 보장한다.  

### Todo
- [ ] **feat**
  - [ ] `diamond`, `sapphire` 에 대한 `gem_polishing` recipe 를 provider 로 생성해보기.
  - [ ] `dice` 관련 model json 을 provider 로 생성해보기
- [ ] **fix:** `pine boat` 설치가 제대로 작동하지 않는다.

### Done
- [x] **refactoring**
  - [x] `New(Hanging)SignBlockEntity`, `NewBlockEntities.(HANGING)SIGNS` 순환 의존성(circular dependency) 인줄 알았으나 아니었다.
  - [x] `NewSignBlock` 들은 `new NewSignBlockEntity` 를 사용하고 있어서 높은 결합도를 가진다.  
  따라서 이를 수정했다.  

</details>