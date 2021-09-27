## 一、Handler机制
参考：https://www.jianshu.com/p/9f13f5774c08

### 1、简单描述Android消息机制
- Android消息机制主要是由Handler、Looper、MessageQueue、Message协同工作的，主要处理Android线程间的通信，常用来利用主线程与子线程间的通信从而解决Android无法在子线程更新UI的问题
- Handler通过sendMessage(本质上是调用sendMessageAtTime)，从而调用messageQueue.enqueueMessage将Message加入到消息队列MessageQueue(单链表)中，MessageQueue负责消息的存储与管理
- ActivityThread类里面的main方法会调用Looper.prepareMainLooper获取主线程的Looper并调用Looper.loop；loop方法会不断轮询通过queue.next方法获取Message，并将Message交给对应的target handler来处理(handler.dispatchMessage)；handler回调自身的 handleMessage方法来处理Message。

### 2、为什么不能在子线程更新UI ?
参考：https://www.jianshu.com/p/1b2ccd3e1f1f
- ViewRootImpl（onResume之后创建）中会调用checkThread检查当前线程是否是主线程，不是主线程就会抛出异常
- 系统不建议在子线程访问UI是由于Android的UI控件不是线程安全的，如果在多线程中并发访问可能会导致UI控件处于不可预期的状态

### 3、Looper.loop方法是死循环为什么不会造成卡死（ANR）？
- ActivityThread中的main方法主要就是做消息循环，一旦退出消息循环，应用也就退出了
- Looer.loop()方法可能会引起主线程的阻塞（messageQueue中消息为空或没到处理时间），但只要它的消息循环没有被阻塞，能一直处理事件就不会产生ANR异常
- 当没有消息的时候，Looper.loop方法中的queue.next()会进入阻塞的nativePollOnce方法，而此时主线程会释放CPU进入休眠状态直到下个消息的到达，所以也不会很消耗CPU资源

### 4、Handler内存泄露的原因以及处理方式
- **泄漏原因**
  - 当发送延迟消息而Activity关闭之后，延迟消息并未处理，此时MessageQueue会持有Message的引用，而Message又持有Handler的引用，而Handler作为匿名内部类持有了Activity的引用，从而就有了一条引用链（主线程 —> threadlocal —> Looper —> MessageQueue —> Message —> Handler —> Activity）主线程不会被回收从而销毁的Activity亦不能被回收
  - 子线程使用Handler时，运行中的子线程不会被回收，子线程又持有了Activity的引用（不然就无法调用Activity的Handler）从而导致Activity不会被回收

- **处理方式**
    - 静态内部类 或 弱引用
  ```
    MyHandler(WeakReference(this)).sendEmptyMessageDelayed(0, 20000) 
  
    //kotlin中内部类默认为静态内部类 
    class MyHandler(var mActivity: WeakReference<HandlerActivity>):Handler(){ 
      override fun handleMessage(msg: Message?){ 
          super.handleMessage(msg) 
          mActivity.get()?.changeBtn() 
              } 
          }
  ```
    - 在Activity销毁时onDestroy中移除未来得及处理的延迟消息：handler.removeCallbacksAndMessages(null)，取消handler对message的监听（本质上是message.recycleUnchecked，将msg.target置null）防止内存泄漏

### 5、子线程使用Handler要注意什么
<span style="margin-left:2em"/>子线程中使用Handler需要调用Looper.prepare获取Looper对象并调用Looper.loop并在Handler初始化时将looper传入构造器（最后线程执行完毕时需要调用looper.quit）；不过不建议子线程new Handler,直接使用封装好了的HandlerThread更好

### 6、使用Handler.postDelay后消息队列会如何变化
<span style="margin-left:2em"/>使用Handler的postDealy后消息队列可能会进行重新排序。消息队列里消息按执行先后时间进行排序，先执行的在前，后执行的在后。postDealy发送的消息会根据延迟时间与消息队列里存在的消息的执行时间进行比较，然后寻找插入位置插入消息

### 7、子线程中维护的Looper，消息队列无消息的时候的处理方案是什么?
<span style="margin-left:2em"/>子线程中消息队列无消息时，睡眠等待，如没有消息，可调用Looper.quit（内部调用messageQueue.quit将消息回收，并执行nativeWake方法唤醒nativePollOnce退出循环）释放线程。主线程调用quit会抛异常"Main thread not allowed to quit."

### 8、为什么建议使用Message.obtain()来创建Message实例？
<span style="margin-left:2em"/>obtain方法是将一个Message对象的所有数据清空，然后添加到链表头中。sPool就是个消息池，默认的缓存是50个，Looper在分发结束以后，会将用完的消息回收掉，并添加到回收池里从而提高消息的复用。（也可以调用handler.obtainMessage来获取message）

### 9、handler如何实现线程的切换
<span style="margin-left:2em"/>不同的线程发送消息，线程之间的资源是共享的。也就是任何变量在任何线程都可以修改，只要做并发操作就好了。message入队就是加锁的synchronized，Handler中我们使用的是同一个MessageQueue对象，同一时间只能一个线程对消息进行入队操作。消息存储到队列中后，主线程的Looper还在一直循环loop()处理。这样主线程就能拿到子线程存储的Message对象，在我们没有看见的时候完成了线程的切换

### 10、HandlerThread
<span style="margin-left:2em"/>本质上是一个继承了Thread的线程类，只是在run方法中嵌入了Looper对象可进行looper循环以及封装了一个Handler对象，IntentService就用到了HandlerThread

### 11、关于ThreadLocal，谈谈你的理解？
<span style="margin-left:2em"/>ThreadLocal类似于每个线程有一个自己的 ThreadLocalMap 类对象，可以将线程自己的对象保持到其中，各管各的，线程可以正确的访问到自己的对象，相当于每个线程都有自己单独的内存空间，不共享，ThreadLocal在set的时候会将数据存入对应线程的ThreadLocalMap中，key=ThreadLocal，value=值；

