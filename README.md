# QA_Project
- 抽鬼牌
- 平台：Java
- 規則
``` 
玩家一人 vs 電腦3人，兩張數字一樣的牌可丟出。 

最先丟完的人為贏家，最後剩下鬼牌的人為輸家。若是玩家已丟完牌則結束遊戲 

每一回合分為抽牌及出牌兩部分

牌共有52+1張 先出牌者在發牌時會取得14張牌，其餘玩家取得13張牌

以下定義兩張數字相同的牌為pair
```

## 功能
#### 功能列
- 開始新遊戲
  - 輸入使用者名稱(預設為user)
  - 抽選先出牌者
  - 抽選抽牌方向
    - 順時針 
    - 逆時針   
  - 洗牌
  - 發牌
  - 丟出手上持有的pair
  - 開始遊戲
- 離開遊戲
  - 彈跳視窗:是否確定離開遊戲?
    - 是
      - 關閉程式
    - 否
      - 返回程式，繼續進行
#### 主畫面  
- 分為5區 上、下、左、右、中   
- 上下左右4區，分別以與牌背顯示，其數量符合該玩家所持有的卡
- 在各區下方顯示玩家名稱
  - 左-player1 
  - 上-player2   
  - 右-player3 
  - 下-user  
    - 右下顯示結束回合按鈕
        - 結束回合按鈕  
        - 在玩家出牌回合提醒玩家 
    - 中
        - 抽牌  
        - 玩家**抽牌**時，將被抽卡者的牌(背面)顯示在**中間**畫面
        - 玩家**被抽牌**時，將玩家的牌(正面)顯示在**中間**畫面
        - 滑鼠移至牌上方時，該牌亮紅框
        - 選擇該牌時，該牌亮紅框並鎖定

#### AI玩家
  - 名稱為player1、player2、player3
  - 自動丟出Pair
