####概念
> Encapsulate a request ass an object, thereby leeting you prameterize clints with different requests ,queue or log request,
> and support operations;
>

#### 命令模式基本有三个基本的角色
* 命令接收者
* 命令
* 命令执行（调用）者

#### 优点
* 类间的解耦
* 可扩展性强
* 可以和其它模式结合来增强功能 如 责任链模式；

#### 使用场景
存在指令执行的地方都可以使用，如，GUI开发点击的操作，模拟DOS。