## 二、View相关
### 1、事件分发的流程
- 参考：https://www.jianshu.com/p/e99b5e8bd67b  
https://www.jianshu.com/p/238d1b753e64
- 事件分发主要分三块：分发(dispatchTouchEvent)、拦截(ViewGroup独有的onInterceptTouchEvent)以及处理(onTouchEvent)；当我们触摸到屏幕产生点击事件后，首先传递给Activity的dispatchTouchEvent方法，方法会调用getWindow().superDispatchTouchEvent(ev)通过PhoneWindow传递给DecorView,然后再传递给根ViewGroup,进入ViewGroup的dispatchTouchEvent事件的分发方法，然后执行事件拦截方法onInterceptTouchEvent，在不拦截的情况下，此时会遍历ViewGroup的子元素，进入子元素View的dispatchToucnEvent方法，然后执行view的onTouchEvent方法，如果是返回false不消费的话再接着走ViewGroup的消费事件onTouchEvent，如果ViewGroup也不消费即返回false，最后会回到Activity的消费事件onTouchEvent。
- dispathTouchEvent返回true表示事件被消费，不再向子View分发（即事件终止）;返回false也表示不再向子View分发，会执行父View的onTouchEvent，默认返回super执行默认流程
- onInterceptTouchEvent返回true表示拦截，事件不再向下分发会执行自身的onTouchEvent，默认的话会继续向上执行onTouchEvent；默认返回super（即false）
- onTouchEvent返回true表示自行消费处理不再向上传，默认返回super（即false）则继续向上执行直至activity的onTouchEvent
  ***理解ACTION_DOWN、ACTION_MOVE和ACTION_UP时主要看onTouchEvent谁返回true（谁最终处理），以后的ACTION_MOVE和ACTION_UP时就是否有必要执行onInterceptTouchEvent和activity/viewGroup/view的onTouchEvent***

