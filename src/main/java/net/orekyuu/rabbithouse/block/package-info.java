/**
 * Blockに関する機能を提供するAPI群です。<br>
 * Blockのロードに関する詳細は{@link net.orekyuu.rabbithouse.block.BlockLoader}を参照して下さい。<br>
 * <br>
 * <br>
 * <b>Blockのロード手順</b><br>
 * "gradlew initRabbitHouse"を実行した時に生成されたBlocks.jsonを編集します。<br>
 *  例を下に示します。<br>
 *  [<br>
 *  &emsp;"blocks" : [<br>
 *  &emsp;&emsp;{<br>
 *  &emsp;&emsp;&emsp;"name" : "Block Name",<br>
 *  &emsp;&emsp;&emsp;"resource" : "Icon Name",<br>
 *  &emsp;&emsp;&emsp;"item" : "ItemBlock class name",<br>
 *  &emsp;&emsp;&emsp;"harvestLevel" : [<br>
 *  &emsp;&emsp;&emsp;&emsp;{ "key" : "shovel", "value" : 0},<br>
 *  &emsp;&emsp;&emsp;&emsp;{ "key" : "pickaxe", "value" : 0},<br>
 *  &emsp;&emsp;&emsp;&emsp;{ "key" : "axe", "value" : 0}<br>
 *  &emsp;&emsp;&emsp;],<br>
 *  &emsp;&emsp;&emsp;"resistance" : 1.5,<br>
 *  &emsp;&emsp;&emsp;"lightLevel" : 0.0,<br>
 *  &emsp;&emsp;&emsp;"lightOpacity" : 0,<br>
 *  &emsp;&emsp;&emsp;"hardness" : 1.5<br>
 *  &emsp;&emsp;}<br>
 *  &emsp;]<br>
 *  ]<br>
 *  <br>
 *  <b>Blocks.jsonの要素</b><br>
 *  配列の中にBlockを表す要素を入れていきます。<br>
 *  <table border="3">
 *      <caption>要素一覧</caption>
 *      <tr><th>要素名</th><th>パラメータ</th><th>省略可否</th></tr>
 *      <tr><td>name</td><td>Blockのシステム名</td><td>N</td></tr>
 *      <tr><td>resource</td><td>アイコンの名前</td><td>N</td></tr>
 *      <tr><td>item</td><td>ItemBlockを継承したクラス名</td><td>Y</td></tr>
 *      <tr><td>harvestLevel</td><td>ブロックの壊しやすさを表す子要素</td><td>Y</td></tr>
 *      <tr><td>key</td><td>ブロックの壊しやすさを設定するキー</td><td>Y</td></tr>
 *      <tr><td>value</td><td>ブロックの壊しやすさを表す値</td><td>Y</td></tr>
 *      <tr><td>resistance</td><td>爆発耐性</td><td>Y</td></tr>
 *      <tr><td>lightLevel</td><td>明るさ0.0～1.0</td><td>Y</td></tr>
 *      <tr><td>lightOpacity</td><td>光の透過度0.0～1.0</td><td>Y</td></tr>
 *      <tr><td>hardness</td><td>硬さ</td><td>Y</td></tr>
 *      <tr><td>class</td><td>インスタンス化するクラス</td><td>Y</td></tr>
 *  </table>
 *  @see net.orekyuu.rabbithouse.block.BlockLoader
 */
package net.orekyuu.rabbithouse.block;