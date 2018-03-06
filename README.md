# 从服务器获取七牛云带凭证的url的缓存框架DEMO

## 引用库
[allow](https://github.com/wangdaqi77/allow)

## 使用方式

### 依赖
#### Step 1. 在project的build的repositories{}中添加maven { url 'https://jitpack.io' }
    allprojects {
        repositories {
            ...
            maven { url 'https://jitpack.io' }
        }
    }
#### Step 2. 在module中添加compile 'com.github.wangdaqi77:allow:1.0.7'
    dependencies {
        ...
        compile 'com.github.wangdaqi77:allow:1.0.7'
    }
#### Step 3. 初始化Allow.create().io(new QiNiuIO(context)); 建议在在自定义Application的onCreate()初始化;
    @Override
    public void onCreate() {
        super.onCreate();
        Allow.create().io(IO);
    }
#### Step 4. 具体使用，例如：加载图片
    Allow.load(new QiNiuRuleHander(reqParams), imageView, new OnAllowValueCallBack<ImageView, String>() {
    @Override
    public void success(ImageView target, String allowUrl) {
        // 图片框架加载图片 ...
        Log.i("MainActivity", "url : " + allowUrl);
    }
});

## 需要实现的接口与类

### IO
QiNiuIO，创建请求数据和磁盘缓存框架
##### INet<P>
	QiNiuNetImpl 请求数据，注意：只是模拟从服务器获取凭证url
##### IDisk
	DiskCacheSpImpl   sp缓存

### AbsRuleHandler
QiNiuRuleHander 七牛云凭证的过期规则
