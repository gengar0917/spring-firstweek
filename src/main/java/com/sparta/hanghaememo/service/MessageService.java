package com.sparta.hanghaememo.service;

public class MessageService {
    public static String deleteMessage(boolean isSuccess) {
        if(isSuccess == true){
            return "성공적으로 삭제하였습니다.";
        }else{
            return "비밀번호가 일치하지 않습니다.";
        }
    }

    public static String updateMessage(boolean isEquals) {
        String fail = "";
        if(isEquals == false){
            fail =  "비밀번호가 일치하지 않습니다.";
        }
        return fail;
    }

    public static String getMemo() {
        String isNotId = "해당 id의 메모를 찾을 수 없습니다.";
        return isNotId;
    }
}
