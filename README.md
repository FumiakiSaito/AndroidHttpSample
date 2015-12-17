# AndroidHttpSample

##Volleyを使用したGET/POST処理サンプル

####Volley導入

build.gradle  

```
dependencies {
    compile fileTree(dir: 'libs', include: ['*.jar'])
    testCompile 'junit:junit:4.12'
    compile 'com.android.support:appcompat-v7:23.1.1'
    compile 'com.android.support:design:23.1.1'
    compile 'com.mcxiaoke.volley:library:1.+' ←これを追加
}
```


####AndroidManifest.xmlでパーミッションを許可
```
<uses-permission android:name="android.permission.INTERNET" />
```