![img](https://note.youdao.com/yws/api/personal/file/WEBbf5313915fbd96c9bf1d8cff25012ca2?method=download&shareKey=0eea6c978c28b39791b6c1ee0e05dabf)

### 2、滑动冲突的处理
- 参考：https://www.jianshu.com/p/982a83271327
- **外部拦截法：(由父容器决定事件的传递)**
    <br/>指点击事件都先经过父容器的拦截处理，如果父容器需要此事件就拦截，否则就不拦截。具体方法：需要重写父容器的 onInterceptTouchEvent 方法，在内部做出相应的拦截。

- **内部拦截法：(自己决定事件的传递)**
  <br/>指父容器不拦截任何事件，而将所有的事件都传递给子容器，如果子容器需要 此事件就直接消耗，否则就交由父容器进行处理。具体方法：需要配合requestDisallowInterceptTouchEvent来禁止父控件对自己的拦截行为的方法。

### 3、自定义View
<span style="margin-left:2em"/>一般分为：**扩展、组合、重写**，通常步骤：
1. 在res的values文件夹下新建attrs文件，并在其中获取要使用的属性
2. 继承相关view(假如有合适的view),在构造方法中获取相关的属性
3. 在onMeasure中为属性赋值
4. 在onLayout中设置控件摆放位置(viewGroup)
5. 在onDraw中绘制相关的自定义view
6. 重写onTouchEvent中手势相关方法
7. 在要使用的自定义view中的xml中设置相关属性

### 4、view/viewGroup的绘制流程
<span style="margin-left:2em"/>View的绘制流程是从ActivityThread的handleResumeActivity方法中触发最终调用到ViewRootImpl的performTraversals开始的，它经过measure，layout，draw三个过程最终将View绘制出来。performTraversals会依次调用performMeasure，performLayout，performDraw三个方法，他们会依次调用measure，layout，draw方法，然后又调用了onMeasure，onLayout，dispatchDraw。
- onMeasure：主要测量组件本生的大小，从顶层父View到子View递归调用measure()方法，measure()调用onMeasure()方法，onMeasure()方法完成测量工作
  - MeasureSpec是一个大小跟模式的组合值,MeasureSpec中的值是一个整型（32位）将size和mode打包成一个Int型，其中高两位是mode，后面30位存的是size,mode包含EXACTLY、AT_MOST、UNSPECIFIED三种模式
  - MeasureSpec.EXACTLY（精确模式）：在这种模式下，尺寸的值是多少，那么这个组件的长或宽就是多少，对应MATCH_PARENT和确定的值
  - MeasureSpec.AT_MOST（最大模式）：父组件能给出的最大空间，当前组件的长或宽最大只能为这么大，当然也可以比这个小，对应WRAP_CONETNT
  - MeasureSpec.UNSPECIFIED（未指定模式）：当前组件可以随便用空间，不受限制
- onLayout：确定组件在视图中摆放的位置，从顶层父View到子View递归调用layout()方法，父View将上一步measure()方法得到的子View的布局大小和布局参数，将子View放在合适的位置上
- onDraw：绘制最终的视图，首先ViewRoot创建一个Canvas对象，然后调用onDraw()方法进行绘制。onDraw()方法的绘制流程为：绘制视图背景 —> 绘制画布的图层 —> 绘制View内容 —> 绘制子视图 —> 还原图层 —> 绘制滚动条

### 5、RecyclerView和ListView的区别
<span style="margin-left:2em"/>参考：https://mp.weixin.qq.com/s/_kfQeh2lCprlDADpsZ4X4A

- **布局：** Recycleview布局效果更多，增加了纵向，表格，瀑布流等效果
- **缓存：** RecyclerView缓存了View+ViewHolder+Flag,而ListView只缓存View
- **刷新：** RecyclerView支持局部刷新而ListView只能全局刷新
- **动画：** recyclerView中，内置有许多动画API，例如： notifyDataInserted(), notifyItemMoved()等；如果需要自定义动画效果，可以通过实现（RecyclerView.ItemAnimator类）完成自定义动画效果，然后调用RecyclerView.setItemAnimator()；但是ListView并没有实现动画效果，但我们可以在Adapter自己实现item的动画效果。

### 6、RecyclerView的性能优化
- item减少布局层次
- 数据量过大时分页加载数据，对于新增和删除数据使用DiffUtil来进行局部数据刷新
- 如果item高度是固定的话，可以使用 RecyclerView.setHasFixedSize(true)，来避免requestLayout浪费资源
- 加大RecyclerView的缓存（空间换时间）
  ```
  recyclerView.setItemViewCacheSize(20); 
  recyclerView.setDrawingCacheEnabled(true);
  recyclerView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
  ```
- 优化滑动操作，RecyclerView.addOnScrollListener() 来在滑动过程中停止加载的操作
- 减少不必要的监听器的创建
- 增加Prefetch数据预取功能

### 7、Recycleview有几级缓存，缓存过程？

**四级缓存：** mAttachedScrap(屏幕内)、mCacheViews(屏幕外)、mViewCacheExtension(自定义缓存)、mRecyclerPool(缓存池)

- mAttachedScrap(屏幕内)：屏幕内ItemView的快速重用，无需重新createView和bindView
- mCacheViews(屏幕外)：保存最近移除屏幕的viewHolder，包含数据和position位置信息，当来回滑动时直接复用viewHolder而无需重新bindView
- mViewCacheExtension(自定义缓存)：用户自定义实现的缓存，默认不实现
- mRecyclerPool(缓存池)：当mCacheViews满了之后或更换了adapter，会将mCacheViews中移除的viewHolder放入Pool中，放之前会清除viewHolder的数据，所以复用还会重新bindView

**保存缓存流程：** 
- 插入或删除itemView时，先把屏幕内的ViewHolder保存至AttachedScrap中
- 滑动屏幕的时候，先消失的itemview会保存到CacheView，CacheView大小默认是2，超过数量的话按照先入先出原则，移出头部的itemview保存到RecyclerPool缓存池（如果有自定义缓存就会保存到自定义缓存里），RecyclerPool缓存池会按照itemview的itemtype进行保存，每个itemTyep缓存个数为5个，超过就会被回收。

**获取缓存流程：**
- AttachedScrap中获取，通过position匹配holder——>获取失败，从CacheView中获取，也是通过position获取holder缓存 ——>获取失败，从自定义缓存中获取缓存——>获取失败，从mRecyclerPool中获取 ——>获取失败，重新创建viewholder——createViewHolder并bindview。

### 8、requestLayout，invalidate，postInvalidate 之间的区别？
- requestLayout：会重新调用onMeasure、onLayout、onDraw 来刷新界面
- invalidate：调用 onDraw() 来刷新界面
- postInvalidate ：调用onDraw() 来刷新界面，在非UI线程中调用

### 9、Activity、Window、PhoneWindow、DecorView以及ViewRootImpl的关系
- Activity包含window（实际实现是phoneWindow），phoneWindow包含decorView，decorView包含TitleView和ContentView
- Activity内部有个Window成员，Window是一个抽象类提供绘制窗口的API，Window的实际实例是Window的唯一子类PhoneWindow
- PhoneWindow是Window(抽象类)的唯一子类，每个Activity都会创建一个PhoneWindow对象，他是负责Activity和整个View系统交互的管理类,他包含了一个DecorView对象
- DecorView是整个View的最顶层继承自FrameLayout，一般包括TitleView和ContentView两部分，是在PhoneWindow重写的setContentView中调用的installDecor方法中被创建的
- ViewRootImpl是实现了ViewParent接口的一个类，他并不是一个真正的 View，只是视图管理类，用来掌管View的各种事件，包括requestLayout、invalidate、dispatchInputEvent 等。


## 三、Activity相关
### 1、Activity四大启动模式
- **Standard：默认模式**

  不管任务栈里有没有，每次都新建一个Activity，适用于大多数的Activity

- **singleTop：栈顶复用模式**

  当要启动的Activity存在于栈顶时，直接复用该Activity即可、只会走onNewIntent方法，例如QQ推送消息，点击后的展示页面

- **singleTask：栈内复用模式**

  这是一种单实例模式，当要启动的Activity在栈里面存在时，直接使用栈里面的Activity即可（也会回调onNewIntent），并且此Activity上面的都会被移除掉（clearTop效果），浏览器的主页面只启动一次

- **singleInstance：单实例模式**

  Activity会存在一个单独的栈里，一旦该模式的activity的实例已经存在于某个栈中，任何应用在激活该activity时都会重用该栈中的实例，解决了多个task共享一个activity,适合需要程序分离共享页面的情况，例如闹钟的提醒与闹钟的设置分离

### 2、横竖屏切换Activity的生命周期
<span style="margin-left:2em"/>**网上答案千奇百怪，亲测Android11是以下结果**
- 不设置Activity的android:configChanges时或者设置android:configChanges="orientation"或android:configChanges=orientation|keyboardHidden（**Android4.0以后无效**）都跟不设置一样，切屏都会重新重新创建Activity进而重新回调各个生命周期
- 设置Activity的android:configChanges="orientation|screenSize"时，切屏不会重新调用各个生命周期，只会执行onConfigurationChanged方法


## 四、Fragment相关
### 1、Fragment生命周期
 <span style="margin-left:2em"/>onAttach—onCreate—onCreateView—onActivityCreated—onStart—onResume—onPause—onStop—onDestoryView—onDestroy—onDetach

### 2、Fragment懒加载
<span style="margin-left:2em"/>参考：https://www.jianshu.com/p/2201a107d5b5?utm_campaign=hugo

- **Androidx之前**
    - add+show+hide模式下是在内部监听onHiddenChanged方法和isHidden方法来处理默认显示的Fragment
    - ViewPager+Fragment 模式下是监听setUserVisibleHint函数

- **Androidx之后**
    - setMaxLifecycle(showFragment, Lifecycle.State.STARTED)上面做文章
    - viewpager2默认就支持懒加载，在setOffscreenPageLimit()方法设置预加载数量防止重复的创建销毁，将数据加载逻辑放到Fragment的onResume()方法中


## 五、Service相关
### 1、Service启动方式与区别
- **startService：**

  Service会经历onCreate-onStrartCommand，通过stopService停止服务走onDestroy

- **bindService：**

  Service会经历onCreate-onBind，调用者与Service绑定在一起，调用者退出，Service亦会退出，走onUnbind-onDestroy；调用者也可调用unBindService来停止服务

**注意：**
- Service的onCreate只会被调用一次，无论多少次的startService或bindService，Service只被创建一次；如果先是bind了，那么start的时候就直接运行Service的onStart 方法，如果先是start，那么bind的时候就直接运行onBind方法。
- 如果service运行时调用了bindService，这时再调用stopService，service是不会调用onDestroy方法的，service就stop不掉了，只能调用UnbindService, service就会被销毁
- 如果service通过startService 被start之后，多次调用startService 的话，service会多次调用onStart方法。多次调用stopService的话，service只会调用一次onDestroyed方法。
- 如果一个service通过bindService被start之后，多次调用bindService的话，service只会调用一次onBind方法;多次调用unbindService的话会抛出异常

### 2、Service防止被杀死
- **提高进程优先级，降低进程被杀死的概率**
  - 启动前台的service
  - 提升service优先级，在AndroidManifest中设置android:priority = "1000"提升到最高优先级
  - 监控手机锁屏解锁事件，在屏幕锁屏时启动1个像素的 Activity，在用户解锁时将 Activity 销毁掉

- **在进程被杀死后，进行拉活**
  - 注册高频率广播接收器，唤起进程。如网络变化，解锁屏幕，开机等
  - onDestroy方法里重启service：service +broadcast 方式，就是当service走ondestory的时候，发送一个自定义的广播，当收到广播的时候，重新启动service
  - 双进程相互唤起
  - 依靠系统唤起

- **依靠第三方**

    根据终端不同，在小米手机（包括 MIUI）接入小米推送、华为手机接入华为推送；其他手机可以考虑接入腾讯信鸽或极光推送等

### 3、IntentService

-  IntentService是Service的子类，本质上是一个默认开启了单独的工作线程的Service；由于onBind方法默认返回null，所以无法通过bindService的方式启动；无需主动调用stopSelf只要工作线程的任务执行完毕它会自动结束；使用时重写onHandleIntent方法，可以执行耗时操作
- 在Intentservice的onCreate中，通过HandlerThread来开启一条线程，而HandlerThread线程中会跟我们平常用的Handler不太一样，在run方法中创建了looper对象，所以HandlerThread能让IntentService在子线程中使用handler达到耗时操作

## 六、BroadcastReceiver相关

### 1、广播注册方式、种类

<span style="margin-left:2em"/>继承BroadcastReceiver，重写onReceive方法

- **动态注册：**

  ```
  IntentFilter filter =  new IntentFilter("android.provider.Telephony"); 
  MyReceiver receiver = new MyReceiver(); 
  registerReceiver(receiver.filter);
  ```

  非常驻型广播，广播跟随程序的生命周期

- **静态Manifest注册**

  ```
  <receiver android:name=".IncomingSMSReceiver " >
       <intent-filter>
           <action android:name="android.provider.Telephony.SMS_RECEIVED"/>
       <intent-filter>
  <receiver>
  ```
  常驻型广播，即使应用程序关闭后，当有相对应的广播发送过来程序亦会自动运行

- **种类：** 有序广播、无序广播、本地广播

### 2、广播传输的数据是否有限制，是多少，为什么要限制？
 <span style="margin-left:2em"/>广播传输的数据一般经过 IPC，Intent 在传递数据时是有大小限制的，大约限制在 1MB 之内；IPC 需要把数据从内核copy到进程中，每一个进程有一个接收内核数据的缓冲区，默认是1M；如果一次传递的数据超过限制，就会出现异常。


## 七、Kotlin & Jetpack & Gradle

### 1、kotlin高阶函数（let with run apply also）
- 参考：
  - https://blog.csdn.net/u013064109/article/details/78786646
  - https://juejin.cn/post/6975384870675546126

- **let**：作用域函数
    - 结构：object.let{}或object?.let{}
    - 底层：public inline fun <T, R> T.let(block: (T) -> R): R = block(this)；参数为lambda表达式，函数块内可通过it指代该对象，返回值为函数块的最后一行或指定return表达式，若最后一行是赋值语句则返回Unit
    - 作用：
        - 对象进行空检查或者修改其属性
    
- **with**
    - 结构：with(object){}
    
    - 底层：public inline fun <T, R> with(receiver: T, block: T.() -> R): R = receiver.block()；参数为对象和lambda表达式，函数块内可通过it指代该对象，返回值为函数块的最后一行或指定return表达式，若最后一行是赋值语句则返回Unit
    
    - 作用：适用于调用同一个类的多个方法时，可以省去类名重复，直接调用类的方法即可，经常用于Android中RecyclerView中onBinderViewHolder中，数据model的属性映射到UI上；如果是非null的对象并且当函数块中不需要返回值时可使用
    
- **run**
    - 结构：object.run{}
    
    - 底层：public inline fun <T, R> run(block: T.() -> R): R = block()；参数为lambda表达式，函数块内可通过this指代该对象，返回值为函数块的最后一行或指定return表达式，若最后一行是赋值语句则返回Unit
    
    - 作用：适用于计算某个值或限制多个本地变量的范围，可用于let,with函数任何场景。因为run函数是let,with两个函数结合体，准确来说它弥补了let函数在函数体内必须使用it参数替代对象，在run函数中可以像with函数一样可以省略，直接访问实例的公有属性和方法，另一方面它弥补了with函数传入对象判空问题，在run函数中可以像let函数一样做判空处理
    
- **apply** 
    - 结构：object.apply{}
    
    - 底层：public inline fun <T> T.apply(block: T.() -> Unit): T { block(); return this }；参数为lambda表达式，函数块内可通过this指代该对象，返回值是本身（this）
    
    - 作用：apply一般用于用于初始化对象或更改对象属性（activity利用Bundle传值）；
    
- **also**
    - 结构：object.also{}
    
    - 底层：public inline fun T.also(block: (T) -> Unit): T { block(this); return this }；参数为lambda表达式，函数块内可通过it指代该对象，返回值是本身（this）
    
    - 作用：适用于let函数的任何场景，also函数和let很像，只是唯一的不同点就是let函数最后的返回值是最后一行的返回值而also函数的返回值是返回当前的这个对象。一般可用于多个扩展函数链式调用

### 2、协程的概念用法

- https://juejin.cn/post/6844903949686800392?share_token=ea544e5a-7e71-4e57-a2a7-0199659d8d42
- 广义的协程和线程一样都是用来解决异步任务的；kotlin中的协程则有所不同，它其实是线程的一种封装,可以理解为可自动切换线程的线程池，挂起时不需要阻塞线程，能用类似同步的方式进行请求，更好了解决了线程切换，“魔鬼调用”的问题，使得异步调用更优雅更方便

- CoroutineScope.launch、lifecycleScope.launch、viewModelScope.launch去开启一个协程（GlobalScope是生命周期是process级别的，即使Activity或Fragment已经被销毁，协程仍然在执行。所以需要绑定生命周期。lifecycleScope只能在Activity、Fragment中使用，会绑定Activity和Fragment的生命周期、viewModelScope只能在ViewModel中使用，绑定ViewModel的生命周期；通常情况下lifecycleScope、viewModelScope无需手动停止）

    - scope.cancel取消协程作用域内的所有子协程，job.cancel取消单个子协程
    - suspend关键字用于标记挂起函数的关键字,挂起函数必须在另一个挂起函数或者协程中执行
    - withContext函数用来构建一个协程作用域，可以标明作用线程(比如这里的Dispatchers.IO)。这个函数必须在挂起函数或者协程中执行

### 3、 Lifecycler
- **概念：** ：Lifecycler是用于管理Activity和Fragment的生命周期，它是LiveData和viewModel的基础。使用Event和State这两个枚举来跟踪其关联组件的生命周期，
    - Event：Lifecycler 生命周期对应的事件 ON_CREATE、ON_START、ON_RESUME、ON_PAUSE、ON_STOP、ON_DESTROY、ON_ANY
    - State：Lifecycler 生命周期所处的状态 DESTROYED、INITIALIZED、CREATED、STARTED、 RESUMED
- **原理**：在Activity内部加入ReportFragment来获取生命周期回调，使这部分逻辑抽离出来实现Activity的无侵入

### 4、liveData的概念、好处以及原理
- 参考1：https://www.jianshu.com/p/e5e1f96e9dbb
- 参考2：https://juejin.cn/post/6903143273737814029/
- **概念：** liveData是一个具有生命周期感知功能（仅更新处于活跃生命周期状态的应用组件观察者）的可观察的数据存储器类

- **好处**
  - 利用了观察者模式的思想大幅降低互相持有的依赖关系所带来的强耦合性
  - 无缝衔接到MVVM架构中，观察者绑定到lifecycle对象具有生命周期感知能力，当activity销毁时会自行清理避免了内存泄漏和避免在已销毁的activity中更新事件而导致崩溃

- **原理**：
  - LiveData跟LifecycleOwner绑定，能感知生命周期变化，并且只会在LifecycleOwner处于Active状态（STARTED/RESUMED）下通知数据改变；如果用使用setValue或postValue更新数据时数据改变发生在非active状态，数据会变化，但是不发送通知，等owner回到active的状态下，再发送通知；并且会在监听到lifecycleOwner的状态为ONDESTROYED状态时自动移除观察者，从而避免内存泄漏。
  - 粘性事件：LiveData中维护了一个Version，初始值为-1，每次更新数据调用setValue时version会+1，一旦version>-1即数据改变，再设置新的订阅者，会在considerNotify方法中去比较新的订阅者的version(默认-1)，一旦小于当前liveData的version就直接回调onChanged方法并将LiveData中的version赋值给封装了Observer的ObserverWrapper中的version

### 5、viewModel的概念、好处以及原理
- 概念：viewModel是Google官方为MVVM架构弄的一个层级，联系view和model之间的关系，它是以生命周期的方式来存储和管理界面相关的数据

- 好处
  - 不会因为屏幕旋转而销毁，减少状态的维护工作
  - 多个Fragment之间共享数据更方便进行数据的维护和通信
  - 为MVVM量身定做，使解藕更纯粹

- 原理（https://juejin.cn/post/6915012483421831175 ）
  - 关于保存生命周期：是使用了Activity的onRetainNonConfigurationInstance，当Activity因配置改变而销毁时，新的Activity立即创建就会调用此方法；在这个方法中把包裹着的viewModelStore的NonConfigurationInstances(ComponentActivity的静态内部类)返回给ActivityThread保存，在Activity重建的attach回调的时候会拿到这个NonConfigurationInstances
  - 关于保证作用域中的唯一实例：viewModel的创建是通过反射获取的，创建成功后会保存到viewModelStore的一个管理容器（HashMap）中，每次获取都从viewModelStore中提取，进而保证实例的唯一，当然当界面销毁的时候也会调用viewModelStore的clear方法清除集合里的viewModel数据

### 6、Gradle多渠道打包
<span style="margin-left:2em"/>使用友盟的多渠道打包或者使用360加固

## 八、架构相关

### 1、MVC、MVP、MVVM
- 参考：https://juejin.cn/post/6921321173661777933/
- **MVC**
  - Model：主要是数据相关的操作，比如网络请求、数据库相关内容
  - View：一般通过xml界面展示出来的与用户交互的UI展示
  - Controller：主要是进行业务逻辑操作的相关内容，Android中一般activity/Fragment为控制器

    - **优点**： 将view层与model层分离一定程度上降低了耦合性
    - **缺点**：Activity/Fragment既充当了View层也充当了Controller层，耦合性比较高，随着项目复杂度增大，Activity/Fragment代码量骤增且难以维护

- **MVP**
  - 将MVC中的Controller改成Presenter，Presenter通过接口的方式去调用View层进行视图更新，解决了MVC中Activity/Fragment既充当Controller又充当了View耦合度高的问题
  
     - **优点**：view提供接口供Presenter调用使得view与model耦合度进一步降低，业务从Activity/Fragmet中分离出去，把功能逻辑操作都放在了Presenter层，Activity/Fragment只负责视图层的展示，便于后期项目的维护和开发。
    - **缺点**：，Presenter中不仅含有业务逻辑还包含大量的view与model间的手动同步逻辑，而使得Presenter比较笨重；随着视图逐渐复杂，需要编写大量的视图同步接口

- **MVVM**
  - 本质是数据驱动，加入DataBinding数据视图双向绑定机制，使得view与model间的同步逻辑自动化，解决了MVP中Presenter既包含业务逻辑也包含视图同步逻辑稍显笨重的问题，viewModel中不再持有View，数据改变view自动更新
    - **优点**：View和Model使用DataBinding来进行双向绑定，将双方的同步逻辑自动化，项目耦合更低，更易维护
    - **缺点**：过于简单的图形界面并不太适用，有点杀鸡焉用牛刀的感觉；数据绑定的一些逻辑写在了view模版中无法断点调试

### 2、设计模式
- 参考：https://blog.csdn.net/llllllkkkkkooooo/article/details/115360630
- 参考：https://www.jianshu.com/p/d59c64480ed8
- 单例模式（懒汉、饿汉、线程安全饿汉、双重校验锁、静态内部类、枚举类）
- 建造者Builder模式，如AlertDialog
- 观察者模式，如Eventbus、RxJava
- 适配器模式（ListView、RecylerView的Adapter）
- 责任链模式（事件分发）
- 享元模式（Message.obtain共享对象）
- 工厂模式

### 3、组件化、模块化、插件化的区别
- 组件化：主要是为了功能拆分，能够降低功能之间的耦合性，并且组件能单独编译运行
- 模块化：主要解决的是重用的问题，不强调模块可单独编译运行，如统一封装的http请求库
- 插件化：所有组件都是一个apk的特殊组件化，可热更新

## 九、性能优化相关

### 1、内存溢出与内存泄漏
- **内存溢出：(OOM out of memory)**

  当APP所需要的内存超过系统分配的内存就会导致内存溢出程序崩溃，例如加载大图片

- **内存泄漏**

  当一个对象本应被JVM回收但他依然被一个正在使用的对象所持有，从而无法回收(没有用的对象到GC Root是可达的)；内存泄漏也是造成内存溢出的主要原因。

### 2、内存泄漏原因
- 静态变量导致的内存泄露，如静态变量context赋值为activity的this
- 单例模式中传递了Activity的Context/对象，生命周期不对等
- 资源对象未关闭（Cursor、File等）
- 接收器或监测器只注册未取消（广播、EventBus等）
- webView导致的内存泄漏，单独开进程使用AIDL与主进程通信
- 非静态内部类的静态实例，如非静态匿名内部类Handler由于持有外部类Activity的引用所造成的内存泄漏（使用静态内部类+弱应用或者调用removeCallbacksAndMessages移除） 

### 3、ANR
- 描述：Android not responding（应用程序未响应）

- **产生原因：**
  - 事件分发(如点击输入等) 5s未响应 
  - 广播onReceive函数10s未完成 
  - 服务是20s未响应

- **解决方式：**
    - 从logCat或者traces.txt文件定位ANR的原因
    - 主线程中只做跟UI相关的工作，将耗时的操作（如访问网络、Socket通信、查询大量SQL语句、复杂逻辑计算等）放在子线程中， 然后通过 handler、asyncTask等进行异步操作 
    - 广播中如果有耗时操作，建议是放在IntentService中去执行
    - 避免程序中存在大量的线程，使用线程池重用

### 4、APP启动优化
- **界面优化**

  多使用include(提取公共的布局)、merge、约束布局减少嵌套层级（SDK自带工具TraceView）

- **内存优化**

  使用Memory Profiler（AS自带）或LeakCanary来检测内存泄漏

- **apk瘦身**

   lint工具检测资源文件、arm-v7、添加混淆等

- **开屏冷启动**

  - 冷启动：系统没有该应用的进程，需要创建一个新的进程分配给应用，所以会先创建和初始化Application类，再创建和初始化MainActivity类（包括一系列的测量、布局、绘制），最后显示在界面上。
  - 热启动：从已有的进程中来启动，不会创建和初始化Application类，直接创建和初始化MainActivity类（包括一系列的测量、布局、绘制），最后显示在界面上

  ***优化方案：*** 减少在Application和第一个Activity的onCreate()方法的工作量； 不要让Application参与业务的操作； 不要在Application进行耗时操作； 不要以静态变量的方式在Application中保存数据； 减少布局的复杂性和深度

### 5、WebView优化以及native与js通信

<span style="margin-left:2em"/>WebView初始化慢，初始化一个全局的WebView待用；asset存放至本地等

![img](https://note.youdao.com/yws/api/personal/file/WEB16300cbaf377b8939580fc87f8e8e046?method=download&shareKey=7f8dddabfabb4112c4bb1cae1fdc895d)


## 十、跨进程与Binder机制
### 1、跨进程通信的方式

- 使用Intent传递Bundle的方式，常用于四大组件间的进程间通信
- 文件共享
- 广播，如监听开机广播
- ContentProvider：访问电话本
- AIDL通信/Messenger：实质上是系统提供给我们可以方便实现 Binder 的工具
- socket通信

### 2、Android进程的优先级
​		 前台进程 - 可见进程 -服务进程 - 后台进程 - 空进程

### 3、Binder描述

- BInder是一个实现了IBinder接口的类
- 从进程间通信（IPC）来讲，Binder是Android的一种跨进程通信的方式
- 从Framework层来讲，Binder是ServiceManager连接ActivityManager、WindowManager等各种Manager和响应ManagerService的桥梁
- 从应用层来说，Binder是客户端和服务端进行通信的媒介

### 4、Binder的优势
- 性能好，只需要拷贝一次数据
- 易用性高，减少处理数据同步操作
- 安全性高，支持通信双方进行身份校验

### 5、为什么Intent不能传递大数据
<span style="margin-left:2em"/>Intent传递的数据会存储在Binder事务缓冲区，Binder事务缓冲区的大小是固定有限的（定义在BINDER_VM_SIZE =1M-8k）超过就会引发TransactionTooLargeException异常


## 十一、其他

### 1、Serializable和Parcelable的区别?
- **Serializable：** Java自带的序列化，本质是使用了反射，会在序列化的过程中产生很多临时对象从而造成频繁的GC
- **Parcelable：** Android专有的，本质是将完整的对象分解成Intent支持的数据类型Parcelable序列的数据写在内存中，而Serializable则通过IO写在磁盘所以Parcelable更快但Serializable更持久

### 2、APP换肤
- **静态换肤：** 在APP内部放置多套相同的资源，进行资源的切换，最简单的例子就是白天/黑夜主题切换
```
  AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
```
- **动态换肤：** 运行时动态的加载皮肤包，皮肤包通常为网络下载的资源。通常用第三方成熟的框架[Android-skin-support](https://github.com/ximsfei/Android-skin-support)

### 3、Android版本更新内容
- **Android 5**
  - ART成为默认虚拟机，完全代替Dalvik
  - MaterialDesign设计风格
- **Android 6**
  - 运行时权限
- **Android 7**
  - 调用相机拍照获取图片等需要通过FileProvider来获取
  - 支持多窗口
- **Android 8**
  - 不允许安装位置来源的应用
  - 通知和悬浮窗
- **Android 9**
  - 默认不支持http需要https
  - 前台服务需要添加FOREGROUND_SERVICE 权限
  - 不能在非Activity的环境中（比如Service，Application）启动 Activity，否则会崩溃报错，解决办法就是加上FLAG_ACTIVITY_NEW_TASK
- **Android 10** 
  - 默认开启分区存储，应用只能看到本应用专有的目录（通过 Context.getExternalFilesDir() 访问）以及特定类型的媒体
  - 权限升级，普通应用无法获取设备IMEI等设备信息
  - 暗黑主题、5G支持
- **Android 11** 
  - 分区存储强制执行
  - 添加了所有文件访问权限和更改了电话号码相关的权限
  - 自定义消息框视图被屏蔽（无法自定义Toast不能后台显示/延时显示）
  - APK打包签名需要选中v2
  - 后台位置信息访问权限需前往到设置页面去设置

### 4、ART和Dalvik的区别？
- art上应用启动快，运行快，但是耗费更多存储空间，安装时间长，总的来说ART的功效就是”空间换时间”
- Dalvik是Google公司自己设计用于Android平台的Java虚拟机。Dalvik虚拟机是Google等厂商合作开发的Android移动设备平台的核心组成部分之一，它可以支持已转换为.dex(即Dalvik Executable)格式的Java应用程序的运行，.dex格式是专为Dalvik应用设计的一种压缩格式，适合内存和处理器速度有限的系统。Dalvik经过优化，允许在有限的内存中同时运行多个虚拟机的实例，并且每一个Dalvik应用作为独立的Linux进程执行。独立的进程可以防止在虚拟机崩溃的时候所有程序都被关闭。
- ART：Android操作系统已经成熟，Google的Android团队开始将注意力转向一些底层组件，其中之一是负责应用程序运行的Dalvik运行时。Google开发者已经花了两年时间开发更快执行效率更高更省电的替代ART运行时。ART代表Android Runtime,其处理应用程序执行的方式完全不同于Dalvik，Dalvik是依靠一个Just-In-Time(JIT)编译器去解释字节码。开发者编译后的应用代码需要通过一个解释器在用户的设备上运行，这一机制并不高效，但让应用能更容易在不同硬件和架构上运行。ART则完全改变了这套做法，在应用安装的时候就预编译字节码到机器语言，这一机制叫Ahead-Of-Time(AOT)编译。在移除解释代码这一过程后，应用程序执行将更有效率，启动更快。

  **ART优点：** 系统性能的显著提升、应用启动更快、运行更快、体验更流畅、触感反馈更及时。更长的电池续航能力、支持更低的硬件

  **缺点：** 更大的存储空间占用，可能会增加10%-20%、更长的应用安装时间

### 5、Android 系统架构
- Linux 内核：底层硬件驱动
- 硬件抽象层：硬件组件加载库模块
- Android Runtime（ART）
- 原生C/C++库
- Java API框架 FrameWork层
- 系统应用

### 6、apk打包流程
- 打包资源文件生成R.java
- 处理aidl文件成java文件（假如有aidl文件的话）
- 编译项目源代码生成class文件
- 将class文件转换生成classes.dex文件
- 打包生成apk文件
- 对apk文件进行签名处理
- 对签名后的apk文件进行对齐处理

## 十二、FrameWork以及源码相关
### 1、Android启动过程
- Android系统启动流程的主要经历init进程 -> Zygote进程 –> SystemServer进程 –> 各种系统服务 –> 应用进程等阶段
- 启动电源以及系统启动：当电源按下时引导芯片从预定义的地方（固化在ROM）开始执行，加载引导程序BootLoader到RAM，然后执行。
- 引导程序BootLoader：BootLoader是在Android系统开始运行前的一个小程序，主要用于把系统OS拉起来并运行。
- Linux内核启动：当内核启动时，设置缓存、被保护存储器、计划列表、加载驱动。当其完成系统设置时，会先在系统文件中寻找init.rc文件，并启动init进程。
- init进程启动：初始化和启动属性服务，并且启动Zygote进程。
- Zygote进程启动：创建JVM并为其注册JNI方法，创建服务器端Socket，启动SystemServer进程。
- SystemServer进程启动：启动Binder线程池和SystemServiceManager，并且启动各种系统服务。
- Launcher启动：被SystemServer进程启动的AMS会启动Launcher，Launcher启动后会将已安装应用的快捷图标显示到系统桌面上。

### 2、APP启动过程
- APP启动首先是从launcher（启动器）上APP的图标被点击开始的，点击之后转到Instrumentation类的startActivity方法
- Instrumentation通过跨进程通信告知AMS要启动某个应用的需求
- AMS反馈launcher让其进入paused状态
- launcher进入paused状态，AMS转到ZygoteProcess类，并通过socket跟Zygote通信告知zygote需要新建进程（不用binder是由于初始化binder线程池的ServiceManager不能保证在zygote之前初始化完成）
- zygote fork进程，并调用ActivityThread的main方法，也就是app的入口
- ActivityThread的main方法新建了ActivityThread实例，并新建了Looper实例，开始loop循环。
- 同时ActivityThread也告知AMS，进程创建完毕，开始创建Application，Provider，并调用Applicaiton的attach，onCreate方法。
- 最后就是创建Context，通过类加载器加载Activity，调用Activity的onCreate方法。

### 3、EventBus源码分析
- EventBus.getDefault()：利用getDefault双重校验锁单例的方式来获取EventBus实例，从而获得一条默认的事件总线,当然你也可以通过默认构造器（public）创建多条事件总线
- EventBus构造方法中传入默认的DEFAULT_BUILDER对象（EventBusBuilder存放一些默认配置）对相关属性进行初始化，也包括粘性事件ConcurrentHashMap
- register(Object subscriber)：注册事件，利用反射来获取当前注册者类或父类中使用了@Subscribe的所有方法缓存起来并调用subscribe方法来订阅
- post：发送普通事件，post方法将发送的事件保存到List的事件队列，然后循环遍历出队列将事件交给postSingleEvent来处理， 最终会调用postToSubscription方法，postToSubscription方法拿到事件后判断事件是否处于UI线程，不是的话采用Handler去处理，是则直接用反射调用被观察的方法，并且他会根据threadMode判断在哪个线程中运行
- postSticky：发送粘性事件，主要是将事件类型和对应事件保存到stickyEvents中并执行post方法，post方法的最后一段代码会判断当前subscribe注解的sticky属性为true时遍历stickyEvents进行处理
- unregister：通过typeBySubscriber获取当前订阅者所有的事件类型循环遍历每一个事件类型，并删除当前订阅者订阅的方法取消订阅。

### 4、okhttp源码解析
- **源码分析**
  - 首先通过建造者模式创建OkHttpClient和Request
  - OkHttpClient.newCall(request)实质上调用了RealCall类，它对Request进行了封装
  - RealCall的请求方式分为两种：同步请求和异步请求，两种请求分别调用Dispatcher中的execute和excuted方法，最终都会走getResponseWithInterceptorChain()
  - getResponseWithInterceptorChain()内部将一系列的拦截器构成拦截链，然后链式执行proceed()方法完成网络请求
- **内置拦截器**
  - RetryAndFollowUpInterceptor：重试和失败重定向拦截器 
  - BridgeInterceptor：桥接拦截器，处理一些必须的请求头信息的拦截器 
  - CacheInterceptor：缓存拦截器，用于缓存的读取与更新 
  - ConnectInterceptor：连接拦截器，建立可用的连接，是CallServerInterceptor的基本 
  - CallServerInterceptor：请求服务器拦截器，从服务器读取响应数据
- **使用的设计模式**
  - 建造者模式：OkhttpClient,Request等对象的创建
  - 工厂模式：CacheStrategy的创建
  - 单例模式：Platform类
  - 责任链模式：拦截器的链式调用
  - 观察者模式：WebSocketListener，长链接监听

- **为什么response.body().string() 只能调用一次**
  - string()方法内部通过source拿到字节流以后，直接给closeQuietly悄悄关闭了，这样第二次再去通过source读取就直接报流已关闭的异常了

### 5、Retrofit源码分析
- **原理描述**：
    Retrofit通过java接口以及注解来描述网络请求，并用动态代理的方式生成网络请求的request，然后通过client调用相应的网络框架（默认okhttp）去发起网络请求，并将返回的response通过converterFactorty转换成相应的数据model，最后通过calladapter转换成其他数据方式（如 rxjava/Observable/suspend协程）
  - 通过Retrofit.create(ciass)方法创建出Service interface的实例，从而使得Service中配置的方法变得可用，这是 Retrofit 代码结构的核心；
  - Retrofit.create()方法内部，使用的是Proxy.newProxylnstance()方法来创建Service 实例。这个方法会为参数中的多个 interface (具体到Retrofit来说，是固定传入一个interface)创建一个对象，这个对象实现了所有interface 的每个方法，并且每个方法的实现都是雷同的：调用对象实例内部的一个InvocationHandler成员变量的invoke()方法，并把自己的方法信息传递进去。这样就在实质上实现了代理逻辑：interface中的方法全部由一个另外设定的InvocatioriHandler对象来进行代理操作。并且，这些方法的具体实现是运行时生成interface实例时才确定的，而不是在编译时（虽然在编译时就已经可以通过代码逻辑推断出来）。这就是网上所说的「动态代理机制」的具体含义。
  
- **动态代理和静态代理的区别？**
   - 静态代理类由程序员创建或工具生成代理类的源码，再编译代理类。所谓静态也就是在程序运行前就已经存在代理类的字节码文件，代理类和委托类的关系在运行前就确定了。
   - 动态代理类是在程序运行期间由 JVM 根据反射等机制动态的生成，所以不存在代理类的字节码文件。代理类和委托类的关系是在程序运行时确定。

### 6、Glide源码解析
- **原理简述**
  - Glide.with(context) 创建了一个RequestManager，并实现了加载图片与组件生命周期绑定：在Activity上创建一个透明的ReuqestManagerFragment ，在RequestManagerFragment 中的相应生命周期方法中通过liftcycle 传递给在lifecycle 中注册的LifecycleListener
  - RequestManager.load(url) 创建了一个RequestBuilder对象T 可以是Drawable 对象或是ResourceType 等
  - RequestBuilder.into(view)返 回 的 是 一 个DrawableImageViewTarget, Target 用来最终展示图片的 

- **两种缓存机制**
  - 内存缓存：LruResourceCache(memory)+弱引用 activeResources，防止应用重复将图片读入到内存造成内存资源浪费
  - 磁盘缓存：DiskLruCache，防止应用重复从网络或其它地方下载和读取数据

- **三级缓存原理：** 当Android端需要获得数据时比如获取网络中的图片，首先从内存中查找（按键查找），内存中没有的再从磁盘文件或 sqlite 中去查找，若磁盘中也没有才通过网络获取

![img](https://note.youdao.com/yws/api/personal/file/WEB4adc4cb23b69dd787e63c57e4dab6bbc?method=download&shareKey=9845d8ec51d975e59696ac03a228db49)

- **LruCache 底层实现原理：** LruCache 中 Lru 算法的实现就是通过 LinkedHashMap 来实现的。LinkedHashMap继承于HashMap，它使用了一个双向链表来存储 Map 中的 Entry 顺序关系，对于 get、put、remove 等操作，LinkedHashMap 除了要做 HashMap 做的事情，还做些调整 Entry 顺序链表的工作。LruCache 中将 LinkedHashMap 的顺序设置为 LRU 顺序来实现 LRU 缓存，每次调用 get(也就是从内存缓存中取图片)，则将该对象移到链表的尾端。调用 put 插入新的对象也是存储在链表尾端，这样当内存缓存达到设定的最大值时，将链表头部的对象（近期最少用到的）移除。

### 7、Leakcanary源码解析
- 参考：https://www.jianshu.com/p/261e70f3083f
- 应用启动时LeakCanary在contentProvider中自行注册初始化
- 通过Application.registerActivityLifecycleCallback()监听Activity的onDestroy方法，正常情况下activity在onDestroy后需要立即被回收，onActivityDestroyed方法最终会调用RefWatcher.watch方法
- 检测机制利用了Java的WeakRefernce和ReferenceQueue,通过将Activity/Fragment包装到WeakReference中，被WeakReference包装过的Activity/Fragment对象如果被回收，该WeakReference引用会被放到ReferenceQueue中，通过监测ReferenceQueue里面的内容就能检查到Activity/Fragment是否能够被回收
- 创建dump文件并通过Android工具Debug.dumpHprofData()写入内存泄漏数据，hprof文件将会在另一个前台服务分析
- ***注：线上监控利用JVMTI有美团的probe和腾讯的matrix***