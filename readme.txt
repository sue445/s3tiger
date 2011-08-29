S3 Tiger
0.0.1

-----------------------------
1. はじめに
これはSlim3用のテストモジュールです。
https://sites.google.com/site/slim3appengine/

S3 Tigerを導入することでSlim3でのテストをちょっとだけ楽にすることができます。

-----------------------------
2. サンプル

public class SomeTest{
	@Test
	public void ignoreOnlyProduction(){
		if(AppEngineUtil.isProduction()){
			return;
		}
		// 本番サーバだけでは動かしたくないテスト
	}

	@Test
	public void runOnlyDevelopment(){
		if(!AppEngineUtil.isDevelopment()){
			return;
		}
		// 開発サーバだけで動かしたいテスト
	}
}

こんな感じに特定の環境では動かしたくないテストというのもあると思います。
（Datastoreのデータはテスト終了時にrollbackされますが、存在するデータを書き換えた場合一緒に消えてしまいます）

一応前述のような分岐を書けば問題ないのですが、同じようなコードをコピペすることになりDRY的にあまりイケていないです。

S3 Tigerを使うことで下記のようにテストを書き直すことができます。

import net.sue445.s3tiger.Slim3;
@RunWith(Slim3.class)
public class SomeTest{
	@IgnoreProduction
	@Test
	public void ignoreOnlyProduction(){
		// 本番サーバだけでは動かしたくないテスト
	}

	@IgnoreDevelopment
	@Test
	public void runOnlyDevelopment(){
		// 開発サーバだけでは動かしたくないテスト
	}
}

このように、アノテーションで制御することでテストコードがすっきりします。

-----------------------------
3. リファレンス

S3 Tigerを使いたいテストクラスには @RunWith(Slim3.class) をつけます
アノテーションは下記が使えます

@IgnoreProduction
	このアノテーションがついたクラスやメソッドは本番サーバではテストが無効になります
	(AppEngineUtil.isProduction() == false の時だけテストが実行されます)

@IgnoreDevelopment
	このアノテーションがついたクラスやメソッドは開発サーバではテストが無効になります
	(AppEngineUtil.isDevelopment() == false の時だけテストが実行されます)

@IgnoreServer
	このアノテーションがついたクラスやメソッドはサーバではテストが無効になります
	(AppEngineUtil.isServer() == false の時だけテストが実行されます)

@IgnoreNotServer
	このアノテーションがついたクラスやメソッドはサーバ以外（つまりEclipseでテストを実行した時）ではテストが無効になります
	(AppEngineUtil.isServer() == true の時だけテストが実行されます)

-----------------------------
4. 動作確認環境
・Eclipse JUnit Plugin @Eclipse 3.7
・Google App Engine 開発用サーバ＆本番サーバ @appengine 1.0.13
　（サーバ上でテストを動かすにはktrwjrを使います）

-----------------------------
5. 使い方
target/s3tiger-xxx.jarをwar/WEB-INF/libに配置してビルドパスに追加します。

-----------------------------
6. 仕様
・Eclipseで動かす時にはappengine-web.xmlの<system-properties>を自動的にシステムプロパティとして設定します。
　（システムプロパティを設定していないとModelに @Attribute(cipher = true) がついている場合にテストが失敗するため）
・jarのビルドにはbuild.xmlのdistタスクを使ってください
　（ktrwjrをデプロイする関係でsrc/mainとsrc/test/の出力先を同じにしてるため
　　そのままmvn packageするとテストコードもjarに含まれてしまう・・・）

-----------------------------
7. おまけ1
junitやhamcrestにないちょっと便利かもしれないMatcherも一緒に入れています^^

サンプル
import static net.sue445.s3tiger.Matchers.*;
assertThat(list, is(ascending(someComparator)));
assertThat(list, is(descending()));
assertThat(str, is(emptyString()));

-----------------------------
8. おまけ2
http://s3tiger-test.appspot.com/ktrwjr/
認証は外しているので誰でもテストを実行できます