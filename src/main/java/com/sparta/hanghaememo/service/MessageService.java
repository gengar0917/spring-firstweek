package com.sparta.hanghaememo.service;

public class MessageService {
    //MemoService.deleteMemo() 동작 중 password의 일치 여부에 따라 메세지를 출력하는 메서드
    public static String deleteMessage(boolean isSuccess) {
        if(isSuccess == true){
            return "성공적으로 삭제하였습니다.";
        }else{
            return "비밀번호가 일치하지 않습니다.";
        }
    }

    //MemoService.updateMemo() 동작 중 password가 다를 경우 에러메세지를 출력하게 하는 메서드
    public static String updateMessage(boolean isEquals) {
        String fail = "";
        if(isEquals == false){
            fail =  "비밀번호가 일치하지 않습니다.";
        }
        return fail;
    }

    //MemoService.getMemo() 동작 중 id가 다를 경우 에러메세지를 출력하게 하는 메서드
    public static String getMemo() {
        String isNotId = "해당 id의 메모를 찾을 수 없습니다.";
        return isNotId;
    }
}
