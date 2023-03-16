package com.onlysky.chatroom.controller.service.impl;

import com.onlysky.chatroom.bean.CommonResult;
import com.onlysky.chatroom.controller.service.ChatRoomService;
import com.onlysky.chatroom.util.PrintUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


/**
 * 实现一个聊天室的功能：
 * 要求：
 * 1. 单纯的业务逻辑不包含网络层通信
 * 2. 一个加入聊天室的方法。joinRoom(String roomId,String userId)
 * 3. 一个退出聊天室的方法。leaveRoom(String roomId,String userId)
 * 4. 需要集成springboot+springmvc来实现，使用http来加入聊天室和退出聊天室。要求加入退出聊天室时打印聊天室内除了自己的所有成员。
 * 5. 代码用git托管，写好过后给出git地址
 */
@Service
@Slf4j
public class ChatRoomServiceImpl implements ChatRoomService {

    Map<String, Set<String>> roomMap = new HashMap<String, Set<String>>();

    @Override
    public CommonResult joinRoom(String roomId, String userId) {
        //定义局部变量
        Set<String> userSet = roomMap.get(roomId);
        String printText = PrintUtil.getPrintTextWithoutUserId(userSet, userId);
        //1、聊天室如果不存在，则添加聊天室
        if(userSet == null){
            userSet = new HashSet<>();
            roomMap.put(roomId, userSet);
        }
        //2、用户如果不存在，则进行添加操作
        if(!userSet.contains(userId)){
            userSet.add(userId);
        }

        log.info("RoomId:" + roomId + " userId:" + printText);
        return new CommonResult(200, "");
    }

    @Override
    public CommonResult leaveRoom(String roomId, String userId) {
        //定义局部变量
        Set<String> userSet = roomMap.get(roomId);
        CommonResult commonResult = new CommonResult();
        String printText = PrintUtil.getPrintTextWithoutUserId(userSet, userId);

        //1、聊天室如果不存在，则直接错误响应
        if(userSet == null){
            commonResult.setCode(500);
            commonResult.setMessage("聊天室不存在");
        }
        else {
            //2、如果用户不存在，则直接错误响应
            if(!userSet.contains(userId)){
                commonResult.setCode(500);
                commonResult.setMessage("当前用户并未加入该聊天室");
            }
            else{
                userSet.remove(userId);
                commonResult.setCode(200);
            }
        }
        log.info("RoomId:" + roomId + " userId:" + printText);
        return commonResult;
    }
}
