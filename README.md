# IPv6 Address Calculator

## リポジトリについて

IPアドレス計算機です。JavaのCLIで動きます。現在開発中ですが、よく動くものが出来たら他の言語に移植するかもしれません。

リポジトリの名前の通り、IPv6アドレス計算機能を追々実装する予定ですが、今のところ名前詐欺になっています(IPv4アドレスしか計算できないため)。

## 11/16現在実装している主な機能
IPv4アドレスとプレフィックス長を与えることにより、以下の計算ができます。

* サブネットマスク
* ネットワークアドレス
* ブロードキャストアドレス
* 同一ネットワーク内に存在可能なホストの最大数

機能は予告なく変更・削除されることがあります。

### プログラムの動作について
`IPv4Exec.java` を実行した場合の動作について示します。このプログラムは、引数を与えた場合と与えない場合で動作が異なります。

引数として、CIDR表記でアドレスとプレフィックスを与えた場合、標準出力で1行ずつ各種情報を出力してプログラムが終了します。

※ここでのCIDR表記とは、 `192.168.1.2/24` のように、アドレスとプレフィックスを半角スラッシュで区切った表記を指します。

引数を与えなかった場合、対話型でアドレスとプレフィックスを聞きます。ユーザーの入力が終わると、標準出力で1行ずつ各種情報を出力してプログラムが終了します。

### 実装が不十分な箇所など
例外処理やエラー処理が不完全な状態です。

おかしな引数を与えた場合などは例外を吐いて終了する可能性があります。

標準入力で変な値を渡されたときはできるだけエラーメッセージを出力するようにしていますが、たまに例外が出ます。

## ToDo

IPv6アドレスの計算で求めたいもの

* アドレスの種類（GUA or ULA or LLA or MCA or NAT64/DNS64 Address or Loop-back Address）
* 与えられたアドレスプレフィックス配下に存在可能なネットワークの個数

IPv6ではインターフェースIDの長さ（IPv4におけるホスト部）は64ビット固定長なので、インターフェースIDの数は計算しないものとします。