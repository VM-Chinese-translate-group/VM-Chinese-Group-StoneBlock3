# VM-Chinese-Group-StoneBlock3

 ## 这是什么？
 
 这是VM汉化组石头世界3检测汉化补丁更新的提醒模组，当玩家汉化补丁旧时，模组会在聊天框发送信息提醒玩家更新汉化。
 
 ## 检测原理是什么？
 这个模组相当简单，作者甚至都不会 Java ，是用ChatGPT生成的（不过错误很多，还是手动改了一部分）。
 1. 在游戏的config文件夹中查找`vmct.txt`（不会自动创建，需要手动创建）并阅读内容；
 2. 获取`https://vmct-cn.top/sb3/update.txt`的内容；
 3. 对比2个内容，不一致就在聊天框提醒更新。
  ![加载失败](https://vmct-cn.top/img/mod.png)
