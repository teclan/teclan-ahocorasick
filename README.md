#### 测试
``` 
mvn package -Dmaven.test.skip=True

java -jar teclan-ahocorasick-xxx-SNAPSHOT.jar filePath keyword1 keyword2 ...
```
#### 说明
采用 AC 算法对大文件进行关键字精确检索(不支持模糊检索)，每次从文件读取 100MB 进行检索

