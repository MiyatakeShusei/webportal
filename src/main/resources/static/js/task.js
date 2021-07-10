/**
 * 削除リンク押下時のアラート表示
 */


function deleteButtonClick(){
    var result = window.confirm('完了したタスクを削除します、よろしいですか？');

 if(result == true) {
  return true;
 } else{
  return false;
 }
}