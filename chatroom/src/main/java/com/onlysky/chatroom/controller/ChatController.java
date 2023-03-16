package com.onlysky.chatroom.controller;

import com.onlysky.chatroom.bean.CommonResult;
import com.onlysky.chatroom.controller.service.ChatRoomService;
import com.onlysky.chatroom.util.PrintUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;




@RestController
@Slf4j
public class ChatController {

    @Autowired
    ChatRoomService chatRoomService;

    @PostMapping("/chat/joinroom")
    public CommonResult joinRoom(@RequestParam("roomId") String roomId,@RequestParam("userId") String userId){
        return chatRoomService.joinRoom(roomId, userId);
    }

    @PostMapping("/chat/leaveroom")
    public CommonResult leaveRoom(@RequestParam("roomId") String roomId,@RequestParam("userId") String userId){
        return chatRoomService.leaveRoom(roomId, userId);
    }

}
