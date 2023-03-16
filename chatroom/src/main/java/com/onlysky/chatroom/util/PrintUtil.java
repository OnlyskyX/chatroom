package com.onlysky.chatroom.util;

import org.springframework.util.StringUtils;

import java.util.Set;

public class PrintUtil {

    public static String getPrintTextWithoutUserId(Set<String> userIds, String matchId){
        StringBuilder stringBuilder = new StringBuilder();
        if(userIds == null || userIds.size() <= 0){
            return stringBuilder.toString();
        }
        stringBuilder.append("{");
        for(String userId : userIds){
            if(matchId != null && !matchId.equals(userId)){
                stringBuilder.append(userId + " ");
            }
        }
        stringBuilder.append("}");
        return stringBuilder.toString();
    }
}
