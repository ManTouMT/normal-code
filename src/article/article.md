#### 并发相关的文章
1. 一文彻底搞懂并发编程与内存屏障（2023年12月04日，by IT知识刺客）
   - https://mp.weixin.qq.com/s?__biz=MzkyMjQzOTkyMQ==&mid=2247483765&idx=1&sn=c09dda54554f6ba0b86c6b31777c7ceb&chksm=c1f518ebf68291fd4f8bf203ccc96f57a15331caf02370e969b7a817253595a3a24a057603f1&cur_album_id=3283560586754949125&scene=190#rd
   - 结合intel的手册介绍了指令重排序，分析了各种重排序带来的影响，多线程情况下，只有写自己读别人的指令重排序会有问题
   - 内存屏障的作用：1. 禁止指令重排 2. 本线程/core内的变量/缓存刷回主存，对其他线程/core可见
2. 几行烂代码，用错Transactional，赔了16万（2024年07月30日，by 苏三说技术）
   - https://mp.weixin.qq.com/s/qtQ_cEk0de-hOuSbMfLnrw
   - 注意声明式事物的提交是在方法调用结束后